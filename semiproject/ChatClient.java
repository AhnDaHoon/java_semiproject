package semiproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements  ActionListener, Runnable, KeyListener{
	Socket s;
	PrintWriter pw; 
	BufferedReader br;
	
	JPanel jp1, jp2, jp3;
	
	JLabel jlIp, jlPort;
	JTextField jtfIp, jtfPort;
	JButton jbtnConnect , jbtnExit;
	
	CardLayout cl ; 
	
	JScrollPane jsp;
	JTextArea jta;
	JTextField jtf;
	JButton jbtn;
	
	String ip , port;
	
	
	
	
	
	public ChatClient(){
		setTitle("Chat Client");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jlIp = new JLabel("IP");
		jlPort = new JLabel("PORT");
		jtfIp = new JTextField("192.168.219.104");
		jtfPort = new JTextField("5000");
		
		jbtnConnect = new JButton("CONNECT");
		jbtnExit = new JButton("����");
		
		jp3.setLayout(null);
		
		// ��� ������Ʈ�� ũ��� ��ġ ����
		jlIp.setBounds(50, 100, 100, 50);
		jlPort.setBounds(50, 300, 100, 50);
		
		jtfIp.setBounds(250, 100, 150, 50);
		jtfPort.setBounds(250, 300, 150, 50);
		
		jbtnConnect.setBounds(150, 450, 150, 50);
		jbtnExit.setBounds(350, 450, 150, 50);
		
		jp3.add(jlIp);jp3.add(jlPort);jp3.add(jtfIp);
		jp3.add(jtfPort);jp3.add(jbtnConnect);jp3.add(jbtnExit);
		
		jta = new JTextArea();
		jta. setEditable(false);
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jtf = new JTextField(40); 
		jbtn = new JButton("����"); 
		
		jp1.setLayout(new BorderLayout());
		jp1.add(jsp,"Center"); 
		jp2.add(jtf); 
		jp2.add(jbtn);
		
		jp1.add(jp2,"South");
		
		cl = new CardLayout();
		// ���� �����ӿ� ��ġ�����ڸ� CardLayout ���� ���� 
		setLayout(cl);
		
		add(jp3, "login");
		add(jp1, "chat");
		
		// ī�巹�̾ƿ����� ������ �гο� � â�� ������ ����
		
		cl.show(getContentPane(), "login");
		
		// �̺�Ʈ ó�� 
		
		jbtnConnect.addActionListener(this);
		jbtnExit.addActionListener(this);
		jbtn.addActionListener(this);
		
		jtf.addKeyListener(this);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		ChatClient cc = new ChatClient();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnConnect) {
			ip = jtfIp.getText();
			port = jtfPort.getText();

			// ä�ñ��
			vchatting();
			
			cl.show(getContentPane(), "chat"); // �� ȭ������ �г��� �ٲ�
		}else if(obj == jbtnExit) {
			System.exit(0);
		}else if(obj == jbtn) {
			send();
		}
		
	}
	private void vchatting() {
		// ���� Ŭ���̾�Ʈ ���α׷��� ������ ���� �ֵ��� ��Ƽ ������� �ۼ�
		Thread th = new Thread(this); 
		th.start();
	}

	@Override
	public void run() {
		// ���ÿ� ó���� �ڵ�
		// ä��
		try {
			s = new Socket(ip, Integer.parseInt(port));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
			// �ݺ��ؼ� �б� 
			String msg = null;
			while(true) {
				msg = br.readLine();
				jta.append(msg+"\n");	
				// ���� ��ũ�ѱ��, ���ڰ� ���� ��ũ���� �����ִ� ���
				JScrollBar sb = jsp.getVerticalScrollBar();
				// ��ũ���� �ִ밪�� �޾ƿ´�.
				int position = sb.getMaximum();
				// ��ũ�� �ִ밪���� �̵�
				sb.setValue(position);
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			send();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void send() {
		System.out.println("����");
		// �ؽ�Ʈ �ʵ忡 �ִ� ������ �������� 
		String msg = jtf.getText();
		jta.append("me : " + msg + "\n");
		// ���� ��ü�� ���ؼ� ������ ���� 
//		jta.append("me: " + msg + "\n");
		pw.println(msg);
		pw.flush();
		// jTextField�� ���ڸ� �����
		jtf.setText("");
		// ��Ŀ�� �ֱ�
		jtf.requestFocus();
	}
}
