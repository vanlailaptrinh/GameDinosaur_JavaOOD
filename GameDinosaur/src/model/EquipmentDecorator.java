package model;

public abstract class EquipmentDecorator extends Dinosaur {
 protected Dinosaur dinosaur ; 
 
 public EquipmentDecorator(Dinosaur dinosaur) {
	this.dinosaur = dinosaur ;
}
 public abstract String getDescription() ;
 
}


