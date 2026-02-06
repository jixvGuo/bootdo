
var prefix = "/petroleum_engineering_award/oilProQualityGoldReviewRecord"
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
									field : 'proDescIs', 
									title : '是否有工程简介 1有 0没有' 
								},
																{
									field : 'applyTabelIs', 
									title : '《石油安装工程优质奖申报表》是否有，1有 0没有' 
								},
																{
									field : 'confirmFileIs', 
									title : '证实性材料是否有：1有 0没有' 
								},
																{
									field : 'videoFileIs', 
									title : '影像资料是否有：1有 0没有' 
								},
																{
									field : 'innovateSumIs', 
									title : '工程创新成果总结是否有1有0没有' 
								},
																{
									field : 'confirmCoverIs', 
									title : '证实性材料封皮是否有 1有，0没有' 
								},
																{
									field : 'commitmentIs', 
									title : '承诺书是否有1有0没有' 
								},
																{
									field : 'catalogIs', 
									title : '目录是否存在1存在0没有' 
								},
																{
									field : 'busiLicenseIs', 
									title : '主申报单位（非建设单位申报时）资质证书' 
								},
																{
									field : 'approvalFileIs', 
									title : '工程初步设计批复文件（批复页））' 
								},
																{
									field : 'buildFileIs', 
									title : '工程立项核准或批复文件（批复页）' 
								},
																{
									field : 'contractIs', 
									title : '工程报建批复文件，主要包括：环评批复文件（批复页）、安评批复文件（批复页）、职业卫生批复文件（批复页），建设工程规划许可证，建设用地规划许可证，土地使用证，施工许可证或开工报告' 
								},
																{
									field : 'certificateIs', 
									title : '工程质量监督单位的工程质量评定文件' 
								},
																{
									field : 'completedFileIs', 
									title : '工程专项竣工验收文件，包括：规划、节能、环保、水土保持、消防、安全、职业卫生、档案验收文件等' 
								},
																{
									field : 'checkFileIs', 
									title : '工程竣工验收及备案文件' 
								},
																{
									field : 'auditReporIs', 
									title : '工程竣工决算书或审计报告' 
								},
																{
									field : 'noQualityIs', 
									title : '无安全质量事故、无拖欠农民工工资证明文件' 
								},
																{
									field : 'bureauLevelEnginIs', 
									title : '局级优质工程奖证书' 
								},
																{
									field : 'bureauLevelDesignIs', 
									title : '局级优秀设计奖证书' 
								},
																{
									field : 'signContractIs', 
									title : '主申报单位（非建设单位申报时）与建设单位签订的承包合同' 
								},
																{
									field : 'otherQualityIs', 
									title : '其他说明工程质量的材料（局级级以上QC活动成果、工法等）' 
								},
																{
									field : 'outConfirmCoverIs', 
									title : '境外证实性材料封皮是否有 1有，0没有' 
								},
																{
									field : 'outCommitmentIs', 
									title : '境外承诺书是否有1有0没有' 
								},
																{
									field : 'outCatalogIs', 
									title : '境外目录是否存在1存在0没有' 
								},
																{
									field : 'outBusiLicenseIs', 
									title : '境外主申报单位（非建设单位申报时）资质证书' 
								},
																{
									field : 'outContractLicenseIs', 
									title : '境外对外承包工程经营资格证书' 
								},
																{
									field : 'outBuildFileIs', 
									title : '境外工程立项文件。其中，由国内投资（含对外援建工程）且执行国内相关标准的，应提供政府批复文件（批复页），完全由国外业主投资的项目，提供业主批复文件（批复页）' 
								},
																{
									field : 'outBuildStandIs', 
									title : '境外工程施工承包商务合同和技术协议（主要页及签字盖章页）。其中，执行境外工程建设标准的项目需提供与国内标准比较的对标说明' 
								},
																{
									field : 'outCheckFileIs', 
									title : '境外工程竣工验收资料，以及分部工程、单位工程验收报告' 
								},
																{
									field : 'outUseOpinionIs', 
									title : '境外工程使用单位的评价意见' 
								},
																{
									field : 'outBookOpinionIs', 
									title : '境外中方驻外大使馆经济商务参赞处对工程质量和使用情况的书面意见' 
								},
																{
									field : 'outBureauLevelEnginIs', 
									title : '境外局级优质工程奖证书或局级主管部门出具工程质量评价证明。另外，获得工程所在国质量奖的，需提供参赞处对质量奖级别的鉴定说明' 
								},
																{
									field : 'outBureauLevelDesignIs', 
									title : '境外局级优秀设计奖证书或局级主管部门出具设计质量评价证明' 
								},
																{
									field : 'outNoQualityIs', 
									title : '境外工程项目无安全、质量事故证明。此证明由主申报单位上级行政主管部门出具。' 
								},
																{
									field : 'outOtherQualityIs', 
									title : '其他质量、安全、科技、节能、环保等相关资料' 
								},
																{
									field : 'outContributorIs', 
									title : '突出贡献者名单' 
								},
																{
									field : 'reviewResult', 
									title : '' 
								},
																{
									field : 'opinionDesc', 
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
									field : 'optUid', 
									title : '形式审查用户id' 
								},
																{
									field : 'proId', 
									title : '项目id' 
								},
																{
									field : 'taskId', 
									title : '' 
								},
																{
									field : 'applyId', 
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