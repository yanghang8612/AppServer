<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ApplyLoan record = (ApplyLoan) request.getAttribute("record");
%>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <div class="panel panel-default">
                <div class="panel-heading">
                    房产信息
                </div>
                <div class="panel-body">
                    <div class="container">
                        <p>抵押房产地址：<%=record.getHouseAddress()%></p>
                        <p>房产证号：<%=record.getHousePropertyCard()%></p>
                        <p>土地来源：<%=record.getHouseLandSources()%></p>
                        <p>房屋性质：<%=record.getHouseType()%></p>
                        <p>建筑年代：<%=record.getHouseBuildYear()%></p>
                        <p>建筑面积(㎡)：<%=record.getHouseBuildArea()%></p>
                        <p>交房日期：<%=record.getHouseHandingTime()%></p>
                        <p><i class="fa <%=(record.isHouseOwnedByOthers() ? "fa-check-circle" : "fa-times-circle")%>"></i> 房产是否与其他人共有</p>
                        <p><i class="fa <%=(record.isHouseIsMortgaged() ? "fa-check-circle" : "fa-times-circle")%>"></i> 房屋目前是否有抵押</p>
                        <p><i class="fa <%=(record.isHouseBorrowerIsOwner() ? "fa-check-circle" : "fa-times-circle")%>"></i> 借款人是否为房产产权人之一</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    借款信息
                </div>
                <div class="panel-body">
                    <div class="container">
                        <p>借款人姓名：<%=record.getBorrowerName()%></p>
                        <p>借款人手机号：<%=record.getBorrowerPhoneNumber()%></p>
                        <p>借款金额(元)：<%=record.getBorrowerAmount()%></p>
                        <p>婚姻状况：<%=record.getBorrowerMarriage()%></p>
                        <p>住宅所在地：<%=record.getBorrowerAddress()%></p>
                        <p>详细地址：<%=record.getBorrowerDetailedAddress()%></p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    申请状态
                </div>
                <div class="panel-body">
                    <div class="container">
                        <select class="custom-select" id="apply-state-select">
                            <option value="0">未处理</option>
                            <option value="1">未联系上申请人</option>
                            <option value="2">已接受申请</option>
                            <option value="3">已放款</option>
                            <option value="4">已拒绝</option>
                        </select>
                        <script>
                            $('#apply-state-select').val(<%=record.getApplyState()%>)
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
            url: "/AppServer/Financial/UpdateLoanState",
            data: {id:<%=record.getId()%>,state:$("#apply-state-select").val()},
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
