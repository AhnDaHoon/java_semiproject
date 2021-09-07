package semiproject;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ComboBox extends JFrame implements ActionListener {

	boolean isExist4 = true;
	JTextField jtf;
	ImageIcon iconback, icontitle, icon, changeicon;
	Image img, changeimg;
	JButton btn, btnback;
	JLabel lbback, lb1;

	Music m;
	
	ComboBox() {
		this.m = Login.m;
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

		btn = new JButton("ã��");
		btn.setBounds(320, 200, 100, 50);
		lbback.add(btn);

		btn.addActionListener(this);
		btnback.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ID / PW ã��");
		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new ComboBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnback) {
			setVisible(false);
			Login lo = new Login();
			m.stop();
		} else if (e.getSource() == btn) {
			
			SmemberDAO dao = new SmemberDAO();
			isExist4 = dao.isExists4(jtf.getText().trim());
			if (isExist4) {
				JOptionPane.showConfirmDialog(this, "���� �Ϸ�", "����", JOptionPane.PLAIN_MESSAGE);
				SmemberVO vo = dao.selectOne(jtf.getText().trim());
				String data = "ID : " + vo.getId() + " 	PW : " + vo.getPw();

				jtf.setText(data);
				dao.close();
				m.stop();
			} else {
				JOptionPane.showConfirmDialog(this, "�������� �ʴ� �ڵ��Դϴ�.", "����", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
