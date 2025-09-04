package app;

import com.formdev.flatlaf.FlatLightLaf;
import ui.LoginPanel;
import ui.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final JPanel root = new JPanel(layout);

    public AppFrame() {
        FlatLightLaf.setup();          // modern Swing look

        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 600);
        setLocationRelativeTo(null);   // center window

        // Create the two screens and register callbacks
        var login = new LoginPanel(this::onLoginSuccess);
        var menu  = new MenuPanel(this::onLogout);

        root.add(login, "login");
        root.add(menu,  "menu");
        setContentPane(root);

        showLogin();
    }

    private void showLogin() { layout.show(root, "login"); }
    private void showMenu()  { layout.show(root, "menu"); }

    // called by LoginPanel when login OK
    private void onLoginSuccess(String cardNumber) {
        // (we'll use cardNumber later when DB exists)
        showMenu();
    }

    // called by MenuPanel on logout
    private void onLogout() { showLogin(); }
}
