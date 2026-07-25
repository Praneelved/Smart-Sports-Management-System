import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class ViewPlayers extends JFrame {
    public ViewPlayers() {
        setTitle("View Players"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color primaryColor = Color.decode("#2C3E50"), textColor = Color.WHITE; Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel mainPanel = new JPanel(new BorderLayout()); mainPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("ALL PLAYERS", JLabel.CENTER); titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(textColor); titleLabel.setBorder(new EmptyBorder(15,10,15,10)); mainPanel.add(titleLabel, BorderLayout.NORTH);
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Sport", "Age", "Dept"}, 0);
        JTable playersTable = new JTable(tableModel); playersTable.setFont(new Font("Segoe UI", Font.PLAIN, 12)); playersTable.setRowHeight(25);
        mainPanel.add(new JScrollPane(playersTable), BorderLayout.CENTER);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM players ORDER BY id")) {
            while (rs.next()) tableModel.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)});
        } catch (Exception e) {}
        JPanel buttonPanel = new JPanel(new FlowLayout()); buttonPanel.setBackground(primaryColor);
        JButton backButton = new JButton("Back"); backButton.setFont(regularFont); backButton.setBackground(Color.decode("#E74C3C")); backButton.setForeground(textColor);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); MainPortal portal = new MainPortal(); portal.setVisible(true); } });
        buttonPanel.add(backButton); mainPanel.add(buttonPanel, BorderLayout.SOUTH); add(mainPanel);
    }
}
