var prefix = "/award_flow"

$().ready(function () {
    $('.summernote').summernote({
        height : '220px',
        lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
    });
    validateRule();
    $("#majorSel").on("change",function () {
        var text = $(this).find("option:selected").text();
        $("#majorInput").val(text);
    })
    $("#majorInput").on("click",function () {
      	 $("#majorSel").trigger("foucs");
    })
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

/*$("#userName").click(function () {
    layer.open({
        type: 2,
        title: '选择人员',
        area: ['300px', '450px'],
        content: "/sys/user/treeView"
    })
});*/

function loadUser(id,name){
    console.log(id+name);
    $("#userId").val(id);
    $("#userName").val(name);
}

function save() {
    var content = $('.summernote').summernote("code");
    $("#proDesc").val(content);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/enterprise_pro/create_pro",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
                //上报资料
                console.log("----->uploadDoc---->",data)
                uploadDoc(data.proId);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function uploadDoc(proId){
	var uploadDocPage = parent.layer.open({
		type : 2,
		title : '上报资料',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :  '/award_flow/to_upload_doc/' + proId // iframe的url
	});
	parent.layer.full(uploadDocPage);
}


function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + ""
            }
        }
    })
}