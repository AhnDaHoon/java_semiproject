package semiproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GameClient extends JFrame implements ActionListener, Runnable{
	
	// ū Ʋ�� ������ �г�(�Է�â, ����â, ����â)
	JPanel jplInput, jplUser, jplProblem;
	
	// �����г��� ������ 5���� �����г�
	JPanel jplUser1, jplUser2, jplUser3, jplUser4, jplUser5;
	
	// ������ ����� ǥ���ϴ� TextArea
	JTextArea jtaUser1, jtaUser2, jtaUser3, jtaUser4, jtaUser5;
	
	// ������ ĳ���� ������ ǥ���ϴ� ��
	JLabel jlbCharacter1, jlbCharacter2, jlbCharacter3, jlbCharacter4, jlbCharacter5;
	
	// ������ �г����� ǥ���ϴ� ��
	JLabel name1, name2, name3, name4, name5;
	
	// ������ ǥ���� TextArea
	JTextArea jtaProblem;
	
	// ����� �Է��� �ؽ�Ʈ�ʵ�
	JTextField jtfInput;
	
	// ����� ������ �ؽ�Ʈ�ʵ�
	JButton jbtnSend;
	
	// �����г� ��׶��� �̹���
	ImageIcon icon;
	
	// ����
	Socket s;
	
	JButton jbtnStart;
	GameClient() {
		// �������� Ÿ��Ʋ
		setTitle("����");
		
		// ��ġ������ ����
		setLayout(null);
		
		// �Է�â �г� �ʱ�ȭ
		jplInput = new JPanel();
		// ����â �г� �ʱ�ȭ
		jplUser = new JPanel();
		
		
		icon = new ImageIcon("src\\images\\problembg.png");
		// ����â �г� �ʱ�ȭ
		jplProblem = new JPanel() {
			 public void paintComponent(Graphics g) {
	                g.drawImage(icon.getImage(), 0, 0, null);
	                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
	                super.paintComponent(g);
	            }
		};
		
		
		
		
		
		// ���� �г� �ʱ�ȭ
		jplUser1 = new JPanel();
		jplUser2 = new JPanel();
		jplUser3 = new JPanel();
		jplUser4 = new JPanel();
		jplUser5 = new JPanel();
		
		// ��ǳ�� TextArea �ʱ�ȭ
		jtaUser1 = new JTextArea("User1 ��ǳ��");
		jtaUser2 = new JTextArea("User2 ��ǳ��");
		jtaUser3 = new JTextArea("User3 ��ǳ��");
		jtaUser4 = new JTextArea("User4 ��ǳ��");
		jtaUser5 = new JTextArea("User5 ��ǳ��");
		
		// ĳ���� �� �ʱ�ȭ
		jlbCharacter1 = new JLabel(new ImageIcon("src\\Images\\user1.png"));
		jlbCharacter2 = new JLabel(new ImageIcon("src\\Images\\user2.png"));
		jlbCharacter3 = new JLabel(new ImageIcon("src\\Images\\user3.png"));
		jlbCharacter4 = new JLabel(new ImageIcon("src\\Images\\user4.png"));
		jlbCharacter5 = new JLabel(new ImageIcon("src\\Images\\user5.png"));
		
		// �г��� �� �ʱ�ȭ
		name1 = new JLabel("User1");
		name2 = new JLabel("User2");
		name3 = new JLabel("User3");
		name4 = new JLabel("User4");
		name5 = new JLabel("User5");
		
		
		
		// ��� �Է� TextField�� ��� ������ ��ư �ʱ�ȭ
		jtfInput = new JTextField();
		jbtnSend = new JButton("����");
		
		
		// ���� ����
		jbtnStart = new JButton(new ImageIcon("src\\Images\\gamestart.gif"));
		
		
		
		// �г��� �� �߾� ����
		name1.setHorizontalAlignment(JLabel.CENTER);
		name2.setHorizontalAlignment(JLabel.CENTER);
		name3.setHorizontalAlignment(JLabel.CENTER);
		name4.setHorizontalAlignment(JLabel.CENTER);
		name5.setHorizontalAlignment(JLabel.CENTER);

		
		
		// ����
		jtaProblem = new JTextArea("������ �Էµ� �� �Դϴ�.");
		
	
		jplUser.setBackground(Color.red);
		jplProblem.setBackground(Color.white);
		
		
		jtaUser1.setBackground(Color.black);
		jtaUser2.setBackground(Color.black);
		jtaUser3.setBackground(Color.black);
		jtaUser4.setBackground(Color.black);
		jtaUser5.setBackground(Color.black);

		jtaUser1.setForeground(Color.white);
		jtaUser2.setForeground(Color.white);
		jtaUser3.setForeground(Color.white);
		jtaUser4.setForeground(Color.white);
		jtaUser5.setForeground(Color.white);
		
		
		
		jplUser1.setLayout(null);
		jplUser2.setLayout(null);
		jplUser3.setLayout(null);
		jplUser4.setLayout(null);
		jplUser5.setLayout(null);

		
		
		
		
		// ������Ʈ ��ġ �� ������ �ʱ�ȭ
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
		
		
		
		jtaProblem.setBounds(200, 150, 600, 100);
		jtaProblem.setBackground(new Color(255,246,226));
		jtaProblem.setFont(new Font("����ü", Font.BOLD, 15));
		
		
		jplProblem.setLayout(null);
		jplUser.setLayout(new GridLayout());
		
		jplInput.setBounds(0, 715, 1000, 50);
		jplUser.setBounds(0, 415, 1000, 300);
		jplProblem.setBounds(0, 0, 1000, 415);
		
		
		jtfInput.setBounds(100,10, 600, 30);
		jbtnSend.setBounds(750,10,200,30);
		
		jbtnStart.setBounds(400, 100, 200, 200);
		add(jbtnStart);
		

		// ��ǳ�� 
		jplUser1.add(jtaUser1);
		jplUser2.add(jtaUser2);
		jplUser3.add(jtaUser3);
		jplUser4.add(jtaUser4);
		jplUser5.add(jtaUser5);
		
		// ĳ����
		jplUser1.add(jlbCharacter1);
		jplUser2.add(jlbCharacter2);
		jplUser3.add(jlbCharacter3);
		jplUser4.add(jlbCharacter4);
		jplUser5.add(jlbCharacter5);
		
		// �г���
		jplUser1.add(name1);
		jplUser2.add(name2);
		jplUser3.add(name3);
		jplUser4.add(name4);
		jplUser5.add(name5);
		
		

		// �� ���� �г� �����гο� ���̱�
		jplUser.add(jplUser1);
		jplUser.add(jplUser2);
		jplUser.add(jplUser3);
		jplUser.add(jplUser4);
		jplUser.add(jplUser5);
		
		
		
		jplProblem.add(jtaProblem);
		
	
		jplInput.setLayout(null);
		
		jplInput.add(jtfInput);
		jplInput.add(jbtnSend);
		
		
		add(jplInput);
		add(jplUser);
		add(jplProblem);
		
		jbtnSend.addActionListener(this);
		jbtnStart.addActionListener(this);
		vchatting();
		
		
		// ��� ȭ�鿡�� ������ġ�� �������� ��ġ��Ű������ ����
		int x, y;
		
		// �ش� ��ũ���� ������ �˾ƿ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		double width = d.getWidth();
		double height = d.getHeight();
		
		x = (int) (width / 2 - 1015 /2);
		y = (int) (height/2 - 800 / 2);
		
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(x, y, 1015, 800);
	}

	
	public static void main(String[] args) {
		new GameClient();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnStart) {
			System.out.println("��������");
			jbtnStart.setVisible(false);
			
			
			// jtaProblem�� ���� ����
			
			 jtaProblem.setText(""); // ���ڿ� �ʱ�ȭ
			 
			 // ������ ���� �޾ƿ���
			 ProblemDAO dao = new ProblemDAO();
			 ProblemVO vo = dao.problem(new Random().nextInt(2)+1); // ���� �������� �̱����ؼ� RandomŬ������ �Ű������� �ش�.
			 jtaProblem.append(vo.getProblem());
			 
			 // ��ü ���� ���� �޾ƿ���
			 ProblemNumberDAO ndao = new ProblemNumberDAO();
			 ProblemNumberVO nvo = ndao.problem(new Random().nextInt(2)+1);

			 // ������ ������ ���� blank���� ����
			 String blank = "      ";
			 // ������ �޾ƿ��
			 jtaProblem.append("\n\n\n\n " + nvo.getNumone() +blank+ nvo.getNumtwo() +blank+ nvo.getNumthree() +blank+ nvo.getNumfour());
		}
	}
	
	
	
	
	
	

	private void vchatting() {
		// ���� Ŭ���̾�Ʈ ���α׷��� ������ ��� �� �ֵ��� ��Ƽ ������� �ۼ�
	
		Thread th = new Thread(this);
		th.start();
		
	}

	@Override
	public void run() {
		try {
			s = new Socket("192.168.219.101", 5000);
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
	

	
	
	
}
