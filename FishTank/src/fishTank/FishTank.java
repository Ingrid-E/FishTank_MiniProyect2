package fishTank;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class FishTank extends JPanel{

	private static final long serialVersionUID = 1L;
	private ImageIcon[] fishes;
	private JLabel[] fish;
	private JLabel aquarium, shine;
	private ImageIcon fishTank, tankShine;
	
	public FishTank() {
		//Add Images
		fishes = new ImageIcon[8];
		fish = new JLabel[8];
		for(int i= 0; i<8; i++) {
			fishes[i] = new ImageIcon("src/images/Fish"+i+".gif");
			fish[i] = new JLabel(fishes[i]);
		}
		fishTank = new ImageIcon("src\\images\\Aquarium.png");
		tankShine = new ImageIcon("src\\images\\shine.png");
		setRequestFocusEnabled(false);
		setOpaque(false);
		this.setSize(206, 194);
		setLayout(null);
		shine = new JLabel(tankShine);
		shine.setBounds(0, 0, 206, 194);
		add(shine);
		aquarium = new JLabel(fishTank);
		aquarium.setBounds(0, 0, 206, 194);
		add(aquarium);
		this.setVisible(true);	
		
	}
	
	public void addAllFishes() {
		this.remove(aquarium);
		for(int i=0; i<8; i++) {
			fish[i].setBounds(0, 0, 206, 194);
			add(fish[i]);
		}
		add(aquarium);
	}
	
	public void removeFishes() {
		for(int i=0; i<8; i++) {
			this.remove(fish[i]);
		}
	}
	
	public void removeFish(int id) {
		this.remove(fish[id]);
	}
	
	public void addFish(int id) {
		this.remove(aquarium);
		fish[id].setBounds(0, 0, 206, 194);
		add(fish[id]);
		add(aquarium);
	}
	

}
