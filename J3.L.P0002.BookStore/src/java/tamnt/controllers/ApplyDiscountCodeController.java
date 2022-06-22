/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.DiscountDAO;
import tamnt.daos.OrderDAO;
import tamnt.dtos.CartBookDTO;
import tamnt.dtos.DiscountDTO;
import tamnt.dtos.OrderDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
@WebServlet(name = "ApplyDiscountCodeController", urlPatterns = {"/ApplyDiscountCodeController"})
public class ApplyDiscountCodeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ApplyDiscountCodeController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USERID");
            String discount = request.getParameter("txtDiscount");
            OrderDAO daoO = new OrderDAO();
            OrderDTO checkUsed = daoO.checkUsedDiscount(userID, discount);
            DiscountDAO daoD = new DiscountDAO();
            DiscountDTO checkDiscount = daoD.findByDiscountCode(discount);
            if (checkUsed != null) {
                request.setAttribute("ERROR", "You have already used this discount code!");
            } else if (checkDiscount == null) {
                request.setAttribute("ERROR", "This discount code does not exist!");
            } else {
                float percent = checkDiscount.getDiscountPercent();
                CartBookDTO shoppingCart = (CartBookDTO) session.getAttribute("CART");
                session.setAttribute("TOTAL", shoppingCart.getTotal() * (1 - percent));
                session.setAttribute("DISCOUNT", discount);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
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
