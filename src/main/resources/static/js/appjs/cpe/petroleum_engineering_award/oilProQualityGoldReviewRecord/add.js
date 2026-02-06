$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	let formData = new Object();
	formData.proId = $("input[name='proId']").val();
	formData.id = $("input[name='id']").val();
	formData.taskId = $("input[name='taskId']").val();
	formData.applyId = $("input[name='applyId']").val();
	let opinionDesc = $("#opinionDesc").val();
	formData.opinionDesc = opinionDesc;
	formData.applyUnit = $("input[name='applyUnit']").val();
	formData.applyAwardName = $("input[name='applyAwardName']").val();

	let checkCount = $(".inline.fields").length;
	$(".inline.fields").each(() => {

		$('input').each(function () {
			let name = $(this).attr("name");
			let tabIndex = $(this).attr("tabindex");

			if ($(this).parent().checkbox("is checked") && formData[name] != tabIndex) {
				console.log("------->tab=", tabIndex, name);
				formData[name] = tabIndex;
				checkCount--;
			}
		});
	});

	console.log("allFileds ----checkCount>", checkCount);
	if(checkCount > 0) {
		parent.layer.msg("存在未选择的项目，请先选择再提交");
		return;
	}


	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilProQualityGoldReviewRecord/save",
		data : formData,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				$("input[name='id']").val(data.id);
				let iframeSrc = $("iframe[data-id='/petroleumEngineering/toQualityGoldList']",parent.document).attr('src');
				$("iframe[data-id='/petroleumEngineering/toQualityGoldList']",parent.document).attr('src', iframeSrc);
				$(".J_menuTab.active i",parent.document).click();
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