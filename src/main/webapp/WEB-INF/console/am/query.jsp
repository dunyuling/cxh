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
                <cs:toolbar title="代理商消息管理" tableId="tab_agentmessage" width="100%" height="100%" menuCode="26"
                            hdMenu="15">
                    <button class="btn btn-sm btn-success" type="button" onclick="view()">
                        <input type="hidden" name="title1" value="回访记录"/>
                        <i class="fa fa-plus"></i>&nbsp;查看回访记录
                    </button>
                </cs:toolbar>
            </c:if>
            <c:if test="${role != null && role == '客服'}">
                <cs:toolbar title="代理商消息管理" tableId="tab_agentmessage" width="100%" height="100%" menuCode="26"
                            hdMenu="15">
                    <button class="btn btn-sm btn-success" type="button" onclick="view()">
                        <input type="hidden" name="title1" value="回访记录"/>
                        <i class="fa fa-plus"></i>&nbsp;查看回访记录
                    </button>
                </cs:toolbar>
            </c:if>

            <input hidden id="agentId" value="${agentId}"/>
            <table id="tab_agentmessage"
                   data-toggle="table"
                   data-url="/am/query2.cs?name=${name}"
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
                    <th data-field="visit" data-formatter="visitFormatter">是否回访</th>
                    <th data-field="visitDate" data-formatter="dateFormatter">回访时间</th>
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

    function visitFormatter(value) {
        return value == false ? "未回访" : "已回访";
    }

    $("#search").click(function () {
        var name = $("#name").val();

        if (name.trim() != "") {
            window.location.href = "/am/query.cs?agentId=0&name=" + name;
        } else {
            var agentId = $("#agentId").val();
            window.location.href = "/am/list.cs?agentId=" + agentId;
        }
    });
</script>
</body>
</html>