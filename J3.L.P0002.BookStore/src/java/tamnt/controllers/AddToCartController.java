/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.BookDAO;
import tamnt.dtos.BookDTO;
import tamnt.dtos.CartBookDTO;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddToCartController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("FULLNAME");
            CartBookDTO shoppingCart = (CartBookDTO) session.getAttribute("CART");
            if (shoppingCart == null && name == null) {
                shoppingCart = new CartBookDTO();
            } else if (shoppingCart == null) {
                shoppingCart = new CartBookDTO(name);
            }
            String image = request.getParameter("txtImage");
            String bookID = request.getParameter("txtBookID");
            String title = request.getParameter("txtTitle");
            String price = request.getParameter("txtPrice");
            String quantity = request.getParameter("txtQuantity");

            BookDTO dto = new BookDTO(bookID, title, image, Float.parseFloat(price), Integer.parseInt(quantity));
            
            shoppingCart.addToCart(dto);
            session.setAttribute("CART", shoppingCart);
            session.setAttribute("SIZE", shoppingCart.getCart().values().size());
            session.setAttribute("NAME", shoppingCart.getCustomerName());
            session.setAttribute("CARTLIST", shoppingCart.getCart().values());
            session.setAttribute("TOTAL", shoppingCart.getTotal());
            
            BookDAO daoBoo = new BookDAO();
            List<BookDTO> resultBoo = daoBoo.bookList();
            request.setAttribute("BOOKLIST", resultBoo);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher("user.jsp").forward(request, response);
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
