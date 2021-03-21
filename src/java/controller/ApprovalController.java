/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.CommentDAO;

/**
 *
 * @author ThaiDuongNg
 */
public class ApprovalController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //get filter comment by ...
            String type = request.getParameter("type");
            CommentDAO cmtDao = new CommentDAO();
            ArrayList<Comment> comments
                    = cmtDao.getAllCommentToManagebyPostAuthor("user1");

            //all comment
            if (type.equals("all")) {
                request.setAttribute("comments", comments);

            } //peding comment
            else if (type.equals("0")) {
                List<Comment> list = comments.stream()
                        .filter(cmt -> cmt.isIsApproved() == false)
                        .collect(Collectors.toList());
                request.setAttribute("comments", list);
            } //approved comment
            else if (type.equals("1")) {
               List<Comment> list = comments.stream()
                        .filter(cmt -> cmt.isIsApproved() == true)
                        .collect(Collectors.toList());
                request.setAttribute("comments", list);
            } //spam coomment
            else if (type.equals("2")) {
                List<Comment> list = comments.stream()
                        .filter(cmt -> cmt.getReject_reason_id()== 2)
                        .collect(Collectors.toList());
                request.setAttribute("comments", list);
            } //trash comment
            else if (type.equals("3")) {
                List<Comment> list = comments.stream()
                        .filter(cmt -> cmt.getReject_reason_id()== 3)
                        .collect(Collectors.toList());
                request.setAttribute("comments", list);
                request.setAttribute("comments", list);
            } else {
                request.setAttribute("error", "Sorry! can not handle your request");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("approve", "active");
            request.getRequestDispatcher("approve.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("error", "Sorry! Error occurred");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
