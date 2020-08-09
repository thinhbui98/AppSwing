/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Thinh Bui
 */

public class DAO {
    
    public Connection conn;
    
    protected DAO() {
        try {
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
