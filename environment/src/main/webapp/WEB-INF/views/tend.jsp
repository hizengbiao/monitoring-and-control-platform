<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*"%>
<%
	String path = (String)request.getContextPath() ;

%>
<html>
	<head>
		<meta charset="utf-8">
		<title>环境量变化趋势</title>


		<!-- load the dashboard css -->
		<link href="<%=path %>/sDashboard/sDashboard.css" rel="stylesheet">

		<!-- load gitter css -->
		<link href="<%=path %>/sDashboard/example/css/gitter/css/jquery.gritter.css" rel="stylesheet"/>
		<link href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css" rel="stylesheet">
		<!-- load jquery library -->
		<script src="<%=path %>/sDashboard/example/libs/jquery/jquery-1.8.2.js" type="text/javascript"> </script>
		<!-- load jquery ui library -->
		<script src="<%=path %>/sDashboard/example/libs/jquery/jquery-ui.js" type="text/javascript"> </script>

		
		<!-- load touch punch library to enable dragging on touch based devices -->
		<script src="<%=path %>/sDashboard/example/libs/touchpunch/jquery.ui.touch-punch.js" type="text/javascript"> </script>
		<!-- load gitter notification library -->
		<script src="<%=path %>/sDashboard/example/libs/gitter/jquery.gritter.js" type="text/javascript"> </script>

		<!-- load datatables library -->
		<script src="<%=path %>/sDashboard/example/libs/datatables/jquery.dataTables.js"> </script>

		<!-- load flotr2 charting library -->
		<!--[if IE]>
		<script language="javascript" type="text/javascript" src="sDashboard/example/libs/flotr2/flotr2.ie.min.js"></script>
		<![endif]-->
		<script src="<%=path %>/sDashboard/example/libs/flotr2/flotr2.js" type="text/javascript"> </script>
 
		<!-- load dashboard library -->
		<script src="<%=path %>/sDashboard/jquery-sDashboard.js" type="text/javascript"> </script>

		<!-- sample data external script file -->
		<script src="<%=path %>/sDashboard/example/libs/exampleData.js" type="text/javascript"> </script>
		<!-- example code -->

		<link rel="stylesheet" href="<%=path %>/css/templatemo_main.css">

		<script src="<%=path %>/js/Chart.min.js"></script>
		<script src="<%=path %>/js/templatemo_script.js"></script>
		<script src="<%=path %>/js/bootstrap.min.js"></script>

		<script type="text/javascript">
			$(function() {

				//var randomString = "Lorem ipsum dolor sit amet,consectetur adipiscing elit. Aenean lacinia mollis condimentum. Proin vitae ligula quis ipsum elementum tristique. Vestibulum ut sem erat.";
				
				//**********************************************//
				//dashboard json data
				//this is the data format that the dashboard framework expects
				//**********************************************//

				var dashboardJSON = [
					{
					widgetTitle : "温度曲线",
					widgetId : "id1",
					widgetType : "chart",
					getDataBySelection : true,
					widgetContent : {
						data : myExampleData.lineChartData,
						options : myExampleData.lineChartOptions
					}

				},
					{
						widgetTitle : "湿度曲线",
						widgetId : "id2",
						widgetType : "chart",
						getDataBySelection : true,
						widgetContent : {
							data : myExampleData.lineChartData,
							options : myExampleData.lineChartOptions
						}

					},
					{
						widgetTitle : "光照曲线",
						widgetId : "id3",
						widgetType : "chart",
						getDataBySelection : true,
						widgetContent : {
							data : myExampleData.lineChartData,
							options : myExampleData.lineChartOptions
						}

					},
					{
						widgetTitle : "CO2浓度曲线",
						widgetId : "id4",
						widgetType : "chart",
						getDataBySelection : true,
						widgetContent : {
							data : myExampleData.lineChartData,
							options : myExampleData.lineChartOptions
						}

					},
					{
						widgetTitle : "室外温度曲线",
						widgetId : "id5",
						widgetType : "chart",
						getDataBySelection : true,
						widgetContent : {
							data : myExampleData.lineChartData,
							options : myExampleData.lineChartOptions
						}

					}

				];

				//basic initialization example
				$("#myDashboard").sDashboard({
					dashboardData : dashboardJSON
				});

				//table row clicked event example
				$("#myDashboard").bind("sdashboardrowclicked", function(e, data) {
					$.gritter.add({
						position: 'bottom-left',
						title : 'Table row clicked',
						time : 1000,
						text : 'A table row within a table widget has been clicked, please check the console for additional event data'
					});

					if (console) {
						console.log("table row clicked, for widget: " + data.selectedWidgetId);
					}
				});

				//plot selected event example
				$("#myDashboard").bind("sdashboardplotselected", function(e, data) {
					$.gritter.add({
						position: 'bottom-left',
						title : 'Plot selected',
						time : 1000,
						text : 'A plot has been selected within a chart widget, please check the console for additional event data'
					});
					if (console) {
						console.log("chart range selected, for widget: " + data.selectedWidgetId);
					}
				});
				//plot click event example
				$("#myDashboard").bind("sdashboardplotclicked", function(e, data) {
					$.gritter.add({
						position: 'bottom-left',
						title : 'Plot Clicked',
						time : 1000,
						text : 'A plot has been clicked within a chart widget, please check the console for additional event data'
					});
					if (console) {
						console.log("chart clicked, for widget: " + data.selectedWidgetId);
					}
				});

				//widget order changes event example
				$("#myDashboard").bind("sdashboardorderchanged", function(e, data) {
					$.gritter.add({
						position: 'bottom-left',
						title : 'Order Changed',
						time : 4000,
						text : 'The widgets order has been changed,check the console for the sorted widget definitions array'
					});
					if (console) {
						console.log("Sorted Array");
						console.log("+++++++++++++++++++++++++");
						console.log(data.sortedDefinitions);
						console.log("+++++++++++++++++++++++++");
					}
					
				});
				/*
				//example for adding an line chart widget
				$("#btnAddLineChartWidget").click(function() {
					$("#myDashboard").sDashboard("addWidget", {
						widgetTitle : "Line Chart 2",
						widgetId : "id004",
						widgetType : "chart",
						getDataBySelection : true,
						widgetContent : {
							data : myExampleData.lineChartData,
							options : myExampleData.lineChartOptions
						}

					});
				});
				*/

			});

		</script>
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
				<li ><a href="<%=path %>/realtime"><i class="fa fa-bullseye"></i>实时监测</a></li>
				<li class="active"><a href="#"><i class="fa fa-compass"></i>环境量变化趋势</a></li>
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
				<li><a href="<%=path %>/setting"><i class="fa fa-cog"></i>环境预设置</a></li>
				<li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
			</ul>
		</div><!--/.navbar-collapse -->
		<div class="templatemo-content-wrapper">
			<div class="templatemo-content" style="background:#CCC">
				<ol class="breadcrumb">
					<li><a href="<%=path %>/index">主页</a></li>
					<li class="active">环境变化</li>
				</ol>
				<div style="padding-top: 5px;">
				</div>
				<div class="container" style="background-color: whitesmoke">
				<ul id="myDashboard">

				</ul>
				</div>
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



	</body>
</html>