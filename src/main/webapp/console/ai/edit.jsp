<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/common/head.jsp"%>
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="editAIForm"/>
                  <form class="form-horizontal" action="edit.cs" id="editAIForm" method="POST">
                      <div class="form-group">
                          <label class="col-sm-3 control-label">access_token：</label>
                          <div class="col-sm-6">
                              <input id="access_token" name="access_token" value="${ai.access_token}"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入access_token'}" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">corpID：</label>
                          <div class="col-sm-6">
                              <input id="corpID" name="corpID" value="${ai.corpID}"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入公司ID'}" >
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-sm-3 control-label">secret：</label>
                          <div class="col-sm-6">
                              <input id="secret" name="secret" value="${ai.secret}"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入密码'}" >
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