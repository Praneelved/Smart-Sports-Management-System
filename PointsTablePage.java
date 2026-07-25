import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PointsTablePage extends JFrame {
    JTextField teamField;
    JTextField playedField;
    JTextField wonField;
    JTextField lostField;
    JTextField pointsField;
    DefaultTableModel tableModel;

    public PointsTablePage() {
        setTitle("Points Table");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color primaryColor = Color.decode("#2C3E50");
        Color textColor = Color.WHITE;
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);
        
        JLabel titleLabel = new JLabel("TOURNAMENT POINTS TABLE", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        String[] columns = {"Team Name", "Played", "Won", "Lost", "Points"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable pointsTable = new JTable(tableModel);
        pointsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pointsTable.setRowHeight(25);
        
        mainPanel.add(new JScrollPane(pointsTable), BorderLayout.CENTER);
        load();
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(primaryColor);
        
        teamField = new JTextField(10);
        playedField = new JTextField(3);
        wonField = new JTextField(3);
        lostField = new JTextField(3);
        pointsField = new JTextField(3);
        
        JLabel teamLabel = new JLabel("Team:");
        teamLabel.setForeground(textColor);
        teamLabel.setFont(regularFont);
        
        JLabel pLabel = new JLabel("P:");
        pLabel.setForeground(textColor);
        pLabel.setFont(regularFont);
        
        JLabel wLabel = new JLabel("W:");
        wLabel.setForeground(textColor);
        wLabel.setFont(regularFont);
        
        JLabel lLabel = new JLabel("L:");
        lLabel.setForeground(textColor);
        lLabel.setFont(regularFont);
        
        JLabel ptsLabel = new JLabel("Pts:");
        ptsLabel.setForeground(textColor);
        ptsLabel.setFont(regularFont);
        
        inputPanel.add(teamLabel);
        inputPanel.add(teamField);
        inputPanel.add(pLabel);
        inputPanel.add(playedField);
        inputPanel.add(wLabel);
        inputPanel.add(wonField);
        inputPanel.add(lLabel);
        inputPanel.add(lostField);
        inputPanel.add(ptsLabel);
        inputPanel.add(pointsField);
        
        JButton addButton = new JButton("Add Team");
        addButton.setFont(regularFont);
        addButton.setBackground(Color.decode("#27AE60"));
        addButton.setForeground(textColor);
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                PreparedStatement pst = null;

                try {
                    con = DBConnection.getConnection();
                    pst = con.prepareStatement(
                        "INSERT INTO points_table VALUES(?,?,?,?,?)"
                    );

                    pst.setString(1, teamField.getText());
                    pst.setInt(2, Integer.parseInt(playedField.getText()));
                    pst.setInt(3, Integer.parseInt(wonField.getText()));
                    pst.setInt(4, Integer.parseInt(lostField.getText()));
                    pst.setInt(5, Integer.parseInt(pointsField.getText()));

                    pst.executeUpdate();
                    load();
                    
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Database Error");
                }
            }
        });
        
        inputPanel.add(addButton);
        
        JPanel bottomWrapper = new JPanel(new BorderLayout());
        bottomWrapper.setBackground(primaryColor);
        bottomWrapper.add(inputPanel, BorderLayout.NORTH);
        
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
        bottomWrapper.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(bottomWrapper, BorderLayout.SOUTH); 
        
        add(mainPanel);
    } 
    
    public void load() {
        tableModel.setRowCount(0);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM points_table ORDER BY points DESC");
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5)
                };
                tableModel.addRow(row);
            }
        } catch(Exception e) {
            System.out.println("Error loading table");
        }
    }
}
