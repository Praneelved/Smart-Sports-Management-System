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
    
    JButton addButton;
    JButton backButton;
    
    DefaultTableModel model;
    JTable table;

    public PointsTablePage() {
        setTitle("Points Table");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color addBtnColor = Color.decode("#27AE60");
        Color backBtnColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("TOURNAMENT POINTS TABLE", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[] { "Team Name", "Played", "Won", "Lost", "Points" }, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(primaryColor);

        JLabel teamLbl = new JLabel("Team:"); 
        teamLbl.setForeground(textColor); 
        teamLbl.setFont(font);
        teamField = new JTextField(10);
        
        JLabel pLbl = new JLabel("P:"); 
        pLbl.setForeground(textColor); 
        pLbl.setFont(font);
        playedField = new JTextField(5);
        
        JLabel wLbl = new JLabel("W:"); 
        wLbl.setForeground(textColor); 
        wLbl.setFont(font);
        wonField = new JTextField(5);
        
        JLabel lLbl = new JLabel("L:"); 
        lLbl.setForeground(textColor); 
        lLbl.setFont(font);
        lostField = new JTextField(5);
        
        JLabel ptsLbl = new JLabel("Pts:"); 
        ptsLbl.setForeground(textColor); 
        ptsLbl.setFont(font);
        pointsField = new JTextField(5);

        inputPanel.add(teamLbl); 
        inputPanel.add(teamField);
        
        inputPanel.add(pLbl); 
        inputPanel.add(playedField);
        
        inputPanel.add(wLbl); 
        inputPanel.add(wonField);
        
        inputPanel.add(lLbl); 
        inputPanel.add(lostField);
        
        inputPanel.add(ptsLbl); 
        inputPanel.add(pointsField);

        addButton = new JButton("Add Team");
        addButton.setFont(font);
        addButton.setBackground(addBtnColor);
        addButton.setForeground(textColor);
        addButton.setFocusPainted(false);
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeam();
            }
        });
        inputPanel.add(addButton);

        JPanel bottomWrapper = new JPanel(new BorderLayout());
        bottomWrapper.setBackground(primaryColor);
        bottomWrapper.add(inputPanel, BorderLayout.NORTH);

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
        bottomWrapper.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomWrapper, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void loadData() {
        model.setRowCount(0);
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM points_table ORDER BY points DESC");
            
            while (rs.next()) {
                model.addRow(new Object[] { 
                    rs.getString(1), 
                    rs.getInt(2), 
                    rs.getInt(3), 
                    rs.getInt(4), 
                    rs.getInt(5) 
                });
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTeam() {
        if (teamField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Team name cannot be empty!");
            return;
        }
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO points_table VALUES (?,?,?,?,?)");
            ps.setString(1, teamField.getText());
            ps.setInt(2, Integer.parseInt(playedField.getText()));
            ps.setInt(3, Integer.parseInt(wonField.getText()));
            ps.setInt(4, Integer.parseInt(lostField.getText()));
            ps.setInt(5, Integer.parseInt(pointsField.getText()));
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Team added successfully.");
            
            ps.close();
            con.close();
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid Data");
        }
    }
}
