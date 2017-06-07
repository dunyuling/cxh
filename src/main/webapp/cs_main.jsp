<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <base href=" <%=basePath%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>

    <title>车险汇管理系统 - 主页</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <script src="res/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="res/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="res/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="res/js/hplus.js"></script>
    <script type="text/javascript" src="res/js/contabs.js"></script>

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" src="res/img/profile_small.jpg"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${user.name}</strong></span>
                                <span class="text-muted text-xs block">${roleName} <b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="javascript:;" id="edit_main_pwd">修改密码</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">H+
                    </div>
                </li>
                <input type="hidden" id="user_id" value="${user.id}"/>

                <c:forEach items="${permissions }" var="p">
                    <c:if test="${p.pid == 0 }">
                        <li>
                            <a href="#">
                                <i class="fa fa fa-bar-chart-o"></i>
                                <span class="nav-label">${p.menu_name }</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level">
                                <c:forEach items="${permissions }" var="p2">
                                    <c:if test="${p2.pid == p.menu_id && p2.display }">
                                        <li>
                                            <a class="J_menuItem" href="${p2.url}?menuCode=${p2.menu_code}&agentId=0"
                                               code="${p2.menu_code }">${p2.menu_name }</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="mgr/logout.cs" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="mgr/index.cs" frameborder="0"
                    data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-middle">&copy; 2014-2015 <a href="javascript:;" target="_blank">版权所有：郑州爱峰科技有限公司</a>
                <!--                 <div class="pull-right">&copy; 2014-2015 <a href="#" target="_blank">zihan's blog</a> -->
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<script type="text/javascript">
    $("#edit_main_pwd").click(function () {
        var user_id = $("#user_id").val();
        if (user_id == null) return;
        $.openDlg({
            url: "/mgr/to_edit_main_pwd.cs?id=" + user_id,
            title: '修改密码' + "${title}",
            width: '768px',
            height: '420px'
        });
    });
</script>
</body>
</html>
