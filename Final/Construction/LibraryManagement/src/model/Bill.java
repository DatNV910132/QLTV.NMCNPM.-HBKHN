/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dom
 */
public class Bill {
    private String ID;
    private String readerID;
    private String librarianID;
    private int totalMoney;
    private String status;    

    public Bill(String ID, String readerID, String librarianID, int totalMoney, String status) {
        this.ID = ID;
        this.readerID = readerID;
        this.librarianID = librarianID;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public Bill() {
        
    }
    
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(String librarianID) {
        this.librarianID = librarianID;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
