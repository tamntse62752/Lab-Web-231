/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.BookDAO;
import tamnt.daos.UserDAO;
import tamnt.dtos.BookDTO;
import tamnt.dtos.LPDErrorObject;
import tamnt.dtos.UserDTO;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    private static final String INVALID = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            LPDErrorObject errorObj = new LPDErrorObject();
            boolean valid = true;
            if (userID.length() == 0) {
                valid = false;
                errorObj.setUserIDError("UserID can't be blank");
            }
            if (password.length() == 0) {
                valid = false;
                errorObj.setPasswordError("Password can't be blank");
            }
            if (valid) {
                UserDAO dao = new UserDAO();
                String check = dao.checkLogin(userID, password);
                UserDTO result = dao.findByUserID(userID);
                if (check.equals("inactive")) {
                    request.setAttribute("ERROR", "Your account is inactive");
                } else if (check.equals("failed")) {
                    request.setAttribute("ERROR", "User ID or password is incorrect");
                } else if (check.equals("AD")) {
                    url = ADMIN;
                } else {
                    url = USER;
                }
                HttpSession session = request.getSession();
                session.setAttribute("USERID", userID);
                session.setAttribute("FULLNAME", result.getFullName());
                session.setAttribute("ROLEID", result.getRoleID());
                BookDAO daoBoo = new BookDAO();
                List<BookDTO> resultBoo = daoBoo.bookList();
                request.setAttribute("BOOKLIST", resultBoo);
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
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