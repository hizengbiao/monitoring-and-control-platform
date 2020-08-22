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
    
          <style type="text/css">
		 th{text-align:center; width:10%}
		 td{text-align:center}
	</style>
  </head>

  
  <body>
  
    <ol class="breadcrumb">
      <li><a href="<%=path %>/admin/index">首页</a></li>
      <li class="active">基地详情</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">基地名称</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=base.getName() %></span>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="address" class="col-sm-3 control-label">基地地址</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=base.getAddress() %></span>
	    </div>
	  </div>
	</form>
	
	<hr />
	
	    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">用户列表</h2>
          </div>
          <div class="col-xs-4">
          </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>用户名</th>
                  <th>姓名</th>
                  <th>联系方式</th>
                  <th>管辖大棚</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <% for ( User user : base.getUsers() ) { if ( user.getIsAdmin() == 0 ) {%>
                <tr>
                  <td><%=user.getUsername() %></td>
                  <td><%=user.getName() %></td>
                  <td><%=user.getPhone() %></td>
                  <td><%=user.getUserSheds().size() %></td>
                  <td>
                  	<button class="btn btn-info btn-xs">详情</button>
                  </td>
                </tr>
                <%  } } %>
              </tbody>
            </table>
           </div>
           
              <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">大棚列表</h2>
          </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>大棚ID</th>
                  <th>大棚编号</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <% for ( Shed shed : base.getSheds() ) { %>
                <tr>
                  <td><%=shed.getId() %></td>
                  <td><%=shed.getCode() %></td>
                  <td>
                  	<button class="btn btn-info btn-xs">详情</button>
                  </td>
                </tr>
                <% } %>
              </tbody>
            </table>
           </div>
	

    
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