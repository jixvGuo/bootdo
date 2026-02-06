$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	$("input[type='hidden']").each(function () {
		var name = $(this).attr("name");
		if(name != 'proId') {
			var val = $("#"+name).html();
			$(this).val(val);
		}
	})
    let groupName = $("#tdProGroupName").text();
    $("#proGroupName").val(groupName ? groupName : '');

	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/awardStyleValidateScience/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
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