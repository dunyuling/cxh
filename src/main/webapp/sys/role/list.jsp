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
    	
    	// 预览事件
    	function setPermission(){
    		var data = getSelectedRow("tab_role");
    		if(null == data){
    			return;
    		}
    		$.openDlg({
    			url:'transfer.cs?action=setPermission&id=' + data.id,
    			title:'设定角色权限',
    			width:'420px',
    			height:'90%',
    		});
    	}
    	
    </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<!--           <div class="ibox " style=""> -->
<!--               <div class="ibox-content"> -->
					
	              <div class="row row-lg">
<!-- 		                <div  style="padding-bottom: 10px"> -->
<!-- 		                        <div class="form-inline"> -->
<!-- 		                            <div class="form-group"> -->
<!-- 		                                <label for="code"  >角色代码：</label> -->
<!-- 		                                <input type="text" placeholder="请输入角色代码" id="code" class="form-control" /> -->
<!-- 		                            </div> -->
<!-- 		                            <div class="form-group"> -->
<!-- 		                                <label for="name"  >角色名称：</label> -->
<!-- 		                                <input type="text" placeholder="请输入角色名称" id="name" class="form-control" /> -->
<!-- 		                            </div> -->
<!-- 		                            <button class="btn " type="button" onclick="doQuery()">查询</button> -->
<!-- 		                        </div> -->
<!-- 		                </div> -->
	              
	              		 <cs:toolbar title="角色" tableId="tab_role" width="768px" height="420px"  menuCode="${menuCode }" hdMenu="1"> 
	              		 		<p:permission menuCode="${menuCode }" action="16">
		              		 		<button class="btn btn-sm btn-info" type="button" onclick="setPermission()"><i class="fa fa-info"></i>&nbsp;分配菜单权限</button>
	              		 		</p:permission>
	              		 </cs:toolbar>
		                  <table id="tab_role"  
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
		                              <th data-field="code">角色代码</th>
		                              <th data-field="name">名称</th>
		                              <th data-field="note">描述</th>
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
