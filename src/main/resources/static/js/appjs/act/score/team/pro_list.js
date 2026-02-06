
var prefix = "/specialist"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/team/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 100000, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								// {
								// 	checkbox : true
								// },
																{
									field : 'id', 
									title : '序号'
								},
																{
									field : 'proType',
									title : '项目类别'
								},
								                            	{
									field : 'proResultCode',
									title : '项目编号'
								},
																{
									field : 'teamName',
									title : '团队名称'
								},
														 
																{
									field : 'researchDirection',
									title : '专业'
								},
																{
									field : 'proStatStr',
									title : '审核状态'
								},
																{
									field : 'scoreDate',
									title : '打分日期'
								},

																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm  " href="#" mce_href="#" title="评分标准" onclick="openstard()"> 评分标准 </a> ';
										var d = '<a class="btn btn-warning btn-sm  " href="#" title=" "  mce_href="#" onclick="onwatch (\''
                                            + row.proId
                                            + '\',\''
                                            + row.taskId
                                            + '\')"">查看项目</a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title=" "  mce_href="#" onclick="openScore(\''
												+ row.id
												+ '\',' + row.proId + ')">评分</a> ';
										var downloadSupport = '<a class="btn btn-success" href="#" title="查看形式审查结果"  mce_href="#" onclick="viewCheckResult(\''
                                            + row.proId
                                            + '\',\''
                                            + 'science_team'
                                            + '\')">查看形式审查结果</a> ';
                                        var download = downloadSupport;
										return   d + f + download ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
/**
 * 查看形式审查结果
 */
function viewCheckResult(proId, proSubType) {
     let url = '';
     let title = '';
     if (proSubType == 'science_personal') {
         url = '/system/awardStyleValidatePerson/add?personId=0&readonly=1&';
         title = '个人形式审查';
     } else if (proSubType == 'science_team') {
         url = '/system/awardStyleValidateTeam/add?teamId=0&readonly=1&';
         title = '团队奖形式审查';
     } else if (proSubType == 'science_progress') {
         url = '/chengguo_info/toReview?readonly=1&';
         title = '科技奖形式审查';
     }
     if(url) {
        page(url + "proId=" + proId + "&proSubType=" + proSubType, title, 20220414, true);
     }else {
        layer.msg("不存在的奖项类型:"+proSubType);
     }
}

function add() {
	layer.confirm('提交后不可再次进行分数修改,是否确定提交?', {
      btn: ['确定','取消'] //按钮
    }, function(){
		$.ajax({
			cache: true,
			type: "POST",
			url: "/specialist/scoreOver",
			data: {
				scoreType:$("input[name='scoreType']").val()
			},// 你的formid
			async: false,
			error: function (request) {
				parent.parent.layer.alert("Connection error");
			},
			success: function (data) {
				if (data.code == 0) {
					parent.parent.layer.msg("操作成功");
					window.location.reload();
				} else {
					parent.parent.layer.alert(data.msg)
				}

			}
		});
    }, function(){
    });
}

function cancelSubmit(){
	layer.confirm('是否撤销提交?', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			cache: true,
			type: "POST",
			url: "/specialist/scoreCancel",
			data: {
				scoreType:$("input[name='scoreType']").val()
			},// 你的formid
			async: false,
			error: function (request) {
				parent.parent.layer.alert("Connection error");
			},
			success: function (data) {
				if (data.code == 0) {
					parent.parent.layer.msg("操作成功");
					window.location.reload();
				} else {
					parent.parent.layer.alert(data.msg)
				}
			}
		});
	}, function(){
	});
}

/***
 * 科技打分
 */
function openScore(teamId, proId) {
    console.log("评分teamId=" + teamId);
    layer.open({
        type : 2,
        title : '团队打分',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/toScoreProTeam?teamId='+ teamId + "&proId=" + proId // iframe的url
    });
}

function onwatch(id, taskId) {
    layer.open({
        type: 2,
        title: '查看',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/chengguo_team/apply_team/edit?proId=' + id + "&taskId=" + taskId // iframe的url
    });
}

function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

function openstard() {
    layer.open({
        type : 2,
        title : '团队奖评分标准',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/standard/team' // iframe的url
    });
}

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}