<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>掌触金控后台管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="/AppServer/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/AppServer/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/AppServer/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/AppServer/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/AppServer/vendor/sb-admin/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/AppServer/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link href="/AppServer/vendor/css/server.css" rel="stylesheet" type="text/css">

    <link href="https://cdn.bootcss.com/toastr.js/latest/toastr.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    掌触金控
                </a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-nav">
                <li class="active"><a href="main.html">代理商管理</a></li>
                <li><a href="credit_card.html">信用卡管理</a></li>
                <li><a href="loan.html">贷款管理</a></li>
                <li><a href="feedback.html">意见反馈</a></li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <%--<li class="sidebar-search">--%>
                            <%--<div class="input-group custom-search-form">--%>
                                <%--<input type="text" class="form-control" placeholder="Search...">--%>
                                <%--<span class="input-group-btn">--%>
                                <%--<button class="btn btn-default" type="button">--%>
                                    <%--<i class="fa fa-search"></i>--%>
                                <%--</button>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                            <%--<!-- /input-group -->--%>
                        <%--</li>--%>
                        <%@ page import="com.huachuang.server.entity.User" %>
                        <%@ page import="java.util.List" %>
                        <%@ page import="java.util.ArrayList" %>
                        <%
                            List<User> agents = (List<User>) request.getAttribute("agents");
                            List<User> levelOneAgents = new ArrayList<>();
                            List<User> levelTwoAgents = new ArrayList<>();
                            List<User> levelThreeAgents = new ArrayList<>();
                            for (User user : agents) {
                                if (user.getUserType() == 1) {
                                    levelOneAgents.add(user);
                                }
                                else if (user.getUserType() == 2) {
                                    levelTwoAgents.add(user);
                                }
                                else {
                                    levelThreeAgents.add(user);
                                }
                            }
                            for (User levelOneAgent : levelOneAgents) {
                                %>
                                <li>
                                    <a href="#"><i class="fa fa-user fa-fw"></i><%=levelOneAgent.getUserPhoneNumber()%><span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="javascript:void(0)" onclick="get_agent_info(this, <%=levelOneAgent.getUserPhoneNumber()%>)"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                        </li>
                                        <%
                                            for (User levelTwoAgent : levelTwoAgents) {
                                                if (levelTwoAgent.getSuperiorUserId() == levelOneAgent.getUserId()) {
                                                    %>
                                                    <li>
                                                        <a href="#"><i class="fa fa-user fa-fw"></i><%=levelTwoAgent.getUserPhoneNumber()%><span class="fa arrow"></span></a>
                                                        <ul class="nav nav-third-level">
                                                            <li>
                                                                <a href="javascript:void(0)" onclick="get_agent_info(this, <%=levelTwoAgent.getUserPhoneNumber()%>)"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                                            </li>
                                                            <%
                                                                for (User levelThreeAgent : levelThreeAgents) {
                                                                    if (levelThreeAgent.getSuperiorUserId() == levelTwoAgent.getUserId()) {
                                                                        %>
                                                                        <li>
                                                                            <a href="javascript:void(0)" onclick="get_agent_info(this, <%=levelThreeAgent.getUserPhoneNumber()%>)"><i class="fa fa-user fa-fw"></i><%=levelThreeAgent.getUserPhoneNumber()%></a>
                                                                        </li>
                                                                        <%
                                                                    }
                                                                }
                                                            %>
                                                        </ul>
                                                    </li>
                                                    <%
                                                }
                                            }
                                            for (User levelThreeAgent : levelThreeAgents) {
                                                if (levelThreeAgent.getSuperiorUserId() == levelOneAgent.getUserId()) {
                                                    %>
                                                        <li>
                                                            <a href="javascript:void(0)" onclick="get_agent_info(this, <%=levelThreeAgent.getUserPhoneNumber()%>)"><i class="fa fa-user fa-fw"></i><%=levelThreeAgent.getUserPhoneNumber()%></a>
                                                        </li>
                                                    <%
                                                }
                                            }
                                        %>
                                    </ul>
                                </li>
                                <%
                            }
                        %>
                        <li>
                            <a href="javascript:void(0)" onclick="add_new_agent(0,1)"><i class="fa fa-plus fa-fw"></i>添加一级代理商</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <%--<div class="row">--%>
                <%--<div class="col-lg-12">--%>
                    <%--<h1 class="page-header">控制台</h1>--%>
                <%--</div>--%>
                <%--<!-- /.col-lg-12 -->--%>
            <%--</div>--%>
            <%--<!-- /.row -->--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-3 col-md-6">--%>
                    <%--<div class="panel panel-primary">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-xs-3">--%>
                                    <%--<i class="fa fa-comments fa-5x"></i>--%>
                                <%--</div>--%>
                                <%--<div class="col-xs-9 text-right">--%>
                                    <%--<div class="huge">26</div>--%>
                                    <%--<div>新消息！</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<a href="#">--%>
                            <%--<div class="panel-footer">--%>
                                <%--<span class="pull-left">查看详细</span>--%>
                                <%--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-lg-3 col-md-6">--%>
                    <%--<div class="panel panel-green">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-xs-3">--%>
                                    <%--<i class="fa fa-users fa-5x"></i>--%>
                                <%--</div>--%>
                                <%--<div class="col-xs-9 text-right">--%>
                                    <%--<div class="huge">12</div>--%>
                                    <%--<div>新用户！</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<a href="#">--%>
                            <%--<div class="panel-footer">--%>
                                <%--<span class="pull-left">查看详细</span>--%>
                                <%--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-lg-3 col-md-6">--%>
                    <%--<div class="panel panel-yellow">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-xs-3">--%>
                                    <%--<i class="fa fa-shopping-cart fa-5x"></i>--%>
                                <%--</div>--%>
                                <%--<div class="col-xs-9 text-right">--%>
                                    <%--<div class="huge">124</div>--%>
                                    <%--<div>新订单！</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<a href="#">--%>
                            <%--<div class="panel-footer">--%>
                                <%--<span class="pull-left">查看详细</span>--%>
                                <%--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-lg-3 col-md-6">--%>
                    <%--<div class="panel panel-red">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-xs-3">--%>
                                    <%--<i class="fa fa-support fa-5x"></i>--%>
                                <%--</div>--%>
                                <%--<div class="col-xs-9 text-right">--%>
                                    <%--<div class="huge">13</div>--%>
                                    <%--<div>系统事件！</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<a href="#">--%>
                            <%--<div class="panel-footer">--%>
                                <%--<span class="pull-left">查看详细</span>--%>
                                <%--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<!-- /.row -->--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-8">--%>
                    <%--<div class="panel panel-default">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<i class="fa fa-bar-chart-o fa-fw"></i> 终端在线用户数--%>
                        <%--</div>--%>
                        <%--<!-- /.panel-heading -->--%>
                        <%--<div class="panel-body">--%>
                            <%--<div id="morris-area-chart"></div>--%>
                        <%--</div>--%>
                        <%--<!-- /.panel-body -->--%>
                    <%--</div>--%>
                    <%--<!-- /.panel -->--%>
                <%--</div>--%>
                <%--<!-- /.col-lg-8 -->--%>
                <%--<div class="col-lg-4">--%>
                    <%--<div class="panel panel-default">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<i class="fa fa-bell fa-fw"></i> 系统通知--%>
                        <%--</div>--%>
                        <%--<!-- /.panel-heading -->--%>
                        <%--<div class="panel-body">--%>
                            <%--<div class="list-group">--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-comment fa-fw"></i> 新消息--%>
                                    <%--<span class="pull-right text-muted small"><em>4 minutes ago</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-envelope fa-fw"></i> 收到新的用户反馈--%>
                                    <%--<span class="pull-right text-muted small"><em>27 minutes ago</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-user fa-fw"></i> 新用户注册--%>
                                    <%--<span class="pull-right text-muted small"><em>43 minutes ago</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-upload fa-fw"></i> 系统重启--%>
                                    <%--<span class="pull-right text-muted small"><em>11:32 AM</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-bolt fa-fw"></i> 系统崩溃--%>
                                    <%--<span class="pull-right text-muted small"><em>11:13 AM</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-shopping-cart fa-fw"></i> 新订单产生--%>
                                    <%--<span class="pull-right text-muted small"><em>9:49 AM</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                                <%--<a href="#" class="list-group-item">--%>
                                    <%--<i class="fa fa-money fa-fw"></i> 用户支付请求--%>
                                    <%--<span class="pull-right text-muted small"><em>Yesterday</em>--%>
                                    <%--</span>--%>
                                <%--</a>--%>
                            <%--</div>--%>
                            <%--<!-- /.list-group -->--%>
                            <%--<a href="#" class="btn btn-default btn-block">查看所有消息</a>--%>
                        <%--</div>--%>
                        <%--<!-- /.panel-body -->--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<!-- /.col-lg-4 -->--%>
            <%--</div>--%>
            <%--<!-- /.row -->--%>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- Modal -->
    <div class="modal fade" id="newAgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">添加一级代理商</h4>
                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group" id="inputPhoneNumberGroup">
                            <label>手机号</label>
                            <input class="form-control" id="inputPhoneNumber">
                        </div>
                        <div class="form-group" id="inputPasswordGroup">
                            <label>密码</label>
                            <input type="password" class="form-control" id="inputPassword">
                        </div>
                        <div class="form-group" id="inputConfirmPasswordGroup">
                            <label>确认密码</label>
                            <input type="password" class="form-control" id="inputConfirmPassword">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="add-agent">提交</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

    <!-- jQuery -->
    <script src="/AppServer/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/AppServer/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/AppServer/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="/AppServer/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/AppServer/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/AppServer/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="/AppServer/vendor/raphael/raphael.min.js"></script>
    <script src="/AppServer/vendor/morrisjs/morris.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/AppServer/vendor/sb-admin/js/sb-admin-2.js"></script>

    <script src="https://cdn.bootcss.com/toastr.js/latest/toastr.min.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        var currentAgentPhoneNumber;
        var currentType;
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "positionClass": "toast-bottom-center",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "300",
            "timeOut": "1500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        function get_agent_info(obj, phone_number) {
            $('#page-wrapper').load("/AppServer/agent_info.html?phoneNumber=" + phone_number);
        }
        function add_new_agent(phone_number, type) {
            currentAgentPhoneNumber = phone_number;
            currentType = type;
            if (type == 1) {
                $('#newAgentModal .modal-title').text('添加一级代理商');
            }
            else if (type == 2) {
                $('#newAgentModal .modal-title').text('添加二级代理商');
            }
            else {
                $('#newAgentModal .modal-title').text('添加三级代理商');
            }
            $('#newAgentModal').modal(0);
        }
        $('#add-agent').click(function () {
            if($('#inputPhoneNumber').val().length != 11){
                toastr.error('请输入正确的手机号');
                return;
            }

            if($('#inputPassword').val().length<6){
                toastr.error('密码最少包含6个字符');
                return;
            }

            if($('#inputPassword').val()!=$('#inputConfirmPassword').val()){
                toastr.error('两次输入密码不一致');
                return;
            }

            $.ajax({
                type: "POST",
                url: "/AppServer/UserManager/AddAgent",
                data: {superiorPhoneNumber:currentAgentPhoneNumber,phoneNumber:$("#inputPhoneNumber").val(),password:$("#inputPassword").val(),type:currentType},
                dataType: "json",
                success: function (data) {
                    if (data.Status == 'true') {
                        window.location.reload();
                    }
                    else {
                        toastr.info(data.Info);
                    }
                }
            });
        });
    </script>

</body>

</html>
