/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
/**
 *
 * @author Thinh Bui
 */
public class DAORequest extends DAO{
    public boolean addRequest(model.Request r) {
        String sql = "INSERT INTO requests(user_id, status) "
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
    
    public boolean addRequestDetail(int book_id, int user_id, int type){
        int status = 3;
        if (type == 1) {
            status = 1;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String insertRequest = "INSERT INTO requests(user_id, status) " + "VALUES(?,?)";
        int request_id = 0;
         try {
            PreparedStatement psinsertRequest = conn.prepareStatement(insertRequest,Statement.RETURN_GENERATED_KEYS);
            psinsertRequest.setInt(1, user_id);
            psinsertRequest.setInt(2, status);
            psinsertRequest.executeUpdate();
            ResultSet rsinsertRequest = psinsertRequest.getGeneratedKeys();
            rsinsertRequest.next();
            request_id = rsinsertRequest.getInt(1);
            if (request_id > 0 ) {
                 String insertRequestDetail = "INSERT INTO request_details(book_id, request_id, status, return_date, start_date) " + "VALUES(?,?,?,?,?)";
                try {
                    PreparedStatement psinsertRequestDetail = conn.prepareStatement(insertRequestDetail);
                    psinsertRequestDetail.setInt(1, book_id);
                    psinsertRequestDetail.setInt(2, request_id);
                    psinsertRequestDetail.setInt(3, 1);
                    psinsertRequestDetail.setString(4, String.valueOf(dtf.format(now)));
                    psinsertRequestDetail.setString(5, String.valueOf(dtf.format(now)));
                    return psinsertRequestDetail.executeUpdate() > 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean acceptRequest(model.Request r) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE requests SET status = 1 WHERE id = " + r.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean declineRequest(model.Request r) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE requests SET status = 2 WHERE id = " + r.getId());
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
    
    public boolean returnRequest(model.Request r) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE requests SET status = 4 WHERE id = " + r.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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
                r.setBookname(rs.getString("bookname"));
                r.setStart_date(rs.getString("start_date"));
                r.setReturn_date(rs.getString("return_date"));
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
