package com.g7;

/**
 * Created by perry_13 on 10/8/15.
 */

import java.sql.*;

public class BackEndManager {

    //singleton object
    private static BackEndManager sharedManager = null;

    protected BackEndManager(){

    }

    public static BackEndManager sharedManager() throws Exception {
        if (sharedManager == null){
            sharedManager = new BackEndManager();

            try {

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


    //--SQL calls----------

    public void registerNewUser(String firstName, String lastName, String password, String email, String phoneNumber, int accountType){


        if (firstName != null && lastName != null && password != null && email != null && phoneNumber != null){
            //begin sql calls

            try {
                System.out.println("inserting records into user table");

                String sql = "INSERT INTO Users " +
                        "VALUES ("+
                        "0, " + "'"+firstName+"', "+ "'"+lastName+"', "+ "'"+password+"', "+ "'"+email+"', "+ "'"+phoneNumber+"', "+ accountType+")";
                System.out.println(sql);

                Connection userConnection = getUserConnection();

                Statement statement = userConnection.createStatement();
                statement.executeUpdate(sql);


            }catch (Exception e){
                System.out.println("failed to insert records with error: ");
                e.printStackTrace();
            }


        }else{
            System.out.println("Missing something from registration");
        }



    }




}
