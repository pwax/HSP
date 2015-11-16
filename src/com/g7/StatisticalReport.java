package com.g7;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.sql.*;

/**
 * Created by Kunhee on 11/1/2015.
 */
public class StatisticalReport extends JFrame{
    private JPanel contentPane;
    private Color backgroundColor = new Color(211,216,255);
    private Color panelColor = new Color(228,255,214);
    private int numUsers = 0;
    private int numPatients = 0;
    private int numDoctors = 0;
    private int numHSPs = 0;
    private int numLabTechs = 0;
    private int Cigna = 0;
    private int Aetna = 0;
    private int IHC = 0;
    private int Humana = 0;
    private int BCBS = 0;
    private int heart = 0;
    private int asthma = 0;
    private int cancer = 0;
    private int arthritis = 0;
    private int cough = 0;
    private int cold = 0;
    private int stroke = 0;
    private int diabetes = 0;

    public StatisticalReport(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 850, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        contentPane.setBackground(backgroundColor);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        this.setVisible(true);

        getData();

        JLabel patientStat = new JLabel("STATISTICAL REPORT GENERATION");
        patientStat.setFont(patientStat.getFont().deriveFont(20f));
        patientStat.setBounds(contentPane.getWidth() / 2 -175, contentPane.getHeight() / 2 - 250, 400, 100);
        contentPane.add(patientStat);

        JLabel userStat = new JLabel("<html>------------ USER DATA ------------<br><br>Total Users: " + numUsers + "<br><br>" + "Total Patients: " + numPatients + "<br><br>" + "Total Doctors: " + numDoctors + "<br><br>" + "Total HSPs: " + numHSPs + "<br><br>" + "Total Labtechs: " + numLabTechs + "</html>");
        userStat.setBounds(50, contentPane.getHeight() / 2 - 60, 283, 200);
        contentPane.add(userStat);

        JLabel insuranceStat = new JLabel("<html>------------ INSURANCE DATA ------------<br><br>Cigna: " + Cigna + "<br><br>Aetna: " + Aetna + "<br><br>IHC: " + IHC + "<br><br>Humana: " + Humana + "<br><br>BCBS: " + BCBS);
        insuranceStat.setBounds(contentPane.getWidth() / 3 + 30, contentPane.getHeight() / 2 - 60, 283, 200);
        contentPane.add(insuranceStat);

        JLabel medHistory = new JLabel("<html>------------ MEDICAL CONDITION DATA ------------<br><br>Heart: " + heart + "<br><br>Asthma: " + asthma + "<br><br>Cancer: " + cancer + "<br><br>Arthritis: " + arthritis + "<br><br>Cough: " + cough + "<br><br>Cold: "+ cold + "<br><br>Stroke: " + stroke + "<br><br>Diabetes: " + diabetes);
        medHistory.setBounds(contentPane.getWidth() - contentPane.getWidth()/3, contentPane.getHeight() / 2 - 175, 283, 400);
        contentPane.add(medHistory);

        contentPane.repaint();
        contentPane.revalidate();
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

    public void getData(){

        try {
            Connection userConnection = getUserConnection();
            Statement statement = userConnection.createStatement();

            System.out.println("Pulling table");

            // Extract data about users
            String sqlNumUsers = "SELECT count(*) FROM Users;";
            String sqlNumPatients = "SELECT count(*) FROM Users WHERE accountType = 0;";
            String sqlNumDoctors = "SELECT count(*) FROM Users WHERE accountType = 1;";
            String sqlNumHSPs = "SELECT count(*) FROM Users WHERE accountType = 2;";
            String sqlNumLabTechs = "SELECT count(*) FROM Users WHERE accountType = 1;";

            ResultSet set = statement.executeQuery(sqlNumUsers);
            if (set.next()) {
                System.out.println(set.getInt("count(*)"));
                numUsers = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlNumPatients);
            if (set.next()){
                numPatients = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlNumDoctors);
            if (set.next()){
                numDoctors = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlNumHSPs);
            if (set.next()){
                numHSPs = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlNumLabTechs);
            if (set.next()){
                numLabTechs = set.getInt("count(*)");
            }

            // Extract data about insurance providers
            String sqlCigna = "SELECT count(*) FROM Users WHERE insurance = 'Cigna';";
            String sqlAetna = "SELECT count(*) FROM Users WHERE insurance = 'Aetna';";
            String sqlIHC = "SELECT count(*) FROM Users WHERE insurance = 'IHC';";
            String sqlHumana = "SELECT count(*) FROM Users WHERE insurance = 'Humana';";
            String sqlBCBS = "SELECT count(*) FROM Users WHERE insurance = 'BCBS';";

            set = statement.executeQuery(sqlCigna);
            if (set.next()){
                Cigna = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlAetna);
            if (set.next()){
                Aetna = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlIHC);
            if (set.next()){
                IHC = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlHumana);
            if (set.next()){
                Humana = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlBCBS);
            if (set.next()){
                BCBS = set.getInt("count(*)");
            }

            // Extract data about medical conditions

            String sqlHeart = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%heart%';";
            String sqlAsthma = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%asthma%';";
            String sqlCancer = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%cancer%';";
            String sqlArthritis = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%arthritis%';";
            String sqlCough = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%cough%';";
            String sqlCold = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%cold%';";
            String sqlStroke = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%stroke%';";
            String sqlDiabetes = "SELECT count(*) FROM Users WHERE medicalHistory LIKE '%diabetes%';";

            set = statement.executeQuery(sqlHeart);
            if (set.next()){
                heart = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlAsthma);
            if (set.next()){
                asthma = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlCancer);
            if (set.next()){
                cancer = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlArthritis);
            if (set.next()){
                arthritis = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlCough);
            if (set.next()){
                cough = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlCold);
            if (set.next()){
                cold = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlStroke);
            if (set.next()){
                stroke = set.getInt("count(*)");
            }

            set = statement.executeQuery(sqlDiabetes);
            if (set.next()){
                diabetes = set.getInt("count(*)");
            }

        }catch (Exception e){
            System.out.println("FAILED CATCH");
            e.printStackTrace();
            numUsers = 0;
        }
    }
}
