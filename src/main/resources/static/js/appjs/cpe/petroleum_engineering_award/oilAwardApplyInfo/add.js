$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$('input[name=unitConstructionProject]').each(function () {
		if($(this).parent().checkbox("is checked")){
			let tab = $(this).attr("tabindex");
			console.log("------->tab=", tab);
			$("#unitConstructionProjectVal").val(tab);
		}
	});
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilAwardApplyInfo/save",
		data : $('#signupFormApply').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let id = data.id;
				$("input[name='applyInfoId']").val(id);
				$("#signupFormApply input[name='id']").val(id);
				$(".ui.top").find("a").each(()=>{
					$(this).removeClass('disabled');
				});
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function saveGet() {
	let applyInfoId = $("#signupFormGet").find("input[name='applyInfoId']").val();
	if(!applyInfoId) {
		parent.layer.msg("请先添加上面的申报单位信息");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilAwardGetInfo/save",
		data : $('#signupFormGet').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let proId = $("#signupFormGet input[name='proId']").val();
				let taskId = $("#signupFormGet input[name='taskId']").val();
				let applyInfoId = $("#signupFormGet input[name='applyInfoId']").val();
				let oilProType = $("input[name='oilProType']").val();
				if(oilProType == 'gold') {
					window.location.replace('/enterpriseQualityGoldAward/toAddGold?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}else {
					window.location.replace('/enterpriseQualityAward/toAdd?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function savePartake() {
	let applyInfoId = $("#signupFormPartake").find("input[name='applyInfoId']").val();
	if(!applyInfoId) {
		parent.layer.msg("请先添加上面的申报单位信息");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilAwardPartakeUnit/save",
		data : $('#signupFormPartake').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let proId = $("#signupFormPartake input[name='proId']").val();
				let taskId = $("#signupFormPartake input[name='taskId']").val();
				let applyInfoId = $("#signupFormPartake input[name='applyInfoId']").val();
				let oilProType = $("input[name='oilProType']").val();
				if(oilProType == 'gold') {
					window.location.replace('/enterpriseQualityGoldAward/toAddGold?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}else {
					window.location.replace('/enterpriseQualityAward/toAdd?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function saveUnit() {
	let applyInfoId = $("#signupFormUnit").find("input[name='applyInfoId']").val();
	if(!applyInfoId) {
		parent.layer.msg("请先添加上面的申报单位信息");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/petroleum_engineering_award/oilAwardUnitInfo/save",
		data : $('#signupFormUnit').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				let proId = $("#signupFormUnit input[name='proId']").val();
				let taskId = $("#signupFormUnit input[name='taskId']").val();
				let applyInfoId = $("#signupFormUnit input[name='applyInfoId']").val();
				let oilProType = $("input[name='oilProType']").val();
				if(oilProType == 'gold') {
					window.location.replace('/enterpriseQualityGoldAward/toAddGold?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}else {
					window.location.replace('/enterpriseQualityAward/toAdd?proId=' + proId + "&taskId=" + taskId + "&id=" + applyInfoId);
				}
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