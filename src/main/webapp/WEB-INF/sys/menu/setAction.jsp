<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<%@ include file="/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
    <script src="/js/plugins/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>
    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js" type="text/javascript"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
    <script>
    	
    	function test(value,row,index){
//     		return '<a href="#">' + row['id'] + '</a>';
    		return '<a href="#">编辑 </a>|<a href="#">删除</a>';
    	}
    	
    </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<!--           <div class="ibox " style=""> -->
<!--               <div class="ibox-content"> -->
	              <div class="row row-lg">
	              		<div class="col-sm-12">
	              		 <cs:toolbar title="操作权限" tableId="tab_action" width="768px" height="420px" menuCode="181"  hdMenu="1"> </cs:toolbar>
		                  <table id="tab_action"  
		                  	   data-toggle="table"
						       data-url="<%=basePath %>action/getMenuActionList.cs?menuCode=${menu.code }"
						       data-method="get"
						       data-click-to-select="true"
						       data-data-type="json"
						       data-toolbar="#def_toolbar"  
						       data-mobile-responsive="true"
								>
		                      <thead>
		                          <tr>
		                              <th data-checkbox="true"	data-click-to-select="true"></th>
		                              <th data-field="name">操作名称</th>
		                              <th data-field="val">权限值</th>
		                          </tr>
		                      </thead>
		                  </table>
	              		
	              		</div>
	              	
	              </div>
				 
<!--               </div> -->
<!--           </div> -->
    </div>
    

</body>

</html>
