<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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

    <!-- Awesome Checkbox style -->
    <link href="/AppServer/static/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <!-- DataTables style -->
    <link href="/AppServer/static/css/plugins/dataTables/datatables.min.css" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="/AppServer/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

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
                <li class="active">
                    <a href="main.html"><i class="fa fa-users"></i> <span class="nav-label">代理商管理 </span></a>
                </li>
                <li>
                    <a href="credit_card.html"><i class="fa fa-credit-card"></i> <span class="nav-label">信用卡管理 </span></a>
                </li>
                <li>
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
                <h2>代理商管理</h2>
                <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#addAgentModal"><i class="fa fa-plus-square-o"></i>&nbsp;添加代理商</button>
                <button class="btn btn-danger primary btn-sm" type="button" onclick="deleteAgent()"><i class="fa fa-times-circle-o"></i>&nbsp;删除代理商</button>
            </div>
        </div>
        <div class="fh-breadcrumb">
            <div class="fh-column">
                <div class="full-height-scroll">
                    <ul class="list-group elements-list">
                        <%
                            List<User> agents = (List<User>) request.getAttribute("agents");
                            Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                            for (User agent : agents) {
                                long agentID = agent.getUserId();
                                %>
                                    <li class="list-group-item">
                                        <a data-toggle="tab" href="javascript:void(0)" onclick="showRightContent(<%=agent.getUserPhoneNumber()%>)">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <img alt="image" class="img-circle" src="<%=(agent.isHeaderState() ? "/AppServer/header/" + agentID + ".jpg" : "/AppServer/imgs/default_header_image.jpg")%>" style="width: 48px;height: 48px;" />
                                                </div>
                                                <div class="col-md-8">
                                                    <strong><%=agent.getUserPhoneNumber()%></strong><br/>
                                                    <small><%=(agent.isCertificationState() ? certifications.get(agentID).getUserName() : "<未实名注册>")%></small><br/>
                                                    <small><%=(agent.getUserType() == 1 ? "一级代理商" : "二级代理商")%></small>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                <%
                            }
                        %>
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
<div class="modal inmodal fade" id="addAgentModal" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加代理商</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="form-group">
                            <label>手机号</label>
                            <input type="text" placeholder="Phone Number" class="form-control inputPhoneNumber">
                        </div>
                        <div class="form-group">
                            <label>密码</label>
                            <input type="password" placeholder="Password" class="form-control inputPassword">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" placeholder="Password" class="form-control inputConfirmPassword">
                        </div>
                        <div class="radio radio-inline">
                            <input type="radio" id="inlineRadio1" value="1" name="agentTypeRadio" checked>
                            <label for="inlineRadio1"> 一级代理商 </label>
                        </div>
                        <div class="radio radio-inline">
                            <input type="radio" id="inlineRadio2" value="2" name="agentTypeRadio">
                            <label for="inlineRadio2"> 二级代理商 </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addAgent()">提交</button>
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

<!-- Sweet alert -->
<script src="/AppServer/static/js/plugins/sweetalert/sweetalert.min.js"></script>

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
        $('.list-group').find('a')[0].click();
        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/GetUnreadFeedbackCount",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    if (data.Count != 0) {
                        $('#unreadLabel').css({visibility: "visible"});
                        $('#unreadLabel').text(data.Count);
                    }
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    });
    var currentAgentPhoneNumber = 0;
    function showRightContent(phone_number) {
        currentAgentPhoneNumber = phone_number;
        $('.tab-pane').load("/AppServer/agent_info.html?phoneNumber=" + phone_number);
    }
    function addAgent() {
        if($('.inputPhoneNumber').val().length != 11){
            toastr.error('请输入正确的手机号');
            return;
        }

        if($('.inputPassword').val().length < 6){
            toastr.error('密码最少包含6个字符');
            return;
        }

        if($('.inputPassword').val() != $('.inputConfirmPassword').val()){
            toastr.error('两次输入密码不一致');
            return;
        }

        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/AddAgent",
            data: {phoneNumber:$('.inputPhoneNumber').val(),password:$('.inputPassword').val(),type:$('input:radio[name="agentTypeRadio"]:checked').val()},
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
    }
    function deleteAgent() {
        swal({
            title: "",
            text: "确认删除代理商 " + currentAgentPhoneNumber + " 吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "POST",
                url: "/AppServer/UserManager/DeleteUser",
                data: {phoneNumber:currentAgentPhoneNumber},
                dataType: "json",
                success: function (data) {
                    if (data.Status == 'true') {
                        swal({
                            title: "",
                            text: "删除成功",
                            type: "success",
                            showCancelButton: false,
                            closeOnConfirm: true
                        }, function () {
                            window.location.reload();
                        });
                    }
                    else {
                        toastr.info(data.Info);
                    }
                }
            });
        });
    }
</script>

</body>
</html>
