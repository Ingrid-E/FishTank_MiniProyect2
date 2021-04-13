package fishTank;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Rules GUI Window, shows rules and go back button
 * @version 1.0
 */
public class RulesWindow extends JPanel{
	//Creates atributes
	private JLabel images, rules;
	private JButton mainMenu, leftArrow, rightArrow;
	private Listen listen;
	private int image;
	private static final long serialVersionUID = 1L;
	protected boolean mainMenuClicked;
	private JLabel background;
	/**
	 * Default public constructor start atributes and objects
	 */
	public RulesWindow() {
		
		//Initilize contents 
		listen = new Listen();
		images = new JLabel();
		rules = new JLabel("Rules");
		mainMenu = new JButton();
		mainMenu.addMouseListener(listen);
		leftArrow = new JButton();
		leftArrow.setFocusable(false);
		leftArrow.addMouseListener(listen);
		rightArrow = new JButton();
		rightArrow.setFocusable(false);
		rightArrow.addMouseListener(listen);
		mainMenuClicked = false;
		//Current rule showing up
		image = 1;
		//Basic Window Atributes
		setBackground(new Color(173, 216, 230));
		setSize(new Dimension(800, 600));
		setLayout(null);
		initGUI();
	}
	/**
	 * Starts window GUI adding elements to the JFrame
	 */
	private void initGUI() {
		
		images.setSize(new Dimension(600, 442));
		images.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/rule1.png")));
		images.setBounds(96, 65, 600, 446);
		add(images);
		
		rules.setForeground(Color.WHITE);
		rules.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		rules.setBounds(321, 0, 152, 71);
		add(rules);
		
		mainMenu.setContentAreaFilled(false);
		mainMenu.setBorderPainted(false);
		mainMenu.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/mainMenuButton.png")));
		mainMenu.setBounds(237, 504, 335, 96);
		add(mainMenu);
		
		leftArrow.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/leftArrow.png")));
		leftArrow.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		leftArrow.setContentAreaFilled(false);
		leftArrow.setBorderPainted(false);
		leftArrow.setBounds(0, 252, 100, 81);
		add(leftArrow);
		
		rightArrow.setContentAreaFilled(false);
		rightArrow.setBorderPainted(false);
		rightArrow.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/rightArrow.png")));
		rightArrow.setBounds(701, 252, 89, 81);
		add(rightArrow);
		
		background = new JLabel();
		background.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/Bubbles.gif")));
		background.setBounds(0, 0, 800, 600);
		add(background);
	}
	/**
	 * @author Ingrid-E {@link https://github.com/Ingrid-E}
	 * Class that implements MouseListener and checks if a button is pressed
	 * if it's arrows then moves the images, and if main menu is pressed
	 * then boolean value is changed
	 */
	class Listen  implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == mainMenu) {
				mainMenuClicked = true;
			}
			if(e.getSource() == leftArrow) {
				if(image-1 != 0) {
					image--;
					images.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/rule" + image +".png")));
				}
			}
			if(e.getSource() == rightArrow) {
				if(image+1 != 5) {
					image++;
					images.setIcon(new ImageIcon(RulesWindow.class.getResource("/images/rule" + image +".png")));
				}
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
			if(e.getSource() == mainMenu) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == mainMenu) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		}
	}
}
