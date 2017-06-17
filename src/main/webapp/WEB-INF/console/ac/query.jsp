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
    <div class="row row-lg">
        <div class="col-sm-12">
            <cs:toolbar title="取消授权记录管理" tableId="tab_auditcancel" width="100%" height="100%" menuCode="291"
                        hdMenu="15">
            </cs:toolbar>
            <input hidden id="agentId" value="${agentId}"/>
            <table id="tab_auditcancel"
                   data-toggle="table"
                   data-url="/audit_cancel/query2.cs?name=${name}"
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
                    <th data-field="createDate" data-formatter="dateFormatter">操作时间</th>
                </tr>
                </thead>
                <c:if test="${agentId == 0}">
                    <div class="row m-b-sm m-t-sm">
                        <div class="col-md-11">
                            <div class="input-group">
                                名字<input id="name" name="name" value="${name}" type="text" placeholder="请输入名字" class="input-sm">
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
            window.location.href = "/audit_cancel/query.cs?agentId=0&name=" + name;
        } else {
            var agentId = $("#agentId").val();
            window.location.href = "/audit_cancel/list.cs?agentId=" + agentId;
        }
    });
</script>
</body>
</html>