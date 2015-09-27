package com.g7;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.JTextField;

/**
 * Window class used to display all GUI
 */
public class WindowManager extends JFrame {

    private JPanel contentPane;

    //Creates main window
    public WindowManager(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 705, 511);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        this.setVisible(true);
        ShowLogin();
    }

    //Show Login page
    public void ShowLogin(){
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content pane
        JButton btnMedicalHistory = new JButton("Medical History");
        btnMedicalHistory.setBounds(54, 184, 139, 23);
        contentPane.add(btnMedicalHistory);

        //Reapply panel
        setContentPane(contentPane);
    }

    //Show Registration page
    public void ShowRegister(){
        //Clear old content pane
        contentPane.removeAll();

        //Apply changes to content pane
        JButton btnNewButton = new JButton("Update Medical Status");
        btnNewButton.setBounds(264, 184, 139, 23);
        contentPane.add(btnNewButton);

        //Reapply panel
        setContentPane(contentPane);
    }

    //Show Patient Dashboard
    public void ShowPatientDashboard(){

    }

    //Show Doctor Dashboard
    public void ShowDoctorDashboard(){

    }
}
