var prefix ="/specialist"
$(function() {
    getTreeData();

    $("select#award_select").change(function () {
        getTreeData();
        $("#list").html("");
    });
});

/***
 * 选择奖项的触发条件
 */
function awardOnSelect() {
   var type =  $('#award_select').val();
   console.log("选择的类型" + type);
   var major = $("input[name='major']").val();
   var account = $("input[name='specialistAccount']").val();
   if(!account) {
        $("#viewSummaryBtn").show();
        $("#downSummaryBtn").show();
        $("#downScoreTableBtn").hide();
        $("#downReviewTableBtn").hide();
   }else {
        $("#viewSummaryBtn").hide();
        $("#downSummaryBtn").hide();
        $("#downScoreTableBtn").show();
        $("#downReviewTableBtn").show();
   }
   if(type == 'science_progress') {
       getScienceProList(type,major,account);
   }else if(type == "science_team") {
       getTeamProList(major,account);
   }else if(type == "science_personal") {
       getPersonalProList(major,account);
   }

}

function getTeamProList(major,account) {
    $("#tableHead").html("<th>序号</th>\n" +
        "                            <th>项目编号</th>\n" +
        "                            <th>团队名称</th>\n" +
        "                            <th>奖项类型</th>\n" +
        "                            <th>申请单位</th>\n" +
        "                            <th>操作</th>");
    $("#list").html("");
    $.ajax({
        type : 'GET',
        url : prefix + '/getTeamPros?major='+major+'&account='+account +"&taskId=" + $("#taskId").val(),
        success : function(r) {
            for(var i=0;i<r.length;i++){
                var obj = r[i];
                var str = "<tr>\n" +
                    "                            <td style=\"width:49px\">"+(i+1)+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.proResultCode+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.proName+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.proType+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.enterpriseName+"</td>\n" +
                    "                            <td style=\"width:229px\">\n" +
                    "                                <button id=\"specialistScoreTotal\" type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onScoreSheet("+obj.proId+","+obj.itemId+")\">查看打分表</button>\n" +
                    "                                <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onShowForm("+obj.proId+","+obj.itemId+")\">查看评审意见表</button>\n" +
                    "                            </td>\n" +
                    "                        </tr>";
                $("#list").append(str);
            }
        }
    });
}

function getPersonalProList(major,account) {
    $("#tableHead").html("<th>序号</th>\n" +
        "                            <th>项目编号</th>\n" +
        "                            <th>用户姓名</th>\n" +
        "                            <th>奖项类型</th>\n" +
        "                            <th>申请单位</th>\n" +
        "                            <th>操作</th>");
    $("#list").html("");
    $.ajax({
        type : 'GET',
        url : prefix + '/getPersonalPros?major='+major+'&account='+account + "&taskId=" + $("#taskId").val(),
        success : function(r) {
            for(var i=0;i<r.length;i++){
                var obj = r[i];
                var str = "<tr>\n" +
                    "                            <td style=\"width:49px\">"+(i+1)+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.proResultCode+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.proName+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.proTypeStr+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.enterpriseName+"</td>\n" +
                    "                            <td style=\"width:229px\">\n" +
                    "                                <button id=\"specialistScoreTotal\" type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onScoreSheet("+obj.proId+","+obj.itemId+")\">查看打分表</button>\n" +
                    "                                <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onShowForm("+obj.proId+","+obj.itemId+")\">查看评审意见表</button>\n" +
                    "                            </td>\n" +
                    "                        </tr>";
                $("#list").append(str);
            }
        }
    });
}

