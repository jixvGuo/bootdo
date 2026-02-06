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
	console.log("---->aaa-->", document.getElementById('btn_file'));
	console.log("---->bbb-->", document.getElementById('btn_file')[0]);
	if (document.getElementById('btn_file') != null && document.getElementById('btn_file').files != null) {
		console.log("------------>upload file--->")
		fd.append('file', document.getElementById('btn_file').files[0]);
	}
	$.ajax({
		type : "POST",
		url : "/system/enterpriTeamEnclosureList/save",
		data: fd,// 你的formid
		cache: false,
		contentType: false,
		processData: false,
		dataType: "json",
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