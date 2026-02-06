
var prefix = "/cpe/surverReviewDesignResult"
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
									title : '' 
								},
																{
									field : 'applyUnitIsBear', 
									title : '是否申报单位承担' 
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
									field : 'useTimeIsCert', 
									title : '建设单位证明的投运时间' 
								},
																{
									field : 'technologyIsStart', 
									title : '引进国外技术或中外合作设计，在国内建设且由申报单位进行初设' 
								},
																{
									field : 'fileIsApproval', 
									title : '是否有批准立项文件或初设批复(分期、分单项或单位工程申报）' 
								},
																{
									field : 'investmentIsBeyond', 
									title : '投资是否超概算' 
								},
																{
									field : 'designIsCert', 
									title : '设计资质' 
								},
																{
									field : 'unitIsThreeSafe', 
									title : '单位三年无事故安全证明' 
								},
																{
									field : 'userList', 
									title : '人员名单数' 
								},
																{
									field : 'localGovIsOpinion', 
									title : '地方政府审批意见' 
								},
																{
									field : 'buildIsOpinion', 
									title : '建设方评价意见' 
								},
																{
									field : 'contractIsCert', 
									title : '合同' 
								},
																{
									field : 'superiorIsCert', 
									title : '上级或建设方证明' 
								},
																{
									field : 'localGovIsCert', 
									title : '地方政府证明' 
								},
																{
									field : 'viewNewIsReport', 
									title : '查新报告(报一等奖)' 
								},
																{
									field : 'techFileIsCheck', 
									title : '技术审定文件' 
								},
																{
									field : 'proIsDesc', 
									title : '工程项目简单介绍' 
								},
																{
									field : 'applyIsReason', 
									title : '申报理由' 
								},
																{
									field : 'mainEconomicIsOk', 
									title : '主要经济指标对比情况' 
								},
																{
									field : 'mainTechnologyIsOk', 
									title : '主要技术成果对比情况' 
								},
																{
									field : 'newTechnologyIsOk', 
									title : '采用新技术的名称及来源' 
								},
																{
									field : 'generalLayoutIsOk', 
									title : '总平面图' 
								},
																{
									field : 'workmanshipIsOk', 
									title : '主要工艺流程图' 
								},
																{
									field : 'equTableIsOk', 
									title : '设备表' 
								},
																{
									field : 'workmanshipSysIsOk', 
									title : '新工艺的系统图' 
								},
																{
									field : 'normalGeneralLayoutIsOk', 
									title : '总平面图（平、立、剖面）-普通建筑' 
								},
																{
									field : 'highGeneralLayoutIsOk', 
									title : '总平面图（平、立、剖面）-高层及大跨度' 
								},
																{
									field : 'structuralPicIsOk', 
									title : '结构主要图纸-高层及大跨度' 
								},
																{
									field : 'equPicIsOk', 
									title : '设备主要图纸-高层及大跨度' 
								},
																{
									field : 'surverIsReport', 
									title : '勘察报告-高层及大跨度' 
								},
																{
									field : 'softListIsOk', 
									title : '各专业应用计算机软件表' 
								},
																{
									field : 'completeIsCert', 
									title : '交竣工证明' 
								},
																{
									field : 'economicIsOk', 
									title : '经济' 
								},
																{
									field : 'socialIsOk', 
									title : '社会' 
								},
																{
									field : 'envIsOk', 
									title : '环境' 
								},
																{
									field : 'safeIsOk', 
									title : '安全' 
								},
																{
									field : 'rawMaterialIsOk', 
									title : '工业项目主要原料、材料消耗定额与生产实际值对比表' 
								},
																{
									field : 'qualityIsOk', 
									title : '产品质量设计值与生产实际值对比表' 
								},
																{
									field : 'expendIsOk', 
									title : '公用工程消耗定额设计值与生产实际值对比表' 
								},
																{
									field : 'residueIsOk', 
									title : '工业项目废渣、废液、废气排放量及排放指标设计值与生产实际值对比表' 
								},
																{
									field : 'audioIsNeed', 
									title : '需要时提供项目和新技术有关音像制品' 
								},
																{
									field : 'applySourceQuestion', 
									title : '申报材料存在问题' 
								},
																{
									field : 'feedbackInfo', 
									title : '问题回馈情况' 
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
									title : '' 
								},
																{
									field : 'remarks', 
									title : '' 
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