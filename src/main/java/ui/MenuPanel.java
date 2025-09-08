package ui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    // Interface for handling logout events (callback to AppFrame)
    public interface LogoutHandler {
        void onLogout();
    }

    private final LogoutHandler onLogout; // callback for logout
    private double balance = 500.00;       // account balance (demo value)

    public MenuPanel(LogoutHandler onLogout) {
        this.onLogout = onLogout;

        // Use BorderLayout so we can place welcome text at the top
        // and buttons in the center
        setLayout(new BorderLayout());
        setOpaque(false); // transparent background (so BackgroundPanel shows through)

        // Welcome label at the top
        JLabel welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        // adds spacing around the label

        // Panel with buttons (GridLayout: 4 rows, 1 column, with spacing)
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        buttonsPanel.setOpaque(false); // transparent as well

        // Create ATM action buttons
        JButton checkButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton logoutButton = new JButton("Logout");

        // Add buttons into the grid
        buttonsPanel.add(checkButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(logoutButton);

        // Add components to the panel
        add(welcomeLabel, BorderLayout.NORTH);   // welcome text at the top
        add(buttonsPanel, BorderLayout.CENTER);  // buttons in the middle

        //Button logic

        // Show balance in a dialog
        checkButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Balance: $" + String.format("%.2f", balance)
            );
        });

        // Deposit money: prompt user for amount, validate, update balance
        depositButton.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            if (text == null) return; // user pressed cancel
            try {
                double amount = Double.parseDouble(text);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be positive");
                    return;
                }
                balance += amount;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Number");
            }
        });

        // Withdraw money: prompt user for amount, validate, check funds
        withdrawButton.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            if (text == null) return; // user pressed cancel
            try {
                double amount = Double.parseDouble(text);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be positive");
                    return;
                }
                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds");
                    return;
                }
                balance -= amount;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Number");
            }
        });

        // Logout: call back to AppFrame to show login screen again
        logoutButton.addActionListener(e -> onLogout.onLogout());
    }
}

