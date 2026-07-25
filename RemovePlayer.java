import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RemovePlayer extends JFrame {
    JTextField idField;

    public RemovePlayer() {
        setTitle("Remove Player");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color primaryColor = Color.decode("#2C3E50");
        Color textColor = Color.WHITE;
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);
        
        JLabel titleLabel = new JLabel("REMOVE PLAYER", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(primaryColor);
        
        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setForeground(textColor);
        idLabel.setFont(regularFont);
        
        idField = new JTextField(15);
        idField.setFont(regularFont);
        
        centerPanel.add(idLabel);
        centerPanel.add(idField);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);
        
        JButton removeButton = new JButton("Remove");
        removeButton.setFont(regularFont);
        removeButton.setBackground(Color.decode("#C0392B"));
        removeButton.setForeground(textColor);
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                Statement st = null;

                try {
                    con = DBConnection.getConnection();
                    st = con.createStatement();
                    
                    int rows = st.executeUpdate(
                        "DELETE FROM players WHERE id=" + idField.getText()
                    );
                    
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not found.");
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Database Error");
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
        
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
        
        add(mainPanel);
    }
}
