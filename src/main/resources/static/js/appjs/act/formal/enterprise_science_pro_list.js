
var prefix = "/award_flow/formal"
$(function() {
	load();
});

function selectLoad() {
	var html = "";
	$.ajax({
		url : '/publish_task/lastest_list',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].taskName + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						taskId : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

function load() {
	selectLoad();
	var pageSource = $("#pageSource").val();
	var role = $("#role").val();
	var act = $("#act").val();
	var applyType = $("#applyType").val();
	applyType = !applyType ? "" : applyType;
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/scienceProgressScience/list", // 服务器数据的加载地址
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
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框:"${publishTaskId}
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
                        publishTaskId:$("#publishTaskId").val(),
						pageSource:pageSource,
						role:role,
						act:act,
						applyType:applyType,
						taskId : $('#searchName').val(),
						isValidate:true,//是否是形式审查，true是形式审查，false不是
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
					{
						checkbox : true
					},
					{
						 visible :false,
						field : 'id',
						title : '编号'
					},
					{
					   field: 'showNum',
					   title: '编号'
					},
					{
					  field: 'proCode',
					  title: '项目编号'
					},
                    {
                        // visible :false,
                        field : 'proTypeStrCate',
                        title : '项目类别'
                    },
                    {
                        // visible :false,
                        field : 'proName',
                        title : '项目名称'
                    },
                    {
                        // visible :false,
                        field : 'enterpriseName',
                        title : '申报单位'
                    },
                    {
                        field : 'major',
                        title : '专业',
                    },
                    {
                        field : 'assignedUserName',
                        title : '审查人员',
                    },
                    {
                        field : 'applyStat',
                        title : '审查状态',
                    },
                    {
                        field : 'checkStartTime',
                        title : '审查日期',
                    },
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							let url = '/scienceProgressScience/toEditChengguo?proId=' + row.id +"&readonly=1";
                            var e = '<a class="btn btn-primary" href="javascript:page(\''+ url + '\',\'' + row.chengguo + '\',\'' + 1888 + '\')" mce_href="#" title="信息查看" >信息查看</a> ';

                            let validateUrl = '/chengguo_info/toReview?proId=' + row.id;
                            var d = '<a class="btn btn-primary btn-sm" href="javascript:page(\''+ validateUrl + '\',\'' + row.chengguo + '审查\',\'' + 1888 + '\')" title="形式审查"  mce_href="#" ' +
								'>形式审查</a> ';



							return e + d  ;
						}
					} ]
			});

	layui.use('table', function(){
          var table = layui.table;
          //监听单元格事件
          table.on('tool(demoEvent)', function(obj){
            var data = obj.data;
            if(obj.event === 'editMajor'){
              layer.prompt({
                formType: 2
                ,title: '修改 ID 为 ['+ data.id +'] 的用户签名'
                ,value: data.sign
              }, function(value, index){
                layer.close(index);

                //这里一般是发送修改的Ajax请求

                //同步更新表格和缓存对应的值
                obj.update({
                  sign: value
                });
              });
            }
          });
    });
}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

/***
 * 查看详情
 */
function detail(id,proid) {


    layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content :  '/award_flow/to_edit_chengguo?proId='+id+'&procInsId=0'
    });

}

function reviewUploadDoc(id,chengguo,procInsId) {
   	var pageSource = $("#pageSource").val();
   	var LAYER = pageSource == 'enterprise_validate_menu' ? layer : parent.layer;
	var reviewPage = LAYER.open({
		type : 2,
		title : '形式检查-' + chengguo,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/ass_validate_pro?id=' + id + "&procInsId="+procInsId + "&pageSource="+pageSource // iframe的url
	});
	LAYER.full(reviewPage);
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/publish_award_task' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/publish_award_task/' + id+"/edit" // iframe的url
	});
}
function toUpload(id,chengguo,procInsId) {
	var toUploadDocPage = layer.open({
        zIndex:100,
		type : 2,
		title : '上传资料-'+chengguo,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/to_upload_doc/' + id +"?procInsId="+procInsId // iframe的url
	});
	layer.full(toUploadDocPage)
}

function toEdit(proId,chengguo,procInsId) {
	var toEditDocPage = layer.open({
		zIndex:100,
		type : 2,
		title : '编辑-'+chengguo,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/to_edit_chengguo?proId='+proId+"&procInsId="+procInsId // iframe的url
	});
	layer.full(toEditDocPage)
}

/***
 * 打印
 */
function printFile(id){
    layer.open({
        type: 2,
        title: '状态',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/chengguo_info/print?id=' + id  // iframe的url
    });
}



function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function apply(id){
	layer.open({
		type : 2,
		title : '申请奖项',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/form/' + id // iframe的url
	});
}

function applyAwardPro() {
	var applyType = $("#applyType").val();
	var contentUrl = "";
	var titleName = "";
	if(applyType == "science") {
		contentUrl = prefix + "/chengguo_science";
		titleName = "申请科技进步奖";
	}else if(applyType == "team") {
		contentUrl = prefix + "/apply_team";
		titleName = "申请团队进步奖";
	}

	var applyPage =layer.open({
		type : 2,
		title : titleName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : contentUrl // iframe的url
	});

	layer.full(applyPage)
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
	}, function() {});
}