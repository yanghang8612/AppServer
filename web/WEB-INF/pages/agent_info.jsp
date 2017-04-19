<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row header-row">
        <%
            User agent = (User) request.getAttribute("agent");
            UserCertificationInfo certificationInfo = (UserCertificationInfo) request.getAttribute("CertificationInfo");
            List<User> users = (List<User>) request.getAttribute("users");
        %>
        <div class="col-lg-2 col-md-2">
            <img src="<%=(agent.isHeaderState()?"/header/"+agent.getUserId()+".jpg":"/imgs/default_header_image.jpg")%>" class="img-circle img-responsive center-block">
        </div>
        <div class="col-lg-2">
            <h3><%=agent.getUserPhoneNumber()%></h3>
            <h4><%=(agent.isCertificationState())?certificationInfo.getUserName():"<未实名认证>"%></h4>
            <p>级别:<%=(agent.getUserType()==1)?"一":(agent.getUserType()==2)?"二":"三"%>级代理商</p>
            <p>邀请码:<%=agent.getInvitationCode()%></p>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    用户列表
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                            <tr>
                                <th>手机号码</th>
                                <th>用户类型</th>
                                <th>注册日期</th>
                                <th>上次登录时间</th>
                                <th>在线状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (User user : users) {
                                    %>
                                        <tr onclick="get_user_info(<%=user.getUserPhoneNumber()%>)">
                                            <td><%=user.getUserPhoneNumber()%></td>
                                            <td><%=(user.isVip())?"VIP用户":"普通用户"%></td>
                                            <td><%=user.getRegisterTime()%></td>
                                            <td><%=user.getLastLoginTime()%></td>
                                            <td>在线</td>
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
<!-- Modal -->
<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        个人基本信息
                    </div>
                    <div class="panel-body">
                        <div class="container">
                            <img src="/imgs/default_header_image.jpg" width="64px" height="64px" class="img-circle">
                            <h3>18511838501</h3>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        实名认证信息
                    </div>
                    <div class="panel-body">

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        结算卡信息
                    </div>
                    <div class="panel-body">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
//    function get_user_info(phone_number) {
//        $('#userInfoModal').modal();
//    }
</script>