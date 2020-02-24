package Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HourCalculation extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    String fullNameFilter;
    String dateFilter;
    ArrayList<String> rsList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> timeList = new ArrayList<>();
    ArrayList<String> realTimeList = new ArrayList<>();
    ArrayList<Integer> indexList = new ArrayList<>();
    ArrayList<Integer> time = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    ArrayList<Integer> secList = new ArrayList<>();
    int workinTime = 0;
    String timestamp;
    String date1;
    String time1;
    int sec_1;
    int sec_2;
    int hour_1;
    int hour_2;
    int min_1;
    int min_2;
    int s_1 = 0;
    int s_2 = 0;
    String sec_str1;
    int sec = 0;

    String sec_str2;
    String min_str1;
    String min_str2;
    String hour_str1;
    String hour_str2;

    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    String hours_str = null;
    String minutes_str = null;
    String seconds_str = null;
    String realTime = null;
    int payment ;
    int work_hour;
    double currentPayment = 0;
    int currentHour = 0;
    String name;
    String surname;
    String element;
    //getting the content from the database and putting it in a arrayList called rsList
    public void connectDB() {

        fullNameFilter = inputEmployee.getText();
        dateFilter = inputDate.getText();
        char space = ' ';
        int indexSpace = fullNameFilter.indexOf(space);
        name = fullNameFilter.substring(0, indexSpace);
         surname= fullNameFilter.substring(indexSpace+1, fullNameFilter.length());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("select timestamp from worktime where timestamp like '"+dateFilter+"%' and user_fk = ( Select id from user WHERE employee_id = ( SELECT id FROM employee where first_name = '"+name+"' and last_Name = '"+surname+"' ) )");
            //jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            int i = 0;
            if (rs.next() == false) { 
                JOptionPane.showMessageDialog(null,"There's no such employee or the date entered is wrong yyyy-MM"); 
            } 
            while (rs.next()) {
                timestamp = rs.getString("timestamp");
                rsList.add(timestamp);
                i++;
            }
            

        } catch (Exception e) {
            System.out.println("Your exception is : in the result set area \n" + e);
        }

    }
    
        public void getEmpDB() {
            
        fullNameFilter = inputEmployee.getText();
        dateFilter = inputDate.getText();
        char space = ' ';
        int indexSpace = fullNameFilter.indexOf(space);
        String name = fullNameFilter.substring(0, indexSpace);
        String surname = fullNameFilter.substring(indexSpace+1, fullNameFilter.length());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("SELECT hourly_Rate  from employee where first_name = '"+name+"' and last_Name='"+surname+"'");
           
            while(rs.next()){
                String strpayment = rs.getString("hourly_Rate");
                payment=Integer.parseInt(strpayment);
            }
            rs = stmt.executeQuery("SELECT hours  from employee where first_name = '"+name+"' and last_Name='"+surname+"'");
            while(rs.next()){
                String strhour = rs.getString("hours");
                work_hour = Integer.parseInt(strhour);
            } 

        } catch (Exception e) {
            System.out.println("Your exception is : in the payment and the hourly rate  \n" + e);
        }
          
    }

    public HourCalculation() {
        initComponents();
    }
    
    public class DateTime{
         
        public String date;
        public String time;
       
        DateTime(String date, String time){
            this.date=date;
            this.time=time;
        }
    }
    
    public ArrayList ListDateTime(ArrayList dateList, ArrayList realTimeList){
        ArrayList <DateTime> dt = new ArrayList <DateTime> ();
        
        DateTime dttmp;
        for (int i = 0 ; i < dateList.size();i++){
            String d = (String)dateList.get(i);
            String t= (String)realTimeList.get(i);
            dttmp = new DateTime (d,t);
            dt.add(dttmp);
        }    
    
        
        
        
        return dt;
    }
    
    public void addRows(){
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        ArrayList<DateTime> dt = ListDateTime(dateList,  realTimeList);
        Object rowData[] = new Object [2];
        for(int i = 0 ; i < dt.size();i++){
            rowData[0]= dt.get(i).date;
            rowData[1] = dt.get(i).time;
            model.addRow(rowData);
        }
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        inputEmployee = new javax.swing.JTextField();
        inputDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        goButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        workHours = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        salary = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Calculation of  hours to a employee");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Full Name Filter");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date Filter");

        goButton.setBackground(new java.awt.Color(0, 0, 0));
        goButton.setForeground(new java.awt.Color(255, 255, 255));
        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Work Information");

        jTable.setBackground(new java.awt.Color(0, 0, 0));
        jTable.setForeground(new java.awt.Color(255, 255, 255));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hours of work");

        workHours.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        workHours.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Payment");

        salary.setBackground(new java.awt.Color(0, 0, 0));
        salary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        salary.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel4))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(inputEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(goButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(workHours, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(goButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(workHours, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        //calling the method that makes possible to connect with the database and get the values of rs and putting them to rsList
        connectDB();
        //creating the right dateList with elements of the date taken from rsList, no repetition and creating the indexList in the same time
        String el = (String) rsList.get(0);
        dateList.add(el.substring(0, 10));
        indexList.add(0);

        for (int i = 0; i < rsList.size(); i++) {
            if ((dateList.get(dateList.size() - 1)).equals(rsList.get(i).substring(0, 10))) {

            } else {
                String dl = rsList.get(i).substring(0, 10);
                dateList.add(dl);
                indexList.add(i);
            }
        }
        indexList.add(rsList.size());


        //creating the right timeList
        for (int i = 0; i < rsList.size(); i++) {
            timeList.add(rsList.get(i).substring(10, timestamp.length()));
        }

       //trying to loop in the elements of the indexList arrayList
        List<String> tmp_str = new ArrayList<>();
        for (int i = 0; i < indexList.size() - 1; i++) {
            tmp_str = timeList.subList(indexList.get(i), indexList.get(i + 1));
            
            for (int j = 0; j < tmp_str.size() - 1; j += 2) {
                sec_str1 = "" + tmp_str.get(j).charAt(7) + tmp_str.get(j).charAt(8);
                sec_str2 = "" + tmp_str.get(j + 1).charAt(7) + tmp_str.get(j + 1).charAt(8) + "";
                min_str1 = "" + tmp_str.get(j).charAt(4) + tmp_str.get(j).charAt(5) + "";
                min_str2 = "" + tmp_str.get(j + 1).charAt(4) + tmp_str.get(j + 1).charAt(5) + "";
                hour_str1 = "" + tmp_str.get(j).charAt(1) + tmp_str.get(j).charAt(2) + "";
                hour_str2 = "" + tmp_str.get(j + 1).charAt(1) + tmp_str.get(j + 1).charAt(2) + "";

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

            secList.add(sec);
            hours = sec / 3600;
            sec = sec - (hours * 3600);
            minutes = sec / 60;
            sec = sec - (minutes * 60);
            seconds = sec;
            if (hours < 10) {
                hours_str = "0" + hours;
            } else {
                hours_str = "" + hours;
            }

            if (minutes < 10) {
                minutes_str = "0" + minutes;
            } else {
                minutes_str = "" + minutes;
            }

            if (seconds < 10) {
                seconds_str = "0" + seconds;
            } else {
                seconds_str = "" + seconds;
            }

            realTime = hours_str + ":" + minutes_str + ":" + seconds_str;
            realTimeList.add(realTime);
            sec = 0;
        }

        for (int i = 0; i < secList.size(); i++) {
            workinTime += secList.get(i);
        }
        currentHour = workinTime;
        hours = workinTime / 3600;
        workinTime = workinTime - (hours * 3600);
        minutes = workinTime / 60;
        workinTime = workinTime - (minutes * 60);
        seconds = workinTime;
        if (hours < 10) {
            hours_str = "0" + hours;
        } else {
            hours_str = "" + hours;
        }

        if (minutes < 10) {
            minutes_str = "0" + minutes;
        } else {
            minutes_str = "" + minutes;
        }

        if (seconds < 10) {
            seconds_str = "0" + seconds;
        } else {
            seconds_str = "" + seconds;
        }
        //setting the right working time for the whole required month
        realTime = hours_str + ":" + minutes_str + ":" + seconds_str;
        workHours.setText(realTime);
        
        //setting the right salary
        getEmpDB();
        work_hour  *= 3600;
        if (payment == 0 ){
            JOptionPane.showMessageDialog(null, "The default salary for "+ name+" is 0");
        }else if (currentHour == 0){
            JOptionPane.showMessageDialog(null,name + " have not worked this month");
        }else if(work_hour == 0){
            JOptionPane.showMessageDialog(null,"The default hours of work in a month for "+name+" are 0");
        }else{
            
        currentPayment = (payment*currentHour)/work_hour;
        salary.setText(currentPayment+"");
        }
        
       addRows();

    }//GEN-LAST:event_goButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HourCalculation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goButton;
    private javax.swing.JTextField inputDate;
    private javax.swing.JTextField inputEmployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel salary;
    private javax.swing.JLabel workHours;
    // End of variables declaration//GEN-END:variables
}
