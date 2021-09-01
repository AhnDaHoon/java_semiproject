package semiproject;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GameClient extends JFrame implements ActionListener, KeyListener{
	
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
	
	ProblemNumberVO nvo;
	
	// 시간을 표시하는 라벨
	JLabel jlbTimeImage;
	
	// 문제 포인트 변수
	int point;
	JLabel jlbPoint;
	JLabel jlbPointBar;
	
	// 문제 count
	int problemCount = 0;
	
	// 문제 중복방지를 위한 변수
	List<Integer> deduplication;
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
		jtaProblem.setEditable(false);
		
		// 시간
		jlbTimeImage = new JLabel();
	
		// 포인트
		jlbPoint = new JLabel("0");
		jlbPoint.setFont(new Font(null, ABORT, 30));
//		jlbPointBar = new JLabel(new ImageIcon("src\\Images\\heartAceFront.gif"));
		
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
		
		// 시작버튼
		jbtnStart.setBounds(400, 100, 200, 200);
		add(jbtnStart);

		// 포인트
		jlbPoint.setBounds(10, 10, 100, 100);
		add(jlbPoint);
		
//		문제 게이지바 
//		jlbPointBar.setBounds(0, 100, 30, 0);
//		add(jlbPoint);
		
		
		add(jlbTimeImage);
		
		
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
		jtfInput.addKeyListener(this);
		
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
			
			// 게임 제한시간
			gameTimeImage();
			
//			 브금
//			Music m = new Music("music1.wav",true);
//			m.start();
			jbtnStart.setVisible(false);

			
			
			// jtaProblem의 문자 삭제
			
			 jtaProblem.setText(""); // 문자열 초기화
			 
			 
			 // problemShuffle 쓰는 코드
			 // 문제의 중복을 막고 문제를 섞는 클래스
			 problemShuffle ps = new problemShuffle();
			 deduplication = ps.problemShuffleMethod(3);

			 
			 // 출제할 문제 받아오기
			 ProblemDAO dao = new ProblemDAO();
			 ProblemVO vo = dao.problem(deduplication.get(problemCount)); 
			 jtaProblem.append(vo.getProblem());
			 
			 // 출체 문제 보기 받아오기
			 ProblemNumberDAO ndao = new ProblemNumberDAO();
			 nvo = ndao.problem(deduplication.get(problemCount));

			 // 객관식 간격을 위해 blank변수 생성
			 String blank = "      ";
			 // 객관식 받아요기
			 jtaProblem.append("\n\n\n\n " + nvo.getNumone() +blank+ nvo.getNumtwo() +blank+ nvo.getNumthree() +blank+ nvo.getNumfour());
			 dao.close();
			 

			 
			 
			 
			 
		}else if(obj == jbtnSend) {
			answerEvent();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			answerEvent();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	public void answerEvent() {
		try {
			
			// start버튼 눌러야 객체생성이됨
			// 사용자가 입력한 답이랑 정답 확인
			int userInput = Integer.parseInt(jtfInput.getText().trim());
			int answer = nvo.getAnswerNo();
			
				
			// 정답을 맞출시 출력
			if(problemCount >= 2) {
				System.out.println("게임끝");
				
			}else {
				if(userInput == answer) {
					problemCount ++;
					
					System.out.println("정답");
					jtfInput.setText("");
					jtaProblem.setText("");
					
					
					// 맞추면 100포인트
					point += 100;
					jlbPoint.setText(Integer.toString(point));
					
					ProblemDAO dao = new ProblemDAO();
					ProblemVO vo = dao.problem(deduplication.get(problemCount)); 
					jtaProblem.append(vo.getProblem());
					 
					// 출체 문제 보기 받아오기
					ProblemNumberDAO ndao = new ProblemNumberDAO();
					nvo = ndao.problem(deduplication.get(problemCount));

					// 객관식 간격을 위해 blank변수 생성
					String blank = "      ";
					// 객관식 받아요기
					jtaProblem.append("\n\n\n\n " + nvo.getNumone() +blank+ nvo.getNumtwo() +blank+ nvo.getNumthree() +blank+ nvo.getNumfour());
					dao.close();
					
				}else {
					System.out.println("떙");
					jtfInput.setText("");
					
					// 문제가 틀리면 40점감점
					point -= 40;
					jlbPoint.setText(Integer.toString(point));
					
				}
			}
				
			
		}catch (NumberFormatException nfe) {
			JOptionPane.showConfirmDialog(this, "숫자만 입력하세요.", "확인", JOptionPane.PLAIN_MESSAGE);
		}catch (NullPointerException npe ) {
			JOptionPane.showConfirmDialog(this, "게임을 시작하세요.", "확인", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	
	public void gameTimeImage() {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 21;

            public void run() {

//                System.out.println(countdownStarter);
                countdownStarter--;
                jlbTimeImage.setBounds(900, 0, 100, 100);
                jlbTimeImage.setIcon(new ImageIcon("src\\Images\\ball"+countdownStarter+"_2.png"));
                if (countdownStarter < 0) {
                    System.out.println("Time Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

	

	


}
