<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 22.03.2019
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>

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
                    <a class="nav-link" href="#"><fmt:message key="header.playbill"/></a>
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
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/"
                            style="padding-left: 12px">
                            <img src="${pageContext.request.contextPath}/pic/langs/United-Kingdom.png"
                                style="margin-right: 24px"/><fmt:message key="header.lang.eng"/>
                        </a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/?curLang=uk"
                            style="padding-left: 12px;">
                            <img src="${pageContext.request.contextPath}/pic/langs/Ukraine.png"
                                style="margin-right: 24px"/><fmt:message key="header.lang.ukr"/>
                        </a>
                    </div>
                </li>
                <li class="nav-item mybutton">
                    <a class="btn nav-link rounded singup" href="${pageContext.request.contextPath}/jsp/signup.jsp"><fmt:message key="header.signup"/></a>
                </li>
                <li class="dropdown mybutton">
                    <a href="#" class="btn-warning nav-link rounded login" data-toggle="dropdown"><fmt:message key="header.login"/><span class="caret"></span></a>
                    <ul id="login-dp" class="dropdown-menu shadow-c">
                        <li>
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form" method="post" role="form" accept-charset="UTF-8" id="login-nav">
                                        <div class="form-group">
                                            <label class="sr-only" for="username"></label>
                                            <input id="username" type="text" class="form-control" placeholder="<fmt:message key="header.inplaceholder.email"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="password"></label>
                                            <input id="password" type="password" class="form-control" placeholder="<fmt:message key="header.inplaceholder.password"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <button id="signBtn" type="button" class="btn btn-primary btn-block"><fmt:message key="header.login"/></button>
                                        </div>
                                    </form>
                                    <div id="errorInp" class="alert alert-danger">
                                        <span id="osp">some msg</span>
                                    </div>
                                </div>
                                <div class="bottom text-center">
                                    <fmt:message key="header.loginbar.newhere"/> <a href="#"><b><fmt:message key="header.loginbar.join"/></b></a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
