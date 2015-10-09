package com.g7;

import sun.plugin.dom.css.RGBColor;
import sun.plugin2.util.ColorUtil;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Window class used to display all GUI
 */
public class WindowManager extends JFrame {

//region Leave all this alone
    private JPanel contentPane;
    private Color backgroundColor = new Color(211,216,255);
    private Color panelColor = new Color(228,255,214);

    //Creates main window
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
//endregion

//region Example pages in here
    //Show Login page
    public void ShowLogin(){
        System.out.print("showing login\n");
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content panel

        //Title
        JLabel titleLabel = new JLabel("Patient Health Care System");
        titleLabel.setBounds(contentPane.getWidth() / 2 - 75, contentPane.getHeight() / 2 - 100, 200, 14);
        contentPane.add(titleLabel);

        //Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(contentPane.getWidth() / 2 - 175, contentPane.getHeight() / 2 - 60, 66, 14);
        contentPane.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 60, 216, 20);
        contentPane.add(usernameField);
        //Not sure if necessary? Looked it up and tried without and it works fine and doesn't seem to effect anything
        //usernameField.setColumns(10);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(contentPane.getWidth() / 2 - 175, contentPane.getHeight() / 2 - 10, 66, 14);
        contentPane.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 10, 216, 20);
        contentPane.add(passwordField);
        //Not sure if necessary? Looked it up and tried without and it works fine and doesn't seem to effect anything
        //passwordField.setColumns(10);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ShowRegister();
            }
        });
        registerButton.setBounds((contentPane.getWidth() / 9) * 7, (contentPane.getHeight() / 7) * 6, 100, 25);
        contentPane.add(registerButton);

        JLabel invalidinputLabel = new JLabel("Invalid input, try again");
        invalidinputLabel.setForeground(Color.RED);
        invalidinputLabel.setBounds(contentPane.getWidth() / 2 - 60, contentPane.getHeight() / 2 + 25, 200, 14);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Check correct input to login
                switch (usernameField.getText()){
                    case ("p"):
                        ShowPatientDashboard(1);
                        break;
                    case ("d"):
                        ShowDoctorDashboard(2);
                        break;
                    default:
                        contentPane.remove(invalidinputLabel);

                        contentPane.add(invalidinputLabel);

                        //Reapply panel
                        contentPane.repaint();
                        contentPane.revalidate();
                        break;
                }
            }
        });
        loginButton.setBounds(contentPane.getWidth() / 2 - 50, contentPane.getHeight() / 2 + 50, 100, 25);
        contentPane.add(loginButton);

        //Reapply panel
        contentPane.repaint();
        contentPane.revalidate();
    }

    //Show Registration page
    public void ShowRegister(){
        System.out.print("showing register\n");

        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content pane

        //First name
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 200, 140, 14);
        contentPane.add(fnameLabel);

        JTextField fnameField = new JTextField();
        fnameField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 200, 116, 20);
        contentPane.add(fnameField);
        fnameField.setColumns(10);

        //Last Name
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 160, 140, 14);
        contentPane.add(lnameLabel);

        JTextField lnameField = new JTextField();
        lnameField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 160, 116, 20);
        contentPane.add(lnameField);
        lnameField.setColumns(10);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 120, 140, 14);
        contentPane.add(passwordLabel);

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 120, 116, 20);
        contentPane.add(passwordField);

        //Confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 80, 140, 14);
        contentPane.add(confirmPasswordLabel);

        JTextField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 80, 116, 20);
        contentPane.add(confirmPasswordField);

        //Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 40, 140, 14);
        contentPane.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 40, 116, 20);
        contentPane.add(emailField);
        emailField.setColumns(10);

        //Phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2, 140, 14);
        contentPane.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2, 116, 20);
        contentPane.add(phoneField);
        phoneField.setColumns(10);

        //Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 + 40, 140, 14);
        contentPane.add(accountTypeLabel);

        JRadioButton patientRadioButton = new JRadioButton("Patient");
        patientRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 40, 116, 20);
        contentPane.add(patientRadioButton);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 70, 116, 20);
        contentPane.add(doctorRadioButton);

        JRadioButton nurseRadioButton = new JRadioButton("Nurse");
        nurseRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 100, 116, 20);
        contentPane.add(nurseRadioButton);

        JRadioButton labtechRadioButton = new JRadioButton("Lab Tech");
        labtechRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 130, 116, 20);
        contentPane.add(labtechRadioButton);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(patientRadioButton);
        accountTypeGroup.add(doctorRadioButton);
        accountTypeGroup.add(nurseRadioButton);
        accountTypeGroup.add(labtechRadioButton);

        //Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ShowLogin();
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 125, contentPane.getHeight() / 2 + 175, 100, 25);
        contentPane.add(backButton);

        //Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int accountType;

                //Check current state of radio buttons
                if(patientRadioButton.isSelected()){
                    accountType = 0;
                }else if(doctorRadioButton.isSelected()){
                    accountType = 1;
                }else if(nurseRadioButton.isSelected()){
                    accountType = 2;
                }else{
                    accountType = 3;
                }

                //Apply register check and completion here
                ShowLogin();
            }
        });
        registerButton.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 175, 100, 25);
        contentPane.add(registerButton);

        //Reapply panel
        contentPane.repaint();
        contentPane.revalidate();
    }

    //Show Patient Dashboard
    public void ShowPatientDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = new JLabel("Patient Dashboard");
        titleLabel.setBounds(contentPane.getWidth() / 2 - 50, 25, 200, 14);
        contentPane.add(titleLabel);

        JButton editinfoButton = new JButton("Edit Information");
        editinfoButton.setBounds((contentPane.getWidth() / 4) * 0 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        editinfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Edit Info
                ShowEditInfo(accountid, 0);
            }
        });
        contentPane.add(editinfoButton);

        JButton healthconditionButton = new JButton("Health Condition");
        healthconditionButton.setBounds((contentPane.getWidth() / 4) * 2 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        healthconditionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Update Health Conditions
                ShowHealthCondition(accountid);
            }
        });
        contentPane.add(healthconditionButton);

        JButton medicalhistoryButton = new JButton("Medical History");
        medicalhistoryButton.setBounds((contentPane.getWidth() / 4) * 0 + 50, (contentPane.getHeight() / 4) * 2, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        medicalhistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //MMedical History
                ShowMedicalHistory(accountid);
            }
        });
        contentPane.add(medicalhistoryButton);

        JButton scheduleappointmentButton = new JButton("Schedule Appointment");
        scheduleappointmentButton.setBounds((contentPane.getWidth() / 4) * 2 + 50, (contentPane.getHeight() / 4) * 2, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        scheduleappointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Schedule Appointment
                ShowScheduleAppointment(accountid);
            }
        });
        contentPane.add(scheduleappointmentButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds((contentPane.getWidth() / 9) * 7, (contentPane.getHeight() / 7) * 6, 100, 25);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Logout
                ShowLogin();
            }
        });
        contentPane.add(logoutButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Edit Info page
    public void ShowEditInfo(int accountid, int dashboardType){
        contentPane.removeAll();

        //First name
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 200, 140, 14);
        contentPane.add(fnameLabel);

        JTextField fnameField = new JTextField();
        fnameField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 200, 116, 20);
        contentPane.add(fnameField);
        fnameField.setColumns(10);

        //Last Name
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 160, 140, 14);
        contentPane.add(lnameLabel);

        JTextField lnameField = new JTextField();
        lnameField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 160, 116, 20);
        contentPane.add(lnameField);
        lnameField.setColumns(10);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 120, 140, 14);
        contentPane.add(passwordLabel);

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 120, 116, 20);
        contentPane.add(passwordField);

        //Confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 80, 140, 14);
        contentPane.add(confirmPasswordLabel);

        JTextField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 80, 116, 20);
        contentPane.add(confirmPasswordField);

        //Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 - 40, 140, 14);
        contentPane.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 - 40, 116, 20);
        contentPane.add(emailField);
        emailField.setColumns(10);

        //Phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2, 140, 14);
        contentPane.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2, 116, 20);
        contentPane.add(phoneField);
        phoneField.setColumns(10);

        //Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(contentPane.getWidth() / 2 - 140, contentPane.getHeight() / 2 + 40, 140, 14);
        contentPane.add(accountTypeLabel);

        JRadioButton patientRadioButton = new JRadioButton("Patient");
        patientRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 40, 116, 20);
        contentPane.add(patientRadioButton);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 70, 116, 20);
        contentPane.add(doctorRadioButton);

        JRadioButton nurseRadioButton = new JRadioButton("Nurse");
        nurseRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 100, 116, 20);
        contentPane.add(nurseRadioButton);

        JRadioButton labtechRadioButton = new JRadioButton("Lab Tech");
        labtechRadioButton.setBounds(contentPane.getWidth() / 2 + 10, contentPane.getHeight() / 2 + 130, 116, 20);
        contentPane.add(labtechRadioButton);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(patientRadioButton);
        accountTypeGroup.add(doctorRadioButton);
        accountTypeGroup.add(nurseRadioButton);
        accountTypeGroup.add(labtechRadioButton);

        //Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ReturnToCurrentDashboard(accountid, dashboardType);
            }
        });
        backButton.setBounds((contentPane.getWidth() / 9) * 1, (contentPane.getHeight() / 7) * 6, 100, 25);
        contentPane.add(backButton);

        //Accept Changes
        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Save changes

                //Return to patient page
                ShowPatientDashboard(accountid);
            }
        });
        acceptButton.setBounds((contentPane.getWidth() / 9) * 7, (contentPane.getHeight() / 7) * 6, 100, 25);
        contentPane.add(acceptButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Health Condition page
    public void ShowHealthCondition(int accountid){
        contentPane.removeAll();



        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Medical History page
    public void ShowMedicalHistory(int accountid){
        contentPane.removeAll();



        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Schedule Appointment page
    public void ShowScheduleAppointment(int accountid){
        contentPane.removeAll();



        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Doctor Dashboard
    public void ShowDoctorDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = new JLabel("Doctor Dashboard");
        titleLabel.setBounds(contentPane.getWidth() / 2 - 50, 25, 200, 14);
        contentPane.add(titleLabel);

        JButton editinfoButton = new JButton("Edit Information");
        editinfoButton.setBounds((contentPane.getWidth() / 4) * 0 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        editinfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Edit Info
                ShowEditInfo(accountid, 1);
            }
        });
        contentPane.add(editinfoButton);

        JButton viewappointmentsButton = new JButton("View Appointments");
        viewappointmentsButton.setBounds((contentPane.getWidth() / 4) * 2 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        contentPane.add(viewappointmentsButton);

        JButton patientsearchButton = new JButton("Patient Search");
        patientsearchButton.setBounds((contentPane.getWidth() / 2) + 50 - (contentPane.getWidth() / 4), (contentPane.getHeight() / 4) * 2, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        contentPane.add(patientsearchButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds((contentPane.getWidth() / 9) * 7, (contentPane.getHeight() / 7) * 6, 100, 25);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Logout
                ShowLogin();
            }
        });
        contentPane.add(logoutButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    public void ReturnToCurrentDashboard(int accountid, int dashboardType){
        switch(dashboardType){
            case (0):
                ShowPatientDashboard(accountid);
                break;
            case (1):
                ShowDoctorDashboard(accountid);
                break;
            case(2):
                //ShowNurseDashboard(accountid);
                break;
            case(3):
                //ShowLabtechDashboard(accountid);
                break;
            default:
                System.out.print("No dashboard of type:" + dashboardType);
        }
    }
}
