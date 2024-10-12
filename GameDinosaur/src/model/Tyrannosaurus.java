package model;

public class Tyrannosaurus extends Dinosaur {
   public Tyrannosaurus() {
	colorBehavior = new ColorBlack();
	roarBehavior = new MediumRoar();
	description = "I'am tyrannosaurus" ;
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
		return "Tyrannosaurus";
	}

}
