<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="com.huachuang.server.entity.UserDebitCard" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="ibox float-e-margins">
    <div class="ibox-title">
        <h5>进件列表</h5>
    </div>
    <div class="ibox-content">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" id="recordsList">
                <thead>
                    <tr>
                        <th>用户姓名</th>
                        <th>用户手机号</th>
                        <th>用户账户类型</th>
                        <th>用户结算银行</th>
                        <th>处理状态</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                        Map<Long, UserDebitCard> cards = (Map<Long, UserDebitCard>) request.getAttribute("cards");
                        for (User user : users) {
                            %>
                                <tr onclick="getIntoRecordInfo(<%=user.getUserId()%>)">
                                    <td><%=certifications.get(user.getUserId()).getUserName()%></td>
                                    <td><%=user.getUserPhoneNumber()%></td>
                                    <%
                                        switch (user.getUserType()) {
                                            case 0:
                                                if (user.isVip()) {
                                                    %><td>VIP用户</td><%
                                                }
                                                else {
                                                    %><td>普通用户</td><%
                                                }
                                                break;
                                            case 1:
                                                %><td>一级代理商</td><%
                                                break;
                                            case 2:
                                                %><td>二级代理商</td><%
                                                break;
                                        }
                                    %>
                                    <td><%=cards.get(user.getUserId()).getHeadOffice()%></td>
                                    <%
                                        switch (user.getMobilePayState()) {
                                            case 0:
                                                %><td>未处理</td><%
                                                break;
                                            case 1:
                                                %><td>已进件</td><%
                                                break;
                                            case 2:
                                                %><td>身份证照片异常</td><%
                                                break;
                                            case 3:
                                                %><td>营业执照异常</td><%
                                                break;
                                            case 4:
                                                %><td>结算卡照片异常</td><%
                                                break;
                                            case 5:
                                                %><td>未使用民生银行结算卡</td><%
                                                break;
                                        }
                                    %>

                                </tr>
                            <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal inmodal fade" id="intoRecordInfoModal" tabindex="-1" role="dialog"  aria-hidden="true">
</div>
<script>
    $('#recordsList').DataTable({
        "bSort": false,
        "language": {
            "sProcessing":   "处理中...",
            "sLengthMenu":   "显示 _MENU_ 项结果",
            "sZeroRecords":  "没有匹配结果",
            "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix":  "",
            "sSearch":       "搜索:",
            "sUrl":          "",
            "sEmptyTable":     "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands":  ",",
            "oPaginate": {
                "sFirst":    "首页",
                "sPrevious": "上页",
                "sNext":     "下页",
                "sLast":     "末页"
            },
            "oAria": {
                "sSortAscending":  ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
    function getIntoRecordInfo(userID) {
        $('#intoRecordInfoModal').load("/AppServer/into_record_info.html?userID=" + userID);
        $('#intoRecordInfoModal').modal('show');
    }
</script>