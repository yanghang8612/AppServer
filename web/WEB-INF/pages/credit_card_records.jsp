<%@ page import="com.huachuang.server.entity.ApplyCreditCard" %>
<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="ibox float-e-margins">
    <div class="ibox-title">
        <h5>申请列表</h5>
    </div>
    <div class="ibox-content">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" id="recordsList">
                <thead>
                <tr>
                    <th>申请时间</th>
                    <th>申请银行</th>
                    <th>申请人/填表人</th>
                    <th>申请人手机号</th>
                    <th>申请人单位</th>
                    <th>申请人账户类型</th>
                    <th>申请人上级</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ApplyCreditCard> records = (List<ApplyCreditCard>) request.getAttribute("records");
                    Map<Long, User> users = (Map<Long, User>) request.getAttribute("users");
                    Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                    for (ApplyCreditCard record : records) {
                        User user = users.get(record.getUserId());
                        UserCertificationInfo certification = certifications.get(record.getUserId());
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        %>
                            <tr onclick="get_record_info(<%=record.getId()%>)">
                                <td><%=fmt.format(record.getApplyTime())%></td>
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
                                        case 8:
                                            %><td>中国民生银行</td><%
                                            break;
                                    }
                                %>
                                <td><%=(record.getApplyUserName().equals(certification.getUserName()) ? record.getApplyUserName() : record.getApplyUserName() + "/" + certification.getUserName())%></td>
                                <td><%=record.getApplyUserPhoneNumber()%></td>
                                <td><%=record.getApplyUserCompany()%></td>
                                <%
                                    User superUser = users.get(user.getSuperiorUserId());
                                    switch (user.getUserType()) {
                                        case 0:
                                            if (user.isVip()) {
                                                %><td>VIP用户<%
                                            }
                                            else {
                                                %><td>普通用户<%
                                            }
                                            break;
                                        case 1:
                                            %><td>一级代理商<%
                                            break;
                                        case 2:
                                            %><td>二级代理商<%
                                            break;
                                    }
                                    if (!record.getApplyUserName().equals(certification.getUserName())) {
                                        %>(代)<%
                                    }
                                %></td>
                                <td><%=(superUser == null) ? "无" : superUser.getUserPhoneNumber()%></td>
                            </tr>
                        <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    $('#recordsList').DataTable({
        "aaSorting": [[ 0, "desc" ]],
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 1,2,3,4,5,6 ] }],
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
    function get_record_info(id) {
//        $('#recordInfoModal').load("/AppServer/credit_card_record_info.html?id=" + id);
//        $('#recordInfoModal').modal(0);
    }
</script>