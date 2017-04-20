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
    
		var user_id = "${user.id}";
    
    	function test(value,row,index){
//     		return '<a href="#">' + row['id'] + '</a>';
    		return '<a href="#">编辑 </a>|<a href="#">删除</a>';
    	}
    	
    	// 预览事件
    	function setUserRole(){
    		var role_codes = [];
    		
    		$($('#tab_role').bootstrapTable('getSelections')).each(function() {
    			role_codes.push(this.role_code);
            });
    		
    		$.post('setUserRole.cs', { roleCodes:role_codes.toString(), userId:user_id }, function(data){
//     			var index = parent.layer.getFrameIndex(window.name);  
//    				parent.layer.close(index);
    			closeCurrentDlg();
    		});
    		
    	}
    	
        function stateFormatter(value, row, index) {
            if (row.set === 1) {
                return {
                    checked: true
                };
            }
            if (row.set === 0) {
                return {
                    checked: false
                }
            }
            return value;
        }
    	
    </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<!--           <div class="ibox " style=""> -->
<!--               <div class="ibox-content"> -->
	              <div class="row row-lg">
	              		<div class="col-sm-12">
		                  <table id="tab_role"  
		                  	   data-toggle="table"
						       data-url="getRoleList.cs?userId=${user.id }"
						       data-method="get"
						       data-data-type="json"
						       data-query-params="query"
						       data-mobile-responsive="true"
								>
		                      <thead>
		                          <tr>
		                              <th data-checkbox="true"	data-click-to-select="true" data-formatter="stateFormatter"></th>
		                              <th data-field="role_id" data-visible="false">id</th>
		                              <th data-field="role_code">角色代码</th>
		                              <th data-field="role_name">名称</th>
		                              <th data-field="set" data-visible="false">标识</th>
<!-- 		                              <th data-formatter="test">操作</th> -->
		                          </tr>
		                      </thead>
		                  </table>
	              		<button class="btn btn-primary" type="button" onclick="setUserRole()"><i class="fa fa-info"></i>&nbsp;确定</button>
	              		</div>
	              	
	              </div>
				 
<!--               </div> -->
<!--           </div> -->
    </div>
    

</body>

</html>
