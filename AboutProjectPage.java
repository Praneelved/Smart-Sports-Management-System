import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class AboutProjectPage extends JFrame {
    public AboutProjectPage() {
        setTitle("About Project"); setSize(450, 300); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("ABOUT PROJECT", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        String text = "\nManaging sports activities manually is time-consuming and error-prone. This project provides a GUI-based Sports Management Portal for managing player records, tournaments, and promoting SDG goals.";
        JTextArea ta = new JTextArea(text); ta.setEditable(false); ta.setBackground(pri); ta.setForeground(txt); ta.setFont(f);
        ta.setLineWrap(true); ta.setWrapStyleWord(true); ta.setMargin(new Insets(10,20,10,20)); m.add(ta, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); MainPortal portal = new MainPortal(); portal.setVisible(true); } });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}
