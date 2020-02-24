/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class WorkTime extends javax.swing.JFrame implements Runnable {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    String id;
    LocalDate localDate = LocalDate.now();
    public static String[] arr;
    public static int count;
    int sec = 0;
    int min = 0;
    int hoursL = 0;
    String time;
    int hoursB = 0;
    int minutesB = 0;
    int secondsB = 0;

    static boolean state = true;

    public void clock() {

        Thread clock = new Thread() {

            public void run() {

                //This block of code does the label to show date and running time
                try {
                    while (true) {
                        Calendar cal = new GregorianCalendar();
                        localDate = LocalDate.now();

                        int second = cal.get(Calendar.SECOND);

                        int minute = cal.get(Calendar.MINUTE);

                        int hour = cal.get(Calendar.HOUR_OF_DAY);

                        if ((hour < 10) && (minute < 10) && (second < 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  0" + hour + ":0" + minute + ":0" + second);
                        } else if ((hour < 10) && (minute < 10) && (second >= 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  0" + hour + ":0" + minute + ":" + second);
                        } else if ((hour < 10) && (minute >= 10) && (second >= 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  0" + hour + ":" + minute + ":" + second);
                        } else if ((hour >= 10) && (minute < 10) && (second < 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  " + hour + ":0" + minute + ":0" + second);
                        } else if ((hour >= 10) && (minute < 10) && (second >= 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  " + hour + ":0" + minute + ":" + second);
                        } else if ((hour >= 10) && (minute >= 10) && (second < 10)) {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  " + hour + ":" + minute + ":0" + second);
                        } else {
                            dateLabel.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "  " + hour + ":" + minute + ":" + second);
                        }
                        sleep(1000);

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(WorkTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        clock.start();
    }

    public WorkTime() {
        initComponents();
    }

    public WorkTime(String paraEmail) {
        initComponents();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("select `firstName`, `id` from `user` where `email`='" + paraEmail + "'");
            if (rs.next()) {
                String name = rs.getString("firstName");
                id = rs.getString("id");

                label.setText("Hello " + name);
            }

        } catch (Exception e) {
            System.out.println("Your exception is : \n" + e);
        }

        clock();

    }

    //Always implement it, it runs the thread
    public void run() {
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        hoursLabel = new javax.swing.JLabel();
        minutesLabel = new javax.swing.JLabel();
        secondsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        startButton.setBackground(new java.awt.Color(52, 122, 28));
        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stopButton.setBackground(new java.awt.Color(253, 2, 52));
        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));

        dateLabel.setBackground(new java.awt.Color(0, 0, 0));
        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));

        hoursLabel.setBackground(new java.awt.Color(0, 0, 0));
        hoursLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        hoursLabel.setForeground(new java.awt.Color(255, 255, 255));
        hoursLabel.setText("00 :");

        minutesLabel.setBackground(new java.awt.Color(0, 0, 0));
        minutesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        minutesLabel.setForeground(new java.awt.Color(255, 255, 255));
        minutesLabel.setText("00 :");

        secondsLabel.setBackground(new java.awt.Color(0, 0, 0));
        secondsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        secondsLabel.setForeground(new java.awt.Color(255, 255, 255));
        secondsLabel.setText("00 ");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Keep up with the good work!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(minutesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(secondsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(startButton)
                        .addGap(208, 208, 208)
                        .addComponent(stopButton))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(95, 95, 95)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 136, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minutesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(secondsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(stopButton))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        //recontinue in the same time if you login within same day

        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = (localDate).format(formatter_1);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(timestamp) AS total FROM `worktime` WHERE timestamp LIKE '"+localDate+"%' AND user_fk = "+id);
            if (rs.next()) {
                //converting the rs into string
                String result = rs.getString("total");
                //converting the string into int
                count = Integer.parseInt(result);
                if (count > 2) {
                    //converting the array with the exact number of element as the count is
                    arr = new String[count];
                    //looping through to input the strings of timestamp
                    rs = stmt.executeQuery("SELECT timestamp FROM `worktime` WHERE timestamp LIKE '" + localDate + "%' AND user_fk = " + id );
                    //puts all the times of a current date into the array
                    try {
                       
                        int i = 0;
                        while (rs.next()) {

                            String timestamp = rs.getString("timestamp");
                            time = timestamp.substring(10, 19);
                            arr[i] = time;
                            i++;
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    // converts the strings of the array into the integer seconds
                    int sec_1;
                    int sec_2;
                    int hour_1;
                    int hour_2;
                    int min_1;
                    int min_2;
                    int s_1 = 0;
                    int s_2 = 0;
                    String sec_str1;

                    String sec_str2;
                    String min_str1;
                    String min_str2;
                    String hour_str1;
                    String hour_str2;

                    //System.out.println("the i-th hour minute and second of the array "+ arr[1].charAt(1)+arr[1].charAt(2)+"-"+ arr[1].charAt(4)+  arr[1].charAt(5)+ "-"+ arr[1].charAt(7)+  arr[1].charAt(8)+ " done with time "); 
                    for (int i = 0; i < count - 1; i += 2) {

                        sec_str1 = "" + arr[i].charAt(7) + arr[i].charAt(8);
                        sec_str2 = "" + arr[i+1].charAt(7) + arr[i+1].charAt(8) + "";
                        min_str1 = "" + arr[i].charAt(4) + arr[i].charAt(5) + "";
                        min_str2 = "" + arr[i+1].charAt(4) + arr[i+1].charAt(5) + "";
                        hour_str1 = "" + arr[i].charAt(1) + arr[i].charAt(2) + "";
                        hour_str2 = "" + arr[i+1].charAt(1) + arr[i+1].charAt(2) + "";

                        sec_1 = Integer.parseInt(sec_str1);
                        sec_2 = Integer.parseInt(sec_str2);

                        min_1 = Integer.parseInt(min_str1);
                        min_2 = Integer.parseInt(min_str2);

                        hour_1 = Integer.parseInt(hour_str1);
                        hour_2 = Integer.parseInt(hour_str2);

                        s_1 = sec_1 + (min_1 * 60) + (hour_1 * 3600);
                        s_2 = sec_2 + (min_2 * 60) + (hour_2 * 3600);

                        sec += s_2 - s_1;
                    }
                    //converts the seconds to hours the correct one
                    hours= sec/3600;
                    sec = sec - (hours * 3600);
                    minutes = sec / 60;
                    sec = sec - (minutes * 60);
                    seconds = sec;

                    if (seconds < 10) {
                        secondsLabel.setText("0" + seconds);
                    } else {
                        secondsLabel.setText(seconds + "");
                    }
                    if (minutes < 10) {
                        minutesLabel.setText("0" + minutes);
                    } else {
                        minutesLabel.setText(minutes + "");
                    }
                    if (hours < 10) {
                        hoursLabel.setText("0" + hours);
                    } else {
                        hoursLabel.setText(hours + "");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("this is the outer try catch: \n" + e);
        }

        //save the current timestamp in the database 
        try {

            String query = "INSERT INTO `worktime`(`timestamp`, `user_fk`) VALUES (?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, dateLabel.getText());
            pst.setString(2, id);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Your exception is : \n" + e);
        }

        //activates the stopwatch thread
        state = true;
        Thread t = new Thread() {

            public void run() {

                for (;;) {

                    if (state == true) {

                        try {
                            sleep(1000);
                            seconds++;
                            if (seconds >= 60) {
                                seconds = 0;
                                minutes++;
                            }
                            if (minutes >= 60) {
                                minutes = 0;
                                hours++;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Your exception is " + ex);
                        }

                        if (seconds < 10) {

                            secondsLabel.setText("0" + seconds);
                        } else {
                            secondsLabel.setText(seconds + "");
                        }
                        if (minutes < 10) {

                            minutesLabel.setText("0" + minutes + " :");
                        } else {
                            minutesLabel.setText(minutes + " :");
                        }
                        if (hours < 10) {

                            hoursLabel.setText("0" + hours + " :");
                        } else {
                            hoursLabel.setText(hours + " :");
                        }

                    } else {
                        break;
                    }
                }
            }
        };
        t.start();

    }//GEN-LAST:event_startButtonActionPerformed


    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

        state = false;
        try {

            String query = "INSERT INTO `worktime`(`timestamp`, `user_fk`) VALUES (?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, dateLabel.getText());
            pst.setString(2, id);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Your exception is : \n" + e);
        }

    }//GEN-LAST:event_stopButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkTime().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel hoursLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.JLabel secondsLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
