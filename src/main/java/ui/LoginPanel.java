package ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    // interface for callback after successful login
    public interface LoginSuccessHandler {
        void onSuccess(String cardNumber);
    }

    private final LoginSuccessHandler onSuccess;

    public LoginPanel(LoginSuccessHandler onSuccess) {
        this.onSuccess = onSuccess;

        // use BorderLayout for main layout
        setLayout(new BorderLayout());

        // create form with grid: 3 rows, 2 columns
        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));

        // labels and fields for card and pin
        JLabel cardLabel = new JLabel("Card Number");
        cardLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cardLabel.setForeground(Color.DARK_GRAY);

        JTextField cardField = new JTextField();
        cardField.setBackground(new Color(40, 40, 40));
        cardField.setForeground(Color.WHITE);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pinLabel.setForeground(Color.DARK_GRAY);

        JPasswordField pinField = new JPasswordField();
        pinField.setBackground(new Color(40, 40, 40));
        pinField.setForeground(Color.WHITE);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 102, 204)); // blue button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));

        // add components into the form
        form.add(cardLabel);
        form.add(cardField);
        form.add(pinLabel);
        form.add(pinField);
        form.add(new JLabel()); // empty cell
        form.add(loginButton);

        // add padding around form
        JPanel padding = new JPanel(new BorderLayout());
        padding.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        padding.add(form, BorderLayout.CENTER);

        // add everything to panel
        add(padding, BorderLayout.CENTER);

        // action when login button is clicked
        loginButton.addActionListener(e -> {
            String card = cardField.getText().trim();
            String pin = new String(pinField.getPassword());

            // check if card and pin are correct
            if (card.equals("1111") && pin.equals("1234")) {
                onSuccess.onSuccess(card); // call callback
            } else {
                // show error message if login fails
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid card or PIN",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

    }
}
