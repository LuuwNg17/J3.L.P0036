/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import db.DBContext;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiDuongNg
 */
public class UserDAO {
    public User getUserByUsername(String user_name) throws SQLException {
        DBContext db = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from [User] where user_name = ? ";
        User user = new User();
        try{
            db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                String password = rs.getString("password");
                user = new User(user_name, password);
                
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            db.closeConnection(conn, ps, rs);
        }
        return user;
    }
    
    
    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByUsername("user1");
         System.out.println(user.getPassword());
    }
}
