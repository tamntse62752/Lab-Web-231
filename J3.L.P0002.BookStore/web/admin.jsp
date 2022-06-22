<%-- 
    Document   : admin
    Created on : Jun 25, 2021, 11:14:42 PM
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
        <link rel="stylesheet" href="./css/admin.css" />
    </head>
    <body>
        <header>
            <c:if test="${requestScope.ERROR != null}" var="alert">
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
            <nav class="navbar navbar-expand-lg">
                <div class="col-7">
                    <div class="header__left">
                        <a class="navbar-brand mr-0" href="#">
                            <img src="./img/logoLPD.jpg" alt="" />
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
                <div class="col-5 px-0">
                    <div class="header__right">
                        <ul class="navbar-nav mr-auto">
                            <div class="d-flex">
                                <li class="nav-item ude-btnGroup d-flex">
                                    <form action="MainController" method="POST">    
                                        <button class="btn btn-warning mx-3 mt-1" type="submit" name="action" value="HistoryAdmin">History Order</button>            
                                        <button class="ude-btnWhite" style="width: 250px">
                                            Welcome, ${sessionScope.USERID} !
                                        </button>
                                        <button type="submit" name="action" value="Logout" class="ude-btnRed">Log Out</button>
                                    </form>
                                </li>
                            </div>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <hr>
        <section class="bodyUser">
            <div style="width: 95%; margin: 0 auto;">
                <div>
                    <div class="font-weight-bold mb-3" style="float: left">
                        Create discount code
                        <a
                            href="createDiscountCode.jsp"
                            >
                            <i class="fa fa-plus"></i>
                        </a>
                    </div>
                    <div class="font-weight-bold mb-3" style="float: right">
                        Create book
                        <a
                            href="createBook.jsp"
                            >
                            <i class="fa fa-plus"></i>
                        </a>
                    </div>
                </div>

                <div>
                    <table class="table"> 
                        <thead>
                            <tr>
                                <th scope="col">#
                                    <div style="height: 28px"></div>
                                </th>
                                <th scope="col">Image
                                    <div style="height: 28px"></div>                                    
                                </th>
                                <th scope="col">Book ID
                                    <div style="height: 28px; width: 60px"></div>                                    
                                </th>
                                <th scope="col">Title
                                    <div style="height: 28px; width: 100px">
                                        <font color="red" class="ml-1">
                                        ${requestScope.INVALID.newTitleError}
                                        </font>
                                    </div>
                                </th>
                                <th scope="col">Description
                                    <div style="height: 28px; width: 100px">
                                        <font color="red" class="ml-1">
                                        ${requestScope.INVALID.newDescriptionError}
                                        </font>
                                    </div>                                    
                                </th>
                                <th scope="col">Price
                                    <div style="height: 28px; width: 120px">
                                        <font color="red" class="ml-1">
                                        ${requestScope.INVALID.newPriceError}
                                        </font>
                                    </div>                                    
                                </th>
                                <th scope="col">Author
                                    <div style="height: 28px; width: 100px">
                                        <font color="red" class="ml-1">
                                        ${requestScope.INVALID.newAuthorError}
                                        </font>
                                    </div>                                    
                                </th>
                                <th scope="col">Quantity
                                    <div style="height: 28px; width: 120px">
                                        <font color="red" class="ml-1">
                                        ${requestScope.INVALID.newQuantityError}
                                        </font>
                                    </div>                                    
                                </th>
                                <th scope="col">Date Created
                                    <div style="height: 28px; width: 100px"></div>                                    
                                </th>
                                <th scope="col">Role
                                    <div style="height: 28px"></div>                                   
                                </th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${requestScope.INFO != null}" var="info">
                                <c:forEach var="dto" items="${requestScope.INFO}" varStatus="counter">
                                    <tr>
                                <form action="MainController" method="POST" enctype="multipart/form-data"> 
                                    <td>${counter.count}</td>
                                    <td>
                                        <img width="80" height="110" src="./img/${dto.image}"> 
                                        <input type="file" style="padding: 0.375rem 1.25rem; margin-left: -1.2rem; border: none" class="form-control" name="newImage">
                                        <input type="hidden" name="image" value="${dto.image}" />
                                    </td>
                                    <td>${dto.bookID}
                                        <input type="hidden" name="txtNewBookID" value="${dto.bookID}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewTitle" value="${dto.title}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewDescription" value="${dto.description}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewPrice" value="${dto.price}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewAuthor" value="${dto.author}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewQuantity" value="${dto.quantity}" />
                                    </td>
                                    <td>${dto.createBookDate}</td>
                                    <td>
                                        <select name="cbxNewCategoryID">  
                                            <c:forEach var="categoryList" items="${sessionScope.CATEGORYLIST}">
                                                <option 
                                                    <c:if test="${dto.categoryID eq categoryList.categoryID}"> 
                                                        selected="true"
                                                    </c:if> 
                                                    value="${categoryList.categoryID}" > ${categoryList.categoryName}
                                                </option>                                                
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>                                               
                                        <button style="padding: 2px; font-size: 14px" class="btn btn-warning" name="action" value="UpdateBook" type="submit">Update</button>                                              
                                    </td>
                                    <td>
                                        <button style="padding: 2px; font-size: 14px" class="btn btn-primary" name="action" value="DeleteBook" type="submit" onclick="if (!confirm('Are you sure?')) {return false;}">Delete</button> 
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!info}">
                            <c:forEach var="dto" items="${requestScope.BOOKLIST}" varStatus="counter">
                                <tr>
                                <form action="MainController" method="POST" enctype="multipart/form-data"> 
                                    <td>${counter.count}</td>
                                    <td>
                                        <img width="80" height="110" src="./img/${dto.image}"> 
                                        <input type="file" style="padding: 0.375rem 1.25rem; margin-left: -1.2rem; border: none" class="form-control" name="newImage">
                                        <input type="hidden" name="image" value="${dto.image}" />
                                    </td>
                                    <td>${dto.bookID}
                                        <input type="hidden" name="txtNewBookID" value="${dto.bookID}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewTitle" value="${dto.title}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewDescription" value="${dto.description}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewPrice" value="${dto.price}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewAuthor" value="${dto.author}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtNewQuantity" value="${dto.quantity}" />
                                    </td>
                                    <td>${dto.createBookDate}</td>
                                    <td>
                                        <select name="cbxNewCategoryID">  
                                            <c:forEach var="categoryList" items="${sessionScope.CATEGORYLIST}">
                                                <option 
                                                    <c:if test="${dto.categoryID eq categoryList.categoryID}"> 
                                                        selected="true"
                                                    </c:if> 
                                                    value="${categoryList.categoryID}" > ${categoryList.categoryName}
                                                </option>                                                
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>                                               
                                        <button style="padding: 2px; font-size: 14px" class="btn btn-warning" name="action" value="UpdateBook" type="submit">Update</button>                                              
                                    </td>
                                    <td>
                                        <button style="padding: 2px; font-size: 14px" class="btn btn-primary" name="action" value="DeleteBook" type="submit" onclick="if (!confirm('Are you sure?')) {return false; }">Delete</button> 
                                    </td>
                                </form>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
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
