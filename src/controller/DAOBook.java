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
public class DAOBook extends DAO{
    public boolean addBook(model.Book b) {
        String sql = "INSERT INTO books(bookname, category_id, description, quantity) "
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getBookname());
            ps.setString(2, String.valueOf(b.getCategory_id()));
            ps.setString(3, b.getDescription());
            ps.setInt(4, b.getQuantity());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteBook(model.Book b) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id = " + b.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateBook(model.Book b) {
        
        String sql =
                "UPDATE books " +
                "SET bookname = ?, " +
                "category_id = ?, " +
                "description = ?, " +
                "quantity = ? " +
                "WHERE id = ?"; 
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getBookname());
            ps.setString(2, String.valueOf(b.getCategory_id()));
            ps.setString(3, b.getDescription());
            ps.setInt(4, b.getQuantity());
            ps.setInt(5, b.getId());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<model.Book> getListBookSearched(String sql) {
        ArrayList<model.Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.Book b = new model.Book();
                b.setId(rs.getInt("id"));
                b.setBookname(rs.getString("bookname"));
                b.setCategory_id(Integer.parseInt(rs.getString("category_id")));
                b.setDescription(rs.getString("description"));
                b.setQuantity(Integer.parseInt(rs.getString("quantity")));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
