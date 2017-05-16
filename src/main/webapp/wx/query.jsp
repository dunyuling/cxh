<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/mobiscroll_002.js"></script>
<script type="text/javascript" src="js/mobiscroll_004.js"></script>
<script type="text/javascript" src="js/mobiscroll.js"></script>
<script type="text/javascript" src="js/mobiscroll_003.js"></script>
<script type="text/javascript" src="js/mobiscroll_005.js"></script>
<script type="text/javascript" src="../common/translateType.js"></script>

<div class="jilu_main">${count}条信息</div>
<div class="page02_list_main">
    <c:forEach items="${list}" var="obj">
        <div class="page02_lists">
            <a href="/wx/detail.cs?path=total&id=${obj.id}&userid=${user_id}" class="zhuangtai">
                <c:if test="${obj.visit}">
                    <div class="zhuangtai_ok">已回访</div>
                </c:if>
                <c:if test="${!obj.visit}">
                    <div class="zhuangtai_no">未回访</div>
                </c:if>

                <div class="lists_p1">姓名：${obj.name}</div>
                <p>电话：${obj.mobile}</p>
                <p id="need_${obj.id}">${obj.type}</p>
                <p>时间：${obj.createDate}</p>
            </a>
        </div>
    </c:forEach>
</div>
