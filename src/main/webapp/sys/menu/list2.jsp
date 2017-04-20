<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<head>
    <title></title>
    <%@ include file="../../common/head.jsp"%>
    
    <script type="text/javascript">
    
    	var tree = null;
    	
    	function p_add()
    	{
    		var node = tree.getSelected();	
    		if(node)
    		{
    			var _id = node.data.text.split('-')[1].trim();
    			var _pid = $('#menu_' + _id.trim()).attr('pid').trim();
    		}
    		else
    		{
    		 	_id = 0;			
			 	_pid = 0;  // 获取pid
    			
    		}
			 s_open_dlg('新增', 'transfer.cs?action=add&pid='+_pid + '&id=' + _id);
			
			 	
    	}
    	
    	function p_modify(){
    		var node = tree.getSelected();
    		if(node){
    			var _id = node.data.text.split('-')[1].trim();
    			var _pid = $('#menu_' + _id.trim()).attr('pid').trim();
    			
    			if(parseInt(_id) == 0){
    				return;
    			}
    			
    			s_open_dlg('修改', 'transfer.cs?action=modify&pid='+_pid + '&id=' + _id);
    		}
    		else
    			s_show_dlg_question('请先选择节点');
    		
    	}
    	
    	function p_del()
        {
    		var node = tree.getSelected();	
    		var _id = null;
    		var _pid = null;
    		if(node)
    		{
    			 _id = node.data.text.split('-')[1];				
    			 _pid = $('#menu_' + _id.trim()).attr('pid');  // 获取pid
    			
    		}
    		else
    			s_show_dlg_question('请先选择节点');
        }
    
        $(function ()
        {
             tree = $("#tree").ligerTree({ checkbox: false, parentIcon: null, childIcon: null });
            
            $("#toptoolbar").ligerToolBar({ items: [
                  {
                      text: action.add, 
                      click: p_add, 
                      icon:'add'
                   },
                  { line:true },
                  { text: action.update, click: p_modify },
                  { line:true },
                  { text: action.del, click: p_del }
              ]
              });
	   });
    </script>
</head>
<body>  
		<div id="toptoolbar" ></div> 
		
	    <!--简易模式-->     
	    <div style="width:99%; float:left;  border:1px solid #ccc; overflow:auto;  ">
		    <ul id="tree">
		    	<li> 
		    	<span> 
		    		<label style="display: none" id="menu_0" pid="0"></label>
		    		系统菜单 - 0
		    	</span>
		    	<ul>
		    			<s:forEach items="${superMenuList }" var="superMenu">
			        	<li>
			        		<span>
			        			<label style="display: none" id="menu_${superMenu.id }" pid="${superMenu.pid }"></label>
			        			${superMenu.name } - ${superMenu.id}
			        		</span>
			        	</li>
			        </s:forEach>
		    	</ul>
		    	</li>
		    </ul>
	    </div>

        <div style="display:none">
     
    </div>
</body>
</html>
