package MB;

import javax.swing.*;
import java.awt.*;

public class TicketSelectionPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final GUI mainApp;

    // Labels to display ticket counts
    private JLabel economyCountLabel;
    private JLabel standardCountLabel;
    private JLabel goldCountLabel;
    private JLabel premiumCountLabel;
    

    public TicketSelectionPanel(CardLayout cardLayout, JPanel cardPanel, GUI mainApp) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.mainApp = mainApp;

        setLayout(new GridLayout(0, 1, 10, 10)); // Vertical layout for ticket groups
        setBackground(new Color(240, 240, 240)); // Light gray background

        //ticket buttons, labels and add/remove ticket buttons 
        // Economy Ticket
        JPanel economyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        economyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Simple border
        JButton economyButton = new JButton("Add Economy");
        economyButton.setPreferredSize(new Dimension(150, 30)); // Smaller button size
        economyButton.setBackground(Color.LIGHT_GRAY); // Simple color
        JButton economyDecrementButton = new JButton("-1");
        economyDecrementButton.setPreferredSize(new Dimension(50, 30)); // Smaller button size
        economyDecrementButton.setBackground(Color.LIGHT_GRAY); // Simple color
        
        JLabel economyDesc = new JLabel("Standing only");
        economyDesc.setFont(new Font("Arial", Font.PLAIN, 12)); // Basic font
        economyCountLabel = new JLabel("0"); // Initialize counter label
        economyCountLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Counter font
        economyPanel.add(economyButton);
        economyPanel.add(economyDecrementButton);
        economyPanel.add(economyDesc);
        economyPanel.add(economyCountLabel); // Add counter to the panel
        add(economyPanel);
        
        //-----------------------------------------------------------------------------------

        // Standard Ticket
        JPanel standardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        standardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Simple border
        JButton standardButton = new JButton("Add Standard");
        standardButton.setPreferredSize(new Dimension(150, 30)); // Smaller button size
        standardButton.setBackground(Color.LIGHT_GRAY); // Simple color
        JButton standardDecrementButton = new JButton("-1");
        standardDecrementButton.setPreferredSize(new Dimension(50, 30)); // Smaller button size
        standardDecrementButton.setBackground(Color.LIGHT_GRAY); // Simple color
        
        JLabel standardDesc = new JLabel("Seating");
        standardDesc.setFont(new Font("Arial", Font.PLAIN, 12)); // Basic font
        standardCountLabel = new JLabel("0"); // Initialize counter label
        standardCountLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Counter font
        standardPanel.add(standardButton);
        standardPanel.add(standardDecrementButton);
        standardPanel.add(standardDesc);
        standardPanel.add(standardCountLabel); // Add counter to the panel
        add(standardPanel);
        
//-----------------------------------------------------------------------------------

        // Gold Ticket
        JPanel goldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        goldPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Simple border
        JButton goldButton = new JButton("Add Gold");
        goldButton.setPreferredSize(new Dimension(150, 30)); // Smaller button size
        goldButton.setBackground(Color.LIGHT_GRAY); // Simple color
        JButton goldDecrementButton = new JButton("-1");
        goldDecrementButton.setPreferredSize(new Dimension(50, 30)); // Smaller button size
        goldDecrementButton.setBackground(Color.LIGHT_GRAY); // Simple color
       
        JLabel goldDesc = new JLabel("VIP Area, Drinks");
        goldDesc.setFont(new Font("Arial", Font.PLAIN, 12)); // Basic font
        goldCountLabel = new JLabel("0"); // Initialize counter label
        goldCountLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Counter font
        goldPanel.add(goldButton);
        goldPanel.add(goldDecrementButton);
        goldPanel.add(goldDesc);
        goldPanel.add(goldCountLabel); // Add counter to the panel
        add(goldPanel);
//-----------------------------------------------------------------------------------
        // Premium Ticket
        JPanel premiumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        premiumPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Simple border
        JButton premiumButton = new JButton("Add Premium");
        premiumButton.setPreferredSize(new Dimension(150, 30)); // Smaller button size
        premiumButton.setBackground(Color.LIGHT_GRAY); // Simple color
        JButton premiumDecrementButton = new JButton("-1");
        premiumDecrementButton.setPreferredSize(new Dimension(50, 30)); // Smaller button size
        premiumDecrementButton.setBackground(Color.LIGHT_GRAY); // Simple color
       
        JLabel premiumDesc = new JLabel("VIP Area, Food & Drinks");
        premiumDesc.setFont(new Font("Arial", Font.PLAIN, 12)); // Basic font
        premiumCountLabel = new JLabel("0"); // Initialize counter label
        premiumCountLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Counter font
        premiumPanel.add(premiumButton);
        premiumPanel.add(premiumDecrementButton);
        premiumPanel.add(premiumDesc);
        premiumPanel.add(premiumCountLabel); // Add counter to the panel
        add(premiumPanel);
//-----------------------------------------------------------------------------------
         // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(50, 30));
        nextButton.setBackground(Color.WHITE);
        add(nextButton);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(50, 30));
        logoutButton.setBackground(Color.WHITE);
        add(logoutButton);

        // Ticket selection button actions
        economyButton.addActionListener(e -> {
            mainApp.incrementTicketCount("Economy Ticket");
            updateTicketCount(economyCountLabel, 1); // Update counter
        });
        standardButton.addActionListener(e -> {
            mainApp.incrementTicketCount("Standard Ticket");
            updateTicketCount(standardCountLabel, 1); // Update counter
        });
        goldButton.addActionListener(e -> {
            mainApp.incrementTicketCount("Gold Ticket");
            updateTicketCount(goldCountLabel, 1); // Update counter
        });
        premiumButton.addActionListener(e -> {
            mainApp.incrementTicketCount("Premium Ticket");
            updateTicketCount(premiumCountLabel, 1); // Update counter
        });

        // Ticket decrement button actions
        economyDecrementButton.addActionListener(e -> {
            mainApp.decrementTicketCount("Economy Ticket");
            updateTicketCount(economyCountLabel, -1); // Update counter
        });
        standardDecrementButton.addActionListener(e -> {
            mainApp.decrementTicketCount("Standard Ticket");
            updateTicketCount(standardCountLabel, -1); // Update counter
        });
        goldDecrementButton.addActionListener(e -> {
            mainApp.decrementTicketCount("Gold Ticket");
            updateTicketCount(goldCountLabel, -1); // Update counter
        });
        premiumDecrementButton.addActionListener(e -> {
            mainApp.decrementTicketCount("Premium Ticket");
            updateTicketCount(premiumCountLabel, -1); // Update counter
        });

        
        // Button action to go to confirmation page
        nextButton.addActionListener(e -> {
            mainApp.showConfirmationPanel();
        });
        logoutButton.addActionListener(e -> mainApp.showLoginPanel());
    }
    
//-----------------------------------------------------------------------------------
    
    // Updates the ticket count label
    private void updateTicketCount(JLabel countLabel, int change) {
        int currentCount = Integer.parseInt(countLabel.getText());
        currentCount += change;
        if (currentCount < 0) {
            currentCount = 0; // Prevent negative counts
        }
        countLabel.setText(String.valueOf(currentCount)); // Update the label
    }
}