<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>CMS综合运营管理平台</title>
<meta name="description" content="">
<meta name="author" content="ZHENGJINGUANG">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
<link href="loginin/images/logo.png" rel="icon" />
<link href="loginin/images/logo.png" rel="apple-touch-icon" />
<link href="loginin/css/bootstrap.min.css" rel="stylesheet" />
<link href="loginin/css/login.css" rel="stylesheet" />
</head>
<body>
	<div id="login">
		<div class="form">
			<div class="container-fluid">
				<div class="row">
				 
				 <!--  <div class="col-sm-12 text-center">
						 <h2><font color="white">新时代后台管理系统</font></h2> 
						<h3><font color="white"><nobr>New Time Securities Management System</nobr></font></h3>
					</div> --> 
				
					 
					<div class="col-sm-12 text-center error-msg">
						<c:if test="${not empty error}">
							<div class="error">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg">${msg}</div>
						</c:if>
					</div>
				</div>
			</div>
			
			<form class="form-horizontal" action="login" method='POST'>
				<div >
				<!-- 	<label for="inputEmail3" class="col-sm-2 control-label">用户名：</label> -->
					<div class="col-sm-offset-1  col-sm-10 ">
						
						<input type="text" class="form-control" id="username" 
							name="username" placeholder="用户名">
					</div>
				</div>
			
				<div id="Box1">
					<!--  <label for="inputPassword3" class="col-sm-2 control-label">密
						码：</label>   -->
					<div class="col-sm-offset-1 col-sm-10 divb">	
						<input type="password" class="form-control" id="password"
							name="password" placeholder="密码">
					</div>
				</div>
				<div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</div>
				<div class="form-group hide">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label for="remember_me"> <input id="remember_me"
								name="remember_me" type="checkbox"> 记住我
							</label>
						</div>
					</div>
				</div>
				<br>
				
				<div id="Box2">
					<div class="col-sm-offset-1 col-sm-10 col-lg-10 divb">
						<button type="submit" name="submit"
							class="btn btn-default btn-warning btn-block">登录</button>
					</div>
				</div>
				
			</form>
			   <div class="form-group">
			
		</div>
		
	</div>
	<script type="text/javascript">
		$(document).ready(
		/*function() {
			//表单提交
			$("#submit").on(
					"click",
					function(event) {
						var username = $("#username").val();
						var password = $("#password").val();
						var data = "username=" + username + "&"
								+ "password=" + password;
						var url = "/platform/login";
						$.ajax({
							type : "post",
							url : url,
							data : data,
							success : function(data) {
								console.log("success");
							}
						});

					})}*/

		);
	</script>
</body>
</html>
