package semiproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import semiproject2.Music;

public class Login extends JFrame implements ActionListener, KeyListener {
	JLabel jlbId, jlbPw, jlbTitle;
	JTextField jtfId;
	JPasswordField jtfPw;
	JButton jbtnLogin, jbtnRegister, jbtnForget, jbtnexit;
	BufferedImage img = null;
	
	ImageIcon changeicon2, icon2;
	Image img2, changeimg2;
	
	

	// 서버와 연결
	String id, pw;

	// 브금
	public static Music m;
	
	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	Login() {
		// 배경 패널 레이아웃 풀기
		setLayout(null);
		JLayeredPane background = new JLayeredPane();
		background.setBounds(0, 0, 1000, 780);
		background.setLayout(null);

		try {
			img = ImageIO.read(new File("src/images/logf.png"));
			
		} catch (IOException e) {
			System.out.println("이미지 로딩 실패");
		}

		// j패널 클래스 따로 만들어서 버튼 붙이기

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1000, 780);
		//// 2021/09/03 추가 아이디 비밀번호 틀렸을때 소리
//		Music m = new Music("errorsound.wav",false);
//		m.start();
		
		// 컴포넌트 초기화
		jlbTitle = new JLabel("SQLD Quiz!", JLabel.CENTER);
		jlbId = new JLabel("ID", JLabel.CENTER);
		jlbPw = new JLabel("PW", JLabel.CENTER);
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jbtnLogin = new JButton();
		jbtnRegister = new JButton();
		jbtnexit = new JButton();


		// 아이디비번찾기 버튼
		icon2 = new ImageIcon("src/images/Forget.png");
		img2 = icon2.getImage();
		changeimg2 = img2.getScaledInstance(260, 150, Image.SCALE_DEFAULT);
		changeicon2 = new ImageIcon(changeimg2);
		jbtnForget = new JButton(changeicon2);
		jbtnForget.setFocusPainted(false);
		jbtnForget.setContentAreaFilled(false);
		jbtnForget.setBorderPainted(false);
		jbtnForget.setBounds(5, 690, 260, 50);
		background.add(jbtnForget);
		
		
		
		// 컴포넌트 위치 사이즈 지정


		jlbTitle.setBounds(285,498, 420, 50);
		jlbId.setBounds(375, 590, 50, 50);
		jlbPw.setBounds(375, 623, 50, 50);

		jtfId.setBounds(458, 601, 195, 30);
		jtfPw.setBounds(458, 634, 195, 30);

		
		jbtnLogin.setBounds(431, 693, 123, 35);
		jbtnRegister.setBounds(295, 693, 121, 35);
		jbtnexit.setBounds(568, 693, 122, 35);
		

		// 폰트 및 색상
		Font f = new Font("맑은 고딕", Font.BOLD, 20);

		jlbTitle.setForeground(new Color(255, 255, 255));
		jlbTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		
		jlbId.setForeground(new Color(255, 255, 255));
		jlbId.setFont(f);

		
		jlbPw.setForeground(new Color(255, 255, 255));
		jlbPw.setFont(f);


		// 컨테이너 부착


		background.add(jlbTitle);
		background.add(jlbId);
		background.add(jlbPw);

		background.add(jtfId);
		background.add(jtfPw);

		jbtnLogin.setContentAreaFilled(false);    // 버튼 투명하게
		jbtnRegister.setContentAreaFilled(false);   
		jbtnexit.setContentAreaFilled(false);  
		background.add(jbtnLogin);
		background.add(jbtnRegister);
		background.add(jbtnexit);

		background.add(panel);
		add(background);

		// 이벤트 처리
		jbtnLogin.addActionListener(this);
		jbtnRegister.addActionListener(this);
		jbtnForget.addActionListener(this);
		jbtnexit.addActionListener(this);
		// 2021/09/03 추가 엔터키를 눌렀을때 로그인 기능
		jtfId.addKeyListener(this);
		jtfPw.addKeyListener(this);
		jbtnLogin.addKeyListener(this);
		

		setResizable(false);
		setTitle("로그인");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,780);
		setLocationRelativeTo(null);
		setVisible(true);
		
//		 브금
		m = new Music("music1.wav",true);
		m.start();
	}

	public static void main(String[] args) {
		Login login = new Login();
//		login.setIconImage(img2.getImage()); // 프레임 바 아이콘 변경
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if(obj == jbtnexit) {
			System.exit(0);
		}
		else if (obj == jbtnRegister) {
			setVisible(false);
			Register rg = new Register();
		} else if (obj == jbtnLogin) {
			
			SmemberDAO dao = new SmemberDAO();
			id = jtfId.getText();
			pw = jtfPw.getText();
			boolean flag = dao.isExists(id, pw);
			dao.close();
			if (flag) {
				setVisible(false);
				
				new Channel(id);
				

			} else {
				// 2021/09/03 추가 에러 사운드
				Music m = new Music("errorsound2.wav",true);
				m.start();
				JOptionPane.showConfirmDialog(this, "ID 또는 PW를 확인하세요.", "로그인",
				JOptionPane.PLAIN_MESSAGE);
			}

		} else if (obj == jbtnForget) {
			setVisible(false);
			ComboBox cb = new ComboBox();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 2021/09/03 추가 엔터키를 눌렀을때 로그인 기능, 에러 사운드
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			SmemberDAO dao = new SmemberDAO();
			id = jtfId.getText();
			pw = jtfPw.getText();
			boolean flag = dao.isExists(id, pw);
			dao.close();
			if (flag) {
				setVisible(false);
				
				new Channel(id);
			} else {
				Music m = new Music("errorsound2.wav",true);
				m.start();
				JOptionPane.showConfirmDialog(this, "ID 또는 PW를 확인하세요.", "로그인",
				JOptionPane.PLAIN_MESSAGE);
			}

			
		}

		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
