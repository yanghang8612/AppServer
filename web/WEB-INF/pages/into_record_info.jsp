<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="com.huachuang.server.entity.UserDebitCard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getAttribute("user");
    UserCertificationInfo certification = (UserCertificationInfo) request.getAttribute("certification");
    UserDebitCard card = (UserDebitCard) request.getAttribute("card");
%>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h5 class="modal-title">进件详细信息</h5>
        </div>
        <div class="modal-body">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户身份证信息</h5>
                </div>
                <div class="ibox-content">
                    <p>用户姓名：<%=certification.getUserName()%></p>
                    <p>身份证号：<%=certification.getUserIdentityCard()%></p>
                    <img class="into-image" src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/front.jpg">
                    <img class="into-image" src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/back.jpg">
                    <img class="into-image" src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/identify_card/handing.jpg">
                </div>
            </div>
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户结算卡信息</h5>
                </div>
                <div class="ibox-content">
                    <p>结算卡户主：<%=card.getOwnerName()%></p>
                    <p>结算卡卡号：<%=card.getCardNumber()%></p>
                    <p>结算卡银行：<%=card.getHeadOffice()%></p>
                    <p>结算卡开户行：<%=card.getBranch()%></p>
                    <img class="into-image" src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/debit_card/front.jpg">
                    <img class="into-image" src="/AppServer/preview/<%=user.getUserPhoneNumber()%>/debit_card/back.jpg">
                </div>
            </div>
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>进件状态</h5>
                </div>
                <div class="ibox-content">
                <%
                    if (user.getMobilePayState() == 1) {
                        %>
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">状态</label>
                                    <div class="col-md-2">
                                        <p class="form-control-static">已进件</p>
                                    </div>
                                </div>
                            </form>
                        <%
                    }
                    else {
                        %>
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="into-state-select">状态</label>
                                    <div class="col-md-2">
                                        <select class="form-control input-sm" id="into-state-select">
                                            <option value="0">未处理</option>
                                            <option value="1">已进件</option>
                                            <option value="2">身份证照片异常</option>
                                            <option value="3">营业执照异常</option>
                                            <option value="4">结算卡照片异常</option>
                                            <option value="5">未使用民生银行结算卡</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="mid-input">
                                    <label class="col-md-1 control-label" for="mid">MID</label>
                                    <div class="col-md-3">
                                        <input type="text" class="form-control input-sm" id="mid" placeholder="MID">
                                    </div>
                                </div>
                                <div class="form-group" id="key-input">
                                    <label class="col-md-1 control-label" for="key">KEY</label>
                                    <div class="col-md-3">
                                        <input type="text" class="form-control input-sm" id="key" placeholder="KEY">
                                    </div>
                                </div>
                            </form>
                            <script>
                                $('#mid-input').hide();
                                $('#key-input').hide();
                                $('#into-state-select').val(<%=user.getMobilePayState()%>);
                                $('#into-state-select').change(function (e) {
                                    if ($('#into-state-select').val() == 1) {
                                        $('#mid-input').show();
                                        $('#key-input').show();
                                    }
                                    else {
                                        $('#mid-input').hide();
                                        $('#key-input').hide();
                                    }
                                })
                            </script>
                        <%
                    }
                %>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" data-dismiss="modal" id="modifyStateButton">修改状态</button>
        </div>
    </div>
</div>
<script>
    $('#modifyStateButton').click(function () {
        if ((<%=user.getMobilePayState()%>) == 1) {
            return;
        }
        if ($("#into-state-select").val() == 1) {
            if ($("#mid").val().length == 0 || $("#key").val().length == 0) {
                toastr.info('请输入用户进件的MID以及KEY');
                return;
            }
        }
        setTimeout(function(){
            $.ajax({
                type: "POST",
                url: "/AppServer/Financial/UpdateIntoState",
                data: {userID:<%=user.getUserId()%>,state:$("#into-state-select").val(),mid:$("#mid").val(),key:$("#key").val()},
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
        },500);
    })
</script>
