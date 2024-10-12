package model;

public class Gun extends EquipmentDecorator {
	public Gun(Dinosaur dinosaur) {
		super(dinosaur);
	}
	@Override
	public String getDescription() {
		return "GUN can increase 200 power";	
	}
	@Override
	public int index() {
		return 200 + dinosaur.index();
	}
	@Override
	public String getMoreInfo() {
		return  dinosaur.getDescription() 
		+" ."+getDescription();
	}
	@Override
	public void display() {
	}
}
