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
    <div class="sub2">完善资料</div>
    <div class="sub3"></div>
</div>
<%--<div class="submit_main">
    <ul>
        <input type="hidden" id="access_token" value="${access_token}"/>
        <input type="hidden" id="user_id" value="${user_id}"/>
        <li class="su_input"><p>姓名</p><input id="name" name="name" type="text" placeholder="" required></li>
        <li class="su_input"><p>手机号</p><input id="mobile" name="mobile" type="text" placeholder="国际手机号码请加区号"
                                              required>
        </li>
        <li class="su_input"><p>身份证</p><input id="IDCard" name="IDCard" type="text" placeholder="" required
        ></li>
        <li class="su_input content-block"><p>代理地区</p><input name="addr" id="addr" readonly type="text"
                                                             placeholder="省/市/县（区）"></li>
    </ul>
</div>--%>
<div class="submit_main2">
    <li class="su_input2"><p>公司名称</p>
        <div class="input"><input id="corpName" name="corp_name" type="text" placeholder="" required></div>
    </li>
    <div class="shangchuan_img ">
        <p>营业执照</p>
        <div class="z_photo">
            <div id="select_file" class="shangchuan_but z_file">
                <input type="file" name="img" id="img" value="" accept="image/*"
                       onchange="imgChange(this, 'z_photo','z_file');"/>
            </div>
        </div>
        <!--遮罩层-->
        <div class="z_mask">
            <!--弹出框-->
            <div class="z_alert">
                <p style="width:100%; height:2.2rem; border-bottom: 1px solid #ddd;">确定要删除这张图片吗？</p>
                <p style=" width:100%; height:0.8rem">
                    <span class="z_cancel">取消</span>
                    <span class="z_sure">确定</span>
                </p>
            </div>
        </div>

    </div>
    <li class="su_input2"><p>营业执照失效日期</p>
        <div class="input"><input id="expireDate" name="expireDate" type="text"
                                  placeholder="例：20170101" required></div>
    </li>
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


        var imgList = document.getElementsByClassName("z_addImg");
        var imgNum = 0;
//        alert("a: " + imgList.length == undefined);
        if (imgList.length != undefined) {
            console.log("--- \t " + imgList.length);
            for (var j = 0; j < imgList.length; j++) {
                if($(imgList[j]).is(":visible")) {
                    console.log("++++");
                    imgNum++;
                }
            }
        }

        //遍历获取到得图片文件
        if (imgNum < 1) {
//            alert(imgNum);
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
        } else {
            alert("只能选择一张图片");
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

<input type="hidden" id="submit_enable" value="true"/>
<div class="su_details_button"><a href="#" id="submit">提交</a></div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/Popt.js"></script>
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>
<script type="text/javascript">
    $("#addr").click(function (e) {
        SelCity(this, e);
    });

    $("#submit").click(function () {
//        alert($("#submit_enable").val());
        if ($("#submit_enable").val() == 'true') {
            var name = $("#name").val();
            var mobile = $("#mobile").val();
            var IDCard = $("#IDCard").val();
            var addr = $("#addr").val();
            //TODO 选择到 县/区
            var corpName = $("#corpName").val();
            var file = $("#img").val();
            var expireDate = $("#expireDate").val();
            var access_token = $("#access_token").val();
            var user_id = $("#user_id").val();

            if (name == "") {
                alert("姓名必须填写");
                return;
            }

            var reg = /^1[0-9]{10}$/; //验证规则
            var flag = reg.test(mobile); //true
            if (!flag) {
                alert("手机号必须正确填写");
                return;
            }

            reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            flag = reg.test(IDCard);
            if (!flag) {
                alert("身份证号码必须正确填写");
                return;
            }

            if (addr == '' || addr.split("-").length != 3) {
                alert("地址必须正确选择");
                return;
            }


            if (corpName == '') {
                alert("公司名称必须填写");
                return;
            }

            if (file == '') {
                alert("营业执照必须选择");
                return;
            }

            var el = expireDate.length;
            if (el != 8) {
                alert("过期时间格式错误，请重新填写");
                return;
            }

            var year = expireDate.substring(0, 4);
            var month = expireDate.substring(4, 6);
            var day = expireDate.substring(6, 8);
            if (new Date(year, month, day) < new Date()) {
                alert("营业执照已经过期，请重新申请");
                return;
            }

            $("#submit_enable").val(false);
            var formData = new FormData();
            formData.append('file', $('#img')[0].files[0]);
            formData.append("IDCard", IDCard);
            formData.append('name', name);
            formData.append("mobile", mobile);
            formData.append("addr", addr);
            formData.append("corpName", corpName);
            formData.append("expireDate", expireDate);
            formData.append("access_token", access_token);
            formData.append("user_id", user_id);
            $.ajax({
                url: '/wx/agent_info.cs',
                type: 'POST',
                cache: false,
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false
            }).done(function (res) {
                alert(res);
                $("#submit_enable").val(true);
                window.self.close();
            }).fail(function (res) {
                $("#submit_enable").val(true);
            });

//        alert("document.close()"); //TODO 关不掉当前页面
//        document.close();
        } else {
            alert("您已提交过资料")
        }
    });
</script>
</body>
</html>