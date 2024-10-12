package model;

public class T_Rex extends Dinosaur {
	public T_Rex() {
		colorBehavior = new ColorRed();
		roarBehavior = new BigRoar();	
		description = "I'am T_rex" ;
	}
	@Override
	public void display() {
		System.out.println(description);	
	}
	@Override
	public String getMoreInfo() {
		return description + " and " + colorBehavior.color() + " and " + roarBehavior.roar();
	}
	@Override
	public int index() {
		return 50;
	}
	@Override
	public String toString() {
		return "T_Rex";
	}

	

	

}
