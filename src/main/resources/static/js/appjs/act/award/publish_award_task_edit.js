var files = [];
$().ready(function () {
    $('.summernote').summernote({
        height: '220px',
        lang: 'zh-CN',
        callbacks: {
            onImageUpload: function (files, editor, $editable) {
                sendFile(files);
            }
        }
    });

    var validateContent = $("#validateContent").val();
    $('.summernote').summernote("code", validateContent);
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minuteStep: 1,
        todayBtn: true,
        setDate: new Date()
    });


    $("#btn-blockee").click(function (event) {

        var fileType = $(this).attr("fileType");
        var taskId = $("#id").val();
        var pid = 0;
        parent.layer.open({
            title: '上传附件资料',
            maxmin: true,
            type: 2,
            title: '上传附件资料',
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: '/award_flow/to_uploadtask_small?taskId=' + taskId + '&proId=' + pid + '&fileType=' + fileType // iframe的url
        });

    });

    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        updatePro();
    }
});

function openDocView(url, fileName) {
    layer.open({
        type: 2,
        title: '附件:' + fileName,
        area: ['300px', '450px'],
        content: url
    })
}

function loadUser(id, name) {
    console.log(id + name);
    $("#userId").val(id);
    $("#userName").val(name);
}

/**
 * publish_award_task_edit
 */
function updatePro() {
    var fd = new FormData();

    var index = localStorage.getItem("enterType");//输出
    fd.append('id', $("#id").val());
    fd.append('taskName', $("#taskName").val());
    fd.append('file', "");
    fd.append('awardId', index); //
    fd.append('associationUserId', $("#associationUserId").val());

    fd.append('taskStartTime', $("#task_start_time").val());
    fd.append('taskEndTime', $("#task_end_time").val());


    fd.append('checkStartTime', $("#check_start_time").val());
    fd.append('checkEndTime', $("#check_end_time").val());

    fd.append('expertStartTime', $("#expert_start_time").val());
    fd.append('expertEndTime', $("#expert_end_time").val());

    fd.append('expertStartTimeSecond', $("#expert_start_time_second").val());
    fd.append('expertEndTimeSecond', $("#expert_end_time_second").val());

    fd.append('validateRequire', $("#editor").val());
    fd.append('majorIds', $("#majorId").val());


    $("input[name='upDocId']").each(function () {
        fd.append('upDocId', $(this).val());
    })


    $.ajax({
        cache: true,
        type: "POST",
        url: "/award_flow/publish_update",
        // data: $('#signupForm').serialize(),// 你的formid
        data: fd,// 你的formid
        processData: false,  //tell jQuery not to process the data
        contentType: false,  //tell jQuery not to set contentType
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}


function openUpload() {
    var fileType = "task_file";
    var pid = 0;
    var taskId = $("#id").val();
    parent.layer.open({
        title: '上传附件资料',
        maxmin: true,
        type: 2,
        title: '上传附件资料',
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/award_flow/to_uploadtask_small?taskId=' + taskId + '&proId=' + pid + '&fileType=' + fileType // iframe的url
    });
}


function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + ""
            }
        }
    })
}

$.fn.datetimepicker.dates['zh-CN'] = {
    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
    months: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
    monthsShort: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
    today: "今天",
    suffix: [],
    meridiem: ["上午", "下午"]
};
