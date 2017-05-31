<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row header-row">
        <%
            User agent = (User) request.getAttribute("agent");
            UserCertificationInfo certificationInfo = (UserCertificationInfo) request.getAttribute("CertificationInfo");
            List<User> users = (List<User>) request.getAttribute("users");
        %>
        <div class="col-lg-2 col-md-2">
            <img src="<%=(agent.isHeaderState()?"/AppServer/header/"+agent.getUserId()+".jpg":"/AppServer/imgs/default_header_image.jpg")%>" class="img-circle img-responsive center-block">
        </div>
        <div class="col-lg-2">
            <h3><%=agent.getUserPhoneNumber()%></h3>
            <h4><%=(agent.isCertificationState())?certificationInfo.getUserName():"<未实名认证>"%></h4>
            <p>级别:<%=(agent.getUserType()==1)?"一":(agent.getUserType()==2)?"二":"三"%>级代理商</p>
            <p>邀请码:<%=agent.getInvitationCode()%></p>
        </div>
        <div class="col-lg-offset-6 col-lg-2">
            <br/>
            <a href="javascript:void(0)" onclick="delete_agent(<%=agent.getUserId()%>)"><i class="fa fa-times-circle-o"></i> 删除代理商</a>
            <%
                if (agent.getUserType() == 1) {
                    %>
                        <br/>
                        <a href="javascript:void(0)" onclick="add_new_agent(<%=agent.getUserPhoneNumber()%>,2)"><i class="fa fa-user-plus"></i> 添加二级代理商</a>
                        <br/>
                        <a href="javascript:void(0)" onclick="add_new_agent(<%=agent.getUserPhoneNumber()%>,3)"><i class="fa fa-user-plus"></i> 添加三级代理商</a>
                    <%
                }
                if (agent.getUserType() == 2) {
                    %>
                        <br/>
                        <a href="javascript:void(0)" onclick="add_new_agent(<%=agent.getUserPhoneNumber()%>,3)"><i class="fa fa-user-plus"></i> 添加三级代理商</a>
                    <%
                }
            %>
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
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (User user : users) {
                                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    %>
                                        <tr onclick="get_user_info(<%=user.getUserPhoneNumber()%>)">
                                            <td><%=user.getUserPhoneNumber()%></td>
                                            <td><%=(user.isVip())?"VIP用户":"普通用户"%></td>
                                            <td><%=fmt.format(user.getRegisterTime())%></td>
                                            <td><%=(user.getLastLoginTime() == null ? "未登录" : fmt.format(user.getLastLoginTime()))%></td>
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
<!-- Modal -->
<div class="modal fade" id="newAgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加一级代理商</h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group" id="inputPhoneNumberGroup">
                        <label>手机号</label>
                        <input class="form-control" id="inputPhoneNumber">
                    </div>
                    <div class="form-group" id="inputPasswordGroup">
                        <label>密码</label>
                        <input type="password" class="form-control" id="inputPassword">
                    </div>
                    <div class="form-group" id="inputConfirmPasswordGroup">
                        <label>确认密码</label>
                        <input type="password" class="form-control" id="inputConfirmPassword">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="add-agent">提交</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
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
    function delete_agent(user_id) {
        if (confirm('确认删除该代理商吗？')) {
            $.ajax({
                type: "POST",
                url: "/AppServer/UserManager/DeleteUser",
                data: {userID:user_id},
                dataType: "json",
                success: function (data) {
                    if (data.Status == 'true') {
                        window.location.reload();
                    }
                    else {
                        toastr.info(data.Info);
                    }
                }
            });
        }
    }
    function add_new_agent(phone_number, type) {
        currentAgentPhoneNumber = phone_number;
        currentType = type;
        if (type == 1) {
            $('#newAgentModal .modal-title').text('添加一级代理商');
        }
        else if (type == 2) {
            $('#newAgentModal .modal-title').text('添加二级代理商');
        }
        else {
            $('#newAgentModal .modal-title').text('添加三级代理商');
        }
        $('#newAgentModal').modal(0);
    }
    $('#add-agent').click(function () {
        if($('#inputPhoneNumber').val().length != 11){
            toastr.error('请输入正确的手机号');
            return;
        }

        if($('#inputPassword').val().length<6){
            toastr.error('密码最少包含6个字符');
            return;
        }

        if($('#inputPassword').val()!=$('#inputConfirmPassword').val()){
            toastr.error('两次输入密码不一致');
            return;
        }

        $.ajax({
            type: "POST",
            url: "/AppServer/UserManager/AddAgent",
            data: {superiorPhoneNumber:currentAgentPhoneNumber,phoneNumber:$("#inputPhoneNumber").val(),password:$("#inputPassword").val(),type:currentType},
            dataType: "json",
            success: function (data) {
                if (data.Status == 'true') {
                    window.location.reload();
                }
                else {
                    toastr.info(data.Info);
                }
            }
        });
    });
//    function get_user_info(phone_number) {
//        $('#userInfoModal').modal();
//    }
</script>