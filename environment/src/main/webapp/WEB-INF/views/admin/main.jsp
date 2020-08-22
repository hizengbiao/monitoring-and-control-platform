<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = (String)request.getContextPath() ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>智能控制系统_后台管理</title>

		<!-- Bootstrap -->
		<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style type="text/css">
body {
	padding-bottom: 50px;
	padding-top: 70px;
	min-width:1000px;
	min-height:600px;
}
</style>
	</head>

	<body onResize="change();">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand active" href="<%=path %>/admin/main">智能控制系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="<%=path %>/index" target="_blank"> <span class="glyphicon glyphicon-th"
							aria-hidden="true"></span> 控制台</a>
					</li>
					<li>
						<a href="<%=path%>/admin/logout"> <span class="glyphicon glyphicon-log-out"
							aria-hidden="true"></span> 退出系统</a>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
		</nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 col-xs-0">
					<div class="container-fluid navbar-collapse collapse in" style="padding-right:0">

						<!--
        	
        	<div class="col-sm-3 col-md-2 sidebar">
        -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-2" style=" padding-right:0">
							<ul class="nav nav-sidebar">
								<li>
									<a style="padding-top: 10px; padding-bottom: 10px" href="<%=path %>/admin/index" target="content">
										<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
										首页</span>
									</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/base" target="content">
										<span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
										基地管理</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/shed" target="content">
										<span class="glyphicon glyphicon-cloud" aria-hidden="true"></span>
										大棚管理</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/corp" target="content">
										<span class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span>
										作物管理</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/user" target="content">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
										用户管理</a>
								</li>
<!-- 								<li> -->
<%-- 									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/envlog" target="content"> --%>
<!-- 										<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> -->
<!-- 										成长日志</a> -->
<!-- 								</li> -->
                                <li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/opelog" target="content">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										操作日志</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/center" target="content">
										<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
										个人设置</a>
								</li>
								<li>
									<a style="padding-top: 15px; padding-bottom: 15px" href="<%=path %>/admin/logout">
										<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
										退出系统</a>
								</li>
							</ul>
						</div><!-- collapse navbar-collapse -->
					</div><!-- container-fluid navbar-collapse collapse in -->
					<!-- </div> -->
				</div><!-- rol-xs-3-->
                <div class="col-sm-10 col-xs-12" style="height:100%">
                	<div class="container-fluid">
                		<iframe id="content" name="content" src="index.html" style="width:100%" frameborder="0" scrolling="auto"></iframe>
                    </div>
                </div>
			</div><!-- row -->
		</div> <!-- container-fluid -->
		

		</div>
		</div>
		<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container-fluid"
			style="text-align: center; margin-top: 10px">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div style="color: #000">
				CopyRight@2017
				<a href="troyforever.com" target="_blank">&nbsp;浅晨夕</a>
			</div>
		</div>
		<!-- /.container-fluid -->
		</nav>
        
        <script type="text/javascript">
			window.onload = function() {
			//var winWidth = parseInt(document.documentElement.clientWidth);// 客户端浏览器宽
			var winHeight = parseInt(document.documentElement.clientHeight);// 客户端浏览器高度
			// iframe全屏
			//document.getElementById("frmDialog").width = winWidth + "px";
			document.getElementById("contenta").height = (winHeight-120) + "px";
			// iframe半屏
			//document.getElementById("frmDialog").width = (winWidth / 2) + "px";
			//document.getElementById("frmDialog").height = (winHeight / 2) + "px";
			}; 

        </script>
		
       <script type="text/javascript"> 

		window.onload = function() { 
		
			var iframe = document.getElementById("content");  
		
			iframe.style.height = (window.innerHeight-150) + 'px'; 
		
		}; 
		
		</script>
        
        <script type="text/javascript">
			function change ()
			{
				var iframe = document.getElementById("content");  
				
		
			iframe.style.height = (document.documentElement.clientHeight-130) + 'px'; 
			}
        </script>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="<%=path %>/js/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="<%=path %>/js/bootstrap.min.js"></script>
	</body>
</html>