public class TestIDDLList {

	public static void main (String[]args) throws Exception, NullPointerException,RuntimeException {
		
		try {
		IDLList<String> l1 = new IDLList<String>();
		IDLList<Integer> array = new IDLList<Integer>();
		IDLList<Integer> l2 = new IDLList<Integer>();

		l1.add(1,"Roland");
		System.out.println(l1.toString());


		}
		catch (IndexOutOfBoundsException ex) {
			System.out.print(ex.getMessage());
			
		} 
		catch (NegativeArraySizeException ex) {
			System.out.print(ex.getMessage());
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
		}

		
	}
}
