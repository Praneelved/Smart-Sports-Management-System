import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class RegisterPlayer extends JFrame {
    JTextField idField = new JTextField(), nameField = new JTextField(), ageField = new JTextField(), deptField = new JTextField();
    JComboBox<String> sportCombo = new JComboBox<>(new String[]{"Cricket", "Football", "Basketball", "Tennis", "Badminton"});
    public RegisterPlayer() {
        setTitle("Register Player"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color primaryColor = Color.decode("#2C3E50"), textColor = Color.WHITE; Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel mainPanel = new JPanel(new BorderLayout()); mainPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("REGISTER NEW PLAYER", JLabel.CENTER); titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor); titleLabel.setBorder(new EmptyBorder(15,10,15,10)); mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15)); formPanel.setBackground(primaryColor); formPanel.setBorder(new EmptyBorder(10,30,20,30));
        
        JLabel idLabel = new JLabel("ID:"); idLabel.setForeground(textColor); idLabel.setFont(regularFont); idField.setFont(regularFont); formPanel.add(idLabel); formPanel.add(idField);
        JLabel nameLabel = new JLabel("Name:"); nameLabel.setForeground(textColor); nameLabel.setFont(regularFont); nameField.setFont(regularFont); formPanel.add(nameLabel); formPanel.add(nameField);
        JLabel sportLabel = new JLabel("Sport:"); sportLabel.setForeground(textColor); sportLabel.setFont(regularFont); sportCombo.setFont(regularFont); formPanel.add(sportLabel); formPanel.add(sportCombo);
        JLabel ageLabel = new JLabel("Age:"); ageLabel.setForeground(textColor); ageLabel.setFont(regularFont); ageField.setFont(regularFont); formPanel.add(ageLabel); formPanel.add(ageField);
        JLabel deptLabel = new JLabel("Department:"); deptLabel.setForeground(textColor); deptLabel.setFont(regularFont); deptField.setFont(regularFont); formPanel.add(deptLabel); formPanel.add(deptField);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout()); buttonPanel.setBackground(primaryColor);
        JButton registerButton = new JButton("Register"); registerButton.setFont(regularFont); registerButton.setBackground(Color.decode("#27AE60")); registerButton.setForeground(textColor);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?)")) {
                    ps.setInt(1, Integer.parseInt(idField.getText())); ps.setString(2, nameField.getText()); ps.setString(3, sportCombo.getSelectedItem().toString());
                    ps.setInt(4, Integer.parseInt(ageField.getText())); ps.setString(5, deptField.getText());
                    ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
            }
        });
        JButton backButton = new JButton("Back"); backButton.setFont(regularFont); backButton.setBackground(Color.decode("#E74C3C")); backButton.setForeground(textColor);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); MainPortal portal = new MainPortal(); portal.setVisible(true); } });
        buttonPanel.add(registerButton);        buttonPanel.add(backButton); mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
        
        add(mainPanel);

    }   // <-- Constructor close

}       // <-- Class close
