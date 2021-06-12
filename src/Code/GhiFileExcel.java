/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

/**
 *
 * @author n18dc
 */
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JTable;
 

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 

public class GhiFileExcel {
 
    //private  String fileName = "";
    private  String fileName = "";
    private  String tieuDe = "";
    private  Vector chiTiet1 ;
    private  Vector chiTiet2 ;
    private  JTable tb;
    // data to write file
    
    public void setFileName(String fileName) {
           if(fileName.indexOf(".xls")==-1){
               fileName=fileName+".xls";
           }
           this.fileName = fileName;
       }

    public void setTitle(String title) {
        this.tieuDe = title;
    }

    public void setChiTiet1(Vector chiTiet1) {
        this.chiTiet1 = chiTiet1;
    }

    public void setChiTiet2(Vector chiTiet2) {
        this.chiTiet2 = chiTiet2;
    }


    public GhiFileExcel(JTable tb, String title) {
        this.tb = tb;
        this.tieuDe=title;
    }
    
    // create and write new file *.xls
    public void writeFileExcel() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(fileName));
 
            // create sheet
            WritableSheet sheet1 = workbook.createSheet("DS VE", 0);
 
            // Hiện tiêu đề
            sheet1.addCell(new Label(12, 1, this.tieuDe));
            
            //Hiện Thông tin liên quan của bảng 
            int colChiTiet = 9;
            for(int i=0; i<this.chiTiet1.size();colChiTiet+=3,i++)
            {
                sheet1.addCell(new Label(colChiTiet,4, this.chiTiet1.get(i).toString()));
            }
            colChiTiet=9;
            for(int i=0; i<this.chiTiet2.size();colChiTiet+=3,i++)
            {
                sheet1.addCell(new Label(colChiTiet,6, this.chiTiet2.get(i).toString()));
            }
            //sheet1.addCell(new Label(10, 5, this.chiTiet2));
            // row begin write data
            int rowBegin = 10;
            int colBegin = 9;
            for (int col = colBegin, i = 0; col < tb.getColumnCount()+ colBegin; col++, i++) {
                sheet1.addCell(new Label(col, rowBegin, String.valueOf( tb.getColumnName(i).toUpperCase())));
            }
            ++rowBegin;
            for (int row = rowBegin, i = 0; row < tb.getRowCount()+ rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < tb.getColumnCount() + colBegin; col++, j++) {
                    sheet1.addCell(new Label(col, row, String.valueOf( tb.getValueAt(i, j))));
                }
            }
            // write file
            workbook.write();
 
            // close
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }
        System.out.println("create and write success");
    }
    
    public void writeFileExcel2() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(fileName));
 
            // create sheet
            WritableSheet sheet1 = workbook.createSheet("DS VE", 0);
 
            // Hiện tiêu đề
            sheet1.addCell(new Label(12, 1, this.tieuDe));
            
            //Hiện Thông tin liên quan của bảng 
            int colChiTiet = 9;
            for(int i=0; i<this.chiTiet1.size();colChiTiet+=3,i++)
            {
                sheet1.addCell(new Label(colChiTiet,4, this.chiTiet1.get(i).toString()));
            }
            colChiTiet=9;
            
            //sheet1.addCell(new Label(10, 5, this.chiTiet2));
            // row begin write data
            int rowBegin = 10;
            int colBegin = 9;
            for (int col = colBegin, i = 0; col < tb.getColumnCount()+ colBegin; col++, i++) {
                sheet1.addCell(new Label(col, rowBegin, String.valueOf( tb.getColumnName(i).toUpperCase())));
            }
            ++rowBegin;
            for (int row = rowBegin, i = 0; row < tb.getRowCount()+ rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < tb.getColumnCount() + colBegin; col++, j++) {
                    sheet1.addCell(new Label(col, row, String.valueOf( tb.getValueAt(i, j))));
                }
            }
            // write file
            workbook.write();
 
            // close
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }
        System.out.println("create and write success");
    }
 
}