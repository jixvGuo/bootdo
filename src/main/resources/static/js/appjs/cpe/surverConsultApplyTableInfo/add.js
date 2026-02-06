$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	var form = $("#signupForm").serializeArray();
	console.log("勘察奖项-咨询---->", form)
	let postData = new Object();
	$.each(form, function (i, field) {
		postData[field.name] = field.value;
	})
	$("#consultingTable").find("td").each(function () {
	    let isEdit = $(this).attr("contenteditable");
	    if(isEdit) {
	    	let name = $(this).attr("name");
	    	let val = $(this).text();
	    	postData[name] = val;
		}
	});


	console.log("勘察奖咨询申报表数据--->", postData)

	$.ajax({
		cache : true,
		type : "POST",
		url : "/cpe/surverConsultApplyTableInfo/save",
		data : postData,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				let id = data.id;
				$("#id").val(id);
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
