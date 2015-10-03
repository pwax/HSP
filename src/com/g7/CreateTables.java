package com.g7;
import java.sql.*;

/**
 * Created by kevin on 10/3/15.
 */
public class CreateTables {

    //Create tables for SQL database
    public void createUserTable() throws SQLException {
        String create =
                "CREATE TABLE User " +
                        "(userId INTEGER NOT NULL, " +
                        "firstName VARCHAR(15) NOT NULL, " +
                        "lastName VARCHAR(15) NOT NULL, " +
                        "username VARCHAR(15) NOT NULL, " +
                        "password VARCHAR(15) NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createPatientUserTable() throws SQLException {
        String create =
                "CREATE TABLE PatientUser " +
                        "(userId INTEGER NOT NULL, " +
                        "address VARCHAR(100) NOT NULL, " +
                        "healthCareInfo VARCHAR(100) NOT NULL, " +
                        "SSN INTEGER NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createPhysicianTable() throws SQLException {
        String create =
                "CREATE TABLE Physician " +
                        "(userId INTEGER NOT NULL, " +
                        "specialization VARCHAR(15) NOT NULL, " +
                        "yearsOfExperience INTEGER NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createTableStaffNurse() throws SQLException {
        String create =
                "CREATE TABLE StaffNurse " +
                        "(userId INTEGER NOT NULL, " +
                        "department VARCHAR(15) NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createTableLabTech() throws SQLException {
        String create =
                "CREATE TABLE LabTech " +
                        "(userId INTEGER NOT NULL, " +
                        "availableLabTech INTEGER NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createTableMedicalHistory() throws SQLException {
        String create =
                "CREATE TABLE MedicalHistory " +
                        "(userId INTEGER NOT NULL, " +
                        "allergies boolean NOT NULL" +
                        "allergiesPresent VARCHAR(50) NOT NULL, " +
                        "currentCondition INTEGER NOT NULL, " +
                        "numberOfSurgeries INTEGER NOT NULL, " +
                        "numLabTests INTEGER NOT NULL, " +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }*/
    }

    public void createAppointments() throws SQLException {
        String create =
                "CREATE TABLE Appointments " +
                        "(userId INTEGER NOT NULL, " +
                        "physician INTEGER NOT NULL, " +
                        "time VARCHAR(10) NOT NULL" +
                        "date VARCHAR(10) NOT NULL" +
                        "PRIMARY KEY (userId))";
/*
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/
    }
}
