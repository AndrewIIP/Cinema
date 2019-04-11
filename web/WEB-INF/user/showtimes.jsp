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
    <link  href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link  href="${pageContext.request.contextPath}/css/showtimes.css" type="text/css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/WEB-INF/user/header.jsp"/>

    <section class="pricing py-5">
        <div class="wrap-cont">
            <!-- Free Tier -->
            <div class="card-bg-color  mb-5 mb-lg-0">
                <div class="card-body inner-bg">
                    <div class="table-container">
                        <div class="andrew-custom-btn btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-dark active">
                                <input type="radio" name="monday" id="monday" autocomplete="off" checked> Monday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="tuesday" id="tuesday" autocomplete="off"> Tuesday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="wednesday" id="wednesday" autocomplete="off"> Wednesday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="thursday" id="thursday" autocomplete="off"> Thursday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="friday" id="friday" autocomplete="off"> Friday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="saturday" id="saturday" autocomplete="off"> Saturday
                            </label>
                            <label class="btn btn-dark">
                                <input type="radio" name="sunday" id="sunday" autocomplete="off"> Sunday
                            </label>
                        </div>

                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>

                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                        <div class="movie-row">
                            <div class="mov-name-entry">
                                La La Land
                            </div>
                            <div class="mov-time">
                                22:00
                                <button type="button" class="btn btn-info purch-btn">buy</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
