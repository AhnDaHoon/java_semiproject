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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameServer extends JFrame implements ActionListener {

	JScrollPane jsp;
	JTextArea jta;
	JButton jbtn;
	ArrayList<MServer> list = new ArrayList<MServer>();

	ServerSocket ss;

	GameServer() {

		// 컴포넌트 초기화

		jta = new JTextArea();
		jbtn = new JButton("종료");

		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		Font f = new Font("굴림체", Font.PLAIN, 20);
		jta.setFont(f);

		add(jsp, "Center");
		add(jbtn, "South");

		jbtn.addActionListener(this);

		setBounds(300, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("chatServer");
		setVisible(true);

		vChatStart();

	}

	public void vChatStart() {

		try {
			ss = new ServerSocket(5000);

			while (true) {
				Socket client = ss.accept();
				MServer ms = new MServer(client);
				list.add(ms);

				ms.start();
			}

		} catch (IOException e) {
		}

	}

	public static void main(String[] args) {
		new GameServer();
	}

	class MServer extends Thread {

		Socket client;

		BufferedReader br;
		PrintWriter pw;
		String ip;

		public MServer(Socket client) {
			this.client = client;

			ip = client.getInetAddress().getHostAddress();

			jta.append(ip + " 님이 접속하셨습니다." + "\n");
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
						
						// 명령어 판단 c/하하하	<=	
						// mList[0] ==> c
						// mList[1] ==> 하하하
						
						String mList[] = msg.split("/");
						if(mList[0].equals("c")) {
							ip = mList[1];
						}else {
							
						
						
						jta.append("[" + ip + "] : " + msg + "\n");

						// 오토 스크롤 기능
						JScrollBar sb = jsp.getVerticalScrollBar();
						
						int position = sb.getMaximum();
						
						// 이동
						sb.setValue(position);
						
						// 모든 접속자에게 이 메세지를 전달
//						broadcast("[" + ip + "] : " + msg);

						}
					}
				} catch (IOException e) {
					list.remove(this);
				}
		} // run() end

		private void broadcast(String msg) {
			// ArrayList 안에 있는 객체를 하나씩 꺼내서 전송
			System.out.println("list : " + list);
			for (MServer x : list) {
				x.pw.println(msg);
				x.pw.flush();
			}
		} // broadcast() end

	} // MServer class end

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}// ChatServer class end
