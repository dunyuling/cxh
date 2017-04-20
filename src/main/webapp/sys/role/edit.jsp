<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            	 <cs:form_validate formId="addRoleForm"/>
                  <form class="form-horizontal" action="edit.cs" id="addRoleForm" method="POST">
                      <div class="form-group">
                          <label class="col-sm-3 control-label">代码：</label>
                          <div class="col-sm-6">
                          	  <input id="id" name="id" value="${role.id }" class="form-control" type="hidden" >
                              <input id="code" name="code"  class="form-control"  value="${role.code }" validate="{required:true}" validateMessage="{required:'请输入code,不能重复'}" readonly="readonly">
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">名称：</label>
                          <div class="col-sm-6">
                              <input id="name" name="name" class="form-control" value="${role.name }" validate="{required:true}" validateMessage="{required:'请输入名称'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">描述：</label>
                          <div class="col-sm-6">
                              <input id="note" name="note" class="form-control"  value="${role.note }" >
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

