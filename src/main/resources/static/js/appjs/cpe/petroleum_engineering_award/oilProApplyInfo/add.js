$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	console.log("" + $('#signupForm').serialize());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilProApplyInfo/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let proId = $("input[name='proId']").val();
				let taskId = $("input[name='taskId']").val();
				window.location.replace("/enterpriseUnitApply/toAdd?proId=" + proId + "&taskId=" + taskId);
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