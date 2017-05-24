<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<head>
    <%@ include file="/common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <script src="/res/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="/res/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row row-lg">
        <div class="col-sm-12">
            <cs:toolbar title="客服管理" tableId="tab_customerService" width="768px" height="420px" menuCode="21"
                        hdMenu="1"></cs:toolbar>
            <table id="tab_customerService"
                   data-toggle="table"
                   data-url="/cs/list2.cs"
                   data-method="get"
                   data-click-to-select="true"
                   data-pagination="true"
                   data-data-type="json"
                   data-show-refresh="true"
                   data-show-columns="true"
                   data-show-toggle="true"
                   data-search-on-enter-key="true"
                   data-toolbar="#def_toolbar"
                   data-side-pagination="server"
                   data-query-params="queryParams"
                   data-mobile-responsive="true">
                <thead>
                <tr>
                    <th data-checkbox="true" data-click-to-select="true"></th>
                    <th data-field="id" data-visible="false"></th>
                    <th data-field="account">帐户</th>
                    <th data-field="name">名字</th>
                    <th data-field="phone">电话</th>
                    <th data-field="sex" data-formatter="sexFormatter">性别</th>
                    <th data-field="addr">管理省</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script>
    function sexFormatter(value) {
        return value == 0 ? '男' : '女';
    }
</script>
</body>
</html>