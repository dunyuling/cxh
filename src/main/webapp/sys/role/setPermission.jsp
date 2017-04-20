<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    
    <script>
    
    	var role_id = "${role.id}";
    	
    	var zNodes;
    
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
        
			
        var setting = {
                view: {
//                     addHoverDom: addHoverDom,
//                     removeHoverDom: removeHoverDom,
                    selectedMulti: true
                },
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                edit: {
                    enable: false
                },
                callback: {
//             		onCheck: zTreeOnCheck
            	}
            };


            $(document).ready(function(){
            	$.get('getListMenu.cs?roleId=' + role_id,function(data){
            		zNodes = data;
            		$.fn.zTree.init($("#treePermission"), setting, zNodes);
            	});
            });

            var newCount = 1;
            function addHoverDom(treeId, treeNode) {
                var sObj = $("#" + treeNode.tId + "_span");
                if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
                sObj.after(addStr);
                var btn = $("#addBtn_"+treeNode.tId);
                if (btn) btn.bind("click", function(){
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                    return false;
                });
            };
            function removeHoverDom(treeId, treeNode) {
                $("#addBtn_"+treeNode.tId).unbind().remove();
            };
            
//             function zTreeOnCheck(event, treeId, treeNode){
//             	if(treeNode.level == 2 ){ //三级节点
// 					parent_treeNode = treeNode.getParentNode();
//             		if((parent_treeNode.val & treeNode.val) == 0 || parent_treeNode.val == 0){
//             			if(treeNode.checked){
// 	            			parent_treeNode.val += treeNode.val;
//             			}
//             		}
//             		else if((parent_treeNode.val & treeNode.val) != 0){
//             			if(!treeNode.checked){
//             				parent_treeNode.val -= treeNode.val;
//             			}
//             		}
//             	}
//             }
            
            function initNode_level1(){
            	var nodes = $.fn.zTree.getZTreeObj("treePermission").getCheckedNodes(true);
            	
            	for(var i in nodes){
            		var treeNode = nodes[i];
            		if(treeNode.level == 1 ){ //三级节点
            			treeNode.val = 0;
                	}
            	}
            	
            	for(var i in nodes){
            		var treeNode = nodes[i];
            		if(treeNode.level == 2 ){ //三级节点
    					parent_treeNode = treeNode.getParentNode();
                		if((parent_treeNode.val & treeNode.val) == 0 || parent_treeNode.val == 0){
                			if(treeNode.checked){
    	            			parent_treeNode.val += treeNode.val;
                			}
                		}
                	}
            	}
            }
            
            function setPermission(){
            	var acs = [];
            	
            	initNode_level1();
            	
            	var nodes = $.fn.zTree.getZTreeObj("treePermission").getCheckedNodes(true);
            	
            	for(var i in nodes){
            		var _t = {};
            		if(nodes[i].level == 0){
            			_t.menu_code = nodes[i].code;
            			_t.action_val = 0;
            			acs.push(_t);
            		}
            		else if(nodes[i].level == 1){
            			_t.menu_code = nodes[i].code;
            			_t.action_val = nodes[i].val;
            			acs.push(_t);
            		}
            	}
            	
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

	              <div class="row row-lg">
	              		<div class="col-sm-1">
	              			<button class="btn btn-primary btn-sm" type="button" onclick="setPermission()">分配</button>
	              			<ul id="treePermission" class="ztree"></ul>
	              		</div>
	              </div>
				 
<!--               </div> -->
<!--           </div> -->
    </div>
    

</body>

</html>
