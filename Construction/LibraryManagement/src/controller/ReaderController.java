/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connectmysql.ConnectBill;
import connectmysql.ConnectReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Reader;

/**
 * Trao đổi dữ liệu giữa bảng độc giả trên giao diện và đối tượng ConnectReader
 *
 * @author maidoanh
 */
public class ReaderController {

    /**
     * Phân nhóm độc giả cùng số lượng mỗi nhóm và hiển thị trên bảng báo cáo
     * @param select định danh để phân nhóm
     * 7: báo cáo theo năm sinh
     * 8: báo cáo theo giới tính
     * 9: báo cáo theo địa chỉ
     * @param table bảng hiển thị thông tin báo cáo
     */
    public void reportReader(int select, JTable table) {
        String[] colName = new String[2];
        String kieuThongKe = null;
        ConnectReader ConnectR = new ConnectReader();
        ConnectR.doConnect();
        if (select == 7) {
            ResultSet rs1 = ConnectR.countYear();
            colName[0] = "Năm Sinh";
            colName[1] = "Số lượng";
            DefaultTableModel model = new DefaultTableModel(colName, 0);
            Object[] objDG = new Object[2];

            try {
                while (rs1.next()) {
                    objDG[0] = rs1.getInt("YEAR(NgaySinh)");
                    objDG[1] = rs1.getInt("count(YEAR(NgaySinh))");
                    model.addRow(objDG);
                }
                table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(ReaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (select == 8) {
                kieuThongKe = "GioiTinh";
                colName[0] = "Giới Tính";
            }
            if (select == 9) {
                kieuThongKe = "Diachi";
                colName[0] = "Địa Chỉ";
            }
            ResultSet rs = ConnectR.countReader(kieuThongKe);
            colName[1] = "Số lượng";
            DefaultTableModel model = new DefaultTableModel(colName, 0);
            Object[] objDG = new Object[2];

            try {
                while (rs.next()) {
                    objDG[0] = rs.getString(kieuThongKe);
                    objDG[1] = rs.getInt("count(MaDG)");
                    model.addRow(objDG);
                }
                table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(ReaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * thống kê thông tin mượn sách của mỗi độc giả, bao gồm: mã độc giả, tên độc giả, số lượng sách mượn,
     * tổng tiền đặt cọc và hiển thị lên bảng thống kê
     * @param table bảng thống kê
     */
    public void statisticBorrowPay(JTable table) {
        String[] colName = new String[4];
        ConnectReader ConnectR = new ConnectReader();
        ConnectR.doConnect();
        ResultSet rs1 = ConnectR.statisticBillDetail();
        colName[0] = "Mã Độc Giả";
        colName[1] = "Tên Độc Giả";
        colName[2] = "Số Lượng Sách Mượn";
        colName[3] = "Tổng Tiền Đặt Cọc";
        DefaultTableModel model = new DefaultTableModel(colName, 0);
        Object[] objDG = new Object[4];

        try {
            while (rs1.next()) {
                String MaDG = "MaDG";
                objDG[0] = rs1.getString(MaDG);
                String TenDocGia= "TenDocGia";
                objDG[1] = rs1.getString(TenDocGia);
                objDG[2] = rs1.getInt("count(MaSach)");
                objDG[3] = rs1.getInt("sum(TienDatCoc)");
                model.addRow(objDG);
            }
            table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AC(JTable Table, String MaDG){
    ConnectBill ConnectB = new ConnectBill();
    ConnectB.doConnect();
    ResultSet rs = ConnectB.ACBorowPay(MaDG);
    String[] colName = {"Mã Phiếu", "Mã Độc Giả", "Mã Sách","Số lượng", "Ngày Mượn", "Hạn Trả", "Tiền Đặt Cọc"};
    DefaultTableModel model = new DefaultTableModel(colName, 0);
    Object[] objBook = new Object[7];
    try {
                while (rs.next()) {
                    objBook[0] = rs.getString("phieumuon.MaPhieu");
                    objBook[1] = rs.getString("phieumuon.MaDG");
                    objBook[2] = rs.getString("chitietmuontra.MaSach");
                    objBook[3] = rs.getInt("chitietmuontra.SoLuong");
                    objBook[4] = rs.getDate("chitietmuontra.NgayMuon");
                    objBook[5] = rs.getDate("chitietmuontra.HanTra");
                    objBook[6] = rs.getInt("chitietmuontra.TienDatCoc");
                    model.addRow(objBook);
                }
                Table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Tìm kiếm những độc giả đã quá hạn mượn trả về hiển thị lên bảng
     * @param Table  bảng hiển thị thông tin
     */
    public void showOverdueReaders(JTable Table){
    ConnectReader ConnectR = new ConnectReader();
    ConnectR.doConnect();
    ResultSet rs = ConnectR.getOverdueReader();
    String[] colName = {"Mã Độc Giả", "Tên Độc Giả", "Ngày Sinh","Giới Tính", "Địa Chỉ", "Phone", "Email"};
    DefaultTableModel model = new DefaultTableModel(colName, 0);
    Object[] objBook = new Object[7];
    try {
                while (rs.next()) {
                    objBook[0] = rs.getString("MaDG");
                    objBook[1] = rs.getString("TenDocGia");
                    objBook[2] = rs.getString("Ngaysinh");
                    objBook[3] = rs.getString("Gioitinh");
                    objBook[4] = rs.getString("Diachi");
                    objBook[5] = rs.getString("Phone");
                    objBook[6] = rs.getString("Email");
                    model.addRow(objBook);
                }
                Table.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Lấy dữ liệu của các độc giả và hiển thị lên bảng
     *
     * @param rs đối tượng ResultSet chứa dữ liệu của các độc giả
     * @param table bảng hiển thị thông tin các nhân viên
     */
    public void Loaddatatotable(ResultSet rs, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            while (rs.next()) {
                Object row[] = new Object[7];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loadata function ERROR");
        }
    }
    
    /**
     * Thêm một độc giả vào trong cơ sở dữ liệu và hiển thị độc giả trên bảng
     * @param reader độc giả cần thêm vào cơ sở dữ liệu và bảng hiển thị
     * @param table bảng hiện thị danh sách các độc giả
     */
     public void addReader(Reader reader, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        ConnectReader connectReader = new ConnectReader();
        connectReader.doConnect();
        connectReader.insertReader(reader);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ResultSet rs = connectReader.getAllReaders();
        try {
            while (rs.next()) {
                Object row[] = new Object[7];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Addlib function ERROR ");
        }
        
    }
     
     /**
     * Lấy một đối tượng độc giả từ bảng hiển thị danh sách các độc giả
     * @param Table bảng chứa danh sách các nhân viên
     * @param row hàng được chọn trong bảng
     * @return độc giả được chọn
     */
     public Reader getReaderFromTable(JTable Table, int row) {
        Reader reader = new Reader();
        reader.setID((String) Table.getModel().getValueAt(row, 0));
        reader.setName((String) Table.getModel().getValueAt(row, 1));
        reader.setBirth(Date.valueOf(String.valueOf(Table.getModel().getValueAt(row, 2))));
        reader.setGender((String) Table.getModel().getValueAt(row, 3));
        reader.setAddress((String) Table.getModel().getValueAt(row, 4));
        reader.setPhone((String) Table.getModel().getValueAt(row, 5));
        reader.setEmail((String) Table.getModel().getValueAt(row, 6));
        return reader;
    }
}
