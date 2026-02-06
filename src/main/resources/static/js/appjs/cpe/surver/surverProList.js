var prefix = "/surverPro"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/get/proList", // 服务器数据的加载地址
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
                columns: [

                    {
                        field: 'id',
                        title: '序号'
                    },
                    {
                        field: 'proCode',

                        title: '项目编号',


                    },
                    {
                        field: 'proSubTypeStr',
                        title: '项目类别'
                    },
                    {
                        field: 'proName',
                        title: '项目名称'
                    },
                    {
                        field: 'applyCompany',
                        title: '申报单位'
                    },
                    {
                        field: 'major',
                        title: '专业'
                    },

                    {
                        field: 'applyAccount',
                        title: '申报账号'
                    },
                    {
                        field: 'applyStat',
                        title: '状态'
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
                            let rs_download_zip_h = s_download_zip_h;
                            if (!row.isReview) {
                                rs_review_h = 'hidden';
                            }
                            if (!row.isEdit) {
                                //不能编辑操作
                                rs_edit_h = 'hidden';
                                rs_remove_h = 'hidden';
                            }
                            if (!row.isCancelReview) {
                                rs_cancel_review_h = 'hidden';
                            }
                            if (!row.isDownloadProDoc) {
                                rs_download_doc_h = 'hidden';
                                rs_download_zip_h = 'hidden';
                            }

                            var view = '<a class="btn btn-primary  " href="#" mce_href="#" title="查看" onclick="view(\''
                                + row.proId
                                + '\',\''
                                + row.proSubType
                                + '\')">查看</a> ';
                            var e = '<a class="btn btn-primary  ' + rs_edit_h + '" href="#" mce_href="#" title="修改" onclick="edit(\''
                                + row.proId
                                + '\',\''
                                + row.proSubType
                                + '\')">修改</a> ';
                            var d = '<a class="btn btn-warning  ' + rs_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id + '\',\''
                                + row.proId
                                + '\')">删除</a> ';
                            // var print = '<a class="btn btn-success btn-sm '+ s_print_h +'" href="#" title="打印"  mce_href="#" onclick="print(\''
                            // 		+ row.proId
                            // 		+ '\')">打印</a> ';
                            var print = '<a class="btn btn-success " href="#" title="打印"  mce_href="#" onclick="print(\''
                                + row.proId + '\',\''
                                + row.proSubType
                                + '\')">打印</a> ';

                            var downloadAppendix = '<a class="btn btn-success  ' + s_download_zip_h + '" href="#" title="下载"  mce_href="#" onclick="downloadData(\''
                                + row.proId
                                + '\',\'appendix'
                                + '\')">下载附件</a> ';
                            var downloadSupport = '<a class="btn btn-success ' + s_download_zip_h + '" href="#" title="下载"  mce_href="#" onclick="downloadData(\''
                                + row.proId
                                + '\',\'support'
                                + '\')">下载证明</a> ';
                            var download = downloadAppendix + downloadSupport;

                            var saveCode = '<a class="btn btn-success  ' + s_save_code_h + '"  href="#" title="保存"  mce_href="#" onclick="saveProResultCode(\''
                                + row.proId
                                + '\')">保存</a> ';

                            let subCheckIsHide = row.isSubCheck == 1 ? '' : 'hidden';
                            var h = '<a class="btn btn-success ' + rs_edit_h + ' ' + subCheckIsHide + '" href="#" onclick="subCheck(' + row.proId + ')" title="提交审核"  mce_href="#">提交审核</a> ';
                            var cancelCheck = '<a class="btn btn-success ' + rs_cancel_review_h + '" href="#" onclick="cancelCheck(' + row.proId + ')" title="表单审核撤回"  mce_href="#">回收</a> ';

                            var j = '<a class="btn btn-success  ' + rs_review_h + '" href="#" title="形式检查"  mce_href="#" onclick="reviewUploadDoc(\''
                                + row.id
                                + '\',\''
                                + row.proId
                                + '\',\''
                                + row.proSubType
                                + '\')">形式审查</a> ';

                            var specialistScore = '<a class="btn btn-warning btn-sm  " href="#" title="评分"  mce_href="#" onclick="specialistScore (\''
                                + row.proId
                                + '\',\''
                                + row.taskId
                                + '\')"">评分</a> ';
                            var specialistOpinion = '<a class="btn btn-success btn-sm" href="#" title="评价"  mce_href="#" onclick="specialistOpinion(\''
                                + row.proId
                                + '\',\''
                                + row.taskId
                                + '\')">评价</a> ';

                            return saveCode + view + e + d + print + download + h + cancelCheck + j /*+ specialistScore + specialistOpinion*/;
                        }
                    }], /**
                 * @param {点击列的 field 名称} field
                 * @param {点击列的 value 值} value
                 * @param {点击列的整行数据} row
                 * @param {td 元素} $element
                 */
                onClickCell: function (field, value, row, $element) {

                    if (field === "proCode") {
                        $element.attr('contenteditable', true);
                        $element.blur(function () {
                            let index = $element.parent().data('index');
                            let tdValue = $element.html();
                            console.log("index" + index);
                            console.log("tdValue" + tdValue);
                            setCode(tdValue);
                        })
                    }

                }

            });
    if($("#isViewProCode").val() == 'false') {
       $('#exampleTable').bootstrapTable('hideColumn', 'proCode');
    }
}

