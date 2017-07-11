<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ApplyLoan record = (ApplyLoan) request.getAttribute("record");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h5 class="modal-title">贷款详细信息</h5>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>房产信息</h5>
                        </div>
                        <div class="ibox-content">
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
                <div class="col-md-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>借款信息</h5>
                        </div>
                        <div class="ibox-content">
                            <p>借款人姓名：<%=record.getBorrowerName()%></p>
                            <p>借款人手机号：<%=record.getBorrowerPhoneNumber()%></p>
                            <p>借款金额(元)：<%=record.getBorrowerAmount()%></p>
                            <p>婚姻状况：<%=record.getBorrowerMarriage()%></p>
                            <p>住宅所在地：<%=record.getBorrowerAddress()%></p>
                            <p>详细地址：<%=record.getBorrowerDetailedAddress()%></p>
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
                        <select class="custom-select" id="applyStateSelect">
                            <option value="0">未处理</option>
                            <option value="1">申请所在地区未开通该项业务</option>
                            <option value="2">已接受申请</option>
                            <option value="3">已放款</option>
                            <option value="4">已拒绝</option>
                        </select>
                        <script>
                            $('#applyStateSelect').val(<%=record.getApplyState()%>)
                        </script>
                    </div>
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
        setTimeout(function(){
            $.ajax({
                type: "POST",
                url: "/AppServer/Financial/UpdateLoanState",
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
    })
</script>
