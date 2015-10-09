package com.g7;

/**
 * Created by perry_13 on 10/8/15.
 */

import java.sql.*;

public class BackEndManager {

    //singleton object
    private static BackEndManager sharedManager = null;

    static Connection connection;

    protected BackEndManager(){

    }

    public static BackEndManager sharedManager() throws Exception {
        if (sharedManager == null){
            sharedManager = new BackEndManager();

            try {
                connection = getConnection();
            }catch (Exception e){
                System.out.println(e);
            }


        }

        return sharedManager;
    }

    private static Connection getConnection() throws Exception{

        try {
            String driver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://68.178.143.97";
            String user = "CSE360DB";
            String pass = "cse360Password!";

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(dbURL, user, pass);

            System.out.println("Connected to database");

            return connection;

        }catch (Exception e){
            System.out.println(e);
        }



        return null;

    }



}
