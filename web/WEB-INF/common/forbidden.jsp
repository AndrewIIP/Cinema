<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.04.2019
  Time: 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Title</title>
    <link  href="${pageContext.request.contextPath}/css/forb.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/<%--webjars/bootstrap/3.2.0/--%>css/bootstrap.css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
</head>
<body>

<div class="main-card">
    <img src="../../pic/etc/alert.png"/>
    <div class="sorry-msg">
        <h2 class="font-weight-normal"><fmt:message key="forbidden"/></h2>
        <h4 class="font-weight-normal" style="margin-top: 35px;">
            <a class="" href="${pageContext.request.contextPath}/shows_you/"><fmt:message key="back.home"/></a>
        </h4>
    </div>
</div>

</body>
</html>
