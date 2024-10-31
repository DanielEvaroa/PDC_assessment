package MB;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final GUI mainApp;

    // Login stuff
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JLabel statusLabel;

    // Registration stuff
    private final JTextField regUsernameField;
    private final JPasswordField regPasswordField;
    private final JLabel regStatusLabel;

    public LoginPanel(CardLayout cardLayout, JPanel cardPanel, GUI mainApp) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.mainApp = mainApp;

        setLayout(new CardLayout());

        // Panel for login form
        JPanel loginForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel loginTitleLabel = new JLabel("Login:");
        loginTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginForm.add(loginTitleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        loginForm.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        loginForm.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        loginForm.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        loginForm.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);
        loginForm.add(statusLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginForm.add(loginButton, gbc);

        //Link to registration page
        gbc.gridy++;
        JButton goToRegisterButton = new JButton("Create an account");
        loginForm.add(goToRegisterButton, gbc);

        //Panel for registration page
        JPanel registrationForm = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel regTitleLabel = new JLabel("Register:");
        regTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registrationForm.add(regTitleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        registrationForm.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        regUsernameField = new JTextField(15);
        registrationForm.add(regUsernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        registrationForm.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        regPasswordField = new JPasswordField(15);
        registrationForm.add(regPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        regStatusLabel = new JLabel("");
        regStatusLabel.setForeground(Color.RED);
        registrationForm.add(regStatusLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("Register");
        registrationForm.add(registerButton, gbc);

        //Link to login page
        gbc.gridy++;
        JButton goToLoginButton = new JButton("Already have an account? Login");
        registrationForm.add(goToLoginButton, gbc);

        //Adds login and registration forms to this panel
        add(loginForm, "LoginForm");
        add(registrationForm, "RegistrationForm");

        //Go to the registration form when 'Create an account' button is clicked
        goToRegisterButton.addActionListener(e -> {
            ((CardLayout) getLayout()).show(this, "RegistrationForm");
        });

        // Switch to the login form when 'Already have an account? Login' is clicked
        goToLoginButton.addActionListener(e -> {
            ((CardLayout) getLayout()).show(this, "LoginForm");
        });

        //--------------------------------------------------------------------------------
        //Action to make the login button function
        loginButton.addActionListener(e -> {
            String Email = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (mainApp.login(Email, password)) {
                Current_user.Email = Email; // linked to the current user class 
                // Successful login, move to ticket selection panel
                statusLabel.setText("");
                cardLayout.show(cardPanel, "TicketSelection");
            } else {
                // Invalid login
                statusLabel.setText("Invalid login. Please try again.");
            }
        });

        //--------------------------------------------------------------------------------
        // Action listener for the registration button
        registerButton.addActionListener(e -> {
            String username = regUsernameField.getText();
            String password = new String(regPasswordField.getPassword());

            if (mainApp.RegUserInDB(username, password)) {
                // Successful registration, switch to ticket selection panel
                Current_user.Email = username;
                regStatusLabel.setText("");
                cardLayout.show(cardPanel, "TicketSelection");
            } else {
                // Username already exists
                regStatusLabel.setText("Username already taken. Please choose another.");
            }
        });
    }
}