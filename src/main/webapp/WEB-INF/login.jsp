<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>车险汇管理系统 - 登录</title>
    <link rel="shortcut icon" href="../favicon.ico">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">

    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">G+</h1>
        </div>
        <h3>欢迎登陆车险汇管理系统</h3>

        <form class="m-t" role="form" action="/mgr/login.cs" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="account" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="pwd" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            <p class="text-muted text-center">
                <a href="/mgr/to_pwd_reminder.cs?action=admin">
                    <small>忘记密码了？</small>
                </a>
            </p>
            <p class="text-center">
                <big>不推荐使用IE浏览器，请用Google或者火狐浏览器。</big>
            </p>
        </form>
    </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!--统计代码，可删除-->
</body>

</html>