/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import db.DBContext;
import entities.Post;
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
public class PostDAO {
    public Post getPostById (int id) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [Post] where post_id = ?";
        
        Post post = new Post();
        
        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                String author_name = rs.getString("author_name");
                Date date_time = rs.getDate("date_time");
                String content = rs.getString("content");
                
                post = new Post(id, author_name, date_time, content);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        
        return post;
    }
    
    //get all post
    public ArrayList<Post> getAllPost() throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [Post]";
        
        ArrayList<Post> posts = new ArrayList<>();
        
        try {
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("post_id");
                String author_name = rs.getString("author_name");
                Date date_time = rs.getDate("date_time");
                String content = rs.getString("content");
                
                Post post = new Post(id, author_name, date_time, content);
                posts.add(post);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(conn, ps, rs);
        }
        
        return posts;
    }
    
    public static void main(String[] args) throws SQLException {
        PostDAO dao = new PostDAO();
//        Post post = dao.getPostById(1);
        ArrayList<Post> posts = dao.getAllPost();
        
        for (Post post : posts) {
            System.out.println(post.getAuthor_name() + " " 
            + post.getContent() + " " 
            + post.getDateFormat());
            System.out.println("\n");
        }
//        System.out.println(post.getAuthor_name() + " " 
//            + post.getContent() + " " 
//            + post.getDateFormat());
    }
}
