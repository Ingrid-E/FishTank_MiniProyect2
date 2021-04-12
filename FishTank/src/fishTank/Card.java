package fishTank;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.ImgUtils;

public class Card extends JPanel{
	private static final long serialVersionUID = 1L;
	private int id, state, width, height;
	private float opacity;
	private boolean alreadyFlipped;
	private JLabel card;
	private BufferedImage fishImage, backCard;
	
	

	
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
        this.fishImage = new ImgUtils().scaleImage(width,height,"src/images/"+id+".png");
        this.backCard = new ImgUtils().scaleImage(width,height,"src/images/cardBackwards.png");
		//Objects
		add(card);
		this.setSize(width, height);
		this.setOpaque(false);
		updateUI();
	}
	public int getId() {
		return id;
	}
	
	public void setImage(int id) {
		this.id = id;
        this.fishImage = new ImgUtils().scaleImage(width,height,"src/images/"+id+".png");
		this.setState(state);
	}
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int state) {
		this.state = state;
		if(state == 0) {
			this.card.setIcon(new ImageIcon(backCard));
		}else {
			this.card.setIcon(new ImageIcon(fishImage));
		}
	}
	
	public void setAlreadyFlipped(boolean flip) {
		this.alreadyFlipped = flip;
	}
	
	public boolean getAlreadyFlipped() {
		return this.alreadyFlipped;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		setImage(this.id);
	}

	public Card getCard() {
		return this;
	}
	
	public float getOpacity() {
		return opacity;
	}
	
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
