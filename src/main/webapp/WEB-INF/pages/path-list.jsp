<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
      <h1>Paths</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, Gokhan Tamkoc.</p>
    </jsp:attribute>

    <jsp:body>
        <a href="${pageContext.request.contextPath}/path/create">Create New Path</a>

        <br/>
        <br/>

        <table class="table table-responsive table-bordered">
            <thead>
            <tr>
                <th width="25px">Id</th>
                <th width="150px">Domain</th>
                <th width="150px">Name</th>
                <th width="25px">Description</th>
                <th width="25px">Status</th>
                <th width="50px">*</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="path" items="${pathList}">
                <tr>
                    <td>${path.id}</td>
                    <td>${path.domain.name}</td>
                    <td>${path.name}</td>
                    <td>${path.description}</td>
                    <td>${path.pathStatus.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/path/edit/${path.id}">Edit</a><br/>
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