﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>车险汇</title>
<link rel="stylesheet" href="/css/front/css.css">
<link rel="stylesheet" href="/css/front/index.css">
<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/js/front/banner.roll.js"></script>
<script type="text/javascript" src="/js/front/distpicker.data.js"></script>
<script type="text/javascript" src="/js/front/distpicker.js"></script>
</head>
<body>
<div class="index_body">
	<!--nav-->
	<div class="index_nav_body">
		<div class="index_nav_main">
			<div class="index_nav_box1">
				<div class="index_nav_box1_icon"><p class="nav_icon"><img src="/img/front/icon_01.png"></p><p>cxhqcbxdl@126.com</p></div>
				<div class="index_nav_box1_icon"><p class="nav_icon"><img src="/img/front/icon_02.png"></p><p>4000080099</p></div>
				<div class="index_nav_box1_icon2"><p class="nav_icon"><img src="/img/front/icon_03.png"></p><p>周一至周五  09:00-18:00</p></div>
			</div>
			<div class="index_nav_box2">
				<div class="index_logo"><a href="/front/index/pc.cs">
					<img src="/img/front/logo_03.png"></a></div>
				<ul>
					<li><a href="/front/index/pc.cs" class="nav_txtss now">首页</a></li>
					<li><a href="/front/get_news/pc.cs" class="nav_txtss">新闻动态</a></li>
					<li><a href="/front/about/pc.cs" class="nav_txtss">关于我们</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--main-->
	<div class="index_txt_body">
		<div class="index_txt_main">
			<div class="index_txt_left">
				<div class="index_txt_left_img"><img src="/img/front/banner_txt.png"></div>
				<div class="index_txt_left_button"><a href="/pages/front/about.html">了解详情</a></div>
			</div>
			
			<div class="index_txt_right">
				<div class="index_txt_right_tit">车险询价</div>
				<div class="index_txt_right_in1">
					<span>地区</span>
					<form class="form-inline">
						<div data-toggle="distpicker" class="form-control-main">
							<div class="form-group">
								<select class="form-control" id="province"></select>
							</div>
							<div class="form-group">
								<select class="form-control" id="city"></select>
							</div>
							<div class="form-group">
								<select class="form-control" id="area"></select>
							</div>
						</div>
					</form>
				</div>
				<div class="index_txt_right_in"><span>姓名</span><input id="name" type="text" required></div>
				<div class="index_txt_right_in"><span>手机号码</span><input id="mobile"></div>
				<div class="index_txt_right_in">
					<span>车险类型</span>
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
				</div>
				<div class="index_txt_right_but"><a id="submit">提交订单</a></div>
			</div>
		</div>
	</div>
	<!--banner-->
	<div class="wrap banner_roll">
		<ul class="rslides" id="dowebok">
			<li><a target="_blank" class="banner_roll_img" style="background:url(/img/front/banner1.jpg) no-repeat center center"></a></li>
			<li><a target="_blank" class="banner_roll_img" style="background:url(/img/front/banner2.jpg) no-repeat center center"></a></li>
			<li><a target="_blank" class="banner_roll_img" style="background:url(/img/front/banner3.jpg) no-repeat center center"></a></li>
		</ul>
		<script>
		$(function() {
			$('#dowebok').responsiveSlides({
				pager: true,
				nav: true,
				namespace: 'centered-btns'
			});


			$("#submit").click(function() {
                var province = $("#province option:selected").text();
                var city = $("#city option:selected").text();
                var area = $("#area option:selected").text();
                var name = $("#name").val().trim();
                var mobile = $("#mobile").val();
                var insurance = $("#insurance option:selected").val();

                if(name == "") {
					alert("名字必须填写");
					return;
				}

                if(province == '—— 省 ——') {
                    alert("请选择正确的省份");
                    return;
				}
                if(city == '—— 市 ——') {
                    alert("请选择正确的市");
                    return;
                }
                if(area == '—— 县/区 ——') {
                    alert("请选择正确的县/区");
                    return;
                }

                var reg = /^1[0-9]{10}$/; //验证规则
                var flag = reg.test(mobile); //true
				if(!flag) {
					alert("请填写正确的手机号码 " + mobile);
					return;
                }

//                $.post( "/cxh/front/register_member.cs",
                $.post( "/front/register_member.cs",
					{ province: province,city:city,area:area,name:name,mobile:mobile,index:insurance})
                    .done(function( data ) {
                        alert(data );
                        location.reload(true);
                    });
				//13569874561

                /*alert("province: " + province + " \t city: " + city + "\t area: " + area +
                    "\t name: " + name + " \t mobile: " + mobile + " \t in: " + insurance
                );*/
            });
		});
		</script>
	</div>
