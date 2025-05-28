import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserPage extends JFrame {

public UserPage() {

	 setTitle("User Page");
     setSize(550, 400);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setLocationRelativeTo(null);
     getContentPane().setBackground(new Color(139, 0, 0));
     setLayout(new GridBagLayout());

     GridBagConstraints gbc = new GridBagConstraints();
     gbc.insets = new Insets(10, 10, 10, 10);

     JLabel title = new JLabel("Welcome");
     title.setFont(new Font("Arial", Font.BOLD, 20));
     title.setForeground(Color.WHITE);
     gbc.gridwidth = 2;
     gbc.gridx = 0;
     gbc.gridy = 0;
     add(title, gbc);
	
     JButton backButton = new JButton("Log Out");
     gbc.gridwidth = 1;
     gbc.gridy = 3;
     gbc.gridx = 0;
     add(backButton, gbc);
     
     backButton.addActionListener(e -> {
         dispose();
         new LoginPanel().setVisible(true);
     });
	}
}