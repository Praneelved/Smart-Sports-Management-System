import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class MainPortal extends JFrame {
    public MainPortal() {
        setTitle("Main Portal"); setSize(600, 500); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE, btnColor = Color.decode("#3498DB");
        Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel t = new JLabel("SPORTS MANAGEMENT PORTAL", JLabel.CENTER);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); t.setForeground(txt); t.setBorder(new EmptyBorder(15,10,15,10));
        m.add(t, BorderLayout.NORTH);
        
        JPanel bp = new JPanel(new GridLayout(4, 2, 15, 15)); bp.setBackground(pri); bp.setBorder(new EmptyBorder(10,30,30,30));
        
        JButton b1 = new JButton("Register Player"); b1.setFont(f); b1.setBackground(btnColor); b1.setForeground(txt);
        b1.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new RegisterPlayer().setVisible(true); } });
        
        JButton b2 = new JButton("View Players"); b2.setFont(f); b2.setBackground(btnColor); b2.setForeground(txt);
        b2.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new ViewPlayers().setVisible(true); } });
        
        JButton b3 = new JButton("Find Player"); b3.setFont(f); b3.setBackground(btnColor); b3.setForeground(txt);
        b3.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new FindPlayer().setVisible(true); } });
        
        JButton b4 = new JButton("Remove Player"); b4.setFont(f); b4.setBackground(btnColor); b4.setForeground(txt);
        b4.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new RemovePlayer().setVisible(true); } });
        
        JButton b5 = new JButton("Tournament"); b5.setFont(f); b5.setBackground(btnColor); b5.setForeground(txt);
        b5.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new SportsEventPage().setVisible(true); } });
        
        JButton b6 = new JButton("SDG Goals"); b6.setFont(f); b6.setBackground(btnColor); b6.setForeground(txt);
        b6.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new SDGPage().setVisible(true); } });
        
        JButton b7 = new JButton("Logout"); b7.setFont(f); b7.setBackground(Color.decode("#E74C3C")); b7.setForeground(txt);
        b7.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); new LoginPage().setVisible(true); } });
        
        bp.add(b1); bp.add(b2); bp.add(b3); bp.add(b4); bp.add(b5); bp.add(b6); bp.add(b7);
        m.add(bp, BorderLayout.CENTER); add(m);
    }
}
