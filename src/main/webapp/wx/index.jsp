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
    <link rel="stylesheet" href="css/base.css" type="text/css"/>
    <link rel="stylesheet" href="css/page01.css" type="text/css"/>
</head>
<body>
<div>
    <div class="page_header">
        <div class="sub1"><a href="#1"><i><img src="img/header_icon.png"></i>
            <p>返回</p></a></div>
        <div class="sub2">统计管理</div>
        <div class="sub3"></div>
    </div>
    <div class="page01_main">
        <div class="page01_listss">
            <div class="page01_list01"><a href="/wx/total.cs?userid=${userid}"><span class="icon01"></span>
                <p>全部咨询记录</p></a></div>
            <div class="page01_list02"><a href="/wx/today.cs?userid=${userid}"><span class="icon02"></span>
                <p>今日咨询记录</p></a></div>
        </div>
        <div class="page01_listss">
            <div class="page01_list01"><a href="/wx/today_not_visit.cs?userid=${userid}"><span class="icon03"></span>
                <p>今日未回访</p></a></div>
            <div class="page01_list02"><a href="/wx/today_visit.cs?userid=${userid}"><span class="icon04"></span>
                <p>今日已回访</p></a></div>
        </div>
    </div>
</div>
</body>
</html>