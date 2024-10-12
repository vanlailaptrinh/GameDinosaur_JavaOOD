package model;

public class Sword extends EquipmentDecorator {
	public Sword(Dinosaur dinosaur) {
		super(dinosaur);		
	}
	@Override
	public String getDescription() {  
	return "SWORD can increase 100 power";
	}
	@Override
	public String getMoreInfo() {
		// TODO Auto-generated method stub
		return dinosaur.getDescription() 
		+ " ." + getDescription();
	}
	@Override
	public int index() {
		return 100 + dinosaur.index();
	}
	@Override
	public void display() {
		System.out.println("My name is Sword");
	}
	

}
