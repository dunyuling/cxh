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
            <cs:toolbar title="反馈问题管理" tableId="tab_problem" width="768px" height="420px" menuCode="183"
                        hdMenu="2">
                <button class="btn btn-sm btn-warning" type="button" onclick="edit()"><i
                        class="fa fa-pencil-square-o"></i> 编辑
                </button>
                <button class="btn btn-sm btn-danger" type="button" onclick="del()"><i class="fa fa-minus"></i> 删除
                </button>
                <button class="btn btn-sm btn-warning" type="button" onclick="solve()"><i
                        class="fa fa-pencil-square-o"></i>解决
                </button>
            </cs:toolbar>

            <table id="tab_problem"
                   data-toggle="table"
                   data-url="/problem/admin_list2.cs"
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
                    <th data-field="title">标题</th>
                    <th data-field="description">描述</th>
                    <th data-field="cs_name">客服</th>
                    <th data-field="a_name">代理商</th>
                    <th data-field="solve" data-formatter="solveFormatter">是否解决</th>
                    <th data-field="solution">解决方案</th>
                </tr>
                </thead>
                <div class="row m-b-sm m-t-sm">
                    <div class="col-md-11">
                        <div class="input-group">
                            代理商名字<input id="agent_name" name="agent_name" type="text" placeholder="请输入代理商名字"
                                        class="input-sm">
                            客服名字<input id="cs_name" name="cs_name" type="text" placeholder="请输入客服名字" class="input-sm">
                            <span><button id="search" type="button"
                                          class="btn btn-sm btn-primary"> 搜索</button></span>
                        </div>
                    </div>
                </div>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#search").click(function () {
        var agent_name = $("#agent_name").val();
        var cs_name = $("#cs_name").val();

        if (agent_name.trim() != "" || cs_name.trim() != "") {
            window.location.href = "/problem/admin_query.cs?agent_name=" + agent_name + "&cs_name=" + cs_name;
        }
    });
</script>
</body>
</html>