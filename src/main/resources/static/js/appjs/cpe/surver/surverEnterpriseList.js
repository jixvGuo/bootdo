
var prefix = "/surverEnterprise"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/get/enterpriseList", // 服务器数据的加载地址
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
								proSubType: $("#proSubType").val(),
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
									field : 'num',
									title : '序号',

								},
							                                    {
								    field : 'enterpriseName',

									title : '企业名称',
							    },

																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var view = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="toUploadSortFile(\''
											+ row.id
											+'\')">上传排序表</a> ';
										let isHide = row.fileUrl ? '' : 'hidden';
										var viewDoc = '<a class="btn btn-primary btn-sm ' + isHide + '" href="#" mce_href="#" title="查看排序表" onclick="toViewUploadDoc(\''
											+ row.enterpriseName
                                            + '\',\''
											+ row.fileUrl
											+'\')">查看排序表</a> ';
										return view + viewDoc;
									}
								} ],
						/**
						 * @param {点击列的 field 名称} field
						 * @param {点击列的 value 值} value
						 * @param {点击列的整行数据} row
						 * @param {td 元素} $element
						 */
						onClickCell: function(field, value, row, $element) {

							if(field === "proCode"){
								$element.attr('contenteditable', true);
								$element.blur(function() {
									let index = $element.parent().data('index');
									let tdValue = $element.html();
									console.log("index" + index);
									console.log("tdValue" + tdValue);
									setCode(tdValue);
								})
							}

						}

					});
}


function toUploadSortFile(departId) {
	let taskId = $("#taskId").val();
	var fileType = 'sur_enter_sort';
	parent.layer.open({
		zIndex:110,
		type : 2,
		title : '上传附件资料',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/award_flow/to_uploadsmall?taskId='+taskId+'&departId=' + departId + '&fileType='+fileType // iframe的url
	});
};

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function toViewUploadDoc(fileName,url) {
        parent.layer.open({
			type : 2,
			title : fileName,
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '800px', '520px' ],
			content : url  // iframe的url
		});
}
