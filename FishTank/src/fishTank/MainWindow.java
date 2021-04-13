package fishTank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Main Window GUI, 
 * @version 1.0
 */
public class MainWindow extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton exit, play, rules;
	private JPanel window;
	private JLabel title;
	private Listen mouseClick;
	protected Boolean playClicked, rulesClicked;

	/**
	 * Public Main Window Constructor, starts components.
	 */
	public MainWindow() {
		
		window = this;
		
		//Initializing components
		mouseClick = new Listen();
		title = new JLabel();
		play = new JButton();
		rules = new JButton();
		exit = new JButton("X");
		//Boolean components
		playClicked = false;
		rulesClicked = false;
		//Adding Listener
		exit.addMouseListener(mouseClick);
		play.addMouseListener(mouseClick);
		rules.addMouseListener(mouseClick);

		//Add components
		initGUI();
		setLayout(null);
		this.setSize(800,600);
		this.setVisible(true);
	
	}
	/**
	 * Main Window GUI
	 */
	private void initGUI() {
		//Game Title
		title.setBounds(88, 103, 626, 92);
		title.setIcon(new ImageIcon(MainWindow.class.getResource("/images/gameTitle.png")));
		title.setAlignmentX(Component.RIGHT_ALIGNMENT);
		title.setAlignmentY(1.0f);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		//Panel that contains the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(195, 284, 370, 213);
		buttonPanel.setOpaque(false);
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonPanel.setAlignmentY(0.0f);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		add(buttonPanel);
		//Play Button
		play.setContentAreaFilled(false);
		play.setRequestFocusEnabled(false);
		play.setIcon(new ImageIcon(MainWindow.class.getResource("/images/playButton.png")));
		play.setMargin(new Insets(2, 14, 10, 14));
		play.setBorder(null);
		buttonPanel.add(play, BorderLayout.NORTH);
		//Rules Button
		rules.setRequestFocusEnabled(false);
		rules.setIcon(new ImageIcon(MainWindow.class.getResource("/images/rulesButton.png")));
		rules.setContentAreaFilled(false);
		rules.setBorder(null);
		buttonPanel.add(rules, BorderLayout.SOUTH);
		//Exit Button

		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.setFont(new Font("Tahoma", Font.BOLD, 47));
		exit.setForeground(new Color(255, 255, 255));
		exit.setContentAreaFilled(false);
		exit.setBorder(null);
		exit.setRequestFocusEnabled(false);
		exit.setBounds(21, 21, 67, 36);
		add(exit);
		//Gif Background Panel
		JLabel background = new JLabel();
		background.setBorder(null);
		background.setIcon(new ImageIcon(MainWindow.class.getResource("/images/underwater.gif")));
		background.setVerticalAlignment(SwingConstants.BOTTOM);
		background.setBounds(0, 0, 800, 600);
		add(background);
	}
	/**
	 * @author Ingrid-E {@link https://github.com/Ingrid-E}
	 * Checks button pressed to go change boolean state and change 
	 * cursor look if its over a button
	 */
	class Listen  implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == play) {
				playClicked = true;
			}
			if(e.getSource() == rules) {
				rulesClicked = true;
			}
			if(e.getSource() == exit) {
				System.exit(0);
			}
			

		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == exit || e.getSource() == play || e.getSource() == rules) {
				window.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == exit || e.getSource() == play || e.getSource() == rules) {
				window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		}
		
	}
}
