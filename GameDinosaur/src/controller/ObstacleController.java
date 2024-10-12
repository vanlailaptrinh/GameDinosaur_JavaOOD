package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ObstacleController implements ActionListener {
    private JLabel obstacle;
    private JPanel contentPane;
    private List<JLabel> obstacles;
    private CharacterMovement characterMovement;
    private Runnable gameOverCallback;
    private List<Timer> gameTimers;

    public ObstacleController(JLabel obstacle, JPanel contentPane, List<JLabel> obstacles, CharacterMovement characterMovement, Runnable gameOverCallback, List<Timer> gameTimers) {
        this.obstacle = obstacle;
        this.contentPane = contentPane;
        this.obstacles = obstacles;
        this.characterMovement = characterMovement;
        this.gameOverCallback = gameOverCallback;
        this.gameTimers = gameTimers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int newX = obstacle.getX() - 10;  
        if (newX + obstacle.getWidth() < 0) {
            contentPane.remove(obstacle);
            obstacles.remove(obstacle);
            ((Timer) e.getSource()).stop();
        } else {
            obstacle.setLocation(newX, obstacle.getY());
            if (!characterMovement.isJumping() && characterMovement.isCollidingWith(obstacle)) {
                gameOverCallback.run();
            }
        }
        contentPane.repaint();
    }
}
