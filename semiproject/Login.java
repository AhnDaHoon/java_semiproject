package semiproject;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	JLabel jlbId, jlbTitle, jlbPw;
	JTextField jtfId;
	JPasswordField jtfPw;
	JButton jbtnLogin, jbtnRegister, jbtnForget;
	TCanvass can;
	JScrollPane scrollPane;
	ImageIcon icon;

	Login() {
		setLayout(null);

		// 배경화면
		icon = new ImageIcon("src/image/p2.jpg");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		jlbTitle = new JLabel("Sql League");
		jlbId = new JLabel("ID");
		jlbPw = new JLabel("PW");
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jbtnLogin = new JButton("Login");
		jbtnRegister = new JButton("Register");
		jbtnForget = new JButton("Forget ID Or Pw?");//

		can = new TCanvass(jlbId, jlbPw, jlbTitle, jtfId, jtfPw, jbtnForget, jbtnForget, jbtnForget);

		
		// 컴포넌트 위치 사이즈 지정
		can.jlbId.setBounds(300, 50, 300, 80);
		setBounds(200, 130, 100, 50);
	
		jtfId.setBounds(400, 140, 200, 50);
		jtfPw.setBounds(400, 250, 200, 50);
		jbtnLogin.setBounds(250, 350, 100, 50);
		jbtnRegister.setBounds(450, 350, 100, 50);
		jbtnForget.setBounds(325, 450, 150, 50);//

		// 폰트
		Font f = new Font("굴림체", Font.BOLD, 20);
		jtfId.setFont(f);
		jtfPw.setFont(f);
		Font f1 = new Font("Serif", Font.BOLD, 30);
		jlbTitle.setFont(f1);

		// 모니터 해상도 구한 후 창 위치 가운데
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 600 / 2);

		// 컨테이너 부착
		background.add(can);
		background.add(jlbPw);
		background.add(jtfId);
		background.add(jtfPw);
		background.add(jbtnLogin);
		background.add(jbtnRegister);

		// 이벤트 처리
		jbtnLogin.addActionListener(this);
		jbtnRegister.addActionListener(this);

		setResizable(false);
		setTitle("로그인");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

	}
}
