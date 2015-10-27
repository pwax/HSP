package com.g7;

import com.sun.xml.internal.bind.v2.TODO;
import sun.plugin.dom.css.RGBColor;
import sun.plugin2.util.ColorUtil;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Window class used to display all GUI
 */
public class WindowManager extends JFrame {

    //region Initialization
    private JPanel contentPane;
    private Color backgroundColor = new Color(211,216,255);
    private Color panelColor = new Color(228,255,214);

    //DEBUG
    private int test = 0;

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

    //Show Login page
    public void ShowLogin(){
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

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(contentPane.getWidth() / 2 - 175, contentPane.getHeight() / 2 - 10, 66, 14);
        contentPane.add(passwordLabel);

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 10, 216, 20);
        contentPane.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ShowRegister();
            }
        });
        registerButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
        contentPane.add(registerButton);

        JLabel invalidinputLabel = new JLabel("Invalid input, try again");
        invalidinputLabel.setForeground(Color.RED);
        invalidinputLabel.setBounds(contentPane.getWidth() / 2 - 60, contentPane.getHeight() / 2 + 25, 200, 14);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Check correct input to login
                boolean userexists = false;
                try {
                    userexists = BackEndManager.sharedManager().doesUserExist(usernameField.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(userexists){//usernameExists){
                    boolean passwordMatches = false;
                    try {
                        passwordMatches = BackEndManager.sharedManager().isPassword(usernameField.getText(),passwordField.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(passwordMatches){//passwordWorks){
                        //Get ID
                        int id = 0;
                        try {
                            id = BackEndManager.sharedManager().getUserID(usernameField.getText());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //Get account type
                        int accountType = 0;
                        try {
                            accountType = BackEndManager.sharedManager().getDashboardType(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //Show dashboard
                        ReturnToCurrentDashboard(id, accountType);
                    }else{
                        contentPane.add(invalidinputLabel);
                        contentPane.repaint();
                        contentPane.revalidate();
                    }
                }else{
                    contentPane.add(invalidinputLabel);
                    contentPane.repaint();
                    contentPane.revalidate();
                }
            }
        });
        loginButton.setBounds(contentPane.getWidth() / 2 - 50, contentPane.getHeight() / 2 + 50, 100, 30);
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
        fnameLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 - 200, 140, 14);
        contentPane.add(fnameLabel);

        JTextField fnameField = new JTextField();
        fnameField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 200, 116, 20);
        contentPane.add(fnameField);

        //Last Name
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 - 160, 140, 14);
        contentPane.add(lnameLabel);

        JTextField lnameField = new JTextField();
        lnameField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 160, 116, 20);
        contentPane.add(lnameField);

        //Username
        //Password
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 - 120, 140, 14);
        contentPane.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 120, 116, 20);
        contentPane.add(usernameField);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 - 80, 140, 14);
        contentPane.add(passwordLabel);

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 80, 116, 20);
        contentPane.add(passwordField);

        //Confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 - 40, 140, 14);
        contentPane.add(confirmPasswordLabel);

        JTextField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 - 40, 116, 20);
        contentPane.add(confirmPasswordField);

        //Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2, 140, 14);
        contentPane.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2, 116, 20);
        contentPane.add(emailField);

        //Phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 + 40, 140, 14);
        contentPane.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 + 40, 116, 20);
        contentPane.add(phoneField);

        //Patient SSN
        JLabel ssnLabel = new JLabel("Social Security Number");
        ssnLabel.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 200, 140, 14);
        contentPane.add(ssnLabel);

        JTextField ssnField = new JTextField();
        ssnField.setBounds(contentPane.getWidth() / 2 + 230, contentPane.getHeight() / 2 - 200, 116, 20);
        contentPane.add(ssnField);

        //Patient Insurance
        JLabel insuranceLabel = new JLabel("Insurance Provider");
        insuranceLabel.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 160, 140, 14);
        contentPane.add(insuranceLabel);

        JTextField insuranceField = new JTextField();
        insuranceField.setBounds(contentPane.getWidth() / 2 + 230, contentPane.getHeight() / 2 - 160, 116, 20);
        contentPane.add(insuranceField);

        //Patient Medical History
        JLabel medHistoryLabel = new JLabel("Input any medical history below");
        medHistoryLabel.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 120, 200, 14);
        contentPane.add(medHistoryLabel);

        JTextArea medHistoryTextArea = new JTextArea();
        medHistoryTextArea.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 100, contentPane.getWidth() / 2 - 110, contentPane.getHeight() / 2 - 5);
        medHistoryTextArea.setWrapStyleWord(true);
        medHistoryTextArea.setLineWrap(true);
        medHistoryTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        contentPane.add(medHistoryTextArea);

        //Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(contentPane.getWidth() / 2 - 240, contentPane.getHeight() / 2 + 80, 140, 14);
        contentPane.add(accountTypeLabel);

        JRadioButton patientRadioButton = new JRadioButton("Patient");
        patientRadioButton.setBounds(contentPane.getWidth() / 2 - 210, contentPane.getHeight() / 2 + 100, 116, 20);
        patientRadioButton.setOpaque(false);
        patientRadioButton.setSelected(true);
        patientRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                contentPane.add(ssnLabel);
                contentPane.add(ssnField);
                contentPane.add(insuranceLabel);
                contentPane.add(insuranceField);
                contentPane.add(medHistoryTextArea);
                contentPane.add(medHistoryLabel);

                contentPane.repaint();
                contentPane.revalidate();
            }
        });
        contentPane.add(patientRadioButton);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setBounds(contentPane.getWidth() / 2 - 210, contentPane.getHeight() / 2 + 130, 116, 20);
        doctorRadioButton.setOpaque(false);
        doctorRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                contentPane.remove(ssnLabel);
                contentPane.remove(ssnField);
                contentPane.remove(insuranceLabel);
                contentPane.remove(insuranceField);
                contentPane.remove(medHistoryTextArea);
                contentPane.remove(medHistoryLabel);

                contentPane.repaint();
                contentPane.revalidate();
            }
        });
        contentPane.add(doctorRadioButton);

        JRadioButton nurseRadioButton = new JRadioButton("HSP");
        nurseRadioButton.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 + 100, 116, 20);
        nurseRadioButton.setOpaque(false);
        nurseRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                contentPane.remove(ssnLabel);
                contentPane.remove(ssnField);
                contentPane.remove(insuranceLabel);
                contentPane.remove(insuranceField);
                contentPane.remove(medHistoryTextArea);
                contentPane.remove(medHistoryLabel);

                contentPane.repaint();
                contentPane.revalidate();
            }
        });
        contentPane.add(nurseRadioButton);

        JRadioButton labtechRadioButton = new JRadioButton("Lab Tech");
        labtechRadioButton.setBounds(contentPane.getWidth() / 2 - 90, contentPane.getHeight() / 2 + 130, 116, 20);
        labtechRadioButton.setOpaque(false);
        labtechRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                contentPane.remove(ssnLabel);
                contentPane.remove(ssnField);
                contentPane.remove(insuranceLabel);
                contentPane.remove(insuranceField);
                contentPane.remove(medHistoryTextArea);
                contentPane.remove(medHistoryLabel);

                contentPane.repaint();
                contentPane.revalidate();
            }
        });
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
        backButton.setBounds(contentPane.getWidth() / 2 - 125, contentPane.getHeight() / 2 + 175, 100, 30);
        contentPane.add(backButton);

        //Register button

        JLabel emptyFieldsLabel = new JLabel("Some fields remain unfilled");
        emptyFieldsLabel.setForeground(Color.RED);
        emptyFieldsLabel.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 210, 200, 30);

        JLabel inconsistentPasswordsLabel = new JLabel("Both passwords don't match");
        inconsistentPasswordsLabel.setForeground(Color.RED);
        inconsistentPasswordsLabel.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 210, 200, 30);
        
        JLabel usernameInUseLabel = new JLabel("Username already in use");
        usernameInUseLabel.setForeground(Color.RED);
        usernameInUseLabel.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 210, 200, 30);

        JLabel incorrectSSNEntryLabel = new JLabel("Incorrect SSN entry");
        incorrectSSNEntryLabel.setForeground(Color.RED);
        incorrectSSNEntryLabel.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 210, 200, 30);

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

                contentPane.remove(emptyFieldsLabel);
                contentPane.remove(inconsistentPasswordsLabel);
                contentPane.remove(incorrectSSNEntryLabel);
                contentPane.remove(usernameInUseLabel);
                contentPane.repaint();
                contentPane.revalidate();

                if(fnameField.getText().compareTo("") != 0 && lnameField.getText().compareTo("") != 0 && usernameField.getText().compareTo("") != 0 && passwordField.getText().compareTo("") != 0 && confirmPasswordField.getText().compareTo("") != 0 && emailField.getText().compareTo("") != 0 && phoneField.getText().compareTo("") != 0){
                    if((accountType != 0) || (ssnField.getText().length() == 10 && insuranceField.getText().compareTo("") != 0)) {
                        if (confirmPasswordField.getText().compareTo(passwordField.getText()) == 0) {
                            //Check username doesn't already exist
                            boolean alreadyExists = false;

                            try {
                                alreadyExists = BackEndManager.sharedManager().doesUserExist(usernameField.getText());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (!alreadyExists) {
                                //Create new account

                                try {
                                    BackEndManager.sharedManager().registerNewUser(fnameField.getText(),lnameField.getText(),usernameField.getText(),passwordField.getText(),emailField.getText(),phoneField.getText(),accountType,ssnField.getText(),insuranceField.getText(),medHistoryTextArea.getText());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //Return to login page
                                ShowLogin();
                            } else {
                                contentPane.add(usernameInUseLabel);

                                contentPane.repaint();
                                contentPane.revalidate();
                            }
                        } else {
                            //Print Error: Passwords not matching
                            contentPane.add(inconsistentPasswordsLabel);

                            contentPane.repaint();
                            contentPane.revalidate();
                        }
                    }else{
                        if(ssnField.getText().length() != 10) {
                            //Print Error: incorrect SSN entry
                            contentPane.add(incorrectSSNEntryLabel);

                            contentPane.repaint();
                            contentPane.revalidate();
                        }else{
                            //Print Error: Empty field
                            contentPane.add(emptyFieldsLabel);

                            contentPane.repaint();
                            contentPane.revalidate();
                        }
                    }
                }else{
                    //Print Error: Empty field
                    contentPane.add(emptyFieldsLabel);

                    contentPane.repaint();
                    contentPane.revalidate();
                }
            }
        });
        registerButton.setBounds(contentPane.getWidth() / 2 + 25, contentPane.getHeight() / 2 + 175, 100, 30);
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

        JButton healthconditionButton = new JButton("Health Condition");
        healthconditionButton.setBounds((contentPane.getWidth() / 4) * 0 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        healthconditionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Update Health Conditions
                ShowHealthCondition(accountid);
            }
        });
        contentPane.add(healthconditionButton);

        JButton medicalhistoryButton = new JButton("Medical History");
        medicalhistoryButton.setBounds((contentPane.getWidth() / 4) * 2 + 50, (contentPane.getHeight() / 4) * 0 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        medicalhistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Medical History
                ShowMedicalHistory(accountid);
            }
        });
        contentPane.add(medicalhistoryButton);

        JButton scheduleappointmentButton = new JButton("Appointments");
        scheduleappointmentButton.setBounds((contentPane.getWidth() / 4) + 50, (contentPane.getHeight() / 4) * 2, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        scheduleappointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Schedule Appointment
                ShowAppointments(accountid);
            }
        });
        contentPane.add(scheduleappointmentButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
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

                //Return to dashboard
                ReturnToCurrentDashboard(accountid, dashboardType);
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

        //Ailment Label
        JLabel ailmentLabel = new JLabel("What is your issue?");
        ailmentLabel.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 6 - 40, 200, 14);
        contentPane.add(ailmentLabel);

        //Ailment Dropdown
        String[] ailmentTypes = {"Select Ailment","Addiction","Broken Limb","Burn","Concussion","Head Injury","Heart Attack","Anxiety"
                ,"Arthritis","Asthma","Bronchitis","Carpal Tunnel","Chronic Fatigue","Common Cold","Confusion"
                ,"Constipation","Dental Pain","Depression","Diarrhea","Digestive Problems","Dysentery"
                ,"Eye Problems","Fatigue","Fertility","Lower Back Pain","Menopause","Migraine","Nausea","Pain"
                ,"Pneumonia","Sleep Loss","Sore Throat","Stress","Tonsillitis","UTI","Vomiting"};

        JComboBox ailmentList = new JComboBox(ailmentTypes);
        ailmentList.setSelectedIndex(0);
        ailmentList.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 6, 300, 30);
        contentPane.add(ailmentList);

        //Severity Label
        JLabel severityLabel = new JLabel("How bad is it?");
        severityLabel.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 2 - 40, 200, 14);
        contentPane.add(severityLabel);

        //Severity Buttons
        JRadioButton oneRadioButton = new JRadioButton("1");
        oneRadioButton.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 2, 20, 40);
        oneRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        oneRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        oneRadioButton.setOpaque(false);
        contentPane.add(oneRadioButton);

        JRadioButton twoRadioButton = new JRadioButton("2");
        twoRadioButton.setBounds(contentPane.getWidth() / 4 - 60, contentPane.getHeight() / 2, 20, 40);
        twoRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        twoRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        twoRadioButton.setOpaque(false);
        contentPane.add(twoRadioButton);

        JRadioButton threeRadioButton = new JRadioButton("3");
        threeRadioButton.setBounds(contentPane.getWidth() / 4 - 20, contentPane.getHeight() / 2, 20, 40);
        threeRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        threeRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        threeRadioButton.setSelected(true);
        threeRadioButton.setOpaque(false);
        contentPane.add(threeRadioButton);

        JRadioButton fourRadioButton = new JRadioButton("4");
        fourRadioButton.setBounds(contentPane.getWidth() / 4 + 20, contentPane.getHeight() / 2, 20, 40);
        fourRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        fourRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        fourRadioButton.setOpaque(false);
        contentPane.add(fourRadioButton);

        JRadioButton fiveRadioButton = new JRadioButton("5");
        fiveRadioButton.setBounds(contentPane.getWidth() / 4 + 60, contentPane.getHeight() / 2, 20, 40);
        fiveRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        fiveRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        fiveRadioButton.setOpaque(false);
        contentPane.add(fiveRadioButton);

        ButtonGroup severityButtonGroup = new ButtonGroup();
        severityButtonGroup.add(oneRadioButton);
        severityButtonGroup.add(twoRadioButton);
        severityButtonGroup.add(threeRadioButton);
        severityButtonGroup.add(fourRadioButton);
        severityButtonGroup.add(fiveRadioButton);

        //Condition History Label
        JLabel historyLabel = new JLabel("Condition History");
        historyLabel.setBounds(contentPane.getWidth() * 3 / 4 - 150, contentPane.getHeight() / 6 - 40, 200, 14);
        contentPane.add(historyLabel);

        //Condition History Scrollpane
        HealthCareConditionEntry[] entries;
        int conditionEntries = 0;
        try {
            entries = BackEndManager.sharedManager().getHealthConditionEntries(accountid);
            conditionEntries = entries.length;

            //Create inner grid pane
            JPanel gridPane;
            if(conditionEntries <= 8) {
                gridPane = new JPanel(new GridLayout(9, 1));
            }else {
                gridPane = new JPanel(new GridLayout(conditionEntries, 1));
            }

            //Create JScrollpane
            JScrollPane scrollPane = new JScrollPane(gridPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 + 20, contentPane.getHeight() / 6, contentPane.getWidth() / 2 - 40, 400);

            for(int i=0; i < conditionEntries; i++) {

                JPanel entryPane = new JPanel();
                entryPane.setBackground(panelColor);
                entryPane.setBorder(BorderFactory.createRaisedBevelBorder());

                JTextArea entryArea = new JTextArea(2, 20);
                entryArea.setText(entries[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                entryPane.add(entryArea);

                gridPane.add(entryPane);
            }

            contentPane.add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ShowPatientDashboard(accountid);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        JLabel invalidinputLabel = new JLabel("No ailment selected");
        invalidinputLabel.setForeground(Color.RED);
        invalidinputLabel.setBounds(contentPane.getWidth() / 2 - 280, contentPane.getHeight() / 2 + 150, 150, 30);
        invalidinputLabel.setHorizontalTextPosition(JLabel.RIGHT);

        //Update button
        JButton updateButton = new JButton("Update");
        updateButton.setToolTipText("Makes new entry in Health Condition Log.");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (ailmentList.getSelectedIndex() != 0) {
                    int severityNum;

                    if(oneRadioButton.isSelected()){
                        severityNum = 1;
                    }else if(twoRadioButton.isSelected()){
                        severityNum = 2;
                    }else if(threeRadioButton.isSelected()){
                        severityNum = 3;
                    }else if(fourRadioButton.isSelected()){
                        severityNum = 4;
                    }else{
                        severityNum = 5;
                    }



                    //Create entry
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    Calendar cal = Calendar.getInstance();
                    try {
                        BackEndManager.sharedManager().createHealthConditionEntry(new HealthCareConditionEntry(accountid,"Date and Time:" + dateFormat.format(cal.getTime()) + "\nIssue:" + ailmentTypes[ailmentList.getSelectedIndex()] + "\tSeverity:" + severityNum));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if(severityNum == 5 || ailmentList.getSelectedIndex() > 0 && ailmentList.getSelectedIndex() < 7){
                        //Show Alert
                        ShowAlert(accountid);
                    }else {
                        //Reshow page
                        ShowHealthCondition(accountid);
                    }
                }else{
                    //Display necessary field entry
                    contentPane.remove(invalidinputLabel);

                    contentPane.add(invalidinputLabel);

                    contentPane.revalidate();
                    contentPane.repaint();
                }
            }
        });
        updateButton.setBounds(contentPane.getWidth() / 2 - 280, contentPane.getHeight() / 2 + 200, 100, 30);

        contentPane.add(updateButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Alert page after Update page
    public void ShowAlert(int accountid){
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content panel

        //Title
        JTextArea warning = new JTextArea("You have a serious health risk.\nCall 911 or get help immediately!");
        warning.setWrapStyleWord(true);
        warning.setLineWrap(true);
        warning.setOpaque(false);
        warning.setEditable(false);
        warning.setFocusable(false);
        warning.setBounds(contentPane.getWidth() / 2 - 75, contentPane.getHeight() / 2 - 100, 200, 100);
        contentPane.add(warning);

        JButton returnButton = new JButton("Okay");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ShowPatientDashboard(accountid);
            }
        });
        returnButton.setBounds(contentPane.getWidth() / 2 - 50, contentPane.getHeight() / 2 + 50, 100, 30);
        contentPane.add(returnButton);

        //Reapply panel
        contentPane.repaint();
        contentPane.revalidate();
    }

    //Show Schedule Appointment page
    public void ShowAppointments(int accountid){
        contentPane.removeAll();

        Appointment[] appointments;
        int appointmentCount;
        try {
            appointments = BackEndManager.sharedManager().getAppointmentList(accountid);
            appointmentCount = appointments.length;

        //Create inner grid pane
        JPanel gridPane;
        if(appointmentCount <= 5) {
            gridPane = new JPanel(new GridLayout(6, 1));
        }else {
            gridPane = new JPanel(new GridLayout(appointmentCount, 1));
        }

        //Create JScrollpane
        JScrollPane scrollPane = new JScrollPane(gridPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

        for(int i=0; i < appointmentCount; i++) {
            JPanel appointmentPane = new JPanel();
            appointmentPane.setBackground(panelColor);
            appointmentPane.setBorder(BorderFactory.createRaisedBevelBorder());
            appointmentPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

            JTextArea entryArea = new JTextArea(2, 20);
            entryArea.setText(appointments[i].info);
            entryArea.setWrapStyleWord(true);
            entryArea.setLineWrap(true);
            entryArea.setOpaque(false);
            entryArea.setEditable(false);
            entryArea.setFocusable(false);
            appointmentPane.add(entryArea);

            JButton deleteAppointmentButton = new JButton("Delete");
            deleteAppointmentButton.setBounds(appointmentPane.getWidth() * 3 / 4, 0, appointmentPane.getWidth() / 4, appointmentPane.getHeight());
            deleteAppointmentButton.putClientProperty("appointmentID", appointments[i].appointmentID);
            deleteAppointmentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    //Delete Appointment
                    try {
                        BackEndManager.sharedManager().removeAppointment((int) deleteAppointmentButton.getClientProperty("appointmentID"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Reload this window
                    ShowAppointments(accountid);
                }
            });
            appointmentPane.add(deleteAppointmentButton);

            gridPane.add(appointmentPane);
        }

        contentPane.add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Appointments Label
        JLabel labelAppointment = new JLabel("Appointments");
        labelAppointment.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 100, 50);
        contentPane.add(labelAppointment);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ReturnToCurrentDashboard(accountid, 0);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        JButton makeAppointmentButton = new JButton("Make Appointment");
        makeAppointmentButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
        makeAppointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Show Make Appointment
                ShowMakeAppointment(accountid);
            }
        });
        contentPane.add(makeAppointmentButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Add Appointment page
    public void ShowMakeAppointment(int accountid){
        contentPane.removeAll();

        JLabel datelabel = new JLabel("Select Date :");
        datelabel.setBounds(185, 25, 100, 100);
        contentPane.add(datelabel);

        String[] day= {"Date","1", "2", "3", "4", "5","6","7","8","9","10","11","12","13","14","15"
                ,"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox dayscombobox = new JComboBox(day);
        dayscombobox.setBounds(contentPane.getWidth() / 2 - 125, contentPane.getHeight() / 2 - 210, 100, 50);
        contentPane.add(dayscombobox);

        String[] month = {"Month", "January", "February", "March", "April", "May","June"
                ,"July","August","September","October","November","December"};
        JComboBox monthcombobox = new JComboBox(month);
        monthcombobox.setBounds(contentPane.getWidth() / 2 , contentPane.getHeight() / 2 - 210, 100, 50);
        contentPane.add(monthcombobox);

        String[] year = {"Year","2015","2016","2017"};
        JComboBox yearcombobox = new JComboBox(year);
        yearcombobox.setBounds(contentPane.getWidth() / 2 + 125, contentPane.getHeight() / 2 - 210, 100, 50);
        contentPane.add(yearcombobox);

        String[] time = {"Time","9 to 10","10 to 11","11 to 12", "12 to 1", "2 to 3", "3 to 4", "4 to 5"};
        JComboBox timecombobox = new JComboBox(time);
        timecombobox.setBounds(contentPane.getWidth() / 2 -275 ,contentPane.getHeight() / 2 - 25, 200,50);
        contentPane.add(timecombobox);

        //TODO get all doctor names based on availability
        String[] physicians = {"Physicians","Vimal", "Curtis", "Matus", "Bryan", "Ryan"};
        JComboBox physiciancombobox = new JComboBox(physicians);
        physiciancombobox.setBounds(contentPane.getWidth() / 2 - 275,contentPane.getHeight() / 2 + 50,200,50);
        contentPane.add(physiciancombobox);

        //Arbitrary
        String[] insurance = {"Insurance Provider", "Aetna", "Aviva", "Life Saver", "Life Care"};
        JComboBox insurancecombobox = new JComboBox(insurance);
        insurancecombobox.setBounds(contentPane.getWidth() / 2 - 275,contentPane.getHeight() / 2 + 125,200,50);
        contentPane.add(insurancecombobox);

        JLabel reasonlabel = new JLabel("Reason For Visit:");
        reasonlabel.setBounds(contentPane.getWidth() / 2 , contentPane.getHeight() / 2 - 125, 200, 200);
        contentPane.add(reasonlabel);

        JTextArea reasonTextArea = new JTextArea();
        reasonTextArea.setBounds(contentPane.getWidth() / 2 - 10, contentPane.getHeight() / 2, 280, 200);
        reasonTextArea.setWrapStyleWord(true);
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        contentPane.add(reasonTextArea);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ShowAppointments(accountid);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        //Make Button
        JLabel emptyFields = new JLabel("Some fields are empty");
        emptyFields.setForeground(Color.RED);
        emptyFields.setBounds(contentPane.getWidth() / 2, contentPane.getHeight()/2 + 225, 200, 20);

        JButton makeButton = new JButton("Make");
        makeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Check if all fields are selected
                if(dayscombobox.getSelectedIndex() != 0 && monthcombobox.getSelectedIndex() != 0 && yearcombobox.getSelectedIndex() != 0 && physiciancombobox.getSelectedIndex() !=0 && timecombobox.getSelectedIndex() != 0 && insurancecombobox.getSelectedIndex() != 0){
                    //Create appointment
                    try {
                        BackEndManager.sharedManager().createAppointment(new Appointment(accountid,new String("Date: " + year[yearcombobox.getSelectedIndex()] + "-" + month[monthcombobox.getSelectedIndex()] + "-" + day[dayscombobox.getSelectedIndex()] + "\nTime: " + time[timecombobox.getSelectedIndex()] + "\n\nDoctor: " + physicians[physiciancombobox.getSelectedIndex()]),-1,BackEndManager.sharedManager().getUserID(physicians[physiciancombobox.getSelectedIndex()])));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //return to appointments list
                    ShowAppointments(accountid);
                }else{
                    //Print Error: Empty fields
                    contentPane.add(emptyFields);

                    contentPane.repaint();
                    contentPane.revalidate();
                }
            }
        });
        makeButton.setBounds(contentPane.getWidth() / 2 + 300, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(makeButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Medical History
    public void ShowMedicalHistory(int accountid){
        contentPane.removeAll();

        //Medical History Label
        JLabel medicalhistoryLabel = new JLabel("Medical History");
        medicalhistoryLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 100, 50);
        contentPane.add(medicalhistoryLabel);

        //Medical History Text Area
        JTextArea medicalhistoryTextArea = new JTextArea();
        medicalhistoryTextArea.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);
        medicalhistoryTextArea.setWrapStyleWord(true);
        medicalhistoryTextArea.setLineWrap(true);
        medicalhistoryTextArea.setFocusable(false);
        medicalhistoryTextArea.setEditable(false);
        medicalhistoryTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        try {
            //medicalhistoryTextArea.setText(BackEndManager.sharedManager().getMedicalHistory(accountID));
        } catch (Exception e) {
            medicalhistoryTextArea.setText("No medical history found.");
            e.printStackTrace();
        }
        contentPane.add(medicalhistoryTextArea);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO
                //Save changes
                /*try {
                    BackEndManager.sharedManager().setMedicalHistory(patientAccountid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                   */

                //Return to dashboard
                ShowPatientDashboard(accountid);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Doctor Dashboard
    public void ShowDoctorDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = null;
        try {
            titleLabel = new JLabel(BackEndManager.sharedManager().getUsername(accountid) + "'s Doctor Dashboard");
        } catch (Exception e) {
            titleLabel = new JLabel("Doctor Dashboard");
            e.printStackTrace();
        }
        titleLabel.setBounds(contentPane.getWidth() / 2 - 50, 25, 200, 14);
        contentPane.add(titleLabel);

        JButton viewappointmentsButton = new JButton("View Appointments");
        viewappointmentsButton.setBounds((contentPane.getWidth() / 4) * 0 + 50, (contentPane.getHeight() / 4) * 1 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        viewappointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Show Appointments
                ShowDoctorAppointments(accountid);
            }
        });
        contentPane.add(viewappointmentsButton);

        JButton patientsearchButton = new JButton("Patient Search");
        patientsearchButton.setBounds((contentPane.getWidth() / 4) * 2 + 50, (contentPane.getHeight() / 4) * 1 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        patientsearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Patient Search
                try {
                    ShowPatientSearch(accountid, 1, BackEndManager.sharedManager().searchPatients(""));
                } catch (Exception e) {
                    ShowPatientSearch(accountid, 1, null);
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(patientsearchButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
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

    //Show Doctor Appointments
    public void ShowDoctorAppointments(int accountid){
        contentPane.removeAll();

        Appointment[] appointments;
        int appointmentCount;
        try {
            appointments = BackEndManager.sharedManager().getDoctorAppointmentList(accountid);
            appointmentCount = appointments.length;

            //Create inner grid pane
            JPanel gridPane;
            if(appointmentCount <= 5) {
                gridPane = new JPanel(new GridLayout(6, 1));
            }else {
                gridPane = new JPanel(new GridLayout(appointmentCount, 1));
            }

            //Create JScrollpane
            JScrollPane scrollPane = new JScrollPane(gridPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

            for(int i=0; i < appointmentCount; i++) {
                JPanel appointmentPane = new JPanel();
                appointmentPane.setBackground(panelColor);
                appointmentPane.setBorder(BorderFactory.createRaisedBevelBorder());
                appointmentPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

                JTextArea entryArea = new JTextArea(2, 20);
                entryArea.setText(appointments[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                appointmentPane.add(entryArea);

                JButton deleteAppointmentButton = new JButton("Delete");
                deleteAppointmentButton.setBounds(appointmentPane.getWidth() * 3 / 4, 0, appointmentPane.getWidth() / 4, appointmentPane.getHeight());
                deleteAppointmentButton.putClientProperty("appointmentID", appointments[i].appointmentID);
                deleteAppointmentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Delete Appointment
                        try {
                            BackEndManager.sharedManager().removeAppointment((int)deleteAppointmentButton.getClientProperty("appointmentID"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Reload this window
                        ShowDoctorAppointments(accountid);
                    }
                });
                appointmentPane.add(deleteAppointmentButton);

                gridPane.add(appointmentPane);
            }

            contentPane.add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Appointments Label
        JLabel labelAppointment = new JLabel("Appointments");
        labelAppointment.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 100, 50);
        contentPane.add(labelAppointment);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ReturnToCurrentDashboard(accountid, 1);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show HSP Dashboard
    public void ShowHSPDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = null;
        try {
            titleLabel = new JLabel(BackEndManager.sharedManager().getUsername(accountid) + "'s HSP Dashboard");
        } catch (Exception e) {
            titleLabel = new JLabel("HSP Dashboard");
            e.printStackTrace();
        }
        titleLabel.setBounds(contentPane.getWidth() / 2 - 50, 25, 200, 14);
        contentPane.add(titleLabel);

        JButton patientsearchButton = new JButton("Patient Search");
        patientsearchButton.setBounds((contentPane.getWidth() / 4) * 1 + 50, (contentPane.getHeight() / 4) * 1 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        patientsearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Patient Search
                try {
                    ShowPatientSearch(accountid, 2, BackEndManager.sharedManager().searchPatients(""));
                } catch (Exception e) {
                    ShowPatientSearch(accountid, 2, null);
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(patientsearchButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
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

    //Show LabTech Dashboard
    public void ShowLabtechDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = null;
        try {
            titleLabel = new JLabel(BackEndManager.sharedManager().getUsername(accountid) + "'s Labtech Dashboard");
        } catch (Exception e) {
            titleLabel = new JLabel("Labtech Dashboard");
            e.printStackTrace();
        }
        titleLabel.setBounds(contentPane.getWidth() / 2 - 50, 25, 200, 14);
        contentPane.add(titleLabel);

        JButton patientsearchButton = new JButton("Patient Search");
        patientsearchButton.setBounds((contentPane.getWidth() / 4) * 1 + 50, (contentPane.getHeight() / 4) * 1 + 50, (contentPane.getWidth() / 2) - 100, (contentPane.getHeight() / 2) - 100);
        patientsearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Patient Search
                try {
                    ShowPatientSearch(accountid, 3, BackEndManager.sharedManager().searchPatients(""));
                } catch (Exception e) {
                    ShowPatientSearch(accountid, 3, null);
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(patientsearchButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
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

    //Show patient search window
    public void ShowPatientSearch(int accountid, int dashboardType, Patient[] patientList){
        contentPane.removeAll();

        //Search Patients containing searchField substring and then redisplay

        //Search Box
        JTextField searchField = new JTextField();
        searchField.setBounds(contentPane.getWidth() / 2 + 30, contentPane.getHeight() / 2 - 235, 150, 20);
        contentPane.add(searchField);

        //Search Button
        JButton searchButton = new JButton("Search for Patient");
        searchButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 - 240, 150, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Patient[] newPatientList = BackEndManager.sharedManager().searchPatients(searchField.getText());
                    ShowPatientSearch(accountid,dashboardType,newPatientList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(searchButton);

        if(patientList != null) {
            //Create inner grid pane
            int patientCount = patientList.length;

            JPanel gridPane;
            if (patientCount <= 5) {
                gridPane = new JPanel(new GridLayout(6, 1));
            } else {
                gridPane = new JPanel(new GridLayout(patientCount, 1));
            }

            //Search Results Area
            JScrollPane scrollPane = new JScrollPane(gridPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

            for (int i = 0; i < patientCount; i++) {
                JPanel resultsPane = new JPanel();
                resultsPane.setBackground(panelColor);
                resultsPane.setBorder(BorderFactory.createRaisedBevelBorder());
                resultsPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

                JTextArea entryArea = new JTextArea(2, 20);
                entryArea.setText(patientList[i].name);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                resultsPane.add(entryArea);


                JButton patientHealthConditionsButton = new JButton("Health Conditions");
                patientHealthConditionsButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                patientHealthConditionsButton.putClientProperty("patientID", patientList[i].id);    //Used to store the patien ID on button object, java is nifty
                patientHealthConditionsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Select Patient Health Conditions
                        ShowPatientHealthConditions(accountid, dashboardType, (int) patientHealthConditionsButton.getClientProperty("patientID"));
                    }
                });
                //Anti Perms
                if(dashboardType == 3){
                    patientHealthConditionsButton.setEnabled(false);
                }
                resultsPane.add(patientHealthConditionsButton);

                JButton patientMedicalHistoryButton = new JButton("Medical History");
                patientMedicalHistoryButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                patientMedicalHistoryButton.putClientProperty("patientID", patientList[i].id);    //Used to store the patien ID on button object, java is nifty
                patientMedicalHistoryButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Select Patient Health Conditions
                        ShowPatientMedicalHistory(accountid, dashboardType, (int) patientHealthConditionsButton.getClientProperty("patientID"));
                    }
                });
                //Anti Perms
                if(dashboardType == 3){
                    patientMedicalHistoryButton.setEnabled(false);
                }
                resultsPane.add(patientMedicalHistoryButton);

                JButton patientPrescriptionsButton = new JButton("Prescriptions");
                patientPrescriptionsButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                patientPrescriptionsButton.putClientProperty("patientID", patientList[i].id);    //Used to store the patien ID on button object, java is nifty
                patientPrescriptionsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Select Patient Health Conditions
                        ShowPatientPrescriptions(accountid, dashboardType, (int) patientHealthConditionsButton.getClientProperty("patientID"));
                    }
                });
                //Anti Perms
                if(dashboardType == 3){
                    patientPrescriptionsButton.setEnabled(false);
                }
                resultsPane.add(patientPrescriptionsButton);

                JButton patientLabWorkButton = new JButton("Lab Work");
                patientLabWorkButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                patientLabWorkButton.putClientProperty("patientID", patientList[i].id);    //Used to store the patien ID on button object, java is nifty
                patientLabWorkButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Select Patient Health Conditions
                        ShowPatientLabRecords(accountid, dashboardType, (int) patientHealthConditionsButton.getClientProperty("patientID"));
                    }
                });
                //Anti Perms
                if(dashboardType == 2){
                    patientLabWorkButton.setEnabled(false);
                }
                resultsPane.add(patientLabWorkButton);
                gridPane.add(resultsPane);
            }
                contentPane.add(scrollPane);
        }

        //Patient Search Label
        JLabel patientSearchLabel = new JLabel("Patient Search");
        patientSearchLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 100, 50);
        contentPane.add(patientSearchLabel);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ReturnToCurrentDashboard(accountid, dashboardType);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Patient Health Conditions
    public void ShowPatientHealthConditions(int accountid, int dashboardType, int patientAccountid){
        contentPane.removeAll();

        //Ailment Label
        JLabel ailmentLabel;
        try {
            ailmentLabel = new JLabel("What is " + BackEndManager.sharedManager().getUsername(patientAccountid) + "'s issue?");
        } catch (Exception e) {
            ailmentLabel = new JLabel("What is the patient's issue?");
            e.printStackTrace();
        }
        ailmentLabel.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 6 - 40, 200, 14);
        contentPane.add(ailmentLabel);

        //Ailment Dropdown
        String[] ailmentTypes = {"Select Ailment","Addiction","Broken Limb","Burn","Concussion","Head Injury","Heart Attack","Anxiety"
                ,"Arthritis","Asthma","Bronchitis","Carpal Tunnel","Chronic Fatigue","Common Cold","Confusion"
                ,"Constipation","Dental Pain","Depression","Diarrhea","Digestive Problems","Dysentery"
                ,"Eye Problems","Fatigue","Fertility","Lower Back Pain","Menopause","Migraine","Nausea","Pain"
                ,"Pneumonia","Sleep Loss","Sore Throat","Stress","Tonsillitis","UTI","Vomiting"};

        JComboBox ailmentList = new JComboBox(ailmentTypes);
        ailmentList.setSelectedIndex(0);
        ailmentList.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 6, 300, 30);
        contentPane.add(ailmentList);

        //Severity Label
        JLabel severityLabel = new JLabel("How bad is it?");
        severityLabel.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 2 - 40, 200, 14);
        contentPane.add(severityLabel);

        //Severity Buttons
        JRadioButton oneRadioButton = new JRadioButton("1");
        oneRadioButton.setBounds(contentPane.getWidth() / 4 - 100, contentPane.getHeight() / 2, 20, 40);
        oneRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        oneRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        oneRadioButton.setOpaque(false);
        contentPane.add(oneRadioButton);

        JRadioButton twoRadioButton = new JRadioButton("2");
        twoRadioButton.setBounds(contentPane.getWidth() / 4 - 60, contentPane.getHeight() / 2, 20, 40);
        twoRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        twoRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        twoRadioButton.setOpaque(false);
        contentPane.add(twoRadioButton);

        JRadioButton threeRadioButton = new JRadioButton("3");
        threeRadioButton.setBounds(contentPane.getWidth() / 4 - 20, contentPane.getHeight() / 2, 20, 40);
        threeRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        threeRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        threeRadioButton.setSelected(true);
        threeRadioButton.setOpaque(false);
        contentPane.add(threeRadioButton);

        JRadioButton fourRadioButton = new JRadioButton("4");
        fourRadioButton.setBounds(contentPane.getWidth() / 4 + 20, contentPane.getHeight() / 2, 20, 40);
        fourRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        fourRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        fourRadioButton.setOpaque(false);
        contentPane.add(fourRadioButton);

        JRadioButton fiveRadioButton = new JRadioButton("5");
        fiveRadioButton.setBounds(contentPane.getWidth() / 4 + 60, contentPane.getHeight() / 2, 20, 40);
        fiveRadioButton.setVerticalTextPosition(JRadioButton.BOTTOM);
        fiveRadioButton.setHorizontalTextPosition(JRadioButton.CENTER);
        fiveRadioButton.setOpaque(false);
        contentPane.add(fiveRadioButton);

        ButtonGroup severityButtonGroup = new ButtonGroup();
        severityButtonGroup.add(oneRadioButton);
        severityButtonGroup.add(twoRadioButton);
        severityButtonGroup.add(threeRadioButton);
        severityButtonGroup.add(fourRadioButton);
        severityButtonGroup.add(fiveRadioButton);

        //Condition History Label
        JLabel historyLabel = new JLabel("Condition History");
        historyLabel.setBounds(contentPane.getWidth() * 3 / 4 - 150, contentPane.getHeight() / 6 - 40, 200, 14);
        contentPane.add(historyLabel);

        //Condition History Scrollpane
        HealthCareConditionEntry[] entries;
        int conditionEntries = 0;
        try {
            entries = BackEndManager.sharedManager().getHealthConditionEntries(patientAccountid);
            conditionEntries = entries.length;

            //Create inner grid pane
            JPanel gridPane;
            if(conditionEntries <= 8) {
                gridPane = new JPanel(new GridLayout(9, 1));
            }else {
                gridPane = new JPanel(new GridLayout(conditionEntries, 1));
            }

            //Create JScrollpane
            JScrollPane scrollPane = new JScrollPane(gridPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 + 20, contentPane.getHeight() / 6, contentPane.getWidth() / 2 - 40, 400);

            for(int i=0; i < conditionEntries; i++) {

                JPanel entryPane = new JPanel();
                entryPane.setBackground(panelColor);
                entryPane.setBorder(BorderFactory.createRaisedBevelBorder());

                JTextArea entryArea = new JTextArea(2, 20);
                entryArea.setText(entries[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                entryPane.add(entryArea);

                gridPane.add(entryPane);
            }

            contentPane.add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ReturnToCurrentDashboard(accountid,dashboardType);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        JLabel invalidinputLabel = new JLabel("No ailment selected");
        invalidinputLabel.setForeground(Color.RED);
        invalidinputLabel.setBounds(contentPane.getWidth() / 2 - 280, contentPane.getHeight() / 2 + 150, 150, 30);
        invalidinputLabel.setHorizontalTextPosition(JLabel.RIGHT);

        //Update button
        JButton updateButton = new JButton("Update");
        updateButton.setToolTipText("Makes new entry in Health Condition Log.");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (ailmentList.getSelectedIndex() != 0) {
                    int severityNum;

                    if(oneRadioButton.isSelected()){
                        severityNum = 1;
                    }else if(twoRadioButton.isSelected()){
                        severityNum = 2;
                    }else if(threeRadioButton.isSelected()){
                        severityNum = 3;
                    }else if(fourRadioButton.isSelected()){
                        severityNum = 4;
                    }else{
                        severityNum = 5;
                    }

                    //Create entry
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    Calendar cal = Calendar.getInstance();
                    try {
                        BackEndManager.sharedManager().createHealthConditionEntry(new HealthCareConditionEntry(patientAccountid,"Date and Time:" + dateFormat.format(cal.getTime()) + "\nIssue:" + ailmentTypes[ailmentList.getSelectedIndex()] + "\tSeverity:" + severityNum));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Reshow page
                    ShowPatientHealthConditions(accountid, dashboardType, patientAccountid);
                }else{
                    //Display necessary field entry
                    contentPane.remove(invalidinputLabel);

                    contentPane.add(invalidinputLabel);

                    contentPane.revalidate();
                    contentPane.repaint();
                }
            }
        });
        updateButton.setBounds(contentPane.getWidth() / 2 - 280, contentPane.getHeight() / 2 + 200, 100, 30);

        contentPane.add(updateButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Patient Medical History
    public void ShowPatientMedicalHistory(int accountid, int dashboardType, int patientAccountid){
        contentPane.removeAll();

        //Medical History Label
        JLabel medicalhistoryLabel = null;
        try {
            medicalhistoryLabel = new JLabel("Editing " + BackEndManager.sharedManager().getUsername(patientAccountid) + "'s Medical History");
        } catch (Exception e) {
            medicalhistoryLabel = new JLabel("Editing Patient's Medical History");
            e.printStackTrace();
        }
        medicalhistoryLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(medicalhistoryLabel);

        //Medical History Text Area
        JTextArea medicalhistoryTextArea = new JTextArea();
        //medicalhistoryTextArea.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);
        medicalhistoryTextArea.setWrapStyleWord(true);
        medicalhistoryTextArea.setLineWrap(true);
        medicalhistoryTextArea.setFocusable(true);
        medicalhistoryTextArea.setEditable(true);
        medicalhistoryTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        try {
            //medicalhistoryTextArea.setText(BackEndManager.sharedManager().getMedicalHistory(accountID));
        } catch (Exception e) {
            medicalhistoryTextArea.setText("No medical history found.");
            e.printStackTrace();
        }

        //Scroll Pane
        JScrollPane scrollPane = new JScrollPane(medicalhistoryTextArea);
        scrollPane.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);

        contentPane.add(scrollPane);

        //Save and Exit Button
        JButton backButton = new JButton("Save and Exit");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO
                //Save changes
                /*try {
                    BackEndManager.sharedManager().setMedicalHistory(patientAccountid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                   */

                //Return to dashboard
                ReturnToCurrentDashboard(accountid, dashboardType);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);

        contentPane.add(backButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Patient Prescriptions
    public void ShowPatientPrescriptions(int accountid, int dashboardType, int patientAccountid){

    }

    //Show Patient Lab Results
    public void ShowPatientLabRecords(int accountid, int dashboardType, int patientAccountid){

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
                ShowHSPDashboard(accountid);
                break;
            case(3):
                ShowLabtechDashboard(accountid);
                break;
            default:
                System.out.print("No dashboard of type:" + dashboardType);
        }
    }
}
