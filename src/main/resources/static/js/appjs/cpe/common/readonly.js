$(function () {
    readonly();
});
function readonly() {
    if ($("#isReadonly").val() == 1) {
        $("input").attr("readonly", true);
        $("textarea").attr("readonly", true);
        $("a").each(function () {
            let name = $(this).html();
            if (name == '修改' || name == "删除") {
                $(this).remove();
            }
        });
        $(".button").each(function () {
            let name = $(this).html();
            if (name == '保存' || name == '更新' || name == '提交' || name == '增加' || name == '修改') {
                $(this).remove();
            }
        });
        $(".file-box").each(function () {
            $(this).find(".buttons").remove();
        });
        $(".btn-block").each(function () {
            $(this).remove();
        });

        $("button").remove();
        $("td").attr("contenteditable", false);
        $("td").css("pointer-events", "none");
    }
}
