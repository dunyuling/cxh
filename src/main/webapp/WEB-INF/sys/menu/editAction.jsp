<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<head>
	<%@ include file="/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="addActionForm"/>
                  <form class="form-horizontal" action="editAction.cs" id="addActionForm" method="POST">
                     <div class="form-group">
                          <label class="col-sm-3 control-label">菜单名称：</label>
                          <div class="col-sm-6">
                              <input  class="form-control" value="${menu.name }" type="text" readonly="readonly" >
                          </div>
                      </div>
                     <div class="form-group">
                          <label class="col-sm-3 control-label">action名称：</label>
                          <div class="col-sm-6">
                              <input id="name" name="name" class="form-control" validate="{required:true, minlength:1}" value="${ac.name }" >
                              <input id="id" name="id" class="form-control" value="${ac.id }" type="hidden" >
                              <input id="menuCode" name="menuCode" class="form-control" value="${menu.code }" type="hidden" >
                          </div>
                      </div>
                     <div class="form-group">
                          <label class="col-sm-3 control-label">val：</label>
                          <div class="col-sm-6">
                              <input id="val" readonly="readonly" name="val" class="form-control" value="${ac.val }" >
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

