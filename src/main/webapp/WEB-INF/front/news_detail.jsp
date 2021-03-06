﻿<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>车险汇</title>
    <link rel="stylesheet" href="/css/front/css.css">
    <link rel="stylesheet" href="/css/front/new.css">
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/front/jquery.paging.js"></script>
</head>
<body>
<div class="index_body_all">
    <!--nav-->
    <div class="index_nav_body">
        <div class="index_nav_main">
            <div class="index_nav_box1">
                <div class="index_nav_box1_icon"><p class="nav_icon"><img src="/img/front/icon_01.png"></p><p>cxhqcbxdl@126.com</p></div>
                <div class="index_nav_box1_icon"><p class="nav_icon"><img src="/img/front/icon_02.png"></p><p>4000080099</p></div>
                <div class="index_nav_box1_icon2"><p class="nav_icon"><img src="/img/front/icon_03.png"></p><p>周一至周五  09:00-18:00</p></div>
            </div>
            <div class="index_nav_box2">
                <div class="index_logo"><a href="/front/index/pc.cs">
                    <img src="/img/front/logo_03.png"></a></div>
                <ul>
                    <li><a href="/front/index/pc.cs" class="nav_txtss">首页</a></li>
                    <li><a href="/front/get_news/pc.cs" class="nav_txtss now">新闻动态</a></li>
                    <li><a href="/front/about/pc.cs" class="nav_txtss">关于我们</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--面包屑-->
<div class="breadcrumb_main">
    <ul>
        <li class="breadcrumb_my"><a href="/pages/front/index.html">首页</a></li>
        <li class="breadcrumb_my"></li>
        <%--<li class="breadcrumb_my"><a href="/cxh/front/get_news.cs">新闻动态</a></li>--%>
        <li class="breadcrumb_my"><a href="/front/get_news/pc.cs">新闻动态</a></li>
        <li class="breadcrumb_my"></li>
        <li>详情</li>
    </ul>
</div>
<!--新闻详情-->
<div class="new2_main">
    <div class="new2_title_main">
        <div class="new2_title">${news.title}</div>
        <div class="new2_time">
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${news.createDate}"/>
        </div>
    </div>
    <p>${news.description}</p>
    <p>${news.content}</p>
</div>
<!--页尾-->
<div class="public_bottom">
    <div class="public_bottom_main1">
        <div class="xinxi_main">
            <p>地址：河南省南阳市工业南路兴达商务楼六楼G室</p>
            <p>Copyright @ 车险汇 &nbsp;&nbsp;&nbsp;&nbsp;营业执照注册号：9141130235803319XY 备案号：豫ICP备12010516号-2</p>
        </div>
        <div class="dianhua_main">
            <div class="dianhua_img"><img src="/img/front/dianhua.png"></div>
            <div class="dianhua_txt1">客服热线&nbsp;&nbsp;4000080099</div>
            <div class="dianhua_txt2">周一至周五&nbsp;9:00-18:00（仅收市话费）</div>
        </div>
    </div>
</div>
</body>
</html>