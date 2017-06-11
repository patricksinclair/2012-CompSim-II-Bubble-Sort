import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Bubble {

	private double[] array;

	public Bubble(double[] array){
		this.array = array;
	}

	public double[] getArray(){
		return array;
	}
	public void setMess(double[] array){
		this.array = array;
	}




	public void bubbleSort(){

		for(int i = getArray().length-1; i > 0; i--){
			for(int j = 0; j < i; j++){

				int k = j+1;
				if(getArray()[j] > getArray()[k]){

					double temp = getArray()[j];
					getArray()[j] = getArray()[k];
					getArray()[k] = temp;
				}
			}
		}
	}



	public void print(){

		for(int i = 0; i < getArray().length; i++){	
			String str = String.format("%.4f", getArray()[i]);
			System.out.println(str);
		}	
	}



	public static int askSize(){

		Scanner keyboard = new Scanner(System.in);
		int n = 0;

		while(true){
			try{
				System.out.println("Please enter the size of the array: ");
				n = keyboard.nextInt();
				if(n > 1){
					break;
				}
				System.out.println("The array must be greater than one.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}	
		return n;
	}



	public static double[] randArray(){

		int n = Bubble.askSize();

		double[] mess = new double[n];

		for(int i = 0; i < mess.length; i++){

			mess[i] = Math.random();
		}		
		return mess;
	}


	public static Bubble messArray(int n){

		double[] mess = new double[n];
		for(int i = 0; i < mess.length; i++){
			mess[i] = Math.random();
		}
		return new Bubble(mess);
	}


	public static Bubble messArray(){

		return new Bubble(randArray());
	}



	public static Bubble tidyArray(Bubble mess){

		Bubble tidy = new Bubble(Arrays.copyOf(mess.getArray(), mess.getArray().length));	
		tidy.bubbleSort();
		return tidy;		
	}


	public static void compare()throws IOException{

		Bubble mess = Bubble.messArray();
		Bubble tidy = Bubble.tidyArray(mess);

		System.out.println("Before: ");
		mess.print();
		System.out.println("After: ");
		tidy.print();

		BubbleFrame bf = new BubbleFrame(mess, tidy);
		printFile(mess, tidy);
	}



	public static void printFile(Bubble mess, Bubble tidy) throws IOException{

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of the destination file: ");
		String filename = keyboard.next()+".txt";

		BufferedWriter out = new BufferedWriter(new FileWriter(new File(filename)));

		out.write("Unsorted: \n");	
		for(int i = 0; i < mess.getArray().length; i++){
			String str = String.format("%.4f\n", mess.getArray()[i]);
			out.write(str);
		}

		out.write("\nSorted: \n");
		for(int i = 0; i < tidy.getArray().length; i++){
			String str = String.format("%.4f\n", tidy.getArray()[i]);
			out.write(str);
		}

		out.close();
		System.out.println("Results have been saved to: " +filename);
	}
}
