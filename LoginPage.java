import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class LoginPage extends JFrame {
    JTextField usernameField = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    public LoginPage() {
        setTitle("Login"); setSize(450, 300); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color primaryColor = Color.decode("#2C3E50"), textColor = Color.WHITE;
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15), boldFont = new Font("Segoe UI", Font.BOLD, 18);
        JPanel mainPanel = new JPanel(new BorderLayout()); mainPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("SPORTS MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(boldFont); titleLabel.setForeground(textColor); titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel(new GridBagLayout()); formPanel.setBackground(primaryColor);
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(8,8,8,8);
        JLabel userLabel = new JLabel("Username:"); userLabel.setForeground(textColor); userLabel.setFont(regularFont);
        JLabel passLabel = new JLabel("Password:"); passLabel.setForeground(textColor); passLabel.setFont(regularFont);
        usernameField.setFont(regularFont); passwordField.setFont(regularFont);
        gbc.gridx=0; gbc.gridy=0; formPanel.add(userLabel, gbc); gbc.gridx=1; formPanel.add(usernameField, gbc);
        gbc.gridx=0; gbc.gridy=1; formPanel.add(passLabel, gbc); gbc.gridx=1; formPanel.add(passwordField, gbc);
        JButton loginButton = new JButton("LOGIN"); loginButton.setFont(regularFont); loginButton.setBackground(Color.decode("#3498DB")); loginButton.setForeground(textColor);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("Praneel") && new String(passwordField.getPassword()).equals("Praneel@123")) {
                    dispose(); new MainPortal().setVisible(true);
                } else JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        });
        gbc.gridx=1; gbc.gridy=2; formPanel.add(loginButton, gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER); add(mainPanel);
    }
}
