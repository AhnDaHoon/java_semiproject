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

		// ������Ʈ �ʱ�ȭ

		jta = new JTextArea();
		jbtn = new JButton("����");

		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		Font f = new Font("����ü", Font.PLAIN, 20);
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

			jta.append(ip + " ���� �����ϼ̽��ϴ�." + "\n");
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
						
						// ��ɾ� �Ǵ� c/������	<=	
						// mList[0] ==> c
						// mList[1] ==> ������
						
						String mList[] = msg.split("/");
						if(mList[0].equals("c")) {
							ip = mList[1];
						}else {
							
						
						
						jta.append("[" + ip + "] : " + msg + "\n");

						// ���� ��ũ�� ���
						JScrollBar sb = jsp.getVerticalScrollBar();
						
						int position = sb.getMaximum();
						
						// �̵�
						sb.setValue(position);
						
						// ��� �����ڿ��� �� �޼����� ����
//						broadcast("[" + ip + "] : " + msg);

						}
					}
				} catch (IOException e) {
					list.remove(this);
				}
		} // run() end

		private void broadcast(String msg) {
			// ArrayList �ȿ� �ִ� ��ü�� �ϳ��� ������ ����
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
