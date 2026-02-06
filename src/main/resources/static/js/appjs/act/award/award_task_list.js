
var prefix = "/award_flow"
$(function() {
	load();
});

function load() {
	var awrdId =  localStorage.getItem("enterType") + "" ;//输出
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list_publish_tasks", // 服务器数据的加载地址
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
						limit : params.limit,
						offset : params.offset,
					    name:$('#searchName').val(),
						awardId: awrdId,
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
						// visible :false,
						field : 'id',
						title : '编号'
					},

					{
						field : 'taskName',
						title : '标题'/*,
						formatter: function(value,row,index){
							return '<a href="#" onclick="edit(\''+row.id+'\')">'+row.title+'</a>';
						}*/
					},
					{
						field : 'associationUserName',
						title : '协会负责人'
					},

                    {
                        field : 'taskStatStr',
                        title : '状态',

                    },


                    {

						field : 'publishDate',
						title : '发布时间',
						formatter: function(value,row,index){
							return value.replace(/.[0-9]*$/,'');
						}
					},
					// {
					// 	// visible : false,
					// 	field : 'applyEndDate',
					// 	title : '结束时间',
					// 	formatter: function(value,row,index){
					// 		return value.replace(/.[0-9]*$/,'');
					// 	}
					// },

					// 协会领导：查看 项目汇总 分数查询
					//
					{
						visible:false,
						field :"proId",
						title:"项目id"
					},
					{
						visible:false,
						field:"selSpecialist",
						title:"是否选择专家"
					},
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							var asssociationProList = '<a class="btn btn-primary btn-sm ' + s_assciation_prolist + '" href="#" mce_href="#" title="查看" onclick="viewProList(\''
								+ row.id
								+ '\','
							    + row.awardId
								+')">项目列表</a> ';
                            var a = '<a class="btn btn-primary btn-sm ' + s_watch_h + '" href="#" mce_href="#" title="查看" onclick="watchPro(\''
                                + row.id
                                + '\')">查看</a> ';

							var file = '<a class="btn btn-primary btn-sm ' + s_expert_upload_file_btn + '" href="#" mce_href="#" title="上传附件" onclick="uplaodFile(\''
								+ row.id
								+ '\')">上传材料</a> ';




                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')">编辑</a> ';


                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';

							// var isSpecialAdmin = row.isSpecialAdmin ? '' : ' style="pointer-events:none" disabled="true" ';
							var isSpecialAdmin = '';
							let specGroupAdminUrl = '/scienceProgressScience/toAssignExperts?taskId='+row.id+"&proType=science_progress";
							let managementBtn = row.isSpecialAdmin ? s_management_h : 'hidden';
							var f = '<a class="btn btn-primary btn-sm ' + managementBtn + '" ' + isSpecialAdmin + ' href="javascript:page(\''+ specGroupAdminUrl  +'\',\'专业组管理\')" title="专业组管理"  mce_href="#">专业组管理</a> ';

							let projectScoreBtn = row.isScore ? '' : 'hidden';
							let viewScoreUrl = '/specialist/associationViewScore?taskId='+row.id+"&proType=science_progress";
							if (row.awardId == 2) {
                            	viewScoreUrl = '/surverScore/associationViewScore?taskId=' + row.id
                            }
							var g = '<a class="btn btn-primary btn-sm  ' + projectScoreBtn +  '"  href="javascript:page(\''+ viewScoreUrl  +'\',\'分数查询\')" title="分数查询"  mce_href="#">分数查询</a> ';

							var ha = '<a class="btn btn-success btn-sm '+ s_project_summary +'" href="#" title="项目汇总"  mce_href="#" onclick="listPro(\''
								+ row.id
								+ '\')"> 项目汇总</a> ';//' + s_project_summary +  '





                            // var isAssignFlg = row.isAssign ? '' : ' style="pointer-events:none" disabled="true" ';
							var isAssignFlg = '';
							let taskAssignBtn = row.isAssign ? '' : 'hidden';

							let validateUrl = '/scienceTask/toAssign?taskId=' + row.id;
							if(row.awardId == 3) {
								//QC奖
								validateUrl = '/qcProcess/toAssign?taskId=' + row.id;
							}
							if (row.awardId == 2) {
								validateUrl = '/cpe/suverProcess/toAssign?taskId=' + row.id
							}

							var i = '<a class="btn btn-success btn-sm '+ taskAssignBtn +'" ' + isAssignFlg + ' href="javascript:page(\''+ validateUrl +'\',\'分派\',2022020700)" title="分派项目"  mce_href="#"  >分派</a> ';

							let selSpecialistBtn = row.isSpecialAdmin ? s_selSpecialist_h : 'hidden';
							var j = '<a class="btn btn-success btn-sm ' + selSpecialistBtn + '" href="#" title="选择专家"  mce_href="#" onclick="selectSpecialist(\''
								+ row.id
                                + '\',\''
							    + row.awardId
								+ '\')">分派专家</a> ';
							//取消此种方式的分派专家20220909
							j = '';

							let qcApplyBtn = row.isApply &&  row.awardId == 3 ? s_apply_qc_btn : 'hidden';
							//奖项申报按钮
							var applyQcBtn = '<a class="btn btn-success btn-sm ' + qcApplyBtn + '" href="#" title="QC项目申报"  mce_href="#" onclick="applyAward(\''
								+ row.id
								+ '\',\'qc\')">QC项目申报</a> ';

							//工法奖项申报按钮
							let gfApplyBtn = row.isApply &&  row.awardId == 5 ? s_apply_gf_btn : 'hidden';
							var applyGfBtn = '<a class="btn btn-success btn-sm ' + gfApplyBtn + '" href="#" title="工法项目申报"  mce_href="#" onclick="applyAward(\''
								+ row.id
								+ '\',\'gf\')">工法项目申报</a> ';

							let surverProListBtn = row.awardId == 2 ? s_surver_pro_list_btn : 'hidden';
							var proListBtn =  '<a class="btn btn-success btn-sm ' + surverProListBtn + '" href="#" title="勘察奖项目列表"  mce_href="#" onclick="surverProList(\''
								+ row.id
								+ '\',\'design\')">勘察奖项目列表</a> ';

                            let readonly = row.isApply ? 0 : 1;
							let applySurverBtn = row.isApply &&  row.awardId == 2 ? s_apply_surver_btn : 'hidden';
							var applyAwardBtn = '<select class="btn btn-success btn-sm ' + applySurverBtn + '" onchange="applySurverAward(\''+row.id+'\',this.options[this.options.selectedIndex].value, ' + readonly + ')">' +
								'  <option value="">选择申报项目</option>' +
								'  <option value="excellent">石油工程建设优秀勘察奖</option>' +
								'  <option value="design">石油工程建设优秀设计奖</option>' +
								'  <option value="software">石油工程建设优秀勘察设计计算机软件奖</option>' +
								'  <option value="standard">石油工程建设优秀标准设计奖</option>' +
								'  <option value="consulting">石油工程建设优秀咨询奖</option>' +
								'</select>';

							var surverEnterListBtn = '<a class="btn btn-success btn-sm ' + s_surver_enterprise_list_btn + '" href="#" title="企业列表"  mce_href="#" onclick="toEnterpriseList(\''
								+ row.id
								+ '\')">企业列表</a> ';

                            //是否当前是形式审查状态
                            let checkResultFlg = row.isAssign ? s_check_result_import : 'hidden';
							var checkResultImportBtn = '<a class="btn btn-success btn-sm ' + checkResultFlg + '" href="#" title="导入形式审查结果"  mce_href="#" onclick="uploadFileData(\''
                                + row.id
                                + '\',\'import_check_result'
                                + '\')">导入形式审查结果</a> ';

                            //优质工程奖
                            if(row.awardId == 4) {
                              let readonlyBestPro = row.isApply ? 0 : 1
                              let applyBestProBtn = row.isApply ? s_apply_best_pro_btn : 'hidden';
                              applyAwardBtn = '<select class="btn btn-success btn-sm '+applyBestProBtn+'" onchange="applyBestProAward(\''+row.id+'\',this.options[this.options.selectedIndex].value,' + readonlyBestPro + ')">' +
                             	'  <option value="">选择申报项目</option>' +
                             	'  <option value="bestPro">石油优质工程奖</option>' +
                             	'  <option value="bestProGold">石油优质工程金奖</option>' +
                             	'  <option value="bestProInstall">石油安装工程</option>' +
                             	'</select>';
                              proListBtn =  '<a class="btn btn-success btn-sm ' + s_best_pro_list_btn + '" href="#" title="优质工程奖项目列表"  mce_href="#" onclick="bestProList(\''
                             							    	+ row.id
                             								    + '\')">优质工程奖项目列表</a> ';
                            }

                            //科技奖
							let readonlyScience = row.isApply ? 0 : 1
							let applyTechnologyBtn = row.isApply &&  row.awardId == 1 ? s_apply_technology_btn : 'hidden';
							if(row.awardId == 1) {
                              applyAwardBtn = '<select class="btn btn-success btn-sm '+applyTechnologyBtn+'" onchange="applyTechnologyAward(\''+row.id+'\',this.options[this.options.selectedIndex].value,' + readonlyScience + ')">' +
                              	'  <option value="">选择申报项目</option>' +
                              	'  <option value="science">科学技术奖成果</option>' +
                              	'  <option value="team">先进团队成果</option>' +
                              	'  <option value="personal">先进个人成果</option>' +
                              	'</select>';
							}
							let gfProListBtn = row.awardId == 5 ? s_gf_pro_list_btn : 'hidden';
                            if(row.awardId == 5) {
                              proListBtn =  '<a class="btn btn-success btn-sm ' + gfProListBtn + '" href="#" title="工法奖项目列表"  mce_href="#" onclick="gfProList(\''
							    	+ row.id
								    + '\')">工法奖项目列表</a> ';
                            }

							let scienceProListBtn = row.awardId == 1 ? s_science_pro_list_btn : 'hidden';
							if(row.awardId == 1) {
								proListBtn =  '<a class="btn btn-success btn-sm ' + scienceProListBtn + '" href="#" title="科技奖项目列表"  mce_href="#" onclick="technologyProList(\''
									+ row.id
									+ '\')">科技奖项目列表</a> ';
							}



                            let qcProListBtn = row.awardId == 3 ? s_qc_pro_list_btn : 'hidden';
                            // if(row.awardId == 3){
                            //   proListBtn =  '<a class="btn btn-success btn-sm ' + scienceProListBtn + '" href="#" title="QC奖项目列表"  mce_href="#" onclick="QcProList(\''
							//     	+ row.id
							// 	    + '\')">QC奖项目列表</a> ';
                            // }
							if(row.awardId == 3){
								proListBtn =  '<a class="btn btn-success btn-sm ' + qcProListBtn + '" href="#" title="QC奖项目列表"  mce_href="#" onclick="QcProList(\''
									+ row.id
									+ '\')">QC奖项目列表</a> ';
							}

                            let cleanProBtn = '<a class="btn btn-success btn-sm ' + s_task_pro_clean_btn + '" href="#" title="删除全部项目"  mce_href="#" onclick="cleanTaskAllPro(\''
                                              								+ row.id
                                              								+ '\')">删除</a> ';
							return proListBtn + applyAwardBtn + surverEnterListBtn + checkResultImportBtn + applyQcBtn + applyGfBtn + asssociationProList + a +file + e + d +  i + j + ha + f+ g + cleanProBtn;
						}
					} ],
				onPostBody:function (data) {
				}
			});
}

