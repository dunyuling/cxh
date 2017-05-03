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
            title: '查看' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    // 新增事件
    function add() {
//	    alert("add");
        $.openDlg({
            url: 'transfer.cs?action=add',
            title: '新增' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    // 删除事件
    function del() {
//	    alert("del");
        var ids = [];
        var param_id = '';

        $($('#' + "${tableId}").bootstrapTable('getSelections')).each(function () {
//			ids.push(this.id);
            param_id += this.id + ',';
        });

        if (param_id.length == 0) return;
        param_id = param_id.substr(0, param_id.length - 1);
//        alert("param_id: " + param_id);

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

    // 双击事件
    function view_dbclick(id) {
        $.openDlg({
            url: 'transfer.cs?action=view&id=' + id,
            title: '查看' + "${title}",
            width: '${width}',
            height: '${height}'
        });
    }

    function audit() {
        var data = getSelectedRow("${tableId}");
        /*$.each( data, function( key, value ) {
         alert( key + ": " + value );
         });*/

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

    function audit1() {
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
            alert("只有等待中的会员才需要审核！")
        }
    }
</script>