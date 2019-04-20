<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 06.04.2019
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<jsp:useBean id="moviesBean" scope="request" type="java.util.List"/>
<html>
<head lang="${sessionScope.curLang}">
    <title>Title</title>
    <link  href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/movies.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/admin/header.jsp"/>
    <link rel='stylesheet prefetch' href='${pageContext.request.contextPath}/css/swiper.min.css'>
    <script src='${pageContext.request.contextPath}/js/swiper.min.js'></script>



    <section class="pricing py-5">
        <div class="wrap-cont">
            <div class="">
                <!-- Free Tier -->
                <div class="">
                    <div class="card-bg-color  mb-5 mb-lg-0">
                        <div class="card-body inner-bg">
                            <style class="cp-pen-styles">img { max-height: 100% }
                            .swiper-container {
                                width: 100%;
                                height: 580px;
                            }
                            .swiper-slide {
                                text-align: center;
                                font-size: 18px;
                                background: #fffB;
                                /* Center slide text vertically */
                                display: -webkit-box;
                                display: -ms-flexbox;
                                display: -webkit-flex;
                                display: flex;
                                -webkit-box-pack: center;
                                -ms-flex-pack: center;
                                -webkit-justify-content: center;
                                justify-content: center;
                                -webkit-box-align: center;
                                -ms-flex-align: center;
                                -webkit-align-items: center;
                                align-items: center;
                                /*width:90%;*/
                            }</style>
                            <!-- Swiper -->
                            <div class="swiper-container andr-swiper">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide andrew-swiper-slide">
                                        <div class="nm-head">
                                            <h2 class="font-weight-normal">Add new movie</h2>
                                            <p class="font-weight-light">Please, make sure that the aspect ratio of image is 10:11 (width : height) </p>
                                        </div>
                                            <div class="nm-form">
                                                <form>
                                                    <div class="form-group">
                                                        <label for="engName">English name</label>
                                                        <input type="text" class="form-control" id="engName" aria-describedby="engNameHint">
                                                        <small id="engNameHint" class="form-text text-muted">Please, enter an english name of the movie</small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="ukrName">Ukrainian name</label>
                                                        <input type="text" class="form-control" id="ukrName" aria-describedby="ukrNameHint">
                                                        <small id="ukrNameHint" class="form-text text-muted">Please, enter an ukrainian name of the movie</small>
                                                    </div>
                                                    <label for="inputGroupFile01">Picture file</label>
                                                    <div class="input-group mb-3">
                                                        <div class="custom-file">
                                                            <input type="file" class="custom-file-input" id="inputGroupFile01">
                                                            <label class="custom-file-label" for="inputGroupFile01"></label>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="nm-btn btn btn-primary mb-2 btn-block">Add movie</button>
                                                </form>
                                            </div>
                                    </div>
                                    <c:forEach items="${moviesBean}" var="entry">
                                        <div class="swiper-slide andrew-swiper-slide">
                                            <div class="container-in">
                                                <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/${entry.picUrl}"/>
                                            </div>
                                            <div class="inner-showtimes">
                                                <h2 class="font-weight-normal">${entry.name}</h2>
                                                <c:forEach items="${entry.sessions}" var="session">
                                                    <button class="btn btn-outline-dark btn-sm"
                                                            onclick="post('/room', {sessionId : ${session.id}}, 'get')">
                                                            ${session.day.shortName} ${session.timeHoursMins}
                                                    </button>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <!-- Add Pagination -->
                            <div class="swiper-pagination andrew-bullets"></div>
                            <script >var swiper = new Swiper('.swiper-container', {
                                pagination: '.swiper-pagination',
                                effect: 'coverflow',
                                grabCursor: true,
                                centeredSlides: true,
                                spaceBetween: 0,
                                //loop: true,
                                /*autoplay: 5000,*/
                                autoplayDisableOnInteraction: false,
                                slidesPerView: 4,
                                coverflow: {
                                    rotate: 30,
                                    stretch: 0,
                                    depth: 100,
                                    modifier: 1,
                                    slideShadows : true
                                }
                            });

                            //# sourceURL=pen.js
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
