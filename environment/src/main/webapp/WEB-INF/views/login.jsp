<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = (String)request.getContextPath() ;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能控制系统登录</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link rel="stylesheet" type="text/css" href="<%=path %>/css/login.css" />

</head>

<body>

	<div id="content" class="outFrame">
		
		<div class="bj_3" style="background:url(<%=path %>/images/bj_03.jpg)  center no-repeat">
			<div class="login_box" style="background:url(<%=path%>/images/sogin.png) no-repeat">
				<form id="login_form" method="post">
				<div class="input1">
				<input type="text" id="username" name="username" style="text-align: center" class="inputer" value=""/>
				</div>
				<div class="input2" style="margin-top: 64px">
					<input type="password" id="password" name="password" style="text-align: center" class="inputer" value=""/>
				</div>
				<!-- <div class="go"><input id="btn" type="image" name="image"  src="<%=path %>/images/go.png" title="单击此处登录" onClick=""/></div> -->
				<div style="margin-left:41px;margin-top:65px"><input id="btn" type="button" style="background:#5bc0de; width:272px; height:36px" value="登陆" title="单击此处登录"/></div>
				
				<div class="clear"></div>
				</form>
			</div>
		</div>
		
	</div>
	
	<script src="<%=path %>/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$("#btn").click(function() {
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
						url: '<%=path%>/login.do' ,
						method: 'POST' ,
						async: false ,
						data: {username:username,password:password} ,
						dataType: 'JSON' ,
						success: function(msg)
						{
							if ( msg == '1001' ) {
								window.location.href="<%=path%>/index" ;
							} else if ( msg == '1000' ) {
								alert('无此用户,请联系管理员！') ;
								$("#username").val("") ;
								$("#password").val("") ;
								$("#username").focus();
							} else if ( msg == '1002' ) {
								alert('账户密码不匹配！') ;
								$("#password").val("") ;
								$("#password").focus();
								}
								
						}
						
						});
				}
			}) ;
	</script>
</body>
</html>
