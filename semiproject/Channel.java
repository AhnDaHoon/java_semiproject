package semiproject;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Channel extends JFrame implements ActionListener {

	private static final String TITLE = null;
	ImageIcon iconback;
	JLabel lbback;

	JButton btnchannel, btnrank, btnchoose, btnback;

	ImageIcon icon, icon2, icon3, icon4, changeicon, changeicon2, changeicon3, changeicon4;
	Image img, img2, img3, img4, changeimg, changeimg2, changeimg3, changeimg4;

	String id;

	Music m;

	Channel(String id) {
		this.id = id;
		this.m = Login.m;
		setLayout(null);

		// 배경
		iconback = new ImageIcon("src\\Images\\back2.jpg");
		lbback = new JLabel(iconback);
		lbback.setBounds(0, 0, 800, 500);
		add(lbback);

		// 입장하기버튼
		icon = new ImageIcon("src/images/channel.png");
		img = icon.getImage();
		changeimg = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		changeicon = new ImageIcon(changeimg);
		btnchannel = new JButton(changeicon);
		btnchannel.setFocusPainted(false);
		btnchannel.setContentAreaFilled(false);
		btnchannel.setBorderPainted(false);

		btnchannel.setBounds(600, 30, 150, 50);

		lbback.add(btnchannel);

		// 랭킹보기버튼
		icon2 = new ImageIcon("src/images/rank.png");
		img2 = icon2.getImage();
		changeimg2 = img2.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		changeicon2 = new ImageIcon(changeimg2);
		btnrank = new JButton(changeicon2);
		btnrank.setFocusPainted(false);
		btnrank.setContentAreaFilled(false);
		btnrank.setBorderPainted(false);

		btnrank.setBounds(600, 100, 150, 50);

		lbback.add(btnrank);

		// 뽑기버튼
		icon3 = new ImageIcon("src/images/choose.png");
		img3 = icon3.getImage();
		changeimg3 = img3.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		changeicon3 = new ImageIcon(changeimg3);
		btnchoose = new JButton(changeicon3);
		btnchoose.setFocusPainted(false);
		btnchoose.setContentAreaFilled(false);
		btnchoose.setBorderPainted(false);

		btnchoose.setBounds(600, 170, 150, 50);

		lbback.add(btnchoose);

		// 뒤로가기버튼
		icon4 = new ImageIcon("src/images/back.jpg");
		img4 = icon4.getImage();
		changeimg4 = img4.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		changeicon4 = new ImageIcon(changeimg4);
		btnback = new JButton(changeicon4);

		btnback.setFocusPainted(false);
		btnback.setContentAreaFilled(false);
		btnback.setBorderPainted(false);

		btnback.setBounds(0, 0, 80, 80);

		lbback.add(btnback);

		btnchoose.addActionListener(this);
		btnchannel.addActionListener(this);
		btnrank.addActionListener(this);
		btnback.addActionListener(this);

		setTitle("대기방");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnchannel) {
			setVisible(false);
			new CharChoice(id);

		} else if (obj == btnback) {
			setVisible(false);
			new Login();
		} else if (obj == btnrank) {
			setVisible(false);
			new Rank(id);
		} else if (obj == btnchoose) {

			if (JOptionPane.showConfirmDialog(this, "광고를 보시고 뽑기를 하시겠습니까?", "뽑기", JOptionPane.YES_NO_OPTION) == 0) {
				m.stop();
				setVisible(false);
				App application = new App(TITLE, "src\\commercial\\commercial1.mp4", id);
				application.setVisible(true);
				application.loadVideo("src\\commercial\\commercial1.mp4");
				application.initialize();
			}
		}

	}
}
