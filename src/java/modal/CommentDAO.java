/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import db.DBContext;
import entities.Comment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ThaiDuongNg
 */
public class CommentDAO {

    //get all comment of post
    public ArrayList<Comment> getAllCommentByPostId(int post_id) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Comment where post_id = ?";

        ArrayList<Comment> comments = new ArrayList<>();

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, post_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int comment_id = rs.getInt("comment_id");
                String user_name = rs.getString("user_name");
                String content = rs.getString("comment_content");
                Date date_time = rs.getDate("date_time");
                boolean isApproved = rs.getBoolean("isApproved");
                int reject_reason_id = rs.getInt("reject_reason_id");
                boolean status_alert = rs.getBoolean("status_alert");

                Comment comment = new Comment(comment_id, user_name, post_id,
                        content, date_time, isApproved, reject_reason_id, status_alert);
                comments.add(comment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return comments;
    }

    //get all comment by post has been writen by post_author
    public ArrayList<Comment> getAllCommentToManagebyPostAuthor(String post_author) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Comment, Post\n"
                + "where Comment.post_id = Post.post_id and Post.author_name = ?";

        ArrayList<Comment> comments = new ArrayList<>();

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, post_author);
            rs = ps.executeQuery();

            while (rs.next()) {
                int comment_id = rs.getInt("comment_id");
                String user_name = rs.getString("user_name");
                int post_id = rs.getInt("post_id");
                String content = rs.getString("comment_content");
                Date date_time = rs.getDate("date_time");
                boolean isApproved = rs.getBoolean("isApproved");
                int reject_reason_id = rs.getInt("reject_reason_id");
                boolean status_alert = rs.getBoolean("status_alert");

                Comment comment = new Comment(comment_id, user_name, post_id,
                        content, date_time, isApproved, reject_reason_id, status_alert);
                comments.add(comment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return comments;
    }

    //add comment to post=====done
    public boolean addCommentToPost(Comment comment) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([user_name]\n"
                + "           ,[post_id]\n"
                + "           ,[comment_content]\n"
                + "           ,[date_time]\n"
                + "           ,[isApproved]\n"
                + "           ,[reject_reason_id]\n"
                + "           ,[status_alert])\n"
                + "     VALUES(?, ? , ?, ?, ?, ?, ?)";

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, comment.getUser_name());
            ps.setInt(2, comment.getPost_id());
            ps.setString(3, comment.getComment_content());
            ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setBoolean(5, comment.isIsApproved());
            ps.setInt(6, comment.getReject_reason_id());
            ps.setBoolean(7, comment.isStatus_alert());

            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return false;
    }

    //aprrove comment
    public boolean approveCommnent(int commnent_id) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "UPDATE [dbo].[Comment] "
                + "SET [isApproved] = 1, "
                + "[status_alert] = 1 "
                + "WHERE [comment_id] = ?";

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, commnent_id);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return false;
    }

    //reject comment by reason
    public boolean rejectCommentwithReason(int reason_id, int commnent_id) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "UPDATE [dbo].[Comment] "
                + "SET [isApproved] = 0, "
                + "[status_alert] = 1,"
                + "[reject_reason_id] = ? "
                + "WHERE [comment_id] = ?";

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            if (reason_id == 1) {
                return false;
            }
            ps.setInt(1, reason_id);
            ps.setInt(2, commnent_id);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return false;
    }

    //switch alert status
    //count commnet of post
    public int getNumberOfCommentInPost(int postId) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select count(comment_id) as number from Comment "
                + "where Comment.post_id = ? and [isApproved] = 1";

        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int number = rs.getInt("number");
                return number;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        return 0;
    }

    public static void main(String[] args) throws SQLException {
        CommentDAO dao = new CommentDAO();

        ArrayList<Comment> al = dao.getAllCommentToManagebyPostAuthor("user1");
        for (Comment comment : al) {
            System.out.println(comment.getComment_content() + '\n');
        }
    }
}
