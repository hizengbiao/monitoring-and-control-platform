<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	List<Base> bases = (List<Base>) request.getAttribute("bases") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>基地管理</title>

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
      <li class="active">基地管理</li>
    </ol>
    <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
            <h2 class="sub-header">基地列表</h2>
          </div>
          <div class="col-xs-4">
          </div>
            <div class="col-xs-4">
                <button class="btn btn-info pull-right" onClick="javascript:location.href='<%=path %>/admin/baseAdd'" style="margin-top: 20px;width: 100px;">添加基地</button>
            </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>基地ID</th>
                  <th>基地名称</th>
                  <th>用户数量</th>
                  <th>大棚数量</th>
                  <th>地址</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <% for ( int i = 0 ; i < bases.size(); i ++ ) { %>
                <tr>
                  <td><%=bases.get(i).getId() %></td>
                  <td><%=bases.get(i).getName() %></td>
                  <td><%=bases.get(i).getUsers().size() %></td>
                  <td><%=bases.get(i).getSheds().size() %></td>
                  <td><%=bases.get(i).getAddress() %></td>
                  <td>
                  	<button class="btn btn-default btn-xs" onclick="javascript:location.href='<%=path%>/admin/base/detail?id=<%=bases.get(i).getId() %>'">详情</button>
                    <button class="btn btn-info btn-xs" onclick="javascript:location.href='<%=path%>/admin/baseEdit?id=<%=bases.get(i).getId() %>'">编辑</button>
		            <button class="btn btn-warning btn-xs" onclick="baseDelete(<%=bases.get(i).getId()%>)">删除</button>
                  </td>
                </tr>
                <% } %>
              </tbody>
            </table>
           </div>
<!--             <div class="container"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-xs-3"></div> -->
<!--                     <div class="col-xs-6"> -->
<!--                           <nav style="text-align:center"> -->
<!--                               <ul class="pagination pagination-sm"> -->
<!--                                 <li><a href="#" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li> -->
<!--                                 <li class="disabled"><a>1&nbsp;/&nbsp;1</a></li> -->
<!--                                 <li><a href="#" aria-label="Next"><span aria-hidden="true">下一页</span></a></li> -->
    
<!--                               </ul> -->
<!--                             </nav> -->
<!--                     </div>  -->
<!--                   <div class="col-xs-3"></div> -->
<!--               </div>	row -->
<!--            </div> container -->
          
          	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path %>/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		function baseDelete(id)
		{
			$.ajax({
				url: '<%=path%>/admin/baseDelete' ,
				method: 'POST' ,
				data: {id:id} ,
				dataType: 'JSON' ,
				success: function (msg)
				{
					if ( msg == '1001' )
					{
						alert('删除成功') ;
						location.reload();
					}
					else if ( msg == '1002' )
						alert('删除失败') ;
					else if ( msg == '1003')
						alert('禁止删除！！！') ;
				}
			});
		}
	</script>
  </body>
</html>