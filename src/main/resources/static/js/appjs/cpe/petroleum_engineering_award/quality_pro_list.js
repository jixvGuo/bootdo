var prefix = "/petroleumEngineering"
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
                url: prefix + "/qualityList", // 服务器数据的加载地址
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
                        taskId: $("input[name='taskId']").val(),
                        proType: 'oil_quality',
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
                    // {
                    //     checkbox: true
                    // },
                    {
                        field: 'SerialNumber',
                        title: '编号',
                        formatter: function (value, row, index) {
                            return index+1;
                        }
                    },

                    {
                        field: 'proName',
                        title: '工程名称'
                    },

                    {
                        field: 'proUnitName',
                        title: '单位名称'
                    },
                    {
                        field: 'applyStat',
                        title: '申报状态'
                    },
                    {
                        field: 'created',
                        title: '申报日期',
                        formatter: function (value, row, index) {
                            if(value) {
                                value = value.replace(".0","");
                            }
                            return value;
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let rs_edit_h = s_edit_h;
                            let rs_remove_h = s_remove_h;
                            let rs_print_h = s_print_h;
                            let rs_review_h = s_review_h;
                            let rs_cancel_review_h = s_cancel_review_h;
                            let rs_download_doc_h = s_download_doc_h;

                            if(!row.isReview) {
                                rs_review_h = 'hidden';
                            }
                            if(!row.isEdit) {
                                //不能编辑操作
                                rs_edit_h = 'hidden';
                                rs_remove_h = 'hidden';
                            }
                            if(!row.isCancelReview) {
                                rs_cancel_review_h = 'hidden';
                            }
                            if(!row.isDownloadProDoc){
                                rs_download_doc_h = 'hidden';
                            }
                            var v = '<a class="btn btn-primary" href="javascript:page(\'/enterpriseQualityAward/toEditApply?id='+row.applyInfoId+'&proId='+ row.proId + '&readonly=1\',\'' + row.proName + '\',\'' + 202101 + '\', ' + s_parent_view + ')" mce_href="#" title="查看">查看</a> ';
                            var review = '<a class="btn btn-primary ' + rs_review_h + '" href="javascript:page(\'/enterpriseQualityAward/toReview?applyInfoId='+row.applyInfoId+'&proId='+ row.proId + '\',\'' + row.proName + '\',\'' + 202101 + '\')" mce_href="#" title="审核">审核</a> ';
                            var e = '<a class="btn btn-primary ' + rs_edit_h + '" href="javascript:page(\'/enterpriseQualityAward/toEditApply?id='+row.applyInfoId+'&proId='+ row.proId + '\',\'' + row.proName + '\',\'' + 202101 + '\')" mce_href="#" title="修改">修改</a> ';
                            var f = '<a class="btn btn-success ' + rs_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.proId
                                + '\')"> 删除</a> ';
                            var g = '<a class="btn btn-success ' + rs_print_h + '" href="/enterpriseQualityAward/toPrintMatirals?id='+row.applyInfoId+'&proId='+row.proId+'" title="打印"  mce_href="#">打印 </a> ';



                            // var g = '<a class="btn btn-success ' + rs_print_h + '" href="#?id='+row.companyId+'&proId='+row.proId+'" title="打印"  mce_href="#">打印 </a> ';

                            // var g = '<a class="btn btn-success  " href="#" title="打印"  mce_href="#" onclick="print(\''
                            //     + row.id
                            //     + '\')">打印</a> ';

                            let subCheckIsHide = row.isSubCheck == 1 ? '' : 'hidden';
                            var h = '<a class="btn btn-success ' + rs_edit_h + ' ' + subCheckIsHide+'" href="#" onclick="subCheck('+row.proId+')" title="提交审核"  mce_href="#">提交审核</a> ';
                            var cancelCheck = '<a class="btn btn-success ' + rs_cancel_review_h + ' '+subCheckIsHide+'" href="#" onclick="cancelCheck('+row.proId+')" title="表单审核撤回"  mce_href="#">回收</a> ';

                            let rs_review_result_h = row.isReviewResult ? '' : 'hidden';
                            var reviewResult = '<a class="btn btn-primary ' + rs_review_result_h + '" href="javascript:page(\'/petroleumEngineering/viewQualityCheckResultList?id='+row.applyInfoId+'&proId='+ row.proId + '&readonly=1\',\'' + row.proName + '\',\'' + 202102 + '\')" mce_href="#" title="审查结果">审查结果</a> ';
                            var downloadDoc = '<a class="btn btn-primary ' + rs_download_doc_h + '" href="#" onclick="downloadDocFile(' + row.proId + ')" mce_href="#" title="下载全部文件">下载</a> ';

                            return v + review + e + f + g + h + cancelCheck + reviewResult + downloadDoc;
                        }
                    }]
            });
}


// 状态点击事件
function print(id, taskId) {
    layer.open({
        type: 2,
        title: '状态',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/enterpriseTeam/print?id=' + id  // iframe的url
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

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/removeQualityPro",
            type: "post",
            data: {
                'proId': id
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

function subCheck(proId){
    layer.confirm('提交后,无法撤回,确定要提交审核吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/subCheck",
            type: "post",
            data: {
                'proId': proId
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

function cancelCheck(proId){
    layer.confirm('确定要撤回,重新修改吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/cancelCheck",
            type: "post",
            data: {
                'proId': proId
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

function downloadDocFile(proId){
    $.ajax({
        url: prefix + "/downloadProDocFiles",
        type: "post",
        data: {
            'proId': proId
        },
        success: function (r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                layer.open({
                    type: 2,
                    title: "下载文件",
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: "/common/sysFile/toDownload?url=" + r.zipUrl // iframe的url
                });
            } else {
                layer.msg(r.msg);
            }
        }
    });
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
