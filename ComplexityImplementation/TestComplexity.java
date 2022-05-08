import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class TestComplexity {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		long startTime = System.nanoTime();
		//Complexity.method1(10);
		//Complexity.method2(10);
		//Complexity.method3(10);
		//Complexity.method4(50);
		//Complexity.method5(11);
		
		
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in seconds: " 
		+ (double)timeElapsed / 1000000000);
								
	}


}
