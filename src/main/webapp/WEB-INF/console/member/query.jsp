<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<%@taglib prefix="tp" uri="http://www.test.com/jsp/type" %>

<!doctype html>
<head>
    <%@ include file="/WEB-INF/common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <script src="/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/common/translateType.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row row-lg">
        <div class="col-sm-12">
            <cs:toolbar title="会员管理" tableId="tab_member" width="768px" height="420px" menuCode="24" hdMenu="15">
                <button class="btn btn-sm btn-info" type="button" onclick="auditMember()"><i class="fa fa-info"></i>&nbsp;审核
                </button>
                <button class="btn btn-sm btn-danger" type="button" onclick="error()"><i class="fa fa-minus"></i> 无效
                </button>
            </cs:toolbar>
            <table id="tab_member"
                   data-toggle="table"
                   data-url="/member/query2.cs?name=${name}&mobile=${mobile}"
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
                    <th data-field="tma_name">操作人</th>
                </tr>
                </thead>
                <div class="row m-b-sm m-t-sm">
                    <div class="col-md-11">
                        <div class="input-group">
                            名字<input id="name" name="name" value="${name}" type="text" placeholder="请输入名字" class="input-sm">
                            手机<input id="mobile" name="mobile" value="${mobile}" type="text" placeholder="请输入手机" class="input-sm">
                            <span><button id="search" type="button" class="btn btn-sm btn-primary"> 搜索</button></span>
                        </div>
                    </div>
                </div>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        function formatStatus(status) {
            switch (status) {
                case "WAITING":
                    return "等待中";
                case "SUCCESS":
                    return "成功";
                case "FAILURE":
                    return "失败";
                case "ERROR":
                    return "错误";
            }
        }

        $("#search").click(function () {
            var mobile = $("#mobile").val();
            var name = $("#name").val();

            if (mobile.trim() != "" || name.trim() != "") {
                window.location.href = "/member/query.cs?mobile=" + mobile + "&name=" + name;
            } else {
                window.location.href = "/member/list.cs";
            }
        });
    </script>
</div>
</body>
</html>