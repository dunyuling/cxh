<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="../../common/head.jsp"%> --%>

<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="type" type="java.lang.Integer" required="true" %>
<%@ attribute name="desc" type="java.lang.String" required="false" %>
<%@ attribute name="url" type="java.lang.String" required="false" %>
<%@ attribute name="action" type="java.lang.String" required="false" %>

<% 
	String path2 = request.getContextPath(); 
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）: 
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + path2+"/"; 
%> 

<% if(type >= 100 && type < 200){ %>
		<img alt="" style="height:100px; width:100px; cursor: pointer;"   
			<%if(null != url && !"".equals(url)){ %>
				src = "${url}"
			<%}else{ %>
			    src = "/res/img/a1.jpg"
			<%}%>
			<%if(!"view".equals(action)){ %>
				class="show-img"
			<%}else{ %>
				onclick="javascript:$.showPic(this.src)"
			<%} %>
		>
	 	<input name="${name }" type="hidden" id="${name }" class="input normal upload-path"  value="${url }"  />
		<div class="upload-box upload-img" style="display: none;" fileType="${type }"></div>
<%} else if(type >= 200){%>
<!-- 		<div> -->
		<div alt="" style="cursor: pointer; width: 95%; float: left;"  class="form-control  show-file" >${desc}</div>
			<%if((null != url || !"".equals(url)) && !"add".equals(action)  ) {%>
				<a href="${url }" style="width: 5%;"  target="_self" download="${name }" >下载</a>
			<%} %>
<!-- 			</div> -->
		
        <input name="${name }" type="hidden" id="${name }" class="input normal upload-path" value="${url }" />
         <div class="upload-box upload-file" style="display: none;" fileType="${type }"></div>
<%} %>
<jsp:doBody/>