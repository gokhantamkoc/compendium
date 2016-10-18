<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <title>Compendium</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/site.css" rel="stylesheet" media="screen">
</head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/domain/list">Domains</a></li>
                        <li><a href="${pageContext.request.contextPath}/path/list">Paths</a></li>
                        <li><a href="${pageContext.request.contextPath}/course/list">Courses</a></li>
                        <li><a href="${pageContext.request.contextPath}/session/list">Sessions</a></li>
                        <li><a href="#">Tests</a></li>
                        <li><a href="#">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container-fluid wrap-page">
            <div id="pageheader">
                <jsp:invoke fragment="header"/>
            </div>
            <div id="body">
                <div class="container-fluid">
                <jsp:doBody/>
                </div>
            </div>
            <div id="pagefooter">
                <jsp:invoke fragment="footer"/>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>