import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterPlayer extends JFrame {
    JTextField idField;
    JTextField nameField;
    JComboBox<String> sportCombo;
    JTextField ageField;
    JTextField deptField;

    public RegisterPlayer() {
        setTitle("Register Player");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color primaryColor = Color.decode("#2C3E50");
        Color textColor = Color.WHITE;
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);
        
        JLabel titleLabel = new JLabel("REGISTER NEW PLAYER", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBackground(primaryColor);
        formPanel.setBorder(new EmptyBorder(10,30,20,30));
        
        idField = new JTextField();
        nameField = new JTextField();
        String[] sports = {"Cricket", "Football", "Basketball", "Tennis", "Badminton"};
        sportCombo = new JComboBox<String>(sports);
        ageField = new JTextField();
        deptField = new JTextField();
        
        idField.setFont(regularFont);
        nameField.setFont(regularFont);
        sportCombo.setFont(regularFont);
        ageField.setFont(regularFont);
        deptField.setFont(regularFont);

        JLabel l1 = new JLabel("ID:");
        l1.setForeground(textColor);
        l1.setFont(regularFont);
        
        JLabel l2 = new JLabel("Name:");
        l2.setForeground(textColor);
        l2.setFont(regularFont);
        
        JLabel l3 = new JLabel("Sport:");
        l3.setForeground(textColor);
        l3.setFont(regularFont);
        
        JLabel l4 = new JLabel("Age:");
        l4.setForeground(textColor);
        l4.setFont(regularFont);
        
        JLabel l5 = new JLabel("Department:");
        l5.setForeground(textColor);
        l5.setFont(regularFont);
        
        formPanel.add(l1);
        formPanel.add(idField);
        
        formPanel.add(l2);
        formPanel.add(nameField);
        
        formPanel.add(l3);
        formPanel.add(sportCombo);
        
        formPanel.add(l4);
        formPanel.add(ageField);
        
        formPanel.add(l5);
        formPanel.add(deptField);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);
        
        JButton registerButton = new JButton("Register");
        registerButton.setFont(regularFont);
        registerButton.setBackground(Color.decode("#27AE60"));
        registerButton.setForeground(textColor);
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                PreparedStatement pst = null;

                try {
                    con = DBConnection.getConnection();
                    pst = con.prepareStatement(
                        "INSERT INTO players VALUES(?,?,?,?,?)"
                    );

                    pst.setInt(1, Integer.parseInt(idField.getText()));
                    pst.setString(2, nameField.getText());
                    pst.setString(3, sportCombo.getSelectedItem().toString());
                    pst.setInt(4, Integer.parseInt(ageField.getText()));
                    pst.setString(5, deptField.getText());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Registered successfully!");

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Database Error");
                }
            }
        });
        
        JButton backButton = new JButton("Back");
        backButton.setFont(regularFont);
        backButton.setBackground(Color.decode("#E74C3C"));
        backButton.setForeground(textColor);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPortal portal = new MainPortal();
                portal.setVisible(true);
            }
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
        
        add(mainPanel);
    }
}
