<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.huachuang.server.CommonUtils" %>
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

    <!-- TouchSpin style -->
    <link href="/AppServer/static/css/plugins/touchspin/jquery.bootstrap-touchspin.min.css" rel="stylesheet">

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
                <li>
                    <a href="feedback.html"><i class="fa fa-commenting"></i> <span class="nav-label">意见反馈 </span><span class="label label-warning pull-right" id="unreadLabel">1</span></a>
                </li>
                <li class="active">
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
                <h2>注册费用及费率设置</h2>
                <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#modifyVariable"><i class="fa fa-plus-square-o"></i>&nbsp;修改参数</button>
            </div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>VIP注册费用</h5>
                            <h2><%="￥" + CommonUtils.getProperty("VIP_FEE")%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>一级代理商注册费用</h5>
                            <h2><%="￥" + CommonUtils.getProperty("LEVEL_ONE_AGENT_FEE")%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>二级代理商注册费用</h5>
                <h2><%="￥" + CommonUtils.getProperty("LEVEL_TWO_AGENT_FEE")%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>普通用户费率</h5>
                            <h2><%=CommonUtils.getProperty("COMMON_RATE") + "%"%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>VIP用户费率</h5>
                            <h2><%=CommonUtils.getProperty("VIP_RATE") + "%"%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>代理商费率</h5>
                            <h2><%=CommonUtils.getProperty("AGENT_RATE") + "%"%></h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>客服电话</h5>
                            <h2><%=CommonUtils.getProperty("SERVICE_PHONE_NUMBER")%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>一级代理商固定抽成比例</h5>
                            <h2><%=CommonUtils.getProperty("LEVEL_ONE_AGENT_COMMON_PROFIT_RATIO") + "%"%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>二级代理商固定抽成比例</h5>
                            <h2><%=CommonUtils.getProperty("LEVEL_TWO_AGENT_COMMON_PROFIT_RATIO") + "%"%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>VIP注册费用推荐奖金比例</h5>
                            <h2><%=CommonUtils.getProperty("VIP_REGISTER_SHARE_PROFIT_RATIO") + "%"%></h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h5>代理商注册费用推荐奖金比例</h5>
                            <h2><%=CommonUtils.getProperty("AGENT_REGISTER_SHARE_PROFIT_RATIO") + "%"%></h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="ibox">
                        <div class="ibox-title">
                            <h5>VIP以上用户三级利润分成比例(含推荐奖)</h5>
                        </div>
                        <div class="ibox-content">
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>一级用户</h5>
                                    <h2><%=CommonUtils.getProperty("VIP_LEVEL_ONE_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("VIP_LEVEL_ONE_PROFIT_RATIO") + "%"%>;" class="progress-bar progress-bar-danger"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>二级用户</h5>
                                    <h2><%=CommonUtils.getProperty("VIP_LEVEL_TWO_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("VIP_LEVEL_TWO_PROFIT_RATIO") + "%"%>;" class="progress-bar progress-bar-danger"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>三级用户</h5>
                                    <h2><%=CommonUtils.getProperty("VIP_LEVEL_THREE_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("VIP_LEVEL_THREE_PROFIT_RATIO") + "%"%>;" class="progress-bar progress-bar-danger"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="ibox">
                        <div class="ibox-title">
                            <h5>普通用户三级利润分成比例(不含推荐奖)</h5>
                        </div>
                        <div class="ibox-content">
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>一级用户</h5>
                                    <h2><%=CommonUtils.getProperty("COMMON_LEVEL_ONE_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("COMMON_LEVEL_ONE_PROFIT_RATIO") + "%"%>;" class="progress-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>二级用户</h5>
                                    <h2><%=CommonUtils.getProperty("COMMON_LEVEL_TWO_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("COMMON_LEVEL_TWO_PROFIT_RATIO") + "%"%>;" class="progress-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ibox">
                                <div class="ibox-content">
                                    <h5>三级用户</h5>
                                    <h2><%=CommonUtils.getProperty("COMMON_LEVEL_THREE_PROFIT_RATIO") + "%"%></h2>
                                    <div class="progress progress-mini">
                                        <div style="width: <%=CommonUtils.getProperty("COMMON_LEVEL_THREE_PROFIT_RATIO") + "%"%>;" class="progress-bar"></div>
                                    </div>
                                </div>
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
<div class="modal inmodal fade" id="modifyVariable" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">参数设置</h4>
            </div>
            <div class="modal-body">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    VIP注册费用
                                </h6>
                                <input id="vip-fee" type="number" value="<%=CommonUtils.getProperty("VIP_FEE")%>" name="vip-fee" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    一级代理商注册费用
                                </h6>
                                <input id="level-one-agent-fee" type="number" value="<%=CommonUtils.getProperty("LEVEL_ONE_AGENT_FEE")%>" name="level-one-agent-fee" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    二级代理商注册费用
                                </h6>
                                <input id="level-two-agent-fee" type="number" value="<%=CommonUtils.getProperty("LEVEL_TWO_AGENT_FEE")%>" name="level-two-agent-fee" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    客服电话
                                </h6>
                                <div class="input-group">
                                    <span class="input-group-addon">tel</span>
                                    <input id="service-phone-number" type="tel" value="<%=CommonUtils.getProperty("SERVICE_PHONE_NUMBER")%>" name="level-two-agent-fee" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    普通用户费率
                                </h6>
                                <input id="common-rate" type="text" value="<%=CommonUtils.getProperty("COMMON_RATE")%>" name="common-rate" class="small-rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    VIP用户费率
                                </h6>
                                <input id="vip-rate" type="text" value="<%=CommonUtils.getProperty("VIP_RATE")%>" name="vip-rate" class="small-rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    代理商费率
                                </h6>
                                <input id="agent-rate" type="text" value="<%=CommonUtils.getProperty("AGENT_RATE")%>" name="agent-rate" class="small-rate-spin form-control">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    VIP注册费用推荐奖金比例
                                </h6>
                                <input id="vip-register-share-profit-ratio" type="text" value="<%=CommonUtils.getProperty("VIP_REGISTER_SHARE_PROFIT_RATIO")%>" name="vip-register-share-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    代理商注册费用推荐奖金比例
                                </h6>
                                <input id="agent-register-share-profit-ratio" type="text" value="<%=CommonUtils.getProperty("AGENT_REGISTER_SHARE_PROFIT_RATIO")%>" name="agent-register-share-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    一级代理商固定抽成比例
                                </h6>
                                <input id="level-one-agent-common-profit-ratio" type="text" value="<%=CommonUtils.getProperty("LEVEL_ONE_AGENT_COMMON_PROFIT_RATIO")%>" name="level-one-agent-common-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-3">
                                <h6 class="text-center">
                                    二级代理商固定抽成比例
                                </h6>
                                <input id="level-two-agent-common-profit-ratio" type="text" value="<%=CommonUtils.getProperty("LEVEL_TWO_AGENT_COMMON_PROFIT_RATIO")%>" name="level-two-agent-common-profit-ratio" class="rate-spin form-control">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>VIP以上用户三级利润分成比例(含推荐奖)</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    一级用户
                                </h6>
                                <input id="vip-level-one-profit-ratio" type="text" value="<%=CommonUtils.getProperty("VIP_LEVEL_ONE_PROFIT_RATIO")%>" name="vip-level-one-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    二级用户
                                </h6>
                                <input id="vip-level-two-profit-ratio" type="text" value="<%=CommonUtils.getProperty("VIP_LEVEL_TWO_PROFIT_RATIO")%>" name="vip-level-two-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    三级用户
                                </h6>
                                <input id="vip-level-three-profit-ratio" type="text" value="<%=CommonUtils.getProperty("VIP_LEVEL_THREE_PROFIT_RATIO")%>" name="vip-level-three-profit-ratio" class="rate-spin form-control">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>普通用户三级利润分成比例(不含推荐奖)</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    一级用户
                                </h6>
                                <input id="common-level-one-profit-ratio" type="text" value="<%=CommonUtils.getProperty("COMMON_LEVEL_ONE_PROFIT_RATIO")%>" name="common-level-one-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    二级用户
                                </h6>
                                <input id="common-level-two-profit-ratio" type="text" value="<%=CommonUtils.getProperty("COMMON_LEVEL_TWO_PROFIT_RATIO")%>" name="common-level-two-profit-ratio" class="rate-spin form-control">
                            </div>
                            <div class="col-md-4">
                                <h6 class="text-center">
                                    三级用户
                                </h6>
                                <input id="common-level-three-profit-ratio" type="text" value="<%=CommonUtils.getProperty("COMMON_LEVEL_THREE_PROFIT_RATIO")%>" name="common-level-three-profit-ratio" class="rate-spin form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="modifyVariable()">确认修改</button>
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

<!-- TouchSpin script -->
<script src="/AppServer/static/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js"></script>

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
        $(".small-rate-spin").TouchSpin({
            min: 0,
            max: 100,
            step: 0.01,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '%',
            buttondown_class: 'btn btn-white',
            buttonup_class: 'btn btn-white'
        });
        $(".rate-spin").TouchSpin({
            min: 0,
            max: 100,
            step: 1,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '%',
            buttondown_class: 'btn btn-white',
            buttonup_class: 'btn btn-white',
            forcestepdivisibility: 'none'
        });
    });
    function modifyVariable() {
        $.ajax({
            type: "POST",
            url: "/AppServer/UserInfo/UpdateGlobalVariable",
            data: {
                vipFee: $('#vip-fee').val(),
                levelOneAgentFee: $('#level-one-agent-fee').val(),
                levelTwoAgentFee: $('#level-two-agent-fee').val(),
                servicePhoneNumber: $('#service-phone-number').val(),
                commonRate: $('#common-rate').val(),
                vipRate: $('#vip-rate').val(),
                agentRate: $('#agent-rate').val() ,
                vipRegisterShareProfitRatio: $('#vip-register-share-profit-ratio').val(),
                agentRegisterShareProfitRatio: $('#agent-register-share-profit-ratio').val() ,
                levelOneAgentCommonProfitRatio: $('#level-one-agent-common-profit-ratio').val(),
                levelTwoAgentCommonProfitRatio: $('#level-two-agent-common-profit-ratio').val(),
                vipLevelOneProfitRatio: $('#vip-level-one-profit-ratio').val(),
                vipLevelTwoProfitRatio: $('#vip-level-two-profit-ratio').val(),
                vipLevelThreeProfitRatio: $('#vip-level-three-profit-ratio').val(),
                commonLevelOneProfitRatio: $('#common-level-one-profit-ratio').val(),
                commonLevelTwoProfitRatio: $('#common-level-two-profit-ratio').val() ,
                commonLevelThreeProfitRatio: $('#common-level-three-profit-ratio').val()
            },
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
</script>

</body>
</html>
