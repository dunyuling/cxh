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
            <cs:form_validate formId="editAddressForm"/>
            <form class="form-horizontal" action="edit.cs" id="editAddressForm" method="POST">

                <div class="form-group">
                    <label class="col-sm-3 control-label">名字：</label>
                    <div class="col-sm-6">
                        <input type="hidden" name="id" value="${temp.id}"/>
                        <input id="name" name="name" class="form-control" validate="{required:true}"
                               value="${temp.name}"
                               validateMessage="{required:'请输入名字'}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-6">
                        <input id="pwd" name="pwd" class="form-control"
                               validateMessage="{required:'请输入密码'}"> 留空则不做修改
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">省：</label>
                    <div class="col-sm-7">
                        <input type="hidden" id="province_" value="${temp.addr}"/>
                        <%--<input type="hidden" id="province_" value="北京市，天津市，上海市"/>--%>
                        <select id="province" name="province" class="form-control" multiple>
                            <option value="北京市">北京市</option>
                            <option value="天津市">天津市</option>
                            <option value="河北省">河北省</option>
                            <option value="山西省">山西省</option>
                            <option value="内蒙古自治区">内蒙古自治区</option>
                            <option value="辽宁省">辽宁省</option>
                            <option value="吉林省">吉林省</option>
                            <option value="黑龙江省">黑龙江省</option>
                            <option value="上海市">上海市</option>
                            <option value="江苏省">江苏省</option>
                            <option value="浙江省">浙江省</option>
                            <option value="安徽省">安徽省</option>
                            <option value="福建省">福建省</option>
                            <option value="江西省">江西省</option>
                            <option value="山东省">山东省</option>
                            <option value="河南省">河南省</option>
                            <option value="湖北省">湖北省</option>
                            <option value="湖南省">湖南省</option>
                            <option value="广东省">广东省</option>
                            <option value="广西壮族自治区">广西壮族自治区</option>
                            <option value="海南省">海南省</option>
                            <option value="重庆市">重庆市</option>
                            <option value="四川省">四川省</option>
                            <option value="贵州省">贵州省</option>
                            <option value="云南省">云南省</option>
                            <option value="西藏自治区">西藏自治区</option>
                            <option value="陕西省">陕西省</option>
                            <option value="甘肃省">甘肃省</option>
                            <option value="青海省">青海省</option>
                            <option value="宁夏回族自治区">宁夏回族自治区</option>
                            <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                            <option value="台湾省">台湾省</option>
                            <option value="香港特别行政区">香港特别行政区</option>
                            <option value="澳门特别行政区">澳门特别行政区</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">电话：</label>
                    <div class="col-sm-6">
                        <input id="phone" name="phone" class="form-control" validate="{required:true}"
                               value="${temp.phone}"
                               validateMessage="{required:'请输入电话'}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">性别：</label>
                    <div class="col-sm-7">
                        <input type="hidden" id="sex_" value="${!temp.sex ? 0 : 1}"/>
                        <select id="sex" name="sex" class="form-control">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
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
    var sex_ = $("#sex_").val();
    $("#sex option[value='" + sex_ + "']").attr("selected", true);

    $.each($("#province_").val().split(","), function (i, val) {
        $("#province option[value='" + val + "']").attr("selected", true);
    })
</script>
</html>