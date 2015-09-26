package com;

        import javax.swing.*;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class RegistrationGUI extends JApplet implements ActionListener{

    Container content;
    JLabel regLabel;
    JLabel blankspace1;
    JLabel blankspace2; //there's got to be a better way to do this
    JLabel phoneLabel;
    JTextField phoneField;
    JLabel unameLabel;
    JTextField unameField;
    JLabel passLabel;
    JPasswordField passField;
    JLabel passConfLabel;
    JPasswordField passConfField;
    JLabel nameLabel;
    JTextField nameField;
    JLabel emailLabel;
    JTextField emailField;
    JLabel addressLabel;
    JTextField addressField;
    JPanel panelCenter;
    JPanel panelSouth;
    JRadioButton patientButton;
    JRadioButton doctorButton;
    JRadioButton nurseButton;
    JRadioButton labTechButton;
    ButtonGroup groupOfButtons;
    Boolean finishAdded;
    Boolean patientPressed;
    JLabel ssnLabel;
    JTextField ssnField;
    JLabel medHistLabel;
    JTextField medHistField;
    public void init(){
        content = getContentPane();
        content.setLayout(new BorderLayout());
        regLabel = new JLabel("Registration", SwingConstants.CENTER);
        blankspace1 = new JLabel("");
        blankspace2 = new JLabel("");
        phoneLabel = new JLabel("Phone Number: ");
        phoneField = new JTextField(2); //does the 2 actually do anything? if so, what??
        unameLabel = new JLabel("Username: ");
        unameField = new JTextField(20); //the 20 is probably unnecessary, or just wrong
        passLabel = new JLabel("Password: "); //requirements for password????
        passField = new JPasswordField();
        passConfLabel = new JLabel("Password Confirmation: ");
        passConfField = new JPasswordField();
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(); //first, last, middle????
        emailLabel = new JLabel("Email Address: ");
        emailField = new JTextField();
        addressLabel = new JLabel("Home Address: ");
        addressField = new JTextField();
        panelCenter = new JPanel(new GridLayout(16, 2)); //so that's how that works. weird.
        panelCenter.add(blankspace1);
        panelCenter.add(blankspace2);
        panelCenter.add(phoneLabel);
        panelCenter.add(phoneField);
        panelCenter.add(unameLabel);
        panelCenter.add(unameField);
        panelCenter.add(passLabel);
        panelCenter.add(passField);
        panelCenter.add(passConfLabel);
        panelCenter.add(passConfField);
        panelCenter.add(nameLabel);
        panelCenter.add(nameField);
        panelCenter.add(emailLabel);
        panelCenter.add(emailField);
        panelCenter.add(addressLabel);
        panelCenter.add(addressField);
        panelSouth = new JPanel(new FlowLayout());
        patientButton = new JRadioButton("Patient");
        doctorButton = new JRadioButton("Doctor");
        nurseButton = new JRadioButton("Nurse");
        labTechButton = new JRadioButton("Lab Tech");
        groupOfButtons = new ButtonGroup();
        groupOfButtons.add(patientButton);
        groupOfButtons.add(doctorButton);
        groupOfButtons.add(nurseButton);
        groupOfButtons.add(labTechButton);
        panelCenter.add(patientButton);
        panelCenter.add(doctorButton);
        panelCenter.add(nurseButton);
        panelCenter.add(labTechButton);
        patientButton.addActionListener(this); //so this is apparently correct
        doctorButton.addActionListener(this);
        nurseButton.addActionListener(this);
        labTechButton.addActionListener(this);
        content.add(panelCenter, BorderLayout.CENTER);
        content.add(regLabel, BorderLayout.NORTH);
        content.add(panelSouth, BorderLayout.SOUTH);
        finishAdded = false;
        patientPressed = false;
        ssnLabel = new JLabel("Social Security Number: ");
        ssnField = new JTextField();
        medHistLabel = new JLabel("Medical History: ");
        medHistField = new JTextField(); //this is obviously super incomplete, but I wasn't
        //sure what all we needed
        //content.add(button4, BorderLayout.WEST);
        //content.add(button5, BorderLayout.EAST);

        setSize(300, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //the thing before is correct only in conjunction
        //with this, placed here
        if(e.getSource() == patientButton){
            System.out.println("patientpressed");
            panelCenter.add(ssnLabel);
            panelCenter.add(ssnField);
            panelCenter.add(medHistLabel);
            panelCenter.add(medHistField);
            content.revalidate(); //these lines fix the only-update-after-resize
            content.repaint();    //problem
            patientPressed = true;
            if(finishAdded == false){
                panelSouth.add(new JButton("Finished"));
                content.add(panelSouth, BorderLayout.SOUTH);
                content.revalidate(); //these lines fix the only-update-after-resize
                content.repaint();    //problem
                finishAdded = true;
            }

        }
        else{
            System.out.println("otherpressed");
            if(finishAdded == false){
                panelSouth.add(new JButton("Finished"));
                content.add(panelSouth, BorderLayout.SOUTH);
                content.revalidate(); //these lines fix the only-update-after-resize
                content.repaint();    //problem
                finishAdded = true;
            }
            if(patientPressed == true){
                panelCenter.remove(ssnLabel);
                panelCenter.remove(ssnField);
                panelCenter.remove(medHistLabel);
                panelCenter.remove(medHistField);
                content.revalidate(); //these lines fix the only-update-after-resize
                content.repaint();    //problem
                patientPressed = false;
            }
        }



    }
}
