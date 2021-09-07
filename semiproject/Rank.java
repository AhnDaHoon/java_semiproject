package semiproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rank extends JFrame implements ActionListener{
	
	ImageIcon iconback;
	JLabel lbback;
	
	JButton btnrank, btnback, btnrank2, btnrank3;
	JButton btn1st, btn2st, btn3st; 
	
	ImageIcon icon,icon2,icon3,icon4,icon5,icon6,icon7;
	ImageIcon changeicon,changeicon2,changeicon3,changeicon4,changeicon5,changeicon6,changeicon7;
	Image img,img2,img3,img4,img5,img6,img7;
	Image changeimg,changeimg2,changeimg3,changeimg4,changeimg5,changeimg6,changeimg7;
	
	JLabel lb4st,lb5st;
	
	JLabel nickname[] = new JLabel[5];
	JLabel score[] = new JLabel[5];
	ArrayList<RankVO> list = new ArrayList<RankVO>();
	
	String id;
	
	
	Rank(String id){
		this.id = id;
		
		//배경
		iconback = new ImageIcon("src\\Images\\back3.jpg");
		lbback = new JLabel(iconback);
		lbback.setBounds(0, 0, 800, 600);
		add(lbback);
		
		
		// 랭킹타이틀
		icon = new ImageIcon("src/images/rank.png");
		img = icon.getImage();
		changeimg = img.getScaledInstance(250, 200, Image.SCALE_DEFAULT);
		changeicon = new ImageIcon(changeimg);
		btnrank = new JButton(changeicon);
		btnrank.setFocusPainted(false);
		btnrank.setContentAreaFilled(false);
		btnrank.setBorderPainted(false);
						
		btnrank.setBounds(150, 10, 230, 80);
				
		lbback.add(btnrank);
		
		
		//순위
		icon2 = new ImageIcon("src/images/back4.png");
		img2 = icon2.getImage();
		changeimg2 = img2.getScaledInstance(100, 170, Image.SCALE_DEFAULT);
		changeicon2 = new ImageIcon(changeimg2);
		btnrank2 = new JButton(changeicon2);
		btnrank2.setFocusPainted(false);
		btnrank2.setContentAreaFilled(false);
		btnrank2.setBorderPainted(false);
		btnrank2.setBounds(100, 80, 80, 80);
		lbback.add(btnrank2);
		
		
		
		// 닉네임 / 누적점수
		icon3 = new ImageIcon("src/images/back6.png");
		img3 = icon3.getImage();
		changeimg3 = img3.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		changeicon3 = new ImageIcon(changeimg3);
		btnrank3 = new JButton(changeicon3);
		btnrank3.setFocusPainted(false);
		btnrank3.setContentAreaFilled(false);
		btnrank3.setBorderPainted(false);
		btnrank3.setBounds(180, 80, 300, 80);
		lbback.add(btnrank3);
		
		
		//뒤로가기버튼
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
		
		
		//1~3위 순위
		icon5 = new ImageIcon("src/images/rank01.png");
		icon6 = new ImageIcon("src/images/rank02.png");
		icon7 = new ImageIcon("src/images/rank03.png");
		
		img5 = icon5.getImage();
		img6 = icon6.getImage();
		img7 = icon7.getImage();
		
		changeimg5 = img5.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		changeimg6 = img6.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		changeimg7 = img7.getScaledInstance(47, 60, Image.SCALE_DEFAULT);
		
		changeicon5 = new ImageIcon(changeimg5);
		changeicon6 = new ImageIcon(changeimg6);
		changeicon7 = new ImageIcon(changeimg7);
		
		btn1st = new JButton(changeicon5);
		btn2st = new JButton(changeicon6);
		btn3st = new JButton(changeicon7);
		
		btn1st.setFocusPainted(false);
		btn1st.setContentAreaFilled(false);
		btn1st.setBorderPainted(false);
		
		btn2st.setFocusPainted(false);
		btn2st.setContentAreaFilled(false);
		btn2st.setBorderPainted(false);
		
		btn3st.setFocusPainted(false);
		btn3st.setContentAreaFilled(false);
		btn3st.setBorderPainted(false);
		
		
		btn1st.setBounds(110, 150, 60, 60);
		btn2st.setBounds(110, 230, 60, 60);
		btn3st.setBounds(112, 315, 60, 60);
		
		lbback.add(btn1st);
		lbback.add(btn2st);
		lbback.add(btn3st);
			
		
		//4~5위 순위
		Font f = new Font("맑은 고딕", Font.BOLD, 20);
		lb4st = new JLabel("4위",JLabel.CENTER);
		lb4st.setFont(f);
		lb4st.setBounds(117, 390, 50, 50);
		lbback.add(lb4st);
		
		lb5st = new JLabel("5위",JLabel.CENTER);
		lb5st.setFont(f);
		lb5st.setBounds(117, 470, 50, 50);
		lbback.add(lb5st);
		
		
		RankDAO rankDAO = new RankDAO();
		list = rankDAO.selectAll();
		rankDAO.close();
		//1~5위 닉네임, 1~5위 누적점수
		for(int i = 0; i < 5; i++) {
			RankVO rankVO = new RankVO();
			rankVO = list.get(i);
			String thisNickname = rankVO.getNickname();
			int thisScore = rankVO.getScore();
			nickname[i] = new JLabel(thisNickname, JLabel.CENTER);
			nickname[i].setFont(f);
			nickname[i].setSize(100, 30);
			nickname[i].setLocation(200, 160 + (i*80));
			lbback.add(nickname[i]);
			
			score[i] = new JLabel(Integer.toString(thisScore), JLabel.CENTER);
			score[i].setFont(f);
			score[i].setSize(100, 30);
			score[i].setLocation(330, 160 + (i*80));
			lbback.add(score[i]);
		}
		
		btnback.addActionListener(this);
		
		
		setTitle("랭킹");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnback) {
			setVisible(false);
			new Channel(id);
		}
	}
}
