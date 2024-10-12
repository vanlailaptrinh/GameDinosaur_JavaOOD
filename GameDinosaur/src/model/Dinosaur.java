package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Timer;

import controller.CharacterMovement;
import controller.ObstacleController;
import view.GameInterface;
import view.StartInterface;

public abstract class Dinosaur {
	protected ColorBehavior colorBehavior;
	protected RoarBehavior roarBehavior;
	protected String description;

	public abstract void display();

	public abstract int index();

	public abstract String getMoreInfo();

	public String getDescription() {
		return description;
	}
	private ImageIcon[] runImages;
	private Timer runAnimationTimer;
	private int currentRunImageIndex = 0;
	private Timer backgroundTimer;
	private Timer scoreTimer;
	private Timer obstacleTimer;
	private Random random = new Random();
	private List<Timer> gameTimers = new ArrayList<>();
	private GameInterface game;
	private List<JLabel> obstacles = new ArrayList<>();
	private CharacterMovement characterMovement;





	public ImageIcon[] getRunImages() {
		return runImages;
	}

	public void setRunImages(ImageIcon[] runImages) {
		this.runImages = runImages;
	}

	public Timer getRunAnimationTimer() {
		return runAnimationTimer;
	}

	public void setRunAnimationTimer(Timer runAnimationTimer) {
		this.runAnimationTimer = runAnimationTimer;
	}

	private static int JUMP_HEIGHT = 100;
	private static int JUMP_SPEED = 10;
	private static final int TIMER_DELAY = 5;
	private int jumpHeight;
	private boolean isJumping;
	private boolean up;

	private Timer jumpTimer;

	public static int getJumpHeight() {
		return JUMP_HEIGHT;
	}

	public static int getJumpSpeed() {
		return JUMP_SPEED;
	}

	public static int getTimerDelay() {
		return TIMER_DELAY;
	}

	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	public Dinosaur() {
		this.jumpHeight = 0;
		this.isJumping = false;
		this.up = true;
	}

