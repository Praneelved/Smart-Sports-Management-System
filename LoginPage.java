import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginPage extends JFrame {

    private final JTextField usernameField = new JTextField(18);
    private final JPasswordField passwordField = new JPasswordField(18);

    public LoginPage() {
        setTitle("Sports Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(8, 8, 8, 8);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        formPanel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        formPanel.add(passwordField, constraints);

        JButton loginButton = new JButton("LOGIN");
        loginButton.addActionListener(event -> login());
        passwordField.addActionListener(event -> login());

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, constraints);

        add(new JLabel("SPORTS MANAGEMENT SYSTEM", JLabel.CENTER), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if ("Praneel".equals(username) && "Praneel@123".equals(password)) {
            dispose();
            SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Credentials",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
