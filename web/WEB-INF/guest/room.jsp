<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.04.2019
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Title</title>
    <link  href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/room.css" rel="stylesheet" type="text/css"/>
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/guest/header.jsp"/>
    <jsp:useBean id="showSession"  scope="request" class="model.entity.Session"/>
    <section class="pricing py-5">
        <div class="wrap-cont">
            <!-- Free Tier -->
            <div class="card-bg-color  mb-5 mb-lg-0">
                <div class="card-body inner-bg">
                    <div class="table-container">
                        <div class="screen"><h2 class="font-weight-light"><fmt:message key="screen"/></h2></div>
                        <div class="sits">
                            <div class="row">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${showSession.isEngagedPlace(i)}">
                                            <div class="place-gray"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="place"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <c:forEach var="i" begin="6" end="11">
                                    <c:choose>
                                        <c:when test="${showSession.isEngagedPlace(i)}">
                                            <div class="place-gray"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="place"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <c:forEach var="i" begin="12" end="19">
                                    <c:choose>
                                        <c:when test="${showSession.isEngagedPlace(i)}">
                                            <div class="place-gray"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="place"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <c:forEach var="i" begin="20" end="27">
                                    <c:choose>
                                        <c:when test="${showSession.isEngagedPlace(i)}">
                                            <div class="place-gray"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="place"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <c:forEach var="i" begin="28" end="35">
                                    <c:choose>
                                        <c:when test="${showSession.isEngagedPlace(i)}">
                                            <div class="place-gray"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="place"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <h4 class="font-weight-light" style="margin-top: 50px"><fmt:message key="please.sgn.purch"/></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
