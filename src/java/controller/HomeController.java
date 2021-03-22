/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Comment;
import entities.Post;
import entities.Reply;
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
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            PostDAO postDao = new PostDAO();
            ArrayList<Post> posts = postDao.getAllPost();

            CommentDAO cmtDao = new CommentDAO();
            
            for (Post post : posts) {
//                //filter list comment have been approved
//                List<Comment> comments
//                        = cmtDao.getAllCommentByPostId(post.getPost_id())
//                                .stream()
//                                .filter(cmt -> cmt.isIsApproved() == true)
//                                .collect(Collectors.toList());

                post.setComments(cmtDao.getAllCommentByPostId(post.getPost_id()));
                post.setNumberOfComment(cmtDao.getNumberOfCommentInPost(post.getPost_id()));
            }
            
            
            
            ArrayList<Reply> replies = cmtDao.getAllReplies();
            
            
            request.setAttribute("posts", posts);
//            
//            request.setAttribute("rep", replies.get(0));
            request.setAttribute("replies", replies);
            request.setAttribute("home", "active");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

//d65327
