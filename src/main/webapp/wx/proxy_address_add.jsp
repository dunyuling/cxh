<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html manifest="manifest.appcache">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="no"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>车险汇</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/submit.css">
    <link rel="stylesheet" type="text/css" href="css/LArea.css">
</head>
<body>
<div class="page_header">
    <div class="sub1"><a href="#1"><i><img src="img/header_icon.png"></i>
        <p>返回</p></a></div>
    <div class="sub2">添加代理地区</div>
    <div class="sub3"></div>
</div>
<div class="submit_main">
    <ul>
        <input type="hidden" id="access_token" value="${access_token}"/>
        <input type="hidden" id="user_id" value="${user_id}"/>
        <c:if test="${not empty agent}">
            <li class="su_input content-block">
                <p>代理地区</p>
                <input name="addr" id="addr" readonly type="text" placeholder="省/市/县（区）">
            </li>
            <div class="su_details_button"><a href="#" id="submit">提交</a></div>
        </c:if>
        <c:if test="${empty agent}">
            <li class="content-block" style="color:black;alignment:center;">
                <p>请先进行身份验证</p>
            </li>
        </c:if>
        <input type="hidden" id="submit_enable" value="true"/>

    </ul>
</div>

<script type="text/javascript">
    //px转换为rem
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if (clientWidth >= 640) {
                    docEl.style.fontSize = '100px';
                } else {
                    docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);

    function imgChange(obj, obj1, obj2) {
        //获取点击的文本框
//        var file = document.getElementById("img");
        var file = obj;
        //存放图片的父级元素
        var imgContainer = document.getElementsByClassName(obj1)[0];
        //获取的图片文件
        var fileList = file.files;
        //文本框的父级元素
        var input = document.getElementsByClassName(obj2)[0];
        var imgArr = [];
        //遍历获取到得图片文件
        for (var i = 0; i < fileList.length; i++) {
            var imgUrl = window.URL.createObjectURL(file.files[i]);
            imgArr.push(imgUrl);
            var img = document.createElement("img");
            img.setAttribute("src", imgArr[i]);
            var imgAdd = document.createElement("div");
            imgAdd.setAttribute("class", "z_addImg");
            imgAdd.appendChild(img);
            imgContainer.appendChild(imgAdd);
        }
        imgRemove();
    }

    function imgRemove() {
        var imgList = document.getElementsByClassName("z_addImg");
        var mask = document.getElementsByClassName("z_mask")[0];
        var cancel = document.getElementsByClassName("z_cancel")[0];
        var sure = document.getElementsByClassName("z_sure")[0];
        for (var j = 0; j < imgList.length; j++) {
            imgList[j].index = j;
            imgList[j].onclick = function () {
                var t = this;
                mask.style.display = "block";
                cancel.onclick = function () {
                    mask.style.display = "none";
                };
                sure.onclick = function () {
                    mask.style.display = "none";
                    t.style.display = "none";
                };

            }
        }
    }
</script>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/Popt.js"></script>
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>
<script type="text/javascript">
    $("#addr").click(function (e) {
        SelCity(this, e);
    });

    $("#submit").click(function () {
        if ($("#submit_enable").val() == 'true') {
            var addr = $("#addr").val();
            var user_id = $("#user_id").val();

            if (addr == '' || addr.split("-").length != 3) {
                alert("地址必须正确选择");
                return;
            }

            disable();
            $.post('/wx/agent_add.cs', {'addr': addr, 'user_id': user_id}, function (res) {
                active();
                alert(res);
            });
        }
    });

    function active() {
        $("#submit_enable").val(true);
    }

    function disable() {
        $("#submit_enable").val(false);
    }
</script>
</body>
</html>