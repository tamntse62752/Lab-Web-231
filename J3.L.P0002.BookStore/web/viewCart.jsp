<%-- 
    Document   : newCart
    Created on : Jun 26, 2021, 10:47:01 AM
    Author     : SIMON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
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

    </head>
    <body style="background-color: #EED8DE; height: 100%;">
        <div class="viewCart">
            <form action="MainController" method="POST">
                <div class="modal-dialog modal-xl modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">
                                <c:if test="${sessionScope.FULLNAME == null}" var="checkName">
                                    ${sessionScope.NAME}
                                </c:if>
                                <c:if test="${!checkName}">
                                    ${sessionScope.FULLNAME}
                                </c:if>
                            </h5>
                            <div>
                                <font color="red">
                                ${requestScope.ERROR}
                                </font>
                            </div>
                            <a href="LoadController" style="text-align: right; color: red">Back to Home page<i class="fa fa-undo ml-2"></i></a>
                        </div>
                        <div class="modal-body">
                            <div class="bgTable">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col"></th>
                                            <th scope="col">Title</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Total</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${sessionScope.CARTLIST != null}">
                                            <c:forEach var="dto" items="${sessionScope.CARTLIST}" varStatus="counter">
                                                <tr>
                                                    <td scope="row">${counter.count}</td>
                                                    <td><img height="110" width="110" src="./img/${dto.image}" alt=""></td>
                                                    <td style="font-weight: bold;">${dto.title}</td>
                                                    <td>
                                                        <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                                        <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                                                        <input type="text" name="txtQuantityCart" value="${dto.quantityCart}" />
                                                    </td>
                                                    <td>${dto.price}</td>
                                                    <td>${dto.quantityCart*dto.price}</td>
                                                    <td><input type="checkbox" name="chkRemove" value="${dto.bookID}" /></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">  
                            TOTAL: ${sessionScope.TOTAL}
                            <button type="submit" name="action" value="UpdateCart" class="btn btn-success ml-3">UPDATE</button>
                            <button type="submit" name="action" value="RemoveCart" class="btn btn-danger" onclick="if (!confirm('Are you sure?')) { return false; }"><i class="fa fa-trash-alt mr-2"></i>DELETE</button>                         
                            <c:if test="${sessionScope.FULLNAME != null }" var="checkBuy">                                                                 
                                    <input type="text" name="txtDiscount" value="${param.txtDiscount}" placeholder="Input discount code" />
                                    <button type="submit" name="action" value="ApplyCode" class="btn btn-secondary mr-3">APPLY</button>
                                    <button type="submit" name="action" value="BuyCash" class="btn btn-warning">CHECK OUT</button>
                                    <input type="hidden" name="txtProductName" value="${sessionScope.FULLNAME}" />
                                    <input type="hidden" name="txtSubtotal" value="0" />
                                    <input type="hidden" name="txtShipping" value="0" />
                                    <input type="hidden" name="txtTax" value="0" />
                                    <input type="hidden" name="txtTotal" value="${sessionScope.TOTAL}" />
<!--                                    <button type="submit" name="action" value="BuyPayPal" class="btn btn-primary">BUY PAYPAL</button> -->
                            </c:if>
                            </form>
                            <c:if test="${!checkBuy}">
                                <a href="login.jsp" class="btn btn-warning">
                                    CHECK OUT
                                </a>
<!--                                <a href="login.jsp" class="btn btn-primary">
                                    BUY PAYPAL
                                </a>-->
                            </c:if>
                        </div>
                    </div>
                </div>

        </div>
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
