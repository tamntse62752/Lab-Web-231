/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.controllers;

import tamnt.daos.BookDAO;
import tamnt.dtos.BookDTO;
import tamnt.dtos.LPDErrorObject;
import tamnt.utils.UploadImage;
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
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "LoadController";
    private static final String INVALID = "createBook.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String newBookID = request.getParameter("txtNewBookID");
            String newTitle = request.getParameter("txtNewTitle");
            String newDescription = request.getParameter("txtNewDescription");
            String newPriceStr = request.getParameter("txtNewPrice");
            float newPrice = 0;
            String newAuthor = request.getParameter("txtNewAuthor");
            String newQuantityStr = request.getParameter("txtNewQuantity");
            int newQuantity = 0;
            String newCategory = request.getParameter("cbxNewCategoryID");
            UploadImage uploadImage = new UploadImage();
            String fileName = uploadImage.uploadFile(request);
            LPDErrorObject errorObj = new LPDErrorObject();
            BookDAO dao = new BookDAO();
            BookDTO checkID = dao.checkBookID(newBookID);
            boolean valid = true;
            if (!newBookID.matches("^[A-Z]{1}-[0-9]{3}$")) {
                valid = false;
                errorObj.setNewBookIDError("Book ID isn't correct format: X-000");
            } else if (checkID != null) {
                valid = false;
                errorObj.setNewBookIDError("Book ID is existed");
            }
            if (newTitle.length() == 0) {
                valid = false;
                errorObj.setNewTitleError("Title can't be blank");
            }
            if (newDescription.length() == 0) {
                valid = false;
                errorObj.setNewDescriptionError("Description can't be blank");
            }
            if (newPriceStr.length() == 0) {
                valid = false;
                errorObj.setNewPriceError("Price can't be blank");
            } else if (!newPriceStr.matches("^\\d*\\.?\\d*$")) {
                valid = false;
                errorObj.setNewPriceError("Price incorrect format");
            } else if (Float.parseFloat(newPriceStr) <= 0) {
                valid = false;
                errorObj.setNewPriceError("Price must > 0");
            } else {
                newPrice = Float.parseFloat(newPriceStr);
            }
            if (newAuthor.length() == 0) {
                valid = false;
                errorObj.setNewAuthorError("Author can't be blank");
            }
            if (newQuantityStr.length() == 0) {
                valid = false;
                errorObj.setNewQuantityError("Quantity can't be blank");
            } else if (!newQuantityStr.matches("[0-9]{1,}")) {
                valid = false;
                errorObj.setNewQuantityError("Quantity incorrect format");
            } else if (Integer.parseInt(newQuantityStr) < 1) {
                valid = false;
                errorObj.setNewQuantityError("Quantity must > 0");
            } else {
                newQuantity = Integer.parseInt(newQuantityStr);
            }
            if (fileName.isEmpty()) {
                valid = false;
                errorObj.setNewImageError("Image can't be blank");
            }
            if (valid) {
                BookDTO dto = new BookDTO(newBookID, newTitle, fileName, newDescription, newAuthor, "Active", newCategory, newPrice, newQuantity, Date.valueOf(LocalDate.now()));
                if (dao.createBook(dto)) {
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
