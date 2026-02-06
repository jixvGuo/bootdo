$().ready(function () {
    validateProStat();
});

function validateProStat() {
    var proStat = $("#proStat").val();
    if (proStat != '' && proStat != 'reject') {
        $("#signupForm").find("input").each(function () {
            var type = $(this).attr("type");
            if (type != 'hidden') {
                var val = $(this).val();
                var parent = $(this).parent();
                parent.append(val);
                $(this).remove();
            }
        });
        $("#signupForm").find("td").each(function () {
            var contentEditablePro = $(this).attr("contentEditable");
            if(contentEditablePro) {
                $(this).attr("contentEditable",false);
            }
        });
        $("#signupForm").find("textarea").each(function () {
            $(this).attr("readonly",true);
        })
        $("#signupForm").find("select").each(function () {
            $(this).attr("disabled",true);
        });
        $("#signupForm").find("button").each(function () {
            var type = $(this).attr("type");
            if(type == 'submit' ) {
                $(this).hide();
            }
        });
        $(".updocss_b").find("button").each(function () {
            $(this).attr("disabled",true);
        })
    }
}
