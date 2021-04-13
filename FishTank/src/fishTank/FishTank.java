package fishTank;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 * @author Ingrid-E {@link https://github.com/Ingrid-E}
 * Shows aquarium and adds fishes with method
 * @version 1.0
 */
public class FishTank extends JPanel{
	//Atributes
	private static final long serialVersionUID = 1L;
	private ImageIcon[] fishes;
	private JLabel[] fish;
	private JLabel aquarium, shine;
	private ImageIcon fishTank, tankShine;
	/**
	 * Public Constructor, starts atributes and objects
	 */
	public FishTank() {
		//Add Images
		fishes = new ImageIcon[8];
		fish = new JLabel[8];
		//Adds fishes
		for(int i= 0; i<8; i++) {
			fishes[i] = new ImageIcon(MainWindow.class.getResource("/images/Fish"+i+".gif"));
			fish[i] = new JLabel(fishes[i]);
		}
		//Adds images
		fishTank = new ImageIcon(MainWindow.class.getResource("/images/Aquarium.png"));
		tankShine = new ImageIcon(MainWindow.class.getResource("/images/shine.png"));
		
		shine = new JLabel(tankShine);
		shine.setBounds(0, 0, 206, 194);
		add(shine);
		aquarium = new JLabel(fishTank);
		aquarium.setBounds(0, 0, 206, 194);
		add(aquarium);
		
		setRequestFocusEnabled(false);
		setOpaque(false);
		this.setSize(206, 194);
		setLayout(null);
		this.setVisible(true);	
		
	}
	/**
	 * Adds all the fishes to the aquarium
	 */
	public void addAllFishes() {
		this.remove(aquarium);
		for(int i=0; i<8; i++) {
			fish[i].setBounds(0, 0, 206, 194);
			add(fish[i]);
		}
		add(aquarium);
	}
	/**
	 * Remove all the fishes
	 */
	public void removeFishes() {
		for(int i=0; i<8; i++) {
			this.remove(fish[i]);
		}
	}
	/**
	 * Removes an especific fish
	 * @param id <- fish
	 */
	public void removeFish(int id) {
		this.remove(fish[id]);
	}
	/**
	 * Adds an especific fish
	 * @param id <- fish
	 */
	public void addFish(int id) {
		this.remove(aquarium);
		fish[id].setBounds(0, 0, 206, 194);
		add(fish[id]);
		add(aquarium);
	}
	

}
