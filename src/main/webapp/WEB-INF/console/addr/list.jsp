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
            <c:if test="${role != null && role != '客服'}">
                <cs:toolbar title="地区费用管理" tableId="tab_addr" width="768px" height="420px" menuCode="21" hdMenu="15">
                    <button class="btn btn-sm btn-warning" type="button" onclick="edit()"><i
                            class="fa fa-pencil-square-o"></i> 编辑
                    </button>
                </cs:toolbar>
            </c:if>
            <table id="tab_addr"
                   data-toggle="table"
                   data-url="/addr/list2.cs"
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
                    <th data-field="province">省</th>
                    <th data-field="city">市</th>
                    <th data-field="area">县/区</th>
                    <th data-field="amount">费用(元)</th>
                </tr>
                </thead>
                <div class="row m-b-sm m-t-sm">
                    <div class="col-md-11">
                        <div class="input-group">
                            省<input id="province" name="province" type="text" placeholder="请输入省" class="input-sm">
                            市<input id="city" name="city" type="text" placeholder="请输入市" class="input-sm">
                            县/区<input id="area" name="area" type="text" placeholder="请输入县/区" class="input-sm">
                            <span><button id="search" type="button" class="btn btn-sm btn-primary"> 搜索</button></span>
                        </div>
                    </div>
                </div>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#search").click(function () {
        var province = $("#province").val();
        var city = $("#city").val();
        var area = $("#area").val();

        if (province.trim() != "" || city.trim() != "" || area.trim() != "") {
            window.location.href = "/addr/query.cs?province=" + province + "&city=" + city + "&area=" + area;
        }
    });
</script>
</body>
</html>