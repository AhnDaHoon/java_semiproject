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

//import semiproject.Music;

public class GameClient extends JFrame implements ActionListener, Runnable, KeyListener {

	JPanel jplInput, jplUser, jplProblem;
	JPanel[] userPanels;
	JTextArea[] speechBubbles;
	JLabel[] userCharacters;
	JLabel[] nicknames;
	JTextArea jtaProblem;
	JTextField jtfInput, jtfInputchat;
	JButton jbtnSend, jbtnSendchat;
	ImageIcon icon;

	Socket s;
	String id;

	String imagePlace;
	PrintWriter pw;
	BufferedReader br;

	JButton jbtnStart;

	ProblemNumberVO2 nvo;

	// �ð��� ǥ���ϴ� ��
	JLabel jlbTimeImage;

	// ���� ����Ʈ ����
	String point = "0";

	// ���� count
	int problemCount = 0;

	// ���� �ߺ������� ���� ����
	List<Integer> deduplication;

	boolean inputBoolean = true;

	// ���ھ� ĳ���̹���
	JPanel[] facePanel;
	JLabel[] userCharface;
	JLabel[] facenick;
	JPanel jplfaceUser;
	JLabel[] jlbPoint = new JLabel[5];

	JButton jlbOX[] = new JButton[5];

	String nickname;

