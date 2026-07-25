import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class LoginPage extends JFrame {
    JTextField u = new JTextField(15); JPasswordField p = new JPasswordField(15);
    public LoginPage() {
        setTitle("Login"); setSize(450, 300); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE;
        Font f = new Font("Segoe UI", Font.PLAIN, 15), bf = new Font("Segoe UI", Font.BOLD, 18);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel t = new JLabel("SPORTS MANAGEMENT SYSTEM", JLabel.CENTER);
        t.setFont(bf); t.setForeground(txt); t.setBorder(new EmptyBorder(15,10,15,10));
        m.add(t, BorderLayout.NORTH);
        JPanel fp = new JPanel(new GridBagLayout()); fp.setBackground(pri);
        GridBagConstraints c = new GridBagConstraints(); c.insets = new Insets(8,8,8,8);
        JLabel ul = new JLabel("Username:"); ul.setForeground(txt); ul.setFont(f);
        JLabel pl = new JLabel("Password:"); pl.setForeground(txt); pl.setFont(f);
        u.setFont(f); p.setFont(f);
        c.gridx=0; c.gridy=0; fp.add(ul, c); c.gridx=1; fp.add(u, c);
        c.gridx=0; c.gridy=1; fp.add(pl, c); c.gridx=1; fp.add(p, c);
        JButton b = new JButton("LOGIN"); b.setFont(f); b.setBackground(Color.decode("#3498DB")); b.setForeground(txt);
        b.addActionListener(e -> {
            if (u.getText().trim().equals("Praneel") && new String(p.getPassword()).equals("Praneel@123")) {
                dispose(); new MainPortal().setVisible(true);
            } else JOptionPane.showMessageDialog(null, "Invalid Credentials");
        });
        c.gridx=1; c.gridy=2; fp.add(b, c);
        m.add(fp, BorderLayout.CENTER); add(m);
    }
}
