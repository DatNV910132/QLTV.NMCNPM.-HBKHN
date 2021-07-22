package controller;

import connectmysql.ConnectBill;
import model.Bill;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Trao đổi dữ liệu giữa bảng phiếu mượn trên giao diện và đối tượng ConnectBill
 *
 * @author maidoanh
 */
public class BillController {

    /**
     * Lấy dữ liệu của các phiếu mượn và hiển thị lên bảng
     *
     * @param rs đối tượng ResultSet chứa dữ liệu của các phiếu mượn
     * @param table bảng hiển thị thông tin các phiếu mượn
     */
    public void Loaddatatotable(ResultSet rs, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            while (rs.next()) {
                Object row[] = new Object[5];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loadata function ERROR");
        }
    }

    /**
     * Thêm một phiếu mượn vào trong cơ sở dữ liệu và hiển thị phiếu mươn trên bảng
     * @param bill phiếu mượn cần thêm vào cơ sở dữ liệu và bảng hiển thị
     * @param table bảng hiện thị danh sách các phiếu mượn
     */
    public void addBill(Bill bill, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        ConnectBill connectBill = new ConnectBill();
        connectBill.doConnect();
        connectBill.insertBill(bill);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ResultSet rs = connectBill.getAllBills();
        try {
            while (rs.next()) {
                Object row[] = new Object[5];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Addlib function ERROR ");
        }

    }

    /**
     * Lấy một đối tượng phiếu mượn từ bảng hiển thị danh sách phiếu mượn
     * @param Table bảng chứa danh sách các phiếu mượn
     * @param row hàng được chọn trong bảng
     * @return phiếu mượn được chọn
     */
    public Bill getBillFromTable(JTable Table, int row) {
        Bill bill = new Bill();
        bill.setID((String) Table.getModel().getValueAt(row, 0));
        bill.setReaderID((String) Table.getModel().getValueAt(row, 1));
        bill.setLibrarianID(String.valueOf(Table.getModel().getValueAt(row, 2)));
        bill.setStatus(String.valueOf(Table.getModel().getValueAt(row, 3)));
        bill.setTotalMoney(Integer.valueOf(Table.getModel().getValueAt(row, 4).toString()));
        return bill;
    }
}
