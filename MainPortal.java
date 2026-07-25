import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainPortal extends JFrame {
    public MainPortal() {
        setTitle("Main Portal");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color buttonColor = Color.decode("#3498DB");
        Color logoutColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("SPORTS MANAGEMENT PORTAL", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        buttonPanel.setBackground(primaryColor);
        buttonPanel.setBorder(new EmptyBorder(10, 30, 30, 30));

        String[] buttons = { "Register Player", "View Players", "Find Player", "Remove Player", 
                             "Tournament", "Tournament Schedule", "SDG Goals", "About Us", 
                             "Points Table", "Logout" };

        for (int i = 0; i < buttons.length; i++) {
            String btnText = buttons[i];
            JButton btn = new JButton(btnText);
            btn.setFont(font);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            
            if (btnText.equals("Logout")) {
                btn.setBackground(logoutColor);
            } else {
                btn.setBackground(buttonColor);
            }
            
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    navigate(btnText);
                }
            });
            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void navigate(String option) {
        if (option.equals("Register Player")) {
            new RegisterPlayer().setVisible(true);
        } else if (option.equals("View Players")) {
            new ViewPlayers().setVisible(true);
        } else if (option.equals("Find Player")) {
            new FindPlayer().setVisible(true);
        } else if (option.equals("Remove Player")) {
            new RemovePlayer().setVisible(true);
        } else if (option.equals("Tournament")) {
            new SportsEventPage().setVisible(true);
        } else if (option.equals("Tournament Schedule")) {
            new TournamentSchedulePage().setVisible(true);
        } else if (option.equals("SDG Goals")) {
            new SDGPage().setVisible(true);
        } else if (option.equals("About Us")) {
            new AboutProjectPage().setVisible(true);
        } else if (option.equals("Points Table")) {
            new PointsTablePage().setVisible(true);
        } else if (option.equals("Logout")) {
            new LoginPage().setVisible(true);
        }
    }
}
