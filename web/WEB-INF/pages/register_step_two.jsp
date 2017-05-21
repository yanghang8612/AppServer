<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>掌触金控</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="/AppServer/vendor/css/server.css">
</head>

<body>
    <nav class="navbar navbar-light bg-faded register-navbar">
        <a class="navbar-brand" href="#">
            <img src="/AppServer/imgs/icon.png" width="30" height="30" alt="">
            掌触金控
        </a>
    </nav>

    <div class="container register-form">
        <div class="form-group row" id="inputPhoneNumberGroup">
            <label for="inputPhoneNumber" class="col-4 col-form-label col-form-label-sm">手机号</label>
            <div class="col-8">
                <input type="number" class="form-control form-control-sm" id="inputPhoneNumber">
            </div>
        </div>
        <div class="form-group row" id="inputVerificationCodeGroup">
            <label for="inputVerificationCode" class="col-4 col-form-label col-form-label-sm">验证码</label>
            <div class="col-5">
                <input type="number" class="form-control form-control-sm" id="inputVerificationCode">
            </div>
            <div class="col-3">
                <button class="btn btn-primary btn-sm btn-block btn-danger get-verification-code-btn">获取</button>
            </div>
        </div>
        <div class="form-group row" id="inputIdentifyCodeGroup">
            <label for="inputIdentifyCode" class="col-4 col-form-label col-form-label-sm">邀请码/推荐人</label>
            <div class="col-8">
                <input type="text" class="form-control form-control-sm" id="inputIdentifyCode">
            </div>
        </div>
        <%--<div class="form-group row" id="recommenderIDGroup">--%>
        <%--<label for="recommenderID" class="col-3 col-form-label col-form-label-sm">推荐人</label>--%>
        <%--<div class="col-7">--%>
        <%--<input type="number" class="form-control form-control-sm" id="recommenderID">--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="form-group row" id="inputPasswordGroup">
            <label for="inputPassword" class="col-4 col-form-label col-form-label-sm">密码</label>
            <div class="col-8">
                <input type="password" class="form-control form-control-sm" id="inputPassword">
            </div>
        </div>
        <div class="form-group row" id="inputConfirmPasswordGroup">
            <label for="inputConfirmPassword" class="col-4 col-form-label col-form-label-sm">确认密码</label>
            <div class="col-8">
                <input type="password" class="form-control form-control-sm" id="inputConfirmPassword">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-12">
                <button type="submit" class="btn btn-sm btn-block btn-danger register-btn">注册</button>
            </div>
        </div>
    </div>

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/toastr.js/latest/toastr.min.js"></script>
    <script type="text/javascript">
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
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }
        $('#inputIdentifyCode').val(getUrlParam('identifyCode'));
        var phoneNumberCheckState = false;
        var identifyCodeCheckState = false;
        var generatedVerificationCode;
        $('.register-btn').click(function () {
            if ($('#inputPhoneNumber').val().length != 11) {
                toastr.error('请输入正确的手机号');
                return;
            }
            if (!phoneNumberCheckState) {
                $.ajax({
                    type: "POST",
                    url: "/AppServer/UserManager/VerifyPhoneNumber",
                    data: {phoneNumber: $("#inputPhoneNumber").val()},
                    dataType: "json",
                    success: function (data) {
                        if (data.Status == 'true') {
                            phoneNumberCheckState = true;
                        }
                        else {
                            toastr.info(data.Info);
                        }
                    }
                });
            }

            if ($('#inputIdentifyCode').val().length != 6 && $('#inputIdentifyCode').val().length != 11) {
                toastr.error('6位数字大写字母组合的邀请码或11位推荐人手机号，请检查后重试');
                return;
            }
            if (!identifyCodeCheckState) {
                if ($('#inputIdentifyCode').val().length == 6) {
                    $.ajax({
                        type: "POST",
                        url: "/AppServer/UserManager/VerifyInvitationCode",
                        data: {invitationCode: $("#inputIdentifyCode").val()},
                        dataType: "json",
                        success: function (data) {
                            if (data.Status == 'true') {
                                identifyCodeCheckState = true;
                            }
                            else {
                                toastr.info(data.Info);
                            }
                        }
                    });
                }
                else {
                    $.ajax({
                        type: "POST",
                        url: "/AppServer/UserManager/VerifyRecommenderID",
                        data: {recommenderID: $("#inputIdentifyCode").val()},
                        dataType: "json",
                        success: function (data) {
                            if (data.Status == 'true') {
                                identifyCodeCheckState = true;
                            }
                            else {
                                toastr.info(data.Info);
                            }
                        }
                    });
                }
            }

            if ($('#inputPassword').val().length < 6) {
                toastr.error('密码至少包含6位字符');
                return;
            }

            if ($('#inputPassword').val() != $('#inputConfirmPassword').val()) {
                toastr.error('两次输入密码不一致');
                return;
            }

            if (generatedVerificationCode != $('#inputVerificationCode').val()) {
                toastr.error('验证码错误，请检查后重试');
            }
            else {
                if (phoneNumberCheckState && identifyCodeCheckState) {
                    $.ajax({
                        type: "POST",
                        url: "/AppServer/UserManager/Register",
                        data: {
                            phoneNumber: $("#inputPhoneNumber").val(),
                            password: $("#inputPassword").val(),
                            identifyCode: $("#inputIdentifyCode").val(),
                            shareType: getUrlParam("shareType")
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.Status == 'true') {
                                window.location.href = "/AppServer/success.html";
                            }
                            else {
                                toastr.info(data.Info);
                            }
                        }
                    });
                }
            }
        })
        $('.get-verification-code-btn').click(function () {
            if ($('#inputPhoneNumber').val().length != 11) {
                toastr.error('请输入正确的手机号');
                return;
            }
            if (!phoneNumberCheckState) {
                $.ajax({
                    type: "POST",
                    url: "/AppServer/UserManager/VerifyPhoneNumber",
                    data: {phoneNumber: $("#inputPhoneNumber").val()},
                    dataType: "json",
                    success: function (data) {
                        if (data.Status == 'true') {
                            phoneNumberCheckState = true;
                            generatedVerificationCode = parseInt(1000000 * Math.random());
                            var content = "#code#=" + generatedVerificationCode + "&#hour#=5分钟";
                            $.ajax({
                                type: "POST",
                                url: "https://sms.yunpian.com/v2/sms/tpl_single_send.json",
                                data: {
                                    apikey: '0cbc31053ed959bba435f03633e0777e',
                                    tpl_id: '1751388',
                                    tpl_value: content,
                                    mobile: $("#inputPhoneNumber").val()
                                },
                                dataType: "json",
                                success: function (data) {
                                }
                            });
                            settime($('.get-verification-code-btn'));
                        }
                        else {
                            toastr.info(data.Info);
                        }
                    }
                });
            }
        })
        var countdown = 30;
        function settime(obj) {
            if (countdown == 0) {
                obj.removeClass("disabled");
                obj.text("获取");
                countdown = 30;
                return;
            } else {
                obj.addClass("disabled");
                obj.text(countdown + "S");
                countdown--;
            }
            setTimeout(function() {
                    settime(obj) }
                ,1000)
        }
    </script>
</body>
</html>