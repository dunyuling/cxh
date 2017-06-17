<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/common/head.jsp"%>

    <%--<script type="text/javascript" src="/js/plugins/webuploader/uploader.js"></script>
    <script type="text/javascript" src="/js/plugins/webuploader/webuploader.min.js"></script>
    <cs:initFile></cs:initFile>--%>

    <%@taglib prefix="cs" tagdir="/WEB-INF/tags" %>
</head>

<body>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
            	 <cs:form_validate formId="addNewsForm"/>
                  <form class="form-horizontal" action="add.cs" id="addNewsForm" method="POST" enctype="multipart/form-data" >
                      <div class="form-group">
                          <label class="col-sm-3 control-label">名称：</label>
                          <div class="col-sm-6">
                              <input id="title" name="title"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入名称'}" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">图片：</label>
                          <div class="col-sm-6">
                              <input id="img" name="img" type="file" class="form-control" validate="{required:true}" validateMessage="{required:'请上传图片'}" required >
                              <%--<cs:file name="img"  type="150" />--%>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-3 control-label">描述：</label>
                          <div class="col-sm-6">
                              <input id="description" name="description"  class="form-control" validate="{required:true}" validateMessage="{required:'请输入描述'}" >
                          </div>
                      </div>
                     <div class="form-group">
                          <label class="col-sm-3 control-label">内容：</label>
                          <div class="col-sm-6">
                              <%--<input id="area" name="area" class="form-control" validate="{required:true}" validateMessage="{required:'请输入县/区'}" >--%>
                              <cs:wordedit name="内容"/>
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

