import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainPortal extends JFrame {
    public MainPortal() {
        UIUtils.setupFrame(this, "Main Portal", 600, 500);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("SPORTS MANAGEMENT PORTAL"), BorderLayout.NORTH);

        JPanel bp = UIUtils.createPanel(new GridLayout(5, 2, 15, 15));
        bp.setBorder(new EmptyBorder(10, 30, 30, 30));
        String[] btns = { "Register Player", "View Players", "Find Player", "Remove Player", "Tournament",
                "Tournament Schedule", "SDG Goals", "About Us", "Points Table", "Logout" };
        for (String b : btns) {
            JButton btn = UIUtils.createBtn(b, b.equals("Logout") ? Color.decode("#E74C3C") : UIUtils.BTN_CLR);
            btn.addActionListener(e -> {
                dispose();
                nav(b);
            });
            bp.add(btn);
        }
        m.add(bp, BorderLayout.CENTER);
        add(m);
    }

    void nav(String b) {
        switch (b) {
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
    }
}
