//Roland John CS 570-B
public class Complexity {

	/*
	 * method1 is a method that has time complexity O(n^2) or quadratic time
	 * complexity.
	 */

	public static void method1(int n) {
		int counter = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.println("Column A: " + i + " || " + "Column B: " + j);
				counter++;
				System.out.println("The number of operations performed: " + counter + "\n");
			}
		}

	}

	/*
	 * method2 is a method that has time complexity O(n^3) or cubic time complexity
	 * 
	 * This method is meant to countdown from n. If n = 0 print "BOOM!"
	 */
	public static void method2(int n) {
		int counter = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					System.out.println("Column A: " + i + " || " + "Column B: " + j + " || " + "Column C: " + k);
					counter++;
					System.out.println("The number of operations performed: " + counter + "\n");
				} // end inner loop
			} // end middle loop
		} // end outer loop
	}// end method 2

	// method3 has log n complexity
	public static void method3(int n) {
		int counter = 0;
		for (int i = n; i > 0; i = i / 2) {
			System.out.println("The number is: " + i);
			counter++;
			System.out.println("The number of operations performed: " + counter + "\n");
		} // end of for loop
	}// end of method3

	// method4 has n log n time complexity
	public static void method4(int n) {
		int counter = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < n; j = j * 2) {
				System.out.println("The number is: " + i);
				counter++;
				System.out.println("The number of operations performed: " + counter + "\n");
			}
		}
	}

	// method5 has log*log*n time complexity (half the halving from log n
	// complexity)
	public static void method5(int n) {
		int counter = 0;
		for (int i = n; i > 0; i = (i / 2) / 2) {
			System.out.println("The number is: " + i);
			counter++;
			System.out.println("The number of operations performed: " + counter + "\n");
		}

	}

}// end of complexity class
