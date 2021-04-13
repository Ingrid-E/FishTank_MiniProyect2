package fishTank;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.ImgUtils;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Creates different fish cards with prefered size and fish
 * @version 1.0
 */
public class Card extends JPanel{
	private static final long serialVersionUID = 1L;
	private int id, state, width, height;
	private float opacity;
	private boolean alreadyFlipped;
	private JLabel card;
	private BufferedImage fishImage, backCard;

	/**
	 * Card Constructor starts atributes and creates the card
	 * @param width
	 * @param height
	 * @param id
	 */
	public Card(int width, int height, int id){
		//Initialize atributes int
		this.width = width;
		this.height = height;
		this.id = id;
		//State 0 backward, 1 flipped
		this.state = 0;	
		this.opacity = 1;
		//Boolean
		this.alreadyFlipped = false;
		//Png
		this.card = new JLabel();
        this.fishImage = new ImgUtils().scaleImage(width,height,Card.class.getResource("/images/"+id+".png"));
        this.backCard = new ImgUtils().scaleImage(width,height,Card.class.getResource("/images/cardBackwards.png"));
		//Objects
		add(card);
		this.setSize(width, height);
		this.setOpaque(false);
		updateUI();
	}
	/**
	 * Get Card if to know which fish is showing in the card
	 * @return int 
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets Card Image
	 * @param id
	 */
	public void setImage(int id) {
		this.id = id;
        this.fishImage = new ImgUtils().scaleImage(width,height,Card.class.getResource("/images/"+id+".png"));
		this.setState(state);
	}
	/**
	 * Gets Card State if flipped or not
	 * @return
	 */
	public int getState() {
		return this.state;
	}
	/**
	 * Sets Cards State 
	 * 0 - Back Card
	 * 1 - Flipped
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
		if(state == 0) {
			this.card.setIcon(new ImageIcon(backCard));
		}else {
			this.card.setIcon(new ImageIcon(fishImage));
		}
	}
	/**
	 * Sets Card state
	 * @param flip
	 */
	public void setAlreadyFlipped(boolean flip) {
		this.alreadyFlipped = flip;
	}
	/**
	 * Gets if card was already flipped
	 * @return boolean
	 */
	public boolean getAlreadyFlipped() {
		return this.alreadyFlipped;
	}
	/**
	 * Sets Card Size
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		setImage(this.id);
	}
	/**
	 * Gets Card
	 * @return
	 */
	public Card getCard() {
		return this;
	}
	/**
	 * Gets the opacity of the card
	 * @return
	 */
	public float getOpacity() {
		return opacity;
	}
	/**
	 * Changed the opacity if the card with the opacity param
	 * @param opacity
	 */
	public void fadeCard(float opacity) {
		this.opacity = opacity;
		BufferedImage fading = new BufferedImage(fishImage.getWidth(),fishImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D createGraphics = fading.createGraphics();
		//float opacity = 0.5f;
		createGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		createGraphics.drawImage(fishImage, null, 0,0);
		this.card.setIcon(new ImageIcon(fading));
		createGraphics.dispose();
	}

}
