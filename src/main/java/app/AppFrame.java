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
        // in constructor, right at the top:
        com.formdev.flatlaf.themes.FlatMacLightLaf.setup(); // or FlatLightLaf/FlatDarculaLaf

        // Global UI tuning:
        UIManager.put( "Component.arc", 16 );           // rounded corners
        UIManager.put( "Button.arc", 20 );
        UIManager.put( "TextComponent.arc", 14 );
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "Component.innerFocusWidth", 0 );
        UIManager.put( "Button.minimumWidth", 120 );
        UIManager.put( "ScrollBar.thumbArc", 999 );     // pill scrollbars
        UIManager.put( "ScrollBar.track", new Color(245,245,247) );          // modern Swing look


        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 600);
        setLocationRelativeTo(null);   // center window

        ui.BackgroundPanel bg = new ui.BackgroundPanel("/atm-bg.jpg");
        setContentPane(bg);

        root.setOpaque(false);
        bg.add(root);

        // Create the two screens and register callbacks
        var login = new LoginPanel(this::onLoginSuccess);
        login.setOpaque(false); // let background show through

        var menu  = new MenuPanel(this::onLogout);
        menu.setOpaque(false);  // let background show through


        root.add(login, "login");

        root.add(menu,  "menu");

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
