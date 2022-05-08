
public class TestBinaryNumber {
	public static void main(String[] args) throws Exception, IndexOutOfBoundsException, NegativeArraySizeException {

		try {
			BinaryNumber n1 = new BinaryNumber("010");
			BinaryNumber n2 = new BinaryNumber("1011");
			BinaryNumber n3 = new BinaryNumber("1001");
			BinaryNumber n4 = new BinaryNumber("101");
			BinaryNumber n5 = new BinaryNumber("1");
			BinaryNumber n6 = new BinaryNumber(3);

			
			System.out.println(n6.getLength());
			System.out.println(n6.toString());
			System.out.println(n2.getDigit(3));
			n1.add(n4);
			
			System.out.println(n2);
			System.out.println(n1.toString());
			System.out.println(n2.toDecimal());
			System.out.println(n3.toDecimal());
			n2.add(n3);
			System.out.println(n2);
			
			
			n2.clearOverflow();
			System.out.println(n2);
			System.out.println(n2.toDecimal());
			

		} catch (IndexOutOfBoundsException ex) {
			System.out.print(ex.getMessage());

		} catch (NegativeArraySizeException ex) {
			System.out.print(ex.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());

		}
	}

}
