$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	let postData = new Object();
	$("#scoreTable").find("td[name='score']").each(function () {
		var name = $(this).attr("id");
		postData[name] = $(this).text();
	});
	$("#scoreTable").find("input").each(function () {
		let val = $(this).val();
		let name = $(this).attr('name');
		postData[name] = val;
	});
	console.log(postData);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/cpe/qcSurveyStatisticInfo/save",
		data : postData,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				$("#id").val(data.id);
				parent.layer.msg("操作成功");
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
