var selWorks = null;
var allApply = null;

$().ready(function () {


    // getAllWorker();
    // getAllApplyProfesstions();
});


/***
 * 获取协会外聘人员
 */
function getAllWorker() {

    var taskId = $('#taskId').val();
    $.ajax({
        cache: true,
        type: "POST",
        url: "/enterprise_pro/to_getworker",
        data: {
            taskId: taskId,
        },
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (r) {

            if (r.code == 0) {
                selWorks = r.allWorkers;
                var filePath = "";
                for (var i = 0; i < selWorks.length; i++) {
                    var obj = selWorks[i];
                    filePath += "<div class='item'>" + obj.name + "</div>";
                }


                $("#externalMenu").html(filePath);

                $("#externalMenu").parent().dropdown('set selected', selWorks[0].name);

            } else {
                parent.layer.msg(r.msg);
            }

        }
    });
}


/***
 * 获取申请的专业
 */
function getAllApplyProfesstions() {

    var taskId = $('#taskId').val();
    $.ajax({
        cache: true,
        type: "POST",
        url: "/enterprise_pro/manage_profession/external",
        data: {
            taskId: taskId,
        },
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (r) {

            if (r.code == 0) {
                allApply = r.list;

            } else {
                parent.layer.msg(r.msg + "AA");
            }

        }
    });
}

/***
 * 分配
 */
function assignPro() {

    var asWorkerName = $("#asWorkerName").val();

    var proIds = "";
    $('#righttbody tr').each(function () {
        let proId = $(this).find('input[name="proId"]').val();
        console.log("== ", proId);
        proIds += proId + ",";
    });


    var taskId = $('#taskId').val();
    var awardType = $("#selAwardType").val()

    var data = {"taskId": taskId, "proIds": proIds, "asWorkerName": asWorkerName, "awardType": awardType};

    console.log("分配的数据" + JSON.stringify(data));
    $.ajax({
        cache: true,
        type: "POST",
        url: "/qcProcess/assignPro",
        data: data, // 你的formid
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (r) {
            if (r.code == 0) {
                parent.layer.msg(r.msg);
            } else {
                parent.layer.msg(r.msg);
            }
        }
    });
}

