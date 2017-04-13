<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>掌触金控后台管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/vendor/sb-admin/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link href="/css/server.css" rel="stylesheet" type="text/css">

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
                <li class="active"><a href="#">代理商管理</a></li>
                <li><a href="#">贷款管理</a></li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
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
                                boolean levelOneHasChild = false;
                                for (User agent : levelTwoAgents) {
                                    if (agent.getSuperiorUserId() == levelOneAgent.getUserId()) {
                                        levelOneHasChild = true;
                                    }
                                }
                                if (levelOneHasChild) {
                                    %>
                                    <li>
                                        <a href="#"><i class="fa fa-user fa-fw"></i><%=levelOneAgent.getUserPhoneNumber()%><span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="javascript:void(0)" onclick="get_agent_info(<%=levelOneAgent.getUserPhoneNumber()%>)"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                            </li>
                                            <%
                                                for (User levelTwoAgent : levelTwoAgents) {
                                                    if (levelTwoAgent.getSuperiorUserId() == levelOneAgent.getUserId()) {
                                                        boolean levelTwoHasChild = false;
                                                        for (User agent : levelThreeAgents) {
                                                            if (agent.getSuperiorUserId() == levelTwoAgent.getUserId()) {
                                                                levelTwoHasChild = true;
                                                            }
                                                        }
                                                        if (levelTwoHasChild) {
                                                            %>
                                                            <li>
                                                                <a href="#"><i class="fa fa-user fa-fw"></i><%=levelTwoAgent.getUserPhoneNumber()%><span class="fa arrow"></span></a>
                                                                <ul class="nav nav-third-level">
                                                                    <li>
                                                                        <a href="javascript:void(0)" onclick="get_agent_info(<%=levelTwoAgent.getUserPhoneNumber()%>)"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                                                    </li>
                                                                    <%
                                                                        for (User levelThreeAgent : levelThreeAgents) {
                                                                            if (levelThreeAgent.getSuperiorUserId() == levelTwoAgent.getUserId()) {
                                                                                %>
                                                                                <li>
                                                                                    <a href="javascript:void(0)" onclick="get_agent_info(<%=levelThreeAgent.getUserPhoneNumber()%>)"><i class="fa fa-user fa-fw"></i><%=levelThreeAgent.getUserPhoneNumber()%></a>
                                                                                </li>
                                                                                <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </ul>
                                                            </li>
                                                            <%
                                                        }
                                                        else {
                                                            %>
                                                            <li>
                                                                <a href="javascript:void(0)" onclick="get_agent_info(<%=levelTwoAgent.getUserPhoneNumber()%>)"><i class="fa fa-user fa-fw"></i><%=levelTwoAgent.getUserPhoneNumber()%></a>
                                                            </li>
                                                            <%
                                                        }
                                                    }
                                                }
                                            %>
                                        </ul>
                                    </li>
                                    <%
                                }
                                else {
                                    %>
                                    <li>
                                        <a href="javascript:void(0)" onclick="get_agent_info(<%=levelOneAgent.getUserPhoneNumber()%>)"><i class="fa fa-user fa-fw"></i><%=levelOneAgent.getUserPhoneNumber()%></a>
                                    </li>
                                    <%
                                }
                            }
                        %>
                        <li>
                            <a href="#"><i class="fa fa-user fa-fw"></i>18511838501<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                 <li>
                                    <a href="#"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-user fa-fw"></i>18511838501</a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-user fa-fw"></i>18511838501</a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-user fa-fw"></i>18511838501<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#"><i class="fa fa-info-circle fa-fw"></i>代理商信息</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-user fa-fw"></i>18511838501</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-user fa-fw"></i>18511838501</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-user fa-fw"></i>18511838501</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">

        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/vendor/sb-admin/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        function get_agent_info(phone_number) {
            $('#page-wrapper').load("/agent_info.html?phoneNumber=" + phone_number);
        }
    </script>

</body>

</html>
