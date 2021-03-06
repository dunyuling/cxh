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
                <cs:toolbar title="代理商管理" tableId="tab_agent" width="768px" height="420px" menuCode="22" hdMenu="15">
                    <button class="btn btn-sm btn-warning" type="button" onclick="edit()">
                        <i class="fa fa-pencil-square-o"></i> 编辑
                    </button>
                    <button class="btn btn-sm btn-danger" type="button" onclick="auditCancel()"><i
                            class="fa fa-minus"></i>取消授权
                    </button>
                    <button class="btn btn-sm btn-success" type="button" onclick="reAudit()"><i class="fa fa-plus"></i>&nbsp;重新授权
                    </button>
                    <button class="btn btn-sm btn-warning" type="button" onclick="recharge()"><i class="fa fa-plus"></i>
                        充值
                    </button>
                    <button class="btn btn-sm btn-warning" type="button" onclick="refund()"><i class="fa fa-plus"></i>
                        退费
                    </button>
                    <button class="btn btn-sm btn-danger" type="button" onclick="auditCancelFee()"><i
                            class="fa fa-minus"></i>
                        取消授权退费
                    </button>
                </cs:toolbar>
            </c:if>
            <table id="tab_agent"
                   data-toggle="table"
                   data-url="/agent/query2.cs?mobile=${mobile}&IDCard=${IDCard}&expire_days=${expire_days}"
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
                    <th data-field="active" data-formatter="auditStatus">授权状态</th>
                    <th data-field="userid">userid</th>
                    <th data-field="mobile">电话</th>
                    <th data-field="IDCard">身份证号</th>
                    <th data-field="corpName">公司名称</th>
                    <th data-field="money">余额(元)</th>
                    <th data-field="licenseImg" data-formatter="imageFormatter">营业执照</th>
                    <th data-field="expireDate" data-formatter="dateFormatter">营业执照过期时间</th>
                </tr>
                </thead>
                <div class="row m-b-sm m-t-sm">
                    <div class="col-md-11">
                        <div class="input-group">
                            手机<input id="mobile" name="mobile" value="${mobile}" type="text" placeholder="请输入手机" class="input-sm">
                            身份证<input id="IDCard" name="IDCard" value="${IDCard}" type="text" placeholder="请输入身份证" class="input-sm">
                            过期天数<input id="expire_days" name="expire_days" value="${expire_days}" type="number" placeholder="请输入过期天数"
                                       class="input-sm">
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
        var mobile = $("#mobile").val();
        var IDCard = $("#IDCard").val();
        var expire_days = $("#expire_days").val();

        if (expire_days < 0) {
            $("#expire_days").val('');
            return;
        }

        if (mobile.trim() != "" || IDCard.trim() != "" || expire_days != "") {
            window.location.href = "/agent/query.cs?mobile=" + mobile + "&IDCard=" + IDCard + "&expire_days=" + expire_days;
        } else {
            window.location.href = "/agent/list.cs";
        }
    });
</script>
</body>
</html>