import javax.swing.*; //library of gui component. button, pannel etc
import java.awt.*; // color, font, layout library
import java.awt.event.*; // event listener
import java.sql.*; // for sql database

public class LoginPanel extends JFrame {
	
    public LoginPanel() {
        setTitle("Login"); // Login title(top of the window)
        setSize(550, 400); // Determines the weight and height of window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // when we close the window, the program shut down
        setLocationRelativeTo(null); // window is opened middle of the screen
        getContentPane().setBackground(new Color(139, 0, 0)); // background color of window
        setLayout(new GridBagLayout()); // it is useful to set interface like table

        GridBagConstraints gbc = new GridBagConstraints(); // we created an object gbc
        gbc.insets = new Insets(10, 10, 10, 10); // spaces between the components

        JLabel title = new JLabel("Login"); // Login title
        title.setFont(new Font("Arial", Font.BOLD, 20)); // font type and size
        title.setForeground(Color.WHITE); // color
        gbc.gridwidth = 2; // it contains 2 column
        gbc.gridx = 0; // location x
        gbc.gridy = 0; // location y
        add(title, gbc); // transfer the features of gbc to the title

        JLabel usernameLabel = new JLabel("Username:"); // Username label
        usernameLabel.setForeground(Color.WHITE); // color
        gbc.gridy = 1; // location
        // we did't write grid x because last one is still valid
        gbc.gridwidth = 1; 
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(12); // we created a text box
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(12);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        styleButton(loginButton); // bottom of the page we have a function that include buttons features.
        styleButton(registerButton);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> { // action listener determines what will happen when we click the button
            String username = usernameField.getText().trim(); 
            String password = new String(passwordField.getPassword()); // recieves data

            try (Connection conn = DatabaseConnection.getConnection()) { // tries to connect to database
                String query = "SELECT * FROM users WHERE username = ? AND password = ?"; // sql query
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username); //first parameter
                stmt.setString(2, password);// second parameter
                ResultSet rs = stmt.executeQuery(); // it gets the informations

                if (rs.next()) { // if database include user
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username);
                    dispose();
                    new UserPage().setVisible(true); // open the user page
                } else { 
                    JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) { // if there is a problem about connecting to sql.
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPanel().setVisible(true);
        });
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPanel().setVisible(true);
        });
    }
}
