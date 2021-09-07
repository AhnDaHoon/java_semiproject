package semiproject;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GameServer extends JFrame {

	private final JTextArea textArea;
	private final List<Client> clients = new ArrayList<>();
	private final Map<Client, Integer> clientMap = new HashMap<>();

	private final Map<Integer, String> nicknameMap = new HashMap<>();
	private final Map<Integer, String> characterMap = new HashMap<>();
	int cnt = 0;

	// 문제섞는 클래스를 받기위한 변수
	problemShuffle ps = new problemShuffle();
	List<Integer> deduplication = ps.problemShuffle(10); // 숫자는 데이터베이스에 있는 문제의 수를 넣어주면됨

	ProblemNumberVO nvo;
	String stringProblem, stringNumber;

	int problemCount = 0;

	int problemNumber = 0;

	Socket clientSocket;

	Boolean start = false;

	Thread th;
	
	GameServer() {

		initClients();
		textArea = new JTextArea();
		JButton exitButton = new JButton("Exit");
		JScrollPane scrollPanel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Font f = new Font("굴림체", Font.PLAIN, 20);
		textArea.setFont(f);
		add(scrollPanel, "Center");
		add(exitButton, "South");
		exitButton.addActionListener(e -> System.exit(0));
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GameServer");
		setVisible(true);
		setLocationRelativeTo(null);
		chatServerStart();
	}

	private void initClients() {
		for (int i = 0; i < 5; i++) {
			clients.add(null);
		}
	}

	public void chatServerStart() {
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				clientSocket = serverSocket.accept();
				Client client = new Client(this, clientSocket);
				if (isFull()) {
					client.sendMessage("EXCEPTION:방이 꽉찼습니다.");
					clientSocket.close();
				}
				addClient(client);
				client.start();
			}
		} catch (IOException e) {
		}
	}

	private void addClient(Client client) {
		for (int i = 0; i < 5; i++) {
			if (clients.get(i) == null) {
				clientMap.put(client, i);
				clients.set(i, client);
				break;
			}
		}
	}

	// S -> C: broadcast
	// S <- C: send
	// send
	// 1. status
	// 1.1 입장
	// 1.2 퇴장
	// 2. answer

	private boolean isFull() {
		int count = 0;
		for (Client client : clients) {
			if (client != null) {
				count++;
			}
		}
		return count >= 5;
	}

	
	private boolean sameId() {
		boolean same = false;

		for (int i = 1; i < nicknameMap.size(); i++) {
			if (nicknameMap.get(i - 1).equals(nicknameMap.get(nicknameMap.size() - 1))) {
				same = true;
				System.out.println("닉네임 중복됨");
			}
			System.out.println(nicknameMap.get(nicknameMap.size() - 1) + "기준");
			System.out.println(nicknameMap.get(i - 1) + "비교");
		}
		return same;
	}

	private void broadcast(String message) {
		textArea.append(message + "\n");
		for (Client client : clients) {
			if (client != null) {
				client.sendMessage(message);
			}
		}
	}

	// 캐릭터의 정보 전달
	private void sendConnectedClients() {
		StringBuilder stringBuilder = new StringBuilder("E:");
		String delimiter = "";
		for (Map.Entry<Integer, String> entry : nicknameMap.entrySet()) {
			stringBuilder.append(delimiter);
			stringBuilder.append(entry.getValue());
			stringBuilder.append(",");
			stringBuilder.append(entry.getKey());
			delimiter = "|";
		}

		String message = stringBuilder.toString();
		System.out.println("message = " + message);
		broadcast(message);
	}

	// 캐릭터 이미지 전달
	private void sendCharacterImage() {
		StringBuilder stringBuilder = new StringBuilder("C:");
		String delimiter = "";
		for (Map.Entry<Integer, String> entry : characterMap.entrySet()) {
			stringBuilder.append(delimiter);
			stringBuilder.append(entry.getValue());
			stringBuilder.append(",");
			stringBuilder.append(entry.getKey());
			delimiter = "|";
		}

		String message = stringBuilder.toString();
		System.out.println("message = " + message);
		broadcast(message);
	}

	public static void main(String[] args) {
		new GameServer();
	}

	class Client extends Thread {
		private final GameServer gameServer;

		private BufferedReader br;
		private PrintWriter pw;
		private String ip;

		public Client(GameServer gameServer, Socket client) {
			this.gameServer = gameServer;
			this.ip = client.getInetAddress().getHostAddress();
			try {
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// message template
		// client -> server
		// E:nickname
		// X:nickname

		// A:nickname:정답
		// server -> client
		// E:index:nickname
		// X:index

		// A:index:정답
		// login

		// client -> E:client.nickname

		// client -> server
		// E:nickname,index|nickname2,index2|...
		// X:nickname
		// A:nickname:정답

		@Override
		public void run() {
			try {
				while (true) {
					String message = br.readLine();
					String[] split = message.split(":");
					String action = split[0];
					String nickname = split[1];
					int index = getIndex(this);
					switch (action) {
					case "E":
						if (start == false) {
							// gameServer.broadcast(String.format("E:%d:%s", index, nickname));
							String imagePosition = split[2];
							nicknameMap.put(index, nickname);
							characterMap.put(index, imagePosition);
							if (sameId()) {
								sendMessage("EXCEPTION:같은 아이디로 이미 게임중입니다.");
								clientSocket.close();
								gameServer.broadcast(String.format("X:%d", index));
								removeClient(this);
							}
							gameServer.sendConnectedClients();
							gameServer.sendCharacterImage();
							break;
						} else {
							sendMessage("EXCEPTION:이미 게임이 시작되었습니다.");
							clientSocket.close();
						}
					case "X":
						gameServer.broadcast(String.format("X:%d", index));
						removeClient(this);
						break;
					case "A":
						String answer = split.length > 2 ? split[2] : null;
						gameServer.broadcast(String.format("A:%d", index));
						// TODO: 정답 체크

						ProblemNumberDAO dao2 = new ProblemNumberDAO();
						int numAnswer = dao2.problemAnswer(problemNumber);
						if (Integer.parseInt(answer) == numAnswer) {
							gameServer.broadcast(String.format("Ok:%d:%f", index, 100 + Math.random()));
						} else {
							gameServer.broadcast(String.format("No:%d", index));
						}
						// N: 다음문제
						break;

					case "A2":
						String chat = split.length > 2 ? split[2] : null;
						gameServer.broadcast(String.format("chat:%d:%s", index, chat));
						// TODO: 정답 체크
						break;

					case "S":
						// 0번 인덱스만 시작 가능
						if (index == 0) {
							gameServer.broadcast(String.format("Start"));
							th = new ProblemThread();
							th.start();
							start = true;
						} else {
							gameServer.broadcast(String.format("hurryUp:%d:%s", index, "빨리빨리"));
						}

						break;

					default:
						sendMessage("EXCEPTION:잘못된 명령입니다.");
					}
				}
			} catch (IOException e) {
				clients.remove(this);
			}
		} // run() end

		private void sendMessage(String message) {
			pw.println(message);
			pw.flush();
		}

		private class ProblemThread extends Thread {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					String[] problem = test(deduplication.get(i));
					gameServer.broadcast(String.format("P:%s", problem[0]));
					gameServer.broadcast(String.format("N:%s", problem[1]));
					gameServer.broadcast(String.format("N:%s", problem[2]));
					gameServer.broadcast(String.format("N:%s", problem[3]));
					gameServer.broadcast(String.format("N:%s", problem[4]));
					// 2021.09.03 추가
					gameServer.broadcast(String.format("answer2:%s", problem[5]));
					gameTimeImage();
					try {
						// 수정 전
//						Thread.sleep(11000);

						// 수정 후
						Thread.sleep(13000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				gameServer.broadcast("endGame");
				start = false;
			}

		}

	}

	private int getIndex(Client client) {
		return clientMap.get(client);
	}

	private void removeClient(Client client) {
		Integer index = clientMap.remove(client);
		nicknameMap.remove(index);
		clients.set(index, null);
	}

	public String[] test(int deduplicationNum) {
		// problemShuffle 쓰는 코드
		// 문제의 중복을 막고 문제를 섞는 클래스
		String[] a = new String[6];
		// 정답번호 받아오기
		problemNumber = deduplication.get(deduplicationNum);
		// 출제할 문제 받아오기
		ProblemDAO dao = new ProblemDAO();
		ProblemVO vo = dao.problem(deduplication.get(deduplicationNum));
//		 ProblemVO2 vo = dao.problem(99); 
		a[0] = (vo.getProblem());

		// 출체 문제 보기 받아오기
		ProblemNumberDAO ndao = new ProblemNumberDAO();
		nvo = ndao.problem(deduplication.get(deduplicationNum));
//		 nvo = ndao.problem(99);

		// 객관식 받아요기
		a[1] = (nvo.getNumone());
		a[2] = (nvo.getNumtwo());
		a[3] = (nvo.getNumthree());
		a[4] = (nvo.getNumfour());
		a[5] = Integer.toString(nvo.getAnswerNo());
		return a;
	}

	public void gameTimeImage() {

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable runnable = new Runnable() {
			int countdownStarter = 11;

			public void run() {

//	                System.out.println(countdownStarter);
				countdownStarter--;
				// 수정 전
//				broadcast(String.format("Time:%d", countdownStarter));

				// 수정 후
				broadcast(String.format("Time:%d:%b", countdownStarter, true));

				if (countdownStarter <= 0) {
					System.out.println("Time Over!");
					// 추가
					broadcast(String.format("Time:%d:%b", countdownStarter, false));

					scheduler.shutdown();
				}
			}
		};
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);

	}

}// ChatServer class end