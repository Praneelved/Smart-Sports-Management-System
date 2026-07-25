import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterPlayer extends JFrame {
    
    JTextField idField;
    JTextField nameField;
    JComboBox<String> sportBox;
    JTextField ageField;
    JTextField deptField;
    
    JButton registerButton;
    JButton backButton;

    public RegisterPlayer() {
        setTitle("Register Player");
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

        JLabel titleLabel = new JLabel("REGISTER NEW PLAYER", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBackground(primaryColor);
        formPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        idField = new JTextField();
        idField.setFont(font);
        
        nameField = new JTextField();
        nameField.setFont(font);
        
        String[] sports = { "Cricket", "Football", "Basketball", "Tennis", "Badminton", "Volleyball", "Athletics", "Chess", "Table Tennis" };
        sportBox = new JComboBox<>(sports);
        sportBox.setFont(font);
        
        ageField = new JTextField();
        ageField.setFont(font);
        
        deptField = new JTextField();
        deptField.setFont(font);

        String[] labels = { "ID:", "Name:", "Sport:", "Age:", "Department:" };
        JComponent[] fields = { idField, nameField, sportBox, ageField, deptField };

        for (int i = 0; i < 5; i++) {
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
                    JOptionPane.showMessageDialog(null, "Name cannot be empty");
                    return;
                }
                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?)");
                    ps.setInt(1, Integer.parseInt(idField.getText()));
                    ps.setString(2, nameField.getText());
                    ps.setString(3, sportBox.getSelectedItem().toString());
                    ps.setInt(4, Integer.parseInt(ageField.getText()));
                    ps.setString(5, deptField.getText());
                    
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Player Registered successfully!");
                    
                    ps.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
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
