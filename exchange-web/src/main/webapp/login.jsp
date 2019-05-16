<!DOCTYPE html>
<%@ page language="java" import="java.util.*, java.io.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    /* 获得项目相对路径 */
    String path = request.getContextPath();
    /* 获得项目完全路径 */
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    ResourceBundle rb;
    BufferedInputStream inputStream;
    String cd_version = null;
    try {
        inputStream = new BufferedInputStream(new FileInputStream("/usr/local/PlugIn/Product.properties"));
        rb = new PropertyResourceBundle(inputStream);
        cd_version = rb.getString("version");
        inputStream.close();
    } catch (FileNotFoundException e) {
        cd_version = "LANCOO";
    } catch (IOException e) {
        e.printStackTrace();
    }
%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="renderer" content="webkit">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>云桌面管理平台</title>
		<link rel="shortcut icon" href="images/title.ico"/>
		<base href="<%=basePath%>">		
		<!-- CSS -->
		<!--[if lte IE 8]> 
			<script type="text/javascript">
			   window.location.href="./update.html" 
			</script>
		<![endif]-->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" type="text/css" href="js/layer/skin/layer.css">
        <link rel="stylesheet" href="css/login_style.css">
        
        <style>
        	input:-webkit-autofill, textarea:-webkit-autofill, select:-webkit-autofill {
			        -webkit-box-shadow: 0 0 0px 1000px white inset;
				    border: 1px solid #fff!important;
				    margin-left: 37px;
				    width: 261px;
				    padding-left: 0px;
				    height: 37px;
			}
			
        </style>
        <script type="text/javascript" src="js/common/md5.js"></script>
        <script type="text/javascript" src="js/common/base64.js"></script>
        <script type="text/javascript" src="js/common/clientSign.js"></script>
        <script type="text/javascript" src="js/jquery2.2.min.js"></script>
        <script type="text/javascript" src="js/layer/layer.js"></script>
        <script type="text/javascript" src="login.js?time="20111116"></script>        
	</head>
	<body style="margin: 0 auto;">
        <div class="content_head">
        	<div id="content_head_img"></div>
        </div>
        <div class="content_body">
            <div class="login_input">
                <form>
                    <div>
                        <input type="text" maxlength="25" name="username" id="username" placeholder="请输入您的用户名" class="login_user_name">
                    </div>
                    <div>
                        <input type="password" maxlength="25" name="password" id="password" placeholder="请输入密码" class="login_user_password">
                    </div>           
                        <input type="button" id="submit" class="submit_button" />
                        <input type="button" id="reset" class="reset_button" value="重置"
                    style="display: none;" />
                </form>
            </div>
        </div>		
        <div class="button_foot" id="login_foot"></div>
	</body>
	<script type="text/javaScript">
		$(document).ready(function() {
	       	localStorage.versionType = "<%=cd_version%>" ;
	       	var footDOM = document.getElementById("login_foot");
	       	var bgImgDOM = document.getElementById("content_head_img");
	       	if(localStorage.versionType === "LANCOO"){
	       		bgImgDOM.classList.add('lancoo_img');
	       		document.title = "蓝鸽云桌面管理平台";
	       		footDOM.innerHTML = '蓝鸽集团 版权所有 LAG-DM5.1';
	       	}else{
	       		bgImgDOM.classList.add('hyt_img');
	       		document.title = "海云天云桌面管理平台";
	       		footDOM.innerHTML = '<span style="margin-right: 10px;">型号:</span>HYTCDM_V5.1  <span style="margin-right: 10px;">制造商:</span>浙江海云天科技有限公司'
	       	}
			if(window != top) {
			 	top.location.href=location.href;
			}
		})
	</script>
		
</html>