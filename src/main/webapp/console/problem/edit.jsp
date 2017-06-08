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
            <cs:form_validate formId="editAddressForm"/>
            <form class="form-horizontal" action="edit.cs" id="editAddressForm" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">标题：</label>
                    <div class="col-sm-6">
                        <input id="id" name="id" value="${temp.id}" class="form-control" type="hidden">
                        <input id="title" name="title" value="${temp.title }" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入标题'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">描述：</label>
                    <div class="col-sm-6">
                        <input id="description" name="description" value="${temp.description}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入描述'}">
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
</html>