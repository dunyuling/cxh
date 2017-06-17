<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<head>
    <%@ include file="/WEB-INF/common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <script src="/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <!--           <div class="ibox " style=""> -->
    <!--               <div class="ibox-content"> -->
    <div class="row row-lg">
        <div class="col-sm-12">
            <table id="tab_agentmessage"
                   data-toggle="table"
                   data-url="/am/view.cs?id=${id}"
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
                    <th data-field="times">回访次数</th>
                    <th data-field="situation">回访标题</th>
                    <th data-field="remark">回访备注</th>
                    <th data-field="visitDate" data-formatter="dateFormatter">本次回访时间</th>
                    <th data-field="nextVisitDate" data-formatter="dateFormatter">下次回访时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--               </div> -->
    <!--           </div> -->
</div>
</body>
</html>