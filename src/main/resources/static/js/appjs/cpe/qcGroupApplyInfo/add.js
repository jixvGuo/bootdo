var saving = false;
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    if (saving) return;
    saving = true;

    $.ajax({
        cache: false,
        type: "POST",
        url: "/qcAward/update/groupInfo",
        dataType: "json",                  // 强制按JSON解析
        data: $('#signupForm').serialize(),
        async: true,
        complete: function () {
            saving = false;
        },
        error: function () {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                // 关键：先回填主键和申报号，保证下一次提交走更新
                if (data.id) {
                    $("#id").val(data.id);
                }
                if (data.applyId) {
                    $("input[name='applyId']").val(data.applyId);
                }

                parent.layer.msg("操作成功");
				console.log("save resp =>", data);
                // 仅刷新列表页，不刷新当前编辑页
                let navArr = $("iframe", window.parent.parent.document);
                $.each(navArr, function (i, val) {
                    if (val.src && val.src.indexOf('view/proList') !== -1) {
                        val.contentWindow.location.reload(true);
                    }
                });
            } else {
                parent.layer.alert(data.msg || "保存失败");
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