</div>
<!--投保流程-->
<div class="tblc_main">
	<div class="tblc_title"><p>投保流程</p></div>
	<div class="tblc_list">
		<ul>
			<li style="margin-right:75px"><i><img src="/img/front/tblc_01.png"></i><p>提交</p><span>车主在车险汇上提交投车辆的资料及险种</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/tblc_02.png"></i><p>报价</p><span>多家保险公司报价，在线获取报价</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/tblc_03.png"></i><p>付款</p><span>可支付宝转账，无支付宝的用户可跟业务员去网点刷卡</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/tblc_04.png"></i><p>收单</p><span> 车主收到保险单后可打相应保险公司客服电话或者官网验真</span></li>
			<li><i><img src="/img/front/tblc_05.png"></i><p>评价</p><span>对保险公司以及车险汇进行客观评价</span></li>
		</ul>
	</div>
</div>
<!--车险介绍-->
<div class="tblc_main">
	<div class="tblc_title"><p>车险介绍</p></div>
	<div class="cxjs_list">
		<ul id="companyUl">
			<li class="li_now">交强险</li>
			<li>机动车辆损失险</li>
			<li>第三者责任险</li>
			<li>盗抢险</li>
			<li>车上人员责任险</li>
			<li>车身划痕损失险</li>
			<li>玻璃单独破碎险</li>
			<li>自燃损失险</li>
			<li>发动机特别损失险</li>
			<li>不计免赔率特约险</li>
		</ul>
		<div id="companyList" class="cxjs_txt">
			<div id="companyList1" class="tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">机动车交通事故责任强制保险</div>
					<p class="cxjs_txt_main_p">国家法规强制要求，每位车主必须投保的险种</p>
					<p class="cxjs_txt_main_p">在交通事故中造成受害人（不包括本车人员和被保险人）的人身伤亡、财产损失，在责任限额内予以保障性的赔偿</p>
					<p class="cxjs_txt_main_p">一般情况下赔偿最高限额为12.2万元，交强险赔偿额度有限，所以商业险补充是非常必要</p>
				</div>
			</div>
			<div id="companyList2" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">机动车辆损失险</div>
					<p class="cxjs_txt_main_p">是一种负责发生保险事故时，赔偿自己车辆损失的险种。</p>
					<p class="cxjs_txt_main_p">不少车主好奇，车损险是不是必买险种？作用有多大呢？</p>
					<p class="cxjs_txt_main_p">尤其是新手，免不了刮刮碰碰。比如车子撞了护拦或别人车，自己车子受损。这时交强险是无法承保自己爱车的损失。</p>
					<p class="cxjs_txt_main_p">机动车损失险在车子发生碰撞时，来赔偿自己爱车损失的费用。</p>
					<p class="cxjs_txt_main_p">以防万一，新手可以额外投保车损险，同时加上不计免陪险，否则只给赔付80%。</p>
					<p class="cxjs_txt_main_tit2">车损险能保的范围其实挺广</p>
					<p class="cxjs_txt_main_p">车辆发生碰撞、倾覆</p>
					<p class="cxjs_txt_main_p">车辆发生火灾、爆炸</p>
					<p class="cxjs_txt_main_p">车辆遭遇自然灾害</p>
					<p class="cxjs_txt_main_p">外界物体倒塌或坠落、保险车辆行驶中平行坠落造成车辆损失</p>
					<p class="cxjs_txt_main_p">车辆有驾驶人随船照料的情况下，载运保险车辆的渡船遭受以上自然灾害造成车辆损失</p>
				</div>
			</div>
			<div id="companyList3" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">第三者责任险</div>
					<p class="cxjs_txt_main_p">是交强险的有力补充，为赔偿他人损失（包括人和财物）提供保障。</p>
					<p class="cxjs_txt_main_p">交强险赔得少，赔付最高不超过2000元，超过这部分的修理费就需要车主自己掏腰包。</p>
					<p class="cxjs_txt_main_tit2">案例</p>
					<p class="cxjs_txt_main_p">就以2012年关注度很高的“雅阁撞到劳斯莱斯”事故来说，由于是雅阁车在转弯时未让直行的劳斯莱斯，此起事故为雅阁车全责。根据4S店的定损，劳斯莱斯最终维修费定为35万元，如果雅阁车投保较高保额的三者险加不计免陪险的话，便可放心进行维修了</p>
				</div>
			</div>
			<div id="companyList4" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">盗抢险</div>
					<p class="cxjs_txt_main_p">为保障您的车被盗被抢或者被盗抢后，受到损坏或者车上零部件、附属设备丢失需要修复的合理费用提供保障。</p>
					<p class="cxjs_txt_main_p">盗抢险简单来说就是个移动的安全车库！爱车被盗窃、抢劫、抢夺，警察找了60天还未查明下落时；当爱车在被抢劫、抢夺过程中，受到损坏；当爱车全车被盗窃、抢劫、抢夺后，受到损坏或车上零部件、附属设备丢失时，这个安全车库会提供最全面的保障。</p>
				</div>
			</div>
			<div id="companyList5" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">车上人员责任险</div>
					<p class="cxjs_txt_main_p">为乘坐您车的亲人、爱人、挚友们提供更全面的保障。</p>
					<p class="cxjs_txt_main_p">如车撞树上致使车上人员受伤或死亡；如驾驶员在行驶过程中紧急刹车导致的本车人员伤亡；如车辆相撞致使本车车上人员受伤或死亡（本车全责），车上人员会提供最全面的保障</p>
				</div>
			</div>
			<div id="companyList6" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">车身划痕损失险</div>
					<p class="cxjs_txt_main_p">车身油漆单独划伤赔偿，建议购买人群：新车车主和完美主义者！</p>
					<p class="cxjs_txt_main_p">“车身划痕险”里的“划痕”，是指没有明显碰撞痕迹所产生的划痕，就是那些尖锐的物体，比如刀、石子等，对车身油漆的破坏。是人为故意划车所导致的损失，由保险公司进行赔偿。</p>
					<p class="cxjs_txt_main_p">车身的擦蹭，无论是车与车之间，还是车与固定物体之间，基本都不属于划痕险范畴，而属于车损险的碰撞责任，在车损险项下赔偿。</p>
					<p class="cxjs_txt_main_p">车身油漆单独划伤赔偿，建议购买人群：新车车主和完美主义者！</p>
				</div>
			</div>
			<div id="companyList7" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">玻璃单独破碎险</div>
					<p class="cxjs_txt_main_p">玻璃单独破碎险并非鸡肋，商业车险组合很实惠。</p>
					<p class="cxjs_txt_main_p">“因车损险条款列明的原因造成车辆损失同时玻璃也破碎的属于车辆损失险，如果仅仅是玻璃单独破碎，是不能予以赔偿的，需要单独投保玻璃单独破碎保险。”</p>
					<p class="cxjs_txt_main_p">所谓玻璃单独破碎险，即车辆在停放或使用过程中，其他部分没有损坏，仅风挡玻璃和车窗玻璃单独破碎，保险公司负责赔偿。汽车在使用过程中，有太多不可预测的情况出现，汽车玻璃作为非常重要的组成部分有着易破损的特性，一旦受损将严重影响驾驶安全并有很大的隐患。</p>
					<p class="cxjs_txt_main_p">飞石、车祸、贴膜等都有可能使玻璃造成损伤，像王先生这种受到意外“暗算”的情况也时有发生。所以，仓江建议车主在购买车辆损失险的同时，最好再购买一份玻璃单独破碎险，毕竟一块风挡玻璃也价格不菲。</p>
				</div>
			</div>
			<div id="companyList8" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">自燃损失险</div>
					<p class="cxjs_txt_main_p">夏天外出办事，找不到地下停车场而被迫将车停在路边，太阳直射下车内温度高达60℃。挡风玻璃前放了一瓶香水，外观好看，还是是多面切割的，正好一面被太阳直射，形成“放大镜”。含有10%酒精的液体闪点仅仅为49℃，车子一下自燃了。</p>
					<p class="cxjs_txt_main_p">车辆的自燃主要与驾驶人或者使用者在车内遗留某些物品的关系最大。</p>
					<p class="cxjs_txt_main_p">行驶中因为汽车原因的起火，则可能与是否加装电器精品或者改装电路有关。</p>
					<p class="cxjs_txt_main_p">在保险条款中，对自燃的解释是指在没有外界火源的情况下，由于本车电器、线路、供油系统、供气系统等被保险机动车自身原因发生故障或货物自身原因起火燃烧。</p>
				</div>
			</div>
			<div id="companyList9" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">发动机特别损失险</div>
					<p class="cxjs_txt_main_p">本来艳阳高照，忽然之间又大雨瓢泼了，积水较深。一不小心，熄火了。一会儿爱车就变成潜水艇了，发动机进水没商量。因为暴雨带来的维修和更换部件，都可以走车损险，但发动机进水除外。</p>
				</div>
			</div>
			<div id="companyList10" class="hid tabjiehuan">
				<div class="cxjs_txt_main">
					<div class="cxjs_txt_main_tit">不计免赔率特约损失险</div>
					<p class="cxjs_txt_main_p">为您提供出险后的全额赔偿保障。包括车损、三者、车上人员、盗抢都相应的免赔金额，增加不计免赔险后，最高免赔金额可以达到20%，最大程度的降低您的意外开支。</p>
					<p class="cxjs_txt_main_p">比如：江先生（车牌苏A-××086，驾龄5年），当初买的就是二手车，所以投保图便宜就放弃不计免赔险。结果8月份开车撞上路边护栏，修了4000多元，保险公司扣掉20%免赔率，我自己掏了800多元，其实不计免赔险的保费还不到400元，自己 “亏大了”</p>
				</div>
			</div>
		
		</div>
		<script>
		$(function(){
			 /*切换*/
			 $("#companyUl li").bind("click",function(){
					 $(this).addClass("li_now").siblings().removeClass("li_now");
					 $('#companyList .tabjiehuan').eq($(this).index()).show().siblings().hide();
			 });
		})
		</script>
	</div>
	



