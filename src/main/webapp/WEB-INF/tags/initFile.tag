<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	String path2 = request.getContextPath(); 
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）: 
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + path2+"/"; 
%> 


<script type="text/javascript">
	$(function(){
// 		var _fileType = parseInt('${type}');
// 		var _url = '';
// 		if(_fileType >= 100 && _fileType < 200){
<%-- 			_url = '<%=basePath2 %>/file/uploadPic.cs';	 --%>
// 		}
// 		else if(_fileType > 200){
<%-- 			_url = '<%=basePath2 %>/file/uploadFile.cs';	 --%>
// 		}
		
// 		$(".upload-img").InitUploader({ sendurl: _url, swf: '../res/js/plugins/webuploader/uploader.swf', thumbnail: true });
//         $(".upload-file").InitUploader({ sendurl: _url, swf: '../res/js/plugins/webuploader/uploader.swf', fileType: _fileType });
        
//         $('.show-img').click(function(){
//         	$(this).parent().children('.upload-img').children().find('input').click();
//         });
//         $('.show-file').click(function(){
//         	$(this).parent().children('.upload-file').children().find('input').click();
//         });
        
        $('.upload-img').each(function(){
        	var _fileType = parseInt($(this).attr('fileType'));
        	var _url = '<%=basePath2 %>/file/uploadPic.cs';	
        	$(this).InitUploader({ sendurl: _url, swf: '../res/js/plugins/webuploader/uploader.swf', thumbnail: true, fileType: _fileType });
        	$(this).parent().children('.show-img').click(function(){
            	$(this).parent().children('.upload-img').children().find('input').click();
            });
        });
        
        $('.upload-file').each(function(){
        	var _fileType = parseInt($(this).attr('fileType'));
        	var _url = '<%=basePath2 %>/file/uploadFile.cs';	
        	$(this).InitUploader({ sendurl: _url, swf: '../res/js/plugins/webuploader/uploader.swf', thumbnail: true, fileType: _fileType });
        	var _obj = $(this);
        	$(this).parent().find('.show-file').click(function(){
//         	$(this).find('.show-file').click(function(){
//         		alert(_obj.html());
        		_obj.find('input').click();
            });
        });
        
		
 	});
	
	</script>
	