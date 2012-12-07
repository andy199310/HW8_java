import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class HW8Draw extends JPanel{
	
	private int middleX;	//Clock's center X
	private int middleY;	//Clock's center Y
	private int clockSize;
	
	private HW8Timer timer;
	
	public HW8Draw(HW8Timer timer){
		this.timer = timer;
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.BLUE);
		middleX = 150;
		middleY = 150;
		clockSize = 100;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 300, 300);
		drawClock(g);
	}
	
	private void drawClock(Graphics g){
		int[] position;
		int hour = timer.getHour();
		int minute = timer.getMinute();
		int second = timer.getSecond();
		
		if(hour > 12){
			hour -= 12;
		}
		
		//Clock background
		g.setColor(new Color(123,213,5));
		g.fillOval(middleX-clockSize, middleY-clockSize, clockSize*2, clockSize*2);
		
		//Draw number
		for (int i=1; i<=12; i++){
			position = getXY(i*30 - 90);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Serif", Font.BOLD, 12));
			g.drawString(String.valueOf(i), middleX + position[0], middleY + position[1]);
		}
		
		//Draw hour
		position = getXY(hour*30 + minute/2 - 90);
		g.setColor(Color.BLUE);
		g.drawLine(middleX, middleY, middleX + position[0], middleY + position[1]);
		
		position = getXY(minute*6 - 90);
		g.setColor(Color.RED);
		g.drawLine(middleX, middleY, middleX + position[0], middleY + position[1]);
		
		position = getXY(second*6 - 90);
		g.setColor(Color.GREEN);
		g.drawLine(middleX, middleY, middleX + position[0], middleY + position[1]);
	}
	
	private int[] getXY(int degrees){
		int[] position = {0, 0};
		double angle = Math.toRadians(degrees);
		position[0] = (int)( clockSize * Math.cos(angle));
		position[1] = (int)( clockSize * Math.sin(angle));
		return position;
	}
	
	

}
