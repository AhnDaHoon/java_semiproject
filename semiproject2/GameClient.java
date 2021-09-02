package semiproject2;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import semiproject.Music;

public class GameClient extends JFrame implements ActionListener, Runnable, KeyListener {

	JPanel jplInput, jplUser, jplProblem;
	JPanel[] userPanels;
	JTextArea[] speechBubbles;
	JLabel[] userCharacters;
	JLabel[] nicknames;
	JTextArea jtaProblem;
	JTextField jtfInput;
	JButton jbtnSend;
	ImageIcon icon;

	Socket s;
	String id;

	String imagePlace;
	PrintWriter pw;
	BufferedReader br;

	JButton jbtnStart;

	ProblemNumberVO2 nvo;

	// 시간을 표시하는 라벨
	JLabel jlbTimeImage;

	// 문제 포인트 변수
	String point = "0";
	JLabel jlbPoint[] = new JLabel[5];
	

	// 문제 count
	int problemCount = 0;

	// 문제 중복방지를 위한 변수
	List<Integer> deduplication;

	// 
	boolean star = true;
	GameClient(Socket s, String id, String imagePlace) {
		this.s = s;
		this.id = id;
		this.imagePlace = imagePlace;

		setTitle("GameClient");

		setLayout(null);

		jplInput = new JPanel();

		jplUser = new JPanel();

		icon = new ImageIcon("src/images/problembg.png");

		jplProblem = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		
		userPanels = new JPanel[5];
		for (int i = 0; i < userPanels.length; i++) {
			userPanels[i] = new JPanel();
			userPanels[i].setVisible(false);
			userPanels[i].setLayout(null);
		}
		
		
		
		speechBubbles = new JTextArea[5];
		for (int i = 0; i < speechBubbles.length; i++) {
			speechBubbles[i] = new JTextArea();
			speechBubbles[i].setBackground(Color.black);
			speechBubbles[i].setForeground(Color.white);
			speechBubbles[i].setBounds(0, 0, 200, 50);
			speechBubbles[i].setEditable(false);
		}

		userCharacters = new JLabel[5];
		for (int i = 0; i < userCharacters.length; i++) {
			userCharacters[i] = new JLabel(new ImageIcon("src/images/user" + (i + 1) + ".png"));
			userCharacters[i].setBounds(0, 50, 200, 200);
		}

		nicknames = new JLabel[5];
		for (int i = 0; i < nicknames.length; i++) {
			nicknames[i] = new JLabel();
			nicknames[i].setHorizontalAlignment(JLabel.CENTER);
			nicknames[i].setBounds(0, 250, 200, 50);
		}

		jtfInput = new JTextField();
		jbtnSend = new JButton("Send");

		jtaProblem = new JTextArea("Problem ");
		jtaProblem.setEditable(false);
		// 포인트
		for (int i = 0; i < jlbPoint.length; i++) {
			jlbPoint[i] = new JLabel("0");
			jlbPoint[i].setFont(new Font(null, ABORT, 30));
			jlbPoint[i].setBounds(10+120*i, 10, 100, 80);
			add(jlbPoint[i]);
		}
		
		
		jplProblem.setBackground(Color.white);

		jtaProblem.setBounds(200, 150, 600, 150);
		jtaProblem.setBackground(new Color(255, 246, 226));
		jtaProblem.setFont(new Font("consolas", Font.BOLD, 15));

		jplProblem.setLayout(null);
		jplUser.setLayout(new GridLayout());

		jplInput.setBounds(0, 715, 1000, 50);
		jplUser.setBounds(0, 415, 1000, 300);
		jplProblem.setBounds(0, 0, 1000, 415);

		jtfInput.setBounds(100, 10, 600, 30);
		jbtnSend.setBounds(750, 10, 200, 30);


		
		for (int i = 0; i < userPanels.length; i++) {
			userPanels[i].add(speechBubbles[i]);
			userPanels[i].add(userCharacters[i]);
			userPanels[i].add(nicknames[i]);
			jplUser.add(userPanels[i]);
		}

		jplProblem.add(jtaProblem);

		jplInput.setLayout(null);

		jplInput.add(jtfInput);
		jplInput.add(jbtnSend);

		add(jplInput);
		add(jplUser);
		add(jplProblem);

		jbtnSend.addActionListener(this);
		jtfInput.addKeyListener(this);

		// 수정코드
		// 게임 시작
		jbtnStart = new JButton(new ImageIcon("src\\Images\\gamestart.gif"));

		// 시간
		jlbTimeImage = new JLabel();
		jlbTimeImage.setBounds(900, 50, 100, 100);
		jplProblem.add(jlbTimeImage);
		

		// 시작버튼
		jbtnStart.setBounds(0, 0, 50, 50);
		jplInput.add(jbtnStart);
		jbtnStart.setVisible(star);	
		



		jbtnStart.addActionListener(this);

		int x, y;

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		x = (int) (width / 2 - 1015 / 2);
		y = (int) (height / 2 - 800 / 2);

		setVisible(true);
		setBounds(x, y, 1015, 800);

		Thread th = new Thread(this);
		th.start();

		SmemberDAO smemberDAO = new SmemberDAO();
		String nickname = smemberDAO.getNickname(id);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pw.println(String.format("X:%s", nickname));
				pw.flush();
				System.exit(0);
			}
		});

		pw.println(String.format("E:%s:%s", nickname, imagePlace));
		pw.flush();
		smemberDAO.close();
	}

