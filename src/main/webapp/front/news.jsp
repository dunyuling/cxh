<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>车险汇</title>
    <link rel="stylesheet" href="css/css.css">
    <link rel="stylesheet" href="css/new.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.paging.js"></script>

</head>
<body>
<div class="index_body_all">
    <!--nav-->
    <div class="index_nav_body">
        <div class="index_nav_main">
            <div class="index_nav_box1">
                <div class="index_nav_box1_icon"><p class="nav_icon"><img src="img/icon_01.png"></p>
                    <p>chexianhui@163.com</p></div>
                <div class="index_nav_box1_icon"><p class="nav_icon"><img src="img/icon_02.png"></p>
                    <p>133-3385-0551</p></div>
                <div class="index_nav_box1_icon2"><p class="nav_icon"><img src="img/icon_03.png"></p>
                    <p>周一至周五 09:00-18:00</p></div>
            </div>
            <div class="index_nav_box2">
                <div class="index_logo"><a href="index.html"><img src="img/logo_03.png"></a></div>
                <ul>
                    <li><a href="index.html" class="nav_txtss">首页</a></li>
                    <li><a href="#" class="nav_txtss now">新闻动态</a></li>
                    <li><a href="about.html" class="nav_txtss">关于我们</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--新闻-->
<div class="new_body">
    <div class="new_title"><p>新闻动态</p></div>
    <div class="new_main">
        <input type="hidden" id="current" value="${current}"/>
        <input type="hidden" id="pageCount" value="${pageCount}"/>
        <c:forEach var="news" items="${newses}">
            <div class="new_lists">
                <div class="new_list_img"
                     style="background:url(${news.img}) no-repeat center center; background-size:cover">
                    <a href="/front/get_news_detail.cs?id=${news.id}"></a></div>
                <div class="new_list_text">
                    <div class="new_list_text_p1">
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${news.createDate}"/>
                    </div>
                    <div class="new_list_text_p2"><a href="/front/get_news_detail.cs?id=${news.id}">${news.title}</a>
                    </div>
                    <div class="new_list_text_p3">${news.description}</div>
                    <div class="new_list_text_p4"><a href="/front/get_news_detail.cs?id=${news.id}">查看全文 ></a></div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!--分页-->
<div class="tcdPageCode"></div>
<script>
    var pageCount = $("#pageCount").val();
    var current = $("#current").val();
    $(".tcdPageCode").createPage({
        pageCount: pageCount,
        current: current,
        backFn: function (p) {
            //console.log(p);
        }
    });
</script>
<!--页尾-->
<div class="public_bottom">
    <div class="public_bottom_main1">
        <div class="xinxi_main">
            <p>地址：河南省南阳市工业南路兴达商务楼六楼G室</p>
            <p>Copyright @ 车险汇 &nbsp;&nbsp;&nbsp;&nbsp;营业执照注册号：9141130235803319XY 备案号：豫ICP备12010516号-2</p>
        </div>
        <div class="dianhua_main">
            <div class="dianhua_img"><img src="img/dianhua.png"></div>
            <div class="dianhua_txt1">客服热线&nbsp;&nbsp;4000080099</div>
            <div class="dianhua_txt2">周一至周五&nbsp;9:00-18:00（仅收市话费）</div>
        </div>
    </div>
</div>
</body>
</html>