/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Dom
 */
public class BillDetail {
    private String billID;
    private String bookID;
    private int number;
    private int deposit;
    private Date borrowingDay;
    private Date payDay;

    public BillDetail() {
    }
    
    public BillDetail(String billID, String bookID, int number, int deposit, 
            Date borrowingDay, Date payDay) {
        this.billID = billID;
        this.bookID = bookID;
        this.number = number;
        this.deposit = deposit;
        this.borrowingDay = borrowingDay;
        this.payDay = payDay;
    }
    
    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getDeposit() {
        return deposit;
    }   
    
    public void setBorrowingDay(Date borrowingDay) {
        this.borrowingDay = borrowingDay;
    }

    public Date getBorrowingDay() {
        return borrowingDay;
    }    

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    public Date getPayDay() {
        return payDay;
    }



    
    
    
    
}

