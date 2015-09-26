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

public class patient extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					patient frame = new patient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public patient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
	}
}
