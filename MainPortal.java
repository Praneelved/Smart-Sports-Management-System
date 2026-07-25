import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class MainPortal extends JFrame {
    public MainPortal() {
        setTitle("Main Portal"); setSize(600, 500); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE;
        Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel t = new JLabel("SPORTS MANAGEMENT PORTAL", JLabel.CENTER);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); t.setForeground(txt); t.setBorder(new EmptyBorder(15,10,15,10));
        m.add(t, BorderLayout.NORTH);
        JPanel bp = new JPanel(new GridLayout(5, 2, 15, 15)); bp.setBackground(pri); bp.setBorder(new EmptyBorder(10,30,30,30));
        String[] btns = {"Register Player", "View Players", "Find Player", "Remove Player", "Tournament", "Tournament Schedule", "SDG Goals", "About Us", "Points Table", "Logout"};
        for (String txtBtn : btns) {
            JButton b = new JButton(txtBtn); b.setFont(f); b.setForeground(txt); b.setFocusPainted(false);
            b.setBackground(txtBtn.equals("Logout") ? Color.decode("#E74C3C") : Color.decode("#3498DB"));
            b.addActionListener(e -> {
                dispose();
                switch(txtBtn) {
                    case "Register Player": new RegisterPlayer().setVisible(true); break;
                    case "View Players": new ViewPlayers().setVisible(true); break;
                    case "Find Player": new FindPlayer().setVisible(true); break;
                    case "Remove Player": new RemovePlayer().setVisible(true); break;
                    case "Tournament": new SportsEventPage().setVisible(true); break;
                    case "Tournament Schedule": new TournamentSchedulePage().setVisible(true); break;
                    case "SDG Goals": new SDGPage().setVisible(true); break;
                    case "About Us": new AboutProjectPage().setVisible(true); break;
                    case "Points Table": new PointsTablePage().setVisible(true); break;
                    case "Logout": new LoginPage().setVisible(true); break;
                }
            });
            bp.add(b);
        }
        m.add(bp, BorderLayout.CENTER); add(m);
    }
}
