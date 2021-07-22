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
import model.BillDetail;

/**
 * Class này kế thừa lớp connect, thực hiện việc thao tác với bảng chi tiết phiếu mượn trong CSDL
 * @author maidoanh
 */
public class ConnectBillDetail extends Connect{
    
    /**
     * Lấy toàn bộ chi tiết phiếu mượn trong CSDL
     * @return đối tương ResultSet chứa toàn bộ dữ liệu của bảng chi tiết phiếu mượn
     */
    public ResultSet getAllBillDetail(){
        ResultSet result = null;
        String sqlCommand = "select * from " + tableBillDetail;
        Statement st ;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataBillDetail Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBillDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        
        return result;
    }
    /**
     * Thêm một chi tiết phiếu mượn vào trong CSDL
     * @param billdetail chi tiết phiếu mượn cần thêm vào CSDL
     */
    public void insertBillDetail(BillDetail billdetail){
        String sqlCommand = "insert into "+ tableBillDetail +" value(? ,? ,? ,? ,?, ?)";
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, billdetail.getBillID());
            pst.setString(2, billdetail.getBookID());
            pst.setInt(3, billdetail.getNumber());
            pst.setInt(4, billdetail.getDeposit());
            pst.setDate(5, billdetail.getBorrowingDay());
            pst.setDate(6, billdetail.getPayDay());
            
            int row = pst.executeUpdate();
            if(row>0){
                System.out.println("Inserted to DataBase");
            }else{
                System.out.println("Insert Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Mã ctpm Đã Tồn Tại", "Thông Báo", WARNING_MESSAGE);
        }
    }
    
    /**
     * Xóa một chi tiết phiếu mượn khỏi CSDL
     * @param billID mã phiếu mượn của chi tiết phiếu mượn cần xóa
     * @param bookID mã sách của chi tiết phiếu mượn cần xóa
     */
    public void deleteBillDetail(String billID, String bookID){
        String sqlCommand = "delete from "+ tableBillDetail + " where MaPhieu = ? and MaSach = ?";
        PreparedStatement  pst = null;
        try {
            pst= connection.prepareStatement(sqlCommand);
            pst.setString(1, billID);
            pst.setString(2, bookID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBillDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete ERROR");
        }
    }
    
    /**
     * sửa thông tin một chi tiết phiếu mượn trong CSDL
     * @param billDetail đối tượng chứa thông tin được sửa
     */
    public void updateBillDetail(BillDetail billDetail) {
         String sqlCommand = "update " + tableBillDetail + " set SoLuong=" + billDetail.getNumber()+ ", TienDatCoc=" + billDetail.getDeposit()+ ", NgayMuon='" 
                + billDetail.getBorrowingDay()+"', HanTra = '"+billDetail.getPayDay()
                 +"' where MaPhieu='" + billDetail.getBillID() + "' and MaSach = '"+billDetail.getBookID()+"';";
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            st.execute(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lấy tất cả các chi tiết phiếu mượn của một phiếu mượn
     * @param ID mã phiếu mượn
     * @return đối tượng ResultSet chứa dữ liệu các chi tiết phiếu mượn có 
     * mã phiếu mượn là ID
     */
    public ResultSet getBillDetailByBillID(String ID){
        ResultSet result = null;
        String sqlCommand = "select * from " + tableBillDetail + " where MaPhieu='"+ID+"';";
        Statement st ;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataBillDetail Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBillDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        
        return result;
    }
    
    /**
     * xóa tất cả các chi tiết phiếu mượn của một phiếu mượn
     * @param billID mã phiếu mượn
     */
    public void DeleteBillDetailByBillID(String billID){
        String sqlCommand = "delete from "+ tableBillDetail + " where MaPhieu = ?";
        PreparedStatement  pst = null;
        try {
            pst= connection.prepareStatement(sqlCommand);
            pst.setString(1, billID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBillDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete ERROR");
        }
    }
}
