import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class FindPlayer extends JFrame {
    JTextField idField;

    public FindPlayer() {
        setTitle("Find Player");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color primaryColor = Color.decode("#2C3E50");
        Color textColor = Color.WHITE;
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);
        
        JLabel titleLabel = new JLabel("FIND PLAYER", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(primaryColor);
        
        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setForeground(textColor);
        idLabel.setFont(regularFont);
        
        idField = new JTextField(15);
        idField.setFont(regularFont);
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(regularFont);
        searchButton.setBackground(Color.decode("#F39C12"));
        searchButton.setForeground(textColor);
        
        topPanel.add(idLabel);
        topPanel.add(idField);
        topPanel.add(searchButton);
        
        String[] columns = {"ID", "Name", "Sport", "Age", "Dept"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultTable.setRowHeight(25);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                try {
                    con = DBConnection.getConnection();
                    pst = con.prepareStatement(
                        "SELECT * FROM players WHERE id=?"
                    );
                    
                    pst.setInt(1, Integer.parseInt(idField.getText()));
                    rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        Object[] row = {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)
                        };
                        tableModel.addRow(row);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not Found");
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Database Error");
                }
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);
        
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
        
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
        
        add(mainPanel);
    }
}
