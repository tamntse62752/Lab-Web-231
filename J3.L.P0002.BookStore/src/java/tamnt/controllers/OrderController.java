/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.DiscountDAO;
import tamnt.daos.OrderDAO;
import tamnt.dtos.CartBookDTO;
import tamnt.dtos.OrderDTO;
import tamnt.utils.RamdomOrderID;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "orderSuccess.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartBookDTO cart = (CartBookDTO) session.getAttribute("CART");
            if (cart == null || cart.getCart().values().isEmpty()) {
                request.setAttribute("ERROR", "Order cannot be empty");
            } else {
                String userID = (String) session.getAttribute("USERID");
                int orderID = RamdomOrderID.randomInt(0, 9999);
                float total = (float) session.getAttribute("TOTAL");
                String checkDiscount = (String) session.getAttribute("DISCOUNT");
                String txtDiscount = request.getParameter("txtDiscount");
                OrderDTO dto = null;
                if (checkDiscount != null && !txtDiscount.isEmpty()) {
                    dto = new OrderDTO(orderID, total, Date.valueOf(LocalDate.now()), userID, txtDiscount);
                } else {
                    dto = new OrderDTO(orderID, total, Date.valueOf(LocalDate.now()), userID, null);
                }
                dto.getBookList().addAll(cart.getCart().values());
                String[] listQuantityCart = request.getParameterValues("txtQuantityCart");
                String[] listQuantity = request.getParameterValues("txtQuantity");
                boolean checkQuantity = true;
                for (int i = 0; i < listQuantityCart.length; i++) {
                    if (Integer.parseInt(listQuantityCart[i]) > Integer.parseInt(listQuantity[i])) {
                        request.setAttribute("ERROR", "The quantity exceeds the quantity in the store");
                        checkQuantity = false;
                    }
                }
                if (checkQuantity) {
                    OrderDAO dao = new OrderDAO();
                    DiscountDAO daoD = new DiscountDAO();
                    if (dao.inserOrder(dto)) {
                        url = SUCCESS;
                        if (checkDiscount != null && !txtDiscount.isEmpty()) {
                            daoD.updateStatusDiscount(txtDiscount);
                        }
                        session.removeAttribute("CART");
                        session.removeAttribute("CARTLIST");
                        session.removeAttribute("TOTAL");
                        session.removeAttribute("DISCOUNT");
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Order failed");
                    }
                }
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
