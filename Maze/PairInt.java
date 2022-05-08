
public class PairInt {
	//the data fields in this class and int values x and y
	private int x;
	private int y;
	
	//for the constructor there will always be x, y values
	public PairInt(int x,int y) {
		this.x = x;
		this.y = y;
	}
	//get the x value
	public int getX() {
		return x;
	}
	//set the x value
	public void setX(int x) {
		this.x = x;
	}
	//get the y value
	public int getY() {
		return y;
	}
	//set the y value
	public void setY(int y) {
		this.y = y;
	}
	//
	public boolean equals(Object p) {
		if((!(p instanceof PairInt))){
			return false;
		} else {
			PairInt P = (PairInt)p;
			return P.x == this.x && P.y ==this.y;
		}
	}


	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	public PairInt copy() {
		  PairInt thisPair = new PairInt(x,y);
		  return thisPair;
	}
	
}
