$().ready(function() {
	// validateRule();

	// 强制拦截原生提交，避免页面刷新
    $("#signupForm").on("submit", function (e) {
        e.preventDefault();

        // 如果有 validate 插件，走校验
        if ($.fn.validate) {
            var v = $("#signupForm").data("validator");
            if (!v) {
                $("#signupForm").validate();
            }
            if (!$("#signupForm").valid()) {
                return false;
            }
        }

        save();
        return false;
    });
});

if ($.validator) {
	$.validator.setDefaults({
		submitHandler : function() {
			save();
		}
	});
}


function save() {
	$("#id").val("");
	$.ajax({
		cache : true,
		type : "POST",
		url : "/cpe/qcReviewResultRecord/save",
		
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				$("#id").val(data.id);
				parent.layer.msg("操作成功");
				reloadProList();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function reloadProList() {
	var docs = [];
	try { docs.push(window.parent.document); } catch (e) {}
	try { docs.push(window.parent.parent.document); } catch (e) {}
	try { docs.push(window.top.document); } catch (e) {}

	$.each(docs, function (idx, doc) {
		if (!doc) return;
		var navArr = $("iframe", doc);
		$.each(navArr, function (i, val) {
			if (val.src && val.src.indexOf('/qcAward/toProListMain') != -1) {
				val.contentWindow.location.reload(true);
			}
		});
	});
}

function rejectPro() {
	// proId 来自页面隐藏域
	var proId = $("#proId").val();
	if (!proId) {
		parent.layer.alert("缺少项目ID");
		return;
	}

	layer.confirm('确定要驳回该课题吗？', {
		btn: ['确定', '取消']
	}, function () {
		$.ajax({
			type: "POST",
			url: "/qcProcess/reject",
			data: { proId: proId },
			success: function (r) {
				if (r.code == 0) {
					parent.layer.msg("驳回成功");
					reloadProList();
				} else {
					parent.layer.alert(r.msg || "驳回失败");
				}
			},
			error: function () {
				parent.layer.alert("Connection error");
			}
		});
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
