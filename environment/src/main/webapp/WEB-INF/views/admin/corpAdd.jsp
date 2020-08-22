<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	List<Shed> sheds = (List<Shed>)request.getAttribute("sheds") ;
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加作物</title>

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
      <li class="active">添加作物</li>
    </ol>
    
    <form class="form-horizontal" role="form" style="width: 1000px">
	  <div class="form-group">
	    <label for="name" class="col-sm-3 control-label">作物名称</label>
	    <div class="col-sm-4">
	      <input type="text" class="form-control" id="name" placeholder="作物名称">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="code" class="col-sm-3 control-label">作物编码</label>
	    <div class="col-sm-4">
	      <input type="text" class="form-control"  id="code" placeholder="作物编码">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-4">
	      <button type="button" id="update" class="btn btn-info btn-block">添加作物</button>
	    </div>
	  </div>
	</form>
    
<!--     <div class="container-fluid"> -->
<!--     	<form onSubmit="return check()" action="index.html" method="get"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="name">姓名</label> -->
<!--                             <input class="form-control" type="text" name="name" id="name"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--                  <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="gender">性别</label> -->
<!--                             <select id="gender" name="gender" class="form-control"> -->
<!--                                 <option>男</option> -->
<!--                                 <option>女</option> -->
<!--                             </select> -->

<!--                         </div> -->
<!--                 </div> -->
<!--             </div> row -->
            
<!--             <div class="row"> -->
<!--                 <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="age">年龄</label> -->
<!--                             <input class="form-control" type="number" name="age" id="age"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--                  <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="phone">联系方式</label> -->
<!--                             <input class="form-control" type="number" name="phone" id="phone"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--             </div> row -->
            
<!--             <div class="row"> -->
<!--                 <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="username">用户名</label> -->
<!--                             <input class="form-control" type="text" name="username" id="username"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--                  <div class="col-xs-10 col-sm-3"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="password">密码</label> -->
<!--                             <input class="form-control" type="password" name="password" id="password"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--             </div> row -->
            
<!--             <div class="row"> -->
<!--                 <div class="col-xs-10 col-sm-6"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="pagesize">页记录数</label> -->
<!--                             <input class="form-control" type="number" name="pagesize" id="pagesize" placeholder="可不填"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--             </div> row -->
            
<!--             <div class="row"> -->
<!--                 <div class="col-xs-10 col-sm-6"> -->
                    
<!--                         <div class="form-group"> -->
<!--                             <label for="address">家庭住址</label> -->
<!--                             <input class="form-control" type="text" name="address" id="address" placeholder="可不填"> -->
<!--                         </div> -->
<!--                 </div> -->
<!--             </div> row -->
<!--             <br/> -->
<!--             <div class="row"> -->
<!--             	<div class="col-xs-10 col-sm-6"> -->
<!--                         <div class="form-group"> -->
<!--                             <div class="col-xs-6" style="padding-left:0px; padding-right:0px"> -->
<!--                                 <button type="submit" class="btn btn-default btn-block">提交</button> -->
<!--                             </div> -->
<!--                             <div class="col-xs-6" style="padding-left:0px; padding-right:0px"> -->
<!--                                 <button type="reset" class="btn btn-default btn-block" onClick="goBack();">返回</button> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </form>  -->
<!--     </div> container -->
	
    
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
			var name = $("#name").val();
			var code = $("#code").val();
			
			$.ajax({
				url: '<%=path%>/admin/corpAdd.do' ,
				method: 'POST' ,
				async: false ,
				data: {name:name, code:code} ,
				dataType: 'JSON' ,

				success: function (msg)
				{
					if ( msg == '1001' )
					{
						window.location.href='<%=path%>/admin/corp';
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