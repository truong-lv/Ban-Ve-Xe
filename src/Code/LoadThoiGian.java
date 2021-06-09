/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;



/**
 *
 * @author n18dc
 */
public class LoadThoiGian extends Thread{
    private JLabel lbTime;
    private JLabel lbDate;
    
    public LoadThoiGian(JLabel lbTime, JLabel lbDate) {
        this.lbTime = lbTime;
        this.lbDate=lbDate;
    }
    
   @Override
   public void run(){
        try {
            while (true) {
                Calendar calendar = Calendar.getInstance();
                String hour = (calendar.getTime().getHours() > 9) ? 
                        "" + calendar.getTime().getHours() + ""
                        : "0" + calendar.getTime().getHours();
                String minute = (calendar.getTime().getMinutes() > 9) ? 
                        "" + calendar.getTime().getMinutes() + ""
                        : "0" + calendar.getTime().getMinutes();
                String second = (calendar.getTime().getSeconds() > 9) ? 
                        "" + calendar.getTime().getSeconds() + ""
                        : "0" + calendar.getTime().getSeconds();
                lbTime.setText(hour + ":" + minute + ":" + second);
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");// /MM/uuuu
                LocalDate localDate = LocalDate.now();
                lbDate.setText(dtf.format(localDate));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }
}
