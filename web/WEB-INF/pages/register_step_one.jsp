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
    
  	<div class="header-container">
  		<div id="headerCarouselIndicators" class="carousel slide" data-ride="carousel">
			  <ol class="carousel-indicators">
			    <li data-target="#headerCarouselIndicators" data-slide-to="0" class="active"></li>
			    <li data-target="#headerCarouselIndicators" data-slide-to="1"></li>
			    <li data-target="#headerCarouselIndicators" data-slide-to="2"></li>
			    <li data-target="#headerCarouselIndicators" data-slide-to="3"></li>
			    <li data-target="#headerCarouselIndicators" data-slide-to="4"></li>
			  </ol>
			  <div class="carousel-inner" role="listbox">
			    <div class="carousel-item active">
			      <img class="d-block img-fluid header-image" src="/AppServer/imgs/ad_1.png" alt="First slide">
			    </div>
			    <div class="carousel-item">
			      <img class="d-block img-fluid header-image" src="/AppServer/imgs/ad_2.png" alt="Second slide">
			    </div>
			    <div class="carousel-item">
			      <img class="d-block img-fluid header-image" src="/AppServer/imgs/ad_3.png" alt="Third slide">
			    </div>
			    <div class="carousel-item">
			      <img class="d-block img-fluid header-image" src="/AppServer/imgs/ad_4.png" alt="Fourth slide">
			    </div>
			    <div class="carousel-item">
			      <img class="d-block img-fluid header-image" src="/AppServer/imgs/ad_5.png" alt="Fifth slide">
			    </div>
			  </div>
			  <a class="carousel-control-prev" href="#headerCarouselIndicators" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#headerCarouselIndicators" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
  	</div>

		<img class="img-fluid body-image" src="/AppServer/imgs/act_body.png">

		<div class="content-container">
			<p class="rules-content">掌触金控，邀请好友一起加入我们。每个手机号码只能注册一次，推荐好友注册可获得现金奖励，分享朋友注册或办理信用卡、贷款、刷卡都可获得积分，积分可兑换现金。杜绝恶意刷单，违反者将取消所有返佣及相应的处罚，具体奖励如下：</p>
			<table class="table table-sm rate-table">
			  <thead>
			    <tr>
			      <th>奖励类型</th>
			      <th>一级好友</th>
			      <th>二级好友</th>
			      <th>三级好友</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <th scope="row">推荐好友</th>
			      <td>25元</td>
			      <td>12.5元</td>
			      <td>5元</td>
			    </tr>
			    <tr>
			      <th scope="row">积分返佣</th>
			      <td>0.005%</td>
			      <td>0.003%</td>
			      <td>0.001%</td>
			    </tr>
			  </tbody>
			</table>
			<button class="btn btn-primary btn-block btn-danger next-step" role="button">注册</button>
		</div>

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	$('.next-step').click(function(){
    		var url = window.location.href;
    		window.location.href = url.replace('one', 'two');
    	})
    </script>
  </body>
</html>