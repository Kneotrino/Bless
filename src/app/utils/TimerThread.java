/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author RHINO
 */
public class TimerThread extends Thread {

        protected boolean isRunning;

        protected JLabel dateLabel;
        protected JLabel timeLabel;

        protected SimpleDateFormat dateFormat = 
                new SimpleDateFormat(" dd MMM yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("hh:mm:ss a");

        public TimerThread(JLabel dateLabel, JLabel timeLabel) {
            this.dateLabel = dateLabel;
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        int d = currentTime.getDay() ;
                        String hari;
                        switch (d) {
                            case 1: hari = "Senin";                               
                                break;
                            case 2: hari = "Selasa";                               
                                break;
                            case 3: hari = "Rabu";                               
                                break;
                            case 4: hari = "Kamis";                               
                                break;
                            case 5: hari = "Jumat";                               
                                break;
                            case 6: hari = "Sabtu";                               
                                break;
                            case 0: hari = "Minggu";                               
                                break;
                            default:
                                throw new AssertionError();
                        }
                        dateLabel.setText(hari+dateFormat.format(currentTime));
                        timeLabel.setText("Sistem Informasi Management Showroom   :["+hari+dateFormat.format(currentTime)+"];[" + timeFormat.format(currentTime)+"]");
                    }
                });

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }