
var prefix = "/specialist"
$(function() {
	load();

});

function load() {

}



function saveData() {
    var result = {"scoreType":"personal_score"};
    result["itemId"] =  $("#itemId").val();
    result["proId"] = $("#proId").val();
    result["major"] = $("#major").val();

    result["technology_create"] = $("#technology_create").val();
    result["gain_awards"] = $("#gain_awards").val();
    result["science_contribution"] = $("#science_contribution").val();
    result["intelli_right"] = $("#intelli_right").val();
    result["pub_work"] = $("#pub_work").val();

    result["opinionText"] = $("#opinion_text").val();

    result["opinionLevel"] = $("#opinion_level").val();

    result["totalscore"] = $("#totalscore").val();

    console.log(JSON.stringify(result) + " ===qq== ");

    $.ajax({
        cache: true,
        type: "POST",
        url: "/specialist/score",
        data: result,// 你的formid
        async: false,
        error: function (request) {
            parent.parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.parent.layer.msg("操作成功");
                // var scoreType = scoreResult.score_type;
                // if(scoreType == 'personal_score'){
                //     $("#personalScoreRst").val(scoreResult.total);
                // }
                // if(scoreType == 'team_score') {
                //     $("#teamScoreRst").val(scoreResult.total);
                // }
                // if(scoreType == 'science_score') {
                //     $("#scienceScoreRst").val(scoreResult.total);
                // }
                // disabledScoreInput();
            } else {
                parent.parent.layer.alert(data.msg)
            }

        }
    });

}
