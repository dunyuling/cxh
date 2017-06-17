<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<head>
	<%@ include file="/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台" />
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术" />

    <!-- Data Tables -->
    <link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
	<script src="/js/plugins/jeditable/jquery.jeditable.js" type="text/javascript"></script>
	<!-- Data Tables -->
	<script src="/js/plugins/dataTables/jquery.dataTables.js" type="text/javascript"></script>
	<script src="/js/plugins/dataTables/dataTables.bootstrap.js" type="text/javascript"></script>
	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
<!--                     <div class="ibox-title"> -->
<!--                         <h5>基本 </h5> -->
<!--                     </div> -->
                    <div class="ibox-content">
						<button class="btn btn-primary " type="button"><i class="fa fa-check"></i>&nbsp;提交</button>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>菜单id</th>
                                    <th>菜单名称</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td>2</td>
                            	<td>3</td>
                            	<td>4</td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
        	
            $('.dataTables-example').dataTable({
                // "sAjaxSource":"2.txt"
				"bLengthChange": false, //改变每页显示数据数量
				"bFilter": false, //过滤功能
				"bSort":false,
				"iDisplayLength":12,
				"ajax":'list2.cs',
				"bServerSide": true, 
				"select": true,//
// 				"aoColumns": [
// 					{ "mDataProp": "id" },
// 					{ "mDataProp": "name" },
// 					],
				'columns':[
					{
						className: "td-checkbox",  
		                orderable : false,  
		                bSortable : false,  
		                data : null,  
		                render : function(data, type, row, meta) {  
		                    var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';  
		                    content += '    <input type="checkbox" class="group-checkable" value="' + data + '" />';  
		                    content += '    <span></span>';  
		                    content += '</label>';  
		                    return content;  
		                }  
		            },
					{'data':'id'},
					{'data':'name'}
				           ],

// 				'columnDefs': [{
// 			         'targets': 0,
// 			         'searchable':false,
// 			         'orderable':false,
// 			         'className': 'dt-body-center',
// 			         'data':null,
// 			         'render': function (data, type, full, meta){
// 			             return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
// 			         }
// 			      }],
                //$_GET['sColumns']将接收到aoColumns传递数据
            });
        });
    </script>

</body>

</html>
