/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectmysql;

import static connectmysql.Connect.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Book;

/**
 * Class này kế thừa lớp connect, thực hiện việc thao tác với bảng sách trong CSDL
 * @author maidoanh
 */
public class ConnectBook extends Connect{
    
    /**
     * Lấy toàn bộ sách trong CSDL
     * @return đối tương ResultSet chứa toàn bộ dữ liệu của bảng sách
     */
    public ResultSet getAllBooks(){
        ResultSet result = null;
        String sqlCommand = "select * from " + tableBook;
        Statement st ;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataBook Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBook.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        
        return result;
    }
    /**
     * Thêm một sách vào trong CSDL
     * @param book sách cần thêm vào CSDL
     */
    public void insertBook(Book book){
        String sqlCommand = "insert into "+ tableBook +" value(? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, book.getID());
            pst.setString(2, book.getName());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getStatus());
            pst.setInt(5, book.getPublishingYear());
            pst.setInt(6, book.getRepublish());
            pst.setString(7, book.getCategory());
            pst.setInt(8, book.getNumber());
            pst.setInt(9, book.getDeposit());
            pst.setString(10, book.getNote());
            pst.setInt(11, book.getPrice());
            pst.setString(12, book.getPublisher());
            
            int row = pst.executeUpdate();
            if(row>0){
                System.out.println("Inserted to DataBase");
            }else{
                System.out.println("Insert Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Mã Sach Đã Tồn Tại", "Thông Báo", WARNING_MESSAGE);
        }
    }
    
    /**
     * Xóa một sách khỏi CSDL
     * @param id mã của sách cần xóa
     */
    public void deleteBook(String id){
        String sqlCommand = "delete from "+ tableBook + " where MaSach = ?";
        PreparedStatement  pst = null;
        try {
            pst= connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Mã Sach Đã Tồn Tại", "Thông Báo", WARNING_MESSAGE);
        }
    }
    //tk
    public ResultSet CountBook(String countid) {
        String sql = "select " + countid + ", count(MaSach) from sach group by " + countid;
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println("CountBook Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBook.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    //Top những sách được mượn nhiều nhất
    public ResultSet getTopBooks(int soLuongSach) {
        String sql = "select sach.MaSach, sach.TenSach, sach.TacGia,"
                + " sach.TheLoai, sach.NXB,count(sach.MaSach) FROM qltv.chitietmuontra,"
                + "qltv.sach where chitietmuontra.MaSach = sach.MaSach"
                + " group by sach.MaSach order by count(sach.MaSach) desc limit " + soLuongSach;
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();System.out.println(sql);
            result = st.executeQuery(sql);
            System.out.println("top Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    
    /**
     * sửa thông tin sách trong CSDL
     * @param ID mã của sách cần sửa
     * @param book đối tượng chứa thông tin được sửa
     */
    public void updateBook(String ID, Book book) {
        String sqlCommand = "update " + tableBook + " set TenSach='" + book.getName() + "',"
                + " TacGia='" + book.getAuthor()+ "', TinhTrang='" + book.getStatus()+ "', NamXB=" + book.getPublishingYear()+ ","
                + " TaiBan=" + book.getRepublish()+ ", TheLoai='" + book.getCategory()+ "', SoLuong=" + book.getNumber()+ 
                ", TienDatCoc=" + book.getDeposit()+ ", GhiChu='" + book.getNote()+ "', Gia = "+book.getPrice()+", NXB = '"+book.getPublisher()+"' where MaSach='" + ID + "'";
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            st.execute(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
