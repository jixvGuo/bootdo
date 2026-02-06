var prefix ="/specialist"
$(function() {
	load();
    getTreeData();

    $("select#award_select").change(function () {
        getTreeData();
        awardOnSelect();
    });
});

function load() {
    awardOnSelect();
}

/***
 * 选择奖项的触发条件
 */
function awardOnSelect() {
    var awardSubType = $("#awardSubType").val();
    var account = null;
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
    $("#tableHead").html("<th>序号</th>\n" +
           "                            <th>奖项名称</th>\n" +
           "                            <th>奖项类型</th>\n" +
           "                            <th>申请单位</th>\n" +
           "                            <th>操作</th>");
    $.ajax({
        type : 'GET',
        url : prefix + '/getCurLeaderPro?proType=' +  type+'&major='+major+'&account='+account,
        success : function(r) {
            console.log("==aaaa==" + JSON.stringify(r));
            $("#list").html("");
            for(var i=0;i<r.length;i++){
                var obj = r[i];
                var str = "<tr>\n" +
                    "                            <td style=\"width:49px\">"+(i+1)+"</td>\n" +
                    "                            <td style=\"width:199px\">"+obj.showProName+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.proTypeStr+"</td>\n" +
                    "                            <td style=\"width:99px\"contentEditable=\"true\">"+obj.enterpriseName+"</td>\n" +
                    "                            <td style=\"width:229px\">\n" +
                    "                                <button id=\"specialistScoreTotal\" type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onScoreSheet("+obj.id+")\">查看打分表</button>\n" +
                    "                                <button type=\"button\" class=\"btn btn-primary btn-xs\" onclick=\"onShowForm("+obj.id+",0)\">查看评审意见表</button>\n" +
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
    console.log("选择的类型--->" + type);
    if (type == 'science_personal'){
        // 个人
        layer.open({
            type : 2,
            title : "专家-个人打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/personal/summery?major='+major+'&account='+account + "&scoreType=personal_score" // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家-科技奖打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/science/down/summery?major='+major+'&account='+account + "&scoreType=science_score" // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家-团队打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '900px', '520px' ],
            content : prefix + '/team/summery?major='+major+'&account='+account + "&scoreType=team_score" // iframe的url
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
    console.log("选择的类型--->" + type);
    if (type == 'science_personal'){
       // 个人
        layer.open({
            type : 2,
            title : "专家-个人打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/personal/summery?major='+major+'&account='+account + "&scoreType=personal_score" // iframe的url
        });

    }else if (type == 'science_progress'){
      //科技
        layer.open({
            type : 2,
            title : "专家-科技奖打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/science/summery?major='+major+'&account='+account + "&scoreType=science_score" // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家-团队打分汇总表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/team/summery?major='+major+'&account='+account + "&scoreType=team_score" // iframe的url
        });
    }

}

/***
 * 查看打分表
 */
function onScoreSheet(proId) {
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
            content : prefix + '/personal/sheet?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家科技打分表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/science/form?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家团队打分表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/team/sheet?proId='+proId+'&major='+major+'&account='+account+'&scoreType='+type // iframe的url
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
            $("#treeview1").treeview({levels:1,enableLinks: false,data:treeData,
                onNodeSelected: function (event, data) {
                    console.log("seleNode -->",data);
                    var href = data.href;
                    var nodeTxt = data.text;
                    $("#specialistScoreTotal").hide();
                    $("input[name='specialistAccount']").val('');
                    $("input[name='major']").val('');
                    if(href == '#major') {
                        //专业
                        $("input[name='major']").val(nodeTxt);
                    }else {
                        //专家账号
                        $("input[name='major']").val(data.parentText);
                        $("input[name='specialistAccount']").val(nodeTxt);
                        $("#specialistScoreTotal").show();
                    }

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
            content : prefix + '/optionPersonalTable?proId='+proId+"&itemId="+itemId+"&account="+account+"&major"+major+"&scoreType=personal_score&proType=" + type // iframe的url
        });

    }else if (type == 'science_progress'){
        //科技
        layer.open({
            type : 2,
            title : "专家科技团队评审意见表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/optionScienceTable?proId='+proId+"&itemId="+itemId+"&account="+account+"&major"+major+"&scoreType=science_score&proType=" + type // iframe的url
        });
    }else if (type == 'science_team'){
        //团队
        layer.open({
            type : 2,
            title : "专家团队评审意见表",
            maxmin : true,
            shadeClose : false,
            area : [ '800px', '520px' ],
            content : prefix + '/optionTeamTable?proId='+proId+"&itemId="+itemId+"&account="+account+"&major"+major+"&scoreType=team_score&proType=" + type // iframe的url
        });
    }
}

/**
 * 下载专家评审打分表
 */
function downScoreTable(){
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
        content: '/specialist/expert_sore/toPrint?account=' + account +'&scoreMajor='+ major+"&scoreType=" + scoreType + "&proType=" + type  // iframe的url
    });

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

