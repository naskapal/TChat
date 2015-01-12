package clientGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Chat extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Chat() {
		intialize();
	}
	private void intialize()
	{
		JLabel lblChat = new JLabel("chat");
		add(lblChat);
	}

}
