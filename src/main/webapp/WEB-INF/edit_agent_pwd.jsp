<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/common/head.jsp" %>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">旧密码：</label>
                    <div class="col-sm-6">
                        <input id="old_pwd" name="old_pwd" class="form-control" type="password"
                               validateMessage="{required:'请输入旧密码'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-6">
                        <input type="hidden" id="id" name="id" value="${id}"/>
                        <input id="pwd" name="pwd" class="form-control" type="password"
                               validateMessage="{required:'请输入密码'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-6">
                        <input id="repeat_pwd" name="repeat_pwd" class="form-control" type="password"
                               validateMessage="{required:'请再次输入密码'}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="button" id="submit1">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#submit1").click(function () {
            var pwd = $("#pwd").val().trim();
            var repeat_pwd = $("#repeat_pwd").val().trim();
            var oldPwd = $("#old_pwd").val().trim();

            if (pwd == '' || repeat_pwd == '' || oldPwd == '') {
                alert("密码中存在空值")
            } else if (pwd != repeat_pwd) {
                alert("两次密码不一致");
            } else {
                var id = $("#id").val();
                $.post("/mgr/edit_agent_pwd.cs", {"id": id, "pwd": pwd,"oldPwd":$("#old_pwd").val()}, function (data) {
                    if (data == 'success') {
                        alert("更新成功");
                    } else if(data == 'oldpwd is wrong') {
                        alert('旧密码错误');
                    }
                    else {
                        alert("更新失败");
                    }
                    $.closeDlg();
                });
            }
        });
    });
</script>
</html>