<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>车险汇</title>
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/base.css">
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/index.css">
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/LArea.css">
    <script type="text/javascript" src="/js/front/mobile/banner.js"></script>
</head>
<body style="background-color:#f5f5f5">
<div>
    <!--nav-->
    <div class="page_header">
        <div class="logo_main"><a href="/front/index/mobile.cs"></a></div>
        <div class="nav_main">
            <ul>
                <li><a href="/front/index/mobile.cs" class="">首页</a></li>
                <li><a href="/front/get_news/mobile.cs" class="nav_bj">新闻动态</a></li>
                <li><a href="/front/about/mobile.cs" class="">关于我们</a></li>
            </ul>
        </div>
    </div>

    <div class="new_banner"><img src="/img/front/mobile/bannertwo.jpg"></div>
    <div class="new_main">
        <input type="hidden" id="current" value="${current}"/>
        <input type="hidden" id="pageCount" value="${pageCount}"/>
        <ul>
            <c:forEach var="news" items="${newses}">
                <li>
                    <a href="/front/get_news_detail/mobile.cs?id=${news.id}">
                        <div class="new_imgs"
                             style="background:url(${news.img}) no-repeat center center; background-size:cover"></div>
                        <div class="new_title">${news.title}</div>
                        <div class="new_tit_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${news.createDate}"/></div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="in_bottom">
        <p>客服热线 4000080099</p>
        <p>工作时间：周一至周五 9:00-18:00</p>
        <p>地址：河南省南阳市工业南路兴达商务楼六楼G室</p>
    </div>
</div>
</body>
</html>