var proIds;

var files = [];
$().ready(function () {

$("#upFileInfo").html("");
    var filedids =  $("#fileId").val();
    var fileUrls =  $("#fileUrl").val();
    var filePath = "";

    var allIds =  filedids.split(",");
    var allUrls =  fileUrls.split(",");

    console.log(JSON.stringify(allIds))
    for(var i=0;i<allIds.length;i++){
       var fileObj = {};
        if (allIds[i].length == 0){
            break;
        }
        fileObj.imgPath = allUrls[i];
        fileObj.fileSaveId = allIds[i];
        files.push(fileObj);
        filePath += "<a href='"+fileObj.imgPath+"' target='_blank'> 下载 </a> <br>    ";
    }

    $("#upFileInfo").html(filePath);

});
