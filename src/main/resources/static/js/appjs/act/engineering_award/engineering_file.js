var files = [];
var currentFileId = "";

$().ready(function () {

   $(".btn-block").click(function (event) {
        let proId = $("input[name='proId']").val();
        let taskId = $("input[name='taskId']").val();
        let fileType = $("input[name='fileType']").val();
        var uploadDocPage =  layer.open({
            title: '上传文件',
            maxmin: true,
            type: 2,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: '/enterpriseQualityAward/toUploadSituationDoc?taskId='+taskId+'&proId=' + proId + '&fileType=' + fileType // iframe的url
        });


    });
});


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
