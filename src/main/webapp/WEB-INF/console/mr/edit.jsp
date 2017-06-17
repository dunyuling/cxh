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
            <cs:form_validate formId="editMRForm"/>
            <form class="form-horizontal" action="edit.cs" id="editMRForm" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">重发间隔：</label>
                    <div class="col-sm-6">
                        <input id="gap" name="gap" value="${mr.gap}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入重发时间间隔（单位秒）'}">单位秒
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">总发次数：</label>
                    <div class="col-sm-6">
                        <input id="totalTimes" name="totalTimes" value="${mr.totalTimes}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入总共发送的次数'}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" type="submit">提交</button>
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
</script>
</html>