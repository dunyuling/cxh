<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<head>
	<%@ include file="../../common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link href="../res/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
    <script src="../res/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="../res/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="../res/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script>
    	
    	function test(value,row,index){
//     		return '<a href="#">' + row['id'] + '</a>';
    		return '<a href="#">编辑 </a>|<a href="#">删除</a>';
    	}
    	
    	function init_pwd(){
    		var data = getSelectedRow("tab_user");
    		if(null == data){
    			return;
    		}
    		
    		$.confirm("确定初始化密码吗？ ", function(){
    			$.post('initPwd.cs?id=' + data.id, 
    					function(result){
    						window.location.reload();
    						}
    			);
    		});
    	}
    	
    	
    	// 分配角色
    	function setRole(){
    		var data = getSelectedRow("tab_user");
    		if(null == data){
    			return;
    		}
    		$.openDlg({
    			url:'transfer.cs?action=setRole&id=' + data.id,
    			title:'分配角色',
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
	              		 <cs:toolbar title="用户" tableId="tab_user" width="768px" height="420px" menuCode="${menuCode }">
	              		 	<p:permission menuCode="${menuCode }" action="16">
	              		 		<button class="btn btn-sm btn-info" type="button" onclick="init_pwd()"><i class="fa fa-info"></i>&nbsp;初始化密码</button>
	              		 	</p:permission>
	              		 	
	              		 	<p:permission menuCode="${menuCode }" action="32">
	              		 			<button class="btn btn-sm btn-info" type="button" onclick="setRole()"><i class="fa fa-info"></i>&nbsp;分配角色</button>
	              		 	</p:permission>
	              		 </cs:toolbar>
		                  <table id="tab_user"  
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
		                              <th data-field="id" data-visible="false">id</th>
		                              <th data-field="code">代码</th>
		                              <th data-field="name">名称</th>
		                              <th data-field="account">账户</th>
		                              <th data-field="pwd">密码</th>
		                              <th data-field="sex">性别</th>
		                              <th data-field="status">状态</th>
		                              <th data-field="createDate">创建时间</th>
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
