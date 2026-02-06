var selfProfession = "";

$(function () {

    var allData = $("#assPros").val();
    console.log(JSON.stringify(allData));


    $('.form_date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minuteStep: 1,
        todayBtn: true,
        setDate: new Date()
    });

    reloadData();

    $("select#assAwardType").change(function () {
        console.log($(this).val() + "-------");
        var str = $(this).val()
        getMajors(str);
    });

});

/***
 * 刷新页面
 */
function reloadData() {
    
}
/****
 * 获取专业
 * @param val
 */
function getMajors(val) {



    var assAwardType = $("#assAwardType").val();  //获取选中的项
    var taskId = $("#publishTaskId").val();

    $.ajax({
        cache: true,
        url: "/scienceProgressScience/getAssignGroups",
        type: 'POST',
        data: {
            proType: assAwardType,
            taskId:taskId
        },
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                let groupListStr = "";
                if(data.groupList) {
                    for(var i=0;i<data.groupList.length;i++) {
                        let groupName = data.groupList[i];
                        let str = "<li>" +
                            "                                <h4 style=\"display:inline-block\" onclick='showSpeclist(\"" + groupName + "\")'>" +
                                                                 groupName+
                            "                                </h4>" +
                            "                                <a onclick=\"javascript:add('"+groupName+"')\" style=\"display:inline-block\"><i class=\"fa fa-plus\"></i></a>" +
                            "                            </li>";
                        groupListStr += str;
                    }
                }

                $("#proGroupList").html(groupListStr);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    })


}


function getYearMonth() {
    var date = new Date;
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = (month < 10 ? "0" + month : month);
    return mydate = (year.toString() + month.toString());
}

/****
 * 分配
 *   <input placeholder="开始日期" autocomplete="off" class="form-control layer-date" id="task_start_time">

 * **/
function add(e) {
    this.selfProfession = e;

    var items = ['1', '2', '4', '5', '6', '7', '8', '9', '10', 'a', 'b', 'c', 'e', 'g', 's', '1', 'q', 'f', 'A'];

    var aindex = ($("#list").children().length + 1);

    var options = $("#assAwardType option:selected");  //获取选中的项

    var expend = options.val();


    if (expend.toString().length == 0) {
        parent.layer.msg("请选择奖项选择");
        return
    }


    console.log("----" + options.val() + "== " + e);

    var raondom = getYearMonth() + expend + aindex + items[Math.floor(Math.random() * items.length)];

    let loginAccount = random;

    console.log("----" + raondom)

    var html = " <tr id='tr_"+loginAccount+"'>" +
        "             <td name='trNum' style=\"width:50px\">" + loginAccount + "</td>" +
        "             <td style=\"width:50px\"><input type='hidden' value='"+e+"' id='groupName"+loginAccount+"' /> " + e + "</td>" +
        "             <td style=\"width:120px\" id=\"accountId" + loginAccount + "\">" + raondom + " </td>" +
        "             <td style=\"width:120px\"contentEditable=\"true\"> " +
        "                 <input type='text' class=\"form-control\" placeholder='专家名称' id='accountName" + loginAccount + "'/> " +
        "             </td>" +
        "             <td style=\"width:120px\"contentEditable=\"true\"><input id='accountCom" + loginAccount +
        "' class=\"form-control\" type='text' placeholder='工作单位'></td>" +
        "             <td style=\"width:120px\"contentEditable=\"true\"><input id='accountBank" + loginAccount +
        "'class=\"form-control\" type='text' placeholder='银行账户'></td>" +
        "             <td style=\"width:120px\"contentEditable=\"true\"><input id='accountPhone" + loginAccount +
        "'class=\"form-control\" type='text' placeholder='手机号'></td>" +
        "             <td id='signImg_"+loginAccount+"' style=\"width:120px\"></td>" +
        "             <td style=\"width:200px\">" +
        "              <button id='saveBtn_"+loginAccount+"' type=\"button\" class=\"btn btn-primary btn-xs\" onclick='save(" + loginAccount + ")'  >保存</button>" +
        "              <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick='onAddSign(" + loginAccount + ")'>上传签章</button>" +
        "              <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick='remove(" + loginAccount + ")' >移出</button>" +
        "             </td>"
    "         </tr>";
    $("#list").append(html);



    $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
}


/***
 * 删除某个条目
 * @param loginAccount
 */
