<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
        //检测平台
        var p = navigator.platform;
        var iphone = p.indexOf("iPhone") != -1;
        var android = p.indexOf("arm") != -1;
//        alert("p: iphone: " + iphone + " \t android: " + android);

        if (android || iphone) {
            window.location.href = "/front/index/mobile.cs";
        } else {
            window.location.href = "/front/index/pc.cs";
        }
    </script>
</head>
</html>