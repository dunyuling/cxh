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
    <div class="sub1"><a href="#1"></a></div>
    <div class="sub2">个人资料</div>
    <div class="sub3"></div>
</div>
<div class="submit_main">
    <ul>
        <li class="su_input"><p>姓名</p><input id="name" name="name" type="text" value="${temp.name}"></li>
        <li class="su_input"><p>手机号</p><input id="mobile" name="mobile" type="text" value="${temp.mobile}">
        </li>
        <li class="su_input"><p>身份证</p><input id="IDCard" name="IDCard" type="text" value="${temp.IDCard}"></li>
        <li class="su_input content-block"><p>代理地区</p><input name="addr" id="addr" readonly type="text"
                                                             value="${temp.province}-${temp.city}-${temp.area}"></li>
    </ul>
</div>
<div class="submit_main2">
    <li class="su_input2"><p>公司名称</p>
        <div class="input"><input id="corpName" name="corp_name" type="text" value="${temp.corpName}"></div>
    </li>
    <div class="shangchuan_img">
        <p>营业执照</p>
        <div class="z_photo">
            <div class="shangchuan_but">
                <img src="${temp.licenseImg}" width="80" height="80"/>
            </div>
        </div>
    </div>
    <li class="su_input2"><p>营业执照失效日期</p>
        <div class="input"><input id="expireDate" name="expireDate" type="text" value="${temp.expireDate}"></div>
    </li>
</div>
</body>
</html>