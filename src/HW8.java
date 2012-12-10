import java.util.Scanner;

public class HW8 {

	public static void main (String[] main){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please select which type you want");
		System.out.println("(A)Console (B)GUI");
		
		String command = input.next();
		
		switch(command){
		case "a":
		case "A":
			new HW8Console();
			break;
		case "b":
		case "B":
			HW8Frame frame = new HW8Frame();
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(500, 500);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			break;
		}
		
	}
}
