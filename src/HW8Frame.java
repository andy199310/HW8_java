import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class HW8Frame extends JFrame{
	
	private HW8Timer timer;
	
	private GridBagConstraints constraints;
	
	private HW8Draw panelUp;
	private JPanel panelDown;
	
	public HW8Frame(){
		timer = new HW8Timer();
		initial();
	}
	
	// Initial the component
	private void initial(){
		constraints = new GridBagConstraints();
		constraints.fill = constraints.BOTH;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		
		panelUp = new HW8Draw();
		panelDown = new JPanel();
		initialUp();
		initialDown();
	}
	
	//Initial panelUp
	private void initialUp(){
		
	}
	
	//Initial panelDown
	private void initialDown(){
		
	}
	
	
	//GridBagLayoutConstraints
	private GridBagConstraints setConstraints(int gridX, int gridY, int gridWidth, int gridHeight)
	{
		constraints.gridx = gridX;
		constraints.gridy = gridY;
		constraints.gridwidth = gridWidth;
		constraints.gridheight = gridHeight;
		return constraints;
	}
}
