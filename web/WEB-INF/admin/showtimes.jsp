<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 08.04.2019
  Time: 4:46
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
    <link  href="${pageContext.request.contextPath}/css/movies.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link  href="${pageContext.request.contextPath}/css/showtimes.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
    <script src="${pageContext.request.contextPath}/js/ticket_scr.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/admin/header.jsp"/>
    <jsp:useBean id="day" scope="request" type="model.entity.Day"/>
    <jsp:useBean id="moviesBean" scope="request" type="java.util.List"/>

    <section class="pricing py-5">
        <div class="wrap-cont">
            <!-- Free Tier -->
            <div class="card-bg-color  mb-5 mb-lg-0">
                <div class="card-body inner-bg">
                    <div class="table-container">
                        <div class="andrew-custom-btn btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-dark ${day.id == 1 ? "focus active" : ""}" onclick="post('/showtimes', {day : 1})">
                                <input type="radio" name="monday" id="monday" autocomplete="off" checked><fmt:message key="monday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 2 ? "focus active" : ""}" onclick="post('/showtimes', {day : 2})">
                                <input type="radio" name="tuesday" id="tuesday" autocomplete="off"><fmt:message key="tuesday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 3 ? "focus active" : ""}" onclick="post('/showtimes', {day : 3})">
                                <input type="radio" name="wednesday" id="wednesday" autocomplete="off"><fmt:message key="wednesday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 4 ? "focus active" : ""}" onclick="post('/showtimes', {day : 4})">
                                <input type="radio" name="thursday" id="thursday" autocomplete="off"><fmt:message key="thursday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 5 ? "focus active" : ""}" onclick="post('/showtimes', {day : 5})">
                                <input type="radio" name="friday" id="friday" autocomplete="off"><fmt:message key="friday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 6 ? "focus active" : ""}" onclick="post('/showtimes', {day : 6})">
                                <input type="radio" name="saturday" id="saturday" autocomplete="off"><fmt:message key="saturday"/>
                            </label>
                            <label class="btn btn-dark ${day.id == 7 ? "focus active" : ""}" onclick="post('/showtimes', {day : 7})">
                                <input type="radio" name="sunday" id="sunday" autocomplete="off"><fmt:message key="sunday"/>
                            </label>
                        </div>

                        <c:forEach items="${day.sessions}" var="session">
                            <div class="movie-row">
                                <div class="mov-name-entry">
                                        ${session.movie.name}
                                </div>
                                <div class="mov-time">
                                        ${session.timeHoursMins}
                                    <button type="button" class="btn btn-info purch-btn" onclick="post('/room', {sessionId : ${session.id}}, 'get')">
                                        <fmt:message key="room"/>
                                    </button>
                                </div>
                                <div class="delete-btn-show">
                                    <button type="button" class="btn btn-danger" onclick="notify_del('${session.id}', 'id-session-holder');appearDivById('sure-span')">
                                        <img src="${pageContext.request.contextPath}/pic/etc/trash30.png"/>
                                    </button>
                                </div>
                            </div>
                        </c:forEach>

                        <h3 class="font-weight-light mt-2 text-center"><fmt:message key="create.session"/></h3>

                        <form id="session-create" action="${pageContext.request.contextPath}/shows_you/addses?${pageContext.request.queryString}" method="post">
                            <div>
                                <div class="movie-row">
                                    <div class="form-group">
                                        <label for="movieSelector"></label>
                                        <select class="form-control" id="movieSelector" name="movieId" required>
                                            <c:forEach items="${moviesBean}" var="movie">
                                                <option value="${movie.id}">${movie.name}</option>
                                            </c:forEach>
                                        </select>
                                        <small id="movieSelectorSpan" class="form-text text-muted"><fmt:message key="movie.name"/></small>
                                    </div>
                                    <div class="mov-time">
                                        <div class="form-group">
                                            <input id="hours" name="hours" type="number" min="9" max="22" class="form-control" aria-describedby="emailHelp" placeholder="09" value="9">
                                            <small id="hoursHint" class="form-text text-muted"><fmt:message key="hours"/></small>
                                        </div>
                                        <h3 class="font-weight-light">:</h3>
                                        <div class="form-group">
                                            <input id="mins" name="mins" type="number" min="0" max="59" class="form-control" aria-describedby="emailHelp" placeholder="00" value="00">
                                            <small id="minsHint" class="form-text text-muted"><fmt:message key="minutes"/></small>
                                        </div>
                                    </div>
                                    <input type="number" class="form-control" id="dayId" name="day" value="${day.id}" hidden>
                            </div>
                                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="add.session"/></button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <div id="sure-span" class="sure-span" style="display:none">
        <div class="order-top">
            <h3 class="font-weight-normal"><fmt:message key="delete.session"/></h3>
            <p id="id-session-holder" style="display: none"></p>
        </div>

        <div class="order-bottom">
            <button class="btn btn-danger" onclick="post('/remses', {sessionId : document.getElementById('id-session-holder').innerText}, 'post')">
                <fmt:message key="remove"/>
            </button>
            <button class="btn btn-outline-secondary" onclick="cls_span('sure-span')">
                <fmt:message key="cancel"/>
            </button>
        </div>
    </div>
</body>
</html>
