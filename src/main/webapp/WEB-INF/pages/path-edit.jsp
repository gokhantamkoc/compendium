<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:layout>

    <jsp:attribute name="header">
      <h1>Paths - ${path.name}</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, Gokhan Tamkoc.</p>
    </jsp:attribute>

    <jsp:body>

      <a class="btn-link" href="${pageContext.request.contextPath}/path/list">Go to Paths</a>

      <br/>
      <br/>

      <form:form commandName="path" modelAttribute="path" action="${pageContext.request.contextPath}/path/edit/${path.id}">

          <div class="row">
              <div class="col-sm-2"><span class="title"><b>Name</b></span></div>
              <div class="col-sm-5"><form:input path="name" cssClass="form-control"/></div>
              <div class="col-sm-4"><form:errors path="name" cssClass="has-error"/></div>
          </div>

          <br/>

          <div class="row">
              <div class="col-sm-2"><span class="title"><b>Description</b></span></div>
              <div class="col-sm-5"><form:textarea path="description" cssClass="form-control" cssStyle="height: 100px"/></div>
              <div class="col-sm-4"><form:errors path="description" cssClass="error-message"/></div>
          </div>

          <br/>

          <div class="row">
              <div class="col-sm-2"><span class="title"><b>Status</b></span></div>
              <div class="col-sm-5">
                  <form:select path="pathStatus"  cssClass="form-control">
                      <c:forEach var="status" items="${pathStatusList}">
                          <c:choose>
                              <c:when test="${status.id == path.pathStatus.id}">
                                  <option value="${status.id.toString()}" selected><c:out value="${status.description}"/></option>
                              </c:when>
                              <c:otherwise>
                                  <option value="${status.id.toString()}"><c:out value="${status.description}"/></option>
                              </c:otherwise>
                          </c:choose>
                      </c:forEach>
                  </form:select>
              </div>
              <div class="col-sm-4"><form:errors path="pathStatus" cssClass="error-message"/></div>
          </div>

          <br/>

          <div class="row">
              <div class="col-sm-2"><span class="title"><b>Status</b></span></div>
              <div class="col-sm-5">
                  <form:select path="domain"  cssClass="form-control">
                      <c:forEach var="domain" items="${domainList}">
                          <c:choose>
                              <c:when test="${domain.id == path.domain.id}">
                                  <option value="${domain.id.toString()}" selected><c:out value="${domain.name}"/></option>
                              </c:when>
                              <c:otherwise>
                                  <option value="${domain.id.toString()}"><c:out value="${domain.name}"/></option>
                              </c:otherwise>
                          </c:choose>
                      </c:forEach>
                  </form:select>
              </div>
              <div class="col-sm-4"><form:errors path="domain" cssClass="error-message"/></div>
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