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
    <link rel="stylesheet" type="text/css" href="css/mobiscroll_002.css">
    <link rel="stylesheet" type="text/css" href="css/mobiscroll.css">
    <link rel="stylesheet" type="text/css" href="css/mobiscroll_003.css">
    <script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
    <script type="text/javascript" src="js/mobiscroll_002.js"></script>
    <script type="text/javascript" src="js/mobiscroll_004.js"></script>
    <script type="text/javascript" src="js/mobiscroll.js"></script>
    <script type="text/javascript" src="js/mobiscroll_003.js"></script>
    <script type="text/javascript" src="js/mobiscroll_005.js"></script>
</head>
<body>
<div class="page_header">
    <div class="sub1"><a href="/wx/manage.cs?userid=${user_id}"><i><img src="img/header_icon.png"></i>
        <p>返回</p></a></div>
    <div class="sub2">全部咨询记录</div>
    <div class="sub3"></div>
</div>
<div class="page02_main">
    <div class="page02_box01">
        <div class="demos">
            <div class="ss_icon"><img src="img/page01_icon.png"></div>
            <input value="" class="time_input" readonly name="appDate" id="appDate" type="text" placeholder="选择日期">
        </div>
        <script type="text/javascript">
            $(function () {
                var currYear = (new Date()).getFullYear();
                var opt = {};
                opt.date = {preset: 'date'};
                opt.datetime = {preset: 'datetime'};
                opt.time = {preset: 'time'};
                opt.default = {
                    theme: 'android-ics light', //皮肤样式
                    display: 'modal', //显示方式
                    mode: 'scroller', //日期选择模式
                    dateFormat: 'yyyy-mm-dd',
                    lang: 'zh',
                    showNow: true,
                    nowText: "今天",
                    startYear: currYear - 10, //开始年份
                    endYear: currYear + 10 //结束年份
                };

                $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
                var optDateTime = $.extend(opt['datetime'], opt['default']);
                var optTime = $.extend(opt['time'], opt['default']);
                $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
                $("#appTime").mobiscroll(optTime).time(optTime);
            });
        </script>
    </div>
    <div class="jilu_main">${count}条信息</div>
    <div class="page02_list_main">
        <c:forEach items="${list}" var="obj">
            <div class="page02_lists">
                <a href="/wx/detail.cs?path=total&id=${obj.id}&userid=${user_id}" class="zhuangtai">
                    <c:if test="${obj.visit}">
                        <div class="zhuangtai_ok">已回访</div>
                    </c:if>
                    <c:if test="${!obj.visit}">
                        <div class="zhuangtai_no">未回访</div>
                    </c:if>

                    <div class="lists_p1">姓名：${obj.name}</div>
                    <p>电话：${obj.mobile}</p>
                    <p>需求：购买保险</p>
                    <p>时间：${obj.createDate}</p>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>