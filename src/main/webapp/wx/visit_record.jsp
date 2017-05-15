<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html manifest="manifest.appcache">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>车险汇</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/submit.css">
    <link rel="stylesheet" type="text/css" href="css/LArea.css">
    <link rel="stylesheet" type="text/css" href="css/mobiscroll_002.css">
    <link rel="stylesheet" type="text/css" href="css/mobiscroll.css">
    <link rel="stylesheet" type="text/css" href="css/mobiscroll_003.css">
    <script type="text/javascript" src="js/jquery.1.11.3.min.js"></script>
    <script type="text/javascript" src="js/mobiscroll_002.js"></script>
    <script type="text/javascript" src="js/mobiscroll_004.js"></script>
    <script type="text/javascript" src="js/mobiscroll.js"></script>
    <script type="text/javascript" src="js/mobiscroll_003.js"></script>
    <script type="text/javascript" src="js/mobiscroll_005.js"></script>
</head>
<body>
<div class="page_header">
    <div class="sub1">
        <a href="/wx/detail.cs?id=${id}&userid=${user_id}&path=${path}"><i><img src="img/header_icon.png"></i>
            <p>返回</p></a>
    </div>
    <div class="sub2">回访备注</div>
    <div class="sub3"></div>
</div>


<div class="submit_main">
    <ul>
        <li class="su_input"><p>回访标题</p><input name="situation" id="situation" type="text" placeholder=""></li>
        <li class="su_input" style=" height:3rem">
            <p>回访备注</p><textarea id="remark" name="remark" type="text" placeholder="请填写回访的详情备注"></textarea>
        </li>
    </ul>
</div>
<div class="page02_box01" style="margin-bottom:0.5rem">
    <div class="demos">
        <div class="ss_icon"><img src="img/page01_icon.png"></div>
        <input value="" class="time_input" readonly name="appDate" id="appDate" type="text" placeholder="选择下次回访时间">
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

<input type="hidden" id="id" value="${id}"/>
<input type="hidden" id="user_id" value="${user_id}"/>
<input type="hidden" id="path" value="${path}"/>
<div class="su_details_button"><a id="submit" href="#">保存</a></div>
<script type="text/javascript">
    $("#submit").click(function () {
//        alert("ss1");
        var id = $("#id").val();
        var user_id = $("#user_id").val();
        var path = $("#path").val();
        var dateStr = $("#appDate").val();
        var situation = $("#situation").val();
        var remark = $("#remark").val();

//        $.post("/wx/add_visit_record1.cs");
        var queryParams = "id=" + id + "&userid=" + user_id + "&path=" + path + "&dateStr=" + dateStr + "&situation=" + situation + "&remark=" + remark;
        window.location.href = "/wx/add_visit_record.cs?" + queryParams;

    });
</script>
</body>
</html>