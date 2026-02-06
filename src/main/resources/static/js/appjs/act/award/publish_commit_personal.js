$(document).ready(function () {
    $("#fileimage").on("change", function () {
        var file_obj = document.getElementById('fileimage').files[0];
        var fd = new FormData();
        fd.append('file', file_obj);
        $.ajax({
            cache: true,
            type: "POST",
            url: "/award_flow/publish_uploadheadfile",
            data: fd,// 你的formid
            processData: false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
            async: false,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {

                console.log("返回的图片对象" + JSON.stringify(data));


                if (data.code == 0) {

                    loadImagePath(data.file)


                }

            }
        });

    })

    $('.ui.form').form({
        on: 'blur',
        fields: {
            name: {
                identifier: 'name',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写基本信息的姓名'
                    }
                ]
            },
            gender: {
                identifier: 'gender',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请选择性别'
                    }
                ]
            },
            birthyears: {
                identifier: 'birthyears',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请选择出生年月'
                    }
                ]
            },
            nation: {
                identifier: 'nation',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写民族'
                    }
                ]
            },
            major: {
                identifier: 'major',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写从事专业'
                    }
                ]
            },
            technology: {
                identifier: 'technology',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写技术职称'
                    }
                ]
            },
            IDNumber: {
                identifier: 'IDNumber',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写身份证号'
                    }
                ]
            },
            education: {
                identifier: 'education',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写最高学历'
                    }
                ]
            },
            graduation: {
                identifier: 'graduation',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写毕业学校'
                    }
                ]
            },
            major: {
                identifier: 'major',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写所学专业'
                    }
                ]
            },
            graduationTime: {
                identifier: 'graduationTime',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请选择毕业时间'
                    }
                ]
            },
            governmentsubsidy: {
                identifier: 'governmentsubsidy',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请选择是否有政府津贴'
                    }
                ]
            },
            contactname: {
                identifier: 'contactname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人姓名'
                    }
                ]
            },
            contacttelephone: {
                identifier: 'contacttelephone',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人电话'
                    }
                ]
            },
            contactphone: {
                identifier: 'contactphone',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人手机'
                    }
                ]
            },
            contactfax: {
                identifier: 'contactfax',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人传真'
                    }
                ]
            },
            contactaddress: {
                identifier: 'contactaddress',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人通讯地址'
                    }
                ]
            },
            postcode: {
                identifier: 'postcode',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写联系人邮政编码'
                    }
                ]
            },
            starttime: {
                identifier: 'starttime',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写主要技术(科技)工作经历的开始时间'
                    }
                ]
            },
            endtime: {
                identifier: 'endtime',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写主要技术(科技)工作经历的结束时间'
                    }
                ]
            },
            companyname: {
                identifier: 'companyname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写主要技术(科技)工作经历的单位名称'
                    }
                ]
            },
            duties: {
                identifier: 'duties',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写主要技术(科技)工作经历的职务'
                    }
                ]
            },
            work: {
                identifier: 'work',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写主要技术(科技)工作经历的从事工作'
                    }
                ]
            }
        }
    });

});

var userId = "";
var workList = [];

/**
 * 点击 添加头像
 */
function loadImagePath(fils) {
    //保存头像
    console.log("====" + JSON.stringify(fils),$("#personalPhoto")[0].src,$("#personalPhoto"));

    $("#personalPhoto").attr("src",  fils.url);

    $("#imgpathid").attr("value", fils.id);


}

/***
 * 保存基本信息
 */
function saveBaseInfo() {
    console.log("save");
    var data = {
        "id":$("#id").val(),
        "proId": $("#proId").val(),
        "taskId": $("#taskId").val(),
        "applyId": $("#applyId").val(),
        "userName": $("#userName").val(),
        "gender": $("#sexType").text(),
        "photo":$("#personalPhoto")[0].src,
        "birthday": $("#birthday").val(),
        "nation": $("#nation").val(),
        "workMajor": $("#workMajor").val(),
        "technicalTitle": $("#technicalTitle").val(),
        "userIden": $("#IDNumber").val(),
        "technicalPosition": $("#technicalPosition").val(),
        "highestEducation": $("#education").val(),
        "graduateSchool": $("#graduation").val(),
        "major": $("#major").val(),
        "graduationTime": $("#graduationTime").val(),
        "isEnjoyGovernmentSubsidies": $("#isEnjoyGovernmentSubsidies").val(),
        "contactsName": $("#contactname").val(),
        "contactsPhone": $("#contacttelephone").val(),
        "contactsTelphone": $("#contactphone").val(),
        "contactsFax": $("#contactfax").val(),
        "contactsPostcode": $("#postcode").val(),
        "contactsAddress": $("#contactaddress").val(),
        "photoId": $("#imgpathid").val() // 此处是ID 需要根据ID查找filepath
    };


    console.log("提交的额数据" + JSON.stringify(data),$("#personalPhoto")[0].src);
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
                $("#id").val(userId);
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}



/***
 * 修改
 */
