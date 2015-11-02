package com.g7;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.sql.*;

/**
 * Created by Kunhee on 11/1/2015.
 */
public class StatisticalReport {
    private int numUsers = 0;
    public static void main(String[] args) {
        System.out.println("numUsers function: " + numUsers());
    }

    public WindowManager(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 850, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        contentPane.setBackground(backgroundColor);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        this.setVisible(true);
        ShowLogin();
    }

    private static Connection getUserConnection(){

        String driver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://68.178.143.97/CSE360DB";
        String user = "CSE360DB";
        String pass = "cse360Password!";

        Connection connection = null;

        try {
            Class.forName(driver);

            System.out.println("Connecting to Users database");

            connection = DriverManager.getConnection(dbURL, user, pass);

            System.out.println("Connected to Users database");
        }catch(SQLException se){
            System.out.println("JDBC error:");
            se.printStackTrace();
        }catch(Exception e){
            System.out.println("class for name error:");
            e.printStackTrace();
        }

        return connection;

    }

    public static void numUsers(){

        int numUsers = 0;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("Pulling table");

            String sql = "SELECT count(*) FROM Users;";

            ResultSet set = statement.executeQuery(sql);
            if (set.next()){
                numUsers = set.getInt("count(*)");
            }

        }catch (Exception e){
            e.printStackTrace();
            numUsers = 0;
        }
    }
}
