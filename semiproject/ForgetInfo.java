package semiproject;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ForgetInfo extends JFrame implements ActionListener{
	JButton jbtnCancel;
	JButton jbtnEmailsend;
	
	JLabel jlbName;
	JLabel jlbEmail;
	JLabel jlbExplan;
	JTextField jtfName;
	JTextField jtfEmail;
	JScrollPane jsp;

	ForgetInfo() {
		setLayout(null);
		jbtnCancel = new JButton("Cancel");
		jbtnEmailsend = new JButton("Certificate");
		jtfName = new JTextField();
		jlbName = new JLabel("Name");
		jtfEmail = new JTextField();
		jlbEmail = new JLabel("Email");
		jlbExplan = new JLabel("Please enter the name and email you used to sign up.");
		jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		jlbExplan.setBounds(180, 80 , 500 ,100);
		jlbName.setBounds(100, 280, 300, 50);
		jlbEmail.setBounds(100, 180, 300, 50);
		jtfName.setBounds(220, 280, 300, 50);
		jtfEmail.setBounds(220, 180, 300 ,50 );
		jbtnEmailsend.setBounds(180, 400, 100, 50);
		jbtnCancel.setBounds(400, 400, 100, 50);
		jbtnEmailsend.setForeground(Color.red);
		
		add(jlbExplan);
		add(jlbName);
		add(jtfName);
		add(jtfEmail);
		add(jlbEmail);
		add(jbtnEmailsend);
		add(jbtnCancel);


	      Font f1 = new Font("Serif", Font.BOLD, 18);
	      jlbExplan.setFont(f1);
		
		jbtnCancel.addActionListener(this);
		jbtnEmailsend.addActionListener(this);
		
	    setTitle("Find My Info");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(300,150,700,550);
				
	}
	
	public static void main(String[] args) {
		ForgetInfo f = new ForgetInfo();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtnCancel) {
			System.exit(0);
		}else if(obj == jbtnEmailsend) {
			String Email =  jtfEmail.getText();
			String Name = jtfName.getText();
			
			
			
		}
		
		
	}

}
