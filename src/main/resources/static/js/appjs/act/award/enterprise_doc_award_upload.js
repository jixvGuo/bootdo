var prefix = "/award_flow"

$().ready(function () {
    validateRule();
    $(".btn-block").on("click",function () {
        var fileType = $(this).parent().attr("id")
        parent.layer.open({
            zIndex:110,
	    	type : 2,
	    	title : '上传资料',
	    	maxmin : true,
	    	shadeClose : false, // 点击遮罩关闭层
	    	area : [ '800px', '520px' ],
	    	content : prefix + '/to_uploadsmall?proId='+$("#proId").val()+'&fileType='+fileType // iframe的url
	    });
    });

    var rstScienceValiFile = $("#rstScienceValiFile").val();
    var scienceArr = rstScienceValiFile.split(",");
    for(var i=0;i<scienceArr.length;i++) {
        $("input[name = 'science_doc_validate'][value='"+scienceArr[i]+"']").prop("checked",true);
    }
    var rstTuanduiValiFile = $("#rstTuanduiValiFile").val();
    var tuanduiArr = rstTuanduiValiFile.split(",");
    for(var i=0;i<tuanduiArr.length;i++) {
        $("input[name = 'tuandui_doc_validate'][value='"+tuanduiArr[i]+"']").prop("checked",true);
    }
    var rstPersonalValiFile = $("#rstPersonalValiFile").val();
    var personalArr = rstPersonalValiFile.split(",");
    for(var i=0;i<personalArr.length;i++) {
        $("input[name = 'personal_doc_validate'][value='"+personalArr[i]+"']").prop("checked",true);
    }

    layui.use('form',function(){
        var form = layui.form;
        //刷新界面 所有元素
        form.render();
    });

    //协会工作人员完成形式审核检查
    $("#assOverValidateBtn").on("click",function () {
        console.log("----->完成审核----》");
        //检验审核数据
        //1.结论是否填写，是否符合要求
        var scienceValidateRst = $('#scienceExtForm input[name="scienceRst"]:checked ').val();
        var tuanduiValidateRst = $('#tuanduiExtForm input[name="tuanduiRst"]:checked ').val();
        var personalValidateRst = $('#personalExtForm input[name="personalRst"]:checked ').val();
        var validateRst = "";
        if(!scienceValidateRst) {
            parent.layer.msg("请选择科技进步奖材料的审核结论!", {icon: 2,time:1800});
            return;
        }
        if(!tuanduiValidateRst) {
            parent.layer.msg("请选择科技创新团队材料的审核结论!", {icon: 2,time:1800});
            return;
        }
        if(!personalValidateRst) {
            parent.layer.msg("请选择先进个人材料的审核结论!", {icon: 2,time:1800});
            return;
        }
        if(scienceValidateRst == '完善后参评' || tuanduiValidateRst == '完善后参评' || personalValidateRst == "完善后参评") {
            //存在驳回的条件,直接驳回
            validateRst = "no";
        }else if(scienceValidateRst == '不评' && tuanduiValidateRst == '不评' && personalValidateRst == "不评") {
            //不进行评审直接任务结束
            validateRst = "over";
        }else {
            //通过进行专家评审
            validateRst = "yes";
        }

        //2.获取已审核过的文件
        var scienceValiFile = [];
        $('#science_doc_list input[name="science_doc_validate"]:checked ').each(function () {
            scienceValiFile.push($(this).val());
        });
        var tuanduiValiFile = [];
        $('#tuandui_doc_list input[name="tuandui_doc_validate"]:checked ').each(function () {
           tuanduiValiFile.push($(this).val());
        });
        var personalValiFile = [];
        $('#personal_doc_list input[name="personal_doc_validate"]:checked ').each(function () {
            personalValiFile.push($(this).val());
        });
        if(scienceValidateRst == '参评' && scienceValiFile.length != 11) {
            parent.layer.msg("科技进步奖材料存在未审核的!", {icon: 2,time:1800});
            return;
        }
        if(tuanduiValidateRst == '参评' && tuanduiValiFile.length != 6) {
            parent.layer.msg("科技创新团队材料存在未审核的!", {icon: 2,time:1800});
            return;
        }
        if(personalValidateRst == '参评' && personalValiFile.length != 5) {
            parent.layer.msg("先进个人材料存在未审核的!", {icon: 2,time:1800});
            return;
        }

        //3.审核结果上报
        var proId = $("#proId").val();
        var procInsId = $("#procInsId").val();
        var actRunTaskId = $("#actRunTaskId").val();
        var scienceFiles = scienceValiFile.join(",");
        var tuanduiFiles = tuanduiValiFile.join(",");
        var personalFiles = personalValiFile.join(",");

        parent.layer.confirm("是否完成审核?",
            {
                btn:['确定','取消'],
                btn1:function(index) {
                    layer.close(index);
                    completeValidate(proId,validateRst,procInsId,actRunTaskId,scienceFiles,tuanduiFiles,personalFiles);
                },
                btn2:function() {

                }
        });
    });

    $("#submitValidate").on("click",function () {
        console.log("------->submitValidate--->");
        var fileSize = $("#fileSize").val();
        if(fileSize == 0){
            parent.layer.msg("请先上传资料文件,再提交审核！", {icon: 2,time:1800});
            return;
        }
        parent.layer.confirm("是否提交审核?",
            {
                btn:['确定','取消'],
                btn1:function(index) {
                    layer.close(index);
                    save();
                },
                btn2:function() {

                }
        });
    });

});

