package fishTank;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Game window that contains the game gui and controls.
 * @version 1.0
 */

public class GameWindow extends JPanel{
	//Atributes
	private static final long serialVersionUID = 1L;
	private Card[] card;
	private JPanel cardPanel, textPanel;
	private JLabel levelText, pointsText;
	private FishTank aquarium;
	private JButton endGame;
	private Listen listen;
	private int cardQuantity, gameCardsFlipped;
	protected int level, points, acumulatedPoints;
	private int cardWidth, cardHeight;
	private Boolean matchingCards, gameEnded;
	private int[] flipped;
	private int[][] cardSizes;
	/**
	 * GameWindow constructor initializing basic atributes
	 */
	public GameWindow() {
		
		
		//Initializing atributes and objects
		level = 1;
		points = 0;
		acumulatedPoints = 0;
		cardQuantity = 4;
		gameCardsFlipped = 0;
		//Starting GUI
		initGUI();
		gameEnded = false;
		cardSizes = new int[][]{{150,225,2,2},{145,217,2,3},{118,190,2,4},{113,148,3,4}};
		cardPanel.setLayout(new GridLayout(cardSizes[0][2], cardSizes[0][3]));
		card = new Card[cardQuantity];
		cardWidth = 150;
		cardHeight = 225;
		listen = new Listen();
		matchingCards = false;
		flipped = new int[2];
		cardPanelGUI();
		//window 
		setBackground(new Color(176, 224, 230));		
		this.setSize(800,600);
		this.setVisible(true);
	}
	/**
	 * Reloads the window for the next level
	 * adding a level and changin the card positions.
	 */
	private void nextLevel() {
		//Initilizing atributes changed
		level = level + 1;
		cardQuantity = cardQuantity + 2;
		gameCardsFlipped = 0;
		flipped = new int[2];
		listen.cardsFlipped = 0;
		//Remove last level cards
		cardPanel.removeAll();
		cardPanel.revalidate();
		//Max cards shown are 12
		if(level >4) {
			cardPanel.setLayout(new GridLayout(cardSizes[3][2], cardSizes[3][3]));
			cardWidth = cardSizes[3][0];
			cardHeight = cardSizes[3][1];
			cardQuantity = 12;
		}else {
			cardPanel.setLayout(new GridLayout(cardSizes[level-1][2], cardSizes[level-1][3]));
			cardWidth = cardSizes[level-1][0];
			cardHeight = cardSizes[level-1][1];
			
		}
		//New cards
		card = new Card[cardQuantity];
		
		matchingCards = false;
		levelText.setText("Level: " + level);
		//Refreshes aquarium
		aquarium.removeFishes();
		aquarium.repaint();
		cardPanelGUI();

	}
	
