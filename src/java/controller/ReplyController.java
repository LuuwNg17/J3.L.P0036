/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Comment;
import entities.Post;
import entities.Reply;
import entities.User;
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
public class ReplyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //comment id
            int id = Integer.parseInt(request.getParameter("id"));

            CommentDAO cmtDao = new CommentDAO();
            
            Comment comment = cmtDao.getCommentByID(id);
            
            ArrayList<Reply> replies = cmtDao.getAllReplies();
            
//            PostDAO postDao = new PostDAO();
//            Post post = postDao.getPostById(id);

            request.setAttribute("comment", comment);
            request.setAttribute("replies", replies);
            request.setAttribute("id", id);

            request.getRequestDispatcher("reply.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ReplyController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String cmt_content = request.getParameter("cmt_content");
            int id = Integer.parseInt(request.getParameter("id"));

            User user = (User) request.getSession(false).getAttribute("user");

            
            CommentDAO dao = new CommentDAO();
            
            Reply reply = new Reply();
            reply.setAuthor_name(user.getUser_name());
            reply.setComment_id(id);
            reply.setReply_content(cmt_content);
            
            dao.addReplyComment(reply);

//            ArrayList<Reply> replies = dao.getAllRepliesByCommentID(id);
            
//            Comment comment = new Comment(user.getUser_name(),
//                    id, cmt_content,
//                    true, 1, true);

//            dao.addCommentToPost(comment);
            response.sendRedirect("home");
        } catch (SQLException ex) {
            Logger.getLogger(ReplyController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
