var prefix = "/surverScore"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/surverPro/get/proList", // 服务器数据的加载地址
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
                            var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="评分标准" onclick="openstard()">评分标准</a> ';
                            var d = '<a class="btn btn-warning btn-sm  " href="#" title=" "  mce_href="#" onclick="onwatch (\''
                            	+ row.proId
                            	+ '\',\''
                            	+ row.proSubType
                            	+ '\')"">查看项目</a> ';

                            var f = '<a class="btn btn-success btn-sm" href="#" title="评分"  mce_href="#" onclick="onscore(\''
                            		+ row.proId
                            	    + '\',\''
                            	    + row.taskId
                            	    + '\',\''
                            	    + row.major
                            		+ '\')">评分</a> ';
                            return e + d + f;
                        }
                    }],

            });
    if($("#isViewProCode").val() == 'false') {
       $('#exampleTable').bootstrapTable('hideColumn', 'proCode');
    }
}


function openstard() {
    page('/surverScore/standardTable', '评分标准', 20220505);
}

/***
 * 提交最终的打分结果
 */
function commitLast() {
    var result = {"scoreType":"personal_score"};
    var proId = $("#proId").val();
    result["proId"] = parseInt(proId);

    var centerData = {};
    console.log(JSON.stringify(result) + " ===qq== ");
    $.ajax({
        cache: true,
        type: "POST",
        url: "/specialist/score",
        data: result,// 你的formid
        async: false,
        error: function (request) {
            parent.parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.parent.layer.msg("操作成功");

            } else {
                parent.parent.layer.alert(data.msg)
            }

        }
    });



}
/****
 * 查看项目
 *  content: '/chengguo_team/apply_team/edit?proId=' + id + "&taskId=" + taskId // iframe的url
 */
function onwatch(proId, proType) {
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
    page(url, title, 20220505);
}


/***
 * 打印
 * @param id
 */
function onscore(proId, taskId, major) {
    page('/surverScore/toScore?proId=' + proId + '&taskId=' + taskId + '&major=' + major, '专业组评审打分', 20220505);
}

function add() {
	layer.confirm('提交后不可再次进行分数修改,是否确定提交?', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			cache: true,
			type: "POST",
			url: "/specialist/scoreOver",
			data: {
				scoreType:$("input[name='scoreType']").val()
			},// 你的formid
			async: false,
			error: function (request) {
				parent.parent.layer.alert("Connection error");
			},
			success: function (data) {
				if (data.code == 0) {
					parent.parent.layer.msg("操作成功");
					window.location.reload();
				} else {
					parent.parent.layer.alert(data.msg)
				}

			}
		});
	}, function(){
	});
}

function cancelSubmit(){
	layer.confirm('是否撤销提交?', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			cache: true,
			type: "POST",
			url: "/specialist/scoreCancel",
			data: {
				scoreType:$("input[name='scoreType']").val()
			},// 你的formid
			async: false,
			error: function (request) {
				parent.parent.layer.alert("Connection error");
			},
			success: function (data) {
				if (data.code == 0) {
					parent.parent.layer.msg("操作成功");
					window.location.reload();
				} else {
					parent.parent.layer.alert(data.msg)
				}
			}
		});
	}, function(){
	});
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
    if($("#isViewProCode").val() == 'false') {
       $('#exampleTable').bootstrapTable('hideColumn', 'proCode');
    }
}