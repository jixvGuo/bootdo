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
		url : "/qcAward/update/groupInfo",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				$("#id").val(data.id);
				parent.layer.msg("操作成功");
				let navArr = $("iframe", window.parent.parent.document);
				console.log(navArr);
				$.each(navArr, function (i, val) {
					if(val.src.indexOf('view/proList') != -1) {
                      val.contentWindow.location.reload(true);
					}
				});
				/*console.log($(".J_menuTab.active", window.parent.parent.document).html());
				$(".J_menuTab.active i", window.parent.parent.document).trigger('click');*/
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
