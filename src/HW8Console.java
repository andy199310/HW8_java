import java.util.InputMismatchException;
import java.util.Scanner;

public class HW8Console {
	HW8Timer timer;	//timer object
	
	public HW8Console()
	{
		timer = new HW8Timer();
		Scanner input = new Scanner(System.in);
		String command = null;
		
		do
		{
			System.out.println("Time:" + timer.toString());
			command = choose();
			switch(command)
			{
			case "1":
				timer.incrementSecond();
				break;
				
			case "2":
				timer.incrementMinute();
				break;
				
			case "3":
				timer.incrementHour();
				break;
				
			case "4":
				System.out.print("Please enter how many second(s) you want to add:");
				try{
					int second = input.nextInt();
					timer.incrementSecond(second);
				}catch(InputMismatchException e){
					System.out.println("**Please enter a integer");
				}
				break;
				
			case "5":
				System.out.println("Bye~~~");
				break;
				
			default:
				System.out.println("**No such command");
				break;
			}
			
			System.out.println();
			
		}while(!command.equals("5"));	//If command isn't 5 run again
		
		
	}
	
	/**
	 * Print the list of function and return the key
	 * @return key
	 */
	private String choose(){
		Scanner input = new Scanner(System.in);
		
		String[] function = {"Add 1 second", "Add 1 minute", "Add 1 hour", "Add second", "Exit"};
		
		System.out.println("Please choose a function(1~5)");
		// Print out the list
		for (int i=0; i<function.length; i++){
			System.out.printf("%d. %s%n", i+1, function[i]);
		}
		
		System.out.print("Choose:");
		
		String command = input.next();
		return command;
	}
}
