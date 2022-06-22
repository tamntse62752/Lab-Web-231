/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import com.paypal.base.rest.PayPalRESTException;
import tamnt.dtos.CartBookDTO;
import tamnt.dtos.OrderPayPalDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import paypal.PaymentServices;

/**
 *
 * @author SIMON
 */
@WebServlet(name = "OrderPayPalController", urlPatterns = {"/OrderPayPalController"})
public class OrderPayPalController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderPayPalController.class);
    private static final String ERROR = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartBookDTO cart = (CartBookDTO) session.getAttribute("CART");
            if (cart == null || cart.getCart().values().isEmpty()) {
                request.setAttribute("ERROR", "Order cannot be empty");
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                String productName = request.getParameter("txtProductName");
                String subtotal = request.getParameter("txtSubtotal");
                String shipping = request.getParameter("txtShipping");
                String tax = request.getParameter("txtTax");
                String total = request.getParameter("txtTotal");
                String discount = request.getParameter("txtDiscount");
                String checkDiscount = (String) session.getAttribute("DISCOUNT");
                OrderPayPalDTO dto = new OrderPayPalDTO(productName, subtotal, shipping, tax, total);
                String[] listQuantityCart = request.getParameterValues("txtQuantityCart");
                String[] listQuantity = request.getParameterValues("txtQuantity");
                boolean checkQuantity = true;
                for (int i = 0; i < listQuantityCart.length; i++) {
                    if (Integer.parseInt(listQuantityCart[i]) > Integer.parseInt(listQuantity[i])) {
                        request.setAttribute("ERROR", "The quantity exceeds the quantity in the store");
                        checkQuantity = false;
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                }
                if (checkQuantity) {
                    if(checkDiscount != null && !discount.isEmpty()) {
                        session.setAttribute("DISCOUNT", discount);
                    }
                    PaymentServices paymentServices = new PaymentServices();
                    String approvalLink = paymentServices.authorizePayment(dto);
                    response.sendRedirect(approvalLink);         
                }
            }
        } catch (PayPalRESTException e) {
            LOGGER.error(e);
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
