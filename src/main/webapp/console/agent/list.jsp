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
            <c:if test="${role != null && role != '客服'}">
                <cs:toolbar title="代理商管理" tableId="tab_agent" width="768px" height="420px" menuCode="22" hdMenu="3">
                    <button class="btn btn-sm btn-warning" type="button" onclick="recharge()"><i class="fa fa-plus"></i>
                        充值
                    </button>
                    <button class="btn btn-sm btn-warning" type="button" onclick="refund()"><i class="fa fa-plus"></i>
                        退费
                    </button>
                </cs:toolbar>
            </c:if>
            <table id="tab_agent"
                   data-toggle="table"
                   data-url="/agent/list2.cs"
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
                    <th data-field="userid">userid</th>
                    <th data-field="mobile">电话</th>
                    <th data-field="IDCard">身份证号</th>
                    <th data-field="corpName">公司名称</th>
                    <th data-field="money">余额(元)</th>
                    <th data-field="licenseImg" data-formatter="imageFormatter">营业执照</th>
                    <th data-field="expireDate" data-formatter="dateFormatter">营业执照过期时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>