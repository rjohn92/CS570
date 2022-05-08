/*student: Roland John CS 570-B
 * 
 * BinaryNumber class will use Big-Endian format
 */

public class BinaryNumber {
	/*
	 * every BinaryNumber is represented by a series of ints making it representable
	 * by int numbers
	 */
	private int data[];
	private boolean overflow;

	/*
	 * this constructor will take a natural number as its length then it will create
	 * a number with that many zeroes.
	 * 
	 * Fulfills the stipulation : A constructor BinaryNumber(int length) for
	 * creating a binary number of length length and consisting only of zeros.
	 */
	public BinaryNumber(int length) {
		// if the length is not positive through a NegativeArraySizeException
		if (!(length >= 1)) {
			throw new NegativeArraySizeException("Length must be a positive integer.");
		}
		/*The size of this array will be the positive integer inputted by the user. Also
		 * any int array is automatically populated with '0' values at every index before
		 * it is filled with values by the user. Since we are not entering any values ourself
		 * and we set the length we will get [length] number of zeroes.
		 */
		this.data = new int[length];
	}// end BinaryNumber constructor

	/*
	 * This new BinaryNumber constructor will take a String value named str and turn
	 * it into int values
	 */
	public BinaryNumber(String str) throws Exception {
			int[] convertedString = new int[str.length()];
			for (int i = 0; i < str.length(); i++) {
				convertedString[i] = Character.getNumericValue(str.charAt(i));
				if (!(str instanceof String) || (!(convertedString[i] == 1 || convertedString[i] == 0))) {
					throw new Exception("Binary numbers are represented in 1's and 0's only.");
				}

			}
			this.data = convertedString;
		
	}// end BinaryNumber string input method
	
	//will tell how many digits are in the number
		public int getLength() {
			return this.data.length;
		}// end getLength method

	/*
	 * getDigit method will take an int input and return the digit at that index to
	 * the user
	 */
	public int getDigit(int index) throws IndexOutOfBoundsException {
		/*If the index given is between the numbers length minus one and 0 (inclusive)
		 * then this is a valid index. We will return the digit at this index. 
		 */
		if ((index<=this.data.length-1)&& (index>=0)) {
			return this.data[index];
			//if the input is incorrect we will throw an exception and tell the user
		} else {
			throw new IndexOutOfBoundsException("This index is out of bounds.");
		}
	}

	/*
	 * shifting all digits in a binary number any number of places to the right, as
	 * indicated by a parameter amountToShift. The new digit should be 0. For
	 * example, the result of shifting “1011” 3 places to the right should yield
	 * “0001011”.
	 */
	public void shiftR(int amount) throws Exception {
		int[] shiftedNumber = new int[this.data.length + amount];
		if (!(amount > 0)) {
			throw new Exception("Amount to shift must a be positive integer.");
		}
		/*
		 * start the original data array at 0 index. Then start the shiftedNumber array
		 * at the the first index plus whatever amount you want to shift to the right.
		 * The new array will populate non numbered indexes with '0' values by default.
		 */
		for (int i = 0; i < this.data.length; i++) {
			shiftedNumber[i + amount] += this.data[i];
		}
		// we will make the shiftedNumber array now the new data[] value
		this.data = shiftedNumber;
	}

	public void add(BinaryNumber aBinaryNumber) throws Exception{
		int sum = 0;
		int[] currentDigit = new int[this.data.length];
		int carryDigit = 0;
		

		if (!(aBinaryNumber.data.length == this.data.length)) {
			throw new Exception("Both numbers must be equal length.");
		}
		/*
		 * We have to add numbers in the rows starting from the last number on the right
		 * to the last number on the left.
		 */
		for (int i = this.data.length-1; i >=0; i--) {
			sum = aBinaryNumber.data[i] + this.data[i] + carryDigit;
			/*
			 * binary numbers have either 1 or 0 digits.if the column of digits equals 1
			 * that means we do NOT have to carry a digit thus we will carry a 0.
			 */
			if (sum == 3) {				
				carryDigit = 1;
				currentDigit[i]+= 1;
				/*
				 * If the the sum of two digits in a column is equal to '2' then we have to
				 * carry and 1 to the next column.
				 */
			} else if (sum == 2) {
				carryDigit = 1;
				currentDigit[i]+= 0;
				/*
				 * 1 + 0 will equal a sum of 1 and we won't have to carry a 1. Similarly 0 +0 is
				 * the only other sum probability that will yield the same outcome.
				 */
			} else if (sum == 1) {
				carryDigit = 0;
				currentDigit[i] += 1;
			} else if (sum == 0) {
				carryDigit = 0;
				currentDigit[i] += 0;
			}
		
		}
		/*
		 * overflow will happen when our summands length are less than the length of the
		 * sum. Whenever we have to carry a 1 we will have overflow. Thus overflow will
		 * be set to true.
		 */
		//create a new temp array to handle the overflow
		int [] temp = new int [currentDigit.length+1];
		if (carryDigit >= 1) {
			overflow = true;
		
		for (int i = currentDigit.length-1; i >=0; i--) {
			temp[i+1]=currentDigit[i];
			temp[0]=1;
			this.data=temp;
		}
		} else {
			overflow = false;
			this.data=currentDigit;
		}
		if (overflow==false) {
		
		}
		
			}

	// create a toString method to print out the BinaryNumber
	public String toString() {
		// Start with an empty string which we will build off of
		String bNumber = "";
		// while our counter is less than the length we will add 0 to the bNumber
		for (int i = 0; i < this.data.length; i++) {
			bNumber += this.data[i];
		}if (overflow==true) {
		return "\nOverflow\n";
		}
		return "\nBinary Number is: " + bNumber;
	}// end toString method
	
	// converts a BinaryNumber into a decimal
		public int toDecimal() {
			/*
			 * our starting exponent will always be the length of minus one BinaryNumber
			 * object however we will have to add an if statement if the BinaryNumber only
			 * contains one digit.
			 */
			// declare starting exponent as length minus one

			// build onto our decimal starting from 0
			int decimal = 0;
			int binaryToDecimal = 0;
			int exponent = getLength() - 1;
			/*
			 * this loop will iterate over our data index with the i variable representing
			 * the index in the binary number starting from zero ending at the length minus
			 * one since indexes of arrays start at zero instead of 1.
			 */
			for (int i = 0; i <= this.data.length-1; i++) {
				binaryToDecimal += (int) ((this.data[i]) * (Math.pow(2, exponent)));
				// decrease the exponent to zero
				exponent--;
			}
			return binaryToDecimal;
		}// end toDecimal method

	// this will set the overflow back to it's natural state of false
	public void clearOverflow() {
		overflow = false;
	}

}// end of BinaryNumber class method
