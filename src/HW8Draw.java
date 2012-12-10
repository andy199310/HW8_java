import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import tw.com.ncu.green.Image.GreenImageFile;

public class HW8Draw extends JPanel{
	
	private int middleX;	//Clock's center X
	private int middleY;	//Clock's center Y
	private int clockSize;
	
	private HW8Timer timer;
	
	private GreenImageFile image;
	
	public HW8Draw(HW8Timer timer){
		this.timer = timer;
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.BLUE);
		middleX = 150;
		middleY = 150;
		clockSize = 100;
		String[] path = {"number/0.gif", "number/1.gif", "number/2.gif", "number/3.gif", "number/4.gif", "number/5.gif", "number/6.gif", "number/7.gif", "number/8.gif", "number/9.gif"}; 
		image = new GreenImageFile(path);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 299, 299);
		drawClock(g);
		drawDigitalClock(g);
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
		
		//Draw Minute
		position = getXY(minute*6 - 90);
		g.setColor(Color.RED);
		g.drawLine(middleX, middleY, middleX + position[0], middleY + position[1]);
		
		//Draw second
		position = getXY(second*6 - 90);
		g.setColor(Color.GREEN);
		g.drawLine(middleX, middleY, middleX + position[0], middleY + position[1]);
	}
	
	private void drawDigitalClock(Graphics g){
		int hour = timer.getHour();
		int minute = timer.getMinute();
		int second = timer.getSecond();
		
		int x = 180;
		int y = 280;
		
		drawDigitalNumber(g, x, y, hour/10);
		x += 10;
		drawDigitalNumber(g, x, y, hour%10);
		x += 20;
		
		drawDigitalNumber(g, x, y, minute/10);
		x += 10;
		drawDigitalNumber(g, x, y, minute%10);
		x += 20;
		
		drawDigitalNumber(g, x, y, second/10);
		x += 10;
		drawDigitalNumber(g, x, y, second%10);
		x += 20;
	}
	
	private void drawDigitalNumber(Graphics g, int positionX, int positionY, int number){
		g.drawImage(image.getImage(number), positionX, positionY, null);
	}
	
	private int[] getXY(int degrees){
		int[] position = {0, 0};
		double angle = Math.toRadians(degrees);
		position[0] = (int)( clockSize * Math.cos(angle));
		position[1] = (int)( clockSize * Math.sin(angle));
		return position;
	}
	
	

}