	public void setisJumping(Boolean a) {
		this.isJumping = a;

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void performColor() {
		if (colorBehavior != null) {
			colorBehavior.color();
		}
	}

	public void performRoar() {
		if (roarBehavior != null) {
			roarBehavior.roar();
		}
	}

	public void setJumping(boolean jumping) {
		isJumping = jumping;
	}

	public void startJump(int initialY) {
		this.isJumping = true;
		this.jumpHeight = 0;
		this.up = true;
	}

	public void updateJumpPosition() {
		if (up) {
			if (jumpHeight < JUMP_HEIGHT) {
				jumpHeight += JUMP_SPEED;
			} else {
				up = false;
			}
		} else {
			if (jumpHeight > 0) {
				jumpHeight -= JUMP_SPEED;
			} else {
				isJumping = false;
			}
		}
	}

	public int getDeltaY() {
		return up ? -JUMP_SPEED : JUMP_SPEED;
	}

	public boolean isJumpComplete() {
		return !isJumping;
	}

	public boolean isColliding(int charX, int charY, int charWidth, int charHeight, int obsX, int obsY, int obsWidth,
			int obsHeight) {
		boolean horizontalCollision = charX + charWidth > obsX && charX < obsX + obsWidth;
		boolean verticalCollision = charY + charHeight > obsY && charY < obsY + obsHeight;
		return horizontalCollision && verticalCollision;
	}

	public ImageIcon updateIconLabel(Dinosaur baseDinosaur) {
		ImageIcon img = null;
		if (baseDinosaur != null) {
			String dinoName = baseDinosaur.getClass().getSimpleName();
			switch (dinoName) {
			case "T_Rex":
				img = new ImageIcon("tx.png");
				break;
			case "Triceratops":
				img = new ImageIcon("trii.png");

				break;
			case "Tyrannosaurus":
				img = new ImageIcon("ty.png");

				break;
			default:
				img = (null);
				break;
			}
		}
		return img;
	}

	public ImageIcon[] updateCharacterMovementImages(Dinosaur baseDinosaur, CharacterMovement characterMovement) {
		ImageIcon[] runImages = null;
		if (baseDinosaur != null) {
			String dinoName = baseDinosaur.getClass().getSimpleName();
			switch (dinoName) {
			case "T_Rex":
				runImages = new ImageIcon[] { new ImageIcon("tminn.png"), new ImageIcon("checkl.png"),
						new ImageIcon("tminn.png"), new ImageIcon("checkl.png")

				};
				break;
			case "Triceratops":
				runImages = new ImageIcon[] { new ImageIcon("trimin.png"), new ImageIcon("trim.png"),
						new ImageIcon("trimin.png"), new ImageIcon("trim.png")

				};
				break;
			case "Tyrannosaurus":
				runImages = new ImageIcon[] { new ImageIcon("tymin.png"), new ImageIcon("tyminn.png"),
						new ImageIcon("tymin.png"), new ImageIcon("tyminn.png")

				};
				break;
			default:
				runImages = new ImageIcon[] { new ImageIcon("default_run1.png"), new ImageIcon("default_run2.png") };
				break;
			}
		}
		return runImages;

	}

	public boolean checkSelections(JComboBox<Dinosaur> dinosaurComboBox) {
		boolean isDinosaurSelected = dinosaurComboBox.getSelectedItem() != null;
		return isDinosaurSelected;
	}

	public Dinosaur applyEquipment(Dinosaur baseDinosaur, Dinosaur decoratedDinosaur,
			JComboBox<String> equipmentComboBox) {
		Dinosaur res = null;
		String selectedEquipment = (String) equipmentComboBox.getSelectedItem();
		if (selectedEquipment.equals("Glove")) {
			res = new Glove(baseDinosaur);
		} else if (selectedEquipment.equals("Gun")) {
			res = new Gun(baseDinosaur);
		} else if (selectedEquipment.equals("Sword")) {
			res = new Sword(baseDinosaur);
		}
		return res;
	}

	public ImageIcon updateIconLabel2(Dinosaur decoratedDinosaur, JLabel iconLabel2) {
		ImageIcon img = null;
		if (decoratedDinosaur != null) {
			String equipmentName = decoratedDinosaur.getClass().getSimpleName();
			switch (equipmentName) {
			case "Glove":
				img = new ImageIcon("gl.png");
				JUMP_HEIGHT = 150;
				JUMP_SPEED = 8;
				break;
			case "Gun":
				img = new ImageIcon("g.png");
				JUMP_HEIGHT = 250;
				JUMP_SPEED = 5;

				break;
			case "Sword":
				img = new ImageIcon("s.png");
				JUMP_HEIGHT = 200;
				JUMP_SPEED = 5;

				break;
			default:
				iconLabel2.setIcon(null);
				break;
			}
		}
		return img;
	}

	public Timer getJumpTimer() {
		return jumpTimer;
	}
	
	public void startObstacleGeneration() {
		obstacleTimer = new Timer(3000, e -> generateObstacle());
		obstacleTimer.start();
		gameTimers.add(obstacleTimer);
	}
	private void generateObstacle() {
		String[] obstacleImages = { "co.png", "f.png", "cooo.png", };
		String imagePath = obstacleImages[random.nextInt(obstacleImages.length)];
		JLabel obstacle = new JLabel(new ImageIcon(imagePath));
		obstacle.setBounds(666, 270, 50, 50);
		game.getContentPane().add(obstacle);
		obstacles.add(obstacle);

		Timer moveTimer = new Timer(30,
				new ObstacleController(obstacle, game.getContentPane(), obstacles, characterMovement, this::gameOver, gameTimers));
		moveTimer.start();
		gameTimers.add(moveTimer);
	}
	private void gameOver() {
		for (Timer timer : gameTimers) {
			timer.stop();
		}

		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setBounds(280, 100, 200, 50);
		game.getContentPane().add(gameOverLabel);

		JButton replayButton = new JButton("Replay");
		replayButton.setBounds(280, 200, 100, 50);
		replayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				resetGame();
			}
		});
		game.getContentPane().add(replayButton);

		game.getContentPane().revalidate();
		game.getContentPane().repaint();
	}
//	private void resetGame() {
//		game.dispose();
//		start = new StartInterface();
//		start.setVisible(true);
//
//	}


}
