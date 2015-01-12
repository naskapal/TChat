package clientGUI;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class TChat {

	private JFrame frame = new JFrame();
	private Chat chat = new Chat();
	private Connect connect = new Connect(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TChat window = new TChat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TChat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		frame.getContentPane().add(connect, "4, 4, fill, fill");
	}
	
	public void changePanel(Component source, String panelName, int x, int y, int width, int height)
	{
		frame.setBounds(x, y, width, height);
		frame.getContentPane().remove(source);
		if (panelName.equals("chat"))
			frame.getContentPane().add(chat, "4, 4, fill, fill");
		else
			frame.getContentPane().add(connect, "4, 4, fill, fill");
	}
	
}
