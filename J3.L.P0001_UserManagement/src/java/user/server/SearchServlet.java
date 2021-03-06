/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.driver.userDAO;
import user.driver.userDTO;

/**
 *
 * @author Admin
 */
public class SearchServlet extends HttpServlet {

    private final String ERROR_PAGE = "login.jsp";
    private final String ADMIN_PAGE = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            String option = request.getParameter("Option");
            String searchName = request.getParameter("SearchName");
            userDAO dao = new userDAO();
            List<userDTO> listUser;
            HttpSession session = request.getSession();
            if (option.equals("All") && searchName.equals("")) {
                listUser = dao.getListUserActive();
                url = ADMIN_PAGE;
            } else if (option.equals("Admin") && searchName.equals("")) {
                listUser = dao.getListUserByRoleActive("1");
                url = ADMIN_PAGE;
            } else if (option.equals("User") && searchName.equals("")) {
                listUser = dao.getListUserByRoleActive("0");
                url = ADMIN_PAGE;
            } else {
                listUser = dao.getListUserByName(searchName);
                url = ADMIN_PAGE;
            }
            session.setAttribute("LISTUSER", listUser);

        } catch (Exception e) {
            log("Error at Search Servlet: " + e.toString());

        } finally {
            response.sendRedirect(url);
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
