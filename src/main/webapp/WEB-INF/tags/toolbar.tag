<%@ tag pageEncoding="utf-8" %>

<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="tableId" type="java.lang.String" required="true" %>
<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="params" type="java.lang.String" required="false" %>
<%@ attribute name="width" type="java.lang.String" required="false" %>
<!-- 打开 -->
<%@ attribute name="height" type="java.lang.String" required="false" %>
<!-- 指定检索url -->
<%@ attribute name="hdMenu" type="java.lang.Integer" required="false" %>
<!-- 控制是否显示指定菜单 -->

<%@ attribute name="menuCode" type="java.lang.String" required="false" %>
<%-- <%@ attribute name="action" type="java.lang.Integer" required="false" %> --%>

<div id="def_toolbar" role="group">
    <% if (hdMenu == null || (hdMenu & 1) == 0) { %>
    <p:permission menuCode="${menuCode}" action="1">
        <button class="btn btn-sm btn-info" type="button" onclick="view()"><i class="fa fa-info"></i>&nbsp;查看</button>
    </p:permission>
    <% } %>
    <% if (hdMenu == null || (hdMenu & 2) == 0) { %>
    <p:permission menuCode="${menuCode}" action="2">
        <button class="btn btn-sm btn-success" type="button" onclick="add()"><i class="fa fa-plus"></i>&nbsp;新增</button>
    </p:permission>
    <% } %>
    <% if (hdMenu == null || (hdMenu & 4) == 0) { %>
    <p:permission menuCode="${menuCode}" action="4">
        <button class="btn btn-sm btn-warning" type="button" onclick="edit()"><i class="fa fa-pencil-square-o"></i> 编辑
        </button>
    </p:permission>
    <% } %>
    <% if (hdMenu == null || (hdMenu & 8) == 0) { %>
    <p:permission menuCode="${menuCode}" action="8">
        <button class="btn btn-sm btn-danger" type="button" onclick="del()"><i class="fa fa-minus"></i> 删除</button>
    </p:permission>
    <% } %>
    <jsp:doBody/>
</div>

<script>

    // 预览事件
    function view() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        $.openDlg({
            url: 'transfer.cs?action=view&id=' + data.id,
            title: '查看' + $("input[name=title1]").val(),
            width: '${width}',
            height: '${height}'
        });
    }

    // 新增事件
    function add() {
        $.openDlg({
            url: 'transfer.cs?action=add',
            title: '新增' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    // 删除事件
    function del() {
        var ids = [];
        var param_id = '';

        $($('#' + "${tableId}").bootstrapTable('getSelections')).each(function () {
            param_id += this.id + ',';
        });

        if (param_id.length == 0) return;
        param_id = param_id.substr(0, param_id.length - 1);

        $.confirm("确定删除吗？ ", function () {
            $.post('del.cs?id=' + param_id,
                function (result) {
                    window.location.reload();
                }
            );
        });
    }

    // 预览事件
    function edit() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        $.openDlg({
            url: 'transfer.cs?action=edit&id=' + data.id,
            title: '编辑' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    function auditProxyAddress() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        if (data.proxyStatus == 'APPLYING') {
            $.openDlg({
                url: 'transfer.cs?action=audit&id=' + data.id,
                title: '审核' + "${title}",
                width: '${width}',
                height: '${height}'
            });
        } else {
            alert("只有申请中的代理才需要审核！")
        }
    }

    function auditMember() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        if (data.status == 'WAITING') {
            $.openDlg({
                url: 'transfer.cs?action=audit&id=' + data.id,
                title: '审核' + "${title}",
                width: '${width}',
                height: '${height}'
            });
        } else {
            alert("只有'等待中'的会员才需要审核！")
        }
    }

    function auditCancel() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        if (data.active == true) {
            $.post("/agent/auditCancel.cs", {"id": data.id}, function (data) {
                if (data.indexOf(200) != -1) {
                    alert("取消授权成功");
//                    refresh();
                    $('#tab_agent').bootstrapTable('refresh');
                } else {
                    alert("取消授权失败");
                }
            });
        } else {
            alert("只有已授权的会员才能取消授权！")
        }
    }

    function reAudit() {
        var data = getSelectedRow("${tableId}");
        if (null == data) {
            return;
        }
        if (data.active == false) {
            $.post("/agent/reAudit.cs", {"id": data.id}, function (data) {
                if (data.indexOf(200) != -1) {
                    alert("重新授权成功");
//                    refresh();
                    $('#tab_agent').bootstrapTable('refresh');
                } else {
                    alert("重新授权失败");
                }
            });
        } else {
            alert("只有取消授权的会员才能取消授权！")
        }
    }

    function recharge() {
        var data = getSelectedRow("${tableId}");
        if (null == data) return;

        $.confirm("确定充值吗？ ", function () {
            $.openDlg({
                url: 'transfer.cs?action=recharge&id=' + data.id,
                title: '充值' + "${title}",
                width: '${width}',
                height: '${height}'
            });
        });
    }

    function refund() {
        var data = getSelectedRow("${tableId}");
        if (null == data) return;

        $.confirm("确定退费吗？ ", function () {
            $.openDlg({
                url: 'transfer.cs?action=refund&id=' + data.id,
                title: '退费' + "${title}",
                width: '${width}',
                height: '${height}'
            });
        });
    }

    function solve() {
        var data = getSelectedRow("${tableId}");
        if (null == data) return;

        $.openDlg({
            url: 'transfer.cs?action=solve&id=' + data.id,
            title: '解决反馈问题' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    function refresh() {
        var index = parent.layer.getFrameIndex(window.name);
        $(parent.document.getElementsByTagName('table')).each(function () {
            _id = $(this).attr('id');
            if (_id != '' && _id != undefined) {
                parent.reflush(_id);
            }
        });
        parent.layer.close(index);
    }
</script>