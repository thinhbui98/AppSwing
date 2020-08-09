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
public class DAORequest extends DAO{
    public boolean addRequest(model.Request r) {
        String sql = "INSERT INTO request(user_id, status) "
                + "VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, r.getUser_id());
            ps.setInt(2, r.getStatus());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean acceptRequest(model.Request r) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE request SET status = 1 WHERE id = " + r.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteRequest(model.Request r) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM request WHERE id = " + r.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//    
//    public boolean updateStudent(model.Student s) {
//        
//        String sql =
//                "UPDATE users " +
//                "SET username = ?, " +
//                "password = ?, " +
//                "fullname = ?, " +
//                "phone = ?, " +
//                "email = ?, " +
//                "address = ?, " +
//                "class_id = ? " +
//                "WHERE id = ?"; 
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, s.getUsername());
//            ps.setString(2, s.getPassword());
//            ps.setString(3, s.getFullname());
//            ps.setString(4, s.getPhone());
//            ps.setString(5, s.getEmail());
//            ps.setString(6, s.getAddress());
//            ps.setInt(7, s.getClass_id());
//            ps.setInt(8, s.getId());
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    
    public ArrayList<model.Request> getListRequestSearched(String sql) {
        ArrayList<model.Request> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.Request r = new model.Request();
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setStatus(rs.getInt("status"));
                r.setUsername(rs.getString("username"));
//                rd.setRequest_id(rs.getInt("request_id"));
//                rd.setBook_id(rs.getInt("book_id"));
//                rd.setStart_date(rs.getString("start_date"));
//                rd.setReturn_date(rs.getString("return_date"));
//                rd.setQuantity(rs.getInt("quantity"));
              list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<model.RequestDetail> getListRequestDetailSearched(String sql) {
        ArrayList<model.RequestDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.RequestDetail rd = new model.RequestDetail();
                rd.setRequest_id(rs.getInt("request_id"));
                rd.setBook_id(rs.getInt("book_id"));
                rd.setStart_date(rs.getString("start_date"));
                rd.setReturn_date(rs.getString("return_date"));
                rd.setQuantity(rs.getInt("quantity"));
              list.add(rd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
