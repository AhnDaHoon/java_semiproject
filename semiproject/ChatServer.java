package semiproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.text.SimpleDateFormat;
public class ChatServer extends JFrame implements ActionListener{
	JTextArea jta;
	JButton jbtn;
	JScrollPane jsp;

	ArrayList<MServer> list = new ArrayList<MServer>();
	
	ServerSocket ss;

	public ChatServer() {
		setTitle("ChatServer");

		jta = new JTextArea();
		Font f = new Font("굴림체", Font.PLAIN, 20);
		jta.setEditable(false);
		jta.setFont(f);
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jbtn = new JButton("종료");
		jbtn.addActionListener(this);
		
		add(jsp, "Center");
		add(jbtn, "South");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 50, 600, 800);
		setVisible(true);

		vChatStart();

	}

	private void vChatStart() {
		try {
			ss = new ServerSocket(5000);

			// 연결 대기
			while (true) {				
				System.out.println(getTime() + "서버가 준비 되었습니다.");
				Socket client = ss.accept();
				MServer ms = new MServer(client);
				list.add(ms);
				ms.start();
			}
		} catch (IOException e) {
		
		}
	}// vChatStart() end

	class MServer extends Thread {

		Socket client;

		BufferedReader br;
		PrintWriter pw;
		String ip;

		MServer(Socket client) {
			this.client = client;

			// 여기서 초기화
			ip = client.getInetAddress().getHostAddress();
			System.out.println(getTime());
			broadcast(ip + " 님이 접속하셨습니다.");
			try {
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			try {
				while (true) {
					String msg = null;
	
						msg = br.readLine();
	
						// 명령어 판단 c/하하하 <==   
						// mList[0] = c
						// mList[1] = 하하하
						// 닉네임 변경 c/변경할이름
						String[] mList = msg.split("/");
						if(mList[0].equals("c")) {
							ip = mList[1];
						}else {
							
						
							
							
						jta.append(getTime() + "[ " + ip + " ] : " + msg +"\n");
	
						// 모든 접속자에게 이 메세지를 전달
						
						
						// 오토 스크롤기능, 문자가 오면 스크롤을 내려주는 기능
						JScrollBar sb = jsp.getVerticalScrollBar();
						// 스크롤의 최대값을 받아온다.
						int position = sb.getMaximum();
						// 스크롤 최대값으로 이동
						sb.setValue(position);
						}
				}
			} catch (IOException e) {
				// 더 이상 채팅에 참여하지 않을것
				list.remove(this);
			}

			
		}// run() end

		private void broadcast(String msg) {
			// ArrayList 안에 있는 객체를 하나씩 꺼내서 전송
			System.out.println("list : " + list);
			for(MServer x : list) {
				x.pw.println(msg); 
				x.pw.flush();
			}
		}// broadcast() end;
		
	}// MServer class end

	public static void main(String[] args) {
		ChatServer cs = new ChatServer();
	}// main method end
	
	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}// actionPerformed() end 
}// ChatServer class end
