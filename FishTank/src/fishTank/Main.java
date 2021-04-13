package fishTank;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Main class that controls the window that are shown.
 * Main Menu, Game, End Window and Rules.
 * @version 1.0
 */

public class Main {
	/* Atributes, diferent windows and mouse adapter */
	private static JFrame window;
	private static MainWindow menu; 
	private static GameWindow game;
	private static EndWindow end;
	private static RulesWindow rules;
	private static MouseAdapter listen;
	/**
	 * Shows the window
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				//Add interface
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						//Interface
						initGUI();
					}
				});
			}
			
		});

	}
	/**
	 * Initilices objects and basic window atributes
	 */
	private static void initGUI() {
		//Initilizing objects
		window = new JFrame();
		window.setFocusable(true);
		//Using Mouse Adapter to drag the window around the screen
		listen = new MouseAdapter() {
			private int x,y;
			@Override
			public void mouseDragged(MouseEvent e) {
				window.setLocation(window.getLocation().x+e.getX()-x, 
						window.getLocation().y +e.getY()-y);
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				
			}
		};
		//Adding mouse listener
		window.addMouseListener(listen);
		window.addMouseMotionListener(listen);
		//Different Windows
		menu = new MainWindow();
		game = new GameWindow();
		end = new EndWindow(game.level, game.points);
		rules = new RulesWindow();
		//Window 
		window.add(menu);
		window.setUndecorated(true);
		window.setSize(800,600);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		//Starting timers
		timer();
	}
	/**
	 * Changes the window shown by removing and adding new component in window
	 * @param string show
	 */
	private static void changeWindow(String show) {
		//Removing all components
		window.getContentPane().removeAll();
		//Adding new component
		switch(show) {
		case "Game":
			game = new GameWindow();
			game.setVisible(true);
			window.add(game);
			break;
		case "End":
			end = new EndWindow(game.acumulatedPoints, game.level);
			end.setVisible(true);
			window.add(end);
			break;
		case "Main":
			menu = new MainWindow();
			menu.setVisible(true);
			window.add(menu);
			break;
		case "Rules":
			rules = new RulesWindow();
			rules.setVisible(true);
			window.add(rules);
		}
		window.revalidate();
		window.repaint();
		
	}
	/**
	 * Timer that checks every window if its visible and if a change window 
	 * button is pressed
	 */
	private static void timer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(menu.isVisible()) {
					if(menu.playClicked) {
						menu.setVisible(false);
						changeWindow("Game");
					}
					if(menu.rulesClicked) {
						menu.setVisible(false);
						changeWindow("Rules");
					}
				}
				if(game.isVisible()) {
					if(game.didGameEnd()) {
						game.setVisible(false);
						changeWindow("End");
					}
				}
				if(end.isVisible()) {
					if(end.menuClicked) {
						end.setVisible(false);
						changeWindow("Main");
					}
					if(end.againClicked) {
						end.setVisible(false);
						changeWindow("Game");
					}
				}
				if(rules.isVisible()) {
					if(rules.mainMenuClicked) {
						rules.setVisible(false);
						changeWindow("Main");
					}
				}
				
			};
		};
		
		timer.scheduleAtFixedRate(task, 0, 30);
	}

}
