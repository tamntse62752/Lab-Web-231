/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.DiscountDAO;
import tamnt.dtos.DiscountDTO;
import tamnt.dtos.LPDErrorObject;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
@WebServlet(name = "CreateDiscountCodeController", urlPatterns = {"/CreateDiscountCodeController"})
public class CreateDiscountCodeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateDiscountCodeController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "LoadController";
    private static final String INVALID = "createDiscountCode.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String newDiscountCode = request.getParameter("txtNewDiscountCode");
            String newDiscountPercentStr = request.getParameter("txtNewDiscountPercent");
            float  newDiscountPercent = 0;
            LPDErrorObject errorObj = new LPDErrorObject();
            DiscountDAO dao = new DiscountDAO();
            DiscountDTO checkID = dao.findByDiscountCode(newDiscountCode);
            boolean valid = true;
            if (newDiscountCode.length() == 0) {
                valid = false;
                errorObj.setNewDiscountCodeError("Discount Code can't be blank");
            } else if (checkID != null) {
                valid = false;
                errorObj.setNewDiscountCodeError("Discount Code is existed");
            }
            if (newDiscountPercentStr.length() == 0) {
                valid = false;
                errorObj.setNewDiscountPercentError("Discount percent can't be blank");
            } else if (!newDiscountPercentStr.matches("^\\d*\\.?\\d*$")) {
                valid = false;
                errorObj.setNewDiscountPercentError("Discount percent incorrect format");
            } else if (Float.parseFloat(newDiscountPercentStr) >= 1 || Float.parseFloat(newDiscountPercentStr) <= 0) {
                valid = false;
                errorObj.setNewDiscountPercentError("0 < Discount percent < 1");
            } else {
                newDiscountPercent = Float.parseFloat(newDiscountPercentStr);
            }
            if (valid) {
                DiscountDTO dto = new DiscountDTO(newDiscountCode, newDiscountPercent, Date.valueOf(LocalDate.now()), true);
                if (dao.createDiscount(dto)) {
                    url = SUCCESS;
                }
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
