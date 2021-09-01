package semiproject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Music extends Thread{

	
	private Player player; 
	// 반복에 대한 설정
	private boolean isLoop;
	
	// 파일담는 변수
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	
	public Music(String name, boolean isloop) {
		try {
			this.isLoop = isLoop;
			file = new File(("D:\\dev\\eclipse\\workspace\\basic\\src\\music\\" + name));
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			double volume = 0.5;
			
			player = new Player(bis); 
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public int getTime() {
		if (player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	public void run () {
		try {
			do {
				Thread.sleep(3000);
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);		
				player = new Player(bis);
			}while(isLoop);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Music m = new Music("music1.wav",true);
		m.start();
		
	}

}
