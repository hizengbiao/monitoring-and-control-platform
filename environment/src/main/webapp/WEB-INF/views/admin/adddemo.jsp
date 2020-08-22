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
    <title>demo</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  
  <body>
  
    <ol class="breadcrumb">
      <li><a href="index.html">首页</a></li>
      <li><a href="disease.html">管理</a></li>
      <li class="active">新建</li>
    </ol>
    
    <div class="container-fluid">
    	<div class="row">
            <div class="col-xs-6">
            	<form onSubmit="return check()" action="index.html" method="get">
                	<div class="form-group">
                    	<label for="disease">病症名称</label>
                    	<input class="form-control" type="text" name="disease" id="disease">
                    </div>
                    <div class="form-group">
                    	<div class="col-xs-6" style="padding-left:0px; padding-right:0px">
                    		<button type="submit" class="btn btn-default btn-block">提&nbsp;&nbsp;&nbsp;&nbsp;交</button>
                        </div>
                        <div class="col-xs-6" style="padding-left:0px; padding-right:0px">
                        	<button type="reset" class="btn btn-default btn-block" onClick="goBack();">返&nbsp;&nbsp;&nbsp;&nbsp;回</button>
                        </div>
                    </div>
                </form> 
            </div>
            
        </div> <!--row-->
    </div> <!--container-->
	
    
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
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>