function remove(loginAccount) {
    $.ajax({
        cache: true,
        url: "/scienceProgressScience/expert/remove",
        type: 'POST',
        data: {
            loginAccount:loginAccount
        },
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                $("#tr_"+loginAccount).remove();
                $("td[name = 'trNum']").each(function (index,e) {
                    $(this).html(index+1);
                })
            } else {
                parent.layer.alert(data.msg)
            }

        }
    })


}



/***
 * 删除某个条目
 * @param a 值为loginAccount
 */
function onAddSign(a) {

    let taskId = $("#publishTaskId").val();
    var loginAccount = $("#accountId" + a).html();
    let saveBtnHtml = $("#saveBtn_" + a).html();

    if(saveBtnHtml != "保存") {
        toUploadSignImg(taskId, loginAccount, a);
        return;
    }

    save(a, function () {
        toUploadSignImg(taskId, loginAccount, a);
    });

}
function toUploadSignImg(taskId, loginAccount, a) {
    layer.open({
        title: '上传转件签名 ',
        maxmin: true,
        type: 2,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/scienceProgressScience/toUploadExpertSign?taskId='+taskId+'&loginAccount=' + loginAccount+"&trIndex="+a
    });
}


/***
 * 保存专家名字
 * @param a
 */
function save(a,callback) {
    var fd = {
        "username": $("#accountId" + a).html(),
        "password": "123456",
        "name": $("#accountName" + a).val(),
        "bankCard": $("#accountBank" + a).val(),
        "mobile": $("#accountPhone" + a).val(),
        "roleIds": "62",
        "status": "1",
        "accountCom":$("#accountCom" + a).val()
    };


    console.log("保存专家数据" + JSON.stringify(fd) + fd);

    $.ajax({
        cache: true,
        url: "/sys/user/savepro",
        type: 'POST',
        data: fd,
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                // parent.layer.msg("操作成功");
                console.log(data.msg + "userid");
                var isLeader = a == $("#list").find("tr").get(0).find("td").get(2).html() ;
                var groupName = $("#groupName"+a).val();
                saveExpertInfo(data.msg,isLeader,groupName, fd, a, callback);

            } else {
                parent.layer.alert(data.msg)
            }
        }
    })
}

function update(a, id) {
    var groupName = $("#groupName"+a).val();
    var accountName = $("#accountName"+a).val();
    var accountCom = $("#accountCom"+a).val();
    var accountBank = $("#accountBank"+a).val();
    var accountPhone = $("#accountPhone"+a).val();

    var fd = {
        "id": id,
        "groupName": groupName,
        "company": accountCom,
        "bankAccount": accountBank,
        "phone": accountPhone,
        "expertName":accountName,
    };
    console.log("===" + JSON.stringify(fd));

    $.ajax({
        cache: true,
        url: "/scienceProgressScience/expert/add",
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

/***
 * 保存对应的专业信息/专业组
 * @param uid
 */
function saveExpertInfo(uid,isLeader,groupName, userFd, trIndex, callback) {
    console.log("== this.selfProfession =" + uid);
    var assAwardType = $("#assAwardType").val();  //获取选中的项
    var fd = {
        "userName": groupName,
        "taskId": $("#publishTaskId").val(),
        "userId": uid,
        "proId": "0",
        "isGroupLeader": isLeader ? 1 : 0,
        "groupName": groupName,
        "company": userFd.accountCom,
        "bankAccount": userFd.bankCard,
        "phone": userFd.mobile,
        "expertName":userFd.name,
        "loginAccount":userFd.username,
        "proType":assAwardType
    };
    console.log("===" + JSON.stringify(fd));

    $.ajax({
        cache: true,
        url: "/scienceProgressScience/expert/add",
        type: 'POST',
        data: fd,
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                $("#saveBtn_" + trIndex).html("更新");
                $("#saveBtn_" + trIndex).onclick = function(){
                   update(trIndex, data.id)
                };
                if(callback) {
                    callback();
                }else {
                    parent.layer.msg("操作成功");
                }
            } else {
                parent.layer.alert(data.msg)
            }

        }
    })


}

function showSpeclist(major) {
    var assAwardType = $("#assAwardType").val();  //获取选中的项
    let taskId = $("#publishTaskId").val();
    window.location.href= "/scienceProgressScience/toAssignExperts?taskId=" + taskId + "&major=" + major + "&proType=" + assAwardType;
}