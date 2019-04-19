<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.04.2019
  Time: 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<html>
<head lang="${sessionScope.curLang}">
    <title>Title</title>
    <link  href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link  href="${pageContext.request.contextPath}/css/showtimes.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>
<jsp:useBean id="tickets" scope="request" type="java.util.List"/>

<section class="pricing py-5">
    <div class="wrap-cont">
        <!-- Free Tier -->
        <div class="card-bg-color  mb-5 mb-lg-0">
            <div class="card-body inner-bg">
                <div class="table-container">
                    <c:forEach items="${tickets}" var="ticket">
                        <div class="movie-row">
                            <div class="tk-mov-name">
                                    ${ticket.session.movie.name}
                            </div>
                            <div class="tk-day">
                                    ${ticket.session.day.name}
                            </div>
                            <div class="tk-time">
                                    ${ticket.session.timeHoursMins}
                            </div>
                            <div class="delete-btn">
                                <button type="button" class="btn btn-danger circu">
                                    <img src="../../pic/etc/trash30.png"/>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>