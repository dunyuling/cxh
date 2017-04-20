<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://www.test.com/jsp/permission" %>
<!doctype html>
<head>
	<%@ include file="../../common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link rel="stylesheet" href="../res/js/plugins/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="../res/js/plugins/ztree/metroStyle/metroStyle.css" type="text/css">
	<script type="text/javascript" src="../res/js/plugins/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="../res/js/plugins/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="../res/js/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript">
    	
    	function test(value,row,index){
//     		return '<a href="#">' + row['id'] + '</a>';
    		return '<a href="#">编辑 </a>|<a href="#">删除</a>';
    	}
    	
    	var _width  = "768px";
    	var _height  = "420px";
    	
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
    	
    	 var setting = {
                 view: {
                     addHoverDom: addHoverDom,
                     removeHoverDom: removeHoverDom,
                     selectedMulti: false
                 },
                 check: {
                     enable: true,
                     chkStyle: "radio",
     				 radioType: "all"
                 },
                 data: {
                     simpleData: {
                         enable: true
                     }
                 },
                 edit: {
     				enable: true,
    			},
                 callback: {
//              		onCheck: zTreeOnCheck
                	 beforeRemove: zTreeBeforeRemove,
                	 beforeEditName: zTreeBeforeEditName
             	}
             };
    	 
    	 function addAction(){
    		 
    		 var nodes = $.fn.zTree.getZTreeObj("treeMenu").getCheckedNodes(true);
 			var _id = 0  ;
 			
 			if(nodes.length > 0){
 				if(nodes[0].level == 1){
 					_id = nodes[0].id;
 					 $.openDlg({
 		    			url:'transfer2.cs?action=addAction&id=0&menu_id=' + _id,
 		    			title:'新增Action',
 		    			width:'769px',
 		    			height:'240px',
 		    	});
 				}
 			}
    	 }
    	 
    	 var newCount = 1;
    	 function addHoverDom(treeId, treeNode) {
             var sObj = $("#" + treeNode.tId + "_span");
             if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
             var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                 + "' title='add node' onfocus='this.blur();'></span>";
             sObj.after(addStr);
             var btn = $("#addBtn_"+treeNode.tId);
             if (btn) btn.bind("click", function(){
//                  var zTree = $.fn.zTree.getZTreeObj("treeMenu");
//                  zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
// 					alert(treeNode.id);
					if(treeNode.level == 2){
						$.openDlg({
			    			url:'transfer2.cs?action=addAction&id=0&menu_id=' + treeNode.getParentNode().id,
			    			title:'新增Action',
			    			width:'769px',
			    			height:'240px',
			    		});
					}
					else{
						$.openDlg({
			    			url:'transfer.cs?action=add&id=' + treeNode.id,
			    			title:'新增菜单',
			    			width:_width,
			    			height:_height,
			    		});
					}
                 return false;
             });
         };
         function removeHoverDom(treeId, treeNode) {
             $("#addBtn_"+treeNode.tId).unbind().remove();
//              var btn = $("#addBtn_"+treeNode.tId);
//              if (btn)btn.bind("click", function(){
//             	 $.confirm("确定删除吗？ ", function(){
//           			$.post('del.cs?id=' + treeId.id, 
//           					function(result){
//           						window.location.reload();
//           						}
//           			);
//           		}); 
//              });
         };
         
         function zTreeBeforeRemove(treeId, treeNode) {
        	 var url = '';
        	 if(treeNode.level == 2){
        		 url = 'delAction.cs?id=' + treeNode.id
        	 }
        	 else{
        		 url = 'del.cs?id=' + treeNode.id
        	 }
        	 
       	 	$.confirm("确定删除吗？ ", function(){
      			$.post(url, 
      					function(result){
      						window.location.reload();
      						}
      			);
     			}); 
       		return false;
       	}
         
         function  zTreeBeforeEditName(treeId, treeNode)  {
        	 if(treeNode.level == 2){
        		 $.openDlg({
         			url:'transfer2.cs?action=editAction&id=' + treeNode.id + '&menu_id=' + treeNode.getParentNode().id,
         			title:'编辑Action',
         			width:'769px',
	    			height:'280px',
         		});
        		 
        	 }
        	 else{
        		 $.openDlg({
         			url:'transfer.cs?action=edit&id=' + treeNode.id,
         			title:'编辑菜单',
         			width:_width,
         			height:_height,
         		});
        	 }
        		return false;
        	}
    	 
    	
    	$(document).ready(function(){
        	$.get('list2.cs', function(data){
        		zNodes = data;
        		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
        	});
        });
    	
    	
    	// 预览事件
    	function view(){
			
			var nodes = $.fn.zTree.getZTreeObj("treeMenu").getCheckedNodes(true);
			
			if(nodes.length < 1 ){
				return ;
			}
			
			var data = nodes[0];
			
    		$.openDlg({
    			url:'transfer.cs?action=view&id=' + data.id,
    			title:'查看菜单',
    			width:_width,
    			height:_height,
    		});
    	}
    	
    	// 新增事件
    	function add(){
    		
			var nodes = $.fn.zTree.getZTreeObj("treeMenu").getCheckedNodes(true);
			var _id = 0  ;
			
			
			
			if(nodes.length > 0){
				if(nodes[0].level == 2){
					nodes = nodes[0].getParentNode().getParentNode();
					_id = nodes.id;
				}
				else if(nodes[0].level == 1){
					_id = nodes[0].getParentNode().id;
				}
				
// 				if(nodes[0].level != 0) return;
			}
    		
    		$.openDlg({
//     			url:'transfer.cs?action=add&id=0',
    			url:'transfer.cs?action=add&id=' + _id,
    			title:'新增菜单',
    			width:_width,
    			height:_height,
    		});
    	}
    	
    	// 编辑事件
    	function edit(){
    		var nodes = $.fn.zTree.getZTreeObj("treeMenu").getCheckedNodes(true);
    		
			var _id = 0  ;
			
			if(nodes.length > 0 ){
				_id = nodes[0].id;
			}
			else{
				return ;
			}
    		
    		$.openDlg({
    			url:'transfer.cs?action=edit&id=' + _id,
    			title:'编辑菜单',
    			width:_width,
    			height:_height,
    		});
    	}

    	// 删除事件
    	function del(){
    		
			var nodes = $.fn.zTree.getZTreeObj("treeMenu").getCheckedNodes(true);
    		
			var _id = 0  ;
			
			if(nodes.length > 0 ){
				_id = nodes[0].id;
			}
			else{
				return ;
			}
    		
    		$.confirm("确定删除吗？ ", function(){
    			$.post('del.cs?id=' + _id, 
    					function(result){
    						window.location.reload();
    						}
    			);
    		});
    		
    	}    	
    	
    </script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row row-lg">
			<div class="col-sm-12">
						<p:permission menuCode="${menuCode }" action="1">
							<button class="btn btn-sm btn-info" type="button" onclick="view()"><i class="fa fa-info"></i>&nbsp;查看</button>
						</p:permission>
						<p:permission menuCode="${menuCode }" action="2">
							<button class="btn btn-sm btn-success" type="button" onclick="add()"><i class="fa fa-plus"></i>&nbsp;新增菜单</button>
						</p:permission>
						<p:permission menuCode="${menuCode }" action="4">
							<button class="btn btn-sm btn-warning" type="button" onclick="addAction()"><i class="fa fa-pencil-square-o"></i> 新增action</button>
						</p:permission>
<%-- 						<p:permission menuCode="181" action="8"> --%>
<!-- 							<button class="btn btn-sm btn-danger" type="button" onclick="del()"><i class="fa fa-minus"></i> 删除</button> -->
<%-- 						</p:permission> --%>
				
					<ul id="treeMenu" class="ztree"></ul>
			</div>

		</div>
	</div>


</body>

</html>
