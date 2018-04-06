<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    
<link href="${pageContext.request.contextPath}/bootstrap-3.3.0/js/bootstrap.min.js" rel="stylesheet">  
<link href="${pageContext.request.contextPath}/bootstrap-fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>          
<script src="${pageContext.request.contextPath}/jquery-3.1.0.min.js"></script>         
<script src="${pageContext.request.contextPath}/bootstrap-fileinput/js/fileinput.min.js" type="text/javascript"></script> 

</head>
<body>
<div class="container kv-main" stype = "border:solid 1px #ffffff;">
    <form enctype="multipart/form-data">
        <div class="form-group">
            <div class="file-loading">
                <input id="file-5" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#">               
            </div>
        </div>
    </form>    
</div>			    
</body>
</html>