<%@ page import="com.huachuang.server.entity.User" %>
<%@ page import="com.huachuang.server.entity.UserCertificationInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="ibox float-e-margins">
    <div class="ibox-title">
        <h5>用户列表</h5>
    </div>
    <div class="ibox-content">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" id="userList">
                <thead>
                     <tr>
                         <th>用户等级</th>
                         <th>手机号码</th>
                         <th>姓名</th>
                         <th>用户类型</th>
                         <th>注册日期</th>
                         <th>上次登录时间</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    Map<Long, UserCertificationInfo> certifications = (Map<Long, UserCertificationInfo>) request.getAttribute("certifications");
                    for (User user : users) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        %>
                        <tr onclick="get_user_info(<%=user.getUserPhoneNumber()%>)">
                            <td><%=(user.getUserLevel())%>级</td>
                            <td><%=user.getUserPhoneNumber()%></td>
                            <td><%=(user.isCertificationState() ? certifications.get(user.getUserId()).getUserName() : "<未实名认证>")%>
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
    </div>
</div>

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
    $('#userList').DataTable({
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