package semiproject;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TCanvass extends JPanel {
	JLabel jlbId, jlbTitle, jlbPw;
	JTextField jtfId;
	JPasswordField jtfPw;
	JButton jbtnLogin, jbtnRegister, jbtnForget;
	public TCanvass(JLabel jlbId, JLabel jlbTitle, JLabel jlbPw, JTextField jtfId, JPasswordField jtfPw,
			JButton jbtnLogin, JButton jbtnRegister, JButton jbtnForget) {
		super();
		this.jlbId = jlbId;
		jlbId = new JLabel("ÀÚ¾Æ¾Æ");
		this.jlbTitle = jlbTitle;
		this.jlbPw = jlbPw;
		this.jtfId = jtfId;
		this.jtfPw = jtfPw;
		this.jbtnLogin = jbtnLogin;
		this.jbtnRegister = jbtnRegister;
		this.jbtnForget = jbtnForget;
	}

}
