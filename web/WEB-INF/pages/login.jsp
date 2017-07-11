<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>掌触金控后台管理系统 | 登录</title>

    <link href="/AppServer/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/AppServer/static/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="/AppServer/static/css/animate.css" rel="stylesheet">
    <link href="/AppServer/static/css/style.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="/AppServer/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <%--<div>--%>

            <%--<h1 class="logo-name">BM+</h1>--%>

        <%--</div>--%>
        <h3>欢迎使用掌触金控后台管理系统</h3>
        </p>
        <div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" required="" id="inputUsername">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" required="" id="inputPassword">
            </div>
            <button class="btn btn-primary block full-width m-b" id="loginButton">登录</button>
        </div>
        <p class="m-t"> <strong>Copyright</strong> 掌触金控 &copy; 2017-2018 </p>
    </div>
</div>

<!-- Mainly scripts -->
<script src="/AppServer/static/js/jquery-2.1.1.js"></script>
<script src="/AppServer/static/js/bootstrap.min.js"></script>

<!-- Toastr script -->
<script src="/AppServer/static/js/plugins/toastr/toastr.min.js"></script>

<script>
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
    }
    $('#loginButton').click(function () {
        if($('#inputUsername').val().length == 0){
            toastr.error('请输入用户名');
            return;
        }

        if($('#inputPassword').val().length == 0){
            toastr.error('请输入密码');
            return;
        }

        $.ajax({
            type: "POST",
            url: "/AppServer/login",
            data: {username:$('#inputUsername').val(),password:$('#inputPassword').val()},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    window.location.href = "main.html";
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    })
</script>

</body>

</html>
