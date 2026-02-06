var proIds;
var selSpecialistObj = null;
$(function() {
	validateRule();
	getSelSpecialist();
	$("#major").on("change",function () {
		if(selSpecialistObj == null) {
			console.log("还没有选择专家信息")
			return;
		}
		$("#specialist").selectpicker('deselectAll');
		$("#specialistLeader").selectpicker('val','');
		var majorIdSel = $(this).val();
		for(var i=0;i<selSpecialistObj.length;i++) {
			var majorId = selSpecialistObj[i].majorId;
			if(majorId == majorIdSel) {
				var uids = selSpecialistObj[i].uids.split(",");
				var specialistLeaderUid = selSpecialistObj[i].specialistLeaderUid;
				$("#specialist").selectpicker('val',uids);
				$("#specialistLeader").selectpicker('val',specialistLeaderUid);
				break;
			}
		}
		$("#specialistLeader").selectpicker("refresh");
		$("#specialist").selectpicker("refresh");
	});

	$("#completeSelBtn").on("click",function () {
		parent.layer.confirm("是否已完成全部的专家选择?",
			{
				btn:['确定','取消'],
				btn1:function(index) {
					console.log("确定按钮",index);
					parent.layer.close(index);
					//结束选择专家
					selSpecialist(true);
				},
				btn2:function() {

				}
		});
	});
});

$.validator.setDefaults({
	submitHandler : function() {
	    var assWorkers = $("#assWorker").val();
		$("#assWorkers").val(assWorkers);
		selSpecialist(false);
	}
});

function getSelSpecialist() {
	var publishTaskId = $("#publishTaskId").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/specialist/get_sel_specialist",
		data : {
			publishAwardId:publishTaskId
		}, // 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if(data) {
				selSpecialistObj = data;
			}
		}
	});
}

function selSpecialist(isComplete) {
	var majorId = $('#major').val();
	if(!isComplete && !majorId) {
		layer.msg("请选择专业");
		return;
	}
	var specialist = $("#specialist").val();
	if(!isComplete && !specialist) {
		layer.msg("请选择专家");
		return;
	}
	var specialistLeader = $("#specialistLeader").val();
	if(!isComplete && !specialistLeader) {
		layer.msg("请选择专家组长");
		return;
	}
	majorId = majorId ? majorId : 0;
	specialist = specialist ? specialist : [];
	specialistLeader = specialistLeader ? specialistLeader : 0;
	var publishTaskId = $("#publishTaskId").val();
	var major = $("#majorName1").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/specialist/select",
		data : {
			majorId:majorId,
			major:major,
			uids:specialist.join(","),
			specialistLeaderUid:specialistLeader,
			publishAwardId:publishTaskId,
			isComplete:isComplete
		}, // 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
				parent.layer.msg(r.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(r.msg);
			}

		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			roleName : {
				required : true
			}
		},
		messages : {
			roleName : {
				required : icon + "请输入角色名"
			}
		}
	});
}