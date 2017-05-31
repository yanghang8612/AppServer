<%@ page import="java.util.List" %>
<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    申请列表
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                            <tr>
                                <th>申请人姓名</th>
                                <th>申请人手机号</th>
                                <th>申请人账户类型</th>
                                <th>申请人上级</th>
                                <th>申请金额</th>
                                <th>申请时间</th>
                                <th>申请状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ApplyLoan> records = (List<ApplyLoan>) request.getAttribute("records");
                                Map<Long, User> users = (Map<Long, User>) request.getAttribute("users");
                                for (ApplyLoan record : records) {
                                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    %>
                                        <tr onclick="get_record_info(<%=record.getId()%>)">
                                            <td><%=record.getBorrowerName()%></td>
                                            <td><%=record.getBorrowerPhoneNumber()%></td>
                                            <%
                                                User user = users.get(record.getUserId());
                                                User superUser = users.get(user.getSuperiorUserId());
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
                                                    case 3:
                                                        %><td>三级代理商</td><%
                                                        break;
                                                }
                                            %>
                                            <td><%=(superUser == null) ? "无" : superUser.getUserPhoneNumber()%></td>
                                            <td><%=record.getBorrowerAmount()%></td>
                                            <td><%=fmt.format(record.getApplyTime())%></td>
                                            <%
                                                switch (record.getApplyState()) {
                                                    case 0:
                                                        %><td>未处理</td><%
                                                        break;
                                                    case 1:
                                                        %><td>未联系上申请人</td><%
                                                        break;
                                                    case 2:
                                                        %><td>已接受申请</td><%
                                                        break;
                                                    case 3:
                                                        %><td>已放款</td><%
                                                        break;
                                                    case 4:
                                                        %><td>已拒绝</td><%
                                                }
                                            %>

                                        </tr>
                                    <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true,
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
    });
    function get_record_info(id) {
        $('#recordInfoModal').load("/AppServer/loan_record_info.html?id=" + id);
        $('#recordInfoModal').modal(0);
    }
</script>