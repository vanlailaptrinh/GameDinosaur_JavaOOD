package model;

public class Glove extends EquipmentDecorator{
	public Glove(Dinosaur dinosaur) {
		super(dinosaur);
	}
	@Override
	public String getDescription() {
		return "GLOVE can increase 50 power";	
	}
	@Override
	public int index() {
		return 50 + dinosaur.index();
	}
	@Override
	public String getMoreInfo() {
		// TODO Auto-generated method stub
		return dinosaur.getDescription() 
		+" ." + getDescription();
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}
