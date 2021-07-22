package controller;

import connectmysql.ConnectBillDetail;
import model.BillDetail;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Trao đổi dữ liệu giữa bảng chi tiết phiếu mượn trên giao diện và đối tượng ConnectBillDetail
 *
 * @author maidoanh
 */
public class BillDetailController {
    
    /**
     * Lấy dữ liệu của các chi tiết phiếu mượn và hiển thị lên bảng
     *
     * @param rs đối tượng ResultSet chứa dữ liệu của các chi tiết phiếu mượn
     * @param table bảng hiển thị thông tin các chi tiết phiếu mượn
     */
    public void Loaddatatotable(ResultSet rs, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            while (rs.next()) {
                Object row[] = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loadata function ERROR");
        }
    }

    /**
     * Thêm một chi tiết phiếu mượn vào trong cơ sở dữ liệu và hiển thị chi tiết phiếu mươn trên bảng
     * @param billDetail chi tiết phiếu mượn cần thêm vào cơ sở dữ liệu và bảng hiển thị
     * @param table bảng hiện thị danh sách các chi tiết phiếu mượn
     */
    public void addBillDetail(BillDetail billDetail, JTable table) {
        ((DefaultTableModel) table.getModel()).setNumRows(0);
        ConnectBillDetail connectBillDetail = new ConnectBillDetail();
        connectBillDetail.doConnect();
        connectBillDetail.insertBillDetail(billDetail);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ResultSet rs = connectBillDetail.getBillDetailByBillID(billDetail.getBillID());
        try {
            while (rs.next()) {
                Object row[] = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getObject(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Addlib function ERROR ");
        }

    }

    /**
     * Lấy một đối tượng chi tiết phiếu mượn từ bảng hiển thị danh sách chi tiết phiếu mượn
     * @param Table bảng chứa danh sách các chi tiết phiếu mượn
     * @param row hàng được chọn trong bảng
     * @return chi tiết phiếu mượn được chọn
     */
    public BillDetail getBillDetailFromTable(JTable Table, int row) {
        BillDetail billDetail = new BillDetail();
        billDetail.setBillID((String) Table.getModel().getValueAt(row, 0));
        billDetail.setBookID((String) Table.getModel().getValueAt(row, 1));
        billDetail.setNumber(Integer.valueOf(Table.getModel().getValueAt(row, 2).toString()));
        billDetail.setDeposit(Integer.valueOf(Table.getModel().getValueAt(row, 3).toString()));
        billDetail.setBorrowingDay(Date.valueOf(Table.getModel().getValueAt(row, 4).toString()));
        billDetail.setPayDay(Date.valueOf(Table.getModel().getValueAt(row, 5).toString()));
        return billDetail;
    }
}
