<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>车险汇</title>
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/base.css">
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/index.css">
    <link rel="stylesheet" type="text/css" href="/css/front/mobile/LArea.css">
    <script type="text/javascript" src="/js/front/mobile/banner.js"></script>
</head>
<body>
<div>
    <!--nav-->
    <div class="page_header">
        <div class="logo_main"><a href="/front/index/mobile.cs"></a></div>
        <div class="nav_main">
            <ul>
                <li><a href="/front/index/mobile.cs" class="nav_bj">首页</a></li>
                <li><a href="/front/get_news/mobile.cs" class="">新闻动态</a></li>
                <li><a href="/front/about/mobile.cs" class="">关于我们</a></li>
            </ul>
        </div>
    </div>
    <!--banner-->
    <div class="wrap" id="focus">
        <ul class="pics">
            <li style="background-image:url(/img/front/mobile/banner01.jpg)"><a href="#"></a></li>
            <li style="background-image:url(/img/front/mobile/banner02.jpg)"><a href="#"></a></li>
            <li style="background-image:url(/img/front/mobile/banner03.jpg)"><a href="#"></a></li>
        </ul>
        <p class="num">
            <i class="dot current"></i>
            <i class="dot"></i>
            <i class="dot"></i>
        </p>
    </div>
    <script type="text/javascript">
        window.addEventListener('DOMContentLoaded', function () {
            var $slide = document.getElementById('focus');
            var aBullet = $slide.querySelectorAll('.dot');
            var len = aBullet.length;

            var mySwipe = new Swipe($slide, {
                auto: 3000,
                callback: function (i) {
                    var n = len;
                    while (n--) {
                        aBullet[n].classList.remove('current');
                    }
                    aBullet[i].classList.add('current');
                }
            });
        });
    </script>
    <!--button-->
    <div class="xunjia_main">
        <div class="title_main">车险询价</div>
        <div class="submit_main">
            <ul>
                <li class="su_input"><p>姓名</p><input id="name" name="name" type="text" required></li>
                <li class="su_input content-block"><p>地区</p><input name="addr" id="addr" readonly type="text"
                                                                   placeholder="省/市/县（区）"></li>
                <li class="su_input"><p>手机号</p><input name="mobile" id="mobile" type="text" placeholder="国际手机号码请加区号"></li>
                <li class="su_input">
                    <p style="width:20%">车险类型</p>
                    <div class="trading2_main_input">
                        <select id="insurance" name="insurance" class="trading2_main_input2">
                            <option value="0">交强险</option>
                            <option value="1">机动车辆损失险</option>
                            <option value="2">第三者责任险</option>
                            <option value="3">盗抢险</option>
                            <option value="4">车上人员责任险</option>
                            <option value="5">车身划痕损失险</option>
                            <option value="6">玻璃单独破碎险</option>
                            <option value="7">自燃损失险</option>
                            <option value="8">发动机特别损失险</option>
                            <option value="9">不计免赔率特约损失险</option>
                        </select>
                    </div>
                </li>
            </ul>
            <div class="xunjia_main_button"><a id="submit">提交订单</a></div>
        </div>
        <script src="/js/jquery-1.11.3.min.js"></script>
        <script src="/js/front/mobile/Popt.js"></script>
        <script src="/js/cityJson.js"></script>
        <script src="/js/citySet.js"></script>
        <script type="text/javascript">
            $("#addr").click(function (e) {
                SelCity(this, e);
            });
        </script>
    </div>
    <!--boxs-->
    <div class="boxs1">
        <div class="title_main">投保流程</div>
        <div class="boxs1_img">
            <ul>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_01.jpg"></div>
                    <div class="boxs1_txts newsy"><span>提交</span>
                        <p>车主在车险汇上提交投车辆的资料及险种</p>
                    </div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_02.jpg"></div>
                    <div class="boxs1_txts newsy"><span>报价</span>
                        <p>多家保险公司报价，在线获取报价</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_03.jpg"></div>
                    <div class="boxs1_txts"><span>付款</span>
                        <p>可支付宝转账，无支付宝的用户可跟业务员去网点刷卡</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_04.jpg"></div>
                    <div class="boxs1_txts"><span>收单</span>
                        <p>车主收到保险单后可打相应保险公司客服电话或者官网验真</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_05.jpg"></div>
                    <div class="boxs1_txts newsy"><span>评价</span>
                        <p>对保险公司以及车险汇进行客观评价</p></div>
                </li>
            </ul>

        </div>
    </div>
    <div class="boxs1">
        <div class="title_main">车险介绍</div>
        <div class="team_join_main">
            <ul id="accordion" class="accordion">
                <li class="default open">
                    <div class="link">交强险<i class="fa fa-chevron-down"><img src="/img/front/mobile/team_icon.png"></i>
                    </div>
                    <ul class="submenu">
                        <li>
                            <span>机动车交通事故责任强制保险</span>
                            <p>国家法规强制要求，每位车主必须投保的险种；</p>
                            <p>在交通事故中造成受害人（不包括本车人员和被保险人）的人身伤亡、财产损失，在责任限额内予以保障性的赔偿；</p>
                            <p>一般情况下赔偿最高限额为12.2万元，交强险赔偿额度有限，所以商业险补充是非常必要。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">机动车辆损失险<i class="fa fa-chevron-down"><img
                            src="/img/front/mobile/team_icon.png"></i></div>
                    <ul class="submenu">
                        <li>
                            <span>机动车辆损失险</span>
                            <p>车辆发生碰撞、倾覆；</p>
                            <p>车辆发生火灾、爆炸；</p>
                            <p>车辆遭遇自然灾害；</p>
                            <p>外界物体倒塌或坠落、保险车辆行驶中平行坠落造成车辆损失；</p>
                            <p>车辆有驾驶人随船照料的情况下，载运保险车辆的渡船遭受以上自然灾害造成车辆损失。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">第三者责任险<i class="fa fa-chevron-down"><img
                            src="/img/front/mobile/team_icon.png"></i></div>
                    <ul class="submenu">
                        <li>
                            <span>第三者责任险</span>
                            <p>是交强险的有力补充，为赔偿他人损失（包括人和财物）提供保障；</p>
                            <p>交强险赔得少，赔付最高不超过2000元，超过这部分的修理费就需要车主自己掏腰包。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">盗抢险<i class="fa fa-chevron-down"><img src="/img/front/mobile/team_icon.png"></i>
                    </div>
                    <ul class="submenu">
                        <li>
                            <span>盗抢险</span>
                            <p>为保障您的车被盗被抢或者被盗抢后，受到损坏或者车上零部件、附属设备丢失需要修复的合理费用提供保障；</p>
                            <p>
                                盗抢险简单来说就是个移动的安全车库！爱车被盗窃、抢劫、抢夺，警察找了60天还未查明下落时；当爱车在被抢劫、抢夺过程中，受到损坏；当爱车全车被盗窃、抢劫、抢夺后，受到损坏或车上零部件、附属设备丢失时，这个安全车库会提供最全面的保障。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">车上人员责任险<i class="fa fa-chevron-down"><img
                            src="/img/front/mobile/team_icon.png"></i></div>
                    <ul class="submenu">
                        <li>
                            <span>车上人员责任险</span>
                            <p>为乘坐您车的亲人、爱人、挚友们提供更全面的保障；</p>
                            <p>如车撞树上致使车上人员受伤或死亡；如驾驶员在行驶过程中紧急刹车导致的本车人员伤亡；如车辆相撞致使本车车上人员受伤或死亡（本车全责），车上人员会提供最全面的保障。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">车身划痕损失险<i class="fa fa-chevron-down"><img
                            src="/img/front/mobile/team_icon.png"></i></div>
                    <ul class="submenu">
                        <li>
                            <span>车身划痕损失险</span>
                            <p>车身油漆单独划伤赔偿，建议购买人群：新车车主和完美主义者！</p>
                            <p>“车身划痕险”里的“划痕”，是指没有明显碰撞痕迹所产生的划痕，就是那些尖锐的物体，比如刀、石子等，对车身油漆的破坏。是人为故意划车所导致的损失，由保险公司进行赔偿。</p>
                            <p>车身的擦蹭，无论是车与车之间，还是车与固定物体之间，基本都不属于划痕险范畴，而属于车损险的碰撞责任，在车损险项下赔偿。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">玻璃单独破碎险<i class="fa fa-chevron-down"><img
                            src="/img/front/mobile/team_icon.png"></i></div>
                    <ul class="submenu">
                        <li>
                            <span>玻璃单独破碎险</span>
                            <p>玻璃单独破碎险并非鸡肋，商业车险组合很实惠。</p>
                            <p>“因车损险条款列明的原因造成车辆损失同时玻璃也破碎的属于车辆损失险，如果仅仅是玻璃单独破碎，是不能予以赔偿的，需要单独投保玻璃单独破碎保险。”</p>
                            <p>
                                所谓玻璃单独破碎险，即车辆在停放或使用过程中，其他部分没有损坏，仅风挡玻璃和车窗玻璃单独破碎，保险公司负责赔偿。汽车在使用过程中，有太多不可预测的情况出现，汽车玻璃作为非常重要的组成部分有着易破损的特性，一旦受损将严重影响驾驶安全并有很大的隐患。</p>
                            <p>
                                飞石、车祸、贴膜等都有可能使玻璃造成损伤，像王先生这种受到意外“暗算”的情况也时有发生。所以，仓江建议车主在购买车辆损失险的同时，最好再购买一份玻璃单独破碎险，毕竟一块风挡玻璃也价格不菲。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">自燃损失险<i class="fa fa-chevron-down"><img src="/img/front/mobile/team_icon.png"></i>
                    </div>
                    <ul class="submenu">
                        <li>
                            <span>自燃损失险</span>
                            <p>
                                夏天外出办事，找不到地下停车场而被迫将车停在路边，太阳直射下车内温度高达60℃。挡风玻璃前放了一瓶香水，外观好看，还是是多面切割的，正好一面被太阳直射，形成“放大镜”。含有10%酒精的液体闪点仅仅为49℃，车子一下自燃了。</p>
                            <p>车辆的自燃主要与驾驶人或者使用者在车内遗留某些物品的关系最大。</p>
                            <p>行驶中因为汽车原因的起火，则可能与是否加装电器精品或者改装电路有关。</p>
                            <p>在保险条款中，对自燃的解释是指在没有外界火源的情况下，由于本车电器、线路、供油系统、供气系统等被保险机动车自身原因发生故障或货物自身原因起火燃烧。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">发动机特别损失险<i class="fa fa-chevron-down"><img src="/img/front/mobile/team_icon.png"></i>
                    </div>
                    <ul class="submenu">
                        <li>
                            <span>发动机特别损失险</span>
                            <p>
                                本来艳阳高照，忽然之间又大雨瓢泼了，积水较深。一不小心，熄火了。一会儿爱车就变成潜水艇了，发动机进水没商量。因为暴雨带来的维修和更换部件，都可以走车损险，但发动机进水除外。</p>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="link">不计免赔率特约险<i class="fa fa-chevron-down"><img src="/img/front/mobile/team_icon.png"></i>
                    </div>
                    <ul class="submenu">
                        <li>
                            <span>不计免赔率特约险</span>
                            <p>为您提供出险后的全额赔偿保障。包括车损、三者、车上人员、盗抢都相应的免赔金额，增加不计免赔险后，最高免赔金额可以达到20%，最大程度的降低您的意外开支。</p>
                            <p>
                                比如：江先生（车牌苏A-××086，驾龄5年），当初买的就是二手车，所以投保图便宜就放弃不计免赔险。结果8月份开车撞上路边护栏，修了4000多元，保险公司扣掉20%免赔率，我自己掏了800多元，其实不计免赔险的保费还不到400元，自己
                                “亏大了”</p>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <script>
            $(function () {
                var Accordion = function (el, multiple) {
                    this.el = el || {};
                    this.multiple = multiple || false;
                    var links = this.el.find('.link');
                    links.on('click', {
                        el: this.el,
                        multiple: this.multiple
                    }, this.dropdown);
                };
                Accordion.prototype.dropdown = function (e) {
                    var $el = e.data.el;
                    $this = $(this), $next = $this.next();
                    $next.slideToggle();
                    $this.parent().toggleClass('open');
                    if (!e.data.multiple) {
                        $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
                    };
                };
                var accordion = new Accordion($('#accordion'), false);

                $("#submit").click(function () {
                    var addr = $("#addr").val();
                    var arr = addr.split("-");
                    if (addr == '' || arr.length != 3) {
                        alert("地址必须正确选择");
                        return;
                    }

                    var name = $("#name").val().trim();
                    if(name == "") {
                        alert("名字必须填写");
                        return;
                    }

                    var mobile = $("#mobile").val();
                    var insurance = $("#insurance option:selected").val();

                    var reg = /^1[0-9]{10}$/; //验证规则
                    var flag = reg.test(mobile); //true
                    if (!flag) {
                        alert("请填写正确的手机号码 " + mobile);
                        return;
                    }

                    $.post("/front/register_member.cs",
                        {province: arr[0], city: arr[1], area: arr[2], name: name, mobile: mobile, index: insurance})
                        .done(function (data) {
                            alert(data);
                            location.reload(true);
                        });
                })
            });
        </script>
    </div>
    <div class="boxs1">
        <div class="title_main">我们的特色</div>
        <div class="boxs1_img">
            <ul>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_06.jpg"></div>
                    <div class="boxs1_txts newsy"><span>保险超市</span>
                        <p>我们与多家保险公司合作让您有更多选择</p>
                    </div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_07.jpg"></div>
                    <div class="boxs1_txts"><span>价格更优惠</span>
                        <p>官方直销 私家车商业险低至55折，再加高额现金优惠</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_08.jpg"></div>
                    <div class="boxs1_txts"><span>咨询服务</span>
                        <p>定制相应的车险方案，提供专业的出险处理建议，协助办理相关手续</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_09.jpg"></div>
                    <div class="boxs1_txts newsy"><span>一键比价</span>
                        <p>车险险种自由搭配，多家保险公司在线比价</p></div>
                </li>
                <li>
                    <div class="boxs1_logo"><img src="/img/front/mobile/boxs_10.jpg"></div>
                    <div class="boxs1_txts"><span>车险到期提醒</span>
                        <p>方便车主及时续保，告别脱保无折扣，服务更贴心</p></div>
                </li>
            </ul>
        </div>
    </div>
    <div class="in_bottom">
        <p>客服热线 4000080099</p>
        <p>工作时间：周一至周五 9:00-18:00</p>
        <p>地址：河南省南阳市工业南路兴达商务楼六楼G室</p>
    </div>
</div>
</body>
</html>