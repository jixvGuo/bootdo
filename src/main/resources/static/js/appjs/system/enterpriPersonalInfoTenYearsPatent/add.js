$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
//        saveTenYear();
    }
});

/***
 * 保存数据
 */
function saveTenYear() {
    var data = {
        "personalId": $("#proId").val(),
        "name": $("#title").val(),
        "type": $("#detailed").val(),
        "effect": $("#effect").val(),
        "indicatorIndex": $("#type").val(),
        "optUid": $("#work_profession").val(),
        "applyId": $("#applyId").val(),
        "taskId": $("#taskId").val(),
        "proId":$("#proId").val(),
        "id":$("#id").val()
    };

    console.log("saveTenYear:" + JSON.stringify(data));

    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/enterpriPersonalInfoTenYearsPatent/save",
        data: data,// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
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
                required: icon + "请输入姓名"
            }
        }
    })
}

function addRoule() {

}