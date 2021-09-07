package semiproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {
	JButton jbtnSingup;
	JButton jbtnCancel;
	JButton jbtnIdDoubleCheck;
	JButton jbtnEmailDoubleCheck;
	JButton jbtnNicknameDoubleCheck;

	JLabel jlbId;
	JLabel jlbPw;
	JLabel jlbEmail;
	JLabel jlbName;
	JLabel jlbSecure;
	JLabel jlbSingup;
	JLabel jlbNickname;

	JTextField jtfId;
	JPasswordField jtfPw;
	JTextField jtfEmail;
	JTextField jtfName;
	JTextField jtfSecure;
	JTextField jtfNickname;

	JTextArea jta;

	Container c = getContentPane();

	JScrollPane jsp;
	ButtonGroup btnGroup;

	boolean isExist = true;

	BufferedImage img = null;

	boolean idOk = false, emailOk = false, nicknameOk = false;

	Music m;
	
	// JPanel 패널을 상속받은 패널 클래스

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	Register() {
		super("WELCOME");
		this.m = Login.m;
		// 컴포넌트 초기화

		// 배경 패널을 위해 레이아웃 풀고 background 패널 생성
		setLayout(null);
		JLayeredPane background = new JLayeredPane();
		background.setBounds(0, 0, 500, 375);
		background.setLayout(null);

		try {
			img = ImageIO.read(new File("src/images/back1.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 로딩 실패");
		}

		// j패널 클래스에 버튼 붙이기

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 500, 375);
		panel.setBackground(Color.BLUE);

		// 컴포넌트 초기화

		jbtnSingup = new JButton("Regist");

		jbtnCancel = new JButton("Cancel");
		jbtnIdDoubleCheck = new JButton("Check");
		jbtnEmailDoubleCheck = new JButton("Check");
		jbtnNicknameDoubleCheck = new JButton("Check");

		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jtfEmail = new JTextField();
		jtfName = new JTextField();
		jtfSecure = new JTextField();
		jtfNickname = new JTextField();

		jlbId = new JLabel("ID");
		jlbPw = new JLabel("PW");
		jlbEmail = new JLabel("Email");
		jlbNickname = new JLabel("NName");
		jlbName = new JLabel("Name");
		jlbSecure = new JLabel("SCode");
		jlbSingup = new JLabel("WELCOME");

		jta = new JTextArea();
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//		ImageIcon normalIcon = new ImageIcon("C:\\Users\\novit\\eclipse-workspace-dev\\Basic\\src\\image\\Regi1.gif");
//		ImageIcon rolloverIcon = new ImageIcon("C:\\Users\\novit\\eclipse-workspace-dev\\Basic\\src\\image\\Regi2.gif");
//		ImageIcon pressedIcon = new ImageIcon("C:\\Users\\novit\\eclipse-workspace-dev\\Basic\\src\\image\\Regi3.gif");

//		JButton jbtnSingup = new JButton("Regist");
//		jbtnSingup.setPressedIcon(pressedIcon); // pressedIcon용 이미지 등록
//		jbtnSingup.setRolloverIcon(rolloverIcon); // rolloverIcon용 이미지 등록

		// 체크박스 결과 값

		btnGroup = new ButtonGroup();

		// 모니터 해상도 구한 후 창 위치 가운데
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 500 / 2);
		int y = (int) (height / 2 - 375 / 2);

		// 컴포넌트 위치 조절

		jlbId.setBounds(100, 50, 150, 50);
		jlbPw.setBounds(100, 80, 150, 50);
		jlbEmail.setBounds(100, 110, 150, 50);
		jlbName.setBounds(100, 150, 150, 50);
		jlbSecure.setBounds(100, 180, 180, 50);
		jlbNickname.setBounds(100, 210, 180, 50);

		jlbSingup.setBounds(180, 10, 180, 50);

		jtfId.setBounds(160, 60, 150, 25);
		jtfPw.setBounds(160, 90, 150, 25);
		jtfEmail.setBounds(160, 120, 150, 25);
		jtfName.setBounds(160, 170, 150, 25);
		jtfSecure.setBounds(160, 200, 150, 25);
		jtfNickname.setBounds(160, 230, 150, 25);

		jbtnIdDoubleCheck.setBounds(320, 60, 80, 25);
		jbtnEmailDoubleCheck.setBounds(320, 120, 80, 25);
		jbtnNicknameDoubleCheck.setBounds(320, 230, 80, 25);
		jbtnSingup.setBounds(140, 300, 100, 25);
		jbtnCancel.setBounds(280, 300, 100, 25);

		// 폰트 색상 , 글꼴 조정

		jbtnIdDoubleCheck.setForeground(Color.BLACK);

		Font f = new Font("Bold", Font.CENTER_BASELINE, 15);
		Color c = new Color(126, 229, 210);

		Font f1 = new Font("Bold", Font.CENTER_BASELINE, 15);
		Color c1 = new Color(57, 77, 141);

		Font f2 = new Font("Bold", Font.CENTER_BASELINE, 30);
		Color c2 = new Color(57, 77, 141);

		jlbId.setFont(f);
		jlbPw.setFont(f);
		jlbEmail.setFont(f);
		jlbNickname.setFont(f);
		jlbName.setFont(f);
		jlbSecure.setFont(f);
		jlbSingup.setFont(f2);

		jlbId.setForeground(c);
		jlbPw.setForeground(c);
		jlbEmail.setForeground(c);
		jlbNickname.setForeground(c);
		jlbName.setForeground(c);
		jlbSecure.setForeground(c);
		jlbSingup.setForeground(c2);

		jbtnIdDoubleCheck.setForeground(c1);
		jbtnEmailDoubleCheck.setForeground(c1);
		jbtnNicknameDoubleCheck.setForeground(c1);

		jbtnIdDoubleCheck.setBackground(new Color(73, 213, 187));
		jbtnEmailDoubleCheck.setBackground(new Color(73, 213, 187));
		jbtnNicknameDoubleCheck.setBackground(new Color(73, 213, 187));

		jbtnSingup.setForeground(new Color(126, 229, 210));
		jbtnCancel.setForeground(new Color(126, 229, 210));

		jbtnSingup.setBackground(new Color(57, 77, 141));
		jbtnCancel.setBackground(new Color(57, 77, 141));

		jtfId.setOpaque(false);
		jtfPw.setOpaque(false);
		jtfEmail.setOpaque(false);
		jtfNickname.setOpaque(false);
		jtfName.setOpaque(false);
		jtfSecure.setOpaque(false);
		jta.setOpaque(false);

		background.add(jlbId);
		background.add(jlbPw);
		background.add(jlbEmail);
		background.add(jlbNickname);
		background.add(jlbName);
		background.add(jlbSingup);
		background.add(jlbSecure);

		background.add(jtfId);
		background.add(jtfPw);
		background.add(jtfEmail);
		background.add(jtfNickname);
		background.add(jtfName);
		background.add(jtfSecure);

		background.add(jsp);

		background.add(jbtnIdDoubleCheck);
		background.add(jbtnEmailDoubleCheck);
		background.add(jbtnNicknameDoubleCheck);
		background.add(jbtnSingup);
		background.add(jbtnCancel);

		background.add(panel);
		add(background);

		jbtnIdDoubleCheck.addActionListener(this);
		jbtnNicknameDoubleCheck.addActionListener(this);
		jbtnEmailDoubleCheck.addActionListener(this);

		jbtnCancel.addActionListener(this);
		jbtnSingup.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(x, y, 500, 375);

	}

	public static void main(String[] args) {
		new Register();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnCancel) {
			setVisible(false);
			new Login();
			m.stop();
		} else if (obj == jbtnSingup) {
			if (idOk == true && emailOk == true && nicknameOk == true) {
				String id = jtfId.getText().trim();
				String pw = jtfPw.getText().trim();
				String email = jtfEmail.getText().trim();
				String name = jtfName.getText().trim();
				String no = jtfSecure.getText().trim();
				String nickname = jtfNickname.getText().trim();

				SmemberDAO dao = new SmemberDAO();
				SmemberVO vo = new SmemberVO(id, pw, email, name, no, nickname);

				
				// 랭크테이블 추가
				dao.insertOne(vo);
				dao.close();
				RankDAO rankDAO = new RankDAO();
				RankVO rankVO = new RankVO(nickname, 0);
				rankDAO.insertOne(rankVO);
				rankDAO.close();
				
				
				// 스킨테이블 추가
				SkinDAO skinDAO = new SkinDAO();
				SkinVO skinVO = new SkinVO(id,"X");
				skinDAO.insertOne(skinVO);
				skinDAO.close();
				
				
				setVisible(false);
				new Login();
				m.stop();
			} else if (idOk == false) {
				JOptionPane.showConfirmDialog(this, "아이디 중복확인을 진행하세요.", "중복확인", JOptionPane.PLAIN_MESSAGE);
			} else if (emailOk == false) {
				JOptionPane.showConfirmDialog(this, "이메일 중복확인을 진행하세요.", "중복확인", JOptionPane.PLAIN_MESSAGE);
			} else if (nicknameOk == false) {
				JOptionPane.showConfirmDialog(this, "닉네임 중복확인을 진행하세요.", "중복확인", JOptionPane.PLAIN_MESSAGE);
			}

		} else if (obj == jbtnIdDoubleCheck) {

			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists(jtfId.getText().trim());
			if (isExist) {
				JOptionPane.showConfirmDialog(this, "아이디가 중복됩니다.", "아이디 중복확인", JOptionPane.PLAIN_MESSAGE);
			} else if (isExist == false) {

				if (jtfId.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "아이디 형식이 올바르지 않습니다 (공백을 입력할 수 없습니다.)", "아이디 중복확인",
							JOptionPane.PLAIN_MESSAGE);
				}else if ("".equals(jtfId.getText())) {
					JOptionPane.showConfirmDialog(this, "아이디를 입력해주세요", "아이디 중복확인", JOptionPane.PLAIN_MESSAGE);
				}
				else if (!jtfId.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "사용할 수 있는 아이디입니다.", "아이디 중복확인", JOptionPane.PLAIN_MESSAGE);
					jtfId.setEditable(false);
					idOk = true;
				}
			}

		} else if (obj == jbtnNicknameDoubleCheck) {

			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists3(jtfNickname.getText().trim());

			if (isExist) {

				JOptionPane.showConfirmDialog(this, "닉네임이 중복됩니다.", "닉네임 중복확인", JOptionPane.PLAIN_MESSAGE);
			} else if (isExist == false) {

				if (jtfNickname.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "닉네임 형식이 올바르지 않습니다 (공백을 입력할 수 없습니다.)", "닉네임 중복확인",
							JOptionPane.PLAIN_MESSAGE);
				} else if ("".equals(jtfNickname.getText())) {
					JOptionPane.showConfirmDialog(this, "닉네임을 입력해주세요", "닉네임 중복확인", JOptionPane.PLAIN_MESSAGE);
				}

				else if (!jtfNickname.getText().contains(" "))
					JOptionPane.showConfirmDialog(this, "사용할 수 있는 닉네임입니다", "닉네임 중복확인", JOptionPane.PLAIN_MESSAGE);
				jtfNickname.setEditable(true);
				nicknameOk = true;
			}
		} else if (obj == jbtnEmailDoubleCheck) {
			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists2(jtfId.getText().trim());

			if (isExist) {
				JOptionPane.showConfirmDialog(this, "이메일이 중복됩니다.", "이메일 중복확인", JOptionPane.PLAIN_MESSAGE);
			}

			else if (isExist == false) {

				if (jtfEmail.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "이메일 형식이 유효하지 않습니다 (공백을 입력할 수 없습니다.)", "이메일 중복확인",
							JOptionPane.PLAIN_MESSAGE);
				} else if (!jtfEmail.getText().contains("@")) {
					JOptionPane.showConfirmDialog(this, "이메일 형식이 유효하지 않습니다 (@가 포함되지 않았습니다.)", "이메일 중복확인",
							JOptionPane.PLAIN_MESSAGE);
				} else if (!jtfEmail.getText().contains(".com")) {
					JOptionPane.showConfirmDialog(this, "이메일 형식이 유효하지 않습니다 (도메인을 입력해주세요.)", "이메일 중복확인",
							JOptionPane.PLAIN_MESSAGE);
				} else if (jtfEmail.getText().contains(".com") && jtfEmail.getText().contains("@")
						&& !jtfEmail.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "확인되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);
					jtfEmail.setEditable(false);
					emailOk = true;
				}

			}
		}
	}
}
