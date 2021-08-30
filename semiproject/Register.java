package semiproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {
	JButton jbtnSingup;
	JButton jbtnCancel;
	JButton jbtnIdDoubleCheck;

	
	JLabel jlbId;
	JLabel jlbPw;
	JLabel jlbEmail;
	JLabel jlbName;
	JLabel jlbGender;
	JLabel jlbJoin;

	JTextField jtfId;
	JPasswordField jtfPw;
	JTextField jtfEmail;
	JTextField jtfName;
	JTextField jtf2;

	JTextArea jta;
	JScrollPane jsp;
	JCheckBox jcbMan;
	JCheckBox jcbWoman;

	
	
	Register() {
		
		super("회원가입");
		setLayout(null);
		jbtnSingup = new JButton("회원가입");
		jbtnCancel = new JButton("취소");
		jbtnIdDoubleCheck = new JButton("중복확인");

		
		
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jtfEmail = new JTextField();
		jtfName = new JTextField();

		
		
		jlbId = new JLabel("ID");
		jlbPw = new JLabel("PW");
		jlbEmail = new JLabel("Email");
		jlbName = new JLabel("이름");
		jlbGender = new JLabel("성별");
		jlbJoin = new JLabel("가입동기");
		
		
		jta = new JTextArea();
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		jcbMan = new JCheckBox("남자");
		jcbWoman = new JCheckBox("여자");

		jlbId.setBounds(100, 80, 300, 50);
		jlbPw.setBounds(100, 150, 300, 50);
		jlbEmail.setBounds(100, 220, 300, 50);
		jlbName.setBounds(100, 280, 300, 50);
		jlbGender.setBounds(100, 350, 300, 50);
		jlbJoin.setBounds(80, 550, 300, 50);

		
		
		jtfId.setBounds(220, 80, 300, 50);
		jtfPw.setBounds(220, 150, 300, 50);
		jtfEmail.setBounds(220, 220, 300, 50);
		jtfName.setBounds(220, 280, 300, 50);
		jta.setBounds(200, 400, 500, 400);

		jcbMan.setBounds(200, 350, 50, 50);
		jcbWoman.setBounds(300, 350, 50, 50);

		jbtnIdDoubleCheck.setBounds(550, 80, 100, 50);
		jbtnSingup.setBounds(250, 900, 100, 50);
		jbtnCancel.setBounds(450, 900, 100, 50);

		jbtnIdDoubleCheck.setForeground(Color.red);
		add(jlbId);
		add(jlbPw);
		add(jlbEmail);
		add(jlbName);
		add(jlbGender);
		add(jlbJoin);

		add(jtfId);
		add(jtfPw);
		add(jtfEmail);
		add(jtfName);

		add(jta);

		add(jcbMan);
		add(jcbWoman);

		add(jbtnIdDoubleCheck);
		add(jbtnSingup);
		add(jbtnCancel);
		
		jbtnIdDoubleCheck.addActionListener(this);
		jbtnCancel.addActionListener(this);
		jbtnSingup.setBackground(Color.lightGray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(0, 0, 800, 1200);

	}

	public static void main(String[] args) {
		Register r = new Register();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnCancel) {
			System.exit(0);
		} else if (obj == jbtnSingup) {
			String Id = jtfId.getText();
			String PW = jtfPw.getText();
			String name = jtfName.getText();
//		}else if() {
			
		}

	}

}