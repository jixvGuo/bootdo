
function toViewUploadDoc(fileName, url) {
    parent.layer.open({
        type: 2,
        title: fileName,
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: url  // iframe的url
    });
}

/***
 * 删除文件
 * @param id
 */
function delUploadDoc(that,id){
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/petroleumEngineering/removeQualityFile/desc",
            data: {
                id:id
            },
            async: false,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                if (data.code == 0) {
                    $(that).parent().parent().parent().remove();
                }
                layer.msg(data.msg);
            }
        });
    })
}
