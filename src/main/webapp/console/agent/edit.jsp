<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <cs:form_validate formId="editAgentForm"/>
            <form class="form-horizontal" action="edit.cs" id="editAgentForm" method="POST"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-3 control-label">名字：</label>
                    <div class="col-sm-6">
                        <input id="id" name="id" value="${agent.id }" class="form-control" type="hidden">
                        <input id="name" name="name" value="${agent.name }" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入名字'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">身份证号：</label>
                    <div class="col-sm-6">
                        <input id="IDCard" name="IDCard" value="${agent.IDCard}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入身份证号'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号：</label>
                    <div class="col-sm-6">
                        <input id="mobile" name="mobile" value="${agent.mobile}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入手机号'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">公司名称：</label>
                    <div class="col-sm-6">
                        <input id="corpName" name="corpName" value="${agent.corpName}" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入公司名称'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">营业执照：</label>
                    <div class="col-sm-6">
                        <input id="img" name="img" type="file" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">营业执照过期时间</label>
                    <div class="col-sm-6">
                        <fmt:formatDate pattern="yyyyMMdd" type="date"
                                        value="${agent.expireDate}" var="expireDate"/>
                        <input id="expireDate" name="expireDate"
                               value="${expireDate}"
                               class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入过期时间'}"
                               placeholder="例：20170101">
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