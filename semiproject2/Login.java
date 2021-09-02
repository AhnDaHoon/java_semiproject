package semiproject2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener, KeyListener {
	JLabel jlbId, jlbPw;
	JTextField jtfId;
	JPasswordField jtfPw;
	JButton jbtnLogin, jbtnRegister, jbtnForget;
	BufferedImage img = null;
	
	
	

	// 서버와 연결
	Socket s;
	String id, pw;

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
		background.setBounds(0, 0, 800, 600);
		background.setLayout(null);

		try {
			img = ImageIO.read(new File("src/images/logf.png"));
		} catch (IOException e) {
			System.out.println("이미지 로딩 실패");
		}

		// j패널 클래스 따로 만들어서 버튼 붙이기

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 800, 600);

		// 컴포넌트 초기화
		jlbId = new JLabel("ID  :   ");
		jlbPw = new JLabel("PW :  ");
		jtfId = new JTextField("User1");
		jtfPw = new JPasswordField("1");
		jbtnLogin = new JButton("Login");
		jbtnRegister = new JButton("Register");
		jbtnForget = new JButton("Forget ID Or Pw?");//

		// 컴포넌트 위치 사이즈 지정

		jlbId.setBounds(460, 190, 300, 50);
		jlbPw.setBounds(450, 250, 300, 50);

		jtfId.setBounds(550, 200, 200, 30);
		jtfPw.setBounds(550, 260, 200, 30);
		
		
		jbtnLogin.setBounds(450, 320, 150, 50);
		jbtnRegister.setBounds(600, 320, 150, 50);
		jbtnForget.setBounds(550, 400, 200, 50);

		// 폰트 및 색상

		jlbId.setForeground(new Color(63,72,204));
		jlbId.setFont(new Font("Ravie", Font.BOLD, 30));

		jlbPw.setForeground(new Color(63,72,204));
		jlbPw.setFont(new Font("Ravie", Font.BOLD, 30));

		jbtnLogin.setBackground(new Color(46, 154, 235));
		jbtnLogin.setFont(new Font("Ravie", Font.BOLD, 25));
		jbtnLogin.setForeground(Color.WHITE);

		jbtnRegister.setBackground(new Color(46, 154, 235));
		jbtnRegister.setFont(new Font("Ravie", Font.BOLD, 18));
		jbtnRegister.setForeground(Color.WHITE);
		
		

		jbtnForget.setBackground(new Color(46, 154, 235));
		jbtnForget.setFont(new Font("Ravie", Font.BOLD, 10));
		jbtnForget.setForeground(Color.WHITE);

		// 모니터 해상도 구한 후 창 위치 가운데
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 600 / 2);

		// 컨테이너 부착


		background.add(jlbId);
		background.add(jlbPw);

		background.add(jtfId);
		background.add(jtfPw);

		background.add(jbtnLogin);
		background.add(jbtnRegister);
		background.add(jbtnForget);

		background.add(panel);
		add(background);

		// 이벤트 처리
		jbtnLogin.addActionListener(this);
		jbtnRegister.addActionListener(this);
		jbtnForget.addActionListener(this);

		// 엔터키누르면 로그인을 위해 만듬, 초점에의해 기능이 사용
		jtfId.addKeyListener(this);
		jtfPw.addKeyListener(this);
		jbtnLogin.addKeyListener(this);
		
		
		setResizable(false);
		setTitle("로그인");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		Login login = new Login();
//		login.setIconImage(img2.getImage()); // 프레임 바 아이콘 변경
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == jbtnRegister) {
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
				
				try {
					s = new Socket("localhost", 5000);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CharChoice cc = new CharChoice(s, id);
				

			} else {
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
		int key = e.getKeyCode(); 
		if(key == KeyEvent.VK_ENTER) {
			SmemberDAO dao = new SmemberDAO();
			id = jtfId.getText();
			pw = jtfPw.getText();
			boolean flag = dao.isExists(id, pw);
			dao.close();
			if (flag) {
				setVisible(false);
				
				try {
					s = new Socket("localhost", 5000);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CharChoice cc = new CharChoice(s, id);
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
