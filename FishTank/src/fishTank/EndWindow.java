package fishTank;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * End Window GUI that shows final acumulates points and final level
 * @version 1.0
 */
public class EndWindow extends JPanel{
	//Creating Atributes
	private static final long serialVersionUID = 1L;
	private FishTank aquarium;
	private JLabel endTitle, finalInfo, background;
	private JPanel buttonPanel, textPanel;
	private JButton playAgain, mainMenu, exit;
	protected Boolean againClicked, menuClicked;
	private Font font;
	private Listen listen;
	/**
	 * EndWindow Constructor
	 * @param points //<= Acumulated Points
	 * @param level
	 */
	public EndWindow(int points, int level) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Toucon__.ttf")).deriveFont(47);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		listen = new Listen();
		//Object
		againClicked = false;
		menuClicked = false;
		//Initilize Objects
		setLayout(null);
		endTitle = new JLabel();
		buttonPanel = new JPanel();
		exit = new JButton("X");
		exit.addMouseListener(listen);
		playAgain = new JButton();		
		playAgain.addMouseListener(listen);
		mainMenu = new JButton();
		mainMenu.addMouseListener(listen);
		aquarium = new FishTank();
		aquarium.addAllFishes();
		textPanel = new JPanel();
		finalInfo = new JLabel("<html>Total <br> Points: " + points + "<br><br>Level: " +level+ "</html>");
		finalInfo.setBounds(22, 11, 397, 198);
		background = new JLabel();
		//Starting GUI
		initGUI();
		//window basic atributes
		setBackground(new Color(176, 224, 230));		
		this.setSize(800,600);
		this.setVisible(true);
		
		
	}
	/**
	 * Adding objects atributes and adding them to the JFrame
	 */
	private void initGUI() {
		
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.setFont(new Font("Tahoma", Font.BOLD, 47));
		exit.setForeground(new Color(255, 255, 255));
		exit.setContentAreaFilled(false);
		exit.setBorder(null);
		exit.setRequestFocusEnabled(false);
		exit.setBounds(0, 0, 45, 45);
		add(exit);
		
		endTitle.setIcon(new ImageIcon(EndWindow.class.getResource("/images/gameOverTitle.png")));
		endTitle.setBounds(0, 0, 800, 176);
		endTitle.setPreferredSize(new Dimension(64, 150));
		endTitle.setFont(font);
		endTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(endTitle);

		buttonPanel.setBounds(0, 478, 800, 122);
		buttonPanel.setPreferredSize(new Dimension(10, 100));
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(buttonPanel);
		
		playAgain.setFocusable(false);
		playAgain.setContentAreaFilled(false);
		playAgain.setBorderPainted(false);
		playAgain.setIcon(new ImageIcon(EndWindow.class.getResource("/images/playAgainButton.png")));
		buttonPanel.add(playAgain);
		
		mainMenu.setFocusable(false);
		mainMenu.setBorderPainted(false);
		mainMenu.setContentAreaFilled(false);
		mainMenu.setIcon(new ImageIcon(EndWindow.class.getResource("/images/mainMenuButton.png")));
		buttonPanel.add(mainMenu);
		
		aquarium.setBounds(506, 219, 223, 223);
		aquarium.setVisible(true);
		aquarium.setPreferredSize(new Dimension(250, 100));
		add(aquarium);
		
		textPanel.setBorder(new LineBorder(new Color(255, 255, 255), 5));
		textPanel.setBackground(new Color(18,115,173));
		textPanel.setBounds(44, 208, 407, 208);
		add(textPanel);
		textPanel.setLayout(null);
		
		finalInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		finalInfo.setForeground(Color.WHITE);
		finalInfo.setFont(new Font("Touch Of Nature", Font.PLAIN, 45));
		finalInfo.setHorizontalAlignment(SwingConstants.LEFT);
		textPanel.add(finalInfo);
		
		background.setIcon(new ImageIcon(EndWindow.class.getResource("/images/Bubbles.gif")));
		background.setBounds(0, 0, 800, 600);
		add(background);
	}
	/**
	 * @author Ingrid-E {@link https://github.com/Ingrid-E}
	 * Checks if a button is pressed or hovered and changed boolean value
	 */
	class Listen  implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == playAgain) {
				againClicked = true;
			}
			if(e.getSource() == mainMenu) {
				menuClicked = true;
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
			if(e.getSource() == playAgain || e.getSource() == mainMenu ||  e.getSource() == exit ) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == playAgain || e.getSource() == mainMenu || e.getSource() == exit ) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		}
		
	}
}
