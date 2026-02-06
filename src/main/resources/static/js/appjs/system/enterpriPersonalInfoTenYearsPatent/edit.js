$().ready(function() {
	validateRule();
});


/***
 * 编辑
 */
function saveTenYear() {




	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/enterpriPersonalInfoTenYearsPatent/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
