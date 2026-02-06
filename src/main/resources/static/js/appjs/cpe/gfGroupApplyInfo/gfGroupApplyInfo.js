
var prefix = "/cpe/gfGroupApplyInfo"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            keyWord: $("#keyWord").val(),
                               	keyType: $("#keyType").val(),
                               	taskId: $("#taskId").val(),
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
								    field:'companyName',
								    title:'公司名称'
								},
																{
									field : 'gfName', 
									title : '工法名称' 
								},
																{
									field : 'gfUnit', 
									title : '主要完成单位' 
								},
																{
									field : 'gfRecommendUnit', 
									title : '推荐的单位' 
								},
																{
									field : 'gfAddress', 
									title : '通讯地址' 
								},
																{
									field : 'gfCode', 
									title : '邮编' 
								},
																{
									field : 'gfContact', 
									title : '联系人' 
								},
																{
									field : 'gfPhone', 
									title : '联系电话' 
								},
																{

									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										let rs_edit_h = s_edit_h;
                                      	let rs_remove_h = s_remove_h;
                                      	let rs_print_h = s_print_h;
                                      	let rs_review_h = s_review_h;
                                      	let rs_cancel_review_h = s_cancel_review_h;
                                      	let rs_download_doc_h = s_download_doc_h;
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

                                      	var view = '<a class="btn btn-primary  " href="#" mce_href="#" title="查看" onclick="view(\''
                                      		+ row.proId
                                      		+ '\')">查看</a> ';
                                      	var e = '<a class="btn btn-primary  '+rs_edit_h+'" href="#" mce_href="#" title="修改" onclick="edit(\''
                                      			+ row.proId
                                      			+ '\')">修改</a> ';
                                      	var d = '<a class="btn btn-warning  '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                      			+ row.id + '\',\''
                                      		    + row.proId
                                      			+ '\')">删除</a> ';
                                      	var print = '<a class="btn btn-success  '+ s_print_h +'" href="#" title="打印"  mce_href="#" onclick="print(\''
                                      			+ row.proId
                                      			+ '\')">打印</a> ';
                                      	var download = '<a class="btn btn-success  '+ s_download_doc_h +'" href="#" title="下载"  mce_href="#" onclick="downloadData(\''
                                      		+ row.proId
                                      		+ '\')">下载</a> ';
                                      	var saveCode = '<a class="btn btn-success  ' + s_save_code_h + '"  href="#" title="保存"  mce_href="#" onclick="saveProResultCode(\''
                                      		+ row.proId
                                      		+ '\')">保存</a> ';

                                      	let subCheckIsHide = row.isSubCheck == 1 ? '' : 'hidden';
                                      	var h = '<a class="btn btn-success ' + rs_edit_h + ' ' + subCheckIsHide + '" href="#" onclick="subCheck(' + row.proId + ')" title="提交审核"  mce_href="#">提交审核</a> ';
                                      	var cancelCheck = '<a class="btn btn-success ' + rs_cancel_review_h + '" href="#" onclick="cancelCheck(' + row.proId + ')" title="表单审核撤回"  mce_href="#">回收</a> ';

                                      	var j = '<a class="btn btn-success '+ rs_review_h + '" href="#" title="形式检查"  mce_href="#" onclick="reviewUploadDoc(\''
                                      		+ row.id
                                      		+ '\',\''
                                      		+ row.proId
                                      		+ '\')">形式审查</a> ';

                                      	var specialistScore = '<a class="btn btn-warning  " href="#" title="评分"  mce_href="#" onclick="specialistScore (\''
                                      		+ row.proId
                                      		+ '\',\''
                                      		+ row.taskId
                                      		+ '\')"">评分</a> ';
                                      	var specialistOpinion = '<a class="btn btn-success " href="#" title="评价"  mce_href="#" onclick="specialistOpinion(\''
                                      		+ row.proId
                                      		+ '\',\''
                                      		+ row.taskId
                                      		+ '\')">评价</a> ';

                                      	return saveCode + view + e + d + print + download + h + cancelCheck + j
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
    page(prefix + '/edit/' + id, '工法信息编辑', 20220731);
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