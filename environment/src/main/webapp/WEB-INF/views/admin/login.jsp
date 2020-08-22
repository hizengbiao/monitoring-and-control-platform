<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = (String)request.getContextPath() ;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能控制系统后台</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/body.css"/> 
</head>
<body onresize="change();" onload="change()">
<div class="container" id="content">
	<section >
		<form method="post" onsubmit="return false">
			<h1>智能控制控制</h1>
			<div>
				<input type="text" placeholder="账号" required="" name="username" id="username" />
			</div>
			<div>
				<input type="password" placeholder="密码" required="" name="password" id="password" />
			</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div> 
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="登录" class="btn btn-primary" id="btn" onclick="login()"/>
				<a href="#">忘记密码?</a>
				<!-- <a href="#">Register</a> -->
			</div>
		</form><!-- form -->
		 
	</section><!-- content -->
</div>
<!-- container -->
	<script type="text/javascript">

			function login(){
				var username = $("#username").val().trim();
				var password = $("#password").val().trim();
				
				if ( username == "" ) {
					alert('请输入用户名!') ;
					$("#username").focus();
				}
				else {
					if ( password == "" ){
						alert('请输入密码!') ;
						$("#password").focus();
					}
				}
				
				if ( username != "" && password != "" )
				{
					$.ajax({
						url: '<%=path%>/admin/login.do' ,
						method: 'POST' ,
						async: false ,
						data: {username:username,password:password} ,
						dataType: 'JSON' ,
						success: function(msg)
						{
							if ( msg == '1001' ) {
								window.location.href="<%=path%>/admin/main" ;
							} else if ( msg == '1000' ) {
								alert('无此用户,请联系管理员！') ;
								$("#username").val("") ;
								$("#password").val("") ;
								$("#username").focus();
							} else if ( msg == '1002' ) {
								alert('账户密码不匹配！') ;
								$("#password").val("") ;
								$("#password").focus();
							} else if ( msg == '1003' )
							{
								alert("您不是管理员！无登陆权限");
							}
								
						}
						
						});
				}
			}
	
			function change ()
			{
				var content = document.getElementById("content") ;
				var contentHeight = $("#content").height() ;
				var docHeight = $(document).height() ;
				content.style.marginTop = ( docHeight - contentHeight ) / 2 + 'px' ;
				
			}
        </script>
        
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path %>/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>
</body>
</html>