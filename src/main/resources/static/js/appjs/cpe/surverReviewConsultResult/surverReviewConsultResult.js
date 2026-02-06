
var prefix = "/cpe/surverReviewConsultResult"
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
									field : 'numCopies', 
									title : '册数' 
								},
																{
									field : 'type', 
									title : '' 
								},
																{
									field : 'proIsComplete', 
									title : '是否是完整项目' 
								},
																{
									field : 'checkIsOk', 
									title : '审查程序是否完善' 
								},
																{
									field : 'runIsOneYear', 
									title : '是否运行一年以上' 
								},
																{
									field : 'unitIsConsultCert', 
									title : '申报单位是否有咨询资质' 
								},
																{
									field : 'unitIsFirstComplete', 
									title : '申报单位是否第一完成单位' 
								},
																{
									field : 'bureauIsFirstAward', 
									title : '是否局级一二等奖' 
								},
																{
									field : 'superiorIsOpionion', 
									title : '上级意见及盖章' 
								},
																{
									field : 'applyRstIsAccept', 
									title : '申报成果是否经同行庄家评审或鉴定，经有关单位采纳' 
								},
																{
									field : 'resultIsReport', 
									title : '成果报告书' 
								},
																{
									field : 'evaluateIsCert', 
									title : '评价或鉴定书' 
								},
																{
									field : 'completeIsCert', 
									title : '交(竣)工验收证明' 
								},
																{
									field : 'bureauIsAwardCert', 
									title : '局一、二等奖证书或文件' 
								},
																{
									field : 'unitIsThreeYearSafe', 
									title : '单位三年无事故安全证明' 
								},
																{
									field : 'viewNewIsReport', 
									title : '查新报告(报一等奖)' 
								},
																{
									field : 'contractIsOk', 
									title : '合同' 
								},
																{
									field : 'superiorUnitIsEvaluate', 
									title : '上级主管部门或建设单位评价意见' 
								},
																{
									field : 'reviewIsEvaluate', 
									title : '评估、评审意见' 
								},
																{
									field : 'superiorReviewIsEvaluate', 
									title : '上级审批意见' 
								},
																{
									field : 'buildUnitIsEvaluate', 
									title : '建设单位验收及评价意见' 
								},
																{
									field : 'resultIsAcceptCert', 
									title : '成果采纳证明' 
								},
																{
									field : 'resultIsQualityEval', 
									title : '委托方成果质量评价' 
								},
																{
									field : 'specilistIsEvaluate', 
									title : '专家评审、鉴定意见(后评价报告)' 
								},
																{
									field : 'publishIsCert', 
									title : '发表证明' 
								},
																{
									field : 'highEvaluateIsCert', 
									title : '高度评价证明' 
								},
																{
									field : 'proIsDesc', 
									title : '项目简单介绍' 
								},
																{
									field : 'applyIsReason', 
									title : '申报理由' 
								},
																{
									field : 'economicIsContrast', 
									title : '主要经济指标对比情况' 
								},
																{
									field : 'technologyIsContrast', 
									title : '主要技术成果对比' 
								},
																{
									field : 'newMethodIsOk', 
									title : '采用新理论、新方法、新技术名称及来源' 
								},
																{
									field : 'planIsDrawing', 
									title : '规划图纸' 
								},
																{
									field : 'photoIsOk', 
									title : '照片' 
								},
																{
									field : 'needIsVcd', 
									title : '需要时VCD' 
								},
																{
									field : 'applySourceQuestion', 
									title : '申报材料存在问题' 
								},
																{
									field : 'feedbackInfo', 
									title : '问题反馈情况' 
								},
																{
									field : 'proContact', 
									title : '项目联系人及电话' 
								},
																{
									field : 'mainUser', 
									title : '主要完成人' 
								},
																{
									field : 'reviewResult', 
									title : '形式审查意见' 
								},
																{
									field : 'remarks', 
									title : '备注' 
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