function modifyWorkInfo() {
    var workTime = $("#workstarttime").val() + ":" + $("#workendtime").val();
    var data1 = {
        "id": $("#workId").val(),
        "personalId": this.userId,
        "workStartEndTime": workTime,
        "workCompany": $("#companyname").val(),
        "workPosition": $("#duties").val(),
        "workContent": $("#work").val(),
    };


    console.log("更新时间" + JSON.stringify(data1));

    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/enterpriPersonalInfoWorkHistory/update",
        dataType: "json",
        data: data1,// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                console.log("测试");
                $('#hisworkid').show();
                $('#hisworkidModify').hide();
                parent.layer.msg("操作成功");
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });


}

function viewUserInfo(id) {
    $.ajax({
        cache: true,
        type: "GET",
        url: "/system/enterpriPersonalInfoWorkHistory/get?id=" + id,
        dataType: "json",
        data: "",// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                console.log("测试");
                $('#hisworkid').hide();
                $('#hisworkidModify').hide();
                var userInfo = data.workInfo;


                var arrTime = [];
                if (userInfo.workStartEndTime.length > 0) {
                    arrTime = userInfo.workStartEndTime.split(":");
                }

                $("#work_form").find("input[name='workId']").val(arrTime.id);
                $("#work_form").find("input[name='workstarttime']").val((arrTime.length > 0 ? arrTime[0] : ""))
                $("#work_form").find("input[name='workendtime']").val((arrTime.length > 0 ? arrTime[1] : ""));
                $("#work_form").find("input[name='companyname']").val(userInfo.workCompany);
                $("#work_form").find("input[name='duties']").val(userInfo.workPosition);
                $("#work_form").find("input[name='work']").val(userInfo.workContent);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}


function viewFixUserInfo(id) {

    $("#work_form").find("input[name='workId']").val("")
    $("#work_form").find("input[name='workstarttime']").val("")
    $("#work_form").find("input[name='workendtime']").val("");
    $("#work_form").find("input[name='companyname']").val("");
    $("#work_form").find("input[name='duties']").val("");
    $("#work_form").find("input[name='work']").val();
    $.ajax({
        cache: true,
        type: "GET",
        url: "/system/enterpriPersonalInfoWorkHistory/get?id=" + id,
        dataType: "json",
        data: "",// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {

                $('#hisworkid').hide();
                $('#hisworkidModify').show();

                var userInfo = data.workInfo;

                console.log("测试" + JSON.stringify(userInfo) + "====");


                var arrTime = [];
                if (userInfo.workStartEndTime.length > 0) {
                    arrTime = userInfo.workStartEndTime.split(":");
                }

                $("#work_form").find("input[name='workId']").val(userInfo.id)
                $("#work_form").find("input[name='workstarttime']").val((arrTime.length > 0 ? arrTime[0] : ""))
                $("#work_form").find("input[name='workendtime']").val((arrTime.length > 0 ? arrTime[1] : ""));
                $("#work_form").find("input[name='companyname']").val(userInfo.workCompany);
                $("#work_form").find("input[name='duties']").val(userInfo.workPosition);
                $("#work_form").find("input[name='work']").val(userInfo.workContent);

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


    if (this.userId.length == 0 && $("#id").val().length == 0) {
        parent.layer.alert("请先输入用户信息");

        return;
    }

    if (this.userId.length == 0) {
        this.userId = $("#id").val();
    }

    var workTime = $("#workstarttime").val() + ":" + $("#workendtime").val();
    var data1 = {
        "personalId": this.userId,
        "workStartEndTime": workTime,
        "workCompany": $("#companyname").val(),
        "workPosition": $("#duties").val(),
        "workContent": $("#work").val(),
        "proId":$("input[name='proId']").val()
    };


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
            if (data.code == 0) {
                var obj = data.workHistory;
                var workHistoryStr = "<tr id='workHistory_"+obj.id+"'> " +
                              "<td>" + obj.id + "</td>" +
                              "<td>" + obj.workCompany + "</td>" +
                              "<td>" + obj.workPosition + "</td> "+
                              "<td> "+
                              "  <a href=\"#\" class=\"ui teal mini basic button\" onclick='viewUserInfo(" + obj.id + ")'>查看</a>\n" +
                              "  <a href=\"#\" class=\"ui teal mini basic button\" onclick='viewFixUserInfo(" + obj.id + ")'>修改</a>\n" +
                              "  <a href=\"#\" class=\"ui teal mini basic button\" onclick='deleteWorkInfo(" + obj.id + ")'>删除</a>\n" +
                              "</td>" +
                        " </tr>";

                $("#workinfo").append(workHistoryStr)
                document.getElementById("work_form").reset();
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
    console.log("asdadsf" + index);
    var fd = {
        "id": index,
    };
    $("#workHistory_"+index).remove();
    $.ajax({
        cache: true,
        url: "/system/enterpriPersonalInfoWorkHistory/remove",
        type: 'POST',
        data: fd,
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
    })


}

function savePersonalExtDesc() {
    var data = {
        "id":$("#id").val(),
        "proId": $("#proId").val(),
        "taskId": $("#taskId").val(),
        "applyId": $("#applyId").val(),
        "personalId": this.userId,
        "personalSummary": $("#personalSummary").val(),
        "companyOpinion": $("#companyOpinion").val(),
        "photo": $("#fileimage").val(),
    };

    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/enterpriPersonalInfo/save",
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

