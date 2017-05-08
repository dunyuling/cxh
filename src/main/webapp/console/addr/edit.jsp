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
                    <label class="col-sm-3 control-label">省：</label>
                    <div class="col-sm-6">
                        <input id="id" name="id" value="${address.id }" class="form-control" type="hidden">
                        <input id="province" name="province" value="${address.province }" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入省'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">市：</label>
                    <div class="col-sm-6">
                        <input id="city" name="city" value="${address.city }" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入市'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">县/区：</label>
                    <div class="col-sm-6">
                        <input id="area" name="area" value="${address.area }" class="form-control"
                               validate="{required:true}" validateMessage="{required:'请输入县/区'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">每次费用(单位元)：</label>
                    <div class="col-sm-6">
                        <input id="amount" name="amount" value="${amount}" type="number"
                               class="form-control" validate="{required:true}" validateMessage="{required:'请输入费用'}">
                        费用在（20-500）元之间
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3 pull-left">
                        <button class="btn btn-default btn-dlg-close">取消</button>
                        <button class="btn btn-primary" id="submit" type="submit">提交</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#submit").click(function () {
        var amount = $("#amount").val();
        if (amount >= 20 && amount <= 500) {
            return true;
        } else {
            alert("费用须在20-500之间");
            return false;
        }
    });
</script>
</body>
</html>