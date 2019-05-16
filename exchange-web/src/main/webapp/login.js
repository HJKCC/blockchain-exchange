//初始化JS
sessionStorage.priResetPasswd = "0";
$(document).ready(function(){

  if(sessionStorage.username){
      $("#username").val(sessionStorage.username);
  }
  //用户登录
  $('#submit').click(function(){
	  submit();
  });

  //重置信息
  $('#reset').click(function(){
	  reset();
  });

  //enter按钮
  $(document).keydown(function(event){
    if(event.keyCode == 13){
        $('#submit').click();
    }
  });
});

function submit() {
	var name = $("#username").val();
  sessionStorage.username = name;
	if (name == "") {
    	layer.msg(
    			"用户名不能为空",
    			{time: 1500, shift:6},
    			function(){
    				$("#name").focus();
    				}
    		);
        return false;
    }
	var pwd = $("#password").val();
    if (pwd == "") {
    	layer.msg(
    			"用户密码为空",
    			{time: 1500, shift:6},
    			function(){
    				$("#pwd").focus();
    			}
    		);
        return false;
    }
    var rename = name;
    name = BASE64.encode(name);
    pwd = BASE64.encode(pwd);
    $.ajax({
    	url: 'login.action',
    	data:{
    		userName: name,
    		userPwd: pwd
    		},
    	type:'POST',
    	async: false,
    	dataType: 'json',
    	success:function(data){
    		localStorage.authMethodNew = "1";
    		localStorage.authAddr = "http://60.12.93.75/UserMgr/Login/Login.aspx";
    		var portnumber = "80"
    		if(data.authenticationFlag == "1"){
    			localStorage.authMethodNew = "0";
    			portnumber = data.authenticationPort || "80";
    			localStorage.authAddr = "http://" + data.authenticationIP + ":" + portnumber + "/UserMgr/Login/Login.aspx";
    		}
    		layer.msg(
    				data.info,
					{time: 1500}
				);
			if(data.code == "1") {		
				localStorage.loginUserName = rename;
				var userRole = {
					purviewType:data.object.purviewType,
					subPurviews:data.object.subPurviews
				}
				if(pwd == "ODg4ODg4"){
					sessionStorage.priResetPasswd = "1";
				}
				localStorage.thisUserRoleInfo = JSON.stringify(userRole);
				switch (data.object.purviewType[0]) {
				case "2":
					location.href='./jsp/NewPage/newIndexPage?time=2018_12_1';
					break;
				case "3":
					location.href='./jsp/NewPage/serverNode';
					break;
				case "4":
					location.href='./jsp/NewPage/template';
					break;
				case "5":
					location.href='./jsp/NewPage/users';
					break;
				case "6":
					location.href='./jsp/NewPage/cloudDesktopAdmin';
					break;
				case "7":
					location.href='./jsp/NewPage/terminalAdmin';
					break;
				/*
				case "8":
					location.href='./jsp/NewPage/virtualNet';
					break;
				*/
				default:
					layer.msg(
		            		"本用户暂无权限浏览管理平台内容信息，请联系管理员授权！",
							{time: 2000}
						);
					break;
				}
	    	} else {
	    		reset();
	    	}},
    	error : function(){
			layer.msg(
            		"网络异常，登录失败！",
					{time: 1500}
				);
		}
    });
}

function reset() {
	$("#password").val("");
}
