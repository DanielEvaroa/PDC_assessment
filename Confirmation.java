package MB;

import javax.swing.*;
import java.awt.*;

public class Confirmation extends JPanel {

    private JLabel economyLabel;
    private JLabel standardLabel;
    private JLabel goldLabel;
    private JLabel premiumLabel;
    private JTextField nameField; // Field for user to enter their name

    public Confirmation(GUI mainApp) {
        setLayout(new GridLayout(6, 2, 10, 10)); // Increased row count for name field

        // Ticket type labels
        add(new JLabel("Economy Tickets:"));
        economyLabel = new JLabel("0");
        add(economyLabel);
        
        add(new JLabel("Standard Tickets:"));
        standardLabel = new JLabel("0");
        add(standardLabel);
        
        add(new JLabel("Gold Tickets:"));
        goldLabel = new JLabel("0");
        add(goldLabel);
        
        add(new JLabel("Premium Tickets:"));
        premiumLabel = new JLabel("0");
        add(premiumLabel);

        // New name field
        add(new JLabel("Name:")); // Label for the name field
        nameField = new JTextField(); // Text field for user to enter their name
        add(nameField);

        //------------------------------------------------------

        // Confirm Button to show the final confirmation
        JButton confirmButton = new JButton("Confirm");
        add(confirmButton);
        
        // Back Button to go back to ticket selection
        JButton backButton = new JButton("Back");
        add(backButton);
        
        // Action for confirm button
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                // Create final confirmation panel
                FinalPage finalPage = new FinalPage(
                        name, 
                        Integer.parseInt(economyLabel.getText()),
                        Integer.parseInt(standardLabel.getText()),
                        Integer.parseInt(goldLabel.getText()),
                        Integer.parseInt(premiumLabel.getText())
                );
                
                Ticket_object eco = new Ticket_object_economy(name,Current_user.Email,Integer.parseInt(standardLabel.getText()),"F");
                Ticket_object stand = new Ticket_object_Standard(name,Current_user.Email,Integer.parseInt(standardLabel.getText()));
                Ticket_object gold = new Ticket_object_Standard(name,Current_user.Email,Integer.parseInt(goldLabel.getText()));
                Ticket_object Prem = new Ticket_object_Standard(name,Current_user.Email,Integer.parseInt(premiumLabel.getText()));
                
                DB_Tables tables = new DB_Tables();
                
                tables.populate_eco_amount(eco.getEmail(), eco.getName(), eco.getAmount(),"F");
                tables.populate_standard_amount(stand.getEmail(), stand.getName(), stand.getAmount());
                tables.populate_gold_amount(gold.getEmail(), gold.getName(), gold.getAmount(), "T");
                tables.populate_premium_amount(Prem.getEmail(),Prem.getName(), Prem.getAmount(), "T", "T");
                
                mainApp.cardPanel.add(finalPage, "FinalConfirmation");
                mainApp.cardLayout.show(mainApp.cardPanel, "FinalConfirmation");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
            }
        });

        // Go back to the selection page
        backButton.addActionListener(e -> {
            mainApp.cardLayout.show(mainApp.cardPanel, "TicketSelection");
        });
    }

    // Update the ticket counts from the main app
    public void updateTicketCounts(int economy, int standard, int gold, int premium) {
        economyLabel.setText(String.valueOf(economy));
        standardLabel.setText(String.valueOf(standard));
        goldLabel.setText(String.valueOf(gold));
        premiumLabel.setText(String.valueOf(premium));
        
        
    }
}