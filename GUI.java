package MB;

import static MB.DatabaseConnector.createTableAndInsertData;
import static MB.DatabaseConnector.getConnection;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GUI extends JFrame {

    public CardLayout cardLayout;
    public JPanel cardPanel;
    private HashMap<String, String> users;

    public int economyCount = 0;
    public int standardCount = 0;
    public int goldCount = 0;
    public int premiumCount = 0;

    private String loggedInUser;
    
    

    public GUI() {
        setTitle("Ticket Booking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        LoginPanel loginPanel = new LoginPanel(cardLayout, cardPanel, this);
        TicketSelectionPanel ticketSelectionPanel = new TicketSelectionPanel(cardLayout, cardPanel, this);
        Confirmation confirmationPanel = new Confirmation(this);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(ticketSelectionPanel, "TicketSelection");
        cardPanel.add(confirmationPanel, "Confirmation");

        add(cardPanel);
        cardLayout.show(cardPanel, "Login");
    }

    //--------------------------------------------------------------------------------
    // Registers a new user

        public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        
        users.put(username, password);
        saveUsers(); // Save users to file called users.txt
        return true;
        }
        public void showLoginPanel() {
            economyCount = 0;
            standardCount = 0;
            goldCount = 0;
            premiumCount = 0;
            loggedInUser = null;

    // Show the login panel
    cardLayout.show(cardPanel, "Login");
}

    //--------------------------------------------------------------------------------
    // Handles the login of the user
    // If the correct password and username is entered it logs in the user
    // If returns false login fails
   
    public boolean login(String username, String password) {
        if(isUserInDatabase(username,password)==true)
        {
            return true;
        }
        else
        {
            return false;
        }      
    }

    //--------------------------------------------------------------------------------
    // This saves a user's login info after registering
    // This means that a user's login is remembered between visits
    // Saves the list of users to a text file called users.txt
    private void saveUsers() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (String username : users.keySet()) {
                writer.write(username + ":" + users.get(username));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------------

    // Loads users from the users.txt file to the users map
    private void loadUsers() {
        try ( BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]); // username:password
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
   public boolean RegUserInDB(String Email, String password) {
    String insertQuery = "INSERT INTO USERS (EMAIL, PASSWORD) VALUES (?, ?)";

    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

        preparedStatement.setString(1, Email);
        preparedStatement.setString(2, password);
        int rowsAffected = preparedStatement.executeUpdate();

        // If rowsAffected is greater than 0, insertion was successful
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false if an exception occurs
    }
}
   
    
    //SQL database checker for user login details ********************************************************
    public boolean isUserInDatabase(String Email, String password) {
        
        String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    
    // Increments tickets for the selected ticket type
    // Uses a switch statement to check which ticket is selected
    public void incrementTicketCount(String ticketType) {
        switch (ticketType) {
            case "Economy Ticket":
                economyCount++;
                break;
            case "Standard Ticket":
                standardCount++;
                break;
            case "Gold Ticket":
                goldCount++;
                break;
            case "Premium Ticket":
                premiumCount++;
                break;
        }
    }

    //--------------------------------------------------------------------------------
    // Decrements tickets for the selected ticket type
    // Uses a switch statement to check which ticket is selected
    public void decrementTicketCount(String ticketType) {
        switch (ticketType) {
            case "Economy Ticket":
                if (economyCount > 0) {
                    economyCount--;
                }
                break;
            case "Standard Ticket":
                if (standardCount > 0) {
                    standardCount--;
                }
                break;
            case "Gold Ticket":
                if (goldCount > 0) {
                    goldCount--;
                }
                break;
            case "Premium Ticket":
                if (premiumCount > 0) {
                    premiumCount--;
                }
                break;
        }
    }

    // This updates the confirmation page with the ticket count the user selects and shows it to the user
    public void showConfirmationPanel() {
        Confirmation confirmation = (Confirmation) cardPanel.getComponent(2);
        confirmation.updateTicketCounts(economyCount, standardCount, goldCount, premiumCount);
        //JOptionPane.showMessageDialog(this, "Thank you, " + loggedInUser + ", for your purchase!");
        cardLayout.show(cardPanel, "Confirmation");
        
    }

    public static void main(String[] args) {
        
        
        //create the DB 
        createTableAndInsertData();     
        //Creates the Tables 
        DB_Tables tables = new DB_Tables();
        
        tables.create_user_table();
        tables.create_Eco_ticket();
        tables.create_Standard_ticket();
        tables.create_GOLD_ticket();
        tables.create_Premium_ticket();
        
        SwingUtilities.invokeLater(() -> {
            GUI app = new GUI();
            app.setVisible(true);
        });
    }
}