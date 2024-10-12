package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Dinosaur;
import view.GameInterface;
import view.StartInterface;

public class CharacterController {
	private Dinosaur baseDinosaur;
	private Dinosaur decoratedDinosaur;
	private StartInterface start;
	private CharacterMovement characterMovement;
	private GameInterface game ;

	public CharacterController(StartInterface start) {
		super();
		this.start = start;
		this.characterMovement = new CharacterMovement(start.getCharacterLabel(), start.getEquipmentLabel(),game);
		initialize();
	}

	private void initialize() {
		start.getBtnNewButton().setEnabled(false);

		start.getDinosaurComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				baseDinosaur = (Dinosaur) start.getDinosaurComboBox().getSelectedItem();
				decoratedDinosaur = baseDinosaur;
				start.updatetextField(updateTextField(decoratedDinosaur));
				start.updateiconLabel(baseDinosaur.updateIconLabel(baseDinosaur));
				characterMovement
						.setRunImages(baseDinosaur.updateCharacterMovementImages(baseDinosaur, characterMovement));
				;
				start.updatebutton(baseDinosaur.checkSelections(start.getDinosaurComboBox()));

			}
		});

		start.getEquipmentComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (baseDinosaur != null) {
					Dinosaur d = baseDinosaur.applyEquipment(baseDinosaur, decoratedDinosaur,
							start.getEquipmentComboBox());
					setDecoratedDinosaur(d);
					start.updatetextField(updateTextField(decoratedDinosaur));
					start.updateiconLabel2(
							decoratedDinosaur.updateIconLabel2(decoratedDinosaur, start.getIconLabel2()));
					start.updatebutton(baseDinosaur.checkSelections(start.getDinosaurComboBox()));

				}
			}
		});
		start.getBtnNewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start.dispose();
				GameInterface gameFrame = new GameInterface(baseDinosaur, decoratedDinosaur);
				gameFrame.setVisible(true);
			}
		});
	}

	public void setDecoratedDinosaur(Dinosaur decoratedDinosaur) {
		this.decoratedDinosaur = decoratedDinosaur;
	}

	public Dinosaur getBaseDinosaur() {
		return baseDinosaur;
	}

	public Dinosaur getDecoratedDinosaur() {
		return decoratedDinosaur;
	}

	public StartInterface getStart() {
		return start;
	}

	public CharacterMovement getCharacterMovement() {
		return characterMovement;
	}

	public String updateTextField(Dinosaur deco) {
		String res = "";
		if (deco != null) {
			res = deco.getMoreInfo();
		}
		return res;
	}

}