function getScienceProList(type,major,account){
    $("#tableHead").html("<th>序号</th>\n" +
           "                            <th>项目编号</th>\n" +
            "                            <th>奖项名称</th>\n" +
           "                            <th>奖项类型</th>\n" +
           "                            <th>申请单位</th>\n" +
           "                            <th>操作</th>");
    $("#list").html("");
    $.ajax({
        type : 'GET',
        url : prefix + '/getCurLeaderPro?proType=' +  type+'&major='+major+'&account='+account + "&applyType=science",
        success : function(r) {
            console.log("====" + JSON.stringify(r));
            for(var i=0;i<r.length;i++){
                var obj = r[i];
                var str = "<tr>\n" +
                    "                            <td style=\"width:49px\">"+(i+1)+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.proCode +"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.showProName+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.proTypeStr+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.enterpriseName+"</td>\n" +
                    "                            <td style=\"width:229px\">\n" +
                    "                                <button id=\"specialistScoreTotal\" type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onScoreSheet("+obj.id+","+obj.itemId+")\">查看打分表</button>\n" +
                    "                                <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onShowForm("+obj.id+","+obj.itemId+")\">查看评审意见表</button>\n" +
                    "                            </td>\n" +
                    "                        </tr>";
                $("#list").append(str);
            }
        }
    });
}

function downSummary(){
    var type =  $('#award_select').val();
    var account = $("input[name='specialistAccount']").val();
    var major = $("input[name='major']").val();
    var taskId = $("#taskId").val();
    console.log("选择的类型--->" + type);
    if (type == 'science_personal'){
        // 个人
        layer.open({
            type : 2,
            title : "专家-个人打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/personal/down/summery?major='+major+'&account='+account + "&scoreType=personal_score&proTypes=science_personal,science_team&taskId=" + taskId // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家-科技奖打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/science/down/summery?major='+major+'&account='+account + "&scoreType=science_score&taskId=" + taskId // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家-团队打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/down/expert_score?major='+major+'&account='+account + "&scoreType=team_score&proTypes=science_personal,science_team&taskId=" + taskId // iframe的url
        });
    }

}

/***
 *  查看打分汇总表 View scoring summary table
 */
function viewSummary() {
    var type =  $('#award_select').val();
    var account = $("input[name='specialistAccount']").val();
    var major = $("input[name='major']").val();
    var taskId = $("#taskId").val();
    console.log("选择的类型--->" + type);
    if (type == 'science_personal'){
       // 个人
        layer.open({
            type : 2,
            title : "专家-个人打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/personal/summery?major='+major+'&account='+account + "&scoreType=personal_score&taskId=" + taskId // iframe的url
        });

    }else if (type == 'science_progress'){
      //科技
        layer.open({
            type : 2,
            title : "专家-科技奖打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/science/summery?major='+major+'&account='+account + "&scoreType=science_score&taskId=" + taskId // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家-团队打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/team/summery?major='+major+'&account='+account + "&scoreType=team_score&taskId=" + taskId // iframe的url
        });
    }

}

/***
 * 查看打分表
 */
function onScoreSheet(proId, itemId) {
    var type =  $('#award_select').val();
    var account = $("input[name='specialistAccount']").val();
    var major = $("input[name='major']").val();
    console.log("选择的类型:" + type);
    if (type == 'science_personal'){
        // 个人
        layer.open({
            type : 2,
            title : "专家个人打分表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/personal/sheet?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type + "&itemId=" + itemId // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家科技打分表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/science/form?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type + "&itemId=" + itemId // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家团队打分表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/team/sheet?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type + "&itemId=" + itemId // iframe的url
        });
    }

}


