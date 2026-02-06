$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	let postData = new Object();
	$("#designTable").find("td").each(function () {
	    let isEdit = $(this).attr("contenteditable");
	    if(isEdit) {
	    	let name = $(this).attr("name");
	    	postData[name] = $(this).text();
		}
	});
	$("designTable").find("input").each(function () {
		let name = $(this).attr("name");
		postData[name] = $(this).val();
	})

	console.log("勘察奖申报表数据--edit->", postData)

	$.ajax({
		cache : true,
		type : "POST",
		url : "/cpe/surverDesignApplyTableInfo/update",
		data : postData,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

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
