package view;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.Dinosaur;
import model.T_Rex;
import model.Triceratops;
import model.Tyrannosaurus;
import controller.CharacterController;
import controller.CharacterMovement;
import java.awt.Color;
import java.awt.Font;

public class StartInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JComboBox<Dinosaur> dinosaurComboBox;
    private JComboBox<String> equipmentComboBox;
    private JLabel iconLabel, iconLabel2,lblNewLabel, lblNewLabel_1;
    private JButton btnNewButton ;
    private GameInterface game ;    
    
    private JLabel characterLabel,equipmentLabel ;
    public JLabel getCharacterLabel() {
		return characterLabel;
	}

	public void setCharacterLabel(JLabel characterLabel) {
		this.characterLabel = characterLabel;
	}

	public JLabel getEquipmentLabel() {
		return equipmentLabel;
	}

	public void setEquipmentLabel(JLabel equipmentLabel) {
		this.equipmentLabel = equipmentLabel;
	}
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StartInterface frame = new StartInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JComboBox<Dinosaur> getDinosaurComboBox() {
		return dinosaurComboBox;
	}

	public JComboBox<String> getEquipmentComboBox() {
		return equipmentComboBox;
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}

	public JLabel getIconLabel2() {
		return iconLabel2;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}


    public StartInterface() {
        setTitle("Game Dinosaur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 425);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new BackgroundPanel("back.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        dinosaurComboBox = new JComboBox<>();
        dinosaurComboBox.setBounds(26, 126, 150, 22);
        contentPane.add(dinosaurComboBox);

         lblNewLabel = new JLabel("Nhân Vật");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 23));
        lblNewLabel.setBackground(new Color(0, 0, 0));
        lblNewLabel.setBounds(55, 93, 121, 22);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Trang Bị");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Cambria", Font.PLAIN, 23));
        lblNewLabel_1.setBackground(new Color(0, 255, 255));
        lblNewLabel_1.setBounds(514, 93, 121, 23);
        contentPane.add(lblNewLabel_1);

        equipmentComboBox = new JComboBox<>();
        equipmentComboBox.setBounds(486, 126, 138, 22);
        contentPane.add(equipmentComboBox);

         btnNewButton = new JButton("Bắt Đầu");
        btnNewButton.setFont(new Font("Cambria", Font.PLAIN, 23));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(Color.PINK);
        btnNewButton.setBounds(277, 240, 121, 41);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(dinosaurComboBox);

        textField = new JTextField();
        textField.setForeground(new Color(0, 0, 0));
        textField.setBackground(new Color(255, 228, 181));
        textField.setBounds(108, 320, 459, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        iconLabel = new JLabel();
        iconLabel.setBounds(234, 29, 200, 200);
        contentPane.add(iconLabel);
        
        iconLabel2 = new JLabel();
        iconLabel2.setBounds(384, 160, 50, 50);
        contentPane.add(iconLabel2);
        
        populateDinosaurComboBox();
        populateEquipmentComboBox();
        
         characterLabel = new JLabel();
         equipmentLabel = new JLabel();
        CharacterMovement characterMovement = new CharacterMovement(characterLabel, equipmentLabel,game);
       new CharacterController(this) ;
    }

    private void populateDinosaurComboBox() {
        dinosaurComboBox.addItem(new T_Rex());
        dinosaurComboBox.addItem(new Triceratops());
        dinosaurComboBox.addItem(new Tyrannosaurus());
    }

    private void populateEquipmentComboBox() {
        equipmentComboBox.addItem("Glove");
        equipmentComboBox.addItem("Gun");
        equipmentComboBox.addItem("Sword");
    }
    public void updatetextField(String newString) {
    	textField.setText(newString);
    }
    public void updateiconLabel (ImageIcon newimageIcon) {
    	iconLabel.setIcon((Icon) newimageIcon);
    }
    public void updatebutton (Boolean b) {
    	btnNewButton.setEnabled(b);
    }
    public void updateiconLabel2 (ImageIcon imageIcon) {
    	iconLabel2.setIcon((Icon) imageIcon);
    }
}
