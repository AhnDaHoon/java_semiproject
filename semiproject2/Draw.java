package semiproject2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Draw extends JFrame implements ActionListener{

	int x;
	int y;

	JButton[] jbtn = new JButton[7];

	Draw() {
		setLayout(null);
		
		
		
		
		// 카드
		jbtn[0] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[0].setBounds(50, 50, 100, 200);

		jbtn[1] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[1].setBounds(200, 50, 100, 200);

		jbtn[2] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[2].setBounds(350, 50, 100, 200);

		jbtn[3] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[3].setBounds(500, 50, 100, 200);

		jbtn[4] = new JButton(new ImageIcon("src/images/Draw.jpg"));
		jbtn[4].setBounds(650, 50, 100, 200);

		// 창 가운데
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();
		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 500 / 2);

		// 광고 시작 버튼
		
		jbtn[5] = new JButton("광고보고 뽑기!");
		jbtn[5].setBounds(500,300,100,100);
		
		
		//뒤로가기 버튼
		jbtn[6] = new JButton();
		jbtn[6].setBounds(200,300,100,100);
		
		add(jbtn[0]);
		add(jbtn[1]);
		add(jbtn[2]);
		add(jbtn[3]);
		add(jbtn[4]);
		add(jbtn[5]);
		add(jbtn[6]);

		jbtn[5].addActionListener(this);
				
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 500);
		setVisible(true);

		
		
		
		
		
	}

	public static void main(String[] args) {
		new Draw();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtn[5]) {
			System.out.println(e.getSource());
		}
		
	}
}
