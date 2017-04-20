<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<head>
	<%@ include file="../../common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="addRoleForm"/>
                  <form class="form-horizontal" action="add.cs" id="addRoleForm" method="POST">
                      <div class="form-group">
                          <label class="col-sm-3 control-label">代码：</label>
                          <div class="col-sm-6">
                              <input id="code" name="code"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入code,不能重复'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">名称：</label>
                          <div class="col-sm-6">
                              <input id="name" name="name" class="form-control" validate="{required:true}" validateMessage="{required:'请输入名称'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">描述：</label>
                          <div class="col-sm-6">
                              <input id="note" name="note" class="form-control"  >
                          </div>
                      </div>
                     
<!--                       <div class="form-group"> -->
<!--                           <label class="col-sm-3 control-label">：</label> -->
<!--                           <div class="col-sm-6"> -->
<!--                               <input id="weight" name="weight"  class="form-control" > -->
<!--                           </div> -->
<!--                       </div> -->
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

