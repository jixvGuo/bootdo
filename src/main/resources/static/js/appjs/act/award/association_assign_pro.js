var proIds;
$(function() {
	getProTreeData();
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
	    var assWorkers = $("#assWorker").val();
		$("#assWorkers").val(assWorkers);
		getAllSelectNodes();
		assignPro();
	}
});
function loadProTree(proTree) {
	$('#proTree').jstree({
		"plugins" : [ "wholerow", "checkbox" ],
		'core' : {
			'data' : proTree
		},
		"checkbox" : {
			//"keep_selected_style" : false,
			//"undetermined" : true
			//"three_state" : false,
			//"cascade" : ' up'
		}
	})
	$('#menuTree').jstree('open_all');
}


function getAllSelectNodes() {
	var ref = $('#proTree').jstree(true); // 获得整个树
	proIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
	console.log("getSelectedProIds",proIds);
	for(var i=0;i<proIds.length;i++){
	    var pid = proIds[i];
		if(pid.length > 4 && pid.substring(0, 4) == 'task') {
			console.log("proIds pop ",pid);
			proIds.pop();
		}
	}
	console.log(proIds);
	$("#proIds").val(proIds);
}
function getProTreeData() {
	var publishTaskId = $('#publishTaskId').val();
	$.ajax({
		type : "GET",
		url : "/enterprise_pro/pro_tree",
		data:{
			publishTaskId:publishTaskId
		},
		success : function(data) {
			var mt = JSON.parse(data);
			loadProTree(mt);
		}
	});
}
function assignPro() {
	$('#proIds').val(proIds);
	var assignProData = $('#signupForm').serialize();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/scienceTask/assignPro",
		data : assignProData, // 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
				parent.layer.msg(r.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(r.msg);
			}

		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			roleName : {
				required : true
			}
		},
		messages : {
			roleName : {
				required : icon + "请输入角色名"
			}
		}
	});
}