
var prefix = "/award_flow"
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
                        // publishTaskId:$("#publishTaskId").val(),
						pageSource:pageSource,
						role:role,
						act:act,
						applyType:applyType,
						keyWord:$("input[name='keyWord']").val()
						// taskId : $('#searchName').val(),
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
						// visible :false,
						field : 'showNum',
						title : '编号'
					},
                    {
                        // visible :false,
                        field : 'proTypeStr',
                        title : '项目类别'
                    },
                    {
                        // visible :false,
                        field : 'enterpriseName',
                        title : '申报单位'
                    },
                    {
                        field : 'major',
                        title : '专业',
                        event: 'editMajor'
                    },

                    {
                        field : 'created',
                        title : '申报日期',
                        event: 'editMajor'
                    },

					{
						field : 'chengguoScience',
						title : '成果'/*,
						formatter: function(value,row,index){
							return '<a href="#" onclick="edit(\''+row.id+'\')">'+row.title+'</a>';
						}*/
					},
					{
						field : 'applyAccount',
						title : '申报账号'
					},
					{
						field : 'applyStat',
						title : '状态'
					},

					// {
					// 	// visible : false,
					// 	field : 'tuandui',
					// 	title : '团队'
					// },
					// {
					// 	// visible : false,
					// 	field : 'person',
					// 	title : '负责人'
					// },
					// {
					//     visible:false,
					//     field:'associationReviewRst',
					// 	title:"审核结果"
					// },
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							let rs_edit_h = s_edit_h;
							let rs_remove_h = s_remove_h;
							let rs_print_h = s_print_h;
							let rs_review_h = s_review_h;
							let rs_cancel_review_h = s_cancel_review_h;
							let rs_download_doc_h = s_download_doc_h;
							console.log("-------------->>seditH=", s_edit_h)
							if (!row.isReview) {
								rs_review_h = 'hidden';
							}
							if (!row.isEdit) {
								//不能编辑操作
								rs_edit_h = 'hidden';
								rs_remove_h = 'hidden';
							}
							if (!row.isCancelReview) {
								rs_cancel_review_h = 'hidden';
							}
							if (!row.isDownloadProDoc) {
								rs_download_doc_h = 'hidden';
							}

							var scoreBtn = "";
							var validateScore = "";
							if(act == 'score' && (role == 'specialist' || role == 'specialist_leader')) {
								//专家或专家组长
								var url = '/chengguo_info/view_chengguo?proId=' + row.id;
								var urlRule = "/specialist/score_rule?proId="+row.id;
								scoreBtn = '<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ url + '\',\'' + row.chengguo + '评分\',\'' + 1888 + '\')"' +
									' title="评分" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-gift"></i></a> '
								    +
									'<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ urlRule + '\',\'' + row.chengguo + '评分标准\',\'' + 1888 + '\')"' +
									' title="评分标准" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-book"></i></a> ';
							}
							if(act == 'review' && role == 'specialist_leader') {
								var url = "/specialist/review_score?proId="+row.id;
								//专家组长审核打分
								validateScore = '<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ url + '\',\'' + row.chengguo + '分数审核\',\'' + 1888 + '\')"' +
									' title="审核分数" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-check"></i></a> ';
							}


							console.log("daadad" + JSON.stringify(row));

                            console.log("daadad" + row.applyInfoId);

							var v =  '<a class="btn btn-primary " href="#" title="查看"  mce_href="#" onclick="toView(\''
								+ row.id
								+ '\',\''+row.chengguo+'\''
								+')">查看</a> ';
							var e = '<a class="btn btn-primary ' + rs_edit_h + '" href="#" title="修改"  mce_href="#" onclick="toEdit(\''
								+ row.id
								+ '\',\''+row.chengguo+'\','
								+ 0
								+')">修改</a> ';
							var f = '<a class="btn btn-success ' + rs_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"> 删除</a> ';

							var g =  '<a class="btn btn-success  ' + rs_print_h + '" href="#" title="打印"  mce_href="#" onclick="printFile(\''
								+ row.id
								+ '\')">打印</a> ';

							var review = '<a class="btn btn-success  '+ rs_review_h + '" href="#" title="审核"  mce_href="#" onclick="reviewUploadDoc(\''
								+ row.id
								+ '\',\''+row.chengguo+'\','
								+ row.procInsId
								+ ')">审核</a> ';
							let subCheckIsHide = row.isSubCheck == 1 ? '' : 'hidden';
							var h = '<a class="btn btn-success ' + rs_edit_h + ' ' + subCheckIsHide + '" href="#" onclick="subCheck(' + row.id + ')" title="提交审核"  mce_href="#">提交审核</a> ';
							var cancelCheck = '<a class="btn btn-success ' + rs_cancel_review_h + '" href="#" onclick="cancelCheck(' + row.id + ')" title="表单审核撤回"  mce_href="#">回收</a> ';

							let rs_review_result_h = row.isReviewResult ? '' : 'hidden';
							let validateUrl = '/chengguo_info/validateResult?proId=' + row.id;
							var reviewResult = '<a class="btn btn-primary ' + rs_review_result_h + '" href="javascript:page(\''+ validateUrl + '\',\'' + row.enterpriseName + '审查结果\',\'' + 1888 + '\')" mce_href="#" title="审查结果">审查结果</a> ';

							var downloadDoc = '<a class="btn btn-primary ' + rs_download_doc_h + '" href="#" onclick="downloadDocFile(' + row.id + ')" mce_href="#" title="下载全部文件">下载</a> ';

							let typeSelList = $("#typeList").html();
							if(typeSelList) {
								typeSelList = typeSelList.replace("IS_UPDATE_TYPE", "isUpdateType(this, '"+row.id+"')");
							}
							return v + review + e + f + g + h + cancelCheck + reviewResult + downloadDoc + scoreBtn + validateScore + typeSelList;
						}
					} ]
			});

	// layui.use('table', function(){
    //       var table = layui.table;
    //       //监听单元格事件
    //       table.on('tool(demoEvent)', function(obj){
    //         var data = obj.data;
    //         if(obj.event === 'editMajor'){
    //           layer.prompt({
    //             formType: 2
    //             ,title: '修改 ID 为 ['+ data.id +'] 的用户签名'
    //             ,value: data.sign
    //           }, function(value, index){
    //             layer.close(index);
	//
    //             //这里一般是发送修改的Ajax请求
	//
    //             //同步更新表格和缓存对应的值
    //             obj.update({
    //               sign: value
    //             });
    //           });
    //         }
    //       });
    // });
}
function downloadDocFile(proId) {
	$.ajax({
		url: "/scienceProgressScience/downloadProDocFiles",
		type: "post",
		data: {
			'proId': proId
		},
		success: function (r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				layer.open({
					type: 2,
					title: "下载文件",
					maxmin: true,
					shadeClose: false, // 点击遮罩关闭层
					area: ['800px', '520px'],
					content: "/common/sysFile/toDownload?url=" + r.zipUrl // iframe的url
				});
			} else {
				layer.msg(r.msg);
			}
		}
	});
}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function subCheck(proId){
	layer.confirm('提交后,无法撤回,确定要提交审核吗？', {
		btn: ['确定', '取消']
	}, function () {
		$.ajax({
			url: "/scienceProgressScience/subCheck",
			type: "post",
			data: {
				'proId': proId
			},
			success: function (r) {
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

function cancelCheck(proId){
	layer.confirm('确定要撤回,重新修改吗？', {
		btn: ['确定', '取消']
	}, function () {
		$.ajax({
			url: "/scienceProgressScience/cancelCheck",
			type: "post",
			data: {
				'proId': proId
			},
			success: function (r) {
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

function toEdit(proId,chengguo,readonly) {
	var toEditDocPage = layer.open({
		zIndex:100,
		type : 2,
		title : '编辑-'+chengguo,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/scienceProgressScience/toEditChengguo?proId='+proId + "&readonly=" +readonly // iframe的url
	});
	layer.full(toEditDocPage)
}

function toView(proId,chengguo){
	toEdit(proId, chengguo, 1)
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



function remove(proId) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/scienceProgressScience/removePro",
			type : "post",
			data : {
				'proId' : proId
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


/***
 * 打印ID
 * @param id
 */
function printExcel(id) {
	var applyPage = layer.open({
		type: 2,
		title: "打印申请个人先进奖",
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['800px', '520px'],
		content: "/scienceProgressScience/printExcel" // iframe的url
	});
	layer.full(applyPage)
}

function applyAwardPro() {
	var applyType = $("#apply_pro_type").val();
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