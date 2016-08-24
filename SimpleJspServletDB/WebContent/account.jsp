<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="POST" action="AccountController" enctype="multipart/form-data" name="frmAddAccount">
	Acc_id: <input type="text" readonly="readonly" name="acc_id"
				value='<c:out value="${account.acc_id}"></c:out>' /> <br/>
				
	Profile: <img src='<c:out value="${account.profilePath}"></c:out>'width="100">
		<input type="file" name="profilePath" accept="image/jpeg, image/x-png" /> <br/>
		
	Username: <input type="text" name="username" 
				value='<c:out value="${account.username}"></c:out>' /> <br/>
				
	Password: <input type="text" name="password"
				value='<c:out value="${account.password}"></c:out>' /> <br/>
				
	Email: <input type="text" name="email"
				value='<c:out value="${account.email}"></c:out>' /> <br/>
				
	<input type="submit" name="submit" /> <br/>
</form>

</body>
</html>