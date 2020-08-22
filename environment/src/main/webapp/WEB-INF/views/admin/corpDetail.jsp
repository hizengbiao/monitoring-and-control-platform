<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	Corp corp = (Corp) request.getAttribute("corp") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>作物详情</title>

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
      <li class="active">作物详情</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">作物名称</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=corp.getName() %></span>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">作物编码</label>
	    <div class="col-sm-4" style="padding-top: 6px">
	      <span style="text-align:center"><%=corp.getCode() %></span>
	    </div>
	  </div>
	  
	</form>
	
	<hr />
	
	    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">种植区域列表</h2>
          </div>
          <div class="col-xs-4">
          </div>
            <div class="col-xs-4">
                <button class="btn btn-info pull-right" onClick="javascript:location.href='<%=path %>/admin/corp/shedAdd?id=<%=corp.getId() %>'" style="margin-top: 20px;width: 100px;">添加种植区域</button>
            </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>大棚编码</th>
                  <th>所属基地</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <% for ( ShedCorp shedCorp : corp.getShedCorps() ) { %>
                <tr>
                  <td><%=shedCorp.getShed().getCode() %></td>
                  <td><%=shedCorp.getShed().getBase().getName() %></td>
                  <td>
                  	<button class="btn btn-info btn-xs" onclick="deleteCorp(<%=shedCorp.getId() %>)">删除</button>
                  </td>
                </tr>
                <%  }  %>
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
            	url: '<%=path %>/admin/corp/shedDelete' ,
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