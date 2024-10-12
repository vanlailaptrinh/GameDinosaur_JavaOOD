package controller;

import javax.swing.*;

import model.Dinosaur;
import view.GameInterface;
import view.StartInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterMovement {
	private final JLabel characterLabel;
	private final JLabel equipmentLabel;
	private boolean isJumping;
	private GameInterface game;
	private Dinosaur dinosaur;
	private ImageIcon[] runImages;
	private int currentRunImageIndex = 0;
	private StartInterface start;
	private Timer runAnimationTimer;
	private Timer jumpTimer;
	private Timer backgroundTimer;
	private Timer scoreTimer;
	private Timer obstacleTimer;
	private Random random = new Random();
	private List<Timer> gameTimers = new ArrayList<>();
	private List<JLabel> obstacles = new ArrayList<>();
	private int score;

	public CharacterMovement(JLabel characterLabel, JLabel equipmentLabel, GameInterface game) {
		this.characterLabel = characterLabel;
		this.equipmentLabel = equipmentLabel;
		this.game = game;
		this.isJumping = false;

	}

	public void setRunImages(ImageIcon[] newRunImages) {
		this.runImages = newRunImages;
		startRunAnimation();

	}

	public void jump() {
		if (!isJumping) {
			isJumping = true;
			int initialY = characterLabel.getY();
			jumpTimer = new Timer(dinosaur.getTimerDelay(), new ActionListener() {
				int jumpHeight = 0;
				boolean up = true;

				public void actionPerformed(ActionEvent e) {
					if (up) {
						if (jumpHeight < dinosaur.getJumpHeight()) {
							moveCharacterAndEquipment(-dinosaur.getJumpSpeed());
							jumpHeight += dinosaur.getJumpSpeed();
						} else {
							up = false;
						}
					} else {
						if (jumpHeight > 0) {
							moveCharacterAndEquipment(dinosaur.getJumpSpeed());
							jumpHeight -= dinosaur.getJumpSpeed();
						} else {
							jumpTimer.stop();
							isJumping = false;
						}
					}
				}
			});
			jumpTimer.start();
		}
	}

	private void moveCharacterAndEquipment(int deltaY) {
		characterLabel.setLocation(characterLabel.getX(), characterLabel.getY() + deltaY);
		equipmentLabel.setLocation(equipmentLabel.getX(), equipmentLabel.getY() + deltaY);
	}

	private void startRunAnimation() {
		if (runImages == null || runImages.length == 0) {
			return;
		}

		runAnimationTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterLabel.setIcon(runImages[currentRunImageIndex]);
				currentRunImageIndex = (currentRunImageIndex + 1) % runImages.length;
			}
		});
		runAnimationTimer.start();
	}

	public boolean isCollidingWith(JLabel obstacle) {
		int charX = characterLabel.getX();
		int charWidth = characterLabel.getWidth();

		int obsX = obstacle.getX();
		int obsWidth = obstacle.getWidth();

		return charX + charWidth > obsX + obsWidth / 2;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void addKey(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.jump();
		}
	}

	public void startBackgroundMovement() {
		backgroundTimer = new Timer(2, e -> game.getContentPane().updateBackground());
		backgroundTimer.start();
		gameTimers.add(backgroundTimer);
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
				new ObstacleController(obstacle, game.getContentPane(), obstacles, this, this::gameOver, gameTimers));
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
				resetGame();
			}
		});
		game.getContentPane().add(replayButton);
		game.getContentPane().revalidate();
		game.getContentPane().repaint();
	}

	private void resetGame() {
		game.dispose();
		start = new StartInterface();
		start.setVisible(true);

	}

	public void startScoreUpdate() {
		scoreTimer = new Timer(1000, e -> updateScore());
		scoreTimer.start();
		gameTimers.add(scoreTimer);
	}

	private void updateScore() {
		score++;
		game.setScoreField("Score: " + score);
	}

}
