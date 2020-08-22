<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	User user = (User)request.getAttribute("user") ;
	List<Shed> sheds = (List<Shed>)request.getAttribute("sheds") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加种植区域</title>

    <!-- Bootstrap -->
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  
  <body>
  
    <ol class="breadcrumb">
      <li><a href="<%=path %>/admin/index">首页</a></li>
      <li class="active">添加种植区域</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">用户名</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=user.getUsername() %></span>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="address" class="col-sm-3 control-label">姓名</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=user.getName() %></span>
	    </div>
	  </div>
	  	  <div class="form-group">
	    <label for="phone" class="col-sm-3 control-label">联系方式</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=user.getPhone() %></span>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="code" class="col-sm-3 control-label">大棚编码</label>
	    <div class="col-sm-4">
	      <select id="shed" style="height:34px;width:100%; text-align:center">
	      	<option value="0">--请选择种植区域--</option>
	      	<% for ( int i = 0 ; i < sheds.size() ; i ++ ) { %>
	      		<option value="<%=sheds.get(i).getId()%>"><%=sheds.get(i).getCode() %></option>
	      	<% } %>
	      </select>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-4">
	      <button type="button" id="update" class="btn btn-info btn-block">添加</button>
	    </div>
	  </div>
	</form>
    
	
    
    <script>
		function check()
		{
			var dom = document.getElementById("disease").value ;
			if(dom==null||dom=="")
			{
				alert('请输入数据') ;
				return false ;
			}
			else
			{
				return true ;
			}
		}
		
		function goBack()
		{
			window.history.back() ;
		}
	</script>

    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path %>/js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>
    
    	
	<script type="text/javascript">
		$("#update").click(function () {
			var id = '<%=user.getId() %>' ;
			var shedId = $("#shed").val();
			
			$.ajax({
				url: '<%=path%>/admin/user/shedAdd.do' ,
				method: 'POST' ,
				async: false ,
				data: {id:id,shedId:shedId} ,
				dataType: 'JSON' ,

				success: function (msg)
				{
					if ( msg == '1001' )
					{
						window.location.href='<%=path%>/admin/user/detail?id=<%=user.getId()%>';
						alert('添加成功') ;
					}
					else if ( msg == '1002' )
						alert('添加失败') ;
				}
			}) ;
		}) ;
	</script>
  </body>
</html>