<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	Base base = (Base) request.getAttribute("base") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>基地详情</title>

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
      <li class="active">基地详情</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">基地名称</label>
	    <div class="col-sm-4">
	      <input type="text" value="<%=base.getName() %>" class="form-control" id="name" placeholder="基地名称">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="address" class="col-sm-3 control-label">基地地址</label>
	    <div class="col-sm-4">
	      <input type="text" value="<%=base.getAddress() %>" class="form-control"  id="address" placeholder="基地地址">
	    </div>
	  </div>

	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-4">
	      <button type="button" id="add" class="btn btn-info btn-block">更新</button>
	    </div>
	  </div>
	</form>
	

    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path %>/js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>
    
    	
	<script type="text/javascript">
		$("#add").click(function () {
			var name = $("#name").val();
			var address = $("#address").val();
			
			$.ajax({
				url: '<%=path%>/admin/baseEdit.do?id=<%=base.getId()%>' ,
				method: 'POST' ,
				async: false ,
				data: {name:name, address:address} ,
				dataType: 'JSON' ,

				success: function (msg)
				{
					if ( msg == '1001' )
					{
						alert('更新成功') ;
						window.location.href='<%=path %>/admin/base' ;
					}
					else if ( msg == '1002' )
						alert('更新失败') ;
				}
			}) ;
		}) ;
	</script>
  </body>
</html>