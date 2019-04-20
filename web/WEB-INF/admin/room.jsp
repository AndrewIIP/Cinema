<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>
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
                                        <div class="place-gray"><p class="place-nb">${i}</p></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="place"><p class="place-nb">${i}</p></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="row">
                            <c:forEach var="i" begin="6" end="11">
                                <c:choose>
                                    <c:when test="${showSession.isEngagedPlace(i)}">
                                        <div class="place-gray"><p class="place-nb">${i}</p></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="place"><p class="place-nb">${i}</p></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="row">
                            <c:forEach var="i" begin="12" end="19">
                                <c:choose>
                                    <c:when test="${showSession.isEngagedPlace(i)}">
                                        <div class="place-gray"><p class="place-nb">${i}</p></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="place"><p class="place-nb">${i}</p></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="row">
                            <c:forEach var="i" begin="20" end="27">
                                <c:choose>
                                    <c:when test="${showSession.isEngagedPlace(i)}">
                                        <div class="place-gray"><p class="place-nb">${i}</p></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="place"><p class="place-nb">${i}</p></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="row">
                            <c:forEach var="i" begin="28" end="35">
                                <c:choose>
                                    <c:when test="${showSession.isEngagedPlace(i)}">
                                        <div class="place-gray"><p class="place-nb">${i}</p></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="place"><p class="place-nb">${i}</p></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="visitors-list">
                            <div class="ticket-row mov-head">
                                <div>
                                    <fmt:message key="movie.name"/>
                                </div>
                                <div>
                                    <fmt:message key="day"/>
                                </div>
                                <div>
                                    <fmt:message key="time"/>
                                </div>
                            </div>
                            <div class="ticket-row">
                                <div>
                                    ${showSession.movie.name}
                                </div>
                                <div>
                                    ${showSession.day.name}
                                </div>
                                <div>
                                    ${showSession.timeHoursMins}
                                </div>
                            </div>

                            <c:if test="${!showSession.ticketList.isEmpty()}">
                                <div class="ticket-row user-head">
                                    <div>
                                        <fmt:message key="place"/>
                                    </div>
                                    <div>
                                        <fmt:message key="user"/>
                                    </div>
                                </div>
                            </c:if>
                            <c:forEach items="${showSession.ticketList}" var="ticket">
                                <div class="ticket-row user-entr">
                                    <div>
                                            ${ticket.place}
                                    </div>
                                    <div>
                                            ${ticket.owner.username}
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
