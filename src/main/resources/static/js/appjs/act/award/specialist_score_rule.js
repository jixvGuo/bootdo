$().ready(function () {
    validateRule();
    $("#scienceScoreSaveBtn").on("click",function () {
       score("science_score");
    });
    $("#teamScoreSaveBtn").on("click",function () {
        score("team_score");
    });
    $("#personalScoreSaveBtn").on("click",function () {
        score("personal_score")
    });

    disabledScoreInput();

    $(".form-control").blur(function () {
        caculateScore(this);
    });
    $(".form-control").on("change",function () {
        caculateScore(this);
    });
});

function disabledScoreInput(){
    var scienceIsScore = parseFloat($("#scienceScoreRst").val());
    if(scienceIsScore > 0) {
        $("#science_score").find("input").each(function (index,e) {
            $(this).prop("disabled",true);
        });
        $("#scienceScoreSaveBtn").prop("disabled",true);
    }
    var teamIsScore = parseFloat($("#teamScoreRst").val());
    if(teamIsScore > 0) {
        $("#team_score").find("input").each(function (index,e) {
            $(this).prop("disabled",true);
        });
        $("#teamScoreSaveBtn").prop("disabled",true);
    }
    var personalIsScore = parseFloat($("#personalScoreRst").val());
    if(personalIsScore > 0) {
        $("#personal_score").find("input").each(function (index,e) {
            $(this).prop("disabled",true);
        });
        $("#personalScoreSaveBtn").prop("disabled",true);
    }
}

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function caculateScore(that) {
    var total = 0;
    $(that).parent().parent().parent().find("input").each(function (inex,e) {
        var score = $(this).val();
        if(score == "") {
            score = "0";
        }
        total += parseFloat(score);
    });
    $(that).parent().parent().parent().find("span[name='total']").html(total);
}

function score(formId) {
    var result = {"score_type":formId};
    var total = 0;
    $("#"+formId).find("input").each(function (index,e) {
        var score = $(this).val();
        var key = $(this).prop("name");
        if(score == "") {
            score = "0";
        }
        result[key] = score;
        total+= parseFloat(score);
    });
    result["total"] = total;
    console.log("score result ",result,formId);
    save(result);
    return result;
}


function loadUser(id,name){
    console.log(id+name);
    $("#userId").val(id);
    $("#userName").val(name);
}

function save(scoreResult) {
    var proId = $("#proId").val();
    scoreResult["proId"] = parseInt(proId);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/specialist/score",
        data: scoreResult,// 你的formid
        async: false,
        error: function (request) {
            parent.parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.parent.layer.msg("操作成功");
                var scoreType = scoreResult.score_type;
                if(scoreType == 'personal_score'){
                    $("#personalScoreRst").val(scoreResult.total);
                }
                if(scoreType == 'team_score') {
                    $("#teamScoreRst").val(scoreResult.total);
                }
                if(scoreType == 'science_score') {
                    $("#scienceScoreRst").val(scoreResult.total);
                }
                disabledScoreInput();
            } else {
                parent.parent.layer.alert(data.msg)
            }

        }
    });

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