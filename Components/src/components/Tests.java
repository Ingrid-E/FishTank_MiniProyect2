package components;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class Tests extends JDialog {

	
	public Tests() {
		setBackground(SystemColor.activeCaption);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel endedText = new JLabel("You Lost!");
		endedText.setForeground(Color.WHITE);
		endedText.setFont(new Font("Touch Of Nature", Font.PLAIN, 30));
		endedText.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(endedText, BorderLayout.CENTER);
		
		JPanel options = new JPanel();
		getContentPane().add(options, BorderLayout.SOUTH);
		
		JButton playAgain = new JButton("Play Again");
		options.add(playAgain);
		
		JButton mainMenu = new JButton("Main Menu");
		options.add(mainMenu);
		
		JButton exitButton = new JButton("Exit");
		options.add(exitButton);
		
	}

}


