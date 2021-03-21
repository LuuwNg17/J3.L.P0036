/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.CommentDAO;

/**
 *
 * @author ThaiDuongNg
 */
public class HandleCommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            int id = Integer.parseInt(request.getParameter("id"));

            CommentDAO cmtDao = new CommentDAO();

            //approve comment
            if (type.equals("1")) {
                cmtDao.approveCommnent(id);
                response.sendRedirect("approve?type=0");
            } //reject comment by spam 
            else if (type.equals("2")) {
                cmtDao.rejectCommentwithReason(2, id);
                response.sendRedirect("approve?type=0");
            }//reject comment by trash 
            else if (type.equals("3")) {
                cmtDao.rejectCommentwithReason(3, id);
                response.sendRedirect("approve?type=0");
            } else {
                request.setAttribute("error", "Sorry! Error occurred");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
