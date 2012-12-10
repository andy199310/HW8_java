import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private boolean proAMPM;
	private int proTickRate;
	
	
	private HW8Draw panelUp;
	private JPanel panelDown;
	
	
	//panelDown component
	private JButton[] downFunctionButton; // +/-button
	private JTextField[] downOutput;
	private JButton downAutoTickButton;
	private JButton downNewTimeButton;
	private JTextField downTickRateField;
	private JButton dowmTickRateButton;
	private JLabel downAMPMLabel;
	private JButton downChooseButton;
	private JButton downToSystemTimeButton;
	
	public HW8Frame(){
		timer = new HW8Timer();
		buttonHanler = new ButtonHanler();
		fieldHanler = new FieldHanler();
		proAutoTick = false;
		proAMPM = false;
		proTickRate = 1;
		initial();
		reflushTime();
	}
	
	// Initial the component
	private void initial(){
		constraints = new GridBagConstraints();
		
		//constraints.ipadx = 20;
		//constraints.ipady = 20;
		//constraints.weightx = 100;
		//constraints.weighty = 20;
		constraints.insets = new Insets(5, 5, 5, 5);
		
		panelUp = new HW8Draw(timer);
		panelDown = new JPanel();
		
		this.setLayout(new GridBagLayout());
		
		this.add(panelUp, setConstraints(0, 0, 1, 1));
		this.add(panelDown, setConstraints(0, 1, 1, 1));
		
		initialUp();
		constraints.fill = constraints.BOTH;
		initialDown();
		panelUp.repaint();
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
		downTickRateField = new JTextField(3);
		downNewTimeButton = new JButton("Set new tick rate(s).");
		downAMPMLabel = new JLabel();
		downChooseButton = new JButton("Add seconds");
		downToSystemTimeButton = new JButton("System time");
		
		//Setup
		panelDown.setLayout(new GridBagLayout());
		
		downChooseButton.setActionCommand("choose");
		downChooseButton.addActionListener(buttonHanler);
		downToSystemTimeButton.setActionCommand("systemTime");
		downToSystemTimeButton.addActionListener(buttonHanler);
		downAutoTickButton.setActionCommand("autoTick");
		downAutoTickButton.addActionListener(buttonHanler);
		downNewTimeButton.setActionCommand("tickRate");
		downNewTimeButton.addActionListener(buttonHanler);
		
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
			panelDown.add(downFunctionButton[i], setConstraints(i+1, 0, 1, 1));
			panelDown.add(downOutput[i], setConstraints(i+1, 1, 1, 1));
			panelDown.add(downFunctionButton[i+3], setConstraints(i+1, 2, 1, 1));
		}
		
		panelDown.add(downChooseButton, setConstraints(4, 0, 1, 1));
		panelDown.add(downToSystemTimeButton, setConstraints(5, 0, 1, 1));
		panelDown.add(downAMPMLabel, setConstraints(0, 1, 1, 1));
		panelDown.add(downAutoTickButton, setConstraints(4, 1, 2, 1));
		panelDown.add(downTickRateField, setConstraints(4, 2, 1, 1));
		panelDown.add(downNewTimeButton, setConstraints(5, 2, 1, 1));
	}
	
	private void reflushTime(){
		//down output
		if (proAMPM == true){ //want AM PM
			if (timer.getHour() >= 12){
				
			}
		}
		panelUp.repaint();
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
				
			case "tickRate":
				try{
					int rate = Integer.parseInt(downTickRateField.getText());
					proTickRate = rate;
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(HW8Frame.this, "Please input number!!");
					downTickRateField.setText("1");
					proTickRate = 1;
				}
				break;
			case "choose":
				try{
					int second = Integer.parseInt(JOptionPane.showInputDialog(HW8Frame.this, "Please input how many second you want to add."));
					timer.incrementSecond(second);
				}catch(NumberFormatException e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(HW8Frame.this, "Please type number.");
				}
				break;
			case "systemTime":
				DateFormat dateFormat = new SimpleDateFormat("HH");
				Calendar cal = Calendar.getInstance();
				timer.setHour(Integer.parseInt(dateFormat.format(cal.getTime())));
				dateFormat = new SimpleDateFormat("mm");
				timer.setMinute(Integer.parseInt(dateFormat.format(cal.getTime())));
				dateFormat = new SimpleDateFormat("ss");
				timer.setSecond(Integer.parseInt(dateFormat.format(cal.getTime())));
				
				break;
			}
			reflushTime();
		}
		
	}
	
	//Field handler
	private class FieldHanler implements ActionListener{
		/*
		 * Rule:
		 * 0, 1, 2: input (h, m, s)
		 */
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			
			System.out.println("(FieldHanler): " + command);
			
			switch(command){
			case "0":	//set hour
				int hour = getUserInput(command);
				if (hour != -100){
					if (hour <0 || hour > 23){
						//Out of range
						JOptionPane.showMessageDialog(HW8Frame.this, "Hour out of range(0~23)");
					}
					else{
						timer.setHour(hour);
					}
				}
				break;
			case "1":	//set minute
				int minute = getUserInput(command);
				if (minute != -100){			
					if (minute < 0 || minute > 59){
						//Out of range
						JOptionPane.showMessageDialog(HW8Frame.this, "Minute out of range(0~59)");
					}
					else{
						timer.setMinute(minute);
					}
				}
				break;
			case "2":	//set second
				int second = getUserInput(command);
				if (second != -100){
					if(second < 0 || second > 59){
						//Out of range
						JOptionPane.showMessageDialog(HW8Frame.this, "Second out of range(0~59)");
					}
					else{
						timer.setSecond(second);
					}
				}
				break;
			}
			reflushTime();
		}
		
		private int getUserInput(String command){
			int time = -100;
			int id = Integer.parseInt(command);
			try{
				 time = Integer.parseInt(downOutput[id].getText());
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(HW8Frame.this, "Please input number!!");
				downOutput[id].setText("");
			}
			return time;
		}
	}
	
	//Time auto tick
	private class TimerDo extends TimerTask{
		public void run(){
			timer.tick(proTickRate);
			reflushTime();
		}
	}
}
