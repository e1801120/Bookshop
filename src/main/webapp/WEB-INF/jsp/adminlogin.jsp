<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Bookshop</title>
</head>
<body>

<h1>Admin login</h1>
 
 <p>
 ${error }
 <p>
    <form action="j_security_check" method="post">
	Username: <input type="text" name="j_username">
	Password:<input type="password" name="j_password">
   
    <button type="submit">Login</button>
	
    </form>
	
  <p>
  <a href="Bookshop">Back</a>
</body>
</html>