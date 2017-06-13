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

            <cs:toolbar title="退费记录管理" tableId="tab_refund" width="100%" height="100%" menuCode="28" hdMenu="15">
            </cs:toolbar>
            <table id="tab_refund"
                   data-toggle="table"
                   data-url="/refund/query2.cs?name=${name}"
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
                    <th data-field="ag_name">代理商</th>
                    <th data-field="amount">金额(元)</th>
                    <th data-field="ad_name">操作人</th>
                    <th data-field="createDate" data-formatter="dateFormatter">退费时间</th>
                </tr>
                </thead>
                <c:if test="${agentId == 0}">
                    <div class="row m-b-sm m-t-sm">
                        <div class="col-md-11">
                            <div class="input-group">
                                名字<input id="name" name="name" type="text" placeholder="请输入名字" class="input-sm">
                                <span><button id="search" type="button"
                                              class="btn btn-sm btn-primary"> 搜索</button></span>
                            </div>
                        </div>
                    </div>
                </c:if>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#search").click(function () {
        var name = $("#name").val();

        if (name.trim() != "") {
            window.location.href = "/refund/query.cs?agentId=0&name=" + name;
        }
    });
</script>
</body>
</html>