package com.g7;

/**
 * Created by perry_13 on 10/8/15.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public void registerNewUser(String firstName, String lastName, String username, String password, String email, String phoneNumber, int accountType){


        if (firstName != null && lastName != null && password != null && email != null && phoneNumber != null){
            //begin sql calls

            try {
                System.out.println("inserting records into user table");

                String sql = "INSERT INTO Users " +
                        "VALUES ("+
                        "0, " + "'"+firstName+"', "+ "'"+lastName+"', "+ "'"+username+"', "+ "'"+password+"', "+ "'"+email+"', "+ "'"+phoneNumber+"', "+ accountType+")";
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

    public boolean doesUserExist(String username){

        boolean doesUserExist;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("checking if user exists...");

            String sql = "SELECT username FROM Users WHERE username = "+"'"+username+"'";


            ResultSet set = statement.executeQuery(sql);

            System.out.println(set);

            if (set.next()){
                System.out.println(username+" already exists");
                doesUserExist = true;
            }else{
                System.out.println(username+" is a new user");
                doesUserExist = false;

            }


        }catch (Exception e){
            e.printStackTrace();
            doesUserExist = true;
        }

        return doesUserExist;

    }

    public int getUserID(String username){

        int id;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting id for user");

            String sql = "SELECT id FROM Users WHERE username = "+"'"+username+"'";
            System.out.println(username + "\n"+ sql);

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                id = set.getInt("id");
                System.out.println("id: "+id);

            }else{
                System.out.println("no id for such user");
                id = -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            id = -1;
        }

        return id;

    }

    public boolean isPassword(String username, String password){

        //if username-> password on data base matches, return true, if not return false

        boolean doesPasswordMatch;

        if (doesUserExist(username)){
            try {
                Connection userConnection = getUserConnection();
                Statement statement = userConnection.createStatement();

                System.out.println("checking if password is correct");

                String sql = "SELECT password FROM Users WHERE username = "+"'"+username+"'";
                System.out.println(username + "\n"+ sql);

                ResultSet set = statement.executeQuery(sql);

                if (set.next()){
                    String serverPassword = set.getString("password");

                    System.out.println("server password: "+serverPassword);
                    System.out.println("entered password: " + password);

                    if(serverPassword.compareTo(password) == 0){
                        System.out.println("passwords match");
                        doesPasswordMatch = true;
                    }else{
                        System.out.println("passwords don't match");
                        doesPasswordMatch = false;
                    }

                }else{
                    System.out.println("no password for such user");
                    doesPasswordMatch = false;
                }

            }catch (Exception e){
                e.printStackTrace();
                doesPasswordMatch = false;
            }

            return doesPasswordMatch;

        }else{
            return false;
        }

    }
//
//    public String[] getHealthConditionEntries(int userID){
//
//        //Return all strings of the given user's healthCondition list
//    }
//
//    public Appointment[] getAppointmentList(int userID){
//
//        //Return all strings of the given user's appointment list
//
//
//    }

    public void createHealthConditionEntry(String info, int userID){

    }

    public void createAppointment(String appointmentInfo, int userID){

    }

    public int getDashboardType(int userID){
        //return account type
        int accountType;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting account type from id");

            String sql = "SELECT accountType FROM Users WHERE id = "+"'"+userID+"'";
            System.out.println(userID + "\n"+ sql);

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                accountType = set.getInt("accountType");
                System.out.println("account type: "+accountType);

            }else{
                System.out.println("no account type for such id");
                accountType = -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            accountType = -1;
        }

        return accountType;
    }



}
