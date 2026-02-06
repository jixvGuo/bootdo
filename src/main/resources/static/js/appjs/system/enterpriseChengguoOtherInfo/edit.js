$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var form = $("#signupForm")[0];
	var fd = new FormData(form);
	$("#signupForm").find("td").each(function () {
		var pname = $(this).attr("pname");
		if(pname) {
			$(this).html()
			var val = $(this).html().replaceAll("<br>","");
			fd.append(pname,val);
		}
	})





	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/enterpriseChengguoOtherInfo/update",
		data : fd,// 你的formid
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
				// parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

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
				required : icon + "请输入名字"
			}
		}
	})
}