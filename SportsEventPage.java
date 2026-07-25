import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class SportsEventPage extends JFrame {
    JTextField idField = new JTextField(), nameField = new JTextField(), dateField = new JTextField(), venueField = new JTextField();
    public SportsEventPage() {
        setTitle("Tournament Registration"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color primaryColor = Color.decode("#2C3E50"), textColor = Color.WHITE; Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel mainPanel = new JPanel(new BorderLayout()); mainPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("TOURNAMENT REGISTRATION", JLabel.CENTER); titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor); titleLabel.setBorder(new EmptyBorder(15,10,15,10)); mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15)); formPanel.setBackground(primaryColor); formPanel.setBorder(new EmptyBorder(10,30,20,30));
        
        JLabel idLabel = new JLabel("ID:"); idLabel.setForeground(textColor); idLabel.setFont(regularFont); idField.setFont(regularFont); formPanel.add(idLabel); formPanel.add(idField);
        JLabel nameLabel = new JLabel("Name:"); nameLabel.setForeground(textColor); nameLabel.setFont(regularFont); nameField.setFont(regularFont); formPanel.add(nameLabel); formPanel.add(nameField);
        JLabel dateLabel = new JLabel("Date:"); dateLabel.setForeground(textColor); dateLabel.setFont(regularFont); dateField.setFont(regularFont); formPanel.add(dateLabel); formPanel.add(dateField);
        JLabel venueLabel = new JLabel("Venue:"); venueLabel.setForeground(textColor); venueLabel.setFont(regularFont); venueField.setFont(regularFont); formPanel.add(venueLabel); formPanel.add(venueField);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout()); buttonPanel.setBackground(primaryColor);
        JButton registerButton = new JButton("Register"); registerButton.setFont(regularFont); registerButton.setBackground(Color.decode("#27AE60")); registerButton.setForeground(textColor);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)")) {
                    ps.setInt(1, Integer.parseInt(idField.getText())); ps.setString(2, nameField.getText());
                    ps.setString(3, dateField.getText()); ps.setString(4, venueField.getText());
                    ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
            }
        });
        JButton backButton = new JButton("Back"); backButton.setFont(regularFont); backButton.setBackground(Color.decode("#E74C3C")); backButton.setForeground(textColor);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); MainPortal portal = new MainPortal(); portal.setVisible(true); } });
        buttonPanel.add(registerButton); buttonPanel.add(backButton); mainPanel.add(buttonPanel, BorderLayout.SOUTH); add(mainPanel);
    }
}
