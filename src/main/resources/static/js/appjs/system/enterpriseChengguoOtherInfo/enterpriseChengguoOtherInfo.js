
var prefix = "/system/enterpriseChengguoOtherInfo"
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
									field : 'proId', 
									title : '' 
								},
																{
									field : 'mainTechnologicalInnovation', 
									title : '主要科技创新' 
								},
																{
									field : 'checkFileInfo', 
									title : '验收类文件-文件填写' 
								},
																{
									field : 'checkReport', 
									title : '验收类文件-验收报告' 
								},
																{
									field : 'checkOpinion', 
									title : '验收类文件-验收意见' 
								},
																{
									field : 'checkConclusion', 
									title : '验收类文件-各种验收结论' 
								},
																{
									field : 'checkEvidence', 
									title : '验收类文件-其他证明材料' 
								},
																{
									field : 'thirdData', 
									title : '第三方资料' 
								},
																{
									field : 'specialData', 
									title : '特殊成果资料' 
								},
																{
									field : 'intellectualPropertyType', 
									title : '知识产权类别' 
								},
																{
									field : 'intellectualPropertyName', 
									title : '知识产权名称' 
								},
																{
									field : 'intellectualPropertyCountry', 
									title : '知识产权-国家(地区)' 
								},
																{
									field : 'intellectualPropertyAuthNum', 
									title : '知识产权-授权号' 
								},
																{
									field : 'intellectualPropertyAuthDate', 
									title : '知识产权-授权日期' 
								},
																{
									field : 'intellectualPropertyCertificateNum', 
									title : '知识产权-证书编号' 
								},
																{
									field : 'intellectualPropertyRightUser', 
									title : '知识产权-权利人' 
								},
																{
									field : 'intellectualPropertyInventor', 
									title : '知识产权-发明人' 
								},
																{
									field : 'intellectualPropertyEffectiveStat', 
									title : '知识产权-专利有效状态' 
								},
																{
									field : 'chengguoStandard', 
									title : '成果标准' 
								},
																{
									field : 'thirdOpinion', 
									title : '第三方评价意见及证书' 
								},
																{
									field : 'mainEnterpriseCompanyName', 
									title : '主要完成单位及主要完成人情况-单位名称' 
								},
																{
									field : 'mainEnterpriseCompleter', 
									title : '主要完成单位及主要完成人情况-主要完成人姓名' 
								},
																{
									field : 'mainEnterpriseCompleterGender', 
									title : '主要完成单位及主要完成人情况-性别' 
								},
																{
									field : 'mainEnterpriseCompleterSort', 
									title : '主要完成单位及主要完成人情况-排名' 
								},
																{
									field : 'mainEnterpriseCompleterBirth', 
									title : '主要完成单位及主要完成人情况-出生年月' 
								},
																{
									field : 'mainEnterpriseCompleterId', 
									title : '主要完成单位及主要完成人情况-身份证号' 
								},
																{
									field : 'mainEnterpriseCompleterNation', 
									title : '主要完成单位及主要完成人情况-民族' 
								},
																{
									field : 'mainEnterpriseCompleterCountry', 
									title : '主要完成单位及主要完成人情况-国籍' 
								},
																{
									field : 'mainEnterpriseCompleterEducation', 
									title : '主要完成单位及主要完成人情况-学历' 
								},
																{
									field : 'mainEnterpriseCompleterAdministrativePost', 
									title : '主要完成单位及主要完成人情况-行政职务' 
								},
																{
									field : 'mainEnterpriseCompleterTechnicalTitle', 
									title : '技术职称' 
								},
																{
									field : 'mainEnterpriseCompleterMajorWork', 
									title : '主要完成单位及主要完成人情况-从事的专业' 
								},
																{
									field : 'mainEnterpriseCompleterMajorStudy', 
									title : '主要完成单位及主要完成人情况-所学的专业' 
								},
																{
									field : 'mainEnterpriseCompleterWorkTelphone', 
									title : '主要完成单位及主要完成人情况-办公电话' 
								},
																{
									field : 'mainEnterpriseCompleterWorkMobile', 
									title : '主要完成单位及主要完成人情况-手机' 
								},
																{
									field : 'mainEnterpriseCompleterWorkEmail', 
									title : '主要完成单位及主要完成人情况-电子邮箱' 
								},
																{
									field : 'mainEnterpriseCompleterWorkAddress', 
									title : '通讯地址' 
								},
																{
									field : 'mainEnterpriseCompleterWorkAddrCode', 
									title : '邮编' 
								},
																{
									field : 'mainEnterpriseCompleterPartakeTime', 
									title : '主要完成单位及主要完成人情况-参加本项目的起止时间' 
								},
																{
									field : 'mainEnterpriseCompleterAcademicContribution', 
									title : '主要完成单位及主要完成人情况-主要完成人对本项目的学术贡献 ' 
								},
																{
									field : 'mainEnterpriseContribution', 
									title : '主要完成单位及主要完成人情况-主要完成单位对本项目科技创新和推广应用情况的贡献 ' 
								},
																{
									field : 'mainEnterpriseCompleterAwards', 
									title : '已获奖项' 
								},
																{
									field : 'chengguoBaseDeclaration', 
									title : '成果基本情况-申报书' 
								},
																{
									field : 'created', 
									title : '创建日期' 
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