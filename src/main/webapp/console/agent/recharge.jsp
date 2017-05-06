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
            <cs:form_validate formId="rechargeAgentForm"/>
            <form class="form-horizontal" action="recharge.cs" id="rechargeAgentForm" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">金额：</label>
                    <div class="col-sm-6">
                        <input id="id" name="id" value="${agent.id }" class="form-control" type="hidden">
                        <input id="amount" name="amount" value="" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入金额'}">
                        单位元
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