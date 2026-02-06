
var prefix = "/system/sysDept"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				id : 'deptId',
				code : 'deptId',
                parentCode : 'parentId',
				type : "GET", // 请求数据的ajax类型
				url : prefix + '/list', // 请求数据的ajax的url

				toolbar : '#exampleToolbar',
				dataType : "json", // 服务器返回的数据类型
				pagination: true, // 设置为true会在底部显示分页条
				pageSize: 10, // 如果设置了分页，每页数据条数
				pageNumber: 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				// showColumns : true, // 是否显示内容下拉框（选择显示的列）
				sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者

				// ajaxParams : {
				// 	name:$("#searchName").val(),
				// 	offset:0,
				// 	limit:30
				// }, // 请求数据的ajax的data属性
				queryParams: function (params) {
					return {
						limit: params.limit,
						offset: params.offset,
						name: $('#searchName').val(),
						deptId:$("#deptId").val(),
						sort: 'dept_id',
						order: 'desc'
					};
				},
				expandColumn : '1', // 在哪一列上面显示展开按钮
				striped : true, // 是否各行渐变色
				bordered : true, // 是否显示边框
				expandAll : false, // 是否全部展开
				// toolbar : '#exampleToolbar',
				columns : [
					{
						title : '编号',
						field : 'deptId',
						visible : false,
						align : 'center',
						valign : 'center',
						width : '50px',
						checkbox : true
					},
					{
						field : 'name',
						title : '企业名称',
                        valign : 'center',
						witth :20
					},
					{
						field : 'registerAddr',
						title : '企业注册地',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'address',
						title : '企业地址',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'enterpriseCreditCode',
						title : '社会信用代码',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'contacts',
						title : '联系人',
						valign : 'center',
						witth :20
					},
					{
						field : 'conPhone',
						title : '手机号',
						valign : 'center',
						witth :20
					},
					{
						field : 'telPhone',
						title : '座机',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'zipCode',
						title : '邮编',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'email',
						title : '邮箱',
						valign : 'center',
						witth :20
					},
					{
						field : 'faxNumber',
						title : '传真号',
						valign : 'center',
						visible : false,
						witth :20
					},
					{
						field : 'orderNum',
						title : '排序',
                        align : 'center',
                        valign : 'center',
						visible : false,
					},
					{
						field : 'delFlag',
						title : '状态',
						align : 'center',
                        valign : 'center',
						formatter : function(item, index) {
							if (item == '0') {
								return '<span class="label label-danger">禁用</span>';
							} else if (item == '1') {
								return '<span class="label label-primary">启用</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
                        valign : 'center',
						formatter : function(value, item, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="审批" onclick="edit(\''
								+ item.deptId
								+ '\')">审批</a> ';
							/*var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="add(\''
								+ item.deptId
								+ '\')"><i class="fa fa-plus"></i></a> ';*/
							var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" title="账号管理" ' +
								'target="_parent" href="javascript:page(\'/enterpriseManager/toAccountList?deptId='+item.deptId+'\',\'账号管理\',\'3000\')" ' +
								' data-index="3"  mce_href="#" >账号管理</a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeone(\''
								+ item.deptId
								+ '\')">删除</a> ';
							var f = '<a class="btn btn-success btn-sm＂ href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
								+ item.deptId
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + a + d;
						}
					} ]
			});
}
function reLoad() {
	var opt = {
		query : {
			type : $('#searchName').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function add(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/' + pId
	});
}
function addAccount(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/addAccount/' + pId
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
function removeone(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'deptId' : id
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
			ids[i] = row['deptId'];
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

