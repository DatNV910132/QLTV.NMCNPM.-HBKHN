/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Duong
 */
public class Book {
    private String ID;
    private String name;
    private String author;
    private String status;
    private int publishingYear;
    private String category;    
    private int number;
    private int deposit;   
    private String note;
    private int price;
    private String publisher;
    private int republish;                                                      //Lần Tái bản                     
    
    public Book(){
        
    }
    
    public Book(String ID, String name, String author, String status, 
                int publishingYear, String category, int number, int deposit, 
                String note, int price, String publisherID, int republish){
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.status = status;
        this.publishingYear = publishingYear;
        this.category = category;
        this.number = number;
        this.deposit = deposit;
        this.note = note;
        this.price = price;
        this.publisher = publisherID;
        this.republish = republish;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
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

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setRepublish(int republish) {
        this.republish = republish;
    }

    public int getRepublish() {
        return republish;
    }   
}
