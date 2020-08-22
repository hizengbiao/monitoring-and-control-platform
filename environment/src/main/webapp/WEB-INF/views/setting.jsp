<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*"%>
<%
	String path = (String)request.getContextPath() ;
	Map map = (Map)request.getAttribute("map") ;
	Shed shed = (Shed)request.getSession().getAttribute("shed") ;

%>
<html>
<head>
  <meta charset="utf-8">
  <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
  <title>环境量预设置</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
  <link rel="stylesheet" href="<%=path %>/css/templatemo_main.css">
  
</head>
<body>
  <div class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <div class="logo"><h1>欢迎进入农场环境监控系统</h1></div>
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">导航</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button> 
      </div>   
    </div>
    <div class="template-page-wrapper">
      <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
          
          <li><a href="<%=path %>/index"><i class="fa fa-home"></i>主页</a></li>
          <li ><a href="<%=path %>/realtime"><i class="fa fa-bullseye"></i>  实时监测</a></li>
          <!--
          <li class="sub open">
            <a href="javascript:;">
              <i class="fa fa-database"></i> 环境趋势 <div class="pull-right"><span class="caret"></span></div>
            </a>
            
            <ul class="templatemo-submenu">
              <li><a href="temperature.html">温度</a></li>
              <li><a href="humidity.html">湿度</a></li>             
              <li><a href="sunshine.html">光照</a></li>
              <li><a href="carbon.html">CO2浓度</a></li>
              <li><a href="tempoutside.html">室外温度</a></li>
            </ul>
            
          </li>
          -->
          <li class="active"><a href="#"><i class="fa fa-cog"></i>环境预设置</a></li>
          <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
      </div><!--/.navbar-collapse -->

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li><a href="<%=path %>/index">主页</a></li>
            <li class="active">环境预设置</li>
          </ol>
          
          
          <form id="form">
          
          <div>
          	<label>温度预设值：</label>
          	<input type="input" min="0" id="temp" max="100" value = "<%=map.get("temp") %>" style="width:100px"><br><br>
			<label>湿度预设值：</label>
          	<input type="input" min="0" id="light" max="100" value = "<%=map.get("light") %>" style="width:100px"><br><br>
            <label>光照预设值：</label>
          	<input type="input" min="0" id="humi" max="100" value = "<%=map.get("humi") %>" style="width:100px"><br><br>
            <label>CO2浓度预设值：</label>
          	<input type="input" min="0" id="gas" max="100" value = "<%=map.get("gas") %>" style="width:100px"><br><br>
            
            
          </div>
          
              
          <div class="row templatemo-form-buttons">
                <div class="col-md-12">
                  <input onClick="check();" type="button" class="btn btn-primary" style="width:250px"  value="提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交">  
                </div>
              </div>
              <br><br>
            </form>
          </div>
        </div>
      </div>


    <!-- Modal -->
    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">您确定要退出吗？</h4>
          </div>
          <div class="modal-footer">
            <a href="<%=path %>/logout" class="btn btn-primary">是</a>
            <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
          </div>
        </div>
      </div>
    </div>

    <footer class="templatemo-footer">
      <div class="templatemo-copyright">
        <p>Copyright &copy; 2017 <a href="http://www.ujs.edu.cn/" target="_blank">江苏大学计算机学院 </a>  版权所有</p>
      </div>
    </footer>
  </div>
</div>
  <script src="<%=path %>/js/jquery.min.js"></script>
  <script src="<%=path %>/js/bootstrap.min.js"></script>
  <script src="<%=path %>/js/templatemo_script.js"></script>

  <script type="text/javascript">
  	function check( )
  	{
  	  	var temp = $("#temp").val();
	  	var light = $("#light").val();
	  	var humi = $("#humi").val();
	  	var gas = $("#gas").val();
  	  	$.ajax({
  	  	  	url: "<%=path%>/setting.do" ,
  	  	  	method: 'POST' ,
  	  	  	data: {temp:temp, light:light, humi:humi, gas:gas} ,
  	  	  	dataType: 'TEXT' ,
  	  	  	success: function(data)
  	  	  	{
  	  	  	  	if ( data == '1001' )
  	  	  	  	{  	
  	  	  	  	  	alert('预设成功') ;
  	  	  	  	  	window.location.href='<%=path%>/realtime?shedId=<%=shed.getId()%>' ;
  	  	  	  	}
  	  	  	}
  	  	});
  	 }
  </script>




</body>
</html>