<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="init.jsp" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>

<link rel="shortcut icon" href="/favicon.ico"/>
<!-- 全局js -->
<script src="/res/js/jquery.min.js" type="text/javascript"></script>
<script src="/res/js/bootstrap.min.js" type="text/javascript"></script>

<script src="/res/js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
<script src="/res/js/plugins/validate/messages_zh.min.js" type="text/javascript"></script>
<script src="/res/js/plugins/layer/layer.min.js" type="text/javascript"></script>
<script src="/res/js/plugins/layer/laydate/laydate.js" type="text/javascript"></script>

<script src="/res/js/jquery.form.js" type="text/javascript"></script>
<script src="/res/js/common.js" type="text/javascript"></script>

<!-- 第三方插件 -->
<!-- <script src="/res/js/plugins/pace/pace.min.js"></script> -->

<link rel="shortcut icon" href="/res/favicon.ico">
<link href="/res/css/bootstrap.min.css" rel="stylesheet">
<link href="/res/css/animate.css" rel="stylesheet">
<link href="/res/css/style.css" rel="stylesheet">
<link href="/res/css/font-awesome.css" rel="stylesheet">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）: 
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<script>

    function imageFormatter(value) {
        return '<a href="' + value + '"target="_blank" ><img src="' + value + '" width="40px" height="40px" /></a>';
    }

    function dateFormatter(value) {
        if (value == null) {
            return value;
        } else {
            var date = new Date(value);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            return year + "-" + format(month) + "-" + format(day) + " " + format(hour) + ":" + format(minute);
        }
    }

    function format(value) {
        return value < 10 ? '0' + value : value;
    }

    function solveFormatter(solve) {
        return solve == false ? "未解决" : "已解决";
    }

    function auditStatus(active) {
        return active == false ? "取消授权" : "已授权";
    }
</script>

