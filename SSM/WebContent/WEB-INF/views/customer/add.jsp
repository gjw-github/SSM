<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加页面</title>
</head>
<body>
     <form action="${pageContext.request.contextPath }/customer/doAdd" method="post">
	            姓名：<input type="text" name="cust_name"><br><br>
	            年龄：<input type="text" name="cust_age"><br><br>
	           <input type="submit" value="提交">
     </form>
</body>
</html>