import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class MainPortal extends JFrame {
    public MainPortal() {
        setTitle("Main Portal"); setSize(600, 500); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color primaryColor = Color.decode("#2C3E50"), textColor = Color.WHITE, btnColor = Color.decode("#3498DB");
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel mainPanel = new JPanel(new BorderLayout()); mainPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("SPORTS MANAGEMENT PORTAL", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); titleLabel.setForeground(textColor); titleLabel.setBorder(new EmptyBorder(15,10,15,10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 15, 15)); buttonPanel.setBackground(primaryColor); buttonPanel.setBorder(new EmptyBorder(10,30,30,30));
        
        JButton registerButton = new JButton("Register Player"); registerButton.setFont(regularFont); registerButton.setBackground(btnColor); registerButton.setForeground(textColor);
        registerButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); RegisterPlayer page = new RegisterPlayer(); page.setVisible(true); } });
        
        JButton viewButton = new JButton("View Players"); viewButton.setFont(regularFont); viewButton.setBackground(btnColor); viewButton.setForeground(textColor);
        viewButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); ViewPlayers page = new ViewPlayers(); page.setVisible(true); } });
        
        JButton findButton = new JButton("Find Player"); findButton.setFont(regularFont); findButton.setBackground(btnColor); findButton.setForeground(textColor);
        findButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); FindPlayer page = new FindPlayer(); page.setVisible(true); } });
        
        JButton removeButton = new JButton("Remove Player"); removeButton.setFont(regularFont); removeButton.setBackground(btnColor); removeButton.setForeground(textColor);
        removeButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); RemovePlayer page = new RemovePlayer(); page.setVisible(true); } });
        
        JButton tournamentButton = new JButton("Tournament"); tournamentButton.setFont(regularFont); tournamentButton.setBackground(btnColor); tournamentButton.setForeground(textColor);
        tournamentButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); SportsEventPage page = new SportsEventPage(); page.setVisible(true); } });
        
        JButton sdgButton = new JButton("SDG Goals"); sdgButton.setFont(regularFont); sdgButton.setBackground(btnColor); sdgButton.setForeground(textColor);
        sdgButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); SDGPage page = new SDGPage(); page.setVisible(true); } });
        
        JButton logoutButton = new JButton("Logout"); logoutButton.setFont(regularFont); logoutButton.setBackground(Color.decode("#E74C3C")); logoutButton.setForeground(textColor);
        logoutButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); LoginPage page = new LoginPage(); page.setVisible(true); } });
        
        buttonPanel.add(registerButton); buttonPanel.add(viewButton); buttonPanel.add(findButton); buttonPanel.add(removeButton); 
        buttonPanel.add(tournamentButton); buttonPanel.add(sdgButton); buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);

    }   // <-- Constructor close

}       // <-- Class close
