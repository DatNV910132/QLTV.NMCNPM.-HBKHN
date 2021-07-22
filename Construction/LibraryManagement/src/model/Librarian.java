/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Duong
 */
public class Librarian extends Reader{
    private int salary;
    private String password;
    private String username;
    private int role;

    public Librarian(){
        
    }
    
    public Librarian(String ID, String name, String gender, Date birth, 
                     String address, String phone, String email, int salary, 
                     String username, String password, int role){
        super(ID, name, gender, birth, address, phone, email);
        this.salary = salary;
        this.password = password;
        this.username = username;
        this.role = role;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }   

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }   
}
