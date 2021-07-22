/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Lớp này dùng để chuyển các bảng thống kê và báo cáo thành file PDF và lưu vào trong máy 
 * @author Doanh
 */
public class Export {

    /**
     * Chuyển đối tượng JTable thành đối tượng PdfPTable
     * @param table đối tượng JTable cần chuyển
     * @return đối tượng PdfPTable được chuyển từ đối tượng JTable
     * @throws DocumentException
     * @throws IOException 
     */
    public static PdfPTable getTable(JTable table) throws DocumentException, IOException {
        BaseFont bf1 = BaseFont.createFont("vuTimes.ttf", BaseFont.IDENTITY_H, true);
        Font fontBang = new Font(bf1, 10, Font.NORMAL, BaseColor.BLACK);
        Font fontCot = new Font(bf1, 12, Font.BOLD, BaseColor.BLUE);
        int numColumm = table.getColumnCount();
        // set cho dòng đầu tiên
        PdfPTable pdfTable = new PdfPTable(numColumm);
        for (int i = 0; i < numColumm; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(table.getColumnName(i), fontCot));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell);
        }
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                PdfPCell cell = new PdfPCell(new Paragraph("" + table.getValueAt(i, j), fontBang));
                pdfTable.addCell(cell);
            }
        }
        return pdfTable;
    }

    /**
     * chuyển bảng thống kê thành file PDF và lưu vào trong máy
     * @param table bảng thống kê cần lưu
     * @param path đường dẫn lưu file
     * @param tenThongKe nội dung thống kê, dùng để hiển thị trong file thống kê
     * @return 
     */
    public static boolean printStatistic(JTable table, String path, String tenThongKe) {
        boolean isSuccess = false;
        try {
            BaseFont bf1 = BaseFont.createFont("vuTimes.ttf", BaseFont.IDENTITY_H, true);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path + ".pdf"));
            document.open();
            Font fontTenThuVien = new Font(bf1, 35, Font.BOLD, BaseColor.RED);
            Font fontTieuDe = new Font(bf1, 20, Font.BOLD, BaseColor.BLACK);
            Font fontChung = new Font(bf1, 14, Font.NORMAL, BaseColor.BLACK);
            Font fontChuKy = new Font(bf1, 16, Font.BOLD, BaseColor.BLACK);
            Font fontLienLac = new Font(bf1, 6, Font.UNDEFINED, BaseColor.BLACK);
            document.add(new Paragraph("      THƯ VIỆN TẠ QUANG BỬU", fontTenThuVien));
            document.add(new Paragraph(" ", fontChuKy));
            document.add(new Paragraph("Đại học Bách Khoa Hà Nội - Số 1 Đại Cồ Việt - Hai Bà Trưng - Hà Nội                                                                                                                                                                                                      SĐT: 0969991097", fontLienLac));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", fontLienLac));
            document.add(new Paragraph("                                  PHIẾU THỐNG KÊ", fontTieuDe));
            document.add(new Paragraph("                               ", fontTieuDe));
            document.add(new Paragraph("Nội dung thống kê      : " + tenThongKe, fontChung));
            document.add(new Paragraph("Ngày thống kê          : " + new java.sql.Date(new Date().getTime()), fontChung));
            document.add(new Paragraph(" ", fontChung));
            PdfPTable table2 = getTable(table);
            document.add(table2);
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph("                                                               Chữ ký cán bộ và đóng dấu ", fontChuKy));
            document.close();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * chuyển bảng báo cáo thành file PDF và lưu vào trong máy
     * @param table bảng báo cáo cần lưu
     * @param path đường dẫn lưu file
     * @param baoCao nội dung báo cáo, dùng để hiển thị trong file báo cáo
     * @return 
     */
    public static boolean printReport(JTable table, String path, String baoCao) {
        boolean isSuccess = false;
        try {
            BaseFont bf1 = BaseFont.createFont("vuTimes.ttf", BaseFont.IDENTITY_H, true);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path + ".pdf"));
            document.open();
            Font fontTenThuVien = new Font(bf1, 35, Font.BOLD, BaseColor.RED);
            Font fontTieuDe = new Font(bf1, 20, Font.BOLD, BaseColor.BLACK);
            Font fontChung = new Font(bf1, 14, Font.NORMAL, BaseColor.BLACK);
            Font fontChuKy = new Font(bf1, 16, Font.BOLD, BaseColor.BLACK);
            Font fontLienLac = new Font(bf1, 6, Font.UNDEFINED, BaseColor.BLACK);
            document.add(new Paragraph("       THƯ VIỆN TẠ QUANG BỬU", fontTenThuVien));
            document.add(new Paragraph(" ", fontChuKy));
            document.add(new Paragraph("Đại học Bách Khoa Hà Nội - Số 1 Đại Cồ Việt - Hai Bà Trưng - Hà Nội                                                                                                                                                                                                      SĐT: 0969991097", fontLienLac));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", fontLienLac));
            document.add(new Paragraph("                                              BÁO CÁO", fontTieuDe));
            document.add(new Paragraph("                               ", fontTieuDe));
            document.add(new Paragraph("Báo Cáo Theo          : " + baoCao, fontChung));
            document.add(new Paragraph("Ngày                  : " + new java.sql.Date(new Date().getTime()), fontChung));
            document.add(new Paragraph("Kết quả           :", fontChung));
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph(" ", fontChung));
            PdfPTable table2 = getTable(table);
            document.add(table2);
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph(" ", fontChung));
            document.add(new Paragraph("                                                               Chữ ký cán bộ và đóng dấu ", fontChuKy));
            document.close();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }
 }
