<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/AppServer/css/server.css">
  </head>
  <body>

  	<nav class="navbar navbar-toggleable-md navbar-light bg-faded register-navbar">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#">
		  	<img src="/AppServer/imgs/icon.png" width="30" height="30" alt="">
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
	    <div class="form-group row" id="inputPhoneNumberGroup">
	      <label for="inputPhoneNumber" class="offset-1 col-3 col-form-label col-form-label-sm">手机号</label>
	      <div class="col-7">
	        <input type="number" class="form-control form-control-sm" id="inputPhoneNumber">
	      </div>
	    </div>
	    <div class="form-group row" id="inputVeritificationCodeGroup">
	      <label for="inputVeritificationCode" class="offset-1 col-3 col-form-label col-form-label-sm">验证码</label>
	      <div class="col-5">
	        <input type="number" class="form-control form-control-sm" id="inputVeritificationCode">
	      </div>
	      <div class="col-3">
	        <button class="btn btn-primary btn-sm btn-danger get-veritification-code-btn">获取</button>
	      </div>
	    </div>
	    <div class="form-group row" id="invitationCodeGroup">
	      <label for="invitationCode" class="offset-1 col-3 col-form-label col-form-label-sm">邀请码</label>
	      <div class="col-7">
	        <input type="text" class="form-control form-control-sm" id="invitationCode">
	      </div>
	    </div>
	    <div class="form-group row" id="recommenderIDGroup">
	      <label for="recommenderID" class="offset-1 col-3 col-form-label col-form-label-sm">推荐人</label>
	      <div class="col-7">
	        <input type="number" class="form-control form-control-sm" id="recommenderID">
	      </div>
	    </div>
	    <div class="form-group row" id="inputPasswordGroup">
	      <label for="inputPassword" class="offset-1 col-3 col-form-label col-form-label-sm">密码</label>
	      <div class="col-7">
	        <input type="password" class="form-control form-control-sm" id="inputPassword">
	      </div>
	    </div>
	    <div class="form-group row" id="inputConfirmPasswordGroup">
	      <label for="inputConfirmPassword" class="offset-1 col-3 col-form-label col-form-label-sm">确认密码</label>
	      <div class="col-7">
	        <input type="password" class="form-control form-control-sm" id="inputConfirmPassword">
	      </div>
	    </div>
	    <div class="form-group row">
	      <div class="offset-1 col-10">
	        <button type="submit" class="btn btn-sm btn-block btn-danger register-btn">注册</button>
	      </div>
	    </div>
		</div>

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	function getUrlParam(name) {
			  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			  var r = window.location.search.substr(1).match(reg);
			  if (r != null) return unescape(r[2]); return null;
			}
    	$('#recommenderID').val(getUrlParam('recommenderID'));
    	var phoneNumberCheckState=false;
    	var invitationCodeCheckState=false;
    	var generatedVerificationCode;
    	$('.register-btn').click(function(){
    		if($('#inputPhoneNumber').val().length != 11){
    			$('#inputPhoneNumberGroup').addClass('has-danger');
    			return;
    		}
    		else {
    			$('#inputPhoneNumberGroup').removeClass('has-danger');
    		}
    		if (!phoneNumberCheckState){
    			$.ajax({
             type: "POST",
             url: "/AppServer/UserManager/VerifyPhoneNumber",
             data: {phoneNumber:$("#inputPhoneNumber").val()},
             dataType: "json",
             success: function(data){
                         if(data.Status=='true'){
                         	phoneNumberCheckState=true;
                         }
                         else{
                         	alert(data.Info);
                         }
                      }
         	});
        }

        if($('#invitationCode').val().length != 6){
    			$('#invitationCodeGroup').addClass('has-danger');
    			return;
    		}
    		else {
    			$('#invitationCodeGroup').removeClass('has-danger');
    		}
    		if (!invitationCodeCheckState){
    			$.ajax({
             type: "POST",
             url: "/AppServer/UserManager/VerifyInvitationCode",
             data: {invitationCode:$("#invitationCode").val()},
             dataType: "json",
             success: function(data){
                         if(data.Status=='true'){
                         	invitationCodeCheckState=true;
                         }
                         else{
                         	alert(data.Info);
                         }
                      }
         	});
        }

        if($('#inputPassword').val().length<6){
        	$('#inputPasswordGroup').addClass('has-danger');
        	return;
        }
        else{
        	$('#inputPasswordGroup').removeClass('has-danger');
        }

        if($('#inputPassword').val()!=$('#inputConfirmPassword').val()){
        	$('#inputConfirmPasswordGroup').addClass('has-danger');
        	return;
        }
        else{
        	$('#inputConfirmPasswordGroup').removeClass('has-danger');
        }

        if(generatedVerificationCode!=$('#inputVeritificationCode').val()){
        	$('#inputVeritificationCodeGroup').addClass('has-danger');
        }
        else{
        	$('#inputVeritificationCodeGroup').removeClass('has-danger');
        	if(phoneNumberCheckState&&invitationCodeCheckState){
        		$.ajax({
             type: "POST",
             url: "/AppServer/UserManager/Register",
             data: {phoneNumber:$("#inputPhoneNumber").val(),invitationCode:$("#invitationCode").val(),recommenderID:$("#recommenderID").val(),password:$("#inputPassword").val()},
             dataType: "json",
             success: function(data){
                         if(data.Status=='true'){
                         	window.location.href="/success.html";
                         }
                         else{
                         	alert(data.Info);
                         }
                      }
         	});
        	}
        }
    	})
    	$('.get-veritification-code-btn').click(function(){
    		if($('#inputPhoneNumber').val().length != 11){
    			$('#inputPhoneNumberGroup').addClass('has-danger');
    			return;
    		}
    		else {
    			$('#inputPhoneNumberGroup').removeClass('has-danger');
    		}
    		if (!phoneNumberCheckState){
    			$.ajax({
             type: "POST",
             url: "/AppServer/UserManager/VerifyPhoneNumber",
             data: {phoneNumber:$("#inputPhoneNumber").val()},
             dataType: "json",
             success: function(data){
                         if(data.Status=='true'){
                         	phoneNumberCheckState=true;
                         	generatedVerificationCode=parseInt(1000000*Math.random());
							         		var content="#code#=" + generatedVerificationCode + "&#hour#=5分钟";
							         		$.ajax({
							             type: "POST",
							             url: "https://sms.yunpian.com/v2/sms/tpl_single_send.json",
							             data: {apikey:'0cbc31053ed959bba435f03633e0777e',tpl_id:'1751388',tpl_value:content,mobile:$("#inputPhoneNumber").val()},
							             dataType: "json",
							             success: function(data){}
							         		});
                         }
                         else{
                         	alert(data.Info);
                         }
                      }
         	});
        }
    	})
    </script>
  </body>
</html>