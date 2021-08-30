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
		Font f = new Font("����ü", Font.PLAIN, 20);
		jta.setEditable(false);
		jta.setFont(f);
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jbtn = new JButton("����");
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

			// ���� ���
			while (true) {				
				System.out.println(getTime() + "������ �غ� �Ǿ����ϴ�.");
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

			// ���⼭ �ʱ�ȭ
			ip = client.getInetAddress().getHostAddress();
			System.out.println(getTime());
			broadcast(ip + " ���� �����ϼ̽��ϴ�.");
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
	
						// ��ɾ� �Ǵ� c/������ <==   
						// mList[0] = c
						// mList[1] = ������
						// �г��� ���� c/�������̸�
						String[] mList = msg.split("/");
						if(mList[0].equals("c")) {
							ip = mList[1];
						}else {
							
						
							
							
						jta.append(getTime() + "[ " + ip + " ] : " + msg +"\n");
	
						// ��� �����ڿ��� �� �޼����� ����
						
						
						// ���� ��ũ�ѱ��, ���ڰ� ���� ��ũ���� �����ִ� ���
						JScrollBar sb = jsp.getVerticalScrollBar();
						// ��ũ���� �ִ밪�� �޾ƿ´�.
						int position = sb.getMaximum();
						// ��ũ�� �ִ밪���� �̵�
						sb.setValue(position);
						}
				}
			} catch (IOException e) {
				// �� �̻� ä�ÿ� �������� ������
				list.remove(this);
			}

			
		}// run() end

		private void broadcast(String msg) {
			// ArrayList �ȿ� �ִ� ��ü�� �ϳ��� ������ ����
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
