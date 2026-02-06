$().ready(function() {
    validateRule();
});



$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});


function save() {
    console.log("AAAAAAAAAAAAAAAA");
    var  formData =  $('#signupForm').serialize();
    console.log("AAAAAAAAAAAAAAAA" + formData);
    $.ajax({
        type : "POST",
        url : "/enterpriseQualityAward/save",
        data: formData,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                let proId = $("#signupForm input[name='proId']").val();
                let taskId = $("#signupForm input[name='taskId']").val();
                window.location.replace('/enterpriseQualityAward/toContributorList?proId=' + proId + '&taskId=' + taskId);
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