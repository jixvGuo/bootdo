$().ready(function() {
	validateRule();

	$("#commitValidate").on("click",function () {
		console.log("------->submitValidate--->");
		console.log($('#commitReviewForm').serialize());
		var data = $('#commitReviewForm').serialize();
		parent.layer.confirm("是否提交审核?",
			{
				btn:['确定','取消'],
				btn1:function(index) {
					console.log("点击确定按钮提交审核",data);
					save();
				},
				btn2:function() {

				}
			});
	});

});

$.validator.setDefaults({
	submitHandler : function() {

	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url: "/award_flow/up_association_review",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				window.parent.parent.location.reload();
			    var index = parent.parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}

function validatePro(validateRst) {
	var proId = $("#proId").val();
	var procInsId = $("#procInsId").val();
	var actRunTaskId = $("#actRunTaskId").val();
	var scienceFiles = '';
	var tuanduiFiles = '';
	var personalFiles = '';
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
			scienceKnowledgeCount:0,
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
				window.location.reload();

				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);
				// parent.location.reload();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function xingshiViewPass() {
	var validateRst = "yes";
	validatePro(validateRst);
}
function xingshiViewNo() {
	var validateRst = "no";
	validatePro(validateRst);
}
