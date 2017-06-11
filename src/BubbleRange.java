import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BubbleRange extends Bubble{

	public BubbleRange(double[] array){
		super(array);
	}



	public static double[] askRange(){

		Scanner keyboard = new Scanner(System.in);
		double min = 0, max = 0;
		double[] range = new double[2];

		while(true){
			try{
				System.out.println("Please enter the minimum value of the range: ");
				min = keyboard.nextDouble();
				break;
			}catch(InputMismatchException e){
				System.out.println("Please enter a number. ");
				keyboard.nextLine();
			}
		}

		while(true){
			try{
				System.out.println("Please enter the maximum value: ");
				max = keyboard.nextDouble();
				if(max > min) break;
				System.out.println("The max value must be greater than the minimum.");
			}catch(InputMismatchException e){
				System.out.println("Please enter a number.");
				keyboard.nextLine();
			}
		}

		range[0] = min;
		range[1] = max;

		return range;
	}



	public static double[] randRangeArray(){

		int n = askSize();
		double[] mess = new double[n];

		double[] range = askRange();
		double min = range[0];
		double max = range[1];

		for(int i = 0; i < mess.length; i++){
			mess[i] = min + Math.random()*(max - min);
		}

		return mess;
	}


	public static Bubble messArray(){
		return new Bubble(randRangeArray());
	}



	public static Bubble tidyArray(Bubble mess){

		Bubble tidy = new Bubble(Arrays.copyOf(mess.getArray(), mess.getArray().length));	
		tidy.bubbleSort();
		return tidy;		
	}



	public static void rangeCompare()throws IOException{

		Bubble mess = BubbleRange.messArray();
		Bubble tidy = BubbleRange.tidyArray(mess);


		System.out.println("Before: ");
		mess.print();
		System.out.println("\nAfter: ");
		tidy.print();
		printFile(mess, tidy);
	}




}
