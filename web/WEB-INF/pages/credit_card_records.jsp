<%@ page import="java.util.List" %>
<%@ page import="com.huachuang.server.entity.ApplyCreditCard" %>
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
                                <th>申请银行</th>
                                <th>申请人姓名</th>
                                <th>申请人手机号</th>
                                <th>申请人单位</th>
                                <th>申请时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ApplyCreditCard> records = (List<ApplyCreditCard>) request.getAttribute("records");
                                for (ApplyCreditCard record : records) {
                                    %>
                                        <tr onclick="get_record_info(<%=record.getId()%>)">
                                            <%
                                                switch (record.getApplyBank()) {
                                                    case 0:
                                                        %><td>兴业银行</td><%
                                                        break;
                                                    case 1:
                                                        %><td>中信银行</td><%
                                                        break;
                                                    case 2:
                                                        %><td>浦发银行</td><%
                                                        break;
                                                    case 3:
                                                        %><td>上海银行</td><%
                                                        break;
                                                    case 4:
                                                        %><td>交通银行</td><%
                                                        break;
                                                    case 5:
                                                        %><td>招商银行</td><%
                                                        break;
                                                    case 6:
                                                        %><td>中国光大银行</td><%
                                                        break;
                                                    case 7:
                                                        %><td>平安银行</td><%
                                                        break;
                                                }
                                            %>
                                            <td><%=record.getApplyUserName()%></td>
                                            <td><%=record.getApplyUserPhoneNumber()%></td>
                                            <td><%=record.getApplyUserCompany()%></td>
                                            <td><%=record.getApplyTime()%></td>
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
//        $('#recordInfoModal').load("/AppServer/credit_card_record_info.html?id=" + id);
//        $('#recordInfoModal').modal(0);
    }
</script>