function getTreeData() {
    let selType = $("#award_select").val();
    $.ajax({
        type : "GET",
        url : prefix + "/awardGroupTree",
        data:{
            order:'asc',
            proType: selType
        },
        success : function(tree) {
            var flg = tree instanceof Object;
            console.log("roles---->",JSON.stringify(tree),flg);
            var treeData = flg ? tree : JSON.parse(tree);
            $("#specialistScoreTotal").hide();
            $("input[name='specialistAccount']").val('');
            $("input[name='major']").val('');
            $("#treeview1").treeview({levels:1,enableLinks: false,data:treeData,
                onNodeSelected: function (event, data) {
                    console.log("seleNode -->",data, new Date().getTime());
                    var href = data.href;
                    var nodeTxt = data.text;
                    var account = data.extData ? data.extData["account"] : '';

                    if(href == '#major') {
                        if($("input[name='major']").val() == nodeTxt && !$("input[name='specialistAccount']").val()) {
                           console.log("seleNode-selected-", nodeTxt)
                           return
                        }
                        //专业
                        $("input[name='specialistAccount']").val("")
                        $("input[name='major']").val(nodeTxt);
                    }else {
                        if($("input[name='specialistAccount']").val() == account) {
                           console.log("seleNode-selected-", account)
                           return
                        }
                        //专家账号
                        $("input[name='major']").val(data.parentText);
                        $("input[name='specialistAccount']").val(account);
                    }

                    $("#specialistScoreTotal").show();
                    awardOnSelect();
                }
            })
        }
    });

}


/***
 * 查看评审意见表
 */
function onShowForm(proId,itemId) {
    var type =  $('#award_select').val();
    var account = $("input[name='specialistAccount']").val();
    var major = $("input[name='major']").val();
    console.log("选择的类型》》" + type);
    if (type == 'science_personal'){
        // 个人
        layer.open({
            type : 2,
            title : "专家个人评审意见表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/optionPersonalTable?proId='+proId+"&itemId="+itemId+"&scoreAccount="+account+"&groupName="+major+"&scoreType=personal_score&proTypes=" + type // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家科技团队评审意见表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/optionScienceTable?proId='+proId+"&itemId="+itemId+"&scoreAccount="+account+"&groupName="+major+"&scoreType=science_score&proTypes=" + type // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家团队评审意见表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/optionTeamTable?proId='+proId+"&itemId="+itemId+"&scoreAccount="+account+"&groupName="+major+"&scoreType=team_score&proTypes=" + type // iframe的url
        });
    }
}

/**
 * 下载专家评审打分表
 */
function downScoreTable(){
    var type =  $('#award_select').val();
    let proTypes = type
    if(type != 'science_progress') {
       proTypes = 'science_personal,science_team'
        var account = $("input[name='specialistAccount']").val();
        var major = $("input[name='major']").val();
        var taskId = $("#taskId").val();
        console.log("account:" + account);
        console.log("major:" + major);
        let scoreType = getScoreTypeByProType(type);
        console.log("scoreType:" + scoreType);


        layer.open({
            type: 2,
            title: '',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: '/specialist/down/expert_score/other?account=' + account +'&major='+ major+"&scoreType=" + scoreType + "&proTypes=" + proTypes + "&taskId" + taskId  // iframe的url
        });

    }else {
        var account = $("input[name='specialistAccount']").val();
        var major = $("input[name='major']").val();
        var taskId = $("#taskId").val();
        console.log("account:" + account);
        console.log("major:" + major);
        let scoreType = getScoreTypeByProType(type);

        console.log("scoreType:" + scoreType);


        layer.open({
            type: 2,
            title: '',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: '/specialist/down/expert_score?account=' + account +'&major='+ major+"&scoreType=" + scoreType + "&proTypes=" + proTypes + "&taskId" + taskId  // iframe的url
        });
    }


}

/**
 * 下载专家评审意见表
 */
function downReviewTable(){

    var type =  $('#award_select').val();
    var account = $("input[name='specialistAccount']").val();
    var major = $("input[name='major']").val();
    console.log("account:" + account);
    console.log("major:" + major);
    let scoreType = getScoreTypeByProType(type);
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/specialist/expert_advise/toPrint?account=' + account +'&scoreMajor='+ major+"&scoreType="+scoreType +"&proType=" + type  // iframe的url
    });

}

function getScoreTypeByProType(proType) {
    let scoreType = '';
    if (proType == 'science_personal'){
        scoreType = 'personal_score';
    }else if (proType == 'science_progress'){
        scoreType = 'science_score';
    }else if (proType == 'science_team') {
        scoreType = 'team_score';
    }
    return scoreType;
}

