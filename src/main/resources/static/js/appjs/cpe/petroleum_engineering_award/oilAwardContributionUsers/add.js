$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilAwardContributionUsers/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let proId = $("#signupForm input[name='proId']").val();
				let taskId = $("#signupForm input[name='taskId']").val();
				let oilProType = $("input[name='oilProType']").val();
				if(oilProType == 'gold') {
					window.location.replace('/enterpriseQualityGoldAward/toContributorList?proId=' + proId + '&taskId=' + taskId);
				}else {
					window.location.replace('/enterpriseQualityAward/toContributorList?proId=' + proId + '&taskId=' + taskId);
				}
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