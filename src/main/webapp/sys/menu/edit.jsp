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
            	  <cs:form_validate formId="editMenuForm"/>
                  <form class="form-horizontal" id="editMenuForm" action="edit.cs" method="POST">
                      <div class="form-group">
                          <label class="col-sm-3 control-label">上级菜单：</label>
                          <div class="col-sm-6">
                          	<select class="form-control m-b" id="pid" name="pid" >
                              		<option value="0">请选择</option>
                              		<c:forEach items="${menuList }" var="superMenu">
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
                              <input id="id" name="id" value="${menu.id }" class="form-control" type="hidden"  >
                              <input id="name" name="name" value="${menu.name }" class="form-control" validate="{required:true, minlength:1}" validateMessage="{required:'请输入菜单名称',minlength:'至少一个字符以上'}"  >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">代码：</label>
                          <div class="col-sm-6">
                              <input id="code" name="code" value="${menu.code }" readonly="readonly" class="form-control" validate="{required:true, minlength:1}" validateMessage="{required:'请输入菜单代码，不能重复',minlength:'至少一个字符以上'}"  >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">url：</label>
                          <div class="col-sm-6">
                              <input id="url" name="url"  class="form-control" value="${menu.url }" validate="{required:true}" validateMessage="{required:'请输入url'}" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">排序：</label>
                          <div class="col-sm-6">
                              <input id="weight" name="weight"  class="form-control" value="${menu.weight }">
                          </div>
                      </div>
                      <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3 pull-left">
                                    <button class="btn btn-default btn-dlg-close">取消</button>
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                       </div>
                  </form>
            </div>
        </div>

    </div>


</body>

</html>

