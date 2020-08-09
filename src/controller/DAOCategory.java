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
public class DAOCategory extends DAO{
    public boolean addCategory(model.Category c) {
        String sql = "INSERT INTO categories(categoryname, description) "
                + "VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCategoryname());
            ps.setString(2, c.getDescription());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCategory(model.Category c) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM categories WHERE id = " + c.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCategory(model.Category c) {
        
        String sql =
                "UPDATE categories " +
                "SET categoryname = ?, " +
                "description = ? " +
                "WHERE id = ?"; 
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCategoryname());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getId());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<model.Category> getListCategorySearched(String sql) {
        ArrayList<model.Category> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.Category c = new model.Category();
                c.setId(rs.getInt("id"));
                c.setCategoryname(rs.getString("categoryname"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