	// 2021.09.03 ���� ����޴� ���� �߰�
	String problemAnswer = "";

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
			userCharacters[i] = new JLabel();
			userCharacters[i].setBounds(0, 50, 200, 200);
		}

		nicknames = new JLabel[5];
		for (int i = 0; i < nicknames.length; i++) {
			nicknames[i] = new JLabel();
			nicknames[i].setHorizontalAlignment(JLabel.CENTER);
			nicknames[i].setBounds(0, 250, 200, 50);
		}

		jtfInput = new JTextField();
		// ���� �������� ä�� �� �� �ִ� ä��â
		jtfInputchat = new JTextField();

		jbtnSend = new JButton("Send");
		jbtnSendchat = new JButton("Sendchat");
		jtaProblem = new JTextArea("Problem ");
		jtaProblem.setLineWrap(true);
		jtaProblem.setEditable(false);

		jplProblem.setBackground(Color.white);

		jtaProblem.setBounds(200, 150, 600, 150);
		jtaProblem.setBackground(new Color(255, 246, 226));
		jtaProblem.setFont(new Font("����ü", Font.BOLD, 15));

		jplProblem.setLayout(null);
		jplUser.setLayout(new GridLayout());

		jplInput.setBounds(0, 715, 1000, 50);

		jplUser.setBounds(0, 415, 1000, 300);
		jplProblem.setBounds(0, 0, 1000, 415);

		jtfInput.setBounds(100, 10, 600, 30);
		jtfInputchat.setBounds(100, 10, 600, 30);
		jbtnSend.setBounds(750, 10, 200, 30);
		jbtnSendchat.setBounds(750, 10, 200, 30);

		for (int i = 0; i < userPanels.length; i++) {
			userPanels[i].add(speechBubbles[i]);
			userPanels[i].add(userCharacters[i]);
			userPanels[i].add(nicknames[i]);
			jplUser.add(userPanels[i]);
		}

		jplProblem.add(jtaProblem);

		jplInput.setLayout(null);
		jplInput.add(jtfInput);
		jplInput.add(jtfInputchat);

		// ���� ä��â�̹Ƿ� ������ ���۵Ǹ� true�� �ٲ� (jtfInput, jbtnSend)
		jtfInput.setVisible(false);
		jplInput.add(jbtnSend);
		jplInput.add(jbtnSendchat);
		jbtnSend.setVisible(false);
		add(jplInput);
		add(jplUser);
		add(jplProblem);

		// ����â �̺�Ʈ
		jbtnSend.addActionListener(this);
		jtfInput.addKeyListener(this);

		// ä��â �̺�Ʈ
		jbtnSendchat.addActionListener(this);
		jtfInputchat.addKeyListener(this);

		// �����ڵ�
		// ���� ����
		jbtnStart = new JButton(new ImageIcon("src\\Images\\gamestart.gif"));

		// �ð�
		jlbTimeImage = new JLabel();
		jlbTimeImage.setBounds(900, 50, 100, 100);
		jplProblem.add(jlbTimeImage);

		// ���۹�ư
		jbtnStart.setBounds(0, 0, 50, 50);
		jplInput.add(jbtnStart);

		jbtnStart.addActionListener(this);

		// ���ھ� ĳ���̹���
		// ����
		jlbPoint = new JLabel[5];
		for (int i = 0; i < jlbPoint.length; i++) {
			jlbPoint[i] = new JLabel("0");
			jlbPoint[i].setHorizontalAlignment(JLabel.CENTER);
			jlbPoint[i].setFont(new Font(null, ABORT, 30));
			jlbPoint[i].setBounds(75, 20, 100, 30);
		}

		// �г���
		facenick = new JLabel[5];
		for (int i = 0; i < facenick.length; i++) {
			facenick[i] = new JLabel();
			facenick[i].setHorizontalAlignment(JLabel.CENTER);
			facenick[i].setBounds(0, 55, 80, 30);
		}
		// �����г�
		facePanel = new JPanel[5];
		for (int i = 0; i < facePanel.length; i++) {
			facePanel[i] = new JPanel();
			facePanel[i].setVisible(false);
			facePanel[i].setLayout(null);
		}
		// ĳ�����̹���
		userCharface = new JLabel[5];
		for (int i = 0; i < userCharface.length; i++) {
			userCharface[i] = new JLabel();
			userCharface[i].setBounds(15, 0, 50, 50);
		}

		// ���ھ� ��ü�г�
		jplfaceUser = new JPanel();
		jplfaceUser.setLayout(new GridLayout());
		jplfaceUser.setBounds(0, 0, 1000, 80);
		jplProblem.add(jplfaceUser);

		for (int i = 0; i < facePanel.length; i++) {
			facePanel[i].add(userCharface[i]);
			facePanel[i].add(facenick[i]);
			facePanel[i].add(jlbPoint[i]);
			jplfaceUser.add(facePanel[i]);
			jplfaceUser.setBackground(new Color(255, 0, 0, 0)); // �гι�� �����ϰ�
//					facePanel[i].setBackground(new Color(255,0,0,0));
		}

		for (int i = 0; i < jlbOX.length; i++) {
			jlbOX[i] = new JButton();
			jlbOX[i].setBounds(0, 50, 200, 200);
			userPanels[i].add(jlbOX[i]);
			jlbOX[i].setVisible(false);
		}

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
		nickname = smemberDAO.getNickname(id);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					pw.println(String.format("X:%s", nickname));
					pw.flush();
				} catch (NullPointerException ne) {
					System.out.println("��������");
				}
				System.exit(0);
			}
		});

		try {
			pw.println(String.format("E:%s:%s", nickname, imagePlace));
			pw.flush();
		} catch (NullPointerException ne) {
			JOptionPane.showConfirmDialog(this, "���Ͽ����� Ȯ���ϼ���.", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
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
			// A:nickname:����

			// server -> client
			// E:index:nickname
			// X:index
			// A:index:����

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
						facePanel[index].setVisible(true);
						facenick[index].setText(nickname);

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
						userCharacters[index].setIcon(new ImageIcon("src/images/user" + imagePosition + ".png"));
						userCharface[index].setIcon(new ImageIcon("src/images/user1" + imagePosition + ".png"));
					}
					break;
				}
				case "X":
					int index = Integer.parseInt(split[1]);
					userPanels[index].setVisible(false);
					facePanel[index].setVisible(false);
					nicknames[index].setText("");
					repaint();
					break;
				case "A":
//					String answer = split[2];
					int index1 = Integer.parseInt(split[1]);