// public static void main(String[] args) {
// new GameClient();
// }

	@Override
	public void run() {
		try {
			// s = new Socket("localhost", 5000);
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));

			// client -> server
			// E:nickname:image
			// X:nickname
			// A:nickname:정답

			// server -> client
			// E:index:nickname
			// X:index
			// A:index:정답

			// 11: 11
			// 22:

			while (true) {
				String message = br.readLine();
				String[] split = message.split(":");
				String action = split[0];
				// int index = Integer.parseInt(split[1]);
				switch (action) {
				case "E": {
					String restMessage = split[1];
					String[] content = restMessage.split("\\|");
					for (String values : content) {
						String[] v = values.split(",");
						String nickname = v[0];
						int index = Integer.parseInt(v[1]);
						userPanels[index].setVisible(true);
						nicknames[index].setText(nickname);
					}
					break;
				}
				case "C": {
					String restMessage = split[1];
					String[] content = restMessage.split("\\|");
					for (String values : content) {
						String[] v = values.split(",");
						String imagePosition = v[0];
						int index = Integer.parseInt(v[1]);
						userCharacters[index].setIcon(new ImageIcon("src/images/" + imagePosition + ".png"));
					}
					break;
				}
				case "X":
					int index = Integer.parseInt(split[1]);
					userPanels[index].setVisible(false);
					nicknames[index].setText("");
					break;
				case "A":
					String answer = split[2];
					int index1 = Integer.parseInt(split[1]);
					speechBubbles[index1].setText(answer);
					break;
				case "P":
					String getProblem = split[1];
					jtaProblem.setText(getProblem + "\n\n");
					jtfInput.setEditable(true);
					break;
				case "N":
					
					String getNum = split[1];
					jtaProblem.append(getNum + "\n");
					break;
				case "Ok":
					// 포인트주기
					int index3 = Integer.parseInt(split[1]);
					int pointadd = Integer.parseInt(split[2]); // 100점
					int point = Integer.parseInt(jlbPoint[index3].getText());//현재점수
					
					jlbPoint[index3].setText(Integer.toString(pointadd + point));
					
					// index
					
					break;
				case "Time":
					int countdownStarter = Integer.parseInt(split[1]);
					
					jlbTimeImage.setIcon(new ImageIcon("src\\Images\\ball" + countdownStarter + "_2.png"));
					System.out.println(countdownStarter);
					break;
					
					
				case "noStart":
					int index4 = Integer.parseInt(split[1]);
					if(index4 > 0) {
						star = false;						
					}
				
				
				
				
				default: {
					JOptionPane.showMessageDialog(this, split[1]);
					System.exit(0);
				}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int pressKey = e.getKeyCode();
		if (pressKey == KeyEvent.VK_ENTER) {
			sendAnswer();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jbtnStart) {


//			 브금
//			Music m = new Music("music1.wav",true);
//			m.start();
			jbtnStart.setVisible(false);

			pw.println("S:123");
			pw.flush();

		} else if (obj == jbtnSend) {
			sendAnswer();
		}
	}

	private void sendAnswer() {
	
		// start버튼 눌러야 객체생성이됨
		// 사용자가 입력한 답이랑 정답 확인
		int userInput = Integer.parseInt(jtfInput.getText().trim());

		pw.println(String.format("A:nickname:%s", userInput));
		pw.flush();
		
		jtfInput.setText("");
		jtfInput.setEditable(false);

	}


}