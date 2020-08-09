/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Thinh Bui
 */
public class DAOStudent extends DAO{
    public boolean addStudent(model.Student s) {
        String sql = "INSERT INTO users(username, password, fullname, phone, email, address, class_id, type) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getFullname());
            ps.setString(4, s.getPhone());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getAddress());
            ps.setInt(7, s.getClass_id());
            ps.setInt(8, 3);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteStudent(model.Student s) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = " + s.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateStudent(model.Student s) {
        
        String sql =
                "UPDATE users " +
                "SET username = ?, " +
                "password = ?, " +
                "fullname = ?, " +
                "phone = ?, " +
                "email = ?, " +
                "address = ?, " +
                "class_id = ? " +
                "WHERE id = ?"; 
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getFullname());
            ps.setString(4, s.getPhone());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getAddress());
            ps.setInt(7, s.getClass_id());
            ps.setInt(8, s.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<model.Student> getListSVSearched(String sql) {
        ArrayList<model.Student> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.Student s = new model.Student();
                s.setId(rs.getInt("id"));
                s.setFullname(rs.getString("fullname"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setAddress(rs.getString("address"));
                s.setClass_id(rs.getInt("class_id"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
