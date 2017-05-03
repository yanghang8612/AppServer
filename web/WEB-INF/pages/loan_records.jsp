<%@ page import="java.util.List" %>
<%@ page import="com.huachuang.server.entity.ApplyLoan" %>
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
                                <th>申请金额</th>
                                <th>申请时间</th>
                                <th>申请状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ApplyLoan> records = (List<ApplyLoan>) request.getAttribute("records");
                                for (ApplyLoan record : records) {
                                    %>
                                        <tr onclick="get_record_info(<%=record.getId()%>)">
                                            <td><%=record.getBorrowerName()%></td>
                                            <td><%=record.getBorrowerPhoneNumber()%></td>
                                            <td><%=record.getBorrowerAmount()%></td>
                                            <td><%=record.getApplyTime()%></td>
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
            responsive: true
        });
    });
    function get_record_info(id) {
        $('#recordInfoModal').load("/AppServer/loan_record_info.html?id=" + id);
        $('#recordInfoModal').modal(0);
    }
</script>