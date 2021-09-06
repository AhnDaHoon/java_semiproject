package semiproject2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CharChoice extends JFrame implements ActionListener{

	JCheckBox cb1, cb2, cb3, cb4, cb5;
	ButtonGroup btnGroup;
	ImageIcon icon;
	JLabel lb1, lb2, lb3, lb4, lb5, lbtitle;
	JButton btnstart;
	
	JLabel lbname1, lbname2, lbname3, lbname4, lbname5;
	
	ImageIcon iconback;
	JLabel lbback;
	
	String str;
	
	Socket s;
	String id;
	
	public CharChoice(String id) {
		this.id = id;
		
		setLayout(null);
		
		//배경
		iconback = new ImageIcon("src\\Images\\b2.jpg");
		lbback = new JLabel(iconback);
		lbback.setBounds(0, 0, 800, 800);
		add(lbback);
		
		// 캐릭이름
		Font f2 = new Font("맑은 고딕", Font.BOLD, 15);
		
		lbname1 = new JLabel("다 오",JLabel.CENTER);
		lbname1.setBackground(Color.WHITE);
		lbname1.setFont(f2);
		lbname1.setBounds(100, 330, 70, 30);
		lbback.add(lbname1);
		lbname1.setOpaque(true);
		
		lbname2 = new JLabel("배 찌",JLabel.CENTER);
		lbname2.setBackground(Color.WHITE);
		lbname2.setFont(f2);
		lbname2.setBounds(350, 330, 70, 30);
		lbback.add(lbname2);
		lbname2.setOpaque(true);
		
		lbname3 = new JLabel("모 스",JLabel.CENTER);
		lbname3.setBackground(Color.WHITE);
		lbname3.setFont(f2);
		lbname3.setBounds(600, 330, 70, 30);
		lbback.add(lbname3);
		lbname3.setOpaque(true);
		
		lbname4 = new JLabel("우 니",JLabel.CENTER);
		lbname4.setBackground(Color.WHITE);
		lbname4.setFont(f2);
		lbname4.setBounds(240, 610, 70, 30);
		lbback.add(lbname4);
		lbname4.setOpaque(true);
		
		lbname5 = new JLabel("디지니",JLabel.CENTER);
		lbname5.setBackground(Color.WHITE);
		lbname5.setFont(f2);
		lbname5.setBounds(520, 610, 70, 30);
		lbback.add(lbname5);
		lbname5.setOpaque(true);
		
		
		
		lbtitle = new JLabel("캐릭터 선택", JLabel.CENTER);
		Font f = new Font("맑은 고딕", Font.BOLD, 50);
		lbtitle.setForeground(Color.white);
		lbtitle.setFont(f);

		lbtitle.setBounds(250, 20, 290, 50);
		lbback.add(lbtitle);

		btnstart = new JButton("게임시작");

		btnstart.setBounds(300, 660, 200, 70);
		lbback.add(btnstart);

		lb1 = new JLabel();
		lb2 = new JLabel();
		lb3 = new JLabel();
		lb4 = new JLabel();
		lb5 = new JLabel();
		
		lb1.setIcon(new ImageIcon("src\\Images\\user1.png"));
		cb1 = new JCheckBox("다오",true);
		cb1.setActionCommand("1");

		lb1.setBounds(50, 120, 200, 200);
		cb1.setBounds(40, 110, 20, 20);

		lb2.setIcon(new ImageIcon("src\\Images\\user2.png"));
		cb2 = new JCheckBox();
		cb2.setActionCommand("2");

		lb2.setBounds(300, 120, 200, 200);
		cb2.setBounds(280, 110, 20, 20);

		lb3.setIcon(new ImageIcon("src\\Images\\user3.png"));
		cb3 = new JCheckBox();
		cb3.setActionCommand("3");

		lb3.setBounds(540, 120, 200, 200);
		cb3.setBounds(520, 110, 20, 20);

		lb4.setIcon(new ImageIcon("src\\Images\\user4.png"));
		cb4 = new JCheckBox();
		cb4.setActionCommand("4");

		lb4.setBounds(160, 400, 200, 200);
		cb4.setBounds(140, 390, 20, 20);

		lb5.setIcon(new ImageIcon("src\\Images\\user5.png"));
		cb5 = new JCheckBox();
		cb5.setActionCommand("5");

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

		
		
		setTitle("캐릭터 선택");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		setVisible(true);
	}

	
//	public static void main(String[] args) {
//		new CharChoice();
//	}


	@Override
	public void actionPerformed(ActionEvent e) {
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
		
	}
}


