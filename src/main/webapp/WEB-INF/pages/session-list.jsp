<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
      <h1>Sessions</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, Gokhan Tamkoc.</p>
    </jsp:attribute>

    <jsp:body>
        <a href="${pageContext.request.contextPath}/session/create">Create New Session</a>

        <br/>
        <br/>

        <table class="table table-responsive table-bordered">
            <thead>
            <tr>
                <th width="25px">Id</th>
                <th width="150px">Course</th>
                <th width="50px"># of Order</th>
                <th width="150px">Name</th>
                <th width="25px">Status</th>
                <th width="50px">-</th>
                <th width="50px">-</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="session" items="${sessionList}">
                <tr>
                    <td>${session.id}</td>
                    <td>${session.course.name}</td>
                    <td>${session.orderNumber}</td>
                    <td>${session.name}</td>
                    <td>${session.sessionStatus.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/session/edit/${session.id}">Edit</a>
                    </td>
                    <td>

                        <a href="${pageContext.request.contextPath}/question/list/${session.id}">Questions</a>
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