/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.dtos.CartBookDTO;
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
@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateCartController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            CartBookDTO shoppingCart = (CartBookDTO) session.getAttribute("CART");
            String[] listID = request.getParameterValues("txtBookID");
            String[] listQuantityCart = request.getParameterValues("txtQuantityCart");
            String[] listQuantity = request.getParameterValues("txtQuantity");
            for (int i = 0; i < listQuantityCart.length; i++) {
                if (!listQuantityCart[i].matches("^[0-9]{1,}$")) {
                    request.setAttribute("ERROR", "Quantity can't be blank or incorrect format");
                } else if (Integer.parseInt(listQuantityCart[i]) < 1) {
                    request.setAttribute("ERROR", "Quantity must be greater than 0");
                } else if (Integer.parseInt(listQuantityCart[i]) > Integer.parseInt(listQuantity[i])) {
                    request.setAttribute("ERROR", "The quantity exceeds the quantity in the store");
                } else {
                    shoppingCart.updateCart(listID[i], Integer.parseInt(listQuantityCart[i]));
                }
            }
            session.setAttribute("TOTAL", shoppingCart.getTotal());
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
