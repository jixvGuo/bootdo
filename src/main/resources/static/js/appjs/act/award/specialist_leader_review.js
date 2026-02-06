var prefix = "/specialist"
$(function() {
	var deptId = '';
	getTreeData();
	load(deptId);
	$("#recommendTable").addClass("hide");
});
var pageSource = $("#pageSource").val();
var columns =  [
					{
						checkbox : true
					},
					{
						// visible :false,
						field : 'id',
						title : '编号'
					},

					{
						field : 'chengguo',
						title : '成果'/*,
						formatter: function(value,row,index){
							return '<a href="#" onclick="edit(\''+row.id+'\')">'+row.title+'</a>';
						}*/
					},
					{
						field : 'major',
						title : '专业'
					},
					{
						// visible : false,
						field : 'tuandui',
						title : '团队'
					},
					{
						// visible : false,
						field : 'person',
						title : '负责人'
					},
					{
					    visible:false,
					    field:'associationReviewRst',
						title:"审核结果"
					},
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-primary btn-sm" href="#" title="上传资料"  mce_href="#" onclick="toUpload(\''
								+ row.id
								+ '\',\''+row.chengguo+'\','
                                + row.procInsId
								+')"><i class="glyphicon glyphicon-upload"></i></a> ';

							if(act == 'score' && (role == 'specialist' || role == 'specialist_leader')) {
								//专家或专家组长
								var url = prefix + '/to_upload_doc/' + row.id +"?procInsId=" + row.procInsId;
								var urlRule = "/specialist/score_rule?proId="+row.id;
								d = '<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ url + '\',\'' + row.chengguo + '评分\',\'' + 1888 + '\')"' +
									' title="评分" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-gift"></i></a> '
								    +
									'<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ urlRule + '\',\'' + row.chengguo + '评分标准\',\'' + 1888 + '\')"' +
									' title="评分" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-book"></i></a> ';
							}
							if(act == 'review' && role == 'specialist_leader') {
								var url = "/specialist/review_score?proId="+row.id;
								//专家组长审核打分
								d = '<a class="btn btn-primary btn-sm" target="_parent" href="javascript:page(\''+ url + '\',\'' + row.chengguo + '分数审核\',\'' + 1888 + '\')"' +
									' title="审核分数" data-index="3"  mce_href="#">' +
									'<i class="glyphicon glyphicon-check"></i></a> ';
							}

							var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';

							var validateIsHidden = !row.associationReviewRst || row.associationReviewRst == 'upload' ? s_assValidatePro_h : "hidden";
							var j = '<a class="btn btn-success btn-sm '+ validateIsHidden + '" href="#" title="形式检查"  mce_href="#" onclick="reviewUploadDoc(\''
								+ row.id
								+ '\',\''+row.chengguo+'\','
								+ row.procInsId
								+ ')"><i class="glyphicon glyphicon-check"></i></a> ';
							if(row.procInsId == '0') {
								j = '';
							}
							//形式审查菜单过来的
							if(pageSource != 'enterprise_validate_menu') {
								j = '<a class="btn btn-success btn-sm '+ validateIsHidden + '" href="#" title="查看"  mce_href="#" onclick="reviewUploadDoc(\''
									+ row.id
									+ '\',\''+row.chengguo+'\','
									+ row.procInsId
									+ ')"><i class="glyphicon glyphicon-eye-open"></i></a> ';
							}
							return e + d + j;
						}
					} ];

function load() {
	var role = $("#role").val();
	var act = $("#act").val();
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/enterprise_pro/list", // 服务器数据的加载地址
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
						role:role
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
				columns :columns
			});
}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '增加用户',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add'
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/sys/user/remove",
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

