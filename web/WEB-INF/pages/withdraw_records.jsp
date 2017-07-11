<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.huachuang.server.entity.UserWithdraw" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="ibox float-e-margins">
    <div class="ibox-title">
        <h5>记录列表</h5>
    </div>
    <div class="ibox-content">
        <div class="table-responsive">
            <table width="100%" class="table table-striped table-bordered table-hover" id="recordsList">
                <thead>
                    <tr>
                        <th>提交时间</th>
                        <th>手机号</th>
                        <th>姓名</th>
                        <th>账户类型</th>
                        <th>提现金额</th>
                        <th>状态</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<UserWithdraw> records = (List<UserWithdraw>) request.getAttribute("records");
                        Map<Long, User> users = (Map<Long, User>) request.getAttribute("users");
                        Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                        for (UserWithdraw record : records) {
                            User user = users.get(record.getUserId());
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            %>
                                <tr onclick="getRecordInfo(<%=record.getId()%>)">
                                    <td><%=fmt.format(record.getCommitTime())%></td>
                                    <td><%=user.getUserPhoneNumber()%></td>
                                    <td><%=(user.isCertificationState() ? certifications.get(user.getUserId()).getUserName() : "<未实名认证>")%></td>
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
                                    <td><%=record.getAmount()%></td>
                                    <%
                                        switch (record.getState()) {
                                            case 0:
                                                %><td>未处理</td><%
                                                break;
                                            case 1:
                                                %><td>已提现</td><%
                                                break;
                                            case 2:
                                                %><td>提现异常</td><%
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
<div class="modal inmodal fade" id="withdrawRecordInfoModal" tabindex="-1" role="dialog"  aria-hidden="true">
</div>
<script>
    $('#recordsList').DataTable({
        "aaSorting": [[ 0, "desc" ]],
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 1,2,3,5 ] }],
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
    function getRecordInfo(id) {
        $('#withdrawRecordInfoModal').load("/AppServer/withdraw_record_info.html?id=" + id);
        $('#withdrawRecordInfoModal').modal('show');
    }
</script>