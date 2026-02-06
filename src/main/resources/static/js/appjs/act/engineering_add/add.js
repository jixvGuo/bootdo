$().ready(function() {
    validateRule();
});



$.validator.setDefaults({
    submitHandler : function() {

    }
});

/**
 *
 */
function save1() {
    var  formData =  $('#signupForm1').serialize();
    $.ajax({
        type : "POST",
        url : "/petroleum_engineering_award/oilAwardApplyInfo/save",
        data: formData,
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

}


/**
 *
 */
function save2() {
    var  formData =  $('#signupForm2').serialize();
    $.ajax({
        type : "POST",
        url : "/petroleum_engineering_award/oilAwardGetInfo/save",
        data: formData,
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

}


/**
 *
 */
function save3() {
    var  formData =  $('#signupForm3').serialize();
    $.ajax({
        type : "POST",
        url : "/petroleum_engineering_award/oilAwardUnitInfo/save",
        data: formData,
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

}


/**
 *
 */
function save4() {
    var  formData =  $('#signupForm4').serialize();
    $.ajax({
        type : "POST",
        url : "/petroleum_engineering_award/oilAwardPartakeUnit/save",
        data: formData,
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