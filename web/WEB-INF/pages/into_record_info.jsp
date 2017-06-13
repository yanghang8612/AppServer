<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="com.huachuang.server.entity.UserDebitCard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getAttribute("user");
    UserCertificationInfo certification = (UserCertificationInfo) request.getAttribute("certification");
    UserDebitCard card = (UserDebitCard) request.getAttribute("card");
%>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <div class="panel panel-default">
                <div class="panel-heading">
                    用户身份证信息
                </div>
                <div class="panel-body">
                    <div class="container">
                        <p>用户姓名：<%=certification.getUserName()%></p>
                        <p>身份证号：<%=certification.getUserIdentityCard()%></p>
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/front.jpg">
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/back.jpg">
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/handing.jpg">
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/license.jpg">
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    用户结算卡信息
                </div>
                <div class="panel-body">
                    <div class="container">
                        <p>结算卡户主：<%=card.getOwnerName()%></p>
                        <p>结算卡卡号：<%=card.getCardNumber()%></p>
                        <p>结算卡银行：<%=card.getHeadOffice()%></p>
                        <p>结算卡开户行：<%=card.getBranch()%></p>
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/debit_card/front.jpg">
                        <img src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/debit_card/back.jpg">
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    申请状态
                </div>
                <div class="panel-body">
                    <div class="container">
                        <select class="custom-select" id="into-state-select">
                            <option value="0">未处理</option>
                            <option value="1">已进件</option>
                            <option value="2">身份证照片异常</option>
                            <option value="3">营业执照异常</option>
                            <option value="4">结算卡照片异常</option>
                            <option value="5">未使用民生银行结算卡</option>
                        </select>
                        <script>
                            $('#into-state-select').val(<%=user.getMobilePayState()%>)
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" data-dismiss="modal" id="modify-state-button">修改状态</button>
        </div>
    </div>
</div>
<script>
    $('#modify-state-button').click(function () {
        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/UpdateIntoState",
            data: {id:<%=user.getUserId()%>,state:$("#into-state-select").val()},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    $('ul.nav a').filter('.active').click();
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    })
</script>
