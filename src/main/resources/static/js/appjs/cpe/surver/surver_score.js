
var prefix = "/specialist"
$(function() {
	load();

});

function load() {

}



function saveData() {
    var result = {"score_type":"surver_score"};
    result["itemId"] =  $("#itemId").val();
    result["proId"] = $("#proId").val();
    result["major"] = $("#major").val();

    result["advancedtechnique"] = $("#advancedtechnique").val();
    result["innovate"] = $("#innovate").val();
    result["facilityvalue"] = $("#facilityvalue").val();
    result["intellectualproperty"] = $("#intellectualproperty").val();
    result["economics"] = $("#economics").val();

    result["energyconservation"] = $("#energyconservation").val();

    result["materialquality"] = $("#materialquality").val();

    result["total"] = $("#Totalscore").val();

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
