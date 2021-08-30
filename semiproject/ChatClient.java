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
		jbtnExit = new JButton("종료");
		
		jp3.setLayout(null);
		
		// 모든 컴포넌트의 크기와 위치 지정
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
		jbtn = new JButton("전송"); 
		
		jp1.setLayout(new BorderLayout());
		jp1.add(jsp,"Center"); 
		jp2.add(jtf); 
		jp2.add(jbtn);
		
		jp1.add(jp2,"South");
		
		cl = new CardLayout();
		// 현재 프레임에 배치관리자를 CardLayout 으로 변경 
		setLayout(cl);
		
		add(jp3, "login");
		add(jp1, "chat");
		
		// 카드레이아웃에서 컨텐츠 패널에 어떤 창을 붙일지 결정
		
		cl.show(getContentPane(), "login");
		
		// 이벤트 처리 
		
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

			// 채팅기능
			vchatting();
			
			cl.show(getContentPane(), "chat"); // 이 화면으로 패널을 바꿈
		}else if(obj == jbtnExit) {
			System.exit(0);
		}else if(obj == jbtn) {
			send();
		}
		
	}
	private void vchatting() {
		// 현재 클라이언트 프로그램을 여러개 띄울수 있도록 멀티 쓰레드로 작성
		Thread th = new Thread(this); 
		th.start();
	}

	@Override
	public void run() {
		// 동시에 처리할 코드
		// 채팅
		try {
			s = new Socket(ip, Integer.parseInt(port));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
			// 반복해서 읽기 
			String msg = null;
			while(true) {
				msg = br.readLine();
				jta.append(msg+"\n");	
				// 오토 스크롤기능, 문자가 오면 스크롤을 내려주는 기능
				JScrollBar sb = jsp.getVerticalScrollBar();
				// 스크롤의 최대값을 받아온다.
				int position = sb.getMaximum();
				// 스크롤 최대값으로 이동
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
		System.out.println("전송");
		// 텍스트 필드에 있는 내용을 가져오기 
		String msg = jtf.getText();
		jta.append("me : " + msg + "\n");
		// 쓰기 객체를 통해서 서버에 전달 
//		jta.append("me: " + msg + "\n");
		pw.println(msg);
		pw.flush();
		// jTextField의 글자를 지우기
		jtf.setText("");
		// 포커스 주기
		jtf.requestFocus();
	}
}
