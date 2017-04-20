<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../common/head.jsp"%>
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                  <form class="form-horizontal" >
<!--                       <div class="form-group"> -->
<!--                           <label class="col-sm-3 control-label">id：</label> -->
<!--                           <div class="col-sm-6"> -->
<%--                               <input id="id" name="id" value="${menu.id }" class="form-control" type="text" readonly="readonly"> --%>
<!--                           </div> -->
<!--                       </div> -->
                      <div class="form-group">
                          <label class="col-sm-3 control-label">上级菜单：</label>
                          <div class="col-sm-6">
                          	<select class="form-control m-b" id="pid" name="pid" disabled="disabled">
                              		<c:forEach items="${menuList }" var="superMenu">
                              			<option value="0">请选择</option>
                              			<option value="${superMenu.id }" 
                              				<c:if test="${superMenu.id == menu.pid }"> selected</c:if> 
                              			>${superMenu.name}</option>
                              		</c:forEach>
                              </select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">名称：</label>
                          <div class="col-sm-6">
                              <input id="name" name="name" value="${menu.name }" class="form-control" readonly="readonly">
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">代码：</label>
                          <div class="col-sm-6">
                              <input id="code" name="code" value="${menu.code }" class="form-control" readonly="readonly">
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">url：</label>
                          <div class="col-sm-6">
                              <input id="url" name="url"  value="${menu.url }" class="form-control" readonly="readonly" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">权重：</label>
                          <div class="col-sm-6">
                              <input id="weight" name="weight" value="${menu.weight }" class="form-control" readonly="readonly" >
                          </div>
                      </div>
                  </form>
            </div>
        </div>

    </div>


</body>

</html>

