package model;

public class Triceratops extends Dinosaur{
    public Triceratops() {
		colorBehavior = new ColorBrown();
		roarBehavior = new SmallRoar();
		description = "I'am triceratops" ;
	}
	@Override
	public void display() {
		System.out.println(description);
	}
	@Override
	public int index() {
		return 50;
	}
	@Override
	public String getMoreInfo() {
		return description + " and " + colorBehavior.color() + " and "+ roarBehavior.roar();
	}
	@Override
	public String toString() {
		return "Triceratops";
	}

}
