<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Customers</title>
<link type="text/css" rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>	
	</div>
	
	<div id="container">
		<div id="content">
		
		<!-- ADD A SEARCH FORM -->
		<form:form action="search" method="POST">
			Search customer:<input type="text" name="theSearchName" />
			<input type="submit" value="Search" class="add-button" />
		</form:form>
		
		<!-- TABLE -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>E-mail</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempCustomer" items="${customers }">
				
					<!-- update link with customer id -->
					<c:url var="updateLink" value="/customer/showFormUpdate">
						<c:param name="customerId" value="${tempCustomer.id }"/>
					</c:url>
					
					<!-- delete link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id }"/>
					</c:url>
					
					<tr> 
						<td> ${tempCustomer.firstName } </td>
						<td> ${tempCustomer.lastName } </td>
						<td> ${tempCustomer.email } </td> 
						<!-- display the update link -->
						<td>
							<a href="${updateLink }">Update</a>
							|
							<a href="${deleteLink }"
								onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					 </tr>
				</c:forEach>
			</table>
			<br>
			
			<!-- BUTTON ADD CUSTOMER -->
			
			<input type="button" value="Add Customer" 
			onclick="window.location.href='showFormAdd'; return false;"
			class="add-button" />
			
		</div>
	</div>

</body>
</html>