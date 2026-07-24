import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Sports Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 360);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.add(new JLabel("DASHBOARD", JLabel.CENTER));

        JButton addButton = new JButton("Add Player");
        addButton.addActionListener(event -> {
            new AddPlayer().setVisible(true);
            dispose();
        });
        panel.add(addButton);

        JButton viewButton = new JButton("View Players");
        viewButton.addActionListener(event -> {
            new ViewPlayers().setVisible(true);
            dispose();
        });
        panel.add(viewButton);

        JButton searchButton = new JButton("Search Player");
        searchButton.addActionListener(event -> {
            new SearchPlayer().setVisible(true);
            dispose();
        });
        panel.add(searchButton);

        JButton deleteButton = new JButton("Delete Player");
        deleteButton.addActionListener(event -> {
            new DeletePlayer().setVisible(true);
            dispose();
        });
        panel.add(deleteButton);

        JButton tournamentButton = new JButton("Tournament");
        tournamentButton.addActionListener(event -> {
            new TournamentPage().setVisible(true);
            dispose();
        });
        panel.add(tournamentButton);

        JButton sdgButton = new JButton("SDG Information");
        sdgButton.addActionListener(event -> {
            new SDGPage().setVisible(true);
            dispose();
        });
        panel.add(sdgButton);

        add(panel);
    }
}
