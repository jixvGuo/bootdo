
var prefix = "/specialist"
$(function() {
    load();

});

function load() {

}



function saveData() {
   /* var result = {"scoreType":"science_score"};
    result["itemId"] =  $("#itemId").val();
    result["proId"] = $("#proId").val();
    result["major"] = $("#major").val();

    result["technology_advancement"] = $("#technology_advancement").val();
    result["degree_innovation"] = $("#degree_innovation").val();
    result["difficulty_complex"] = $("#difficulty_complex").val();
    result["lore_property"] = $("#lore_property").val();
    result["economic_benefit"] = $("#economic_benefit").val();
    result["social_benefit"] = $("#social_benefit").val();
    result["promotion_prospect"] = $("#promotion_prospect").val();
    result["preparation_expression"] = $("#preparation_expression").val();
    result["promotion_prospect"] = $("#promotion_prospect").val();

    result["optionText"] = $("#input_opinion").val();
    result["optionLevel"] = $("#option_level").val();

    result["total"] = $("#Totalscore").val();

    console.log(JSON.stringify(result) + " ===qq== ");*/
    $.ajax({
        cache: true,
        type: "POST",
        url: "/specialist/score",
        data: $('#scoreForm').serialize(),// 你的formid
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
