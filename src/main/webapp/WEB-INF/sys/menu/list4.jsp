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
    	
    	function setAction(){
    		
    		var data = getSelectedRow("tab_menu");
    		if(null == data){
    			return;
    		}
    		
    		$.openDlg({
    			url:'transfer.cs?action=setAction&id=' + data.id,
    			title:"设定操作权限 - " + data.name,
    			width:'100%',
    			height:'100%',
    		});
    	}
    	
    </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<!--           <div class="ibox " style=""> -->
<!--               <div class="ibox-content"> -->
	              <div class="row row-lg">
	              		<div class="col-sm-12">
	              		 <cs:toolbar title="菜单" tableId="tab_menu" width="768px" height="420px" menuCode="181">
<!-- 	              		 	<button class="btn btn-sm btn-info" type="button" onclick="setAction()"><i class="fa fa-info"></i>&nbsp;设点操作权限值</button> -->
	              		 </cs:toolbar>
		                  <table id="tab_menu"  
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
						       data-mobile-responsive="true"
								>
		                      <thead>
		                          <tr>
		                              <th data-checkbox="true"	data-click-to-select="true"></th>
		                              <th data-field="id">菜单id</th>
		                              <th data-field="pid">上级菜单ID</th>
		                              <th data-field="name">菜单名称</th>
		                              <th data-field="code">代码</th>
		                              <th data-field="url">url</th>
		                              <th data-field="weight">权重</th>
<!-- 		                              <th data-formatter="test">操作</th> -->
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