</div>
<!--我们的特色-->
<div class="tblc_main">
	<div class="tblc_title"><p>我们的特色</p></div>
	<div class="tblc_list">
		<ul>
			<li style="margin-right:75px"><i><img src="/img/front/wmdts_01.png"></i><p>保险超市</p><span>我们与多家保险公司合作让您有更多选择</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/wmdts_02.png"></i><p>价格更优惠</p><span>官方直销 私家车商业险低至55折，再加高额现金优惠</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/wmdts_03.png"></i><p>咨询服务</p><span>定制相应的车险方案，提供专业的出险处理建议，协助办理相关手续</span></li>
			<li style="margin-right:75px"><i><img src="/img/front/wmdts_04.png"></i><p>一键比价</p><span>车险险种自由搭配，多家保险公司在线比价</span></li>
			<li><i><img src="/img/front/tblc_05.png"></i><p>车险到期提醒</p><span>方便车主及时续保，告别脱保无折扣，服务更贴心</span></li>
		</ul>
	</div>
</div>
<!--页尾-->
<div class="public_bottom">
	<div class="public_bottom_main1">
		<div class="xinxi_main">
			<p>地址：河南省南阳市工业南路兴达商务楼六楼G室</p>
			<p>Copyright @ 车险汇 &nbsp;&nbsp;&nbsp;&nbsp;营业执照注册号：9141130235803319XY 备案号：豫ICP备12010516号-2</p>
		</div>
		<div class="dianhua_main">
			<div class="dianhua_img"><img src="/img/front/dianhua.png"></div>
			<div class="dianhua_txt1">客服热线&nbsp;&nbsp;4000080099</div>
			<div class="dianhua_txt2">周一至周五&nbsp;9:00-18:00（仅收市话费）</div>
		</div>
	</div>
</div>
</body>
</html>