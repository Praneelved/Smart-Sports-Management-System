import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class TournamentSchedulePage extends JFrame {
    public TournamentSchedulePage() {
        setTitle("Tournament Schedule"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("UPCOMING TOURNAMENTS", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        DefaultTableModel mod = new DefaultTableModel(new String[]{"ID", "Tournament Name", "Date", "Venue"}, 0);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        m.add(new JScrollPane(tb), BorderLayout.CENTER);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM tournament ORDER BY date")) {
            while (rs.next()) mod.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)});
        } catch (Exception e) {}
        JPanel buttonPanel = new JPanel(new FlowLayout()); buttonPanel.setBackground(pri);
        JButton backButton = new JButton("Back"); backButton.setFont(f); backButton.setBackground(Color.decode("#E74C3C")); backButton.setForeground(txt);
        backButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); MainPortal portal = new MainPortal(); portal.setVisible(true); } });
        buttonPanel.add(backButton); m.add(buttonPanel, BorderLayout.SOUTH); 
        
        add(m);

    }

}
