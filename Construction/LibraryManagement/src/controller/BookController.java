/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connectmysql.Connect;
import connectmysql.ConnectBook;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Book;

/**
 * Trao đổi dữ liệu giữa bảng sách trên giao diện và đối tượng ConnectBook
 *
 * @author maidoanh
 */
public class BookController {

    /**
     * Tìm kiếm sách dựa trên một thông tin của sách
     * @param searchMode tên trường để tìm kiếm trong CSDL
     * @param key giá trị của trường tìm kiếm
     * @return danh sách các sách được tìm thấy
     */
    public ArrayList<Book> searchBook(String searchMode, String key) {
        ArrayList<Book> result = new ArrayList<Book>();
        if(key.equals(""))
            return result;
        Connect newConnection = new Connect();
        newConnection.doConnect();
        Connection con = newConnection.connection;
        Statement st;        
        String sql = String.format("SELECT * "
                + "FROM %s "
                + "WHERE %s LIKE '%%%s%%';", Connect.tableBook,
                searchMode, key);
        System.out.println(sql);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book(rs.getString("MaSach"), rs.getString("TenSach"),
                        rs.getString("TacGia"), rs.getString("TinhTrang"),
                        rs.getInt("NamXB"), rs.getString("TheLoai"),
                        rs.getInt("SoLuong"), rs.getInt("TienDatCoc"),
                        rs.getString("GhiChu"), rs.getInt("Gia"),
                        rs.getString("NXB"), rs.getInt("TaiBan"));
                result.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    /**
     * Thống kê sách dựa trên một thông tin sách và hiển thị lên bảng thống kê
     * @param select định danh của trường tìm kiếm trong CSDL
     * 0: tìm kiếm theo tác giả
     * 1: tìm kiếm theo nhà xuất bản
     * 2: tìm kiếm theo năm xuất bản
     * 3: tìm kiếm theo thể loại sách
     * 4: tìm kiếm theo số lần tái bản
     * 6: tìm kiếm theo tình trạng sách
     * 
     * @param table bảng thống kê sách, hiển thị danh sách các sách được tìm thấy
     */
    public void statisticBook(int select, JTable table) {
        String[] colName = new String[2];
        String kieuThongKe = null;
        if (select == 0) {
            kieuThongKe = "TacGia";
            colName[0] = "Tác Giả";
        }
        if (select == 1) {
            kieuThongKe = "NXB";
            colName[0] = "Nhà Xuất Bản";
        }
        if (select == 2) {
            kieuThongKe = "NamXB";
            colName[0] = "Năm Xuất Bản";
        }
        if (select == 3) {
            kieuThongKe = "TheLoai";
            colName[0] = "Thể Loại";
        }
        if (select == 4) {
            kieuThongKe = "TaiBan";
            colName[0] = "Tái Bản";
        }
        if (select == 6) {
            kieuThongKe = "TinhTrang";
            colName[0] = "Tình Trạng";
        }
        colName[1] = "Số lượng";
        DefaultTableModel model = new DefaultTableModel(colName, 0);
        Object[] objSach = new Object[2];
        ConnectBook ConnectB = new ConnectBook();
        ConnectB.doConnect();
        ResultSet rs = ConnectB.CountBook(kieuThongKe);
        try {
            while (rs.next()) {
                objSach[0] = rs.getString(kieuThongKe);
                objSach[1] = rs.getInt("count(MaSach)");
                model.addRow(objSach);
            }
            table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Rewriting(int select, JTable table) {
        String[] colName = new String[3];
        String kieuThongKe = null;
        String MaSach = "MaSach";
        String TenSach = "TenSach";
        kieuThongKe = "SoLuong";
        colName[0] = "Mã Sách";
        colName[1] = "Tên Sách";
        colName[2] = "Số lượng còn lại";
        DefaultTableModel model = new DefaultTableModel(colName, 0);
        Object[] objSach = new Object[3];
        ConnectBook ConnectB = new ConnectBook();
        ConnectB.doConnect();
        ResultSet rs = ConnectB.getAllBooks();
        try {
            while (rs.next()) {
                objSach[0] = rs.getString(MaSach);
                objSach[1] = rs.getString(TenSach);
                objSach[2] = rs.getString(kieuThongKe);
                model.addRow(objSach);
            }
            table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Top(JTable Table, int soLuongSach) {
        ConnectBook ConnectB = new ConnectBook();
        ConnectB.doConnect();
        ResultSet rs = ConnectB.getTopBooks(soLuongSach);
        String[] colName = {"Mã Sách", "Tên Sách", "Tác Giả", "Thể Loại", "NXB", "Số Lượt Mượn"};
        DefaultTableModel model = new DefaultTableModel(colName, 0);
        Object[] objBook = new Object[6];
        try {
            while (rs.next()) {
                objBook[0] = rs.getString("sach.MaSach");
                objBook[1] = rs.getString("sach.TenSach");
                objBook[2] = rs.getString("sach.TacGia");
                objBook[3] = rs.getString("sach.TheLoai");
                objBook[4] = rs.getString("sach.NXB");
                objBook[5] = rs.getInt("count(sach.MaSach)");
                model.addRow(objBook);
            }
            Table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lấy dữ liệu của các quyển sách và hiển thị lên bảng
     *
     * @param rs đối tượng ResultSet chứa dữ liệu của các quyển sách
     * @param table bảng hiển thị thông tin các quyển sách
     */
    public void Loaddatatotable(ResultSet rs, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            while (rs.next()) {
                Object row[] = new Object[12];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getObject(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loadata function ERROR");
        }
    }

    /**
     * Thêm một quyển sách vào trong cơ sở dữ liệu và hiển thị sách trên bảng
     * @param book sách cần thêm vào cơ sở dữ liệu và bảng hiển thị
     * @param table bảng hiện thị danh sách các sách
     */
    public void addBook(Book book, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        ConnectBook connectBook = new ConnectBook();
        connectBook.doConnect();
        connectBook.insertBook(book);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ResultSet rs = connectBook.getAllBooks();
        try {
            while (rs.next()) {
                Object row[] = new Object[12];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getObject(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Addlib function ERROR ");
        }

    }

    /**
     * Lấy một đối tượng sách từ bảng hiển thị danh sách các quyển sách
     * @param Table bảng chứa danh sách các quyển sách
     * @param row hàng được chọn trong bảng
     * @return sách được chọn
     */
    public Book getBookFromTable(JTable Table, int row) {
        Book book = new Book();
        book.setID((String) Table.getModel().getValueAt(row, 0));
        book.setName((String) Table.getModel().getValueAt(row, 1));
        book.setAuthor(String.valueOf(Table.getModel().getValueAt(row, 2)));
        book.setStatus(String.valueOf(Table.getModel().getValueAt(row, 3)));
        book.setPublishingYear(Integer.valueOf(Table.getModel().getValueAt(row, 4).toString()));
        book.setRepublish(Integer.valueOf(Table.getModel().getValueAt(row, 5).toString()));
        book.setCategory((String) Table.getModel().getValueAt(row, 6));
        book.setNumber(Integer.valueOf(Table.getModel().getValueAt(row, 7).toString()));
        book.setDeposit(Integer.valueOf(Table.getModel().getValueAt(row, 8).toString()));
        book.setNote((String) Table.getModel().getValueAt(row, 9));
        book.setPrice(Integer.valueOf(Table.getModel().getValueAt(row, 10).toString()));
        book.setPublisher((String) Table.getModel().getValueAt(row, 11));
        return book;
    }
    
}
