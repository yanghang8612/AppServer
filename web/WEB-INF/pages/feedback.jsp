<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="com.huachuang.server.entity.UserFeedback" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

    <!-- Sweet Alert -->
    <link href="/AppServer/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="/AppServer/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- iCheck style -->
    <link href="/AppServer/static/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body>
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
                <li>
                    <a href="loan.html"><i class="fa fa-money"></i> <span class="nav-label">贷款管理 </span></a>
                </li>
                <li>
                    <a href="into.html"><i class="fa fa-user-circle"></i> <span class="nav-label">用户进件 </span></a>
                </li>
                <li>
                    <a href="withdraw.html"><i class="fa fa-hand-paper-o"></i> <span class="nav-label">用户提现 </span></a>
                </li>
                <li class="active">
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
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-md-12 animated fadeInRight">
                    <div class="mail-box-header">
                        <%--<form method="get" action="" class="pull-right mail-search">--%>
                            <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control input-sm" name="search">--%>
                                <%--<div class="input-group-btn">--%>
                                    <%--<button type="submit" class="btn btn-sm btn-primary">--%>
                                        <%--搜索--%>
                                    <%--</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</form>--%>
                        <h2>
                            消息中心
                        </h2>
                        <div class="mail-tools tooltip-demo m-t-md">
                            <div class="btn-group pull-right">
                                <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i></button>
                                <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i></button>

                            </div>
                            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="Refresh inbox"><i class="fa fa-refresh"></i> 刷新</button>
                            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Move to trash"><i class="fa fa-trash-o"></i> </button>

                        </div>
                    </div>
                    <div class="mail-box">
                        <table class="table table-hover table-mail">
                            <tbody>
                            <%
                                List<UserFeedback> feedbacks = (List<UserFeedback>) request.getAttribute("feedback");
                                Map<Long, User> users = (Map<Long, User>) request.getAttribute("users");
                                Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                for (UserFeedback feedback : feedbacks) {
                                    User user = users.get(feedback.getUserId());
                                    if (feedback.getState() == 0) {
                                        %><tr class="unread"><%
                                    }
                                    else {
                                        %><tr class="read"><%
                                    }
                                    %>
                                        <td class="check-mail"><input type="checkbox" class="i-checks"></td>
                                        <td class="mail-ontact"><%=(user.isCertificationState() ? certifications.get(user.getUserId()).getUserName() : user.getUserPhoneNumber())%></td>
                                        <td class="mail-subject"><a href="javascript:void(0)" onclick="<%=(feedback.getState() == 0 ? "setMessageReaded(this, "+ feedback.getId() + ")" : "")%>"><%=feedback.getMessage()%></a></td>
                                        <td class="text-right mail-date"><%=fmt.format(feedback.getCommitTime())%></td>
                                        <td class="text-right">
                                            <a class="label label-primary" onclick="deleteFeedback(this, <%=feedback.getId()%>)">
                                                <i class="fa fa-check-circle-o"></i>
                                                &nbsp;删除
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                }
                            %>
                            </tbody>
                        </table>


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

<!-- Sweet alert -->
<script src="/AppServer/static/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Toastr script -->
<script src="/AppServer/static/js/plugins/toastr/toastr.min.js"></script>

<!-- iCheck script -->
<script src="/AppServer/static/js/plugins/iCheck/icheck.min.js"></script>

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
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        updateUnreadCount();
    });
    function updateUnreadCount() {
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
    }
    function setMessageReaded(obj, id) {
        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/UpdateFeedbackState",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    $(obj).parents('tr').removeClass('unread');
                    $(obj).parents('tr').addClass('read');
                    $(obj).removeAttr('onclick');
                    updateUnreadCount();
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    }
    function deleteFeedback(obj, id) {
        swal({
            title: "",
            text: "确认删除该条消息吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function () {
            swal("成功", "该条消息已删除", "success");
            $.ajax({
                type: "POST",
                url: "/AppServer/UserManager/DeleteFeedback",
                data: {id:id},
                dataType: "json",
                success: function (data) {
                    if (data.Status == 'true') {
                        $(obj).parents('tr').hide();
                        updateUnreadCount();
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
