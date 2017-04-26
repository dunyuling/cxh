<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/common/head.jsp"%>
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="editAddressForm"/>
                  <form class="form-horizontal" action="edit.cs" id="editAddressForm" method="POST">
                     <div class="form-group">
                          <label class="col-sm-3 control-label">省：</label>
                          <div class="col-sm-6">
                          		<input id="id" name="id" value="${address.id }" class="form-control" type="hidden" >
                              <input id="province" name="province"  value="${address.province }" class="form-control" validate="{required:true}" validateMessage="{required:'请输入省'}" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">市：</label>
                          <div class="col-sm-6">
                              <input id="city" name="city"  value="${address.city }" class="form-control" validate="{required:true}" validateMessage="{required:'请输入市'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">县/区：</label>
                          <div class="col-sm-6">
                              <input id="area" name="area" value="${address.area }" class="form-control" validate="{required:true}" validateMessage="{required:'请输入县/区'}" >
                          </div>
                      </div>
                      
                     <div class="form-group">
                          <label class="col-sm-3 control-label">每次费用(单位角)：</label>
                          <div class="col-sm-6">
                              <input id="amount" name="amount" value="${amount}" class="form-control" validate="{required:true}" validateMessage="{required:'请输入费用'}" >
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