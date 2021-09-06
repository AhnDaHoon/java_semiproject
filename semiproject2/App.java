package semiproject2;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "My First Media Player";
	private static final String VIDEO_PATH = "D:\\dev\\eclipse\\workspace\\basic\\src\\commercial\\commercial1.mp4";
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	JLabel jlb;

	public App(String title) {
		super(title);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.release();
				System.exit(0);
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

		JPanel controlsPane = new JPanel();
		jlb = new JLabel("광고가 곧 시작 됩니다.");
		controlsPane.add(jlb);
		contentPane.add(controlsPane, BorderLayout.SOUTH);
		setContentPane(contentPane);
		setVisible(true);
		mediaPlayerComponent.mediaPlayer().controls().play();

	}

	public void initialize() {
		mediaPlayerComponent.mediaPlayer().controls().play();
		
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable runnable = new Runnable() {
			int countdownStarter = 31;

			public void run() {
				countdownStarter--;
				jlb.setText(countdownStarter+"초 남았습니다.");
				System.out.println(countdownStarter);
				if (countdownStarter <= 0) {
					scheduler.shutdown();
					// 뽑기 페이지로 넘어가기 기능 추가
					new Draw();
					setVisible(false);
				}
			}
		};
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
		

	}

	public void loadVideo(String path) {
		mediaPlayerComponent.mediaPlayer().media().startPaused(path);
	}
   
   

   
   public static void main( String[] args ){
      try {
         UIManager.setLookAndFeel(
         UIManager.getSystemLookAndFeelClassName());
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      App application = new App(TITLE);
      application.setVisible(true);  
      application.loadVideo(VIDEO_PATH);
      application.initialize(); 
   }
}