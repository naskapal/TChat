package clientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.MultiEchoClient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Connect extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private static MultiEchoClient MEC = new MultiEchoClient() ;
	private JTextField serverIP;
	private JTextField nickname;
	private TChat tChat;
	
	public Connect(TChat source)
	{
		tChat = source;
		initialize();
	}
	
	public Connect() {
		initialize();
	}
	
	private void initialize()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblServerIp = new JLabel("Server IP");
		add(lblServerIp, "4, 4, right, default");
		
		serverIP = new JTextField();
		add(serverIP, "6, 4, fill, default");
		serverIP.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname");
		add(lblNickname, "4, 6, right, default");
		
		nickname = new JTextField();
		nickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(nickname, "6, 6, fill, default");
		nickname.setColumns(10);
		
		JButton btnConnect = new JButton("Connect!");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tryConnect(serverIP.getText().toString(), nickname.getText().toString());
			}
		});
		add(btnConnect, "12, 6");
	}
	
	public void tryConnect(String IPAddress, String name)
	{
		if (MEC.initiate(IPAddress,name))
		{
			tChat.changePanel(this, "chat", 100, 100, 600, 800);
		}
	}

}
