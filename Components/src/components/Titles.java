/**
 * @file Titles.java
 * @brief Title class to add text and change basic elements like font,color, text, etc..
 * @author Ingrid Echeverri 201943542
 * @date 1-03-2021
 */
package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Titles extends JLabel {
	private static final long serialVersionUID = 1L;

	//atributes
	//methods
	/** Public Constructor */
	public Titles(String text, int fontSize, Color fontColor) {
		this.setText(text);
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC, fontSize);
		this.setFont(font);
		this.setBackground(fontColor);
		this.setForeground(Color.WHITE);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}
	/**
	 * @brief Change font of text
	 * @param font
	 * @param size
	 */
	public void setFont(String font, int size) {
		this.setFont(new Font(font, Font.BOLD,size));
	}
	/**
	 * @brief Change font color
	 * @param rgb
	 */
	public void setFontColor(Color rgb) {
		this.setForeground(rgb);
	}
	
}
