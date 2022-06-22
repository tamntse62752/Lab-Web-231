<%-- 
    Document   : login
    Created on : Jun 25, 2021, 11:14:31 PM
    Author     : SIMON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Login page</title>
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <!-- Font As -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="./css/login.css" />
    </head>
    <body>
        <c:if test="${requestScope.ERROR != null}">
            <div
                class="alert alert-danger alert-dismissible fade show"
                role="alert"
                style="position: fixed;"
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
        <div class="login">
            <div id="logreg-forms">
                <form class="form-signin" action="MainController" method="POST">
                    <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
                        Sign in
                    </h1>
                    <input
                        type="text"
                        name="txtUserID"
                        class="form-control"
                        placeholder="UserID"
                        value="${param.txtUserID}"
                        />
                    <div style="height: 30px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.userIDError}
                        </font>
                    </div>
                    <input
                        type="password"
                        name="txtPassword"
                        class="form-control"
                        placeholder="Password"
                        />
                    <div style="height: 30px">
                        <font color="red" class="ml-1">
                        ${requestScope.INVALID.passwordError}
                        </font>
                    </div>
                    <button
                        class="btn btn-success btn-block"
                        type="submit"
                        name="action"
                        value="Login"
                        >
                        Sign in
                    </button>
                </form>
                <a href="LoadController" style="text-align: right;">Back to Home page<i class="fa fa-undo ml-2"></i></a>
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
