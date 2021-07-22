/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectmysql;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Reader;
/**
 * Class này kế thừa lớp connect, thực hiện việc thao tác với bảng docgia trong CSDL
 * @author maidoanh
 */
public class ConnectReader extends Connect{
    /**
     * Lấy toàn bộ độc giả trong CSDL
     * @return đối tương ResultSet chứa toàn bộ dữ liệu của bảng độc giả
     */
    public ResultSet getAllReaders(){
        ResultSet result = null;
        String sqlCommand = "select * from " + tableReader;
        Statement st ;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataReader Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        
        return result;
    }
    /**
     * Thêm một độc giả vào trong CSDL
     * @param user độc giả cần thêm vào CSDL
     */
    public void insertReader(Reader user){
        String sqlCommand = "insert into "+ tableReader +" value(? ,? ,? ,? ,? ,? ,?)";
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, user.getID());
            pst.setString(2, user.getName());
            pst.setDate(3, user.getBirth());
            pst.setString(4, user.getGender());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getPhone());
            pst.setString(7, user.getEmail());
            
            int row = pst.executeUpdate();
            if(row>0){
                System.out.println("Inserted to DataBase");
            }else{
                System.out.println("Insert Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Mã Độc Giả Đã Tồn Tại", "Thông Báo", WARNING_MESSAGE);
        }
    }
    
    /**
     * Xóa một độc giả khỏi CSDL
     * @param id mã của độc giả cần xóa
     */
    public void deleteReader(String id){
        String sqlCommand = "delete from "+ tableReader + " where MaDG = ?";
        PreparedStatement  pst = null;
        try {
            pst= connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete ERROR");
        }
    }
    
    /**
     * Phân nhóm và đếm số độc giả trong CSDL theo một trường nhất định
     * @param countid tên trường dùng để phân nhóm các độc giả
     * @return đối tượng ResultSet chứa số lượng độc giả theo từng nhóm
     */
    public ResultSet countReader(String countid) {
        String sql = "select " + countid + ", count(MaDG) from docgia group by " + countid;
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println("CountNV Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }

    /**
     * Phân nhóm và đếm số độc giả trong CSDL dựa theo năm sinh
     * @return đối tượng ResultSet chứa các năm sinh và số độc giả sinh trong mỗi năm đó
     */
    public ResultSet countYear() {
        String sql = "select year(Ngaysinh),count(YEAR(Ngaysinh)) from docgia group by YEAR(Ngaysinh);";
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println("nam Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    public ResultSet statisticBillDetail() {
        String sql = "SELECT MaDG, TenDocGia, count(MaSach), sum(TienDatCoc) "
                + "FROM qltv.phieumuon natural join qltv.docgia natural join qltv.chitietmuontra";
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println("nam Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    
    /**
     * Lấy thông tin các độc giả đã quá hạn phải trả sách
     * @return đối tượng ResultSet chứa thông tin các độc giả đã quá hạn trả sách
     */
    public ResultSet getOverdueReader(){
        String sql = "select distinct docgia.* FROM qltv.chitietmuontra,qltv.phieumuon,"
                + "qltv.docgia where chitietmuontra.MaPhieu = phieumuon.MaPhieu "
                + "&& phieumuon.MaDG = docgia.MaDG && chitietmuontra.HanTra > curdate();";
        ResultSet result = null;
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            result = st.executeQuery(sql);
            System.out.println("nam Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }
        return result;
    }
    
    /**
     * sửa thông tin một độc giả trong CSDL
     * @param MaDG mã của độc giả cần sửa thông tin
     * @param reader đối tượng chứa thông tin được sửa
     */
    public void updateReader(String MaDG, Reader reader) {
        String sqlCommand = "update " + tableReader + " set TenDocGia='" + reader.getName() + "',"
                + " Ngaysinh='" + reader.getBirth() + "', Diachi='" + reader.getAddress() + "', Email='" + reader.getEmail() + "',"
                + " Phone='" + reader.getPhone() + "', Gioitinh='" + reader.getGender() + 
                 "'where MaDG='" + MaDG + "'";
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            st.execute(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
