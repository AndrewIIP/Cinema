<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 06.04.2019
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<script src='https://production-assets.codepen.io/assets/editor/live/console_runner-079c09a0e3b9ff743e39ee2d5637b9216b3545af0de366d4b9aad9dc87e26bfd.js'></script>
<script src='https://production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script>
<meta charset='UTF-8'>
<meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="https://production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type="" href="https://production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" />
<link rel="canonical" href="https://codepen.io/abennington/pen/GZeyKr?depth=everything&order=popularity&page=45&q=pack&show_forks=false" />

<link rel='stylesheet prefetch' href='${pageContext.request.contextPath}/css/swiper.min.css'>
                <%--<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>--%>
<script src='https://production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
                <%--<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>--%>
<script src='https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.3.1/js/swiper.min.js'></script>

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
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
                                <div class="swiper-slide andrew-swiper-slide">
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
                                <div class="swiper-slide andrew-swiper-slide">
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
                                <div class="swiper-slide andrew-swiper-slide">
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
                                <div class="swiper-slide andrew-swiper-slide">
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
                                <div class="swiper-slide andrew-swiper-slide">
                                    <div class="container-in">
                                        <img class="inner-pic" src="${pageContext.request.contextPath}/pic/playbill/land_cropped.jpg"/>
                                    </div>
                                    <div class="inner-showtimes">
                                        <h2 class="font-weight-normal">La La Land</h2>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                        <button class="btn btn-outline-dark btn-sm">MON 22:00</button>
                                    </div>
                                </div>
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


