<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
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
	              		 <cs:toolbar title="数据字典" tableId="tab_dic" width="768px" height="420px" menuCode="${menuCode }"></cs:toolbar>
		                  <table id="tab_dic"  
		                  	   data-toggle="table"
						       data-url="list2.cs"
						       data-method="get"
						       data-click-to-select="true"
						       data-pagination="true"
						       data-data-type="json"
						       data-show-refresh="true"
						       data-show-columns="true"
						       data-show-toggle="true"
						       data-search-on-enter-key="true"
						       data-toolbar="#def_toolbar"  
						       data-side-pagination="server"
						       data-query-params="queryParams"
						       data-mobile-responsive="true">
							  <thead>
		                          <tr>
		                              <th data-checkbox="true"	data-click-to-select="true"></th>
		                              <th data-field="id" data-visible="false">id</th>
		                              <th data-field="type">类型</th>
		                              <th data-field="code">代码</th>
		                              <th data-field="name">字典名称</th>
		                              <th data-field="value">值</th>
		                              <th data-field="note">描述</th>
		                              <th data-field="isfixed">是否固定值</th>
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
