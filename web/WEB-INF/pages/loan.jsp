<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>掌触金控后台管理系统</title>

    <link href="/AppServer/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/AppServer/static/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="/AppServer/static/css/animate.css" rel="stylesheet">
    <link href="/AppServer/static/css/style.css" rel="stylesheet">
    <link href="/AppServer/static/css/server.css" rel="stylesheet">

    <!-- DataTables style -->
    <link href="/AppServer/static/css/plugins/dataTables/datatables.min.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="/AppServer/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">

</head>

<body class="fixed-sidebar no-skin-config full-height-layout">
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="row">
                        <div class="col-md-4">
                            <img alt="image" class="img-circle pull-left" src="/AppServer/static/img/profile_small.jpg" />
                        </div>
                        <div class="col-md-8">
                            <span class="block m-t-xs"> <strong class="font-bold">admin</strong></span>
                            <span class="text-muted text-xs block">管理员 </span>
                        </div>
                    </div>
                </li>
                <li>
                    <a href="main.html"><i class="fa fa-users"></i> <span class="nav-label">代理商管理 </span></a>
                </li>
                <li>
                    <a href="credit_card.html"><i class="fa fa-credit-card"></i> <span class="nav-label">信用卡管理 </span></a>
                </li>
                <li class="active">
                    <a href="loan.html"><i class="fa fa-money"></i> <span class="nav-label">贷款管理 </span></a>
                </li>
                <li>
                    <a href="into.html"><i class="fa fa-user-circle"></i> <span class="nav-label">用户进件 </span></a>
                </li>
                <li>
                    <a href="withdraw.html"><i class="fa fa-hand-paper-o"></i> <span class="nav-label">用户提现 </span></a>
                </li>
                <li>
                    <a href="feedback.html"><i class="fa fa-commenting"></i> <span class="nav-label">意见反馈 </span><span class="label label-warning pull-right" id="unreadLabel">1</span></a>
                </li>
                <li>
                    <a href="setting.html"><i class="fa fa-cog"></i> <span class="nav-label">参数设置 </span></a>
                </li>
            </ul>

        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎使用掌触金控后台管理系统</span>
                    </li>
                    <li>
                        <a href="login.html">
                            <i class="fa fa-sign-out"></i> 注销
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-md-12">
                <h2>贷款管理</h2>
            </div>
        </div>
        <div class="fh-breadcrumb">
            <div class="fh-column">
                <div class="full-height-scroll">
                    <ul class="list-group elements-list">
                        <li class="list-group-item active">
                            <a data-toggle="tab" href="javascript:void(0)" onclick="getApplyLoanRecords(-1)">
                                <small>全部申请</small>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a data-toggle="tab" href="javascript:void(0)" onclick="getApplyLoanRecords(0)">
                                <small>未处理申请</small>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a data-toggle="tab" href="javascript:void(0)" onclick="getApplyLoanRecords(1)">
                                <small>处理中申请</small>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a data-toggle="tab" href="javascript:void(0)" onclick="getApplyLoanRecords(2)">
                                <small>已处理申请</small>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>

            <div class="full-height">
                <div class="full-height-scroll white-bg border-left">
                    <div class="element-detail-box">
                        <div class="tab-content">
                            <div class="tab-pane active">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div>
                <strong>Copyright</strong> 掌触金控 &copy; 2017-2018
            </div>
        </div>
    </div>
</div>

<!-- Mainly scripts -->
<script src="/AppServer/static/js/jquery-2.1.1.js"></script>
<script src="/AppServer/static/js/bootstrap.min.js"></script>
<script src="/AppServer/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/AppServer/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/AppServer/static/js/inspinia.js"></script>
<script src="/AppServer/static/js/plugins/pace/pace.min.js"></script>

<!-- DataTables script -->
<script src="/AppServer/static/js/plugins/dataTables/datatables.min.js"></script>

<!-- Toastr script -->
<script src="/AppServer/static/js/plugins/toastr/toastr.min.js"></script>

<script>
    $(document).ready(function(){
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": false,
            "preventDuplicates": false,
            "positionClass": "toast-bottom-center",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "1500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        $('.tab-pane').load("/AppServer/loan_records.html?state=-1");
        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/GetUnreadFeedbackCount",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    if (data.Count == 0) {
                        $('#unreadLabel').hide();
                    }
                    else {
                        $('#unreadLabel').text(data.Count);
                    }
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    });
    function getApplyLoanRecords(state) {
        $('.tab-pane').load("/AppServer/loan_records.html?state=" + state);
    }
</script>

</body>
</html>
