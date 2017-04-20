<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="formId" type="java.lang.String" required="true" %>

<script type="text/javascript">
	$(function(){
		$('.btn-dlg-close').click(function(){
			var index = parent.layer.getFrameIndex(window.name);  
			parent.layer.close(index);
		});
		
		$('#' + '${formId}').form_validate();
 	});
	
</script>