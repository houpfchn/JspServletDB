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

<table border=1>
	<thead>
		<tr>
			<th>Acc_id</th>
			<th>Profile</th>
			<th>Username</th>
			<th>Password</th>
			<th>Email</th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td><c:out value="${account.acc_id}"></c:out></td>
				<td><img src="<c:out value="${account.profilePath}" />" width="100"></td>
				<td><c:out value="${account.username}"></c:out></td>
				<td><c:out value="${account.password}"></c:out></td>
				<td><c:out value="${account.email}"></c:out></td>
				<td><a href="AccountController?action=update&acc_id=<c:out value="${account.acc_id}"/>">Update</a></td>
				<td><a href="AccountController?action=delete&acc_id=<c:out value="${account.acc_id}"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>

<p><a href="AccountController?action=addAccount">AddAccount</a></p>

</body>
</html>