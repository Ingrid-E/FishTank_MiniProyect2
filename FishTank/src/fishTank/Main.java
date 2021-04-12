package fishTank;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	private static JFrame window;
	private static MainWindow menu; 
	private static GameWindow game;
	private static EndWindow end;
	private static JOptionPane gameLost;
	private static String windowShowing;
	private static RulesWindow rules;

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
	
	private static void initGUI() {
		window = new JFrame();
		windowShowing = "menu";
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
		timer();
	}
	
	private static void changeWindow(String show) {
		window.getContentPane().removeAll();
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
