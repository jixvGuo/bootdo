$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var formData = $("#signupForm").serializeArray();
	var formParams = {};
	$.each(formData,function () {
		formParams[this.name] = this.value;
	})
	$("#contentId").find("tr").each(function () {
	   var params = formParams;
	   $(this).find("td").each(function (index,el) {
	   	   var val = $(this).html().replaceAll("<br>","");
	   	   console.log("knowledge ---index="+index+",val="+val);
		   switch (index) {
			   case 0:
   			   	   params.kType = val;
			   	break;
			   case 1:
			   	   params.kName = val;
			   	break;
			   case 2:
				   params.kArea = val;
				   break;
			   case 3:
				   params.kAuthCode = val;
			   	break;
			   case 4:
				   params.kAuthDate = val;
			   	break;
			   case 5:
				   params.kBookCode = val;
			   	break;
			   case 6:
				   params.kRightUser = val;
				   break;
			   case 7:
				   params.kCreateUser = val;
				   break;
			   case 8:
				   params.kEffectiveStat = val;
				   break;
		   }
	   });
       console.log("保存知识产权---->",params);
		$.ajax({
			cache : true,
			type : "POST",
			url : "/system/enterpriseScienceAwardKnowledgeInfo/save",
			data : params,// 你的formid
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

	})

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