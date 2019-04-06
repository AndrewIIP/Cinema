<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 22.03.2019
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>

<html lang="${sessionScope.curLang}">
<head>
    <title>Home</title>
    <link  href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/signAjax.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mynavbar header shadow-c">
        <div class="container">
            <a class="navbar-brand" href="#">Cinema3D</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active mybutton">
                        <a class="nav-link" href="#"><fmt:message key="header.home"/>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item mybutton">
                        <a class="nav-link" href="#"><fmt:message key="header.showings"/></a>
                    </li>
                    <li class="nav-item dropdown mybutton">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="header.language"/>
                        </a>
                        <!-- Here's the magic. Add the .animate and .slide-in classes to your .dropdown-menu and you're all set! -->
                        <div class="crystal shadow-c dropdown-menu dropdown-menu-right animate slideIn" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/shows_you/"
                               style="padding-left: 12px">
                                <img src="${pageContext.request.contextPath}/pic/langs/United-Kingdom.png"
                                     style="margin-right: 24px"/><fmt:message key="header.lang.eng"/>
                            </a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/shows_you/?curLang=uk"
                               style="padding-left: 12px;">
                                <img src="${pageContext.request.contextPath}/pic/langs/Ukraine.png"
                                     style="margin-right: 24px"/><fmt:message key="header.lang.ukr"/>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item mybutton">
                        <a class="btn nav-link rounded singup" href="${pageContext.request.contextPath}/shows_you/logout?${pageContext.request.queryString}"><fmt:message key="header.logout"/></a>
                    </li>
                    <li class="nav-item mybutton">
                        <a class="btn nav-link rounded singup">Admin ${pageContext.session.getAttribute("username")}</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <!-- Slide One - Set the background image for this slide in the line below -->
                <div class="carousel-item active" style="background-image: url(${pageContext.request.contextPath}/pic/posters/first-man.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">First Slide</h3>
                        <p class="lead">This is a description for the first slide.</p>
                    </div>
                </div>
                <!-- Slide Two - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/pic/posters/jurassic-park.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">Second Slide</h3>
                        <p class="lead">This is a description for the second slide.</p>
                    </div>
                </div>
                <!-- Slide Three - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/pic/posters/pele.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">Third Slide</h3>
                        <p class="lead">This is a description for the third slide.</p>
                    </div>
                </div>
                <!-- Slide Three - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/pic/posters/will-smith.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">Third Slide</h3>
                        <p class="lead">This is a description for the third slide.</p>
                    </div>
                </div>
                <!-- Slide Three - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/pic/posters/blade-runner.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">Third Slide</h3>
                        <p class="lead">This is a description for the third slide.</p>
                    </div>
                </div>
                <!-- Slide Three - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/pic/posters/fantastic-beasts.jpg)">
                    <div class="carousel-caption d-none d-md-block">
                        <h3 class="display-4">Third Slide</h3>
                        <p class="lead">This is a description for the third slide.</p>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

    <!-- Page Content -->
    <section class="py-5">
        <div class="container">
            <h1 class="font-weight-light">Half Page Image Slider</h1>
            <p class="lead">The background images for the slider are set directly in the HTML using inline CSS. The images in this snippet are from <a href="https://unsplash.com">Unsplash</a>!</p>
        </div>
    </section>
</body>
</html>
