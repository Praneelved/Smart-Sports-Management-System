import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class PointsTablePage extends JFrame {
    JTextField t1 = new JTextField(10), t2 = new JTextField(3), t3 = new JTextField(3), t4 = new JTextField(3), t5 = new JTextField(3);
    DefaultTableModel mod = new DefaultTableModel(new String[]{"Team Name", "Played", "Won", "Lost", "Points"}, 0);
    public PointsTablePage() {
        setTitle("Points Table"); setSize(750, 500); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("TOURNAMENT POINTS TABLE", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        m.add(new JScrollPane(tb), BorderLayout.CENTER); load();
        
        JPanel ip = new JPanel(new FlowLayout()); ip.setBackground(pri);
        JLabel l1 = new JLabel("Team:"); l1.setForeground(txt); l1.setFont(f); ip.add(l1); ip.add(t1);
        JLabel l2 = new JLabel("P:"); l2.setForeground(txt); l2.setFont(f); ip.add(l2); ip.add(t2);
        JLabel l3 = new JLabel("W:"); l3.setForeground(txt); l3.setFont(f); ip.add(l3); ip.add(t3);
        JLabel l4 = new JLabel("L:"); l4.setForeground(txt); l4.setFont(f); ip.add(l4); ip.add(t4);
        JLabel l5 = new JLabel("Pts:"); l5.setForeground(txt); l5.setFont(f); ip.add(l5); ip.add(t5);
        
        JButton ab = new JButton("Add Team"); ab.setFont(f); ab.setBackground(Color.decode("#27AE60")); ab.setForeground(txt);
        ab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO points_table VALUES (?,?,?,?,?)")) {
                    ps.setString(1, t1.getText()); ps.setInt(2, Integer.parseInt(t2.getText())); ps.setInt(3, Integer.parseInt(t3.getText()));
                    ps.setInt(4, Integer.parseInt(t4.getText())); ps.setInt(5, Integer.parseInt(t5.getText()));
                    ps.executeUpdate(); load();
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
            }
        }); ip.add(ab);
        JPanel bw = new JPanel(new BorderLayout()); bw.setBackground(pri); bw.add(ip, BorderLayout.NORTH);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new MainPortal().setVisible(true); } });
        bp.add(bb); bw.add(bp, BorderLayout.SOUTH); m.add(bw, BorderLayout.SOUTH); add(m);
    }
    public void load() {
        mod.setRowCount(0);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM points_table ORDER BY points DESC")) {
            while (rs.next()) mod.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)});
        } catch (Exception e) {}
    }
}
