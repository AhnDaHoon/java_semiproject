package semiproject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class test1 extends JFrame{
	JPanel jpl1;
	JPanel jpl2;
	JPanel jpl3;
	
	JPanel jplUser1;
	JPanel jplUser2;
	JPanel jplUser3;
	JPanel jplUser4;
	JPanel jplUser5;
	
	JTextArea jtaUser1;
	JTextArea jtaUser2;
	JTextArea jtaUser3;
	JTextArea jtaUser4;
	JTextArea jtaUser5;
	
	JLabel jlbCharacter1;
	JLabel jlbCharacter2;
	JLabel jlbCharacter3;
	JLabel jlbCharacter4;
	JLabel jlbCharacter5;
	
	JLabel name1;
	JLabel name2;
	JLabel name3;
	JLabel name4;
	JLabel name5;
	
	
	JLabel jpl3Probrem;
	JTextArea jtaProbrem;
	
	

	test1() {
		setTitle("문제");
		
		setLayout(null);
		
		// 입력창
		jpl1 = new JPanel();
		// 유저창
		jpl2 = new JPanel();
		// 문제창
		jpl3 = new JPanel();
		
		// 유저
		jplUser1 = new JPanel();
		jplUser2 = new JPanel();
		jplUser3 = new JPanel();
		jplUser4 = new JPanel();
		jplUser5 = new JPanel();
		
		// 말풍선
		jtaUser1 = new JTextArea();
		jtaUser2 = new JTextArea();
		jtaUser3 = new JTextArea();
		jtaUser4 = new JTextArea();
		jtaUser5 = new JTextArea();
		
		// 캐릭터
		jlbCharacter1 = new JLabel(new ImageIcon("src\\Images\\a.gif"));
		jlbCharacter2 = new JLabel(new ImageIcon("src\\Images\\a.gif"));
		jlbCharacter3 = new JLabel(new ImageIcon("src\\Images\\a.gif"));
		jlbCharacter4 = new JLabel(new ImageIcon("src\\Images\\a.gif"));
		jlbCharacter5 = new JLabel(new ImageIcon("src\\Images\\a.gif"));
		
		// 이름
		name1 = new JLabel(new ImageIcon("src\\Images\\bg.gif"));
		name2 = new JLabel(new ImageIcon("src\\Images\\bg.gif"));
		name3 = new JLabel(new ImageIcon("src\\Images\\bg.gif"));
		name4 = new JLabel(new ImageIcon("src\\Images\\bg.gif"));
		name5 = new JLabel(new ImageIcon("src\\Images\\bg.gif"));
		
		// 문제 테두리 배경
//		jpl3Probrem = new JLabel();


		// 문제
		jtaProbrem = new JTextArea("문제창");
		
		jpl1.setBackground(Color.black);
		jpl2.setBackground(Color.red);
		jpl3.setBackground(Color.white);
		
		jplUser1.setBackground(Color.gray);
		jplUser2.setBackground(Color.magenta);
		jplUser3.setBackground(Color.darkGray);
		jplUser4.setBackground(Color.CYAN);
		jplUser5.setBackground(Color.YELLOW);
		
		
		jtaUser1.setBackground(Color.black);
		jtaUser2.setBackground(Color.black);
		jtaUser3.setBackground(Color.black);
		jtaUser4.setBackground(Color.black);
		jtaUser5.setBackground(Color.black);

		
		
		
		
		jplUser1.setLayout(null);
		jplUser2.setLayout(null);
		jplUser3.setLayout(null);
		jplUser4.setLayout(null);
		jplUser5.setLayout(null);

		
		
		
		
		
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
		
		
		jtaProbrem.setBounds(50, 50, 950, 300);
		
		jpl3.setLayout(null);
		jpl2.setLayout(new GridLayout());
		
		jpl1.setBounds(0, 715, 1000, 50);
		jpl2.setBounds(0, 415, 1000, 300);
		jpl3.setBounds(0, 0, 1000, 415);
		
		

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
		
		

		
		
		
		
		jpl2.add(jplUser1);
		jpl2.add(jplUser2);
		jpl2.add(jplUser3);
		jpl2.add(jplUser4);
		jpl2.add(jplUser5);
		
		
		
		jpl3.add(jtaProbrem);
		
		add(jpl1);
		add(jpl2);
		add(jpl3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(0, 0, 1015, 800);
	}

	
	public static void main(String[] args) {
		new test1();
	}

	
	
	
	
	
	

	
	
	
}
