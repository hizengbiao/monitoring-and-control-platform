<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*"%>
<%
	String path = (String)request.getContextPath() ;
	Map map = (Map)request.getAttribute("map") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>实时监测</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="<%=path %>/css/templatemo_main.css">
<!-- -->
<link rel="stylesheet" href="<%=path %>/css/donuts.css">
<style type="text/css">
	.col-xs-4{height:150px}
</style>

</head>
<body>
	<div class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="logo">
				<h1>欢迎进入农场环境监控系统</h1>
			</div>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
	</div>
	<div class="template-page-wrapper">
		<div class="navbar-collapse collapse templatemo-sidebar">
			<ul class="templatemo-sidebar-menu">

				<li><a href="<%=path %>/index"><i class="fa fa-home"></i>主页</a></li>
				<li class="active"><a href="#"><i class="fa fa-bullseye"></i>
						实时监测</a></li>
				<li><a href="<%=path %>/setting"><i class="fa fa-cog"></i>环境预设置</a></li>
				<li><a href="javascript:;" data-toggle="modal"
					data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
			</ul>
		</div>
		<!--/.navbar-collapse -->

		<div class="templatemo-content-wrapper">
			<div class="templatemo-content" style="background: #CCC">
				<ol class="breadcrumb">
					<li><a href="<%=path %>/index">主页</a></li>
					<li class="active">实时监测</li>
				</ol>

				<div class="container" style="background-color: whitesmoke;width:1074px">
					<div class="row">
						<div class="col-xs-4">
							<p style="height:20px; text-align:center; font-size:14px; line-height:20px">温度</p>
							<p id="temp" style="height:80px; text-align:center; font-size:40px; line-height:80px"><%=map.get("temp") %>°C</p>
							<div class="btn-group btn-group-sm center-block" style="height: 50px;padding-left:60px">
							  <button type="button" name="temp" class="btn btn-default" value="-2">快减</button>
							  <button type="button" name="temp" class="btn btn-default" value="-1">减</button>
							  <button type="button" name="temp" class="btn btn-default" value="0">正常</button>
							  <button type="button" name="temp" class="btn btn-default" value="1">增</button>
							  <button type="button" name="temp" class="btn btn-default" value="2">快增</button>
							</div>
						</div>
												<div class="col-xs-4">
							<p style="height:20px; text-align:center; font-size:14px; line-height:20px">光照</p>
							<p id="light" style="height:80px; text-align:center; font-size:40px; line-height:80px"><%=map.get("light") %>lux</p>
							<div class="btn-group btn-group-sm center-block" style="height: 50px;padding-left:60px">
							  <button type="button" name="light" class="btn btn-default" value="-2">快减</button>
							  <button type="button" name="light" class="btn btn-default" value="-1">减</button>
							  <button type="button" name="light" class="btn btn-default" value="0">正常</button>
							  <button type="button" name="light" class="btn btn-default" value="1">增</button>
							  <button type="button" name="light" class="btn btn-default" value="2">快增</button>
							</div>
						</div>
												<div class="col-xs-4">
							<p style="height:20px; text-align:center; font-size:14px; line-height:20px">湿度</p>
							<p id="humi" style="height:80px; text-align:center; font-size:40px; line-height:80px"><%=map.get("humi")%>%</p>
							<div class="btn-group btn-group-sm center-block" style="height: 50px;padding-left:60px">
							  <button type="button" name="humi" class="btn btn-default" value="-2">快减</button>
							  <button type="button" name="humi" class="btn btn-default" value="-1">减</button>
							  <button type="button" name="humi" class="btn btn-default" value="0">正常</button>
							  <button type="button" name="humi" class="btn btn-default" value="1">增</button>
							  <button type="button" name="humi" class="btn btn-default" value="2">快增</button>
							</div>
						</div>
						<hr>
						<div class="col-xs-2">
						</div>
												<div class="col-xs-4">
							<p style="height:20px; text-align:center; font-size:14px; line-height:20px">CO2浓度</p>
							<p id="gas" style="height:80px; text-align:center; font-size:40px; line-height:80px"><%=map.get("gas")%>%</p>
							<div class="btn-group btn-group-sm center-block" style="height: 50px;padding-left:60px">
							  <button type="button" name="gas" class="btn btn-default" value="-2">快减</button>
							  <button type="button" name="gas" class="btn btn-default" value="-1">减</button>
							  <button type="button" name="gas" class="btn btn-default" value="0">正常</button>
							  <button type="button" name="gas" class="btn btn-default" value="1">增</button>
							  <button type="button" name="gas" class="btn btn-default" value="2">快增</button>
							</div>
						</div>
												<div class="col-xs-4">
							<p style="height:20px; text-align:center; font-size:14px; line-height:20px">室外温度</p>
							<p style="height:80px; text-align:center; font-size:40px; line-height:80px"><%=map.get("outtemp")%>°C</p>
							<div class="btn-group btn-group-sm center-block" style="height: 50px;padding-left:60px">
							</div>
						</div>
					</div>
				</div>


			</div>

			<div class="templatemo-panels">

				<div class="row"></div>
			</div>
		</div>
	</div>




	<!-- Modal -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
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
			<p>
				Copyright &copy; 2017 <a href="http://www.ujs.edu.cn/"
					target="_blank">江苏大学计算机学院 </a> 版权所有
			</p>
		</div>
	</footer>
	</div>

	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<script src="<%=path %>/js/Chart.min.js"></script>
	<script src="<%=path %>/js/templatemo_script.js"></script>

	<script src="<%=path %>/js/donuts.js"></script>
	<!--
  <script src="http://www.ichartjs.com/js/ichart-1.0.min.js"></script>
  <script src="http://www.ichartjs.com/js/ichart.gauge2d.js"></script>
  -->
	<script type="text/javascript">
    // Line chart
    var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
    var lineChartData = {
      labels : ["January","February","March","April","May","June","July"],
      datasets : [
      {
        label: "My First dataset",
        fillColor : "rgba(220,220,220,0.2)",
        strokeColor : "rgba(220,220,220,1)",
        pointColor : "rgba(220,220,220,1)",
        pointStrokeColor : "#fff",
        pointHighlightFill : "#fff",
        pointHighlightStroke : "rgba(220,220,220,1)",
        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
      },
      {
        label: "My Second dataset",
        fillColor : "rgba(151,187,205,0.2)",
        strokeColor : "rgba(151,187,205,1)",
        pointColor : "rgba(151,187,205,1)",
        pointStrokeColor : "#fff",
        pointHighlightFill : "#fff",
        pointHighlightStroke : "rgba(151,187,205,1)",
        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
      }
      ]

    }

    window.onload = function(){
      var ctx_line = document.getElementById("templatemo-line-chart").getContext("2d");
      window.myLine = new Chart(ctx_line).Line(lineChartData, {
        responsive: true
      });
    };

    $('#myTab a').click(function (e) {
      e.preventDefault();
      $(this).tab('show');
    });

    $('#loading-example-btn').click(function () {
      var btn = $(this);
      btn.button('loading');
      // $.ajax(...).always(function () {
      //   btn.button('reset');
      // });
  });
	
    $("button[type='button']").click(function(e){
       	var button = $(e.target) ;
       	var name = button.attr("name") ;
       	$("button[name='" + name + "']").removeClass('btn-info') ;
       	$("button[name='" + name + "']").addClass('btn-default') ;
        button.removeClass('btn-default') ;
        button.addClass('btn-info') ;
    }) ;

    $(document).ready(function(e){
        	$("button[value='0']").removeClass('btn-default') ;
        	$("button[value='0']").addClass('btn-info') ;

        	setInterval('run()',3000 ) ;
    })
    
    function run ( )
    {
        var templevel = $("button[name='temp'][class='btn btn-info']").attr('value') ;
        var lightlevel = $("button[name='light'][class='btn btn-info']").attr('value') ;
        var humilevel = $("button[name='humi'][class='btn btn-info']").attr('value') ;
        var gaslevel = $("button[name='gas'][class='btn btn-info']").attr('value') ;

        $.ajax({
            url: '<%=path%>/change' ,
            method: 'POST' ,
            data: {temp:templevel, light:lightlevel, humi:humilevel, gas:gaslevel} ,
            dataType: 'JSON' ,
            success: function(data)
            {
                var temp = data.temp ;
                var light = data.light ;
                var humi = data.humi ;
                var gas = data.gas ;

                $("#temp").text(temp+'°C') ;
                $("#light").text(light+'lux') ;
                $("#humi").text(humi+'%') ;
                $("#gas").text(gas+'%') ;
            }
        });
        
    }
  </script>


</body>
</html>