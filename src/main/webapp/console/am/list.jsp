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
    <!--           <div class="ibox " style=""> -->
    <!--               <div class="ibox-content"> -->
    <div class="row row-lg">
        <div class="col-sm-12">

            <cs:toolbar title="代理商消息管理" tableId="tab_agentmessage" width="100%" height="100%" menuCode="221" hdMenu="7">
            </cs:toolbar>
            <table id="tab_agentmessage"
                   data-toggle="table"
                   data-url="/am/list2.cs"
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
                    <th data-field="name">名字</th>
                    <th data-field="content">内容</th>
                    <th data-field="times">已发次数</th>
                    <th data-field="visit">是否回访</th>
                    <th data-field="visitDate" data-formatter="dateFormatter">回访时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--               </div> -->
    <!--           </div> -->
</div>
</body>

<script>
    function translateStatus(value) {
        switch (value) {
            case 'APPLYING':
                return '申请中';
            case 'AUTHORED':
                return "已授权";
            case 'EXPIRED':
                return "已过期";
            case 'REFUSED':
                return "被拒绝";
        }
    }
</script>

</html>