
public class HW8 {

	public static void main (String[] main){
		//new HW8Console();
		HW8Frame frame = new HW8Frame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		/*HW8Timer timer = new HW8Timer();
		System.out.println(timer.toString());
		timer.incrementSecond(4351000);
		System.out.println(timer.toString());*/
	}
}
