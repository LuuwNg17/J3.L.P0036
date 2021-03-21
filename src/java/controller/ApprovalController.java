/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Comment;
import entities.User;
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
import modal.PostDAO;

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
            PostDAO postDao = new PostDAO();
            
            User user = (User)request.getSession(false).getAttribute("user");
            
            ArrayList<Comment> allComments
                    = cmtDao.getAllCommentToManagebyPostAuthor(user.getUser_name());
            
            List<Comment> pedingComment = allComments.stream()
                        .filter(cmt -> cmt.isIsApproved() == false 
                                && cmt.getReject_reason_id() == 1)
                        .collect(Collectors.toList());
            
            List<Comment> approvedComment = allComments.stream()
                        .filter(cmt -> cmt.isIsApproved() == true)
                        .collect(Collectors.toList());

            List<Comment> spamComment = allComments.stream()
                        .filter(cmt -> cmt.getReject_reason_id()== 2)
                        .collect(Collectors.toList());
            
            List<Comment> trashComment = allComments.stream()
                        .filter(cmt -> cmt.getReject_reason_id()== 3)
                        .collect(Collectors.toList());
            //all comment
            if (type.equals("all")) {
                
                request.setAttribute("comments", allComments);

            } //peding comment
            else if (type.equals("0")) {
                request.setAttribute("comments", pedingComment);
            } //approved comment
            else if (type.equals("1")) {
               
                request.setAttribute("comments", approvedComment);
            } //spam coomment
            else if (type.equals("2")) {
                
                request.setAttribute("comments", spamComment);
            } //trash comment
            else if (type.equals("3")) {
                
                request.setAttribute("comments", trashComment);
            } else {
                request.setAttribute("error", "Sorry! can not handle your request");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("numberAll", allComments.size());
            request.setAttribute("numberPending", pedingComment.size());
            request.setAttribute("numberApproved", approvedComment.size());
            request.setAttribute("numberSpam", spamComment.size());
            request.setAttribute("numberTrash", trashComment.size());
            request.setAttribute("posts", postDao.getAllPost());
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
