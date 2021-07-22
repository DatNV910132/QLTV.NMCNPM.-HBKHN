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
import model.Bill;

/**
 * Class này kế thừa lớp connect, thực hiện việc thao tác với bảng phiếu mượn trong CSDL
 * @author maidoanh
 */
public class ConnectBill extends Connect{
    /**
     * Lấy toàn bộ phiếu mượn trong CSDL
     * @return đối tương ResultSet chứa toàn bộ dữ liệu của bảng phiếu mượn
     */
    public ResultSet getAllBills(){
        ResultSet result = null;
        String sqlCommand = "select * from " + tableBill;
        Statement st ;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataBill Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBill.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        
        return result;
    }
    /**
     * Thêm một phiếu mượn vào trong CSDL
     * @param bill phiếu mượn cần thêm vào CSDL
     */
    public void insertBill(Bill bill){
        String sqlCommand = "insert into "+ tableBill +" value(? ,? ,? ,? ,?)";
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, bill.getID());
            pst.setString(2, bill.getReaderID());
            pst.setString(3, bill.getLibrarianID());
            pst.setString(4, bill.getStatus());
            pst.setInt(5, bill.getTotalMoney());
            
            int row = pst.executeUpdate();
            if(row>0){
                System.out.println("Inserted to DataBase");
            }else{
                System.out.println("Insert Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Mã pm Đã Tồn Tại", "Thông Báo", WARNING_MESSAGE);
        }
    }
    /**
     * Xóa một phiếu mượn khỏi CSDL
     * @param id mã phiếu mượn cần xóa khỏi CSDL
     */
    public void deleteBill(String id){
        String sqlCommand = "delete from "+ tableBill + " where MaPhieu = ?";
        PreparedStatement  pst = null;
        try {
            pst= connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBill.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete ERROR");
        }
    }
    public ResultSet ACBorowPay(String MaDG) {
        String sql = "SELECT phieumuon.MaPhieu, phieumuon.MaDG, chitietmuontra.MaSach,"
                + " chitietmuontra.SoLuong, chitietmuontra.NgayMuon, chitietmuontra.HanTra,"
                + " chitietmuontra.TienDatCoc FROM qltv.chitietmuontra, qltv.phieumuon"
                + " where chitietmuontra.MaPhieu = phieumuon.MaPhieu && phieumuon.MaDG = '" + MaDG +"'";
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println(" PayBorow Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    
    /**
     * sửa thông tin một phiếu mượn trong CSDL
     * @param ID mã phiếu mượn cần sửa thông tin
     * @param bill đối tượng chứa thông tin được sửa
     */
    public void updateBill(String ID, Bill bill) {
        String sqlCommand = "update " + tableBill + " set MaDG='" + bill.getReaderID()+ "',"
                + " MaNV='" + bill.getLibrarianID()+ "', TinhTrang='" + bill.getStatus()+ "', TongTienDatCoc=" 
                + bill.getTotalMoney()+" where MaPhieu='" + ID + "'";
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            st.execute(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
