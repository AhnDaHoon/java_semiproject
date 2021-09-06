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

public class Commercial extends JFrame {
   private static final long serialVersionUID = 1L;
   private static final String TITLE = "My First Media Player";
   private static final String VIDEO_PATH = "D:\\dev\\eclipse\\workspace\\basic\\src\\commercial\\commercial1.mp4";
   private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
   private JLabel playButton;

   public Commercial(String title) {
      super(title);
      mediaPlayerComponent = new EmbeddedMediaPlayerComponent();		
   }
   public void initialize() {
      this.setBounds(100, 100, 600, 400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.addWindowListener(new WindowAdapter() {
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
      playButton = new JLabel("Play");
//      Time();
      controlsPane.add(playButton);    	
      contentPane.add(controlsPane, BorderLayout.SOUTH);
 	 
      this.setContentPane(contentPane);
      this.setVisible(true);
   }
   public void loadVideo(String path) {
      mediaPlayerComponent.mediaPlayer().media().startPaused(path);   
      mediaPlayerComponent.mediaPlayer().controls().play();

   }
   public static void main( String[] args ){
      try {
         UIManager.setLookAndFeel(
         UIManager.getSystemLookAndFeelClassName());
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      Commercial application = new Commercial(TITLE);
      application.initialize(); 
      application.setVisible(true);  
      application.loadVideo(VIDEO_PATH);
   }
   
   
   
   
   
	public void Time(JLabel j) {

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable runnable = new Runnable() {
			int countdownStarter = 31;

			public void run() {
				countdownStarter--;
				System.out.println(countdownStarter);
				if (countdownStarter <= 0) {
					scheduler.shutdown();
				}
			}
		};
		scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);

	}
   
   
   
}