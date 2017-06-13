<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <li><a href="main.html">代理商管理</a></li>
                <li><a href="credit_card.html">信用卡管理</a></li>
                <li><a href="loan.html">贷款管理</a></li>
                <li class="active"><a href="into.html">用户进件</a></li>
                <li><a href="feedback.html">意见反馈</a></li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i><i class="fa fa-caret-down"></i>
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
                        <li>
                            <a href="javascript:void(0)" class="active" onclick="get_into_records(this, -1)"> 全部用户</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="get_into_records(this, 0)"> 未处理用户</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="get_into_records(this, 1)"> 异常用户</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="get_into_records(this, 2)"> 已进件用户</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">全部用户</h1>
                </div>
            </div>
            <div id="records-container">
            </div>
        </div>

    </div>
    <!-- /#wrapper -->
    <div class="modal fade" id="recordInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    </div>

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
        $('#records-container').load("/AppServer/loan_records.html?state=-1");
        function get_into_records(obj, state) {
            switch (state) {
                case -1:
                    $('.page-header').text('全部用户');
                    break;
                case 0:
                    $('.page-header').text('未处理用户');
                    break;
                case 1:
                    $('.page-header').text('异常用户');
                    break;
                case 2:
                    $('.page-header').text('已进件用户');
                    break;
            }
            $('#records-container').load("/AppServer/loan_records.html?state=" + state);
            $('ul.nav a').removeClass('active');
            $(obj).addClass('active');
        }
    </script>

</body>

</html>
