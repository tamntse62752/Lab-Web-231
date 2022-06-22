<%-- 
    Document   : user
    Created on : Jun 25, 2021, 11:14:20 PM
    Author     : SIMON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
            crossorigin="anonymous"
            />
        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="./css/user.css" />
    </head>
    <body>
        <header>
            <nav class="navbar">
                <div class="col-5">
                    <div class="header__left">
                        <a class="navbar-brand mr-0" href="#">
                            <img src="img/logoLPD.jpg" alt="" />
                        </a>
                        <form action="MainController" method="POST" class="header__form align-self-center ml-5">
                            <div class="input-group">
                                <div>
                                    <select class="btn btn-primary mr-2" name="cbxSearchCategory">
                                        <c:forEach var="dto" items="${sessionScope.CATEGORYLIST}">
                                            <option 
                                                value="${dto.categoryID}" > ${dto.categoryName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input
                                    type="text"
                                    name="txtSearchTitle"
                                    class="form-control ude-HoverbgInputSearch"
                                    placeholder="Book title"
                                    />
                                <input
                                    type="text"
                                    name="txtSearchPrice"
                                    class="form-control ude-HoverbgInputSearch mx-2"
                                    placeholder="Price"
                                    />
                                <button type="submit"
                                        name="action"
                                        value="SearchBook"
                                        class="ude-btnHoverbgSearch">
                                    <div class="input-group-append button">
                                        <span
                                            class="input-group-text ude-btnHoverSearch"
                                            ><i class="fa fa-search"></i
                                            ></span>
                                    </div>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-5">
                    <div class="header__right">
                        <ul class="navbar-nav mr-auto">
                            <div class="d-flex">
                                <li class="nav-item mx-1 border-left">
                                    <a
                                        class="nav-link p-3 ude-btnHoverHeaderCircle"
                                        href="viewCart.jsp"
                                        >
                                        <i class="fa fa-shopping-cart"></i>
                                    </a>
                                </li>
                                <li
                                    style="
                                    width: 25px;
                                    height: 25px;
                                    background-color: #ec5252;
                                    color: white;
                                    font-size: 16px;
                                    "
                                    class="rounded-circle mr-2"
                                    >
                                    <span style="margin: 8.5px;">
                                        <c:if test="${sessionScope.CART != null}" var="checkCart">
                                            <c:if test="${sessionScope.SIZE > 9}" var="size">
                                                9+
                                            </c:if>
                                            <c:if test="${!size}">
                                                ${sessionScope.SIZE}
                                            </c:if>
                                        </c:if>
                                        <c:if test="${!checkCart}">
                                            0
                                        </c:if>
                                    </span>
                                </li>
                                <li class="nav-item ude-btnGroup d-flex">                                        
                                    <c:if test="${sessionScope.FULLNAME == null}" var="checkLogin">
                                        <a href="login.jsp">
                                            <button style="margin-left: 350px" class="ude-btnRed">Log In</button>
                                        </a>
                                    </c:if>
                                    <c:if test="${!checkLogin}">
                                        <form action="MainController" method="POST">     
                                            <button type="submit" name="action" value="HistoryUser" class="btn btn-warning mr-3 mt-1">History</button>
                                            <button class="ude-btnWhite" style="width: 250px" disabled="">
                                                Welcome, ${sessionScope.FULLNAME} !
                                            </button>                                            
                                            <button type="submit" name="action" value="Logout" class="ude-btnRed">Log Out</button>
                                        </form>
                                    </c:if>
                                </li>
                            </div>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <hr/>
        <section class="bodyUser">
            <c:if test="${requestScope.ERROR != null}">
                <div
                    class="alert alert-danger alert-dismissible fade show"
                    role="alert"
                    style="position: fixed; z-index: 9999; margin-top: 100px;"
                    >
                    <strong>${requestScope.ERROR}</strong>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="alert"
                        aria-label="Close"
                        >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <div class="container">
                <div class="row">
                    <c:if test="${requestScope.INFO != null}" var="info">
                        <c:if test="${not empty requestScope.INFO}" var="checkList">
                            <c:forEach var="dto" items="${requestScope.INFO}">
                                <div class="col-4 mb-3">
                                    <div class="card" style="width: 22rem; height: 33rem">
                                        <img height="280" src="./img/${dto.image}" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Title: ${dto.title}</h5>
                                            <p class="card-text">Description: ${dto.description} </p>
                                            <p class="card-text">Price: ${dto.price}$ - Quantity: ${dto.quantity} </p>
                                            <p class="card-text">Author: ${dto.author} - Category:
                                                <c:if test="${dto.categoryID eq 'C'}">
                                                    C
                                                </c:if>
                                                <c:if test="${dto.categoryID eq 'J'}">
                                                    Java
                                                </c:if>
                                                <c:if test="${dto.categoryID eq 'P'}">
                                                    Python
                                                </c:if>
                                            </p>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="txtImage" value="${dto.image}" />
                                                <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                                <input type="hidden" name="txtTitle" value="${dto.title}" />
                                                <input type="hidden" name="txtPrice" value="${dto.price}" />
                                                <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                                                <button type="submit" name="action" value="AddToCart" class="btn btn-primary"><i class="fa fa-shopping-cart mr-2"></i>Add to cart</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${!checkList}">
                            <font color = "Red" class="display-4">
                            Book not found
                            </font>
                        </c:if>
                    </c:if>

                    <c:if test="${!info}" >
                        <c:forEach var="dto" items="${requestScope.BOOKLIST}">
                            <div class="col-4 mb-3">
                                <div class="card" style="width: 22rem; height: 33rem">
                                    <img height="280" src="./img/${dto.image}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">${dto.title}</h5>
                                        <p class="card-text">Description: ${dto.description} </p>
                                        <p class="card-text">Price: ${dto.price}$ - Quantity: ${dto.quantity} </p>
                                        <p class="card-text">Author: ${dto.author} - Category: 
                                            <c:if test="${dto.categoryID eq 'C'}">
                                                C
                                            </c:if>
                                            <c:if test="${dto.categoryID eq 'J'}">
                                                Java
                                            </c:if>
                                            <c:if test="${dto.categoryID eq 'P'}">
                                                Python
                                            </c:if>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="txtImage" value="${dto.image}" />
                                            <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                            <input type="hidden" name="txtTitle" value="${dto.title}" />
                                            <input type="hidden" name="txtPrice" value="${dto.price}" />
                                            <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                                            <button type="submit" name="action" value="AddToCart" class="btn btn-primary"><i class="fa fa-shopping-cart mr-2"></i>Add to cart</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>
        <footer>
            <div class="footer__content">
                <div class="row footer__rowSecond py-3">
                    <div class="col-12 col-md-1 footer__img">
                        <div class="footer__logo">
                            <img src="img/logoLPD.jpg" alt="logoLPD" />
                        </div>
                    </div>
                    <div class="col-12 col-md-9 footer__hr__content">
                        <h6>LPD COMPANY</h6>
                        <p>
                            Address : 208 Nguyen Huu Canh, Ward 22, Binh Thanh District, Ho Chi Minh City.
                        </p>
                        <p>
                            Certificate of business registration number: 0101659783, <br> 30th change registration, 22 month
                            01 year 2020 by the Department of Planning and Investment of Ho Chi Minh City.
                        </p>
                        <p>Hotline: 0974 359 136</p>
                        <p>
                            Email: <a href="#" class="footer__email">lp.duy2k@gmail.com</a>
                        </p>
                    </div>
                    <div class="col-12 col-md-2 footer__img">
                        <div class="footer__BoCo mb-5">
                            <a
                                href="http://online.gov.vn/Home/WebDetails/62782?AspxAutoDetectCookieSupport=1"
                                >
                                <img
                                    src="img/thongbaobocongthuong.jpg"
                                    alt="logoBoCongThuong"
                                    />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

