<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<%@taglib prefix="tp" uri="http://www.test.com/jsp/type" %>

<!doctype html>
<head>
    <%@ include file="/common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <script src="/res/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="/res/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/common/translateType.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row row-lg">
        <div class="col-sm-12">
            <cs:toolbar title="会员管理" tableId="tab_member" width="768px" height="420px" menuCode="21" hdMenu="15">
                <button class="btn btn-sm btn-info" type="button" onclick="audit1()"><i class="fa fa-info"></i>&nbsp;审核
                </button>
                <button class="btn btn-sm btn-danger" type="button" onclick="del()"><i class="fa fa-minus"></i> 删除
                </button>
            </cs:toolbar>
            <table id="tab_member"
                   data-toggle="table"
                   data-url="/member/list2.cs"
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
                    <th data-field="mobile">电话</th>
                    <th data-field="type" data-formatter="formatType">保险类型</th>
                    <th data-field="status" data-formatter="formatStatus">状态</th>
                    <th data-field="province">省</th>
                    <th data-field="city">市</th>
                    <th data-field="area">县/区</th>
                    <th data-field="createDate" data-formatter="dateFormatter">创建时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        /*function formatType(type) {
            switch (type) {
                case "IT_0":
                    return "交强险";
                case "IT_1":
                    return "机动车辆损失险";
                case "IT_2":
                    return "第三者责任险";
                case "IT_3":
                    return "盗抢险";
                case "IT_4":
                    return "车上人员责任险";
                case "IT_5":
                    return "车身划痕损失险";
                case "IT_6":
                    return "玻璃单独破碎险";
                case "IT_7":
                    return "自燃损失险";
                case "IT_8":
                    return "发动机特别损失险";
                case "IT_9":
                    return "不计免赔率特约损失险";
            }
        }*/

        function formatStatus(status) {
            switch (status) {
                case "WAITING":
                    return "等待中";
                case "SUCCESS":
                    return "成功";
                case "FAILURE":
                    return "失败";
            }
        }

    </script>
</div>
</body>
</html>