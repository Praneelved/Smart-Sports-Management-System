import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {

    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    public LoginPage() {
        setTitle("Login");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color buttonColor = Color.decode("#3498DB");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("SPORTS MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(primaryColor);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(textColor);
        userLabel.setFont(font);
        
        userField = new JTextField(15);
        userField.setFont(font);

        c.gridx = 0; c.gridy = 0;
        formPanel.add(userLabel, c);
        c.gridx = 1; c.gridy = 0;
        formPanel.add(userField, c);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(textColor);
        passLabel.setFont(font);
        
        passField = new JPasswordField(15);
        passField.setFont(font);

        c.gridx = 0; c.gridy = 1;
        formPanel.add(passLabel, c);
        c.gridx = 1; c.gridy = 1;
        formPanel.add(passField, c);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(font);
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(textColor);
        loginButton.setFocusPainted(false);

        ActionListener loginAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());
                
                if (username.equals("Praneel") && password.equals("Praneel@123")) {
                    dispose();
                    MainPortal portal = new MainPortal();
                    portal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        };

        loginButton.addActionListener(loginAction);
        passField.addActionListener(loginAction);

        c.gridx = 1; c.gridy = 2;
        formPanel.add(loginButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
}