$.validator.setDefaults({
    submitHandler: function () {
        console.log("-----commit--aaaa");
        var fileSize = $("#fileSize").val();
        if(fileSize == 0){
            parent.layer.msg("请先上传资料文件,再提交审核！", {icon: 2,time:1800});
            return;
        }
        parent.layer.confirm("是否提交审核?",
            {
                btn:['确定','取消'],
                btn1:function(index) {
                    layer.close(index);
                    save();
                },
                btn2:function() {

                }
            });
    }
});

/*$("#userName").click(function () {
    layer.open({
        type: 2,
        title: '选择人员',
        area: ['300px', '450px'],
        content: "/sys/user/treeView"
    })
});*/

function loadUser(id,name){
    console.log(id+name);
    $("#userId").val(id);
    $("#userName").val(name);
}

function completeValidate(proId,validateRst,procInsId,actRunTaskId,scienceValiFile,tuanduiValiFile,personalValiFile) {

    $.ajax({
        cache: true,
        type: "POST",
        url: "/award_flow/update",
        data: {
            taskAgree:validateRst,
            id:proId,
            procInsId:procInsId,
            associationReviewRst:validateRst,
            actRunTaskId:actRunTaskId,
            rstScienceValiFile:scienceValiFile,
            rstTuanduiValiFile:tuanduiValiFile,
            rstPersonalValiFile:personalValiFile,
            scienceLevel:$("#scienceLevel").val(),
            scienceKnowledgeCount:$("#scienceKnowledgeCount").val(),
            scienceVliEnterprise:$("#scienceVliEnterprise").val(),
            scienceApplyTime:$("#scienceApplyTime").val(),
            scienceApplyEnterpriseCount:$("#scienceApplyEnterpriseCount").val(),
            tuanduiCode:$("#tuanduiCode").val(),
            tuanduiCooperate:$("#tuanduiCooperate").val(),
            tuanduiSpecialist:$("#tuanduiSpecialist").val(),
            personalIsEmploy:$("#personalIsEmploy").val(),
            personalIsWorkEthics:$("#personalIsWorkEthics").val()
        },// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
                parent.location.reload();
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/award_flow/up_association_review",
        data: $('#signupForm').serialize(),// 你的formid
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
                //上报资料

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