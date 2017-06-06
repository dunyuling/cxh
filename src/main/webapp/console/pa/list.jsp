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
            <c:if test="${role != null && role != '客服'}">
                <cs:toolbar title="代理地区管理" tableId="tab_proxyaddress" width="100%" height="100%" menuCode="23"
                            hdMenu="7">
                    <button class="btn btn-sm btn-info" type="button" onclick="audit()"><i class="fa fa-info"></i>&nbsp;审核
                    </button>
                </cs:toolbar>
            </c:if>
            <table id="tab_proxyaddress"
                   data-toggle="table"
                   data-url="/pa/list2.cs"
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
                    <th data-field="province">省</th>
                    <th data-field="city">市</th>
                    <th data-field="area">区</th>
                    <th data-field="proxyStatus" data-formatter="translateStatus">审核状态</th>
                    <th data-field="createDate" data-formatter="dateFormatter">申请时间</th>
                    <th data-field="updateDate" data-formatter="dateFormatter">审核时间</th>
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