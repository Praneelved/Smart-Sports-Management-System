import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class FindPlayer extends JFrame {
    public FindPlayer() {
        setTitle("Find Player"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("FIND PLAYER", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel tp = new JPanel(new FlowLayout()); tp.setBackground(pri);
        JLabel il = new JLabel("Player ID:"); il.setForeground(txt); il.setFont(f);
        JTextField idf = new JTextField(15); idf.setFont(f);
        JButton sb = new JButton("Search"); sb.setFont(f); sb.setBackground(Color.decode("#F39C12")); sb.setForeground(txt);
        tp.add(il); tp.add(idf); tp.add(sb);
        DefaultTableModel mod = new DefaultTableModel(new String[]{"ID", "Name", "Sport", "Age", "Dept"}, 0);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        JPanel cp = new JPanel(new BorderLayout()); cp.add(tp, BorderLayout.NORTH); cp.add(new JScrollPane(tb), BorderLayout.CENTER);
        m.add(cp, BorderLayout.CENTER);
        sb.addActionListener(e -> {
            mod.setRowCount(0);
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE id=?")) {
                ps.setInt(1, Integer.parseInt(idf.getText())); ResultSet rs = ps.executeQuery();
                if (rs.next()) mod.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)});
                else JOptionPane.showMessageDialog(null, "Not Found");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}
