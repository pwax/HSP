package com.g7;

/**
 * Created by perry waxman on 10/3/15.
 */

import java.sql.*;

public class DataManager {

    public static Connection getConnection() throws Exception{

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

    public static void registerNewUser(String firstName, String lastName, String username, String password) {

        System.out.println("inserting records into table");

        //Statement statement = getConnection().createStatement();

        //String sql = "INSERT INTO tUsers " + "Values (" + fir

    }






}
