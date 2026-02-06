$().ready(function() {
	$("#scoreTable").find("td[name='score']").each(function () {
		$(this).blur(function (){
			calTotalScore();
		});
	});
	validateRule();
});
function calTotalScore() {
	let totalScore = 0;
	$("#scoreTable").find("td[name='score']").each(function () {
		let score = $(this).prev();//上一个兄弟节点
		console.log(score.text());
		if(score.text().indexOf("总分") != -1 || score.text().indexOf("签字") != -1 || !$(this).text()) {
		}else {
			score = parseFloat(score.text().replace("分",""));
			let setScore = parseInt($(this).text().trim());
			totalScore = totalScore + setScore;
			console.log("calScore:",totalScore, setScore);
			if(setScore > score) {
				$(this).css("background-color","red");
				parent.layer.alert("分值不能大于指定的分值");
			}else {
				$(this).css("background-color","");
			}
			$("#appraiseSum").text(totalScore);
		}
	});
}

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	let postData = new Object();
	let scoreIsOk = true;
	let totalScore = 0;
	$("#scoreTable").find("td[name='score']").each(function () {
		let score = $(this).prev();//上一个兄弟节点
		if(score.text().indexOf("总分") != -1 || score.text().indexOf("签字") != -1 || !$(this).text()) {
		}else {
			score = parseFloat(score.text().replace("分",""));
			let setScore = parseFloat($(this).text().trim());
			totalScore+= setScore;
			if(setScore > score) {
				$(this).css("background-color","red");
				parent.layer.alert("分值不能大于指定的分值");
				scoreIsOk = false;
			}else {
				$(this).css("background-color","");
			}
		}

		var name = $(this).attr("id");
		postData[name] = $(this).text();
	});

	if(!scoreIsOk) {
		return;
	}

	postData['appraiseSum'] = totalScore;
	postData['sumRecommend'] = $("#sumRecommend").text().trim();

	$("#appraiseSum").html(totalScore);

	$("#scoreTable").find("input").each(function () {
		let val = $(this).val();
		let name = $(this).attr('name');
		postData[name] = val;
	});
	console.log(postData);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/cpe/qcResultInnovateScore/save",
		data : postData,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				$("#id").val(data.id);
				parent.layer.msg("操作成功");
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
