package semiproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowRank extends JFrame implements ActionListener {

	JPanel backgroundPanel;
	ImageIcon backgroundIcon;

	JPanel rankPanel[] = new JPanel[5];
	JLabel character[] = new JLabel[5];
	JLabel nickname[] = new JLabel[5];
	JLabel score[] = new JLabel[5];
	int scoreFont[] = new int[5];

	// 점수와 점수에 해당하는 닉네임 캐릭터를 받아올 변수
	float scoreRank[] = new float[5];
	Map<Float, String> nicknameMap = new HashMap<>();
	Map<Float, Integer> characterMap = new HashMap<>();

	JButton exitButton;

	String id;

	// 캐릭터, 닉네임, 점수 받아와야함
	ShowRank(float[] scoreRank, Map<Float, String> nicknameMap, Map<Float, Integer> characterMap, String id) {
		this.scoreRank = scoreRank;
		this.nicknameMap = nicknameMap;
		this.characterMap = characterMap;
		this.id = id;

		// backgroundPanel
		backgroundIcon = new ImageIcon("src/images/ranking.jpg");

		backgroundPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundIcon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// rankPanel
		for (int i = 0; i < 5; i++) {
			rankPanel[i] = new JPanel();
			rankPanel[i].setSize(140, 220);
			rankPanel[i].setOpaque(false);
			backgroundPanel.add(rankPanel[i]);
			rankPanel[i].setLayout(null);
		}
		rankPanel[0].setLocation(430, 60);
		rankPanel[1].setLocation(285, 120);
		rankPanel[2].setLocation(575, 160);
		rankPanel[3].setLocation(100, 260);
		rankPanel[4].setLocation(760, 260);

		// character
		for (int i = 0; i < 5; i++) {
			character[i] = new JLabel();
//			String number = String.format("%d%d%d", (i + 1), (i + 1), (i + 1));
//			character[i].setIcon(new ImageIcon("src/images/user" + number + ".png"));
			character[i].setIcon(new ImageIcon("src/images/user11" + characterMap.get(scoreRank[i]) + ".png"));
			character[i].setBounds(0, 80, 140, 140);
			rankPanel[i].add(character[i]);
		}

		// nickname
		for (int i = 0; i < 5; i++) {
			nickname[i] = new JLabel();
			nickname[i].setText(nicknameMap.get(scoreRank[i]));
			nickname[i].setBounds(0, 40, 140, 40);
			nickname[i].setForeground(Color.white);
			if (i <= 2) {
				nickname[i].setFont(new Font("굴림체", Font.BOLD, 20));
			} else {
				nickname[i].setFont(new Font("굴림체", Font.BOLD, 16));
			}
			nickname[i].setHorizontalAlignment(JLabel.CENTER);
			nickname[i].setVerticalAlignment(JLabel.CENTER);
			rankPanel[i].add(nickname[i]);
		}

		// score
		for (int i = 0; i < 5; i++) {
			score[i] = new JLabel();
			score[i].setBounds(0, 0, 140, 40);
			score[i].setForeground(Color.white);
			score[i].setHorizontalAlignment(JLabel.CENTER);
			score[i].setVerticalAlignment(JLabel.CENTER);
			int scoreInt = (int) (scoreRank[i]);
			score[i].setText(Integer.toString(scoreInt));
			score[i].setVisible(false);
			rankPanel[i].add(score[i]);

			// scoreFont
			if (i != 4) {
				scoreFont[i] = 15 + (3 * (5 - i));
			} else {
				scoreFont[i] = 21;
			}
			score[i].setFont(new Font("굴림체", Font.PLAIN, scoreFont[i]));
		}

		exitButton = new JButton("EXIT");
		exitButton.setBounds(850, 0, 150, 100);
		Font f = new Font("consolas", Font.BOLD, 30);
		exitButton.setFont(f);
		exitButton.setForeground(Color.WHITE);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);

		backgroundPanel.add(exitButton);
		exitButton.addActionListener(this);

		// 본인의 닉네임과 점수
		SmemberDAO dao = new SmemberDAO();
		String myNickname = dao.getNickname(id);
		for (int i = 0; i < 5; i++) {
			if (myNickname.equals(nickname[i].getText())) {
				for (int j = 0; j < 5; j++) {
					if (i != j) {
						nickname[j].setForeground(new Color(250, 77, 103));
						score[j].setForeground(new Color(250, 77, 103));
					}
				}
				nickname[i].setForeground(new Color(148, 255, 108));
				score[i].setForeground(new Color(148, 255, 108));
				RankDAO rankDAO = new RankDAO();
				int usuallyScore = rankDAO.selectScore(myNickname);
				int plusScore = usuallyScore + Integer.parseInt(score[i].getText());
				rankDAO.updateScore(plusScore, myNickname);
				rankDAO.close();
			}
		}
		dao.close();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 560);
		setLocationRelativeTo(null);
		setVisible(true);
		Thread thScore = new ScoreThread();
		thScore.start();
	}

	// score를 표시할 쓰레드
	public class ScoreThread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				if (i < 4) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (Integer.parseInt(score[i].getText()) != 0) {
					score[i].setVisible(true);
				}
			}

			for (int i = 0; i < 2; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int j = 0; j < 5; j++) {
					int scoreFontSize = scoreFont[j] + 5;
					score[j].setFont(new Font("굴림체", Font.BOLD, scoreFontSize));
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int j = 0; j < 5; j++) {
					score[j].setFont(new Font("굴림체", Font.PLAIN, scoreFont[j]));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		new Channel(id);
	}

}
