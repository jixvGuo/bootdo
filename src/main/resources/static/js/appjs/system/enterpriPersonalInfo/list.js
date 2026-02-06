var prefix = "/system/enterpriPersonalInfo"
$(function () {
    load();
});

function selectLoad() {
    var html = "";
    $.ajax({
        url: '/publish_task/lastest_list',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].taskName + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            //点击事件
            $('.chosen-select').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        taskId: params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}

function load() {
    selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        taskId: $('#searchName').val(),
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
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: '编号'
                    },

                    {
                        field: 'userName',
                        title: '姓名'
                    },

                    {
                        field: 'userName',
                        title: '申报单位'
                    },

                    {
                        field: 'major',
                        title: '专业'
                    },

                    // {
                    //     field: 'userName1',
                    //     title: '审查人员'
                    // },

                    // {
                    //     field: 'gender1',
                    //     title: '审查状态'
                    // },
                    // {
                    //     field: 'birthday',
                    //     title: '审查日期'
                    // },


                    {
                        field: 'created',
                        title: '填写日期'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary ' + s_edit_h + '" href="#" mce_href="#" title="修改" onclick="edit(\''
                                + row.applyId
                                + '\','
                                + row.proId
                                + ')"> 修改</a> ';
                            var f = '<a class="btn btn-success" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"> 删除</a> ';

                            var g = '<a class="btn btn-success" href="#" title="打印"  mce_href="#" onclick="printApply(\''
                                + row.id
                                + '\')">打印 </a> ';


                            return e + f + g;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id, proId) {

    console.log("id:" + id);
    console.log("proId:" + proId);
    let readonly = $("#isReadonly").val();
    var editPage = layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/chengguo_personal/apply_personal/edit?applyId=' + id + '&proId=' + proId + "&readonly=" +(readonly == null || readonly == undefined ? 0 : readonly) // iframe的url
    });
}


function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
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


function printApply(id) {
    var applyPage = layer.open({
        type: 2,
        title: "打印申请个人先进奖",
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: "/award_flow/enterprise_personal_print" // iframe的url
    });
    layer.full(applyPage)
}

/**
 * 开始申请奖项
 */
function applyAwardPro() {
    // var applyType = $("#applyType").val();
    // var contentUrl = "";
    // var titleName = "";
    // if(applyType == "science") {
    // 	contentUrl = prefix + "/chengguo_science";
    // 	titleName = "申请科技进步奖";
    // }else if(applyType == "team") {
    // 	contentUrl = prefix + "/apply_team";
    // 	titleName = "申请团队进步奖";
    // }else if(applyType == "personal") {
    // 	contentUrl = prefix + "/apply_personal";
    // 	titleName = "申请个人先进奖";
    // }

    var applyPage = layer.open({
        type: 2,
        title: "申请个人先进奖",
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: "/award_flow/apply_personal" // iframe的url
    });

    layer.full(applyPage)
}


/**
 *  提交审核
 */
function submitReview() {

}


function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}