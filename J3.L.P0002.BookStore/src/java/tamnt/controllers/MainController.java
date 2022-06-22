/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
@MultipartConfig
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String UPDATEBOOK = "UpdateController";
    private static final String DELETEBOOK = "DeleteController";
    private static final String CREATEBOOK = "CreateController";
    private static final String SEARCHBOOK = "SearchController";
    private static final String UPDATECART = "UpdateCartController";
    private static final String ADDTOCART = "AddToCartController";
    private static final String REMOVECART = "RemoveCartController";
    private static final String APPLYCODE = "ApplyDiscountCodeController";
    private static final String CREATECODE = "CreateDiscountCodeController";
    private static final String ORDER = "OrderController";
    private static final String ORDERPAYPAL = "OrderPayPalController";
    private static final String EXECUTE = "ExecutePaymentController";
    private static final String HISTORYUSER = "HistoryUserController";
    private static final String HISTORYADMIN = "HistoryAdminController";
    private static final String HISTORYDETAIL = "HistoryDetailController";
    private static final String SEARCHHISTORYUSER = "SearchHistoryUserController";
    private static final String SEARCHHISTORYADMIN = "SearchHistoryAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("UpdateBook")) {
                url = UPDATEBOOK;
            } else if (action.equals("DeleteBook")) {
                url = DELETEBOOK;
            } else if (action.equals("CreateBook")) {
                url = CREATEBOOK;
            } else if (action.equals("SearchBook")) {
                url = SEARCHBOOK;
            } else if (action.equals("AddToCart")) {
                url = ADDTOCART;
            } else if (action.equals("RemoveCart")) {
                url = REMOVECART;
            } else if (action.equals("UpdateCart")) {
                url = UPDATECART;
            } else if (action.equals("ApplyCode")) {
                url = APPLYCODE;
            } else if (action.equals("CreateCode")) {
                url = CREATECODE;
            } else if (action.equals("BuyCash")) {
                url = ORDER;
            } else if (action.equals("BuyPayPal")) {
                url = ORDERPAYPAL;
            } else if (action.equals("Execute")) {
                url = EXECUTE;
            } else if (action.equals("HistoryUser")) {
                url = HISTORYUSER;
            } else if (action.equals("HistoryAdmin")) {
                url = HISTORYADMIN;
            } else if (action.equals("HistoryDetail")) {
                url = HISTORYDETAIL;
            } else if (action.equals("SearchHistoryUser")) {
                url = SEARCHHISTORYUSER;
            } else if (action.equals("SearchHistoryAdmin")) {
                url = SEARCHHISTORYADMIN;
            } else {
                request.setAttribute("ERROR", "Your action is invalid");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
