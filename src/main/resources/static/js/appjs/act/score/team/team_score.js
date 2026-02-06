
var prefix = "/specialist"
$(function() {
	load();
});

function saveData() {
    var result = {"scoreType":"team_score"};
    result["itemId"] =  $("#teamId").val();
    result["proId"] = $("#proId").val();
    result["major"] = $("#major").val();

    result["cooperation_time"] = $("#cooperation_time").val();
    result["academic_authority"] = $("#academic_authority").val();
    result["scientific_achievements"] = $("#scientific_achievements").val();
    result["national_patent"] = $("#national_patent").val();
    result["utility_model_patents"] = $("#utility_model_patents").val();
    result["conversion_rate"] = $("#conversion_rate").val();

    result["opinionText"] = $("#opinion_text").val();

    result["opinionLevel"] = $("#opinion_level").val();

    result["total"] = $("#totalscore").val();


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