	/**
	 * True if game ended, false if on-going.
	 * @return boolean gameEnded
	 */
	public boolean didGameEnd() {
		return gameEnded;
	}
	/**
	 * Randomly adds pair of cards to the cardPanelGUI
	 */
	private void cardPanelGUI() {
		int pair = 0;
		//initilizes cards with size and id
		for(int i=0; i<cardQuantity; i++) {
			card[i] = new Card(cardWidth, cardHeight, pair);

			card[i].revalidate();
			card[i].repaint();
			if((i+1)%2 == 0) {
				pair++;
			}
			
		}
		//Random Shuffle code from https://www.youtube.com/watch?v=8I37elnmZ2I
		for(int i = 0; i< cardQuantity; i++) {
			int random = i + (int)(Math.random()* (card.length-i));
			Card temp = card[random];
			card[random] = card[i];
			card[i] = temp;
		}
		//Adds cards to JPanel
		for(int i=0; i<cardQuantity; i++) {
			card[i].addMouseListener(listen);
			cardPanel.add(card[i]);
		} 

		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	/**
	 * Checks if two cards are the same by 
	 * checking the id of the card. Then adds or rest points.
	 * Also checks if points > 0, if not game is lost.
	 * @param card1 //Card position
	 * @param card2 //Card position
	 */
	private void checkifMatching(int card1, int card2) {
		
		//Matching cards, add fishes and points
		if(card[card1].getId() == card[card2].getId()) {
			matchingCards = true;
			points++;
			acumulatedPoints++;
			gameCardsFlipped = gameCardsFlipped+2;
			aquarium.addFish(card[card1].getId());
			aquarium.repaint();
			timer(300, 60);
		} 
		//Not Matching cards
		else{
			if(card[card1].getAlreadyFlipped() == true || card[card2].getAlreadyFlipped() == true) {
				points--;
			}
			matchingCards = false;
			timer(0,1000);
		}
		//Sets the card boolean value to already flipped
		card[card1].setAlreadyFlipped(true);
		card[card2].setAlreadyFlipped(true);
		//Checks game state
		if(points < 0) {
			gameEnded = true;
		}
		pointsText.setText("Points: " + points);
	}
	/**
	 * If Cards are matching then they will fade and remove listener
	 * @param card1 //Card position
	 * @param card2 //Card position
	 * @param float opacity
	 */
	private void isMatching(int card1, int card2, float opacity) {
		//If opacity is 0 then remove listener
		if(opacity == 0) {
			card[card1].removeMouseListener(listen);
			card[card2].removeMouseListener(listen);
		}
		//Changes the opacity
		card[card1].fadeCard(opacity);
		card[card2].fadeCard(opacity);
	}
	/**
	 * If Cards are not matching then flippes them over again
	 * @param card1 //Card position
	 * @param card2 //Card position
	 */
	private void notMatching(int card1, int card2) {
		card[card1].setState(0);
		card[card2].setState(0);
	}
	/**
	 * Initilizes the GUI by adding the elements in the position
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25, 0, 525, 250,10};
		gridBagLayout.rowHeights = new int[]{ 46, 210};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		cardPanel = new JPanel();
		cardPanel.setOpaque(false);
		cardPanel.setBackground(new Color(0, 0, 205));
		cardPanel.setSize(539,450);
		GridBagConstraints gbc_cardPanel_1 = new GridBagConstraints();
		gbc_cardPanel_1.fill = GridBagConstraints.BOTH;
		gbc_cardPanel_1.gridheight = 2;
		gbc_cardPanel_1.insets = new Insets(15, 10, 0, 5);
		gbc_cardPanel_1.gridx = 2;
		gbc_cardPanel_1.gridy = 1;
		add(cardPanel, gbc_cardPanel_1);
		
		aquarium = new FishTank();
		GridBagConstraints gbc_aquarium = new GridBagConstraints();
		gbc_aquarium.fill = GridBagConstraints.BOTH;
		gbc_aquarium.anchor = GridBagConstraints.CENTER;
		gbc_aquarium.insets = new Insets(15, 20, 5, 5);
		gbc_aquarium.gridx = 3;
		gbc_aquarium.gridy = 1;
		add(aquarium, gbc_aquarium);
		
		textPanel = new JPanel();
		textPanel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 25, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 2;
		add(textPanel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{225, 0};
		gbl_panel.rowHeights = new int[]{41, 41, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		textPanel.setLayout(gbl_panel);
		
		levelText = new JLabel("Level: " + level);
		levelText.setForeground(new Color(255, 255, 255));
		levelText.setFont(new Font("Cooper Black", Font.PLAIN, 35));
		levelText.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_level_1 = new GridBagConstraints();
		gbc_level_1.fill = GridBagConstraints.BOTH;
		gbc_level_1.insets = new Insets(0, 0, 40, 0);
		gbc_level_1.gridx = 0;
		gbc_level_1.gridy = 0;
		textPanel.add(levelText, gbc_level_1);
		
		pointsText = new JLabel("Points: " + points);
		pointsText.setRequestFocusEnabled(false);
		pointsText.setForeground(Color.WHITE);
		pointsText.setFont(new Font("Cooper Black", Font.PLAIN, 35));
		pointsText.setHorizontalAlignment(SwingConstants.LEFT);
		pointsText.setHorizontalTextPosition(SwingConstants.LEADING);
		GridBagConstraints gbc_Points = new GridBagConstraints();
		gbc_Points.insets = new Insets(0, 0, 50, 0);
		gbc_Points.fill = GridBagConstraints.BOTH;
		gbc_Points.gridx = 0;
		gbc_Points.gridy = 1;
		textPanel.add(pointsText, gbc_Points);
		
		endGame = new JButton();
		endGame.addMouseListener(new Listen());
		endGame.setFocusable(false);
		endGame.setRequestFocusEnabled(false);
		endGame.setContentAreaFilled(false);
		endGame.setBorderPainted(false);
		endGame.setIcon(new ImageIcon(GameWindow.class.getResource("/images/endGame.png")));
		GridBagConstraints gbc_endGame = new GridBagConstraints();
		gbc_endGame.insets = new Insets(0, 0, 20, 50);
		gbc_endGame.anchor = GridBagConstraints.NORTH;
		gbc_endGame.gridx = 3;
		gbc_endGame.gridy = 3;
		add(endGame, gbc_endGame);
	}
	/**
	 * Timer that flipps cards controls opacity
	 * @param startDelay
	 * @param period
	 */
	private void timer(int startDelay, int period) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		int counter = 0;
			@Override
			public void run() {
				if(matchingCards == false) {
					if(counter == 1) {
						notMatching(flipped[0], flipped[1]);
						listen.cardsFlipped = 0;
						timer.cancel();
					}
				}
				if(matchingCards == true) {
					float fade = 1 - (float)counter/20;
					if(fade <0) {
						listen.cardsFlipped = 0;
						if(gameCardsFlipped == cardQuantity) {
							nextLevel();
						}
						timer.cancel();
					}else {
						isMatching(flipped[0], flipped[1], fade);
					}
				}
				counter++;
			};
		};
		
		timer.scheduleAtFixedRate(task, startDelay, period);
	}
	
	/**
	 * @author Ingrid-E {@link https://github.com/Ingrid-E}
	 * Mouse Listener that identifies when two cards is clicked
	 * so it can be checked if its matching or not.
	 * 
	 * Algo checks buttons.
	 *
	 */
	class Listen  implements MouseListener{
		public int cardsFlipped = 0;
		private Card lastPressed;
		@Override 
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == endGame) {
				gameEnded = true;
			}

			if(this.cardsFlipped !=2) {
				if(e.getSource().getClass() == Card.class) {
					for(int i=0; i<cardQuantity; i++) {
						
						if(e.getSource() == card[i] &&  this.cardsFlipped != 2) {
							if(card[i] != lastPressed) {
								lastPressed = card[i];
								flipped[this.cardsFlipped] = i;
								card[i].setState(1);
								this.cardsFlipped++;
							}else {
								
							}
						}
						if(this.cardsFlipped == 2) {
							 checkifMatching(flipped[0], flipped[1]);
							 break;
						}
					}
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
			if(e.getSource() == endGame) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == endGame) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
	}
}
