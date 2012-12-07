import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class HW8Frame extends JFrame{
	
	//Timer class
	private HW8Timer timer;
	
	//sub class
	private GridBagConstraints constraints;
	private ButtonHanler buttonHanler;
	private FieldHanler fieldHanler;
	private TimerDo timerDo;
	
	private Timer timerRun;
	
	//Properties
	private boolean proAutoTick;
	
	
	private HW8Draw panelUp;
	private JPanel panelDown;
	
	
	//panelDown component
	private JButton[] downFunctionButton; // +/-button
	private JTextField[] downOutput;
	private JButton downAutoTickButton;
	
	public HW8Frame(){
		timer = new HW8Timer();
		buttonHanler = new ButtonHanler();
		proAutoTick = false;
		initial();
		reflushTime();
	}
	
	// Initial the component
	private void initial(){
		constraints = new GridBagConstraints();
		constraints.fill = constraints.BOTH;
		//constraints.ipadx = 20;
		//constraints.ipady = 20;
		//constraints.weightx = 100;
		//constraints.weighty = 20;
		constraints.insets = new Insets(5, 5, 5, 5);
		
		panelUp = new HW8Draw();
		panelDown = new JPanel();
		
		this.setLayout(new GridBagLayout());
		
		this.add(panelUp, setConstraints(0, 0, 1, 1));
		this.add(panelDown, setConstraints(0, 1, 1, 1));
		
		initialUp();
		initialDown();
	}
	
	//Initial panelUp
	private void initialUp(){
		
	}
	
	//Initial panelDown
	private void initialDown(){
		Dimension downSize = new Dimension(40, 20);
		
		// Initial the component
		downFunctionButton = new JButton[6];
		downOutput = new JTextField[3];
		downAutoTickButton = new JButton("Start auto tick");
		
		//Setup
		panelDown.setLayout(new GridBagLayout());
		
		downAutoTickButton.setActionCommand("autoTick");
		downAutoTickButton.addActionListener(buttonHanler);
		
		//Add hour, minute, second : text field and button
		for (int i=0; i<3; i++){
			downFunctionButton[i] = new JButton("+");
			downFunctionButton[i+3] = new JButton("-");
			downOutput[i] = new JTextField(3);
			
			downFunctionButton[i].setSize(downSize);
			downFunctionButton[i+3].setSize(downSize);
			//downOutput[i].setSize(downSize);
			
			//Action handler
			downFunctionButton[i].addActionListener(buttonHanler);
			downFunctionButton[i+3].addActionListener(buttonHanler);
			downOutput[i].addActionListener(fieldHanler);
			
			//Action command
			downFunctionButton[i].setActionCommand(i + "+");
			downFunctionButton[i+3].setActionCommand(i + "-");
			downOutput[i].setActionCommand(String.valueOf(i));
			
			//Add
			panelDown.add(downFunctionButton[i], setConstraints(i, 0, 1, 1));
			panelDown.add(downOutput[i], setConstraints(i, 1, 1, 1));
			panelDown.add(downFunctionButton[i+3], setConstraints(i, 2, 1, 1));
		}
		
		panelDown.add(downAutoTickButton, setConstraints(3, 1, 1, 1));
	}
	
	private void reflushTime(){
		//down output
		downOutput[0].setText(String.format("%02d", timer.getHour()));
		downOutput[1].setText(String.format("%02d", timer.getMinute()));
		downOutput[2].setText(String.format("%02d", timer.getSecond()));
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
	
	
	//Button handler
	private class ButtonHanler implements ActionListener{
		/*
		 * Action command rules:
		 * (0,1,2) + (+ / -)
		 * ==h,m,s     add or minus
		 */
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			
			System.out.println("(ButtonHanler): " + command);
			
			switch(command){
			case "0+":	//add one hour
				timer.incrementHour();
				break;
			case "0-":	//minus one hour
				timer.incrementHour(-1);
				break;
			case "1+":	//add one minute
				timer.incrementMinute();
				break;
			case "1-":	//minus one minute
				timer.incrementMinute(-1);
				break;
			case "2+":	//add one second
				timer.incrementSecond();
				break;
			case "2-":	//minus one second
				timer.incrementSecond(-1);
				break;
				
			case "autoTick":
				if (proAutoTick == true){
					//Stop auto tick
					System.out.println("Stop auto ticking");
					downAutoTickButton.setText("Start auto tick");
					proAutoTick = false;
					timerRun.cancel();
				}
				else{
					//Start auto ticking
					System.out.println("Start auto ticking");
					downAutoTickButton.setText("Stop auto tick");
					proAutoTick = true;
					timerRun = new Timer();
					timerDo = new TimerDo();
					timerRun.schedule(timerDo, 0, 1000);
				}
				break;
			}
			reflushTime();
		}
		
	}
	
	//Field handler
	private class FieldHanler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			// TODO button handler
			
		}
			
	}
	
	//Time auto tick
	private class TimerDo extends TimerTask{
		public void run(){
			timer.tick();
			reflushTime();
		}
	}
}
