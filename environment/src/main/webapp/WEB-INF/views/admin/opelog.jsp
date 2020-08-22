<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	List<User> users = (List<User>) request.getAttribute("users") ;
	List<Shed> sheds = (List<Shed>) request.getAttribute("sheds") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>日志管理</title>

    <!-- Bootstrap -->
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		 th{text-align:center; width:10%}
		 td{text-align:center}
	</style>
  </head>
  
  
  <body>
    <ol class="breadcrumb">
      <li><a href="<%=path%>/admin/index">首页</a></li>
      <li class="active">日志管理</li>
    </ol>
    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">日志列表</h2>
          </div>
          <div class="col-xs-4">
          	<select id="user" style="margin-top:20px;height:33px;width:100%;" onchange="change()">
          		<option value="0">--请选择用户--</option>
          	<% for ( User user : users ) { if ( user.getIsAdmin() == 0 ) { %>
          		<option value="<%=user.getId()%>"><%=user.getName() %></option>
          	<% } } %>
          	</select>
          </div>
            <div class="col-xs-4">
          	<select id="shed" style="margin-top:20px;height:33px;width:100%" onchange="change()">
          		<option value="0">--请选择大棚--</option>
          	<% for ( Shed shed : sheds ) { %>
          		<option value="<%=shed.getId()%>"><%=shed.getCode() %></option>
          	<% } %>
          	</select>
            </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>用户</th>
                  <th>基地</th>
                  <th>操作</th>
                  <th>时间</th>
                </tr>
              </thead>
              <tbody id="content">
              </tbody>
            </table>
           </div>
          
          	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path %>/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function () {
			getData(0,0) ;
		});

		function getData(userId, shedId)
		{
			$.ajax({
				url: '<%=path%>/admin/opelog.do' ,
				data: {userId:userId, shedId:shedId} ,
				method: 'POST' ,
				dataType: 'JSON' ,
				success: function ( data )
				{
					showLog(data) ;
				}
			});
		}

		function change ( )
		{
			$("#content").empty();
			var userId = $("#user").val();
			var shedId = $("#shed").val();
			getData(userId, shedId) ;
		}

		function showLog ( data )
		{
			var content = $("#content") ;
			for ( var i = 0 ; i < data.length ; i ++ )
			{
				var name = data[i].user.name ;
				var shed = data[i].shed.code ;
				var operate = data[i].operate ;
				var date = new Date(data[i].time) ;
				var time = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + (date.getDate()) ;

				var str = '<tr><td>' + name + '</td><td>' + shed + '</td><td>' + operate + '</td><td>' + time + '</td></tr>' ;
				content.append(str) ;
			}
		}
	</script>
  </body>
</html>