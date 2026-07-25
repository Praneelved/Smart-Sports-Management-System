import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class FindPlayer extends JFrame {
    
    JTextField idField;
    JButton searchButton;
    JButton backButton;
    JTable table;
    DefaultTableModel model;

    public FindPlayer() {
        setTitle("Find Player");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color buttonColor = Color.decode("#F39C12");
        Color backBtnColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);
        
        JLabel titleLabel = new JLabel("FIND PLAYER", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(primaryColor);
        
        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setForeground(textColor);
        idLabel.setFont(font);
        
        idField = new JTextField(15);
        idField.setFont(font);
        
        searchButton = new JButton("Search");
        searchButton.setFont(font);
        searchButton.setBackground(buttonColor);
        searchButton.setForeground(textColor);
        searchButton.setFocusPainted(false);
        
        topPanel.add(idLabel);
        topPanel.add(idField);
        topPanel.add(searchButton);

        model = new DefaultTableModel(new String[] { "ID", "Name", "Sport", "Age", "Dept" }, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPlayer();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);
        
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

        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    
    public void searchPlayer() {
        model.setRowCount(0);
        try {
            int id = Integer.parseInt(idField.getText());
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM players WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model.addRow(new Object[] { 
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("sport"), 
                    rs.getInt("age"), 
                    rs.getString("department") 
                });
            } else {
                JOptionPane.showMessageDialog(null, "Player Not Found");
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
        }
    }
}
