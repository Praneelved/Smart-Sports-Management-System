import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class RemovePlayer extends JFrame {
    public RemovePlayer() {
        setTitle("Remove Player"); setSize(400, 200); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("REMOVE PLAYER", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel cp = new JPanel(new FlowLayout()); cp.setBackground(pri);
        JLabel il = new JLabel("Player ID:"); il.setForeground(txt); il.setFont(f);
        JTextField idf = new JTextField(15); idf.setFont(f);
        cp.add(il); cp.add(idf); m.add(cp, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton rb = new JButton("Remove"); rb.setFont(f); rb.setBackground(Color.decode("#C0392B")); rb.setForeground(txt);
        rb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection c = DBConnection.getConnection(); Statement st = c.createStatement()) {
                    if (st.executeUpdate("DELETE FROM players WHERE id=" + idf.getText()) > 0)
                        JOptionPane.showMessageDialog(null, "Removed successfully.");
                    else JOptionPane.showMessageDialog(null, "Not found.");
                } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
            }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new MainPortal().setVisible(true); } });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}
