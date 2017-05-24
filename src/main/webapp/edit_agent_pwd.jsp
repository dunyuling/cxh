<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/common/head.jsp" %>
</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-6">
                        <input type="hidden" id="id" name="id" value="${id}"/>
                        <input id="pwd" name="pwd" class="form-control"
                               validateMessage="{required:'请输入密码'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-6">
                        <input id="repeat_pwd" name="repeat_pwd" class="form-control"
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
            if (pwd != "" && pwd != repeat_pwd) {
                alert("两次密码不一致");
            } else {
                var id = $("#id").val();
                $.post("/mgr/edit_agent_pwd.cs", {"id": id, "pwd": pwd}, function (data) {
                    if (data == 'success') {
                        alert("更新成功");
                    } else {
                        alert("更新失败");
                    }
                    $.closeDlg();
                });
            }
        });
    });
</script>
</html>