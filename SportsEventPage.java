import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class SportsEventPage extends JFrame {
    JTextField t1 = new JTextField(), t2 = new JTextField(), t3 = new JTextField(), t4 = new JTextField();
    public SportsEventPage() {
        setTitle("Tournament Registration"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("TOURNAMENT REGISTRATION", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel fp = new JPanel(new GridLayout(4, 2, 10, 15)); fp.setBackground(pri); fp.setBorder(new EmptyBorder(10,30,20,30));
        
        JLabel l1 = new JLabel("ID:"); l1.setForeground(txt); l1.setFont(f); t1.setFont(f); fp.add(l1); fp.add(t1);
        JLabel l2 = new JLabel("Name:"); l2.setForeground(txt); l2.setFont(f); t2.setFont(f); fp.add(l2); fp.add(t2);
        JLabel l3 = new JLabel("Date:"); l3.setForeground(txt); l3.setFont(f); t3.setFont(f); fp.add(l3); fp.add(t3);
        JLabel l4 = new JLabel("Venue:"); l4.setForeground(txt); l4.setFont(f); t4.setFont(f); fp.add(l4); fp.add(t4);
        m.add(fp, BorderLayout.CENTER);
        
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton rb = new JButton("Register"); rb.setFont(f); rb.setBackground(Color.decode("#27AE60")); rb.setForeground(txt);
        rb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)")) {
                    ps.setInt(1, Integer.parseInt(t1.getText())); ps.setString(2, t2.getText());
                    ps.setString(3, t3.getText()); ps.setString(4, t4.getText());
                    ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
            }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new MainPortal().setVisible(true); } });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}
