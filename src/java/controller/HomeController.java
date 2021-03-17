/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Comment;
import entities.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class HomeController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PostDAO postDao = new PostDAO();
            ArrayList<Post> posts = postDao.getAllPost();
            
            CommentDAO cmtDao = new CommentDAO();
            request.setAttribute("posts", posts);
            for (Post post : posts) {
                post.setComments(cmtDao.getAllCommentByPostId(post.getPost_id()));
                post.setNumberOfComment(cmtDao.getNumberOfCommentInPost(post.getPost_id()));
            }
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    
}
