<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.troyforever.env.bean.*, java.util.*" %>
<%
	String path = (String)request.getContextPath() ;
	List<UserShed> userSheds = (List<UserShed>)request.getAttribute("userSheds") ;
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
  <title>主页</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
  <link rel="stylesheet" href="<%=path %>/css/templatemo_main.css">
</head>
<body>
  <div class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <div class="logo"><h1>欢迎进入智能控制系统</h1></div>
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
          
          <li class="active"><a href="<%=path%>/index"><i class="fa fa-home"></i>主页</a></li>
          <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
      </div><!--/.navbar-collapse -->

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content" style="background:#CCC">
          <ol class="breadcrumb">
            <li><a href="<%=path %>/index">主页</a></li>
          </ol>

          <div>
          
          <h1>欢迎智能控制系统！使用愉快！</h1>
          
          <hr/>

              <a href="javascript:;" data-toggle="modal" data-target="#confirmModal1">
                  <button type="button" class="btn btn-primary" id="shedopt">点击选择要管理的大棚</button>
              </a>



           <!--
          <h4>请选择大棚：</h4>
          <div>  
          <form>
			<label class="checkbox-inline">
				<input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="option1" checked> 大棚1</label>
			<label class="checkbox-inline">
				<input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="option2"> 大棚2</label>
            <label class="checkbox-inline">
				<input type="radio" name="optionsRadiosinline" id="optionsRadios3"  value="option2"> 大棚3</label>
                
			<br><br>
            
            <button type="button" class="btn btn-primary" id="myButton4" data-complete-text="Loading finished">确定
            </button>
          </form>
          </div>
          -->
          
          
          
          
          <!--  写点使用介绍  -->
          <p></p>
          
          </div>

          <div class="templatemo-panels">
        
            <div class="row">
           
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

        <!-- Modal -->
        <div class="modal fade" id="confirmModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel1">请选择大棚</h4>
                    </div>
                    <div class="modal-body">
                    <label class="checkbox-inline">
                    <% if ( userSheds != null ) { for ( UserShed userShed : userSheds ) { %>
                         <input type="radio" name="optionsRadiosinline" id="<%=userShed.getShed().getId() %>" value="<%=userShed.getShed().getId() %>"><%=userShed.getShed().getCode() %>
                     <% } } else { %>
                     	 <h1>无大棚，请联系管理员</h1>
                     <% } %>
                     </label>
                            <br><br>
                    </div>
                    <div class="modal-footer">
                        <a onclick="go();" class="btn btn-primary">确定</a>
                    </div>
                    </form>
                </div>
            </div>
        </div>

      <footer class="templatemo-footer">
        <div class="templatemo-copyright">
          <p>Copyright &copy; 2017 <a href="http://www.ujs.edu.cn/" target="_blank">江苏大学计算机学院 </a>  版权所有</p>
        </div>
      </footer>
    </div>

    <script src="<%=path %>/js/jquery.min.js"></script>
    <script src="<%=path %>/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/Chart.min.js"></script>
    <script src="<%=path %>/js/templatemo_script.js"></script>
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

    function go()
    {
        var shedId = $("input[name='optionsRadiosinline']:checked").val();
       	window.location.href= "<%=path%>/realtime?shedId=" + shedId ;
    }
  </script>

 
</body>
</html>