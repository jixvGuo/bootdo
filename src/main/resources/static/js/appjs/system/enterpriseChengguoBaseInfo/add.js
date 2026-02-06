$().ready(function() {
	//$("#upFileInfo").html("");
	$("#upload").on("change",function () {
		var windowURL = window.URL || window.webkitURL;
		//这个函数（window的方法）创建出路径，浏览器能通过原生接口访问本地文件的路径，其中window可以省略，参数为需要创建路径的dom元素。
		var imgUrl = windowURL.createObjectURL(this.files[0]);
		var fileName = this.files[0].name;
		$("#upFileInfo").html("<a href='"+imgUrl+"' target='_blank'>"+fileName+"</a>");
		console.log("=====>",imgUrl,fileName);
	});
	if ($('.form_date').datetimepicker != null) {
		$('.form_date').datetimepicker({
			language: 'zh-CN',
			autoclose: true,
			minuteStep: 1,
			todayBtn: true,
			setDate:new Date()
		});
	}
	validateRule();
});

if ($.fn.datetimepicker != null) {
	$.fn.datetimepicker.dates['zh-CN'] = {
		days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
		months: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
		monthsShort: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
		today: "今天",
		suffix: [],
		meridiem: ["上午", "下午"]
	};
}

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var form = $("#signupForm")[0];
	var fd = new FormData(form);

	$("#signupForm").find("td").each(function () {
		var pname = $(this).attr("pname");
		if(pname) {
			var val = $(this).text().replaceAll("<br>","");
			fd.append(pname,val);
		}
	})

	$("#signupForm").find("input").each(function () {
		var pname = $(this).attr("pname");
		if(pname) {
			fd.delete(pname);
			var val = $(this).val();
			fd.append(pname,val);
		}
	})

    fd.delete("major");
	var major = $("#major").val();
	fd.append("major",major);

	fd.delete("subjectType");
	var subjectType = $("#subjectType").val();
	fd.append("subjectType",subjectType);

	fd.delete("securityLevel");
	var securityLevel = $("#securityLevel").val();
	fd.append("securityLevel",securityLevel);

	$("td[pdname='masterCompleteEnterpriseList").each(function () {
		fd.append("masterCompleteEnterpriseList",$(this).html());
	})


	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/enterpriseChengguoBaseInfo/save",
		data : fd,// 你的formid
		async : false,
		processData: false,  //tell jQuery not to process the data
		contentType: false,  //tell jQuery not to set contentType
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
				required : icon + "请输入姓名"
			}
		}
	})
}