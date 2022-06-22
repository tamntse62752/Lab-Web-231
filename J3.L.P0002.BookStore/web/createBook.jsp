<%-- 
    Document   : createBook
    Created on : Jun 28, 2021, 7:39:50 PM
    Author     : SIMON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Create book</title>
        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <!-- Font As -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="./css/createBook.css" />
    </head>
    <body>
        <div class="login">
            <div id="logreg-forms" class="logreg-forms2">
                <form class="form-signin" action="MainController" method="POST" enctype="multipart/form-data">
                    <input type="text" 
                           name="txtNewBookID" 
                           class="form-control" 
                           placeholder="BookID" 
                           />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newBookIDError}
                        </font>
                    </div>
                    <input type="text" 
                           name="txtNewTitle" 
                           class="form-control" 
                           placeholder="Title" 
                           value="${param.txtNewTitle}"
                           />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newTitleError}
                        </font>
                    </div>
                    <input
                        type="text"
                        name="txtNewDescription"
                        class="form-control"
                        placeholder="Description"
                        value="${param.txtNewDescription}"
                        />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newDescriptionError}
                        </font>
                    </div>
                    <input
                        type="text"
                        name="txtNewPrice"
                        class="form-control"
                        placeholder="Price" 
                        value="${param.txtNewPrice}"
                        />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newPriceError}
                        </font>
                    </div>
                    <input type="text" 
                           name="txtNewAuthor" 
                           class="form-control" 
                           placeholder="Author" 
                           value="${param.txtNewAuthor}"
                           />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newAuthorError}
                        </font>
                    </div>
                    <input type="text" 
                           name="txtNewQuantity" 
                           class="form-control" 
                           placeholder="Quantity" 
                           value="${param.txtNewQuantity}"
                           />
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newQuantityError}
                        </font>
                    </div>
                    <input type="file" class="form-control" name="newImage">
                    <div style="height: 22px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.newImageError}
                        </font>
                    </div>
                    <select name="cbxNewCategoryID">
                        <c:forEach var="categoryList" items="${sessionScope.CATEGORYLIST}">
                            <option value="${categoryList.categoryID}">${categoryList.categoryName}</option>
                        </c:forEach>
                    </select>
                    <button
                        class="btn btn-primary btn-block"
                        id="btn-signup"
                        name="action"
                        type="submit"
                        value="CreateBook"
                        > CREATE
                    </button>
                    <a href="LoadController" style="text-align: right;">Back to page<i class="fa fa-undo ml-2"></i></a>
                </form>
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