function cleanTaskAllPro(taskId) {
   layer.confirm('确定要删除选中任务下的全部项目吗？', {
        btn: ['确定', '取消']
      }, function () {
        $.ajax({
            url: "/proTask/remove",
            type: "post",
            data: {
                'taskId': taskId
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

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function toEnterpriseList(taskId) {
	page('/surverEnterprise/toList?readonly=1&taskId=' + taskId, '企业信息列表', 20220218);
}

function uploadFileData(taskId, fileType) {

     parent.layer.open({
        zIndex:110,
	 	type : 2,
	 	title : '上传形式审查结果',
	 	maxmin : true,
	 	shadeClose : false, // 点击遮罩关闭层
	 	area : [ '800px', '520px' ],
	 	content : '/award_flow/to_uploadsmall?proId=0'+'&fileType='+fileType + '&taskId=' + taskId // iframe的url
	 });
}

function viewProList(taskId, awardId) {
	if(awardId == 1) {
		//TODO 暂未开发
	}

	if(awardId == 3) {
		page('/qcAward/view/proList?readonly=1&taskId=' + taskId, 'QC申报项目列表', 20220218);
	}

	if(awardId == 4) {
       //优质工程奖
       page('/enterpriseQualityAward/taskProList?readonly=1&taskId=' + taskId, '优质工程项目列表', 20220221);
	}
}

/***
 * 显示数据处理
 */
function addData(val) {
   var temp = "";
	switch (val) {
		case 1:
            temp = "";
			break;

    }

}
function add() {
	var index =  localStorage.getItem("enterType") + "" ;//输出

	console.log("==========" + index)
	var addPage = layer.open({
		type : 2,
		title : '创建申报任务',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/publish_award_task?awardId='+ index // iframe的url
	});
	layer.full(addPage);
}

function edit(id) {
	var index =  localStorage.getItem("enterType") + "" ;//输出
	var editPublishTaskPage = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/to_publish_task_edit?publishTaskId=' + id + '&awardId=' + index // iframe的url
	});
	layer.full(editPublishTaskPage);
}

/***
 * 查看
 * @param id
 */
function watchPro(id) {
	var index =  localStorage.getItem("enterType") + "" ;//输出

	var watchPublishTaskPage = layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/to_publish_task_watch?publishTaskId=' + id + '&awardId=' + index // iframe的url
    });
    layer.full(watchPublishTaskPage);
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
	// var createProPage = layer.open({
	// 	type : 2,
	// 	title : '创建申请奖项项目',
	// 	maxmin : true,
	// 	shadeClose : false, // 点击遮罩关闭层
	// 	area : [ '800px', '520px' ],
	// 	content : prefix + '/form/' + id // iframe的url
	// });

	var createProPage = layer.open({
		type : 2,
		title : '科技进步奖成果申报',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/chengguo/' + id // iframe的urlf
	});

	layer.full(createProPage);
}

function listPro(id) {
	var listProPage = layer.open({
		zIndex:90,
		type : 2,
		title : '项目列表',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/enterprise_pro/to_list/' + id // iframe的url
	});

	layer.full(listProPage);
}

function assignPro(taskId) {
	//跳转到分配的页面
	layer.open({
		type : 2,
		title : '分派项目',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/scienceTask/toAssign?taskId='+taskId // iframe的url
	});
}
//跳转到选择专家页面
function selectSpecialist(taskId, awardId) {
	if(awardId == '2') {
		page('/cpe/suverProcess/toAddSpecialist?taskId='+taskId,'分派专家',2022041900);
	}else {
		page('/specialist/to_select?taskId='+taskId,'分派专家',2022020700);
	}
}

/**
 * 申报奖项
 * @param taskId
 * @param awardType
 */
function applyAward(taskId, awardType) {
	let url = '';
	let title = '';

	if (awardType == 'qc') {
		url = '/qcAward/view/proList?taskId=' + taskId;
		title = '申报QC奖';
	}

	else if (awardType == 'gf') {
		url = '/gfAward/view/proList?taskId=' + taskId;
		title = '申报工法奖';
	}
	// ↓ 最后的 else 保持不变 ↓
	else {
		layer.msg('申报奖项类型不存在: ' + awardType);
		return;
	}

	page(url, title, 2022031000);
}

function applySurverAward(taskId, awardType, readonly) {
	let url = '';
	let title = '';
	if(awardType == 'design') {
		url = '/surverApply/toApplyDesign?taskId=' + taskId;
		title = '石油工程建设优秀设计奖';
	}else if(awardType == 'software'){
		url = '/surverSoftwareApply/toApplySoftware?taskId=' + taskId + '&readonly=' + readonly;
		title = '石油工程建设优秀勘察设计计算机软件奖';
	}else if(awardType == 'consulting'){
		url = '/surverConsultingApply/toApply?taskId=' + taskId + '&readonly=' + readonly;
		title = '石油工程建设优秀咨询奖';
	}else if(awardType == 'standard'){
		url = '/surverStandardApply/toApply?taskId=' + taskId + '&readonly=' + readonly;
		title = '石油工程建设优秀标准设计奖';
	}else if(awardType == 'excellent') {
		url = '/surverBaseExlentApply/toApply?taskId=' + taskId + '&readonly=' + readonly;
		title = '石油工程建设优秀勘察奖';
	}else {
		return;
	}
	page(url, title,2022032700);
}

//优质工程奖
function applyBestProAward(taskId, awardType) {
    let url = '';
	let title = '';
	if(awardType == 'bestPro') {
		url = '/petroleumEngineering/toQualityList?taskId=' + taskId;
		title = '石油优质工程奖';
	}else if(awardType == 'bestProGold'){
		url = '/petroleumEngineering/toQualityGoldList?taskId=' + taskId;
		title = '石油优质工程金奖';
	}else if(awardType == 'bestProInstall'){
		url = '/petroleumEngineering/toInstallList?taskId=' + taskId;
		title = '石油安装工程';
	}else {
		return;
	}
	page(url, title,2023020800);
}

function applyTechnologyAward(taskId, awardType) {
	let url = '';
	let title = '';
	if(awardType == 'science') {
		url = '/scienceProgressScience/toApplyPros?taskId=' + taskId;
		title = '科学技术奖成果';
	}else if(awardType == 'team'){
		url = '/enterprise_pro/to_apply_team_pros?taskId=' + taskId;
		title = '先进团队成果';
	}else if(awardType == 'personal'){
		url = '/sciencePersonal/toApplyPersonalPros?taskId=' + taskId;
		title = '先进个人成果';
	}else {
		return;
	}
	page(url, title,2022032700);
}

function applyExlentSurverAward(taskId, awardType) {
	let url = '';
	let title = '';
	if(awardType == 'excellent') {
		url = '/surverBaseExlentApply/toApply?taskId=' + taskId;
		title = '石油工程建设优秀勘察奖';
	}else {
		layer.msg('申报奖项类型不存在-'+ awardType);
		return;
	}
	page(url, title,2022032700);
}

function bestProList(taskId) {
    let url = '/petroleumEngineering/toProListMain?taskId=' + taskId;
	let title = '项目列表';
	page(url, title,2023020900);
}

function technologyProList(taskId) {
	let url = '/scienceProgressScience/toProListMain?taskId=' + taskId;
	let title = '项目列表';
	page(url, title,2022051400);

}


function gfProList(taskId) {
	let url = '/gfAward/toProListMain?taskId=' + taskId;
	let title = '项目列表';
	page(url, title,2022051400);
}



function QcProList(taskId) {
    let url = '/qcAward/toProListMain?taskId=' + taskId;
	let title = '项目列表';
	page(url, title,2023022800);
}

function surverProList(taskId, awardType) {
	let url = '/surverPro/toProListMain?taskId=' + taskId;
	let title = '项目列表';
	page(url, title,2022032800);

}

function uploadDoc(proId) {
	layer.open({
		type : 2,
		title : '上报资料',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :  '/award_flow/to_upload_doc/' + proId // iframe的url
	});
}

function viewUploadDoc(proId) {
	layer.open({
		type : 2,
		title : '上报资料',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :  '/award_flow/to_upload_doc/' + proId // iframe的url
	});
}

function resetPwd(id) {
}

/***
 * 分数查询
 * @param id
 */
function scoreQuery(id) {

}

/***
 * 项目汇总
 * @param id
 */
function projectSummary(id) {
}

/***
 *  专业组管理
 * @param id
 */
function professionalManage(id) {
    layer.open({
        type : 2,
        title : '专业组管理',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/scienceProgressScience/toAssignExperts?taskId='+id // iframe的url
    });

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

function uplaodFile(taskId){
	page('/specialistDoc/view_doc?taskId=' + taskId, '上传资料', 2023082500);
}