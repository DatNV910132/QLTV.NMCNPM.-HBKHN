/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectmysql;

/**
 *
 * @author Dom
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class này thực hiện việc kết nối chương trình với cơ sở dữ liệu
 * @author maidoanh
 */
public class Connect {

    public static Connection connection = null;
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/qltv?useSSL=false";
    public final static String tableLibrarian = "thuthu";
    public final static String tableReader = "docgia";
    public final static String tableBook = "sach";
    public final static String tableBill = "phieumuon";
    public final static String tablePublisher = "nhaxuatban";
    public final static String tableBillDetail = "chitietmuontra";

    /**
     * Tải lớp Driver và tạo đối tượng connection
     */
    public void doConnect() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
