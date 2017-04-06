<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/server.css">
  </head>
  <body>

  	<nav class="navbar navbar-toggleable-md navbar-light bg-faded register-navbar">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#">
		  	<img src="/imgs/icon.png" width="30" height="30" alt="">
		  	掌触金控
		  </a>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		      </li>
		    </ul>
		  </div>
		</nav>
    
  	<div class="container register-form">
		  <form>
		    <div class="form-group row">
		      <label for="inputPhoneNumber" class="offset-1 col-3 col-form-label col-form-label-sm">手机号</label>
		      <div class="col-7">
		        <input type="number" class="form-control form-control-sm" id="inputPhoneNumber">
		      </div>
		    </div>
		    <div class="form-group row">
		      <label for="inputVeritificationCode" class="offset-1 col-3 col-form-label col-form-label-sm">验证码</label>
		      <div class="col-5">
		        <input type="number" class="form-control form-control-sm" id="inputVeritificationCode">
		      </div>
		      <div class="col-3">
		        <button class="btn btn-primary btn-sm btn-danger">获取</button>
		      </div>
		    </div>
		    <div class="form-group row">
		      <label for="invitationCode" class="offset-1 col-3 col-form-label col-form-label-sm">邀请码</label>
		      <div class="col-7">
		        <input type="text" class="form-control form-control-sm" id="invitationCode">
		      </div>
		    </div>
		    <div class="form-group row">
		      <label for="recommenderID" class="offset-1 col-3 col-form-label col-form-label-sm">推荐人</label>
		      <div class="col-7">
		        <input type="number" class="form-control form-control-sm" id="recommenderID">
		      </div>
		    </div>
		    <div class="form-group row">
		      <label for="inputPassword" class="offset-1 col-3 col-form-label col-form-label-sm">密码</label>
		      <div class="col-7">
		        <input type="password" class="form-control form-control-sm" id="inputPassword">
		      </div>
		    </div>
		    <div class="form-group row">
		      <label for="inputConfirmPassword" class="offset-1 col-3 col-form-label col-form-label-sm">确认密码</label>
		      <div class="col-7">
		        <input type="password" class="form-control form-control-sm" id="inputConfirmPassword">
		      </div>
		    </div>
		    <div class="form-group row">
		      <div class="offset-1 col-10">
		        <button type="submit" class="btn btn-sm btn-block btn-danger">注册</button>
		      </div>
		    </div>
		  </form>
		</div>

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	function getUrlParam(name) {
			  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			  var r = window.location.search.substr(1).match(reg);
			  if (r != null) return unescape(r[2]); return null;
			}
    	$('#recommenderID').val(getUrlParam('recommenderID'));
    </script>
  </body>
</html>