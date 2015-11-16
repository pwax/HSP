package com.g7;

/**
 * Created by perry_13 on 10/8/15.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.security.interfaces.RSAKey;
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

    public void registerNewUser(String firstName, String lastName, String username, String password, String email, String phoneNumber, int accountType, String ssn, String insuranceProvider, String medicalHistory){


        if (firstName != null && lastName != null && password != null && email != null && phoneNumber != null){
            //begin sql calls

            try {
                System.out.println("inserting records into user table");

                String sql = "INSERT INTO Users " +
                        "VALUES ("+
                        "0, " + "'"+firstName+"', "+ "'"+lastName+"', "+ "'"+username+"', "+ "'"+password+"', "+ "'"+email+"', "+ "'"+phoneNumber+"', "+ accountType+", '"+ssn+"', "+"'"+insuranceProvider+"', "+ "'"+medicalHistory+"'"+")";
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

            System.out.println("Getting ID for user: " + username);

            String sql = "SELECT id FROM Users WHERE username = "+"'"+username+"'";

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                id = set.getInt("id");
                System.out.println(username + "'s ID: "+id);

            }else{
                System.out.println("No ID for such user: " + username);
                id = -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            id = -1;
        }

        return id;

    }

    public String getUsername(int userid){
        String name;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("Getting id for user");

            String sql = "SELECT username FROM Users WHERE id = '"+userid+"'";

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                name = set.getString("username");
                System.out.println("username: "+name);
            }else{
                System.out.println("no id for such user");
                name = "";
            }

        }catch (Exception e){
            e.printStackTrace();
            name = "";
        }

        return name;

    }

    public String getName(int userid){
        String name = "";

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("Getting firstName for user");

            String sql = "SELECT firstName, accountType FROM Users WHERE id = '"+userid+"'";

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                System.out.println("found name with accounttype: " + set.getInt("accountType"));
                switch(set.getInt("accountType")){
                    case 0:
                        name = set.getString("firstName");
                        break;
                    case 1:
                        name = "Dr. " + set.getString("firstName");
                        break;
                    case 2:
                        name = "HSP " + set.getString("firstName");
                        break;
                    case 3:
                        name = "Lab Tech" + set.getString("firstName");
                        break;
                    default:
                        name = "Mr.noname";
                        break;
                }
            }else{
                System.out.println("no id for such user");
                name = "";
            }

        }catch (Exception e){
            e.printStackTrace();
            name = "";
        }

        return name;

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

    public Doctor[] getDoctorList(){
        try {
            Doctor[] doctors = null;

            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            String sqlForCount = "SELECT COUNT(*) AS count FROM Users WHERE accountType = 1";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");

                doctors = new Doctor[count];

                String sql = "SELECT id, firstName FROM Users WHERE accountType = 1";

                ResultSet entrySet = statement.executeQuery(sql);

                entrySet.next();

                for (int i = 0; i < count; i++) {
                    doctors[i] = new Doctor(entrySet.getInt("id"),entrySet.getString("firstName"));
                    entrySet.next();
                }
            }
            return doctors;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public HealthCareConditionEntry[] getHealthConditionEntries(int userID){

        //Return all hcc entries of the given user's hcc entries

        HealthCareConditionEntry[] entries;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting health care entries");

            String sqlForCount = "SELECT COUNT(*) AS count FROM HealthCareConditions WHERE userID = "+"'"+userID+"'";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" hcc entires");

                entries = new HealthCareConditionEntry[count];

                String sql = "SELECT id, userID, info FROM HealthCareConditions WHERE userID = "+"'"+userID+"'";

                ResultSet entrySet = statement.executeQuery(sql);

                entrySet.next();

                for (int i = 0; i < count; i++) {
                    int userIDFromServer = entrySet.getInt("userID");
                    userID = entrySet.getInt("userID");
                    String info = entrySet.getString("info");

                    System.out.println(userIDFromServer + " " + info);

                    HealthCareConditionEntry entry = new HealthCareConditionEntry(userID, info);

                    entries[i] = entry;

                    entrySet.next();
                }

            }else {
                System.out.println("no appointments");
                entries = null;
            }

            return entries;

        }catch (Exception e){
            e.printStackTrace();
            entries = null;
            return entries;
        }
    }

    public void createHealthConditionEntry(HealthCareConditionEntry entry){

        try {
            System.out.println("inserting records into user table");

            int userID = entry.userID;
            String info = entry.info;

            String sql = "INSERT INTO HealthCareConditions " +
                    "VALUES ('0',"+ "'"+userID+"', "+"'"+info+"'" +")";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);


        }catch (Exception e){
            System.out.println("failed to insert records with error: ");
            e.printStackTrace();
        }

    }

    public Appointment[] getAppointmentList(int userID){

        //Return all appointments of the given user's appointment list

        Appointment[] appointments;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting appointments");

            String sqlForCount = "SELECT COUNT(*) AS count FROM Appointments WHERE userID = "+"'"+userID+"'";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" appointments");

                appointments = new Appointment[count];

                String sql = "SELECT id, userID, info, doctorID FROM Appointments WHERE userID = "+"'"+userID+"'";

                ResultSet appointmentSet = statement.executeQuery(sql);

                appointmentSet.next();

                for (int i = 0; i < count; i++) {
                    System.out.println(appointmentSet.getInt("id"));
                    int appointmentIDFromServer = appointmentSet.getInt("id");
                    int userIDFromServer = appointmentSet.getInt("userID");
                    String info = appointmentSet.getString("info");
                    int doctorIDFromServer = appointmentSet.getInt("doctorID");

                    System.out.println(userIDFromServer + " " + info);

                    Appointment appointment = new Appointment(userIDFromServer, info, appointmentIDFromServer, doctorIDFromServer);  //TODO update Appointment here with appropriate doctor field once created

                    appointments[i] = appointment;

                    appointmentSet.next();
                }
            }else {
                System.out.println("no appointments");
                appointments = null;
            }

            return appointments;

        }catch (Exception e){
            e.printStackTrace();
            appointments = null;
            return appointments;
        }


    }

    public Appointment[] getDoctorAppointmentList(int doctorID){

        //Return all appointments of the given user's appointment list

        Appointment[] appointments;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting appointments");

            String sqlForCount = "SELECT COUNT(*) AS count FROM Appointments WHERE doctorID = "+"'"+doctorID+"'";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" appointments");

                appointments = new Appointment[count];

                String sql = "SELECT id, userID, info, doctorID FROM Appointments WHERE doctorID = "+"'"+doctorID+"'";

                ResultSet appointmentSet = statement.executeQuery(sql);

                appointmentSet.next();

                for (int i = 0; i < count; i++) {
                    System.out.println(appointmentSet.getInt("id"));
                    int appointmentIDFromServer = appointmentSet.getInt("id");
                    int userIDFromServer = appointmentSet.getInt("userID");
                    String info = appointmentSet.getString("info");
                    int doctorIDFromServer = appointmentSet.getInt("doctorID");

                    System.out.println(userIDFromServer + " " + info);

                    Appointment appointment = new Appointment(userIDFromServer, info, appointmentIDFromServer, doctorIDFromServer);

                    appointments[i] = appointment;

                    appointmentSet.next();
                }
            }else {
                System.out.println("no appointments");
                appointments = null;
            }

            return appointments;

        }catch (Exception e){
            e.printStackTrace();
            appointments = null;
            return appointments;
        }


    }

    public void createAppointment(Appointment appointment){

        try {
            System.out.println("inserting new appointment into appointment table");

            int userID = appointment.userID;
            String info = appointment.info;
            int doctorID = appointment.doctorID;
            System.out.println(doctorID);

            String sql = "INSERT INTO Appointments VALUES ('0', '"+userID+"', '"+info+"', '"+doctorID+"')";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to insert appointment with error: ");
            e.printStackTrace();
        }

    }

    public void removeAppointment(int appointmentID){
        try {
            System.out.println("removing appointment id: '" + appointmentID +"'");

            String sql = "DELETE FROM Appointments WHERE id=" + "'" + appointmentID + "'";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);


        }catch (Exception e){
            System.out.println("failed to remove appointment with error: ");
            e.printStackTrace();
        }
    }

    public String getMedicalHistory(int userID){

        String medhis = "";

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting user medical history");

            String sql = "SELECT medicalHistory FROM Users WHERE id = '"+userID+"'";

            ResultSet set = statement.executeQuery(sql);

            if (set.next()) {
                medhis = set.getString("medicalHistory");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Not connecting to server");
            medhis = "No connection to server...";
        }
        return medhis;
    }

    public void setMedicalHistory(int userID, String medicalHistory){

        try {

            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("updating ");

            String sql = "UPDATE Users SET medicalHistory = " + "'" + medicalHistory + "'" + "WHERE id = " + userID;
            System.out.println(sql);

            statement.executeUpdate(sql);

        }catch (Exception e){
            System.out.println("failed to updated medical history with error: ");
            e.printStackTrace();
        }


    }

    public Prescription[] getPrescriptionList(int userID){

        //Return all prescriptions of the given user's prescription list

        Prescription[] prescriptions;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting prescriptions");

            String sqlForCount = "SELECT COUNT(*) AS count FROM Prescriptions WHERE userID = "+"'"+userID+"'";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" prescriptions");

                prescriptions = new Prescription[count];

                String sql = "SELECT id, userID, info FROM Prescriptions WHERE userID = "+"'"+userID+"'";

                ResultSet prescriptionSet = statement.executeQuery(sql);

                prescriptionSet.next();

                for (int i = 0; i < count; i++) {
                    int prescriptionIDFromServer = prescriptionSet.getInt("id");
                    int userIDFromServer = prescriptionSet.getInt("userID");
                    String info = prescriptionSet.getString("info");

                    Prescription prescription = new Prescription(userIDFromServer, info, prescriptionIDFromServer);

                    prescriptions[i] = prescription;

                    prescriptionSet.next();
                }
            }else {
                System.out.println("no prescriptions");
                prescriptions = null;
            }

            return prescriptions;

        }catch (Exception e){
            e.printStackTrace();
            prescriptions = null;
            return prescriptions;
        }
    }

    public void createPrescription(Prescription prescription){
        try {
            System.out.println("inserting new prescription into prescriptions table");

            int userID = prescription.userID;
            String info = prescription.info;

            String sql = "INSERT INTO Prescriptions VALUES ('0', '"+userID+"', '"+info+"')";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to insert prescription with error: ");
            e.printStackTrace();
        }
    }

    public void removePrescription(int prescriptionID){
        try {
            System.out.println("removing prescription id: '" + prescriptionID +"'");

            String sql = "DELETE FROM Prescriptions WHERE id='" + prescriptionID + "'";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to remove prescription with error: ");
            e.printStackTrace();
        }
    }

    public LabWork[] getLabWorkList(int userID){

        //Return all labworks of the given user's labwork list

        LabWork[] labWorks;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting labworks");

            String sqlForCount = "SELECT COUNT(*) AS count FROM LabWork WHERE userID = '"+userID+"'";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" labworks");

                labWorks = new LabWork[count];

                String sql = "SELECT id, userID, info FROM LabWork WHERE userID = '"+userID+"'";

                ResultSet labworkSet = statement.executeQuery(sql);

                labworkSet.next();

                for (int i = 0; i < count; i++) {
                    int labworkIDFromServer = labworkSet.getInt("id");
                    int userIDFromServer = labworkSet.getInt("userID");
                    String info = labworkSet.getString("info");

                    LabWork labWork = new LabWork(userIDFromServer, info, labworkIDFromServer);

                    labWorks[i] = labWork;

                    labworkSet.next();
                }
            }else {
                System.out.println("no prescriptions");
                labWorks = null;
            }

            return labWorks;

        }catch (Exception e){
            e.printStackTrace();
            labWorks = null;
            return labWorks;
        }
    }

    public void createLabWork(LabWork labWork){
        try {
            System.out.println("inserting new labWork into LabWork table");

            int userID = labWork.userID;
            String info = labWork.info;

            String sql = "INSERT INTO LabWork VALUES ('0', '"+userID+"', '"+info+"')";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to create labWork with error: ");
            e.printStackTrace();
        }
    }

    public void updateLabWork(LabWork labWork){
        try {
            System.out.println("updating info of LabWork id: '" + labWork.labWorkID +"'");

            String sql = "UPDATE LabWork SET info = '" + labWork.info + "' WHERE id = " + labWork.labWorkID;
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to update labWork with error: ");
            e.printStackTrace();
        }
    }

    public String getLabWork(int labWorkID){
        try {
            String sql = "SELECT info FROM LabWork WHERE id = '" + labWorkID + "'";

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();

            ResultSet set = statement.executeQuery(sql);

            if (set.next()) {
                return set.getString("info");
            }
            System.out.println("failed to get labWork info");
            return null;
        }catch(Exception e){
            System.out.println("failed to connect to get labWork info with error: ");
            e.printStackTrace();
            return null;
        }
    }

    public void removeLabWork(int labworkID){
        try {
            System.out.println("removing labwork id: '" + labworkID +"'");

            String sql = "DELETE FROM LabWork WHERE id='" + labworkID + "'";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to remove labwork with error: ");
            e.printStackTrace();
        }
    }

    public Patient[] searchPatients(String substring){

        //Return all patient users with name's containing the input substring

        Patient[] patients;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            String sqlForCount = "SELECT COUNT(*) AS count FROM Users WHERE firstName LIKE '%"+substring+"%' AND accountType=0";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");

                patients = new Patient[count];

                String sql = "SELECT id, firstName FROM Users WHERE  firstName LIKE '%"+substring+"%' AND accountType=0";

                ResultSet patientSet = statement.executeQuery(sql);

                patientSet.next();

                for (int i = 0; i < count; i++) {
                    String firstNameFromServer = patientSet.getString("firstName");
                    int userIDFromServer = patientSet.getInt("id");

                    Patient patient = new Patient(firstNameFromServer,userIDFromServer);

                    patients[i] = patient;

                    patientSet.next();
                }
            }else {
                System.out.println("no results");
                patients = null;
            }

            return patients;

        }catch (Exception e){
            e.printStackTrace();
            patients = null;

            return patients;
        }
    }

    public void createAlert(Alert alert){
        try {System.out.println("inserting new alert into Alerts table");

            int userID = alert.userID;
            String info = alert.info;

            String sql = "INSERT INTO Alerts VALUES ('0', '"+userID+"', '"+info+"')";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to create alert with error: ");
            e.printStackTrace();
        }
    }

    public Alert[] getAlerts(){
        //Return all alerts

        Alert[] alerts;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting alerts");

            String sqlForCount = "SELECT COUNT(*) AS count FROM Alerts";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" alerts");

                alerts = new Alert[count];

                String sql = "SELECT id, userID, info FROM Alerts";

                ResultSet alertSet = statement.executeQuery(sql);

                alertSet.next();

                for (int i = 0; i < count; i++) {
                    int labworkIDFromServer = alertSet.getInt("id");
                    int userIDFromServer = alertSet.getInt("userID");
                    String info = alertSet.getString("info");

                    Alert alert = new Alert(userIDFromServer, info, labworkIDFromServer);

                    alerts[i] = alert;

                    alertSet.next();
                }
            }else {
                System.out.println("no prescriptions");
                alerts = null;
            }

            return alerts;

        }catch (Exception e){
            e.printStackTrace();
            alerts = null;
            return alerts;
        }
    }

    public void removeAlert(int alertID){
        try {
            System.out.println("removing alert id: '" + alertID +"'");

            String sql = "DELETE FROM Alerts WHERE id='" + alertID + "'";
            System.out.println(sql);

            Connection userConnection = getUserConnection();

            Statement statement = userConnection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("failed to remove alert with error: ");
            e.printStackTrace();
        }
    }

    public int getDashboardType(int userID){
        //return account type
        int accountType;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting account type from id");

            String sql = "SELECT accountType FROM Users WHERE id = "+"'"+userID+"'";
            System.out.println(userID + "\n" + sql);

            ResultSet set = statement.executeQuery(sql);

            if (set.next()){
                accountType = set.getInt("accountType");
                System.out.println("account type: " + accountType);

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

    public int getHSPCount(){
        try {
            //Connection
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            //HSP Count
            String sqlForCount = "SELECT COUNT(*) AS count FROM Users WHERE accountType = 2";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()) {
                return setCount.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public int getLabTechCount(){
        try {
            //Connection
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            //LabTech Count
            String sqlForCount = "SELECT COUNT(*) AS count FROM Users WHERE accountType = 3";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()) {
                return setCount.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public Patient[] getPatientsAndInsurace(){
        Patient[] patients;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            String sqlForCount = "SELECT COUNT(*) AS count FROM Users WHERE accountType = 0";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");

                patients = new Patient[count];

                String sql = "SELECT id, firstName, insurance FROM Users WHERE accountType = 0";

                ResultSet patientSet = statement.executeQuery(sql);

                patientSet.next();

                for (int i = 0; i < count; i++) {
                    String firstNameFromServer = patientSet.getString("firstName");
                    int userIDFromServer = patientSet.getInt("id");
                    String insuranceFromServer = patientSet.getString("insurance");

                    Patient patient = new Patient(firstNameFromServer,userIDFromServer,insuranceFromServer);

                    patients[i] = patient;

                    patientSet.next();
                }
            }else {
                System.out.println("no results");
                patients = null;
            }

            return patients;

        }catch (Exception e){
            e.printStackTrace();
            patients = null;

            return patients;
        }
    }

    public HealthCareConditionEntry[] getAllHealthConditionEntries(){

        //Return all hcc entries

        HealthCareConditionEntry[] entries;

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("getting health care entries");

            String sqlForCount = "SELECT COUNT(*) AS count FROM HealthCareConditions";
            ResultSet setCount = statement.executeQuery(sqlForCount);
            if (setCount.next()){
                int count = setCount.getInt("count");
                System.out.println(count +" hcc entires");

                entries = new HealthCareConditionEntry[count];

                String sql = "SELECT id, userID, info FROM HealthCareConditions";

                ResultSet entrySet = statement.executeQuery(sql);

                entrySet.next();

                for (int i = 0; i < count; i++) {
                    int userIDFromServer = entrySet.getInt("userID");
                    int userID = entrySet.getInt("userID");
                    String info = entrySet.getString("info");

                    System.out.println(userIDFromServer + " " + info);

                    HealthCareConditionEntry entry = new HealthCareConditionEntry(userID, info);

                    entries[i] = entry;

                    entrySet.next();
                }

            }else {
                System.out.println("no hcc");
                entries = null;
            }

            return entries;

        }catch (Exception e){
            e.printStackTrace();
            entries = null;
            return entries;
        }
    }

}
