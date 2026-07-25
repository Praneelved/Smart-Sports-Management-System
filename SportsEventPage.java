import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SportsEventPage extends JFrame {
    
    JTextField idField;
    JTextField nameField;
    JTextField dateField;
    JTextField venueField;
    
    JButton registerButton;
    JButton backButton;

    public SportsEventPage() {
        setTitle("Tournament Registration");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color buttonColor = Color.decode("#27AE60");
        Color backBtnColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("TOURNAMENT REGISTRATION", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBackground(primaryColor);
        formPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        idField = new JTextField();
        idField.setFont(font);
        nameField = new JTextField();
        nameField.setFont(font);
        dateField = new JTextField();
        dateField.setFont(font);
        venueField = new JTextField();
        venueField.setFont(font);

        String[] labels = { "Tournament ID:", "Tournament Name:", "Date:", "Venue:" };
        JComponent[] fields = { idField, nameField, dateField, venueField };

        for (int i = 0; i < 4; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(textColor);
            lbl.setFont(font);
            formPanel.add(lbl);
            formPanel.add(fields[i]);
        }

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);

        registerButton = new JButton("Register");
        registerButton.setFont(font);
        registerButton.setBackground(buttonColor);
        registerButton.setForeground(textColor);
        registerButton.setFocusPainted(false);
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Tournament Name cannot be empty");
                    return;
                }
                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)");
                    ps.setInt(1, Integer.parseInt(idField.getText()));
                    ps.setString(2, nameField.getText());
                    ps.setString(3, dateField.getText());
                    ps.setString(4, venueField.getText());
                    ps.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Tournament registered successfully.");
                    
                    ps.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Data");
                }
            }
        });

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(backBtnColor);
        backButton.setForeground(textColor);
        backButton.setFocusPainted(false);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPortal page = new MainPortal();
                page.setVisible(true);
            }
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
