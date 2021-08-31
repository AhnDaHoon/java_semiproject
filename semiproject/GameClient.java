package semiproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GameClient extends JFrame implements ActionListener, Runnable{
	
	// 큰 틀을 나누는 패널(입력창, 유저창, 문제창)
	JPanel jplInput, jplUser, jplProblem;
	
	// 유저패널을 나누는 5개의 유저패널
	JPanel jplUser1, jplUser2, jplUser3, jplUser4, jplUser5;
	
	// 유저의 답안을 표시하는 TextArea
	JTextArea jtaUser1, jtaUser2, jtaUser3, jtaUser4, jtaUser5;
	
	// 유저의 캐릭터 사진을 표시하는 라벨
	JLabel jlbCharacter1, jlbCharacter2, jlbCharacter3, jlbCharacter4, jlbCharacter5;
	
	// 유저의 닉네임을 표시하는 라벨
	JLabel name1, name2, name3, name4, name5;
	
	// 문제를 표시할 TextArea
	JTextArea jtaProblem;
	
	// 답안을 입력할 텍스트필드
	JTextField jtfInput;
	
	// 답안을 전송할 텍스트필드
	JButton jbtnSend;
	
	// 문제패널 백그라운드 이미지
	ImageIcon icon;
	
	// 소켓
	Socket s;
	
	JButton jbtnStart;
	GameClient() {
		// 프레임의 타이틀
		setTitle("문제");
		
		// 배치관리자 없음
		setLayout(null);
		
		// 입력창 패널 초기화
		jplInput = new JPanel();
		// 유저창 패널 초기화
		jplUser = new JPanel();
		
		
		icon = new ImageIcon("src\\images\\problembg.png");
		// 문제창 패널 초기화
		jplProblem = new JPanel() {
			 public void paintComponent(Graphics g) {
	                g.drawImage(icon.getImage(), 0, 0, null);
	                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	                super.paintComponent(g);
	            }
		};
		
		
		
		
		
		// 유저 패널 초기화
		jplUser1 = new JPanel();
		jplUser2 = new JPanel();
		jplUser3 = new JPanel();
		jplUser4 = new JPanel();
		jplUser5 = new JPanel();
		
		// 말풍선 TextArea 초기화
		jtaUser1 = new JTextArea("User1 말풍선");
		jtaUser2 = new JTextArea("User2 말풍선");
		jtaUser3 = new JTextArea("User3 말풍선");
		jtaUser4 = new JTextArea("User4 말풍선");
		jtaUser5 = new JTextArea("User5 말풍선");
		
		// 캐릭터 라벨 초기화
		jlbCharacter1 = new JLabel(new ImageIcon("src\\Images\\user1.png"));
		jlbCharacter2 = new JLabel(new ImageIcon("src\\Images\\user2.png"));
		jlbCharacter3 = new JLabel(new ImageIcon("src\\Images\\user3.png"));
		jlbCharacter4 = new JLabel(new ImageIcon("src\\Images\\user4.png"));
		jlbCharacter5 = new JLabel(new ImageIcon("src\\Images\\user5.png"));
		
		// 닉네임 라벨 초기화
		name1 = new JLabel("User1");
		name2 = new JLabel("User2");
		name3 = new JLabel("User3");
		name4 = new JLabel("User4");
		name5 = new JLabel("User5");
		
		
		
		// 답안 입력 TextField와 답안 전송할 버튼 초기화
		jtfInput = new JTextField();
		jbtnSend = new JButton("전송");
		
		
		// 게임 시작
		jbtnStart = new JButton(new ImageIcon("src\\Images\\gamestart.gif"));
		
		
		
		// 닉네임 라벨 중앙 정렬
		name1.setHorizontalAlignment(JLabel.CENTER);
		name2.setHorizontalAlignment(JLabel.CENTER);
		name3.setHorizontalAlignment(JLabel.CENTER);
		name4.setHorizontalAlignment(JLabel.CENTER);
		name5.setHorizontalAlignment(JLabel.CENTER);

		
		
		// 문제
		jtaProblem = new JTextArea("문제가 입력될 곳 입니다.");
		
	
		jplUser.setBackground(Color.red);
		jplProblem.setBackground(Color.white);
		
		
		jtaUser1.setBackground(Color.black);
		jtaUser2.setBackground(Color.black);
		jtaUser3.setBackground(Color.black);
		jtaUser4.setBackground(Color.black);
		jtaUser5.setBackground(Color.black);

		jtaUser1.setForeground(Color.white);
		jtaUser2.setForeground(Color.white);
		jtaUser3.setForeground(Color.white);
		jtaUser4.setForeground(Color.white);
		jtaUser5.setForeground(Color.white);
		
		
		
		jplUser1.setLayout(null);
		jplUser2.setLayout(null);
		jplUser3.setLayout(null);
		jplUser4.setLayout(null);
		jplUser5.setLayout(null);

		
		
		
		
		// 컴포넌트 위치 및 사이즈 초기화
		jtaUser1.setBounds(0, 0, 200, 50);
		jtaUser2.setBounds(0, 0, 200, 50);
		jtaUser3.setBounds(0, 0, 200, 50);
		jtaUser4.setBounds(0, 0, 200, 50);
		jtaUser5.setBounds(0, 0, 200, 50);
	
		jlbCharacter1.setBounds(0, 50, 200, 200);
		jlbCharacter2.setBounds(0, 50, 200, 200);
		jlbCharacter3.setBounds(0, 50, 200, 200);
		jlbCharacter4.setBounds(0, 50, 200, 200);
		jlbCharacter5.setBounds(0, 50, 200, 200);
		
		name1.setBounds(0, 250, 200, 50);
		name2.setBounds(0, 250, 200, 50);
		name3.setBounds(0, 250, 200, 50);
		name4.setBounds(0, 250, 200, 50);
		name5.setBounds(0, 250, 200, 50);
		
		
		
		jtaProblem.setBounds(200, 150, 600, 100);
		jtaProblem.setBackground(new Color(255,246,226));
		jtaProblem.setFont(new Font("굴림체", Font.BOLD, 15));
		
		
		jplProblem.setLayout(null);
		jplUser.setLayout(new GridLayout());
		
		jplInput.setBounds(0, 715, 1000, 50);
		jplUser.setBounds(0, 415, 1000, 300);
		jplProblem.setBounds(0, 0, 1000, 415);
		
		
		jtfInput.setBounds(100,10, 600, 30);
		jbtnSend.setBounds(750,10,200,30);
		
		jbtnStart.setBounds(400, 100, 200, 200);
		add(jbtnStart);
		

		// 말풍선 
		jplUser1.add(jtaUser1);
		jplUser2.add(jtaUser2);
		jplUser3.add(jtaUser3);
		jplUser4.add(jtaUser4);
		jplUser5.add(jtaUser5);
		
		// 캐릭터
		jplUser1.add(jlbCharacter1);
		jplUser2.add(jlbCharacter2);
		jplUser3.add(jlbCharacter3);
		jplUser4.add(jlbCharacter4);
		jplUser5.add(jlbCharacter5);
		
		// 닉네임
		jplUser1.add(name1);
		jplUser2.add(name2);
		jplUser3.add(name3);
		jplUser4.add(name4);
		jplUser5.add(name5);
		
		

		// 각 유저 패널 유저패널에 붙이기
		jplUser.add(jplUser1);
		jplUser.add(jplUser2);
		jplUser.add(jplUser3);
		jplUser.add(jplUser4);
		jplUser.add(jplUser5);
		
		
		
		jplProblem.add(jtaProblem);
		
	
		jplInput.setLayout(null);
		
		jplInput.add(jtfInput);
		jplInput.add(jbtnSend);
		
		
		add(jplInput);
		add(jplUser);
		add(jplProblem);
		
		jbtnSend.addActionListener(this);
		jbtnStart.addActionListener(this);
		vchatting();
		
		
		// 모든 화면에서 같은위치에 프레임을 위치시키기위한 변수
		int x, y;
		
		// 해당 스크린의 사이즈 알아오기
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		double width = d.getWidth();
		double height = d.getHeight();
		
		x = (int) (width / 2 - 1015 /2);
		y = (int) (height/2 - 800 / 2);
		
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(x, y, 1015, 800);
	}

	
	public static void main(String[] args) {
		new GameClient();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnStart) {
			System.out.println("문제출제");
			jbtnStart.setVisible(false);
			
			
			// jtaProblem의 문자 삭제
			
			 jtaProblem.setText(""); // 문자열 초기화
			 
			 // 출제할 문제 받아오기
			 ProblemDAO dao = new ProblemDAO();
			 ProblemVO vo = dao.problem(new Random().nextInt(2)+1); // 문제 랜덤으로 뽑기위해서 Random클래스로 매개변수를 준다.
			 jtaProblem.append(vo.getProblem());
			 
			 // 출체 문제 보기 받아오기
			 ProblemNumberDAO ndao = new ProblemNumberDAO();
			 ProblemNumberVO nvo = ndao.problem(new Random().nextInt(2)+1);

			 // 객관식 간격을 위해 blank변수 생성
			 String blank = "      ";
			 // 객관식 받아요기
			 jtaProblem.append("\n\n\n\n " + nvo.getNumone() +blank+ nvo.getNumtwo() +blank+ nvo.getNumthree() +blank+ nvo.getNumfour());
		}
	}
	
	
	
	
	
	

	private void vchatting() {
		// 현재 클라이언트 프로그램을 여러개 띄울 수 있도록 멀티 쓰레드로 작성
	
		Thread th = new Thread(this);
		th.start();
		
	}

	@Override
	public void run() {
		try {
			s = new Socket("192.168.219.101", 5000);
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
	

	
	
	
}
