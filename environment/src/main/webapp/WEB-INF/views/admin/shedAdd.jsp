<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	List<Base> bases = (List<Base>)request.getAttribute("bases") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加大棚</title>

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
      <li class="active">添加大棚</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="code" class="col-sm-3 control-label">大棚编码</label>
	    <div class="col-sm-4">
	      <input type="text" class="form-control" id="code" placeholder="大棚编码">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="base" class="col-sm-3 control-label">所属基地</label>
	    <div class="col-sm-4">
	      <select id="base" style="height:34px;width:100%; text-align:center">
	      	<option value="0">--请选择基地--</option>
	      <% for ( int i = 0 ; i < bases.size() ; i ++ ) { %>
	      	<option value="<%=bases.get(i).getId() %>"><%=bases.get(i).getName() %></option>
	      <% } %>
	      </select>
	    </div>
	  </div>

	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-4">
	      <button type="button" id="update" class="btn btn-info btn-block">添加大棚</button>
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
			var code = $("#code").val();
			var base = $("#base").val();
			
			$.ajax({
				url: '<%=path%>/admin/shedAdd.do' ,
				method: 'POST' ,
				async: false ,
				data: {baseId:base, code:code} ,
				dataType: 'JSON' ,

				success: function (msg)
				{
					if ( msg == '1001' )
					{
						window.location.href='<%=path%>/admin/shed' ;
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