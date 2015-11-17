package com.g7;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
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

    //Creates main window
    public WindowManager(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 850, 550);
        setLocationRelativeTo(null);
        setResizable(false);
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
                    if(passwordMatches){
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
                        if(accountType != 1) {
                            //Show dashboard
                            ReturnToCurrentDashboard(id, accountType);
                        }else{
                            //Go to alerts page
                            ShowAllAlerts(id);
                        }
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

        String[] insuranceTypes = {"Cigna","Aetna","IHC","Humana","BCBS"};
        JComboBox insuranceCombobox = new JComboBox(insuranceTypes);
        insuranceCombobox.setBounds(contentPane.getWidth() / 2 + 230, contentPane.getHeight() / 2 - 160, 116, 20);
        contentPane.add(insuranceCombobox);

        //Patient Medical History
        JLabel medHistoryLabel = new JLabel("Input any medical history below");
        medHistoryLabel.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 120, 200, 14);
        contentPane.add(medHistoryLabel);

        JTextArea medHistoryTextArea = new JTextArea();
        medHistoryTextArea.setBounds(contentPane.getWidth() / 2 + 80, contentPane.getHeight() / 2 - 100, contentPane.getWidth() / 2 - 110, contentPane.getHeight() / 2 - 5);
        medHistoryTextArea.setWrapStyleWord(true);
        medHistoryTextArea.setLineWrap(true);
        medHistoryTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        medHistoryTextArea.setText("No medical histroy.");
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
                contentPane.add(insuranceCombobox);
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
                contentPane.remove(insuranceCombobox);
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
                contentPane.remove(insuranceCombobox);
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
                contentPane.remove(insuranceCombobox);
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
                    if((accountType != 0) || (ssnField.getText().length() == 10)) {
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
                                    BackEndManager.sharedManager().registerNewUser(fnameField.getText(),lnameField.getText(),usernameField.getText(),passwordField.getText(),emailField.getText(),phoneField.getText(),accountType,ssnField.getText(),insuranceTypes[insuranceCombobox.getSelectedIndex()],medHistoryTextArea.getText());
                                    BackEndManager.sharedManager().setMedicalHistory(BackEndManager.sharedManager().getUserID(usernameField.getText()),medHistoryTextArea.getText());
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
                        //Print Error: incorrect SSN entry
                        contentPane.add(incorrectSSNEntryLabel);

                        contentPane.repaint();
                        contentPane.revalidate();
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
        JLabel titleLabel = null;
        try {
            titleLabel = new JLabel(BackEndManager.sharedManager().getName(accountid) + "'s Patient Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        BackEndManager.sharedManager().createHealthConditionEntry(new HealthCareConditionEntry(accountid,"Date and Time: " + dateFormat.format(cal.getTime()) + "\nIssue: " + ailmentTypes[ailmentList.getSelectedIndex()] + "\nSeverity: " + severityNum));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if(severityNum == 5 || ailmentList.getSelectedIndex() > 0 && ailmentList.getSelectedIndex() < 7){
                        //Create Alert
                        try {
                            BackEndManager.sharedManager().createAlert(new Alert(accountid,"Date and Time: " + dateFormat.format(cal.getTime()) + "\nIssue: " + ailmentTypes[ailmentList.getSelectedIndex()] + "\nSeverity: " + severityNum,-1));
                        } catch (Exception e) {
                            System.out.println("Failed making new alert");
                            e.printStackTrace();
                        }

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
        JTextArea warning = new JTextArea("You have a serious health risk.\nDoctor's have been notified of your condition.\nCall 911 or go to the hospital now!");
        warning.setWrapStyleWord(true);
        warning.setLineWrap(true);
        warning.setOpaque(false);
        warning.setEditable(false);
        warning.setFocusable(false);
        warning.setBounds(contentPane.getWidth() / 2 - 100, contentPane.getHeight() / 2 - 100, 250, 100);
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

    String[] doctorNames;
    Doctor[] doctors;

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
        doctorNames = null;
        doctors = null;
        try {
            doctors = BackEndManager.sharedManager().getDoctorList();
            doctorNames = new String[doctors.length + 1];
            doctorNames[0] = "Doctors";
            for(int i = 1; i < doctors.length + 1; i++){
                doctorNames[i] = "Dr. " + doctors[i - 1].name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JComboBox doctorComboBox = new JComboBox(doctorNames);
        doctorComboBox.setBounds(contentPane.getWidth() / 2 - 275, contentPane.getHeight() / 2 + 50, 200, 50);
        contentPane.add(doctorComboBox);

        //Arbitrary
        String[] insurance = {"Insurance Provider", "Aetna", "Aviva", "Life Saver", "Life Care"};
        JComboBox insurancecombobox = new JComboBox(insurance);
        insurancecombobox.setBounds(contentPane.getWidth() / 2 - 275,contentPane.getHeight() / 2 + 125,200,50);
        contentPane.add(insurancecombobox);

        JLabel reasonlabel = new JLabel("Reason For Visit:");
        reasonlabel.setBounds(contentPane.getWidth() / 2, contentPane.getHeight() / 2 - 125, 200, 200);
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
        emptyFields.setBounds(contentPane.getWidth() / 2, contentPane.getHeight() / 2 + 225, 200, 20);

        JButton makeButton = new JButton("Make");
        makeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Check if all fields are selected
                if (dayscombobox.getSelectedIndex() != 0 && monthcombobox.getSelectedIndex() != 0 && yearcombobox.getSelectedIndex() != 0 && doctorComboBox.getSelectedIndex() != 0 && timecombobox.getSelectedIndex() != 0 && insurancecombobox.getSelectedIndex() != 0) {
                    //Create appointment
                    try {
                        BackEndManager.sharedManager().createAppointment(new Appointment(accountid, new String("Date: " + year[yearcombobox.getSelectedIndex()] + "-" + month[monthcombobox.getSelectedIndex()] + "-" + day[dayscombobox.getSelectedIndex()] + "\nTime: " + time[timecombobox.getSelectedIndex()] + "\nPatient:" + BackEndManager.sharedManager().getName(accountid) + "\nDoctor: " + doctorNames[doctorComboBox.getSelectedIndex()]), -1, doctors[doctorComboBox.getSelectedIndex() - 1].userID));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //return to appointments list
                    ShowAppointments(accountid);
                } else {
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
            medicalhistoryTextArea.setText(BackEndManager.sharedManager().getMedicalHistory(accountid));
        } catch (Exception e) {
            medicalhistoryTextArea.setText("No medical history found.");
            e.printStackTrace();
        }
        contentPane.add(medicalhistoryTextArea);

        //Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Return to dashboard
                ShowPatientDashboard(accountid);
            }
        });
        backButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 70, 30);

        contentPane.add(backButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show all Alerts page
    //Only for doctors
    public void ShowAllAlerts(int accountid){
        contentPane.removeAll();

        //Alert Label
        JLabel prescriptionLabel = new JLabel("Alerts");
        prescriptionLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(prescriptionLabel);

        Alert[] alertList;

        try {
            alertList = BackEndManager.sharedManager().getAlerts();
        } catch (Exception e) {
            e.printStackTrace();
            alertList = null;
        }

        if(alertList != null && alertList.length != 0) {
            //Create inner grid pane
            int alertCount = alertList.length;

            JPanel gridPane;
            if (alertCount <= 5) {
                gridPane = new JPanel(new GridLayout(6, 1));
            } else {
                gridPane = new JPanel(new GridLayout(alertCount, 1));
            }

            //Alert List Area
            JScrollPane scrollPane = new JScrollPane(gridPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

            for (int i = 0; i < alertCount; i++) {
                JPanel resultsPane = new JPanel();
                resultsPane.setBackground(panelColor);
                resultsPane.setBorder(BorderFactory.createRaisedBevelBorder());
                resultsPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

                JTextArea entryArea = new JTextArea(2,20);
                entryArea.setText(alertList[i].info);
                entryArea.setToolTipText(alertList[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                entryArea.setMinimumSize(new Dimension(scrollPane.getWidth() / 2, 20));
                try {
                    entryArea.setText(BackEndManager.sharedManager().getName(alertList[i].userID) + "\n" + alertList[i].info);
                } catch (Exception e) {
                    entryArea.setText("Unknown patient\n" + alertList[i].info);
                    e.printStackTrace();
                }
                resultsPane.add(entryArea);

                //Remove Alert
                JButton removeAlertButton = new JButton("Remove");
                removeAlertButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                removeAlertButton.putClientProperty("alertID", alertList[i].alertID);
                removeAlertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Remove Alert
                        try {
                            BackEndManager.sharedManager().removeAlert((int) removeAlertButton.getClientProperty("alertID"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Reshow page
                        ShowAllAlerts(accountid);
                    }
                });
                resultsPane.add(removeAlertButton);

                gridPane.add(resultsPane);
            }
            contentPane.add(scrollPane);
        }else{
            //No more alerts
            ShowDoctorDashboard(accountid);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Doctor Dashboard
    public void ShowDoctorDashboard(int accountid){
        contentPane.removeAll();

        //Title
        JLabel titleLabel = null;
        try {
            titleLabel = new JLabel(BackEndManager.sharedManager().getName(accountid) + "'s Doctor Dashboard");
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

        JButton statReportButton = new JButton("Stat Reports");
        statReportButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);
        statReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Stat Reports
                ShowStatReports(accountid, 1, null);
            }
        });
        contentPane.add(statReportButton);

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
            titleLabel = new JLabel(BackEndManager.sharedManager().getName(accountid) + "'s Dashboard");
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

        JButton statReportButton = new JButton("Stat Reports");
        statReportButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);
        statReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Stat Reports
                ShowStatReports(accountid, 2, null);
            }
        });
        contentPane.add(statReportButton);

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
            titleLabel = new JLabel(BackEndManager.sharedManager().getName(accountid) + "'s Dashboard");
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
                //Not for: Lab Tech
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
                //Not for: Lab Tech
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
                //Not for: HSP
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
            ailmentLabel = new JLabel("What is " + BackEndManager.sharedManager().getName(patientAccountid) + "'s issue?");
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
            medicalhistoryLabel = new JLabel("Editing " + BackEndManager.sharedManager().getName(patientAccountid) + "'s Medical History");
        } catch (Exception e) {
            medicalhistoryLabel = new JLabel("Editing Patient's Medical History");
            e.printStackTrace();
        }
        medicalhistoryLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(medicalhistoryLabel);

        //Medical History Text Area
        JTextArea medicalhistoryTextArea = new JTextArea();
        medicalhistoryTextArea.setWrapStyleWord(true);
        medicalhistoryTextArea.setLineWrap(true);
        medicalhistoryTextArea.setFocusable(true);
        medicalhistoryTextArea.setEditable(true);
        medicalhistoryTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        try {
            medicalhistoryTextArea.setText(BackEndManager.sharedManager().getMedicalHistory(patientAccountid));
        } catch (Exception e) {
            medicalhistoryTextArea.setText("No connection to server...");
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
                //Save changes
                try {
                    BackEndManager.sharedManager().setMedicalHistory(patientAccountid,medicalhistoryTextArea.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
        contentPane.removeAll();

        //Prescription Label
        JLabel prescriptionLabel = null;
        try {
            prescriptionLabel = new JLabel("Viewing prescriptions for " + BackEndManager.sharedManager().getName(patientAccountid));
        } catch (Exception e) {
            prescriptionLabel = new JLabel("Viewing prescriptions for patient.");
            e.printStackTrace();
        }
        prescriptionLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(prescriptionLabel);

        Prescription[] prescriptionList;

        try {
            prescriptionList = BackEndManager.sharedManager().getPrescriptionList(patientAccountid);
        } catch (Exception e) {
            e.printStackTrace();
            prescriptionList = null;
        }

        if(prescriptionList != null) {
            //Create inner grid pane
            int prescriptionCount = prescriptionList.length;

            JPanel gridPane;
            if (prescriptionCount <= 5) {
                gridPane = new JPanel(new GridLayout(6, 1));
            } else {
                gridPane = new JPanel(new GridLayout(prescriptionCount, 1));
            }

            //Prescriptions List Area
            JScrollPane scrollPane = new JScrollPane(gridPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

            for (int i = 0; i < prescriptionCount; i++) {
                JPanel resultsPane = new JPanel();
                resultsPane.setBackground(panelColor);
                resultsPane.setBorder(BorderFactory.createRaisedBevelBorder());
                resultsPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

                JTextArea entryArea = new JTextArea(2,20);
                entryArea.setText(prescriptionList[i].info);
                entryArea.setToolTipText(prescriptionList[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                entryArea.setMinimumSize(new Dimension(scrollPane.getWidth() / 2, 20));
                resultsPane.add(entryArea);

                //Print Precription
                JButton printPrescriptionButton = new JButton("Print");
                printPrescriptionButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                printPrescriptionButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Print Prescription Info
                        try {
                            entryArea.print();
                        } catch (PrinterException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //No Anti Perms
                resultsPane.add(printPrescriptionButton);

                //Remove Prescription
                JButton removePrescriptionButton = new JButton("Remove");
                removePrescriptionButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                removePrescriptionButton.putClientProperty("prescriptionID", prescriptionList[i].prescriptionID);    //Used to store the patien ID on button object, java is nifty
                removePrescriptionButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Remove Prescription
                        try {
                            BackEndManager.sharedManager().removePrescription((int) removePrescriptionButton.getClientProperty("prescriptionID"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Reshow page
                        ShowPatientPrescriptions(accountid,dashboardType,patientAccountid);
                    }
                });
                //Anti Perms
                //Not for: HSP
                if(dashboardType == 2){
                    removePrescriptionButton.setEnabled(false);
                }
                resultsPane.add(removePrescriptionButton);

                gridPane.add(resultsPane);
            }
            contentPane.add(scrollPane);
        }

        //Create Prescription Button
        JButton createPrescriptionButton = new JButton("Create Prescription");
        createPrescriptionButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 - 240, 150, 30);
        createPrescriptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Show Create Prescription Page
                ShowCreatePrescription(accountid, dashboardType, patientAccountid);
            }
        });
        //Anti Perms
        //Not for: HSP
        if(dashboardType == 2){
            createPrescriptionButton.setEnabled(false);
        }
        contentPane.add(createPrescriptionButton);

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

    //Show Create Prescription
    public void ShowCreatePrescription(int accountid, int dashboardType, int patientAccountid){
        contentPane.removeAll();

        //Prescription Label
        JLabel prescriptionLabel = null;
        try {
            prescriptionLabel = new JLabel("Creating new prescription for " + BackEndManager.sharedManager().getName(patientAccountid));
        } catch (Exception e) {
            prescriptionLabel = new JLabel("Creating new prescription for patient.");
            e.printStackTrace();
        }
        prescriptionLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(prescriptionLabel);

        //Prescription Text Area
        JTextArea prescriptionTextArea = new JTextArea();
        prescriptionTextArea.setWrapStyleWord(true);
        prescriptionTextArea.setLineWrap(true);
        prescriptionTextArea.setFocusable(true);
        prescriptionTextArea.setEditable(true);
        prescriptionTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        prescriptionTextArea.setText("Enter info here...");

        //Scroll Pane
        JScrollPane scrollPane = new JScrollPane(prescriptionTextArea);
        scrollPane.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);

        contentPane.add(scrollPane);

        //Create Button
        JButton createButton = new JButton("Create and Exit");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Save changes
                try {
                    BackEndManager.sharedManager().createPrescription(new Prescription(patientAccountid, prescriptionTextArea.getText(),-1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Return Show Prescriptions page
                ShowPatientPrescriptions(accountid, dashboardType, patientAccountid);
            }
        });
        createButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);
        contentPane.add(createButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Patient Lab Results
    public void ShowPatientLabRecords(int accountid, int dashboardType, int patientAccountid){
        contentPane.removeAll();

        //Lab Work Label
        JLabel labworkLabel = null;
        try {
            labworkLabel = new JLabel("Viewing lab records for " + BackEndManager.sharedManager().getName(patientAccountid));
        } catch (Exception e) {
            labworkLabel = new JLabel("Viewing lab records for patient.");
            e.printStackTrace();
        }
        labworkLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(labworkLabel);

        LabWork[] labworkList;

        try {
            labworkList = BackEndManager.sharedManager().getLabWorkList(patientAccountid);
        } catch (Exception e) {
            e.printStackTrace();
            labworkList = null;
        }

        if(labworkList != null) {
            //Create inner grid pane
            int labworkCount = labworkList.length;

            JPanel gridPane;
            if (labworkCount <= 5) {
                gridPane = new JPanel(new GridLayout(6, 1));
            } else {
                gridPane = new JPanel(new GridLayout(labworkCount, 1));
            }

            //Prescriptions List Area
            JScrollPane scrollPane = new JScrollPane(gridPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 200, 740, 370);

            for (int i = 0; i < labworkCount; i++) {
                JPanel resultsPane = new JPanel();
                resultsPane.setBackground(panelColor);
                resultsPane.setBorder(BorderFactory.createRaisedBevelBorder());
                resultsPane.setMinimumSize(new Dimension(scrollPane.getWidth(), 370 / 5));

                JTextArea entryArea = new JTextArea(2,20);
                entryArea.setText(labworkList[i].info);
                entryArea.setToolTipText(labworkList[i].info);
                entryArea.setWrapStyleWord(true);
                entryArea.setLineWrap(true);
                entryArea.setOpaque(false);
                entryArea.setEditable(false);
                entryArea.setFocusable(false);
                entryArea.setMinimumSize(new Dimension(scrollPane.getWidth() / 2, 20));
                resultsPane.add(entryArea);

                //Print Lab Record Button
                JButton printLabRecordButton = new JButton("Print");
                printLabRecordButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                printLabRecordButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Print Prescription Info
                        try {
                            entryArea.print();
                        } catch (PrinterException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //No Anti Perms
                resultsPane.add(printLabRecordButton);

                //Update Lab Record
                JButton updateLabRecordButton = new JButton("Update");
                updateLabRecordButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                updateLabRecordButton.putClientProperty("labWorkID", labworkList[i].labWorkID);
                updateLabRecordButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        //Update Lab Record page
                        ShowUpdateLabRecord(accountid, dashboardType, patientAccountid, (int) updateLabRecordButton.getClientProperty("labWorkID"));
                    }
                });
                //Anti Perms
                //Not for: Doctor
                if(dashboardType == 1){
                    updateLabRecordButton.setEnabled(false);
                }
                resultsPane.add(updateLabRecordButton);
                
                //Update Lab Record
                JButton deleteLabRecordButton = new JButton("Delete");
                deleteLabRecordButton.setBounds(resultsPane.getWidth() * 3 / 4, 0, resultsPane.getWidth() / 4, resultsPane.getHeight());
                deleteLabRecordButton.putClientProperty("labWorkID", labworkList[i].labWorkID);
                deleteLabRecordButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            BackEndManager.sharedManager().removeLabWork((int) deleteLabRecordButton.getClientProperty("labWorkID"));
                        } catch (Exception e) {
                            System.out.println("Error connecting for deleteing labwork");
                            e.printStackTrace();
                        }

                        //Reshow Lab Record Page
                        ShowPatientLabRecords(accountid,dashboardType,patientAccountid);
                    }
                });
                //Anti Perms
                //Not for: Doctor
                if(dashboardType == 1){
                    deleteLabRecordButton.setEnabled(false);
                }
                resultsPane.add(deleteLabRecordButton);

                gridPane.add(resultsPane);
            }
            contentPane.add(scrollPane);
        }

        //Create Lab Record Button

        JButton createLabRecordButton;

        //if doctor viewing
        if(dashboardType == 1){
            //Only request
            createLabRecordButton = new JButton("Request New Lab");
        }else{
            //Actually make
            createLabRecordButton = new JButton("Create New Entry");
        }
        createLabRecordButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 - 240, 150, 30);
        createLabRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (dashboardType == 3) {
                    //Show Create Lab Record Page
                    ShowCreateLabRecord(accountid, dashboardType, patientAccountid);
                } else {
                    //Make new Lab Work with default text
                    try {
                        BackEndManager.sharedManager().createLabWork(new LabWork(patientAccountid, "New Lab Requested\nDoctor: " + BackEndManager.sharedManager().getName(accountid), -1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ShowPatientLabRecords(accountid, dashboardType, patientAccountid);
                }
            }
        });
        //No Anti Perms
        contentPane.add(createLabRecordButton);

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

    //Show Create Prescription
    public void ShowCreateLabRecord(int accountid, int dashboardType, int patientAccountid){
        contentPane.removeAll();

        //Prescription Label
        JLabel prescriptionLabel = null;
        try {
            prescriptionLabel = new JLabel("Creating new lab record for " + BackEndManager.sharedManager().getName(patientAccountid));
        } catch (Exception e) {
            prescriptionLabel = new JLabel("Creating new lab record for patient.");
            e.printStackTrace();
        }
        prescriptionLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(prescriptionLabel);

        //Lab Record Text Area
        JTextArea labWorkTextArea = new JTextArea();
        labWorkTextArea.setWrapStyleWord(true);
        labWorkTextArea.setLineWrap(true);
        labWorkTextArea.setFocusable(true);
        labWorkTextArea.setEditable(true);
        labWorkTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        labWorkTextArea.setText("Enter lab info here...");

        //Scroll Pane
        JScrollPane scrollPane = new JScrollPane(labWorkTextArea);
        scrollPane.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);

        contentPane.add(scrollPane);

        //Create Button
        JButton createButton = new JButton("Create and Exit");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Save changes
                try {
                    BackEndManager.sharedManager().createLabWork(new LabWork(patientAccountid, labWorkTextArea.getText(), -1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Return Show Lab Records page
                ShowPatientLabRecords(accountid, dashboardType, patientAccountid);
            }
        });
        createButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);
        contentPane.add(createButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Update Lab Record Page
    public void ShowUpdateLabRecord(int accountid, int dashboardType, int patientAccountid, int labrecordID){
        contentPane.removeAll();

        //Labrecord Label
        JLabel labrecordLabel = null;
        try {
            labrecordLabel = new JLabel("Updating lab record for " + BackEndManager.sharedManager().getName(patientAccountid));
        } catch (Exception e) {
            labrecordLabel = new JLabel("Updating lab record for patient.");
            e.printStackTrace();
        }
        labrecordLabel.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 - 250, 250, 50);
        contentPane.add(labrecordLabel);

        //Lab Record Text Area
        JTextArea labWorkTextArea = new JTextArea();
        labWorkTextArea.setWrapStyleWord(true);
        labWorkTextArea.setLineWrap(true);
        labWorkTextArea.setFocusable(true);
        labWorkTextArea.setEditable(true);
        labWorkTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        try {
            labWorkTextArea.setText(BackEndManager.sharedManager().getLabWork(labrecordID));
        } catch (Exception e) {
            labWorkTextArea.setText("Error reading lab info to update");
            e.printStackTrace();
        }

        //Scroll Pane
        JScrollPane scrollPane = new JScrollPane(labWorkTextArea);
        scrollPane.setBounds(20, contentPane.getHeight() / 2 - 200, contentPane.getWidth() - 40, 375);

        contentPane.add(scrollPane);

        //Update Button
        JButton updateButton = new JButton("Update and Exit");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Save changes to existing labwork
                try {
                    BackEndManager.sharedManager().updateLabWork(new LabWork(patientAccountid, labWorkTextArea.getText(), labrecordID));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Return Show Prescriptions page
                ShowPatientLabRecords(accountid, dashboardType, patientAccountid);
            }
        });
        updateButton.setBounds(contentPane.getWidth() / 2 - 370, contentPane.getHeight() / 2 + 200, 150, 30);
        contentPane.add(updateButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    public void ShowStatReports(int accountid, int dashboardType, Graph graph){
        contentPane.removeAll();

        //If not displaying a desired graph
        if(graph == null){

            //useful User Types
            try {
                Patient[] patients = BackEndManager.sharedManager().getPatientsAndInsurace();
                Doctor[] doctors = BackEndManager.sharedManager().getDoctorList();

                //User Type Counts
                int numPatients = patients.length;
                int numDoctors = doctors.length;
                int numHSPs = BackEndManager.sharedManager().getHSPCount();
                int numLabTechs = BackEndManager.sharedManager().getLabTechCount();

                ShowStatReports(accountid,dashboardType,new Graph("User Types", new String[]{"Patient","Doctor","HSP","Lab Tech"},new int[]{numPatients,numDoctors,numHSPs,numLabTechs}));
            } catch (Exception e) {
                System.out.print("Can't pull graph data");
                e.printStackTrace();
            }

        }else {

            JButton usertypesButton = new JButton("User Types");
            usertypesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Patient[] patients = BackEndManager.sharedManager().getPatientsAndInsurace();
                        Doctor[] doctors = BackEndManager.sharedManager().getDoctorList();

                        //User Type Counts
                        int numPatients = patients.length;
                        int numDoctors = doctors.length;
                        int numHSPs = BackEndManager.sharedManager().getHSPCount();
                        int numLabTechs = BackEndManager.sharedManager().getLabTechCount();

                        ShowStatReports(accountid, dashboardType, new Graph("User Types", new String[]{"Patient", "Doctor", "HSP", "Lab Tech"}, new int[]{numPatients, numDoctors, numHSPs, numLabTechs}));
                    } catch (Exception e) {
                        System.out.print("Can't pull graph data");
                        e.printStackTrace();
                    }
                }
            });
            usertypesButton.setBounds(100, 40, 150, 30);
            contentPane.add(usertypesButton);

            JButton seriousConditionsButton = new JButton("Serious Conditions");
            seriousConditionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        String[] seriousConditionList = new String[]{"Addiction", "Broken Limb", "Burn", "Concussion", "Head Injury", "Heart Attack"};

                        HealthCareConditionEntry[] healthCareConditionEntries = BackEndManager.sharedManager().getAllHealthConditionEntries();

                        //User Type Counts
                        int[] nums = new int[seriousConditionList.length];

                        for(int i = 0; i < healthCareConditionEntries.length; i++){
                            String temp = healthCareConditionEntries[i].info;
                            for(int j = 0; j < nums.length; j++){
                                if(temp.contains(seriousConditionList[j])){
                                    nums[j]++;
                                    break;
                                }
                            }
                        }

                        ShowStatReports(accountid, dashboardType, new Graph("Serious Conditions", seriousConditionList,nums));
                    } catch (Exception e) {
                        System.out.print("Can't pull graph data");
                        e.printStackTrace();
                    }
                }
            });
            seriousConditionsButton.setBounds(350, 40, 150, 30);
            contentPane.add(seriousConditionsButton);

            JButton insuranceTypes = new JButton("Insurance Types");
            insuranceTypes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Patient[] patients = BackEndManager.sharedManager().getPatientsAndInsurace();

                        int CignaCount = 0;
                        int AetnaCount = 0;
                        int IHCCount = 0;
                        int HumanaCount = 0;
                        int BCBSCount = 0;

                        //Insurance Types Counts
                        for (int i = 0; i < patients.length; i++) {
                            switch (patients[i].insurance) {
                                case ("Cigna"):
                                    CignaCount++;
                                    break;
                                case ("Aetna"):
                                    AetnaCount++;
                                    break;
                                case ("IHC"):
                                    IHCCount++;
                                    break;
                                case ("Humana"):
                                    HumanaCount++;
                                    break;
                                case ("BCBS"):
                                    BCBSCount++;
                                    break;
                                default:
                                    break;
                            }
                        }

                        ShowStatReports(accountid, dashboardType, new Graph("Insurance Types per User", new String[]{"Cigna", "Aetna", "IHC", "Humana", "BCBS"}, new int[]{CignaCount, AetnaCount, IHCCount, HumanaCount, BCBSCount}));
                    } catch (Exception e) {
                        System.out.print("Can't pull graph data");
                        e.printStackTrace();
                    }
                }
            });
            insuranceTypes.setBounds(600, 40, 150, 30);
            contentPane.add(insuranceTypes);

            //Add the input panel if not void
            graph.repaint();
            contentPane.add(graph);

            //Back Button
            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    //Return to dashboard
                    ReturnToCurrentDashboard(accountid, dashboardType);
                }
            });
            backButton.setBounds(contentPane.getWidth() / 2 + 220, contentPane.getHeight() / 2 + 200, 150, 30);
            contentPane.add(backButton);

            contentPane.revalidate();
            contentPane.repaint();
        }
    }

    public class Graph extends JPanel{

        //Saved values of the group counts used for line spacing on paint
        int[] groupCounts;

        //Highest value represented on the graph, used for sizing of bars and line spacing on paint
        int graphTopCount;

        public Graph(String title, String[] xAxisGroups, int[] groupCounts) {
            setBackground(backgroundColor);

            setLayout(null);
            setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            setBounds(50, 100, 750, 340);

            JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setBounds(getWidth() / 2 - 100, 10, 200, 20);
            add(titleLabel);

            graphTopCount = groupCounts[0];

            for (int i = 0; i < xAxisGroups.length; i++) {
                JLabel tempGroupLabel = new JLabel(xAxisGroups[i],SwingConstants.CENTER);
                //g.fillRect(((600 / groupCounts.length) * (i)) + (int)((600 / groupCounts.length) * ((double)1/6)) + 100, (int)(275 - (275-60)*((double)groupCounts[i] / graphTopCount)), (int)((600 / groupCounts.length) * ((double)2/3)),(int)((275-60)*((double)groupCounts[i]/ graphTopCount)) + 1);
                tempGroupLabel.setBounds((int)(((600 / xAxisGroups.length) * (i)) + (double)(600 / xAxisGroups.length) / 2), 300, 200, 20);
                add(tempGroupLabel);

                //Find largest number
                if(graphTopCount < groupCounts[i]){
                    graphTopCount = groupCounts[i];
                }
            }

            //Make it divisible by 4 so the count lines make sense
            while(graphTopCount%4 != 0 || graphTopCount == 0){
                graphTopCount++;
            }

            //Count Labels
            for(int i = 0; i < 5; i++){
                JLabel tempCountLabel = new JLabel(Integer.toString((int)(graphTopCount * (double)(i)/4)));
                tempCountLabel.setBounds(75, (int) (275 - (i) * (double) 215 / 4) - 10, 100, 20);
                add(tempCountLabel);
            }

            this.groupCounts = groupCounts;
        }

        public void paintComponent(Graphics g){
            //Draw Background
            g.setColor(panelColor);

            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.LIGHT_GRAY);

            //Count Lines
            for(int i = 0; i < 4; i++){
                g.drawLine(100, (int)(275 - (i + 1)*(double)215/4), 700, (int)(275 - (i + 1)*(double)215/4));
            }

            g.setColor(Color.gray);

            //X axis
            g.drawLine(100, 275, 700, 275);

            //Y Axis
            g.drawLine(100, 275, 100, 60);

            //Bars
            for(int i = 0; i < groupCounts.length; i++){
                g.fillRect(((600 / groupCounts.length) * (i)) + (int)((600 / groupCounts.length) * ((double)1/6)) + 100, (int)(275 - (275-60)*((double)groupCounts[i] / graphTopCount)), (int)((600 / groupCounts.length) * ((double)2/3)),(int)((275-60)*((double)groupCounts[i]/ graphTopCount)) + 1);
            }
        }
    }

    //Returns to correct dashboard
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
