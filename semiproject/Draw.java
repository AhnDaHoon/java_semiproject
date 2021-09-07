package semiproject;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Draw extends JFrame implements ActionListener {
	protected static final Component DRAW = null;
	int x;
	int y;
	int num1;
	BufferedImage img2 = null;
	JButton[] jbtn = new JButton[7];
	JLabel[] name = new JLabel[2];
	ImageIcon back3;
	JLabel lbback;
	int rnd;
	int rnd2;

	String id;
	Music m;
	class Panel extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(img2, 0, 0, null);
		}
	}

	Draw(String id) {
		this.m = Login.m;
		this.id = id;
		setTitle("도박");

		back3 = new ImageIcon("src\\Images\\bback3.jpg");
		lbback = new JLabel(back3);
		lbback.setBounds(0, 0, 800, 800);
		add(lbback);

		// 카드
		jbtn[0] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[0].setBounds(50, 50, 100, 200);
		jbtn[0].setBorderPainted(false);

		jbtn[1] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[1].setBounds(200, 50, 100, 200);
		jbtn[1].setBorderPainted(false);

		jbtn[2] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[2].setBounds(350, 50, 100, 200);
		jbtn[2].setBorderPainted(false);

		jbtn[3] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[3].setBounds(500, 50, 100, 200);
		jbtn[3].setBorderPainted(false);

		jbtn[4] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[4].setBounds(650, 50, 100, 200);
		jbtn[4].setBorderPainted(false);

		// 창 가운데
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();
		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 500 / 2);

		// 광고 시작 버튼

		jbtn[5] = new JButton(new ImageIcon("src//images//Draw2.jpg"));
		jbtn[5].setBounds(300, 360, 200, 80);

		add(jbtn[0]);
		add(jbtn[1]);
		add(jbtn[2]);
		add(jbtn[3]);
		add(jbtn[4]);
		add(jbtn[5]);
		add(lbback);

		// 캐릭터 선택 이벤트 2가지 당첨 됐을때와 꽝일때로 구분

		// 광고 시작 버튼 이벤트
		jbtn[5].addActionListener(this);

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 500);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (jbtn[5] == obj) {
			// 뽑기 이벤트

			rnd2 = 1;
			if (rnd2 == 1) {

				// 데이터베이스에 업데이트
				SkinVO vo = new SkinVO(id, "O");
				SkinDAO dao = new SkinDAO();
				dao.updateOne(vo);

				rnd = (int) (Math.random() * 5);
				jbtn[rnd].setIcon(back3);
				jbtn[5].setEnabled(false);
				setVisible(false);

				prizeOX(this.id, "src\\images\\O2.png");

			} else {
				jbtn[5].setEnabled(true);

				prizeOX(this.id, "src\\images\\X2.png");
				
				
				new Channel(this.id);
				m.start();
			}

		}

	}

	public void prizeOX(String id, String path) {
		shuffleGIF sfg = new shuffleGIF("src\\images\\shuffle.gif");
		shuffleGIF sfg2 = new shuffleGIF(path);
		sfg2.setVisible(false);
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable runnable = new Runnable() {
			int countdownStarter = 6;

			public void run() {
				countdownStarter--;
				System.out.println(countdownStarter);
				if (countdownStarter == 2) {
					sfg.setVisible(false);
					JOptionPane.showConfirmDialog(DRAW, "당첨\n히든캐릭터를 선택 하실 수 있습니다.", "당첨", JOptionPane.PLAIN_MESSAGE);

				} else if (countdownStarter <= 0) {
					sfg2.setVisible(false);
					new Channel(id);
					scheduler.shutdown();
				}
			}
		};
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);

	}

}