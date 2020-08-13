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
    
    public boolean addRequestDetail(int user_id, int book_id){
        String checkPending = "SELECT * FROM requests WHERE deleted = 1 AND status = 4 AND user_id = " + user_id;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            //Kiem tra xem da co request cho chua
            PreparedStatement pscheckPending = conn.prepareStatement(checkPending);
            ResultSet rscheckPending = pscheckPending.executeQuery();
            if (rscheckPending.next() == false) {
                //neu chua co request cho
                String checkBookRequest = "SELECT request_details.*, requests.user_id FROM request_details INNER JOIN requests ON requests.id = request_details.request_id WHERE request_details.book_id = " + book_id + " AND request_details.deleted = 1 AND request_details.status = 1 AND requests.user_id = " + user_id;
                try {
                    PreparedStatement pscheckBookRequest = conn.prepareStatement(checkBookRequest);
                    ResultSet rscheckBookRequest = pscheckBookRequest.executeQuery();
                    if (rscheckBookRequest.next()) {
                        return false;
                    } else { 
                        String insertRequest = "INSERT INTO requests(user_id, status) " + "VALUES(?,?)";
                        int request_id = 0;
                         try {
                            PreparedStatement psinsertRequest = conn.prepareStatement(insertRequest,Statement.RETURN_GENERATED_KEYS);
                            psinsertRequest.setInt(1, user_id);
                            psinsertRequest.setInt(2, 1);
                            psinsertRequest.executeUpdate();
                            ResultSet rsinsertRequest = psinsertRequest.getGeneratedKeys();
                             System.out.println(rsinsertRequest.next());
                            if (rsinsertRequest.next()) {
                                request_id = rsinsertRequest.getInt(1);
                            }
                             System.out.println(request_id);
                            if (request_id > 0 ) {
                                 String insertRequestDetail = "INSERT INTO request_details(book_id, request_id, status, return_date, start_date) " + "VALUES(?,?,?,?)";
                                try {
                                    PreparedStatement psinsertRequestDetail = conn.prepareStatement(insertRequestDetail);
                                    psinsertRequestDetail.setInt(1, book_id);
                                    psinsertRequestDetail.setInt(2, request_id);
                                    psinsertRequestDetail.setInt(3, 1);
                                    psinsertRequestDetail.setString(3, String.valueOf(dtf.format(now)));
                                    psinsertRequestDetail.setString(4, String.valueOf(dtf.format(now)));
                                    return psinsertRequestDetail.executeUpdate() > 0;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //neu da co request cho
                String insertRequest = "INSERT INTO requests(user_id, status) " + "VALUES(?,?)";
                ResultSet result;
                try {
                   PreparedStatement psinsertRequest = conn.prepareStatement(insertRequest, Statement.RETURN_GENERATED_KEYS);
                   psinsertRequest.setInt(1, user_id);
                   psinsertRequest.setInt(2, 1);
                   result = psinsertRequest.getGeneratedKeys();
                   
                   if ( psinsertRequest.executeUpdate() > 0) {
                        String insertRequestDetail = "INSERT INTO request_details(book_id, request_id, status, return_date, start_date) " + "VALUES(?,?,?,?,?)";
                       try {
                           int id = result.getInt(1);
                           PreparedStatement psinsertRequestDetail = conn.prepareStatement(insertRequestDetail);
                           psinsertRequestDetail.setInt(1, book_id);
                           psinsertRequestDetail.setInt(2, id);
                           psinsertRequestDetail.setInt(3, 1);
                           psinsertRequestDetail.setString(3, String.valueOf(dtf.format(now)));
                           psinsertRequestDetail.setString(4, String.valueOf(dtf.format(now)));
                           return psinsertRequestDetail.executeUpdate() > 0;
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
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
