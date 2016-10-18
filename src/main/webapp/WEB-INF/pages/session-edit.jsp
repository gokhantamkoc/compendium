<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
      <h1>Sessions - ${session.name}</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, Gokhan Tamkoc.</p>
    </jsp:attribute>

    <jsp:body>

        <a class="btn-link" href="${pageContext.request.contextPath}/session/list">Go to Sessions</a>

        <br/>
        <br/>

        <form:form commandName="session" modelAttribute="session" action="${pageContext.request.contextPath}/session/edit/${session.id}">

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Name</b></span></div>
                <div class="col-sm-5"><form:input path="name" cssClass="form-control"/></div>
                <div class="col-sm-4"><form:errors path="name" cssClass="has-error"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b># of Order</b></span></div>
                <div class="col-sm-5"><form:input path="orderNumber" cssClass="form-control"/></div>
                <div class="col-sm-4"><form:errors path="orderNumber" cssClass="has-error"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Introduction</b></span></div>
                <div class="col-sm-5"><form:textarea path="introduction" cssClass="form-control" cssStyle="height: 100px"/></div>
                <div class="col-sm-4"><form:errors path="introduction" cssClass="error-message"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Body</b></span></div>
                <div class="col-sm-5"><form:textarea path="body" cssClass="form-control" cssStyle="height: 100px"/></div>
                <div class="col-sm-4"><form:errors path="body" cssClass="error-message"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Summary</b></span></div>
                <div class="col-sm-5"><form:textarea path="summary" cssClass="form-control" cssStyle="height: 100px"/></div>
                <div class="col-sm-4"><form:errors path="summary" cssClass="error-message"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Status</b></span></div>
                <div class="col-sm-5">
                    <form:select path="sessionStatus"  cssClass="form-control">
                        <c:forEach var="status" items="${sessionStatusList}">
                            <c:choose>
                                <c:when test="${status.id == session.sessionStatus.id}">
                                    <option value="${status.id.toString()}" selected><c:out value="${status.description}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status.id.toString()}"><c:out value="${status.description}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="col-sm-4"><form:errors path="sessionStatus" cssClass="error-message"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"><span class="title"><b>Status</b></span></div>
                <div class="col-sm-5">
                    <form:select path="course"  cssClass="form-control">
                        <c:forEach var="item" items="${courseList}">
                            <c:choose>
                                <c:when test="${item.id == session.course.id}">
                                    <option value="${item.id.toString()}" selected><c:out value="${item.name}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.id.toString()}"><c:out value="${item.name}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="col-sm-4"><form:errors path="course" cssClass="error-message"/></div>
            </div>

            <br/>

            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-5"><input type="submit" value="Update" class="btn btn-primary"/></div>
                <div class="col-sm-1"></div>
            </div>
        </form:form>
    </jsp:body>

</t:layout>