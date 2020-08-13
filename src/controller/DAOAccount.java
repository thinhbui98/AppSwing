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
public class DAOAccount extends DAO{
    public boolean Login(model.Account a) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setId(rs.getInt("id"));
                a.setFullname(rs.getString("fullname"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setPhone(rs.getString("phone"));
                a.setAddress(rs.getString("address"));
                a.setType(rs.getInt("type"));
                a.setClass_id(rs.getInt("class_id"));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Register(model.Account a) {
        String sql = "SELECT * FROM users WHERE username = ? AND email = ? ";
        System.out.println(a.getUsername());
        System.out.println(a.getEmail());
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getEmail());
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.next());
            if(rs.next() == false){
                String sqlins = "INSERT INTO users(username, password, email, type) "
                        + "VALUES(?,?,?,?)";
                try {
                    PreparedStatement psins = conn.prepareStatement(sqlins);
                    System.out.println(psins);
                    psins.setString(1, a.getUsername());
                    psins.setString(2, a.getPassword());
                    psins.setString(3, a.getEmail());
                    psins.setInt(4,3);
                    psins.executeUpdate();
                    String findUser = "SELECT * FROM users WHERE username = ? OR email = ? ";
                    try {
                        PreparedStatement psfindUser = conn.prepareStatement(findUser);
                        psfindUser.setString(1, a.getUsername());
                        psfindUser.setString(2, a.getPassword());
                        ResultSet rsfindUser = psfindUser.executeQuery();
                        if (rsfindUser.next()) {
                            a.setId(rsfindUser.getInt("id"));
                            a.setUsername(rsfindUser.getString("username"));
                            a.setPassword(rsfindUser.getString("password"));
                            a.setEmail(rsfindUser.getString("email"));
                            a.setType(3);
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
//        String sql = "INSERT INTO users(username, password, email) "
//                + "VALUES(?,?,?)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, a.getUsername());
//            ps.setString(2, a.getPassword());
//            ps.setString(5, a.getEmail());
//            
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
    }
    
    public boolean updateAccount(model.Account a) {
        
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
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFullname());
            ps.setString(4, a.getPhone());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getAddress());
            ps.setInt(7, a.getClass_id());
            ps.setInt(8, a.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<model.Account> getAccount(String sql) {
        ArrayList<model.Account> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.Account a = new model.Account();
                System.out.println(rs.getInt("id"));
                a.setId(rs.getInt("id"));
                a.setFullname(rs.getString("fullname"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setPhone(rs.getString("phone"));
                a.setAddress(rs.getString("address"));
                a.setClass_id(rs.getInt("class_id"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
