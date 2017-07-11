<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.huachuang.server.entity.UserWithdraw" %>
<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    UserWithdraw record = (UserWithdraw) request.getAttribute("record");
    User user = (User) request.getAttribute("user");
    UserCertificationInfo certification = (UserCertificationInfo) request.getAttribute("certification");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h5 class="modal-title">提现信息</h5>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>银行卡信息</h5>
                        </div>
                        <div class="ibox-content">
                            <p>提现银行卡号：<%=record.getCardNumber()%></p>
                            <p>所属银行：<%=record.getBankName()%></p>
                            <p>银行卡类型：<%=record.getCardType()%></p>
                            <p>提现金额：<%=record.getAmount()%></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>申请人信息</h5>
                        </div>
                        <div class="ibox-content">
                            <p>申请人姓名：<%=(certification == null ? "<未实名认证>" : certification.getUserName())%></p>
                            <p>申请人手机号：<%=user.getUserPhoneNumber()%></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>申请状态</h5>
                </div>
                <div class="ibox-content">
                    <div class="container">
                        <%
                            if (record.getState() == 1) {
                                %><p>已提现</p><%
                            }
                            else if (record.getState() == 2) {
                                %><p>提现异常</p><%
                            }
                            else {
                                %>
                                    <select class="custom-select" id="applyStateSelect">
                                        <option value="0">未处理</option>
                                        <option value="1">已提现</option>
                                        <option value="2">提现异常</option>
                                    </select>
                                <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            <%
                if (record.getState() == 0) {
                    %><button type="button" class="btn btn-primary" data-dismiss="modal" onclick="modifyState()">修改状态</button><%
                }
            %>
        </div>
    </div>
</div>
<script>
    function modifyState() {
        setTimeout(function(){
            $.ajax({
                type: "POST",
                url: "/AppServer/Financial/UpdateWithdrawState",
                data: {id:<%=record.getId()%>,state:$("#applyStateSelect").val()},
                dataType: "json",
                success: function (data) {
                    if (data.Status == 'true') {
                        $('.list-group .active').children()[0].click();
                    }
                    else {
                        toastr.info(data.Info);
                    }
                }
            });
        },500);
    }
</script>