//					speechBubbles[index1].setText(answer);
					break;

				case "chat":
					String chat = split[2];
					int index2 = Integer.parseInt(split[1]);
					speechBubbles[index2].setText(chat);
					break;

				case "P":
					String getProblem = split[1];
					jtaProblem.setText(getProblem + "\n\n");
					jtfInput.setEditable(true);

					for (int i = 0; i < 5; i++) {
						try {
							jlbOX[i].setVisible(false);
							userCharacters[i].setVisible(true);
						} catch (NullPointerException ne) {

						}
					}

					break;
				case "N":
					String getNum = split[1];
					jtaProblem.append(getNum + "\n");
					break;
				case "Ok":
					// ����Ʈ�ֱ�
					int index3 = Integer.parseInt(split[1]);
					int pointadd = Integer.parseInt(split[2]); // 100��
					int point = Integer.parseInt(jlbPoint[index3].getText());// ��������

					jlbPoint[index3].setText(Integer.toString(pointadd + point));

					// index
					jlbOX[index3].setIcon(new ImageIcon("src\\images\\O.png"));
					userCharacters[index3].setVisible(false);
					jlbOX[index3].setVisible(true);

					break;
				case "Time":
					// ���� ��
//					int countdownStarter = Integer.parseInt(split[1]);
//
//					jlbTimeImage.setIcon(new ImageIcon("src\\Images\\ball" + countdownStarter + "_2.png"));
//					System.out.println(countdownStarter);
//					break;
					
					
					// ���� ��
					int countdownStarter = Integer.parseInt(split[1]);
					boolean jlbTimeImageVisible = Boolean.parseBoolean(split[2]);


					if(jlbTimeImageVisible) {
						jlbTimeImage.setIcon(new ImageIcon("src\\Images\\ball" + countdownStarter + "_2.png"));
						System.out.println(countdownStarter);
						jlbTimeImage.setVisible(jlbTimeImageVisible);
					}else {
						jlbTimeImage.setVisible(jlbTimeImageVisible);
						jtaProblem.setText("\t\t\t\t ����: "+problemAnswer+"��");
					}
					break;
				case "Start":
					jbtnStart.setVisible(false);

					// ����â
					jbtnSend.setVisible(true);
					jtfInput.setVisible(true);

					// ä��â
					jbtnSendchat.setVisible(false);
					jtfInputchat.setVisible(false);

					for (int i = 0; i < 5; i++) {
						speechBubbles[i].setText("");
					}

					inputBoolean = false;
					break;
				case "No":
					int index6 = Integer.parseInt(split[1]);
					jlbOX[index6].setIcon(new ImageIcon("src\\images\\X.png"));
					userCharacters[index6].setVisible(false);
					jlbOX[index6].setVisible(true);

					break;

				case "hurryUp":
					int index7 = Integer.parseInt(split[1]);
					String chatHurry = split[2];
					speechBubbles[index7].setText(chatHurry);
					break;
				case "answer2":
					problemAnswer = split[1];
					break;
					
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
			if (inputBoolean) {
				chat();
			} else {
				sendAnswer();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jbtnStart) {

//			 ���
//			Music m = new Music("music1.wav",true);
//			m.start();
			jbtnStart.setVisible(false);

			pw.println(String.format("S:%s", nickname));
			pw.flush();

		} else if (obj == jbtnSend) {
			sendAnswer();

		} else if (obj == jbtnSendchat) {
			chat();
		}
	}

	private void sendAnswer() {

		// start��ư ������ ��ü�����̵�
		// ����ڰ� �Է��� ���̶� ���� Ȯ��
		try {

			int userInput = Integer.parseInt(jtfInput.getText().trim());
			pw.println(String.format("A:nickname:%s", userInput));
			pw.flush();
			jtfInput.setEditable(false);
			jtfInput.setText("");

		} catch (NumberFormatException e) {
			JOptionPane.showConfirmDialog(this, "���ڸ� �Է��ϼ���.", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
			jtfInput.setText("");
		}

	}

	private void chat() {

		// start��ư �������� ������
		// ����ڰ� �Է��� ���ڿ� ���
		String userInput = jtfInputchat.getText().trim();
		pw.println(String.format("A2:chat:%s", userInput));
		pw.flush();
		jtfInputchat.setText("");

	}

}