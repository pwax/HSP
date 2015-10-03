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
    }
//endregion

//region Example pages in here
    //Show Login page
    public void ShowLogin(){
        System.out.print("showing login\n");
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content panel

        //Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        loginPanel.setBackground(panelColor);
        loginPanel.setLayout(null);
        loginPanel.setBounds(contentPane.getWidth() / 2 - 150, contentPane.getHeight() / 2 - 100, 300, 200);

        //Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(loginPanel.getWidth() / 2 - 100, loginPanel.getHeight() / 2 - 60, 66, 14);
        loginPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(loginPanel.getWidth() / 2 + 10, loginPanel.getHeight() / 2 - 60, 116, 20);
        loginPanel.add(usernameField);
        //Not sure if necessary? Looked it up and tried without and it works fine and doesn't seem to effect anything
        //usernameField.setColumns(10);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(loginPanel.getWidth() / 2 - 100, loginPanel.getHeight() / 2 - 10, 66, 14);
        loginPanel.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(loginPanel.getWidth() / 2 + 10, loginPanel.getHeight() / 2 - 10, 116, 20);
        loginPanel.add(passwordField);
        //Not sure if necessary? Looked it up and tried without and it works fine and doesn't seem to effect anything
        //passwordField.setColumns(10);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ShowRegister();
            }
        });
        registerButton.setBounds(loginPanel.getWidth() / 2 - 125, loginPanel.getHeight() / 2 + 50, 100, 25);
        loginPanel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Check correct input to login
                //Then something like: ShowDashboard(AccountType)
            }
        });
        loginButton.setBounds(loginPanel.getWidth() / 2 + 25, loginPanel.getHeight() / 2 + 50, 100, 25);
        loginPanel.add(loginButton);

        contentPane.add(loginPanel);

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
        //Register Panel
        JPanel registerPanel = new JPanel();
        registerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        registerPanel.setBackground(panelColor);
        registerPanel.setLayout(null);
        registerPanel.setBounds(contentPane.getWidth() / 2 - 200, contentPane.getHeight() / 2 - 225, 400, 450);

        //First name
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 - 200, 140, 14);
        registerPanel.add(fnameLabel);

        JTextField fnameField = new JTextField();
        fnameField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 - 200, 116, 20);
        registerPanel.add(fnameField);
        fnameField.setColumns(10);

        //Last Name
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 - 160, 140, 14);
        registerPanel.add(lnameLabel);

        JTextField lnameField = new JTextField();
        lnameField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 - 160, 116, 20);
        registerPanel.add(lnameField);
        lnameField.setColumns(10);

        //Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 - 120, 140, 14);
        registerPanel.add(passwordLabel);

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 - 120, 116, 20);
        registerPanel.add(passwordField);

        //Confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 - 80, 140, 14);
        registerPanel.add(confirmPasswordLabel);

        JTextField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 - 80, 116, 20);
        registerPanel.add(confirmPasswordField);

        //Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 - 40, 140, 14);
        registerPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 - 40, 116, 20);
        registerPanel.add(emailField);
        emailField.setColumns(10);

        //Phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2, 140, 14);
        registerPanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2, 116, 20);
        registerPanel.add(phoneField);
        phoneField.setColumns(10);

        //Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(registerPanel.getWidth() / 2 - 140, registerPanel.getHeight() / 2 + 40, 140, 14);
        registerPanel.add(accountTypeLabel);
        
        JRadioButton patientRadioButton = new JRadioButton("Patient");
        patientRadioButton.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 + 40, 116, 20);
        registerPanel.add(patientRadioButton);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 + 70, 116, 20);
        registerPanel.add(doctorRadioButton);

        JRadioButton nurseRadioButton = new JRadioButton("Nurse");
        nurseRadioButton.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 + 100, 116, 20);
        registerPanel.add(nurseRadioButton);

        JRadioButton labtechRadioButton = new JRadioButton("Lab Tech");
        labtechRadioButton.setBounds(registerPanel.getWidth() / 2 + 10, registerPanel.getHeight() / 2 + 130, 116, 20);
        registerPanel.add(labtechRadioButton);

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
        backButton.setBounds(registerPanel.getWidth() / 2 - 125, registerPanel.getHeight() / 2 + 175, 100, 25);
        registerPanel.add(backButton);

        //Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Apply register check and completion here
                ShowLogin();
            }
        });
        registerButton.setBounds(registerPanel.getWidth() / 2 + 25, registerPanel.getHeight() / 2 + 175, 100, 25);
        registerPanel.add(registerButton);

        contentPane.add(registerPanel);

        //Reapply panel
        contentPane.repaint();
        contentPane.revalidate();
    }

    //Show Patient Dashboard
    public void ShowPatientDashboard(){
        contentPane.removeAll();

        JButton btnMedicalHistory = new JButton("Medical History");
        btnMedicalHistory.setBounds(54, 184, 139, 23);
        contentPane.add(btnMedicalHistory);

        JButton btnNewButton = new JButton("Update Medical Status");
        btnNewButton.setBounds(264, 184, 139, 23);
        contentPane.add(btnNewButton);

        JButton btnBookAppointment = new JButton("Book Appointment");
        btnBookAppointment.setBounds(484, 184, 139, 23);
        contentPane.add(btnBookAppointment);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gdasara1\\Desktop\\update 2.jpg"));
        lblNewLabel_1.setBounds(264, 81, 139, 92);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gdasara1\\Desktop\\medical history.jpg"));
        lblNewLabel.setBounds(54, 81, 139, 92);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\gdasara1\\Desktop\\calender final2.png"));
        lblNewLabel_2.setBounds(484, 81, 139, 92);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("     Click on Critical Alert to book appoinment immediately");
        lblNewLabel_3.setBounds(205, 395, 280, 43);
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gdasara1\\Desktop\\critical.jpg"));
        btnNewButton_1.setBounds(201, 285, 298, 99);
        contentPane.add(btnNewButton_1);

        contentPane.revalidate();
        contentPane.repaint();
    }

    //Show Doctor Dashboard
    public void ShowDoctorDashboard(){

    }
    //endregion

    //Edit this method only!
    public void ShowTest(){
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content panel
        //Change stuff in here, not the other functions in this method
        //Test Label
        JLabel testLabel = new JLabel("sample text");
        testLabel.setBounds(contentPane.getWidth() / 2 - 100, contentPane.getHeight() / 2, 100, 25);
        contentPane.add(testLabel);

        //Test field
        JTextField testField = new JTextField();
        testField.setBounds(contentPane.getWidth() / 2, contentPane.getHeight() / 2, 116, 20);
        contentPane.add(testField);

        //Reapply panel
        contentPane.repaint();
        contentPane.revalidate();
    }
}
