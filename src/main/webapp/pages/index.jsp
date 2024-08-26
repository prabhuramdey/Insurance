<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insurance Reports App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>

		<form:form action="searchData" modelAttribute="search" method="POST">
			<table>
				<tr>
					<td>PLAN NAME :</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}" />
						</form:select></td>

					<td>PLAN STATUS :</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>GENDER :</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>START DATE :</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>END DATE :</td>
					<td><form:input type="date" path="endDate" /></td>
				</tr>
				<tr>
					<td>a<a href="/" class="btn btn-secondary">Reset</a></td>
						<td><input type="submit" value="Search"
						class="btn btn-primary"></td>
				</tr>

			</table>
		</form:form>

		<hr />

			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>SL NO.</th>
						<th>Holder Name</th>
						<th>Plan Name</th>
						<th>Plan Status</th>
						<th>Gender</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Benifit Amount</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${plans}" var="plan" varStatus="index">
						<tr>
							<td>${index.count}</td>
							<td>${plan.citizenName}</td>
							<td>${plan.planName}</td>
							<td>${plan.planStatus}</td>
							<td>${plan.gender}</td>
							<td>${plan.planStartDate}</td>
							<td>${plan.planEndDate}</td>
							<td>${plan.benifitAmt}</td>
						</tr>
					</c:forEach>
						<tr>
						<!-- <c:if test="${empty plans}	">
								<td colspan="8" style="text-align: center">NO RECORDS FOUND</td>
						</c:if> -->
						</tr>
				</tbody>
			</table>

		<hr />
		<p class="text-success">${msg}</p>
		Export : <a href="excel">Excel</a> <a href="pdf">Pdf</a>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>

