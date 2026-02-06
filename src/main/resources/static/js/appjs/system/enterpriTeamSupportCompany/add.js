$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	var form = $("#signupForm")[0];
	var fd = new FormData(form);
	$("#signupForm").find("td").each(function () {
		var pname = $(this).attr("pname");
		if (pname) {
			var val = $(this).html().replaceAll("<br>", "");
			fd.append(pname, val);
		}
	})

	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/enterpriTeamSupportCompany/save",
		data: fd,// 你的formid
		async : false,
		processData: false,
		contentType: false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				window.location.reload();
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