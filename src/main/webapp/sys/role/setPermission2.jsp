<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    
    	var role_id = "${role.id}";
    	
    	var action_list;
    
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
        
        
        function setActionVal(value,row,index){
//         	if(parseInt(row.menu_pid) != 0){
//         		return '<select id="action_select_' + row.menu_code + '"   onclick="select_action(' + row.menu_code +  ')">'  +  
//         		'<option value="0">  --选择可以操作功能 -- </option>'
//         		'</select>';
//         	}
//         	else{
//         		return '';
//         	}

// 			var data = toJSON(${actionList});
			var _ret = '';
			for(var obj in action_list){
				if(action_list[obj].menu_code == row.menu_code){
					_ret += '<input type="checkbox" name="' + row.menu_code + '" value="' + action_list[obj].action_val + '" ';
					if(parseInt(action_list[obj].set) > 0){
						_ret += ' checked="checked" ';
					}
					_ret += '>' + action_list[obj].action_name;
				}
			}
			
			return _ret;
    		
    	}
        
        function select_action(menu_code){
        	var _id = 'action_select_' + menu_code;
        	if($('#'+_id + ' option').size() == 1){
        		$.get('getMenuAction.cs?menuCode=' + menu_code, function(data){
    				for(var obj in data){
    					$('#' + _id).append('<option value="' + data[obj].val + '">' + data[obj].name + '</option>');
//     					$('#' + _id).append('<input type="checkbox" >' + data[obj].name);
    				}
    			});
        	}
			
        }
        
        function setPermission(){
        	var acs = [];
			$($('#tab_menu').bootstrapTable('getSelections')).each(function() {
				var _t = {};
				_t.menu_code = this.menu_code;
				_t.action_val = 0;
				$('input[name="'+ this.menu_code +'"]:checked ').each(function(){
					_t.action_val += parseInt($(this).val());
				});
				acs.push(_t);
// 				acs.push(JSON.stringify(_t));
            });
			
			
			$.post('setPermission.cs', { acs: JSON.stringify(acs),roleId:role_id }, 
					function (text, status) 
					{ 
            			 closeCurrentDlg();
					}
			);
        }
        
    </script>
    
    
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<!--           <div class="ibox " style=""> -->
<!--               <div class="ibox-content"> -->
				 <script>
					 $.get('getAllAction.cs?roleId=' + role_id, function(data){
			        		action_list = data;
			        	});
				 </script>

	              <div class="row row-lg">
	              		<div class="col-sm-12">
		                  <table id="tab_menu"  
		                  	   data-toggle="table"
						       data-url="getListMenu.cs?roleId=${role.id}"
						       data-method="get"
						       data-data-type="json"
						       data-query-params="query"
						       data-mobile-responsive="true"
								>
		                      <thead>
		                          <tr>
		                              <th data-checkbox="true"	data-click-to-select="true" data-formatter="stateFormatter"></th>
		                              <th data-field="menu_id" >菜单id</th>
		                              <th data-field="menu_pid">上级菜单ID</th>
		                              <th data-field="menu_name">菜单名称</th>
		                              <th data-field="menu_code">代码</th>
		                              <th data-field="action_val" data-formatter="setActionVal">操作权限</th>
		                          </tr>
		                      </thead>
		                  </table>
	              		<button class="btn btn-primary" type="button" onclick="setPermission()"><i class="fa fa-info"></i>&nbsp;确定</button>
	              		</div>
	              	
	              </div>
				 
<!--               </div> -->
<!--           </div> -->
    </div>
    

</body>

</html>
