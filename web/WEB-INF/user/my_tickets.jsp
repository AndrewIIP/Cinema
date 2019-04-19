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
    <link  href="${pageContext.request.contextPath}/css/room.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link  href="${pageContext.request.contextPath}/css/showtimes.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
    <script src="${pageContext.request.contextPath}/js/ticket_scr.js"></script>
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
                    <div class="my-tk-header">
                        <h3 class="font-weight-light"><fmt:message key="mytickets"/></h3>
                    </div>
                    <div class="header-row">
                        <div class="tk-id">
                            <fmt:message key="number"/>
                        </div>
                        <div class="tk-mov-name">
                            <fmt:message key="movie.name"/>
                        </div>
                        <div class="tk-day">
                            <fmt:message key="day"/>
                        </div>
                        <div class="tk-time">
                            <fmt:message key="time"/>
                        </div>
                        <div class="tk-place">
                            <fmt:message key="place"/>
                        </div>
                    </div>
                    <c:forEach items="${tickets}" var="ticket">
                        <div class="movie-row">
                            <div class="tk-id">
                                    ${ticket.id}
                            </div>
                            <div class="tk-mov-name">
                                    ${ticket.session.movie.name}
                            </div>
                            <div class="tk-day">
                                    ${ticket.session.day.name}
                            </div>
                            <div class="tk-time">
                                    ${ticket.session.timeHoursMins}
                            </div>
                            <div class="tk-place">
                                    ${ticket.place}
                            </div>
                            <div class="delete-btn">
                                <button type="button" class="btn btn-danger circu" onclick="notify_cancel('${ticket.id}');appearDivById('sure-span')">
                                    <img src="../../pic/etc/trash30.png"/>
                                </button>
                            </div>
                        </div>
                    </c:forEach>

                    <div id="sure-span" class="sure-span" style="display:none">
                        <div class="order-top">
                            <h3 class="font-weight-normal"><fmt:message key="delete.tick"/></h3>
                            <p class="font-weight-normal disp"><fmt:message key="ticket.number"/></p> -
                            <p id="tk-num-holder" class="disp"></p>
                        </div>

                        <div class="order-bottom">
                            <button class="btn btn-danger" onclick="post('/remtick', {ticketId : document.getElementById('tk-num-holder').innerText}, 'post')">
                                <fmt:message key="remove"/>
                            </button>
                            <button class="btn btn-outline-secondary" onclick="cls_span('sure-span')">
                                <fmt:message key="cancel"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>