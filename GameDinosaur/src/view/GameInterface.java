package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Dinosaur;
import controller.CharacterMovement;

public class GameInterface extends JFrame {
	private static final long serialVersionUID = 1L;
	private BackgroundPanel contentPane;
	private CharacterMovement characterMovement;
	private JTextField scoreField;
	private JLabel dinoLabel;
	private JLabel equipmentLabel;
	private ImageIcon[] runImages;
	private Dinosaur baseDinosaur;

	public GameInterface(Dinosaur baseDinosaur, Dinosaur decoratedDinosaur) {
		this.baseDinosaur = baseDinosaur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 434);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Game Dinosaur");

		contentPane = new BackgroundPanel("backk.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String dinoImagePath = getDinoImagePath(baseDinosaur);
		String equipmentImagePath = getEquipmentImagePath(decoratedDinosaur);

		dinoLabel = new JLabel(new ImageIcon(dinoImagePath));
		dinoLabel.setBounds(29, 230, 100, 100);
		contentPane.add(dinoLabel);

		equipmentLabel = new JLabel(new ImageIcon(equipmentImagePath));
		equipmentLabel.setBounds(51, 224, 50, 50);
		contentPane.add(equipmentLabel);

		characterMovement = new CharacterMovement(dinoLabel, equipmentLabel,this);

		scoreField = new JTextField("Score: 0");
		scoreField.setBounds(550, 11, 100, 30);
		scoreField.setEditable(false);
		contentPane.add(scoreField);

		characterMovement.startScoreUpdate();
	    characterMovement.startObstacleGeneration();
	    characterMovement.startBackgroundMovement();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				characterMovement.addKey(e);
			}
		});
		setFocusable(true);
		
		setRunImages();
		characterMovement.setRunImages(runImages);
	}

	private String getDinoImagePath(Dinosaur dino) {
		if (dino == null) {
			return null;
		}
		String dinoName = dino.getClass().getSimpleName();
		switch (dinoName) {
		case "T_Rex":
			return "tminn.png";
		case "Triceratops":
			return "trimin.png";
		case "Tyrannosaurus":
			return "tymin.png";
		default:
			return null;
		}
	}

	private void setRunImages() {
		if (baseDinosaur != null) {
			String dinoName = baseDinosaur.getClass().getSimpleName();
			switch (dinoName) {
			case "T_Rex":
				runImages = new ImageIcon[] { new ImageIcon("tminn.png"), new ImageIcon("checkl.png"),
						new ImageIcon("tminn.png"), new ImageIcon("checkl.png") };
				break;
			case "Triceratops":
				runImages = new ImageIcon[] { new ImageIcon("trimin.png"), new ImageIcon("trim.png"),
						new ImageIcon("trimin.png"), new ImageIcon("trim.png") };
				break;
			case "Tyrannosaurus":
				runImages = new ImageIcon[] { new ImageIcon("tymin.png"), new ImageIcon("tyminn.png"),

				};
				break;
			default:
				runImages = new ImageIcon[] { new ImageIcon("default_run1.png"), new ImageIcon("default_run2.png") };
				break;
			}
		}
	}

	private String getEquipmentImagePath(Dinosaur dino) {
		if (dino == null) {
			return null;
		}
		String equipmentName = dino.getClass().getSimpleName();
		switch (equipmentName) {
		case "Glove":
			return "gl.png";
		case "Gun":
			return "g.png";
		case "Sword":
			return "s.png";
		default:
			return null;
		}
	}

	public BackgroundPanel getContentPane() {
		return contentPane;
	}

	public JTextField getScoreField() {
		return scoreField;
	}

	public void setScoreField(String scoreNew) {
		scoreField.setText(scoreNew);
	}
	
	
	
}
