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
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            User user = (User) request.getSession().getAttribute("user");
            
            String user_name = user.getUser_name();

            PostDAO postDao = new PostDAO();

            Comment comment = new Comment();

            //comment was added by author always auto approve
            if (user.getUser_name().equals(postDao.getPostById(post_id).getAuthor_name())) {
                comment = new Comment(user.getUser_name(),
                        post_id, comment_content,
                        true, 1, true);
            } else {
                comment = new Comment(user.getUser_name(),
                        post_id, comment_content,
                        false, 1, false);
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
