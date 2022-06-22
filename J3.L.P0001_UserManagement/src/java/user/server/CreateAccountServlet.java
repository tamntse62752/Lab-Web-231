/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.server;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.ExceededSizeException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.util.encrypted;
import user.driver.userDAO;
import user.driver.userDTO;

/**
 *
 * @author Admin
 */
public class CreateAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE = "admin.jsp";
    private final String CREATE_PAGE = "createAccount.jsp";

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
        String url = LOGIN_PAGE;
        try {
            String path = request.getServletContext().getRealPath("");
            String pathReplace = path.replace("\\", "/");
            int indexBuild = path.indexOf("build");
            String pathRI = pathReplace.substring(0, indexBuild) + pathReplace.substring(indexBuild + 6);

            String pathFull = null;
            if (pathRI.endsWith("/")) {
                pathFull = pathRI + "image";
            } else {
                pathFull = pathRI + "/" + "image";
            }
            MultipartRequest multi = new MultipartRequest(request, pathFull, 1024 * 1024 * 10);
            boolean check = true;
            String errorMessage = "";
            userDAO dao = new userDAO();
            String Username = multi.getParameter("txtUsername");
            String PhotoCode = null;

            Enumeration files = multi.getFileNames();

            while (files.hasMoreElements()) {
                String load = (String) files.nextElement();
                PhotoCode = multi.getOriginalFileName(load);
            }
            String Password = multi.getParameter("txtPassword");
            String RePassword = multi.getParameter("txtRePassword");
            String Name = multi.getParameter("txtName");
            String Email = multi.getParameter("txtEmail");
            String PhoneNumber = multi.getParameter("txtPhoneNumber");
            String Role = multi.getParameter("txtRole");
            String Status = multi.getParameter("txtStatus");
            if (!Password.equals(RePassword)) {
                check = false;
                errorMessage = "The Password and Repassword must match!";
            }
            if (dao.checkExist(Username)) {
                check = false;
                errorMessage = "The Account Existed, Choose new Account!";
            }
            if (check) {
                userDTO user = new userDTO(Username, encrypted.encryptedPassword(Password), PhotoCode, Name, Email, PhoneNumber, Integer.parseInt(Role), Status);
                if (dao.insertUser(user) > 0) {
                    url = LOGIN_PAGE;
                } else {
                    errorMessage = "Can't Insert Account, check again Infomation!";
                    url = CREATE_PAGE;
                }
            } else {
                url = CREATE_PAGE;
            }
            request.setAttribute("CREATE_ERROR", errorMessage);
        } catch (NumberFormatException ex) {
            log("AdminCreateAccount_Number: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AdminCreateAccountServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("AdminCreateAccountServlet_SQLE: " + ex.getMessage());
        } catch (ExceededSizeException ex) {
            request.setAttribute("MAXSIZE", "Maximum size: 10MB");
        } catch(IOException ex) {
            log("AdminCreateAccountServlet_IO: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
