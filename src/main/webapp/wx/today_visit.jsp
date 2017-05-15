<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>车险汇</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/page01.css">
    <script type="text/javascript" src="js/jquery.1.11.3.min.js"></script>
    <script type="text/javascript" src="../common/translateType.js"></script>
</head>
<body>
<div class="page_header">
    <div class="sub1"><a href="/wx/manage.cs?userid=${user_id}"><i><img src="img/header_icon.png"></i>
        <p>返回</p></a></div>
    <div class="sub2">今日已回访</div>
    <div class="sub3"></div>
</div>
<div class="page02_main">
    <div class="jilu_main">${count}条信息</div>
    <div class="page02_list_main">
        <c:forEach items="${list}" var="obj">
            <div class="page02_lists">
                <a href="/wx/detail.cs?path=today_visit&id=${obj.id}&userid=${user_id}" class="zhuangtai">
                    <c:if test="${obj.visit}">
                        <div class="zhuangtai_ok">已回访</div>
                    </c:if>
                    <c:if test="${!obj.visit}">
                        <div class="zhuangtai_no">未回访</div>
                    </c:if>

                    <div class="lists_p1">姓名：${obj.name}</div>
                    <p>电话：${obj.mobile}</p>
                    <p id="need_${obj.id}">${obj.type}</p>
                    <p>时间：${obj.createDate}</p>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>