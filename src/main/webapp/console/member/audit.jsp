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
            <cs:form_validate formId="editAIForm"/>
            <form class="form-horizontal" <%--action="audit.cs"--%> <%--onsubmit="javascript:return false;"--%> id="editAIForm" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">名字：</label>
                    <div class="col-sm-6">
                        <input type="hidden" id="id" name="id" value="${temp.id}"/>
                        <input id="name" name="name" value="${temp.name}" class="form-control"
                               readonly validate="{required:true}" validateMessage="{required:'请输入名字'}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">省：</label>
                    <div class="col-sm-6">
                        <input id="province" name="province" value="${temp.province}" class="form-control"
                               readonly validate="{required:true}" validateMessage="{required:'请输入省'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">市：</label>
                    <div class="col-sm-6">
                        <input id="city" name="city" value="${temp.city}" class="form-control"
                               readonly validate="{required:true}" validateMessage="{required:'请输入市'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">县/区：</label>
                    <div class="col-sm-6">
                        <input id="area" name="area" value="${temp.area}" class="form-control"
                               readonly validate="{required:true}" validateMessage="{required:'请输入县/区'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">状态：</label>
                    <div class="col-sm-7">
                        <select id="status" name="status" class="form-control">
                            <option value="SUCCESS">成功</option>
                            <%--<option value="FAILURE">失败</option>--%>
                        </select>
                    </div>
                </div>
                <div id="denyReason" class="form-group" style="display: none;">
                    <label class="col-sm-3 control-label">拒绝原因：</label>
                    <div class="col-sm-7">
                        <input name="denyReason" type="text" placeholder="拒绝原因" aria-invalid="false" class="valid">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="submit" id="submit">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var content = $("#content").text();
        if (content != null) {
            $("#container").val(content);
        }
    });

    $("#submit").click(function () {
        $("#submit").attr("disabled", true);
        var id = $("#id").val();
        var status = $("#status").find("option:selected").val();
        var denyReason = $("#denyReason").val();
        $.post("/member/audit.cs", {"id": id, "status": status, "denyReason": denyReason}, function (res) {
            var index = parent.layer.getFrameIndex(window.name);
            $(parent.document.getElementsByTagName('table')).each(function () {
                _id = $(this).attr('id');
                if (_id != '' && _id != undefined) {
                    parent.reflush(_id);
                }
            });
            parent.layer.close(index);
        });
        return true;
    });

    $("#status").change(function () {
        var ps = $("#status").find("option:selected").text();
        if (ps == "成功") {
            $("#denyReason").hide();
        } else {
            $("#denyReason").show();
        }
    });
</script>
</html>