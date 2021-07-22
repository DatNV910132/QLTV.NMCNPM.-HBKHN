/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connectmysql.Connect;
import connectmysql.ConnectLibrarian;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Librarian;
import view.LibrarianDataForm;
import view.MainView;

/**
 * Trao đổi dữ liệu giữa bảng nhân viên trên giao diện và đối tượng ConnectLibrarian
 *
 * @author maidoanh
 */
public class LibrarianController {
    
    /**
     * Lấy dữ liệu của các nhân viên và hiển thị lên bảng
     *
     * @param rs đối tượng ResultSet chứa dữ liệu của các nhân viên
     * @param table bảng hiển thị thông tin các nhân viên
     */
    public void Loaddatatotable(ResultSet rs, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            while (rs.next()) {
                Object row[] = new Object[10];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getInt(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loadata function ERROR");
        }
    }

    /**
     * Thêm một nhân viên vào trong cơ sở dữ liệu và hiển thị nhân viên trên bảng
     * @param lib nhân viên cần thêm vào cơ sở dữ liệu và bảng hiển thị
     * @param table bảng hiện thị danh sách các nhân viên
     */
    public void addlib(Librarian lib, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        ConnectLibrarian ConnectLib = new ConnectLibrarian();
        ConnectLib.doConnect();
        ConnectLib.insertLibrarian(lib);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ResultSet rs = ConnectLib.getAllLibrarians();
        try {
            while (rs.next()) {
                Object row[] = new Object[10];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getInt(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Addlib function ERROR ");
        }
    }

    /**
     * Lấy một đối tượng nhân viên từ bảng hiển thị danh sách các nhân viên
     * @param Table bảng chứa danh sách các nhân viên
     * @param row hàng được chọn trong bảng
     * @return nhân viên được chọn
     */
    public Librarian getLibrarianFromTable(JTable Table, int row) {
        Librarian lib = new Librarian();
        lib.setID((String) Table.getModel().getValueAt(row, 0));
        lib.setName((String) Table.getModel().getValueAt(row, 1));
        lib.setBirth(Date.valueOf(String.valueOf(Table.getModel().getValueAt(row, 2))));
        lib.setGender((String) Table.getModel().getValueAt(row, 3));
        lib.setAddress((String) Table.getModel().getValueAt(row, 4));
        lib.setPhone((String) Table.getModel().getValueAt(row, 5));
        lib.setEmail((String) Table.getModel().getValueAt(row, 6));
        lib.setSalary(Integer.parseInt(String.valueOf(Table.getModel().getValueAt(row, 7))));
        lib.setUsername((String) Table.getModel().getValueAt(row, 8));
        lib.setPassword((String) Table.getModel().getValueAt(row, 9));
        return lib;
    }
    
    /**
     * Kiểm tra thông tin đăng nhập của người dùng
     * @param username tên đăng nhập của người dùng
     * @param password mật khẩu của người dùng
     * @return true nếu tên đăng nhập và mật khẩu chính xác, false nếu không chính xác
     */
    public boolean login(String username, String password){
        Librarian librarian = null;
        Connect newConnection = new Connect();
        newConnection.doConnect();
        Connection con = newConnection.connection;
        Statement st;
        String sql = String.format("SELECT * "
                                 + "FROM ThuThu "
                                 + "WHERE TenTaiKhoan = '%s' && MatKhau = '%s';",
                                                          username, password);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                librarian = new Librarian(rs.getString("MaNV"), 
                    rs.getString("TenNV"), rs.getString("Gioitinh"), rs.getDate("Ngaysinh"),
                    rs.getString("Diachi"), rs.getString("Phone"), rs.getString("Email"), 
                    rs.getInt("Luong"), username, password, rs.getInt("VaiTro"));
                MainView.setCurrentUser(librarian);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(librarian != null)
            return true;
        return false;
    }
    
    /**
     * Thay đổi mật khẩu của người dùng
     * @param password mật khẩu mới
     * @return true nếu việc thay đổi thành công, false nếu thất bại
     */
    public boolean changePassword(String password){
        String ID = MainView.getCurrentUser().getID();
        Connect newConnection = new Connect();
        newConnection.doConnect();
        Connection con = newConnection.connection;
        Statement st;
        int flag = 0;
        String sql = String.format("UPDATE ThuThu "
                                 + "SET Matkhau = '%s' "
                                 + "WHERE MaNV = '%s';", password, ID);
        try {
            st = con.createStatement();
            flag = st.executeUpdate(sql);            
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(flag > 0){
            MainView.getCurrentUser().setPassword(password);
            return true;
        }
        return false;
    }
    
    /**
     * Hiện Frame chứa thông tin của người dùng
     */
    public static void showInfo(){
        LibrarianDataForm infoFrame = new LibrarianDataForm();
        infoFrame.setInfo();
        infoFrame.setVisible(true);
    }
    
    /**
     * cập nhật thông tin của người dùng
     * @param address địa chỉ mới
     * @param phone số điện thoại mới
     * @param mail email mới
     * @return true nếu việc cập nhật thành công, false nếu thất bại
     */
    public static boolean updateProfile(String address, String phone, String mail){
        String ID = MainView.getCurrentUser().getID();
        Connect newConnection = new Connect();
        newConnection.doConnect();
        Connection con = newConnection.connection;
        Statement st;
        String sql = String.format("UPDATE ThuThu "
                                 + "SET Diachi = '%s', Phone = '%s', Email = '%s' "
                                 + "WHERE MaNV = '%s'", address, phone, mail, ID);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);            
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    /**
     * Thống kê độc giả theo một thông tin nhất định và hiển thị lên bảng thống kê
     * @param select định danh của thông tin thống kê
     * 11: thống kê theo năm sinh
     * 12: thống kê theo giới tính
     * 13: thống kê theo địa chỉ
     * 14: thống kê theo lương
     * @param table bảng hiển thị thông tin thống kê
     */
    public void statisticReader(int select, JTable table) {
        String[] colName = new String[2];
        String kieuThongKe = null;
        ConnectLibrarian ConnectL = new ConnectLibrarian();
        ConnectL.doConnect();
        ResultSet rs1 = ConnectL.CountYear();
        if (select == 11) {
            colName[0] = "Năm Sinh";
            colName[1] = "Số lượng";
            DefaultTableModel model = new DefaultTableModel(colName, 0);
            Object[] objNV = new Object[2];

            try {
                while (rs1.next()) {
                    objNV[0] = rs1.getInt("YEAR(Ngaysinh)");
                    objNV[1] = rs1.getInt("count(YEAR(Ngaysinh))");
                    model.addRow(objNV);
                }
                table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (select == 12) {
                kieuThongKe = "Gioitinh";
                colName[0] = "Giới Tính";
            }
            if (select == 13) {
                kieuThongKe = "Diachi";
                colName[0] = "Địa Chỉ";
            }
            if (select == 14) {
                kieuThongKe = "Luong";
                colName[0] = "Lương";
            }
            ResultSet rs = ConnectL.CountLibrarian(kieuThongKe);
            colName[1] = "Số lượng";
            DefaultTableModel model = new DefaultTableModel(colName, 0);
            Object[] objNV = new Object[2];

            try {
                while (rs.next()) {
                    objNV[0] = rs.getString(kieuThongKe);
                    objNV[1] = rs.getInt("count(MaNV)");
                    model.addRow(objNV);
                }
                table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Hiển thị thông tin về lương của nhân viên trên bảng báo cáo
     * @param table bảng báo cáo lương của nhân viên
     */
    public void reportSalary(JTable table) {
        String[] colName = {"Mã Nhân Viên", "Tên Nhân Viên", "Lương Tháng Này"};
        DefaultTableModel model = new DefaultTableModel(colName, 0);
        Object[] objNV = new Object[3];
        ConnectLibrarian ConnectLib = new ConnectLibrarian();
        ConnectLib.doConnect();
        ResultSet rs = ConnectLib.getAllLibrarians();
        try {
            while (rs.next()) {
                objNV[0] = rs.getString("MaNV");
                objNV[1] = rs.getString("TenNV");
                objNV[2] = rs.getInt("Luong");
                model.addRow(objNV);
            }
            table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