function getTreeData() {
	$.ajax({
		type : "GET",
		url : prefix + "/awardGroupTree",
		data:{
			order:'asc',
			limit:20,
			offset:0
		},
		success : function(tree) {
			var flg = tree instanceof Object;
			console.log("roles---->",tree,flg);
			var treeData = flg ? tree : JSON.parse(tree);
			loadTree(treeData);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}

function scoreColumns(awardType){
	 var filedName = "";
	 var title = "";
	 var filedOrderNum = "";
	 if(awardType == "science") {
	 	filedName = "scienceAvgScore";
	 	title = "科技进步奖得分";
	 	filedOrderNum = "scienceOrderNum";
	 }else if(awardType == 'team') {
	 	filedName = "teamAvgScore";
	 	title = "团队奖得分";
		filedOrderNum = "teamOrderNum";
	 }else if(awardType == "personal") {
	 	filedName = "personalAvgScore";
	 	title ="先进个人奖得分";
		filedOrderNum = "personalOrderNum";
	 }
	 columns =  [
					{
						checkbox : true
					},
					{
						// visible :false,
						field : 'id',
						title : '编号'
					},
		            {
		          	 // visible :false,
		          	 field : filedOrderNum,
		          	 title : '排序'
		            },
                    {
						field : filedName,
						title : title
					},
					{
						field : 'chengguo',
						title : '项目名称'/*,
						formatter: function(value,row,index){
							return '<a href="#" onclick="edit(\''+row.id+'\')">'+row.title+'</a>';
						}*/
					},
            		{
						field : 'enterpriseName',
						title : '所属单位'
					},
					{
						field : 'major',
						title : '评审组'
					},
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							var j = '<a class="btn btn-success btn-sm" href="#" title="分数复核"  mce_href="#" onclick="reviewScore(\''
								+ row.id
								+ '\',\''+row.chengguo+'\',\''
								+ awardType
								+ '\')"><i class="glyphicon glyphicon-check"></i></a> ';
							//形式审查菜单过来的
							// if(pageSource != 'enterprise_validate_menu') {
							// 	j = '<a class="btn btn-success btn-sm '+ validateIsHidden + '" href="#" title="查看"  mce_href="#" onclick="reviewUploadDoc(\''
							// 		+ row.id
							// 		+ '\',\''+row.chengguo+'\','
							// 		+ row.procInsId
							// 		+ ')"><i class="glyphicon glyphicon-eye-open"></i></a> ';
							// }
							return j;
						}
					} ];
			 $("#exampleTable").bootstrapTable('destroy');
			 load();
}

function reviewColumns() {
	 columns =  [
					{
						checkbox : true
					},
					{
						// visible :false,
						field : 'id',
						title : '编号'
					},

					{
						field : 'chengguo',
						title : '项目名称'
					},
					{
						field : 'scienceAvgScore',
						title : '专业组评分'
					},
		            {
						field : 'scienceIsRecommend',
						title : '是否推荐',
						formatter: function(value,row,index){
							if(row.scienceIsRecommend == "1") {
								return "是";
							}
							return '否';
						}
					},
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {



							var j = '<a class="btn btn-success btn-sm " href="#" title="评审意见"  mce_href="#" onclick="optionReview(\''
								+ row.id
								+ '\',\''+row.chengguo+'\',\''
								+ ''
								+ '\')"><i class="glyphicon glyphicon-check"></i></a> ';

							//形式审查菜单过来的
							// if(pageSource != 'enterprise_validate_menu') {
							// 	j = '<a class="btn btn-success btn-sm '+ validateIsHidden + '" href="#" title="查看"  mce_href="#" onclick="reviewUploadDoc(\''
							// 		+ row.id
							// 		+ '\',\''+row.chengguo+'\','
							// 		+ row.procInsId
							// 		+ ')"><i class="glyphicon glyphicon-eye-open"></i></a> ';
							// }
							return j;
						}
					} ];
			 $("#exampleTable").bootstrapTable('destroy');
			 load();
}
function reviewScore(proId,proName,awardType) {
    var title = proName +"得分复核";
    if(awardType == 'science') {
    	title = proName +"-科技得分复核";
	}else if(awardType == 'team') {
		title = proName +"-团队得分复核";
	}else if(awardType == 'personal') {
		title = proName +"-个人得分复核";
	}
	layer.open({
		type : 2,
		title : title,
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/review_score?proId=' + proId + "&sType="+awardType // iframe的url
	});
}

function optionReview(proId,proName,ext) {
	layer.open({
		type : 2,
		title : "专家评审意见表",
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/option_form?proId=' + proId // iframe的url
	});
}

$('#jstree').on("changed.jstree", function(e, data) {
	console.log("----tree data=",data);
	$("#recommendTable").addClass("hide");
	if (data.selected == -1) {
		var opt = {
			query : {
				selMenu : '',
				majorId:'',
				awardId:''
			}
		}
		// $('#exampleTable').bootstrapTable('refresh', opt);
	} else {
	    var selMenu = data.selected[0];
		var majorId = '';
		var awardId = '';
		if(data.node.parents.length == 3) {
			majorId = data.node.parents[0];
			awardId = data.node.parents[1];
		}
		var opt = {
			query : {
				selMenu : selMenu,
				majorId: majorId,
				awardId: awardId
			}
		}
		if(selMenu == "score_static_personal"){
             //个人打分统计
             scoreColumns("personal");
		}else if(selMenu == "score_static_team") {
             //团队打分统计
			scoreColumns("team");
		}else if(selMenu == "score_static_science") {
             //科技打分统计
			scoreColumns("science");
		}else if(selMenu == 'review') {
			//专家评审意见表
			reviewColumns();
		}else if(selMenu == 'recommend') {
			//专家组推荐意见表
			$.ajax({
				url: '/enterprise_pro/review_specialist_pros',
				data:{
					majorId:majorId
				},
				success:function (res) {
                    if(res) {
                        for(var i=0;i<res.length;i++) {
                        	var proInfo = $("#pro_r_"+res[i].id).attr("id");
                        	if(proInfo) {
                        		continue;
							}
                        	var index = i+1;
                        	var isRecommend = res[i].scienceIsRecommend == 1 ? "是" : "否";
                        	var temp ='<tr style="height:38px;" id="pro_r_'+res[i].id+'">' +
								      '    <td style="text-align:center;width:10%">'+index+'</td>' +
								      '    <td style="text-align:center;width:50%;font-size:10px;">'+res[i].chengguo+'</td>' +
								      '    <td style="text-align:center;width:20%">'+res[i].scienceAvgScore+'</td>' +
								      '    <td style="text-align:center;width:20%">'+isRecommend+'</td>' +
								      '</tr>';
                        	$("#specialistReviewProTitle").after(temp);
						}
					}
				}
			})
			$("#exampleTable").bootstrapTable('destroy');
			$("#recommendTable").removeClass("hide");
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});