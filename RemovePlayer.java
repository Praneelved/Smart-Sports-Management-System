import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RemovePlayer extends JFrame {
    
    JTextField idField;
    JButton removeButton;
    JButton backButton;

    public RemovePlayer() {
        setTitle("Remove Player");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color removeBtnColor = Color.decode("#C0392B");
        Color backBtnColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("REMOVE PLAYER", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(primaryColor);
        
        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setForeground(textColor);
        idLabel.setFont(font);
        
        idField = new JTextField(15);
        idField.setFont(font);
        
        centerPanel.add(idLabel);
        centerPanel.add(idField);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);

        removeButton = new JButton("Remove");
        removeButton.setFont(font);
        removeButton.setBackground(removeBtnColor);
        removeButton.setForeground(textColor);
        removeButton.setFocusPainted(false);
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DBConnection.getConnection();
                    Statement st = con.createStatement();
                    int res = st.executeUpdate("DELETE FROM players WHERE id=" + idField.getText());
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Player removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Player not found.");
                    }
                    st.close();
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

        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
