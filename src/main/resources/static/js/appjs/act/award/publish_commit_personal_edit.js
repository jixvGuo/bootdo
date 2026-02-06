$(document).ready(function () {
    console.log("这是1");
});

var userId = "";
var workList = [];

/***
 * 保存基本信息
 */
function saveBaseInfo() {
    console.log("save");
    var data = {
        "userName": $("#userName").val(),
        "gender": $("#sexType").text(),
        "photo":$("#personalPhoto")[0].src,
        "birthday": $("#birthday").val(),
        "nation": $("#nation").val(),
        "workMajor": $("#workMajor").val(),
        "technicalTitle": $("#work_profession").val(),
        "userIden": $("#IDNumber").val(),
        "technicalPosition": $("#technicalPosition").val(),

        "highestEducation": $("#education").val(),
        "graduateSchool": $("#graduation").val(),
        "major": $("#mainmajor").val(),
        "graduationTime": $("#graduationTime").val(),
        "isEnjoyGovernmentSubsidies": $("#isEnjoyGovernmentSubsidies").val(),
        "contactsName": $("#contactname").val(),
        "contactsPhone": $("#contacttelephone").val(),
        "contactsTelphone": $("#contactphone").val(),
        "contactsFax": $("#contactfax").val(),
        "contactsPostcode": $("#postcode").val(),
        "contactsAddress": $("#contactaddress").val()
    };

    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/enterpriPersonalInfo/save",
        data: data,
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                console.log(JSON.stringify(data) + "=====");
                userId = data.userId;
                console.log(JSON.stringify(userId) + "=====" + userId);
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

/***
 * 增加工作经历
 */
function addWorkInfo() {
    console.log("增加工作经历" + userId);
    if (this.userId.length == 0) {
        parent.layer.alert("请先输入用户信息");
        return;
    }

    var data1 = {
        "personalId": this.userId,
        "workStartEndTime": $("#startandendtime").text(),
        "workCompany": $("#companyname").val(),
        "workPosition": $("#duties").val(),
        "workContent": $("#work").val(),
    };


    this.workList.push(data1)
    console.log(JSON.stringify(this.workList) + "BBBBB")

    console.log("data:" + JSON.stringify(data1));
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/enterpriPersonalInfoWorkHistory/save",
        data: data1,// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            console.log(JSON.stringify(this.workList) + "CCCCCCC")

            if (data.code == 0) {
                var html = "<tr> <td>" +  workList.length +
                    "</td>  <td>" + workList[workList.length -1].workCompany +
                    "</td>" + workList[workList.length -1].workPosition +
                    "                        <td>" + workList[workList.length -1].workContent +
                    "</td><td>" +
                    "                            <a href=\"#\" class=\"ui teal mini basic button\" onclick=''>查看</a>\n" +
                    "                            <a href=\"#\" class=\"ui teal mini basic button\" onclick=''>修改</a>\n" +
                    "                            <a href=\"#\" class=\"ui teal mini basic button\" onclick='deleteWorkInfo()'>删除</a>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
                $("#workinfo").append(html);


            } else {
                parent.layer.alert(data.msg)
            }

        }
    });


}

/**
 * 删除working经历
 * @param index
 */
function deleteWorkInfo(index) {

}