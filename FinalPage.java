package MB;

import javax.swing.*;
import java.awt.*;

public class FinalPage extends JPanel {
    
    
    public FinalPage(String name, int economy, int standard, int gold, int premium) {
        setLayout(new GridLayout(6, 1, 10, 10)); // Layout for displaying information
        
        // Thank you message with user's name
        JLabel thankYouLabel = new JLabel("Thank you, " + name + ", for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font for emphasis
        add(thankYouLabel);
        
        // Display ticket counts
        add(new JLabel("Economy Tickets: " + economy));
        add(new JLabel("Standard Tickets: " + standard));
        add(new JLabel("Gold Tickets: " + gold));
        add(new JLabel("Premium Tickets: " + premium));
        
        

        // Back button to return to the ticket selection
        JButton backButton = new JButton("Back to Selection");
        add(backButton);
        
        backButton.addActionListener(e -> {
            // Logic to return to the Ticket Selection panel
            GUI mainApp = (GUI) SwingUtilities.getWindowAncestor(this);
            mainApp.cardLayout.show(mainApp.cardPanel, "TicketSelection");
        });
    }
}