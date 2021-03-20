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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddCommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int post_id = Integer.parseInt(request.getParameter("post_id"));

            String comment_content = request.getParameter("comment_content");
            User user = (User) request.getSession(false).getAttribute("user");

            PostDAO postDao = new PostDAO();

            Comment comment = new Comment();

            //comment was added by author always auto approve
            boolean isApproved = false;
            if (user.getUser_name() == postDao.getPostById(post_id).getAuthor_name()) {
                isApproved = true;
                comment = new Comment(user.getUser_name(),
                        post_id, comment_content,
                        isApproved, 1, true);
            } else {
                isApproved = false;
                comment = new Comment(user.getUser_name(),
                        post_id, comment_content,
                        isApproved, 1, false);
            }

            CommentDAO commentDao = new CommentDAO();

            boolean isSuccess = commentDao.addCommentToPost(comment);
            
            if(isSuccess) {
                response.sendRedirect("home");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
