import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class SportsEventPage extends JFrame {
    JTextField[] tf = {new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    public SportsEventPage() {
        setTitle("Tournament Registration"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("TOURNAMENT REGISTRATION", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel fp = new JPanel(new GridLayout(4, 2, 10, 15)); fp.setBackground(pri); fp.setBorder(new EmptyBorder(10,30,20,30));
        String[] lbls = {"ID:", "Name:", "Date:", "Venue:"};
        for(int i=0; i<4; i++) {
            JLabel l = new JLabel(lbls[i]); l.setForeground(txt); l.setFont(f);
            tf[i].setFont(f); fp.add(l); fp.add(tf[i]);
        }
        m.add(fp, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton rb = new JButton("Register"); rb.setFont(f); rb.setBackground(Color.decode("#27AE60")); rb.setForeground(txt);
        rb.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)")) {
                ps.setInt(1, Integer.parseInt(tf[0].getText())); ps.setString(2, tf[1].getText());
                ps.setString(3, tf[2].getText()); ps.setString(4, tf[3].getText());
                ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}
