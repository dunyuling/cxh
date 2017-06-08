<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>车险汇管理系统 - 密码找回</title>
    <%--<meta name="keywords" content="计分系统 Bate1.0">
    <meta name="description" content="计分系统 Bate1.0">--%>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="/res/css/font-awesome.css" rel="stylesheet">

    <link href="/res/css/animate.css" rel="stylesheet">
    <link href="/res/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>

<body class="gray-bg">

<div class="ibox-content text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">G+</h1>
        </div>
        <h3>密码找回</h3>

        <form class="form-horizontal m-t" role="form" method="post">
            <div class="form-group">
                <label class="col-sm-4 control-label">手机号：</label>
                <div class="col-sm-4">
                    <input id="mobile" name="mobile" minlength="11" type="text" class="form-control" required
                           aria-required="true">
                </div>
                <di class="col-sm-1" id="get_validate_code_div">
                    <input type="button" id="get＿validate_code" value="获取验证码"/>
                </di>
                <di class="col-lg-1" id="timer_div" style="display:none;">
                    <input type="text" id="timer" readonly/>
                </di>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">验证码：</label>
                <div class="col-sm-4">
                    <input id="code" type="text" class="form-control" name="code" required aria-required="true">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">密码：</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">确认密码：</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="repeat_pwd" name="repeat_pwd" placeholder="密码"
                           required>
                </div>
            </div>
            <input type="hidden" id="action" name="action" value="${action}"/>
            <button type="button" id="submit" class="btn btn-primary m-b center-block">登 录</button>
        </form>
    </div>
</div>

<!-- 全局js -->
<script src="/res/js/jquery.min.js"></script>
<script src="/res/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $("#get＿validate_code").click(function () {
        var mobile = $("#mobile").val();
        if (!validateMobile(mobile)) {
            alert("手机号必须正确填写");
        } else {
//            $("#get_validate_code_div").hide();
//            $("#timer_div").show();
//            startTime(60);

            var action = $("#action").val();
            $.post("/mgr/pwd_reminder.cs", {"action": action, "mobile": mobile}, function (data) {
                if (data == false) {
                    alert("手机号未被使用，请填写正确的手机号");
                }
            });
        }
    });

    $("#submit").click(function () {
        var pwd = $("#pwd").val().trim();
        var repeat_pwd = $("#repeat_pwd").val().trim();
        var mobile = $("#mobile").val();
        var code = $("#code").val();

        if (!validateMobile(mobile)) {
            alert("手机号必须正确填写");
            return;
        }

        if (!validateCode(code)) {
            alert("验证码必须正确填写");
            return;
        }

        if (pwd == '' || repeat_pwd == '') {
            alert("密码中存在空值");
        } else if (pwd != repeat_pwd) {
            alert("两次密码不一致");
        } else {
            var action = $("#action").val();
            $.post("/mgr/retrieval_pwd.cs", {
                "mobile": mobile,
                "pwd": pwd,
                "action": action,
                "code": code
            }, function (data) {
                alert(data);
            });
        }
    });

    function startTime(total) {
        if (total > 0) {
            $("#timer").val(total);
            total--;
            setTimeout('startTime(' + total + ')', 1000);
        } else {
            $("#get_validate_code_div").show();
            $("#timer_div").hide();
        }
    }

    function validateMobile(mobile) {
        var reg = /^1[0-9]{10}$/; //验证规则
        return reg.test(mobile); //true
    }

    function validateCode(code) {
        var reg = /^[0-9]{6}$/; //验证规则
        return reg.test(code); //true
    }
</script>
<!--统计代码，可删除-->
</body>

</html>