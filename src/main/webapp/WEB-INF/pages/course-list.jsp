<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
      <h1>Courses</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, Gokhan Tamkoc.</p>
    </jsp:attribute>

    <jsp:body>
        <a href="${pageContext.request.contextPath}/course/create">Create New Course</a>

        <br/>
        <br/>

        <table class="table table-responsive table-bordered">
            <thead>
            <tr>
                <th width="25px">Id</th>
                <th width="150px">Path</th>
                <th width="150px">Name</th>
                <th width="25px">Status</th>
                <th width="50px">*</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courseList}">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.path.name}</td>
                    <td>${course.name}</td>
                    <td>${course.courseStatus.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/course/edit/${course.id}">Edit</a><br/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br/>

        <div class="alert-success">${successMessage}</div>

        <br/>

        <a href="${pageContext.request.contextPath}/">Return to Home page</a>

        <br/>
        <br/>
    </jsp:body>

</t:layout>