/**
 * 
 */

$(function(){
	
	
	$.upLoadDefaults = $.upLoadDefaults || {};
	
	$.upLoadDefaults.property = {
	        multiple: false, //是否多文件
	        sendurl: null, //发送地址
	        filesize: "2048", //文件大小
	        btntext: "浏览...", //上传按钮的文字
	        swf: null //SWF上传控件相对地址
	    };
	
	$.fn.InitUploadFile = function(b){
		var fun = function (parentObj) {
			var p = $.extend({}, $.upLoadDefaults.property, b || {});
			
			var btnObj = $('<div class="upload-btn btn-success"><i class="fa fa-plus"></i>&nbsp;' + p.btntext + '</div>').appendTo(parentObj);
			
			var uploader = WebUploader.create({
				auto: true, //自动上传
				
			    // swf文件路径
			    swf: p.swf,

			    // 文件接收服务端。
			    server: p.sendurl,

			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick: '#picker',
                
//                formData: {
//                    'type': p.file_type //定义参数
//                },
                
                fileVal: 'Filedata', //上传域的名称
                
			    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false
			});
			
//			// 当有文件被添加进队列的时候
//			uploader.on( 'fileQueued', function( file ) {
//			    $list.append( '<div id="' + file.id + '" class="item">' +
//			        '<h4 class="info">' + file.name + '</h4>' +
//			        '<p class="state">等待上传...</p>' +
//			    '</div>' );
//			});
			
			// 当有文件被添加进队列的时候
			uploader.on( 'fileQueued', function( file ) {
			    $list.append( '<div id="' + file.id + '" class="item">' +
			        '<h4 class="info">' + file.name + '</h4>' +
			        '<p class="state">等待上传...</p>' +
			    '</div>' );
			});
			
			// 文件上传过程中创建进度条实时显示。
			uploader.on( 'uploadProgress', function( file, percentage ) {
			    var $li = $( '#'+file.id ),
			        $percent = $li.find('.progress .progress-bar');

			    // 避免重复创建
			    if ( !$percent.length ) {
			        $percent = $('<div class="progress progress-striped active">' +
			          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
			          '</div>' +
			        '</div>').appendTo( $li ).find('.progress-bar');
			    }

			    $li.find('p.state').text('上传中');

			    $percent.css( 'width', percentage * 100 + '%' );
			});
			
			uploader.on( 'uploadSuccess', function( file ) {
			    $( '#'+file.id ).find('p.state').text('已上传');
			});

			uploader.on( 'uploadError', function( file ) {
			    $( '#'+file.id ).find('p.state').text('上传出错');
			});

			uploader.on( 'uploadComplete', function( file ) {
			    $( '#'+file.id ).find('.progress').fadeOut();
			});
			
		}		
		 return $(this).each(function () {
	            fun($(this));
	        });
	}
	
	
});