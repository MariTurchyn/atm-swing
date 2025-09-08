package ui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    // interface for callback when user logs out
    public interface LogoutHandler {
        void onLogout();
    }

    private final LogoutHandler onLogout;

    // account balance (starts with 500 for demo)
    private double balance = 500.00;

    public MenuPanel(LogoutHandler onLogout) {
        this.onLogout = onLogout;

        // grid layout: 5 rows, 1 column
        setLayout(new GridLayout(5, 1, 8, 8));
        setOpaque(false);

        // label that shows current balance
        JLabel balanceLabel = new JLabel(
                "Balance: $" + String.format("%.2f", balance),
                SwingConstants.CENTER
        );

        // buttons for each action
        JButton checkButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton logoutButton = new JButton("Logout");

        // add all components to the panel
        add(balanceLabel);
        add(checkButton);
        add(depositButton);
        add(withdrawButton);
        add(logoutButton);

        // check balance button
        checkButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Balance: $" + String.format("%.2f", balance)
            );
        });

        // deposit button
        depositButton.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            if (text == null) return; // cancel pressed
            try {
                double amount = Double.parseDouble(text);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be positive");
                    return;
                }
                balance += amount;
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Number");
            }
        });

        // withdraw button
        withdrawButton.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            if (text == null) return; // cancel pressed
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
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Number");
            }
        });

        // logout button (calls callback to AppFrame)
        logoutButton.addActionListener(e -> onLogout.onLogout());
    }
}
