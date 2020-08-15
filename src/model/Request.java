/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Thinh Bui
 */
public class Request {
    private String username, bookname,start_date, return_date;
    int id, user_id, status;

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
