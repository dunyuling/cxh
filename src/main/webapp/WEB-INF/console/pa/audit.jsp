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
            <cs:form_validate formId="editAIForm"/>
            <form class="form-horizontal" action="audit.cs" id="editAIForm" method="POST">
                <div class="form-group">
                    <label class="col-sm-3 control-label">名字：</label>
                    <div class="col-sm-6">
                        <input type="hidden" id="id" name="id" value="${temp.id}"/>
                        <input id="name" name="name" value="${temp.name}" class="form-control"
                               validate="{required:true}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">省：</label>
                    <div class="col-sm-6">
                        <input id="province" name="province" value="${temp.province}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">市：</label>
                    <div class="col-sm-6">
                        <input id="city" name="city" value="${temp.city}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">县/区：</label>
                    <div class="col-sm-6">
                        <input id="area" name="area" value="${temp.area}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">电话：</label>
                    <div class="col-sm-6">
                        <input id="mobile" name="mobile" value="${temp.mobile}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">身份证号：</label>
                    <div class="col-sm-6">
                        <input id="IDCard" name="IDCard" value="${temp.IDCard}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">公司名称：</label>
                    <div class="col-sm-6">
                        <input id="corpName" name="corpName" value="${temp.corpName}" class="form-control"
                               readonly validate="{required:true}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">营业执照：</label>
                    <div class="col-sm-6">
                        <a href="${temp.licenseImg}" target="_blank"><img src="${temp.licenseImg}" width="40px"
                                                                          height="40px"/></a>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">营业执照过期时间：</label>
                    <div class="col-sm-6">
                        <fmt:formatDate value="${temp.expireDate}" pattern="yyyy-MM-dd HH:mm"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">审核类型：</label>
                    <div class="col-sm-7">
                        <select id="proxyStatus" name="proxyStatus" class="form-control">
                            <option value="AUTHORED">授权</option>
                            <%--<option value="REFUSED">拒绝</option>--%>
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

    $("#proxyStatus").change(function () {
        var ps = $("#proxyStatus").find("option:selected").text();
        if (ps == "授权") {
            $("#denyReason").hide();
        } else {
            $("#denyReason").show();
        }
    });
</script>
</html>