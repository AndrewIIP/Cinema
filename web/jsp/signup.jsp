<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 23.03.2019
  Time: 15:59
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
    <title>Title</title>
    <link  href="${pageContext.request.contextPath}/css/signup.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.slim.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card card-signin flex-row ">
                <%--<div class="card-img-left d-none d-md-flex">
                    <!-- Background image for card set in CSS! -->
                </div>--%>
                <div class="card-body">
                    <h5 class="card-title text-center">Register</h5>
                    <form class="form-signin">
                        <div class="form-label-group">
                            <input type="text" id="inputUserame" class="form-control" placeholder="Username" required autofocus>
                            <label for="inputUserame">Username</label>
                        </div>

                        <div class="form-label-group">
                            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
                            <label for="inputEmail">Email address</label>
                        </div>

                        <hr>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                            <label for="inputPassword">Password</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputConfirmPassword" class="form-control" placeholder="Password" required>
                            <label for="inputConfirmPassword">Confirm password</label>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
                        <a class="d-block text-center mt-2 small" href="#">Sign In</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
