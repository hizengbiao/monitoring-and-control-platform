<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	Shed shed = (Shed)request.getAttribute("shed") ;
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
      <li class="active">大棚详情</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">大棚编号</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=shed.getCode() %></span>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="address" class="col-sm-3 control-label">所属基地</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=shed.getBase().getName() %></span>
	    </div>
	  </div>
	</form>
	
	<hr />
	
	    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">管理用户列表</h2>
          </div>
          <div class="col-xs-4">
          </div>
            <div class="col-xs-4">
                <button class="btn btn-info pull-right" onClick="javascript:location.href='<%=path %>/admin/shed/addUser?id=<%=shed.getId() %>'" style="margin-top: 20px;width: 100px;">添加管理用户</button>
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
               <% for ( UserShed userShed : shed.getUserSheds() ) { if ( userShed.getUser().getIsAdmin() == 0 ) {%>
                <tr>
                  <td><%=userShed.getUser().getUsername() %></td>
                  <td><%=userShed.getUser().getName() %></td>
                  <td><%=userShed.getUser().getPhone() %></td>
                  <td><%=userShed.getUser().getUserSheds().size() %></td>
                  <td>
                  	<button class="btn btn-info btn-xs" onclick="deleteUser(<%=userShed.getId() %>)">删除</button>
                  </td>
                </tr>
                <%  } } %>
              </tbody>
            </table>
           </div>

	    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">种植作物列表</h2>
          </div>
          <div class="col-xs-4">
          </div>
            <div class="col-xs-4">
                <button class="btn btn-info pull-right" onClick="javascript:location.href='<%=path %>/admin/shed/addCorp?id=<%=shed.getId() %>'" style="margin-top: 20px;width: 100px;">添加作物</button>
            </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>作物ID</th>
                  <th>作物名称</th>
                  <th>作物编码</th>
                  <th>种植区域</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <% for ( ShedCorp shedCorp : shed.getShedCorps() ) { %>
                <tr>
                  <td><%=shedCorp.getCorp().getId() %></td>
                  <td><%=shedCorp.getCorp().getName() %></td>
                  <td><%=shedCorp.getCorp().getCode() %></td>
                  <td><%=shedCorp.getCorp().getShedCorps().size() %></td>
                  <td>
                  	<button class="btn btn-info btn-xs" onclick="deleteCorp(<%=shedCorp.getId() %>)">删除</button>
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
    	function deleteCorp ( id )
    	{
        	$.ajax({
            	url: '<%=path %>/admin/shed/deleteCorp' ,
            	method: 'POST' ,
            	data: {id:id} ,
            	dataType: 'JSON' ,
            	success: function (msg)
            	{
                	if ( msg == '1001' )
                    {
                        alert('删除成功') ;
                        window.location.reload( ) ;
                    }
                	else
                    	alert('删除失败') ;
                }
            }) ;
        }

    	function deleteUser ( id )
    	{
        	$.ajax({
            	url: '<%=path %>/admin/shed/deleteUser' ,
            	method: 'POST' ,
            	data: {id:id} ,
            	dataType: 'JSON' ,
            	success: function (msg)
            	{
                	if ( msg == '1001' )
                    {
                        alert('删除成功') ;
                        window.location.reload( ) ;
                    }
                	else
                    	alert('删除失败') ;
                }
            }) ;
        }
    </script>
    	
  </body>
</html>