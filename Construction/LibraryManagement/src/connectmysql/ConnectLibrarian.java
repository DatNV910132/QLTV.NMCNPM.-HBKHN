/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectmysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Librarian;

/**
 * Class này kế thừa lớp connect, thực hiện việc thao tác với bảng nhanvien trong CSDL
 * @author maidoanh
 */
public class ConnectLibrarian extends Connect {

    /**
     * Lấy toàn bộ nhân viên trong CSDL
     * @return đối tương ResultSet chứa toàn bộ dữ liệu của bảng nhân viên
     */
    public ResultSet getAllLibrarians() {
        ResultSet result = null;
        String sqlCommand = "select * from " + tableLibrarian;
        Statement st;
        try {
            st = (Statement) connection.createStatement();
            result = st.executeQuery(sqlCommand);
            System.out.println("GetDataLibrarian Correct");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Select ERROR \n" + ex.toString());
        }

        return result;
    }

    /**
     * Thêm một nhân viên vào trong CSDL
     * @param librarian nhân viên cần thêm vào CSDL
     */
    public void insertLibrarian(Librarian librarian) {
        String sqlCommand = "insert into " + tableLibrarian + " value(? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, librarian.getID());
            pst.setString(2, librarian.getName());
            pst.setDate(3, librarian.getBirth());
            pst.setString(4, librarian.getGender());
            pst.setString(5, librarian.getAddress());
            pst.setString(6, librarian.getPhone());
            pst.setString(7, librarian.getEmail());
            pst.setInt(8, librarian.getSalary());
            pst.setString(9, librarian.getUsername());
            pst.setString(10, librarian.getPassword());

            int row = pst.executeUpdate();
            if (row > 0) {
                System.out.println("Inserted to DataBase");
            } else {
                System.out.println("Insert Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Xóa một nhân viên khỏi CSDL
     * @param id nhân viên cần xóa
     */
    public void deleteLibrarian(String id) {
        String sqlCommand = "delete from " + tableLibrarian + " where MaNV = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Delete ERROR");
        }
    }
    
    /**
     * sửa thông tin một nhân viên trong CSDL
     * @param MaNV mã của nhân viên cần sửa thông tin
     * @param lib đối tượng chứa thông tin được sửa
     */
    public void updateLibrarian(String MaNV, Librarian lib) {
        String sqlCommand = "update " + tableLibrarian + " set TenNV='" + lib.getName() + "',"
                + " Ngaysinh='" + lib.getBirth() + "', Diachi='" + lib.getAddress() + "', Email='" + lib.getEmail() + "',"
                + " Phone='" + lib.getPhone() + "', Password='" + lib.getPassword() + "', Gioitinh='" + lib.getGender() + 
                "', Username='" + lib.getUsername() + "' , Luong='" + lib.getSalary() + "'where MaNV='" + MaNV + "'";
        com.mysql.jdbc.Statement st;
        try {
            st = (com.mysql.jdbc.Statement) connection.createStatement();
            st.execute(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Phân nhóm và đếm số nhân viên trong CSDL theo một trường nhất định
     * @param countid tên trường dùng để phân nhóm các nhân viên
     * @return đối tượng ResultSet chứa số lượng nhân viên theo từng nhóm
     */
    public ResultSet CountLibrarian(String countid) {
        String sql = "select " + countid + ", count(MaNV) from thuthu group by " + countid;
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
     * Phân nhóm và đếm số nhân viên trong CSDL dựa theo năm sinh
     * @return đối tượng ResultSet chứa các năm sinh và số nhân viên sinh trong mỗi năm đó
     */
    public ResultSet CountYear() {
        String sql = "select year(Ngaysinh),count(YEAR(Ngaysinh)) from thuthu group by YEAR(Ngaysinh);";
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
}