function specialistScore(proId, taskId) {
    page('/qcScore/toScore?proId=' + proId + '&taskId=' + taskId, 'QC奖申报查看', 20220205);
}

function specialistOpinion(proId, taskId) {
    page('/qcScore/toOpinion?proId=' + proId + '&taskId=' + taskId, 'QC奖申报查看', 20220205);

}


function subCheck(proId) {
    layer.confirm('确定要提交审核吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: "/qcProcess/subCheck",
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


// 状态点击事件
function printExcelPro() {
    layer.open({
        type: 2,
        title: '打印QC项目申报奖',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + "/qc/printExcel" // iframe的url
    });
}


function cancelCheck(proId) {
    layer.confirm('确定要撤回,重新修改吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: "/cpe/suverProcess/cancelCheck",
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

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
    if($("#isViewProCode").val() == 'false') {
       $('#exampleTable').bootstrapTable('hideColumn', 'proCode');
    }
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

function view(proId, proType) {
    let url = '';
    let title = '';
    if (proType == 'design') {
        url = '/surverApply/toApplyDesign?readonly=1&proId=' + proId;
        title = '勘察设计奖';
    } else if (proType == 'software') {
        url = '/surverSoftwareApply/toApplySoftware?readonly=1&proId=' + proId;
        title = '计算机软件奖';
    } else if (proType == 'consulting') {
        url = '/surverConsultingApply/toApply?readonly=1&proId=' + proId;
        title = '咨询奖';
    } else if (proType == 'standard') {
        url = '/surverStandardApply/toApply?readonly=1&proId=' + proId;
        title = '标准设计奖';
    } else if (proType == 'contribution') {
        url = '/surverBaseExlentApply/toApply?readonly=1&proId=' + proId;
        title = '优秀勘察奖';
    }
    page(url, title, 20220328, true);
}

function edit(proId, proType) {
    let url = '';
    let title = '';
    if (proType == 'design') {
        url = '/surverApply/toApplyDesign?proId=' + proId;
        title = '勘察设计奖编辑';
    } else if (proType == 'software') {
        url = '/surverSoftwareApply/toApplySoftware?proId=' + proId;
        title = '计算机软件奖编辑';
    } else if (proType == 'consulting') {
        url = '/surverConsultingApply/toApply?proId=' + proId;
        title = '咨询奖编辑';
    } else if (proType == 'standard') {
        url = '/surverStandardApply/toApply?proId=' + proId;
        title = '标准设计奖编辑';
    } else if (proType == 'contribution') {
        url = '/surverBaseExlentApply/toApply?proId=' + proId;
        title = '优秀勘察奖';
    }
    page(url, title, 20220328, true);
}

function reviewUploadDoc(id, proId, proSubType) {
    let url = '';
    let title = '';
    if (proSubType == 'design') {
        url = '/cpe/surverReviewDesignResult?';
        title = '勘察设计奖形式审查';
    } else if (proSubType == 'software') {
        url = '/cpe/surverReviewSoftResult?';
        title = '计算机软件奖形式审查';
    } else if (proSubType == 'consulting') {
        url = '/cpe/surverReviewConsultResult?';
        title = '咨询奖形式审查';
    } else if (proSubType == 'standard') {
        url = '/cpe/surverReviewStandardResult?';
        title = '标准设计奖形式审查';
    } else if (proSubType == 'contribution') {
        url = '/cpe/surverReviewSurverResult?';
        title = '优秀勘察奖形式审查';
    }
    page(url + "proId=" + proId + "&proSubType=" + proSubType, title, 20220414, true);
}

/***
 * 打印
 */
// function print(id){
// 	layer.open({
// 		type: 2,
// 		title: '状态',
// 		maxmin: true,
// 		shadeClose: false, // 点击遮罩关闭层
// 		area: ['800px', '520px'],
// 		content: prefix+'/print/proinfo?id=' + id  // iframe的url
// 	});
// }

function print(proId, protype) {

    console.log("ddd" + proId);
    console.log("ddd" + protype);


    if (protype == 'design') {
        layer.confirm('确定要打印选中的记录？', {
            btn: ['确定', '取消']
        }, function (index) {
            console.log("ddd" + index);
            layer.close(index);
            layer.open({
                type: 2,
                title: '打印文档',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: '/surverApply/print/proinfo?id=' + proId  // iframe的url
            })

        })
    } else if (protype == 'contribution') {
        //石油工程建设优秀勘察奖
        layer.confirm('确定要打印选中的记录？', {
            btn: ['确定', '取消']
        }, function (index) {
            console.log("ddd" + index);
            layer.close(index);
            layer.open({
                type: 2,
                title: '打印文档',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: '/surverExlentApply/print/proinfo?id=' + proId  // iframe的url
            })

        })
    } else if (protype == 'software') {
        // 石油工程建设优秀勘察设计计算机软件奖
        layer.confirm('确定要打印选中的记录？', {
            btn: ['确定', '取消']
        }, function (index) {
            console.log("ddd" + index);
            layer.close(index);
            layer.open({
                type: 2,
                title: '打印文档',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: '/surverSoftwareApply/print/proinfo?id=' + proId  // iframe的url
            })

        })
    } else if (protype == 'standard') {
// 石油工程建设优秀标准设计奖
        layer.confirm('确定要打印选中的记录？', {
            btn: ['确定', '取消']
        }, function (index) {
            console.log("ddd" + index);
            layer.close(index);
            layer.open({
                type: 2,
                title: '打印文档',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: '/surverStandardApply/print/proinfo?id=' + proId  // iframe的url
            })

        })
    } else if (protype == 'consulting') {
// 石油工程建设优秀咨询奖
        layer.confirm('确定要打印选中的记录？', {
            btn: ['确定', '取消']
        }, function (index) {
            console.log("ddd" + index);
            layer.close(index);
            layer.open({
                type: 2,
                title: '打印文档',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: '/surverConsultingApply/print/proinfo?id=' + proId  // iframe的url
            })

        })
    }


}

let saveValue = "";

/**
 * 设置成果编码
 * @param code
 */
function setCode(code) {
    saveValue = code;
}

function downloadData(proId, fileType) {
    layer.confirm('确定要下载选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/downloadProDocFiles",
            type: "post",
            data: {
                'proId': proId,
                'fileType': fileType
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
    })
}

function remove(id, proId) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove/groupInfo",
            type: "post",
            data: {
                'id': id,
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

function saveProResultCode(proId) {
    if (saveValue == "") {
        return;
    }
    layer.confirm('确定要更新成果编号？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/saveCode",
            type: "post",
            data: {
                'resultCode': saveValue,
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

function resetPwd(id) {
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
