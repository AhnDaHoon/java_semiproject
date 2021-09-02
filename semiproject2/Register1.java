package semiproject2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Register1 extends JFrame implements ActionListener {
	JButton jbtnSingup;
	JButton jbtnCancel;
	JButton jbtnIdDoubleCheck;
	JButton jbtnEmailDoubleCheck;
	JButton jbtnNicknameDoubleCheck;

	JLabel jlbId;
	JLabel jlbPw;
	JLabel jlbEmail;
	JLabel jlbName;
	JLabel jlbSecure;
	JLabel jlbSingup;
	JLabel jlbNickname;

	JTextField jtfId;
	JPasswordField jtfPw;
	JTextField jtfEmail;
	JTextField jtfName;
	JTextField jtfSecure;
	JTextField jtfNickname;

	JTextArea jta;

	JScrollPane jsp;
	ButtonGroup btnGroup;

	boolean isExist = true;

	BufferedImage img = null;

	boolean idOk = false, emailOk = false, nicknameOk = false;

	// JPanel �г��� ��ӹ��� �г� Ŭ����

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	Register1() {

		// ������Ʈ �ʱ�ȭ
		super("ȸ������");

		// ��� �г��� ���� ���̾ƿ� Ǯ�� background �г� ����
		setLayout(null);
		JLayeredPane background = new JLayeredPane();
		background.setBounds(0, 0, 800, 1000);
		background.setLayout(null);

		try {
			img = ImageIO.read(new File("src/images/register.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ε� ����");
		}

		// j�г� Ŭ������ ��ư ���̱�

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 800, 1000);
		panel.setBackground(Color.BLUE);

		// ������Ʈ �ʱ�ȭ

		jbtnSingup = new JButton("ȸ������");
		jbtnCancel = new JButton("���");
		jbtnIdDoubleCheck = new JButton("�ߺ�Ȯ��");
		jbtnEmailDoubleCheck = new JButton("�ߺ�Ȯ��");
		jbtnNicknameDoubleCheck = new JButton("�ߺ�Ȯ��");

		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jtfEmail = new JTextField();
		jtfName = new JTextField();
		jtfSecure = new JTextField();
		jtfNickname = new JTextField();

		jlbId = new JLabel("ID     :   ");
		jlbPw = new JLabel("PW    :   ");
		jlbEmail = new JLabel("Email : ");
		jlbNickname = new JLabel("Nickname : ");
		jlbName = new JLabel("�̸�   :   ");
		jlbSecure = new JLabel("�����ڵ�  :   ");
		jlbSingup = new JLabel("ȸ   ��   ��   ��");

		jta = new JTextArea();
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// üũ�ڽ� ��� ��

		btnGroup = new ButtonGroup();

		// ����� �ػ� ���� �� â ��ġ ���
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 800 / 2);

		// ������Ʈ ��ġ ����

		jlbId.setBounds(100, 140, 300, 50);
		jlbPw.setBounds(100, 220, 300, 50);
		jlbEmail.setBounds(100, 300, 300, 50);
		jlbName.setBounds(100, 380, 300, 50);
		jlbSecure.setBounds(60, 460, 300, 50);
		jlbNickname.setBounds(40, 540, 300, 50);

		jlbSingup.setBounds(330, 50, 300, 50);

		jtfId.setBounds(220, 140, 300, 50);
		jtfPw.setBounds(220, 220, 300, 50);
		jtfEmail.setBounds(220, 300, 300, 50);
		jtfName.setBounds(220, 380, 300, 50);
		jtfSecure.setBounds(220, 460, 300, 50);
		jtfNickname.setBounds(220, 540, 300, 50);

		jbtnIdDoubleCheck.setBounds(550, 140, 100, 50);
		jbtnEmailDoubleCheck.setBounds(550, 300, 100, 50);
		jbtnNicknameDoubleCheck.setBounds(550, 540, 100, 50);
		jbtnSingup.setBounds(250, 700, 100, 50);
		jbtnCancel.setBounds(450, 700, 100, 50);

		// ��Ʈ ���� , �۲� ����

		jbtnIdDoubleCheck.setForeground(Color.red);

		Font f = new Font("serif", Font.CENTER_BASELINE, 30);
		Color c = new Color(91, 12, 119);

		jlbId.setFont(f);
		jlbPw.setFont(f);
		jlbEmail.setFont(f);
		jlbNickname.setFont(f);
		jlbName.setFont(f);
		jlbSecure.setFont(f);
		jlbSingup.setFont(f);

		jlbId.setForeground(c);
		jlbPw.setForeground(c);
		jlbEmail.setForeground(c);
		jlbNickname.setForeground(c);
		jlbName.setForeground(c);
		jlbSecure.setForeground(c);
		jlbSingup.setForeground(c);

		jbtnIdDoubleCheck.setForeground(c);
		jbtnEmailDoubleCheck.setForeground(c);
		jbtnNicknameDoubleCheck.setForeground(c);

		jbtnIdDoubleCheck.setBackground(Color.white);
		jbtnEmailDoubleCheck.setBackground(Color.white);
		jbtnNicknameDoubleCheck.setBackground(Color.white);

		jbtnSingup.setBackground(Color.white);
		jbtnCancel.setBackground(Color.white);

		jtfId.setOpaque(false);
		jtfPw.setOpaque(false);
		jtfEmail.setOpaque(false);
		jtfNickname.setOpaque(false);
		jtfName.setOpaque(false);
		jtfSecure.setOpaque(false);
		jta.setOpaque(false);

		background.add(jlbId);
		background.add(jlbPw);
		background.add(jlbEmail);
		background.add(jlbNickname);
		background.add(jlbName);
		background.add(jlbSingup);
		background.add(jlbSecure);

		background.add(jtfId);
		background.add(jtfPw);
		background.add(jtfEmail);
		background.add(jtfNickname);
		background.add(jtfName);
		background.add(jtfSecure);

		background.add(jsp);

		background.add(jbtnIdDoubleCheck);
		background.add(jbtnEmailDoubleCheck);
		background.add(jbtnNicknameDoubleCheck);
		background.add(jbtnSingup);
		background.add(jbtnCancel);

		background.add(panel);
		add(background);

		jbtnIdDoubleCheck.addActionListener(this);
		jbtnNicknameDoubleCheck.addActionListener(this);
		jbtnEmailDoubleCheck.addActionListener(this);

		jbtnCancel.addActionListener(this);
		jbtnSingup.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(x, y, 800, 800);

	}

	public static void main(String[] args) {
		new Register1();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnCancel) {
			setVisible(false);
			new Login();

		} else if (obj == jbtnSingup) {
			if (idOk == true && emailOk == true && nicknameOk == true) {
				String id = jtfId.getText().trim();
				String pw = jtfPw.getText().trim();
				String email = jtfEmail.getText().trim();
				String name = jtfName.getText().trim();
				String no = jtfSecure.getText().trim();
				String nickname = jtfNickname.getText().trim();

				SmemberDAO dao = new SmemberDAO();
				SmemberVO vo = new SmemberVO(id, pw, email, name, no, nickname);

				dao.insertOne(vo);
				dao.close();

				setVisible(false);
				new Login();

			} else if (idOk == false) {
				JOptionPane.showConfirmDialog(this, "���̵� �ߺ�Ȯ���� �����ϼ���.", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else if (emailOk == false) {
				JOptionPane.showConfirmDialog(this, "�̸��� �ߺ�Ȯ���� �����ϼ���.", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else if (nicknameOk == false) {
				JOptionPane.showConfirmDialog(this, "�г��� �ߺ�Ȯ���� �����ϼ���.", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			}

		} else if (obj == jbtnIdDoubleCheck) {

			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists(jtfId.getText().trim());
			if (isExist) {
				JOptionPane.showConfirmDialog(this, "���̵� �ߺ��˴ϴ�.", "���̵� �ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else if (isExist == false) {

				if (jtfId.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "���̵� ������ �ùٸ��� �ʽ��ϴ� (������ �Է��� �� �����ϴ�.)", "���̵� �ߺ�Ȯ��",
							JOptionPane.PLAIN_MESSAGE);
				} else if (!jtfId.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "����� �� �ִ� ���̵��Դϴ�.", "���̵� �ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
					jtfId.setEditable(false);
					idOk = true;
				}
			}

		} else if (obj == jbtnNicknameDoubleCheck) {

			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists3(jtfNickname.getText().trim());

			if (isExist) {

				JOptionPane.showConfirmDialog(this, "�г����� �ߺ��˴ϴ�.", "�г��� �ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else if (isExist == false) {

				if (jtfNickname.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "�г��� ������ �ùٸ��� �ʽ��ϴ� (������ �Է��� �� �����ϴ�.)", "�г��� �ߺ�Ȯ��",
							JOptionPane.PLAIN_MESSAGE);
				}

				else if (!jtfNickname.getText().contains(" "))
					JOptionPane.showConfirmDialog(this, "����� �� �ִ� �г����Դϴ�", "�г��� �ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
				jtfNickname.setEditable(false);
				nicknameOk = true;
			}

		} else if (obj == jbtnEmailDoubleCheck) {
			SmemberDAO dao = new SmemberDAO();

			isExist = dao.isExists2(jtfId.getText().trim());

			if (isExist) {
				JOptionPane.showConfirmDialog(this, "�̸����� �ߺ��˴ϴ�.", "�̸��� �ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			}

			else if (isExist == false) {

				if (jtfEmail.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "�̸��� ������ ��ȿ���� �ʽ��ϴ� (������ �Է��� �� �����ϴ�.)", "�̸��� �ߺ�Ȯ��",
							JOptionPane.PLAIN_MESSAGE);
				} else if (!jtfEmail.getText().contains("@")) {
					JOptionPane.showConfirmDialog(this, "�̸��� ������ ��ȿ���� �ʽ��ϴ� (@�� ���Ե��� �ʾҽ��ϴ�.)", "�̸��� �ߺ�Ȯ��",
							JOptionPane.PLAIN_MESSAGE);
				} else if (!jtfEmail.getText().contains(".com")) {
					JOptionPane.showConfirmDialog(this, "�̸��� ������ ��ȿ���� �ʽ��ϴ� (�������� �Է����ּ���.)", "�̸��� �ߺ�Ȯ��",
							JOptionPane.PLAIN_MESSAGE);
				} else if (jtfEmail.getText().contains(".com") && jtfEmail.getText().contains("@")
						&& !jtfEmail.getText().contains(" ")) {
					JOptionPane.showConfirmDialog(this, "Ȯ�εǾ����ϴ�.", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
					jtfEmail.setEditable(false);
					emailOk = true;
				}

			}
		}
	}
}
