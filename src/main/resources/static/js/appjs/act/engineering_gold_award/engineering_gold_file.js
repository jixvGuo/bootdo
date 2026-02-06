var files = [];
var currentFileId = "";

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

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minuteStep: 1,
        todayBtn: true,
        setDate: new Date()
    });

    $(".btn-block").click(function (event) {

        console.log("图片时间啊美国参加吗阿第三发");
        var fileType = $(this).attr("fileType");
        var pid = 0;
        var taskId = "";
        var uploadDocPage =  parent.layer.open({
            title: '上传附件资料',
            maxmin: true,
            type: 2,
            title: '上传附件资料',
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: '/award_flow/to_uploadtask_small?taskId='+taskId+'&proId=' + pid + '&fileType=' + fileType // iframe的url
        });


    });
    validateRule();
});





$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function openUpload() {
    var fileType = "task_file";
    var pid = 0;
    var taskId = '';
    parent.layer.open({
        title: '上传附件资料',
        maxmin: true,
        type: 89,
        title: '上传附件资料',
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/award_flow/to_uploadtask_small?taskId='+taskId+'&proId=' + pid + '&fileType=' + fileType // iframe的url
    });
}


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

/***
 * 删除文件
 * @param id
 */
function deleteFile(id) {
    console.log(id + "=====");
    $("#upFileInfo").html("");
    var newFIle = [];
    for (var i = 0; i < this.files.length; i++) {
        var pid = this.files[i];
        if (pid.fileID == id) {
        } else {
            newFIle.push(pid);
        }
    }

    this.files = newFIle;
    var filePath = "";
    for (var i = 0; i < this.files.length; i++) {
        var pid = this.files[i];
        filePath += "<a href='" + pid.imgPath + "' target='_blank'>下载  " + pid.fileName + "</a>   <button class=\"btn btn-primary btn-xs\"" +
            " style='width: 100px' onclick='deleteFile(" + pid.fileID + ")'> 删除 </button> <input type='file' style='display: none' " +
            "id= file" + pid.fileID + "> <br>";
    }
    $("#upFileInfo").html(filePath);
}


/****
 * 保存文件
 */
function saveFile(timeStap) {

    var file_obj = document.getElementById('upload').files[0];
    var fd = new FormData();
    fd.append('file', file_obj);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/award_flow/publish_uploadfile",
        data: fd,// 你的formid
        processData: false,  //tell jQuery not to process the data
        contentType: false,  //tell jQuery not to set contentType
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            var that = this;
            if (data.code == 0) {
                this.currentFileId = data.msg;
                // imageObj.fileID = timestamp;
                // imageObj.fileSaveId = this.currentFileId;
                handleAllFIle(data.msg, timeStap);


            }

        }
    });
}

function handleAllFIle(msg, s) {
    for (var i = 0; i < this.files.length; i++) {
        var pid = this.files[i];
        if (pid.fileID == s) {
            pid.fileSaveId = msg;
        }
    }

    console.log("dddd" + JSON.stringify(this.files))
}


/***
 * 为了保证数据正常保存，先存储一个假的ID 记录下来，
 */
/****
 * 保存数据
 */
function save() {
    var fileId = "";
    var filePath = "";

    var fd = new FormData();

    var index = localStorage.getItem("enterType");//输出

    fd.append('taskName', $("#taskName").val());
    fd.append('awardId', index); //
    fd.append('associationUserId', $("#associationUserId").val());

    fd.append('taskStartTime', $("#task_start_time").val());
    fd.append('taskEndTime', $("#task_end_time").val());


    fd.append('checkStartTime', $("#check_start_time").val());
    fd.append('checkEndTime', $("#check_end_time").val());

    fd.append('expertStartTime', $("#expert_start_time").val());
    fd.append('expertEndTime', $("#expert_end_time").val());

    fd.append('validateRequire', $("#editor").val());
    fd.append('majorIds', $("#majorId").val());

    $("input[name='upDocId']").each(function () {
        fd.append('upDocId', $(this).val());
    })

    $.ajax({
        cache: true,
        type: "POST",
        url: "/award_flow/publish",
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

                handleAllFiles(data.rowId);

            } else {
                parent.layer.alert(data.msg)
            }
        }
    });

}

/***
 * 根据
 * @param rid
 */
function handleAllFiles(rid) {

    $.ajax({
        cache: true,
        type: "GET",
        url: "/award_flow/getAllFils?rid=" +rid ,
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            console.log("==AAA==" + JSON.stringify(data));
            if (data.code == 0) {
                this.files = data.allFiles;
                console.log("==BBBB==" + JSON.stringify(this.files));

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

if ($.fn.datetimepicker != null) {
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
}