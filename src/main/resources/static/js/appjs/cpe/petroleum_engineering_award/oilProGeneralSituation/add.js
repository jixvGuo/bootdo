$().ready(function () {
    validateRule();
});
var curUrl = '/enterpriseUnitApply/toEngineeDescAdd?isFlush=true';
var editUrl = '/enterpriseUnitApply/toEngineeDescEdit?1';
$.validator.setDefaults({
    submitHandler: function () {



    }
});

function saveBaseInfo() {
    if ($("#signupForm").valid()) {
        save();
    }
}
function saveDesignAwardInfo() {
    if ($("#signupFormDesignAward").valid()) {
        saveDesignAward();
    }
}

function saveEngineeAwardInfo() {
    if ($("#signupFormEngineeAward").valid()) {
        saveEngineeAward();
    }
}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/petroleum_engineering_award/oilProGeneralSituation/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                console.log("添加奖项的id:", data)
                parent.layer.msg("操作成功");
                $("#awardId").val(data.id);
                $("input[name='engineeDescId']").val(data.id);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}

function saveDesignAward() {
    let engineeDescId = $("#signupFormDesignAward").find("input[name='engineeDescId']").val();
    if(!engineeDescId) {
        parent.layer.msg("请先添加工程概况信息");
        return;
    }

    $.ajax({
        cache: true,
        type: "POST",
        url: "/petroleum_engineering_award/oilProDesignAward/save",
        data: $('#signupFormDesignAward').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                // var trId = 'design_' + data.id;
                // $("#signupFormDesignAward").find("input[name='id']").val(data.id);
                // var nameAward = $('#signupFormDesignAward').find("input[name='nameAward']").val();
                // var awardUnit = $('#signupFormDesignAward').find("input[name='awardingUnit']").val();
                // addAwardTrData(trId, 'signupFormDesignAward', 'designAwardTboday', 'desginSortNum', nameAward, awardUnit);
                let proId = $('#signupFormDesignAward').find("input[name='proId']").val();
                let tagName = $("#signupFormDesignAward").find("input[name='tagName']").val();
                let url = tagName && tagName == '修改' ? (editUrl + '&id='+engineeDescId) : curUrl;
                window.location.replace(url + "&proId=" + proId);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function addAwardTrData(trId, formId, tbodyId, sortNumName, nameAward, awardingUnit) {
    var trStr = "<tr id='" + trId + "'>" +
        "                        <td></td>" +
        "                        <td>" + nameAward + "</td>" +
        "                        <td>" + awardingUnit + "</td>" +
        "                        <td>" +
        "                            <a href=\"javascript:;\" onclick='updateAward(\"" + trId + "\")' class=\"ui teal mini basic button\">查看</a>" +
        "                            <a href=\"javascript:;\" onclick='updateAward(\"" + trId + "\")' class=\"ui teal mini basic button\">修改</a>" +
        "                            <a href=\"javascript:;\" onclick='delAward(\"" + trId + "\")' class=\"ui teal mini basic button\">删除</a>" +
        "                        </td>\n" +
        "                    </tr>";
    $("#" + tbodyId).prepend(trStr);
    var pageSize = $("#" + tbodyId).children("tr").length;
    if (pageSize > 10) {
        $("#" + tbodyId).children("tr").last().remove();
    }
    $('#' + formId).find("td[name='" + sortNumName + "']").each((index, el) => {
        el.html(index + 1)
    });
}

function saveEngineeAward() {
    let engineeDescId = $("#signupFormEngineeAward").find("input[name='engineeDescId']").val();
    if(!engineeDescId) {
        parent.layer.msg("请先添加工程概况信息");
        return;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url: "/petroleum_engineering_award/oilProEngineeAward/save",
        data: $('#signupFormEngineeAward').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                // var trId = 'enginee_' + data.id;
                // var nameAward = $('#signupFormEngineeAward').find("input[name='engineeNameAward']").val();
                // var awardUnit = $('#signupFormEngineeAward').find("input[name='engineeAwardWinningTime']").val();
                // addAwardTrData(trId, 'signupFormEngineeAward', 'engineeAwardTboday', 'engineeSortNum', nameAward, awardUnit);
                let proId = $('#signupFormEngineeAward').find("input[name='proId']").val();
                let tagName = $("#signupFormDesignAward").find("input[name='tagName']").val();
                let url = tagName && tagName == '修改' ? (editUrl + '&id='+engineeDescId) : curUrl + '?1';
                window.location.replace(url + "&proId=" + proId);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    var signFormRst = $("#signupForm").validate({
        rules: {
            name: {
                required: true
            },
            projectName: {
                required: true,
            },
            buildLocation: {
                required: true,
            },
            projectCategory: {
                required: true,
            },
            projectScale: {
                required: true,
            },
            startTime: {
                required: true,
            },
            completionTime: {
                required: true,
            },
            projectApproval: {
                required: true,
            },
            completionAcceptanceReport: {
                required: true,
            },
            recordTime: {
                required: true,
            },
            declarationContent: {
                required: true,
            },
            projectInvestment: {
                required: true,
            },
            projectFinalAccounts: {
                required: true,
            },
            engineeringContract: {
                required: true,
            },
            projectSettlement: {
                required: true,
            },
            operationEffect: {
                required: true,
            },
            designAdvancedReasonable: {
                required: true,
            },
            hasAnyProof: {
                required: true,
            },
            passedSafetyAssessment: {
                required: true,
            },
            hasEnvironmentalAssessment: {
                required: true,
            },
            constructionUnit: {
                required: true,
            },
            exploitingUnit: {
                required: true,
            },
            designInstitute: {
                required: true,
            },
            constructionControlUnit: {
                required: true,
            }
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            },
            projectName: {
                required: icon + "请填写工程名称",
            },
            buildLocation: {
                required: icon + "请填写建设地点",
            },
            projectCategory: {
                required: icon + "请填写工程类别",
            },
            projectScale: {
                required: icon + "请填写工程规模",
            },
            startTime: {
                required: icon + "请选择开工时间",
            },
            completionTime: {
                required: icon + "请选择竣工时间",
            },
            projectApproval: {
                required: icon + "请选择是否立项批复",
            },
            completionAcceptanceReport: {
                required: icon + "请选择是否有竣工验收报告",
            },
            recordTime: {
                required: icon + "请选择备案时间",
            },
            declarationContent: {
                required: icon + "请填写申报内容",
            },
            projectInvestment: {
                required: icon + "请填写工程投资（万元）",
            },
            projectFinalAccounts: {
                required: icon + "请填写工程决算（万元）",
            },
            engineeringContract: {
                required: icon + "请填写工程合同（万元）",
            },
            projectSettlement: {
                required: icon + "请填写工程结算（万元）",
            },
            operationEffect: {
                required: icon + "请填写工程结算（万元）",
            },
            designAdvancedReasonable: {
                required: icon + "请选择设计是否先进合理",
            },
            hasAnyProof: {
                required: icon + "请选择有无证明",
            },
            passedSafetyAssessment: {
                required: icon + "请选择是否通过安全评估",
            },
            hasEnvironmentalAssessment: {
                required: icon + "请选择是否通过环保评估",
            },
            constructionUnit: {
                required: icon + "请填写建设单位",
            },
            exploitingUnit: {
                required: icon + "请填写使用单位",
            },
            designInstitute: {
                required: icon + "请填写设计单位",
            },
            constructionControlUnit: {
                required: icon + "请填写监理单位",
            }

        }
    });

    var designFormRst = $("#signupFormDesignAward").validate({
        rules: {
            nameAward: {
                required: true
            },
            awardWinningTime: {
                required: true
            },
            awardingUnit: {
                required: true
            }
        },
        messages: {
            nameAward: {
                required: icon + "请填写获奖名称"
            },
            awardWinningTime: {
                required: icon + "请选择获奖时间"
            },
            awardingUnit: {
                required: icon + "请填写颁奖单位"
            },
        }
    });

    var engineeFormRst = $("#signupFormEngineeAward").validate({
        rules: {
            engineeNameAward: {
                required: true
            },
            engineeAwardWinningTime: {
                required: true
            },
            engineeAwardingUnit: {
                required: true
            },
        },
        messages: {
            engineeNameAward: {
                required: icon + "请填写获奖名称"
            },
            engineeAwardWinningTime: {
                required: icon + "请选择获奖时间"
            },
            engineeAwardingUnit: {
                required: icon + "请填写颁奖单位"
            },
        }
    });
    console.log("signFormRst: " + JSON.stringify(signFormRst) + ", designFormRst:" + designFormRst + ",engineeFormRst:" + engineeFormRst)
}
