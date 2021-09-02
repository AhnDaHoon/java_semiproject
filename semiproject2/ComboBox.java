package semiproject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComboBox extends JFrame implements ActionListener {

	boolean isExist4 = true;
	JTextField jtf;
	ImageIcon iconback, icontitle, icon, changeicon;
	Image img, changeimg;
	JButton btn, btnback;
	JLabel lbback, lb1;

	ComboBox() {

		setLayout(null);

		iconback = new ImageIcon("src\\Images\\f2.png");
		lbback = new JLabel(iconback);
		lbback.setBounds(0, 0, 500, 500);
		add(lbback);

		icontitle = new ImageIcon("src\\Images\\pp2.png");
		lb1 = new JLabel(icontitle);
		lb1.setBounds(75, 70, 350, 100);
		lbback.add(lb1);

		jtf = new JTextField();

		jtf.setBounds(80, 200, 200, 50);
		lbback.add(jtf);

		icon = new ImageIcon("src/images/back.jpg");
		img = icon.getImage();
		changeimg = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		changeicon = new ImageIcon(changeimg);
		btnback = new JButton(changeicon);

		btnback.setFocusPainted(false);
		btnback.setContentAreaFilled(false);
		btnback.setBorderPainted(false);

		btnback.setBounds(0, 0, 80, 80);

		lbback.add(btnback);

		btn = new JButton("찾기");
		btn.setBounds(320, 200, 100, 50);
		lbback.add(btn);

		btn.addActionListener(this);
		btnback.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ID / PW 찾기");
		setBounds(100, 100, 500, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComboBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnback) {
			setVisible(false);
			Login lo = new Login();
			
		} else if (e.getSource() == btn) {
			
			SmemberDAO dao = new SmemberDAO();
			isExist4 = dao.isExists4(jtf.getText().trim());
			if (isExist4) {
				JOptionPane.showConfirmDialog(this, "인증 완료", "가입", JOptionPane.PLAIN_MESSAGE);
				SmemberVO vo = dao.selectOne(jtf.getText().trim());
				String data = "ID : " + vo.getId() + " 	PW : " + vo.getPw();

				jtf.setText(data);
				dao.close();
			} else {
				JOptionPane.showConfirmDialog(this, "존재하지 않는 코드입니다.", "가입", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
