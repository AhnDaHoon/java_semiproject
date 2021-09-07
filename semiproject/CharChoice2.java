package semiproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class CharChoice2 extends JFrame implements ActionListener {

	JCheckBox cb1, cb2, cb3, cb4, cb5;
	ButtonGroup btnGroup;

	ImageIcon icon, icon2, icon3, changeicon, changeicon2, changeicon3;
	Image img, img2, img3, changeimg, changeimg2, changeimg3;

	JLabel lb1, lb2, lb3, lb4, lb5, lbtitle;
	JButton btnstart, btnchar, btnnext;

	JLabel lbname1, lbname2, lbname3, lbname4, lbname5;

	ImageIcon iconback;
	JLabel lbback;

	String str;

	Socket s;
	String id;

	public CharChoice2(String id) {
		this.id = id;

		setLayout(null);

		// 배경
		iconback = new ImageIcon("src\\Images\\b4.jpg");
		lbback = new JLabel(iconback);
		lbback.setBounds(0, 0, 800, 800);
		add(lbback);

		// 캐릭이름
		Font f2 = new Font("맑은 고딕", Font.BOLD, 15);

		lbname1 = new JLabel("럭셔리 다오", JLabel.CENTER);
		lbname1.setBackground(Color.black);
		lbname1.setForeground(Color.yellow);
		lbname1.setFont(f2);
		lbname1.setBounds(100, 330, 100, 30);
		lbback.add(lbname1);
		lbname1.setOpaque(true);

		lbname2 = new JLabel("슈퍼 배찌", JLabel.CENTER);
		lbname2.setBackground(Color.black);
		lbname2.setForeground(Color.yellow);
		lbname2.setFont(f2);
		lbname2.setBounds(350, 330, 100, 30);
		lbback.add(lbname2);
		lbname2.setOpaque(true);

		lbname3 = new JLabel("럭셔리 모스", JLabel.CENTER);
		lbname3.setBackground(Color.black);
		lbname3.setForeground(Color.yellow);
		lbname3.setFont(f2);
		lbname3.setBounds(600, 330, 100, 30);
		lbback.add(lbname3);
		lbname3.setOpaque(true);

		lbname4 = new JLabel("슈퍼 우니", JLabel.CENTER);
		lbname4.setBackground(Color.black);
		lbname4.setForeground(Color.yellow);
		lbname4.setFont(f2);
		lbname4.setBounds(210, 610, 100, 30);
		lbback.add(lbname4);
		lbname4.setOpaque(true);

		lbname5 = new JLabel("럭셔리 디지니", JLabel.CENTER);
		lbname5.setBackground(Color.black);
		lbname5.setForeground(Color.yellow);
		lbname5.setFont(f2);
		lbname5.setBounds(540, 610, 100, 30);
		lbback.add(lbname5);
		lbname5.setOpaque(true);

		// 캐릭터 선택
		icon2 = new ImageIcon("src/images/charchoice.png");
		img2 = icon2.getImage();
		changeimg2 = img2.getScaledInstance(290, 50, Image.SCALE_DEFAULT);
		changeicon2 = new ImageIcon(changeimg2);
		btnchar = new JButton(changeicon2);
		btnchar.setFocusPainted(false);
		btnchar.setContentAreaFilled(false);
		btnchar.setBorderPainted(false);
		btnchar.setBounds(250, 20, 290, 50);
		lbback.add(btnchar);

		// 게임시작버튼
		icon = new ImageIcon("src/images/gamestart.png");
		img = icon.getImage();
		changeimg = img.getScaledInstance(200, 80, Image.SCALE_DEFAULT);
		changeicon = new ImageIcon(changeimg);
		btnstart = new JButton(changeicon);
		btnstart.setFocusPainted(false);
		btnstart.setContentAreaFilled(false);
		btnstart.setBorderPainted(false);
		btnstart.setBounds(300, 660, 200, 80);
		lbback.add(btnstart);

		// 왼쪽버튼
		icon3 = new ImageIcon("src/images/next2.png");
		img3 = icon3.getImage();
		changeimg3 = img3.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		changeicon3 = new ImageIcon(changeimg3);
		btnnext = new JButton(changeicon3);
		btnnext.setFocusPainted(false);
		btnnext.setContentAreaFilled(false);
		btnnext.setBorderPainted(false);
		btnnext.setBounds(0, 350, 80, 80);
		lbback.add(btnnext);

		lb1 = new JLabel();
		lb2 = new JLabel();
		lb3 = new JLabel();
		lb4 = new JLabel();
		lb5 = new JLabel();

		lb1.setIcon(new ImageIcon("src\\Images\\user6.png"));

		cb1 = new JCheckBox("다오", true);
		cb1.setActionCommand("6");

		lb1.setBounds(50, 120, 200, 200);
		cb1.setBounds(40, 110, 20, 20);

		lb2.setIcon(new ImageIcon("src\\Images\\user7.png"));
		cb2 = new JCheckBox();
		cb2.setActionCommand("7");

		lb2.setBounds(300, 120, 200, 200);
		cb2.setBounds(280, 110, 20, 20);

		lb3.setIcon(new ImageIcon("src\\Images\\user8.png"));
		cb3 = new JCheckBox();
		cb3.setActionCommand("8");

		lb3.setBounds(540, 120, 200, 200);
		cb3.setBounds(520, 110, 20, 20);

		lb4.setIcon(new ImageIcon("src\\Images\\user9.png"));
		cb4 = new JCheckBox();
		cb4.setActionCommand("9");

		lb4.setBounds(160, 400, 200, 200);
		cb4.setBounds(140, 390, 20, 20);

		lb5.setIcon(new ImageIcon("src\\Images\\user10.png"));
		cb5 = new JCheckBox();
		cb5.setActionCommand("10");

		lb5.setBounds(460, 400, 200, 200);
		cb5.setBounds(440, 390, 20, 20);

		btnGroup = new ButtonGroup();

		btnGroup.add(cb1);
		btnGroup.add(cb2);
		btnGroup.add(cb3);
		btnGroup.add(cb4);
		btnGroup.add(cb5);
		lbback.add(cb1);
		lbback.add(cb2);
		lbback.add(cb3);
		lbback.add(cb4);
		lbback.add(cb5);

		lbback.add(lb1);
		lbback.add(lb2);
		lbback.add(lb3);
		lbback.add(lb4);
		lbback.add(lb5);

		btnstart.addActionListener(this);
		btnnext.addActionListener(this);

		setTitle("캐릭터 선택");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new CharChoice2("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnstart) {
			ButtonModel bm = btnGroup.getSelection();
			str = bm.getActionCommand();

			try {
				s = new Socket("localhost", 5000);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			setVisible(false);
			GameClient gc = new GameClient(s, id, str);
		}else if(obj == btnnext) {
			setVisible(false);
			new CharChoice(id);
		}
		
		

	}
}
