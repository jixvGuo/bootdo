
var prefix = "/cpe/surverSoftApplyTableInfo"
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
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : '' 
								},
																{
									field : 'applyMajor', 
									title : '申报专业' 
								},
																{
									field : 'softName', 
									title : '软件 全称 ' 
								},
																{
									field : 'softShortName', 
									title : '软件简称' 
								},
																{
									field : 'softType', 
									title : '软件类型' 
								},
																{
									field : 'softCategory', 
									title : '软件类别' 
								},
																{
									field : 'softNationalStandard', 
									title : '软件符合哪类现行国家规范' 
								},
																{
									field : 'softTaskSource', 
									title : '任务来源' 
								},
																{
									field : 'softStartEnd', 
									title : '开发起止时间' 
								},
																{
									field : 'softTrialTime', 
									title : '试用时间' 
								},
																{
									field : 'identificationDepartment', 
									title : '鉴定部门' 
								},
																{
									field : 'identificationTime', 
									title : '鉴定时间' 
								},
																{
									field : 'evaluationCompany', 
									title : '评测公司' 
								},
																{
									field : 'evaluationTime', 
									title : '评测时间' 
								},
																{
									field : 'editorChief', 
									title : '主编单位' 
								},
																{
									field : 'cooperationUnit', 
									title : '协作单位' 
								},
																{
									field : 'contactName', 
									title : '联系人' 
								},
																{
									field : 'contactPhone', 
									title : '电话/手机' 
								},
																{
									field : 'fax', 
									title : '传真' 
								},
																{
									field : 'securityLevel', 
									title : '保密级别' 
								},
																{
									field : 'osVersion', 
									title : '运行操作系统名称及版本号' 
								},
																{
									field : 'supportEnvironment', 
									title : '支撑环境名称及版本号' 
								},
																{
									field : 'osLan', 
									title : '编程语言名称及版本号' 
								},
																{
									field : 'mainApplicableIndusty', 
									title : '主要适用行业' 
								},
																{
									field : 'mainPurpose', 
									title : '主要用途' 
								},
																{
									field : 'softwareMainFunctions', 
									title : '软件主要功能及创新概述（附查新报告' 
								},
																{
									field : 'comprehensiveSimilarSoftware', 
									title : '该软件与当前国内外同类软件的综合比较（包括存在问题及改进措施） ' 
								},
																{
									field : 'cconomicSocialBenefits', 
									title : '该软件的经济与社会效益 ' 
								},
																{
									field : 'accessoriesCatalog', 
									title : '附件目录 ' 
								},
																{
									field : 'awards', 
									title : '获奖情况 ' 
								},
																{
									field : 'rportingOpinion', 
									title : ' 申报单位意见 ' 
								},
																{
									field : 'optUid', 
									title : '' 
								},
																{
									field : 'proId', 
									title : '' 
								},
																{
									field : 'taskId', 
									title : '' 
								},
																{
									field : 'created', 
									title : '' 
								},
																{
									field : 'updated', 
									title : '' 
								},
																{
									field : 'deleted', 
									title : '' 
								},
																{
									field : 'upRecommendation', 
									title : '上级主管部门（或建设单位）推荐意见 ' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
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
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
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