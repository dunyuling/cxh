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
    <script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
    <script type="text/javascript" src="../common/translateType.js"></script>
</head>
<body>
<div class="page_header">
    <div class="sub1">
        <c:if test="${not empty path}">
            <a href="/wx/${path}.cs?userid=${user_id}"><i><img src="img/header_icon.png"></i>
                <p>返回</p></a>
        </c:if>
    </div>
    <div class="sub2">详情</div>
    <div class="sub3"></div>
</div>

<div class="page_details_main">
    <ul>
        <li><p>姓名：${name}</p></li>
        <li><p style="color:#4a90e2">电话：${mobile}</p></li>
        <%--<li><p>ID：686868</p></li>--%>
        <li><p>时间：${createDate}</p></li>
        <li><p id="need_${id}">${type}</p></li>
        <li><p>地区：${province}-${city}-${area}</p></li>
    </ul>
    <c:if test="${!visit}">
        <div id="to_visit_div">
            <div class="page_details_button"><a href="#" id="to_visit">确认回访</a></div>
        </div>
    </c:if>
    <c:if test="${visit}">
        <div class="page_details_button2"><a>已回访</a></div>
    </c:if>
    <input type="hidden" value="${id}" id="id"/>

    <script type="text/javascript">
        $("#to_visit").click(function () {
            var id = $("#id").val();
            $.get("/wx/visit.cs?id=" + id, function (data) {
                if (data == 'success') {
                    $("#to_visit_div").html('<div class="page_details_button2"><a>已回访</a></div>');
                }
            })

        });
    </script>
</div>
</body>
</html>