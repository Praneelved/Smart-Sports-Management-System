import os

files = {
"Main.java": """import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}""",
"DBConnection.java": """import java.sql.*;
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sportsdb", "root", "Praneel@123");
    }
}""",
"LoginPage.java": """import java.awt.*;
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
}""",
"MainPortal.java": """import java.awt.*;
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
}""",
"RegisterPlayer.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class RegisterPlayer extends JFrame {
    JTextField[] tf = {new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    JComboBox<String> sp = new JComboBox<>(new String[]{"Cricket", "Football", "Basketball", "Tennis", "Badminton"});
    public RegisterPlayer() {
        setTitle("Register Player"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("REGISTER NEW PLAYER", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel fp = new JPanel(new GridLayout(5, 2, 10, 15)); fp.setBackground(pri); fp.setBorder(new EmptyBorder(10,30,20,30));
        String[] lbls = {"ID:", "Name:", "Sport:", "Age:", "Department:"};
        JComponent[] comps = {tf[0], tf[1], sp, tf[2], tf[3]};
        for(int i=0; i<5; i++) {
            JLabel l = new JLabel(lbls[i]); l.setForeground(txt); l.setFont(f);
            comps[i].setFont(f); fp.add(l); fp.add(comps[i]);
        }
        m.add(fp, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton rb = new JButton("Register"); rb.setFont(f); rb.setBackground(Color.decode("#27AE60")); rb.setForeground(txt);
        rb.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?)")) {
                ps.setInt(1, Integer.parseInt(tf[0].getText())); ps.setString(2, tf[1].getText()); ps.setString(3, sp.getSelectedItem().toString());
                ps.setInt(4, Integer.parseInt(tf[2].getText())); ps.setString(5, tf[3].getText());
                ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"ViewPlayers.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class ViewPlayers extends JFrame {
    public ViewPlayers() {
        setTitle("View Players"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("ALL PLAYERS", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        DefaultTableModel mod = new DefaultTableModel(new String[]{"ID", "Name", "Sport", "Age", "Dept"}, 0);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        m.add(new JScrollPane(tb), BorderLayout.CENTER);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM players ORDER BY id")) {
            while (rs.next()) mod.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)});
        } catch (Exception e) {}
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"FindPlayer.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class FindPlayer extends JFrame {
    public FindPlayer() {
        setTitle("Find Player"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("FIND PLAYER", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel tp = new JPanel(new FlowLayout()); tp.setBackground(pri);
        JLabel il = new JLabel("Player ID:"); il.setForeground(txt); il.setFont(f);
        JTextField idf = new JTextField(15); idf.setFont(f);
        JButton sb = new JButton("Search"); sb.setFont(f); sb.setBackground(Color.decode("#F39C12")); sb.setForeground(txt);
        tp.add(il); tp.add(idf); tp.add(sb);
        DefaultTableModel mod = new DefaultTableModel(new String[]{"ID", "Name", "Sport", "Age", "Dept"}, 0);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        JPanel cp = new JPanel(new BorderLayout()); cp.add(tp, BorderLayout.NORTH); cp.add(new JScrollPane(tb), BorderLayout.CENTER);
        m.add(cp, BorderLayout.CENTER);
        sb.addActionListener(e -> {
            mod.setRowCount(0);
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE id=?")) {
                ps.setInt(1, Integer.parseInt(idf.getText())); ResultSet rs = ps.executeQuery();
                if (rs.next()) mod.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)});
                else JOptionPane.showMessageDialog(null, "Not Found");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"RemovePlayer.java": """import java.awt.*;
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
        rb.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection(); Statement st = c.createStatement()) {
                if (st.executeUpdate("DELETE FROM players WHERE id=" + idf.getText()) > 0)
                    JOptionPane.showMessageDialog(null, "Removed successfully.");
                else JOptionPane.showMessageDialog(null, "Not found.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"SportsEventPage.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class SportsEventPage extends JFrame {
    JTextField[] tf = {new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    public SportsEventPage() {
        setTitle("Tournament Registration"); setSize(450, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("TOURNAMENT REGISTRATION", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JPanel fp = new JPanel(new GridLayout(4, 2, 10, 15)); fp.setBackground(pri); fp.setBorder(new EmptyBorder(10,30,20,30));
        String[] lbls = {"ID:", "Name:", "Date:", "Venue:"};
        for(int i=0; i<4; i++) {
            JLabel l = new JLabel(lbls[i]); l.setForeground(txt); l.setFont(f);
            tf[i].setFont(f); fp.add(l); fp.add(tf[i]);
        }
        m.add(fp, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton rb = new JButton("Register"); rb.setFont(f); rb.setBackground(Color.decode("#27AE60")); rb.setForeground(txt);
        rb.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)")) {
                ps.setInt(1, Integer.parseInt(tf[0].getText())); ps.setString(2, tf[1].getText());
                ps.setString(3, tf[2].getText()); ps.setString(4, tf[3].getText());
                ps.executeUpdate(); JOptionPane.showMessageDialog(null, "Registered successfully!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        });
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(rb); bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"TournamentSchedulePage.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class TournamentSchedulePage extends JFrame {
    public TournamentSchedulePage() {
        setTitle("Tournament Schedule"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("UPCOMING TOURNAMENTS", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        DefaultTableModel mod = new DefaultTableModel(new String[]{"ID", "Tournament Name", "Date", "Venue"}, 0);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        m.add(new JScrollPane(tb), BorderLayout.CENTER);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM tournament ORDER BY date")) {
            while (rs.next()) mod.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)});
        } catch (Exception e) {}
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"PointsTablePage.java": """import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
public class PointsTablePage extends JFrame {
    JTextField[] tf = {new JTextField(10), new JTextField(3), new JTextField(3), new JTextField(3), new JTextField(3)};
    DefaultTableModel mod = new DefaultTableModel(new String[]{"Team Name", "Played", "Won", "Lost", "Points"}, 0);
    public PointsTablePage() {
        setTitle("Points Table"); setSize(750, 500); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("TOURNAMENT POINTS TABLE", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        JTable tb = new JTable(mod); tb.setFont(new Font("Segoe UI", Font.PLAIN, 12)); tb.setRowHeight(25);
        m.add(new JScrollPane(tb), BorderLayout.CENTER); load();
        JPanel ip = new JPanel(new FlowLayout()); ip.setBackground(pri);
        String[] lns = {"Team:", "P:", "W:", "L:", "Pts:"};
        for(int i=0; i<5; i++) { JLabel l = new JLabel(lns[i]); l.setForeground(txt); l.setFont(f); ip.add(l); ip.add(tf[i]); }
        JButton ab = new JButton("Add Team"); ab.setFont(f); ab.setBackground(Color.decode("#27AE60")); ab.setForeground(txt);
        ab.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO points_table VALUES (?,?,?,?,?)")) {
                ps.setString(1, tf[0].getText()); for(int i=1;i<=4;i++) ps.setInt(i+1, Integer.parseInt(tf[i].getText()));
                ps.executeUpdate(); load();
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
        }); ip.add(ab);
        JPanel bw = new JPanel(new BorderLayout()); bw.setBackground(pri); bw.add(ip, BorderLayout.NORTH);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); bw.add(bp, BorderLayout.SOUTH); m.add(bw, BorderLayout.SOUTH); add(m);
    }
    public void load() {
        mod.setRowCount(0);
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM points_table ORDER BY points DESC")) {
            while (rs.next()) mod.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)});
        } catch (Exception e) {}
    }
}""",
"SDGPage.java": """import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class SDGPage extends JFrame {
    public SDGPage() {
        setTitle("Sustainable Development Goals"); setSize(600, 400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("SUSTAINABLE DEVELOPMENT GOALS", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        String text = "\\nSDG 3: Good Health and Well-being\\nSports improve physical and mental health among students.\\n\\nSDG 4: Quality Education\\nSports develop leadership, teamwork, and discipline.\\n\\nOur System promotes SDG 3 by encouraging participation and SDG 4 by fostering soft skills.";
        JTextArea ta = new JTextArea(text); ta.setEditable(false); ta.setBackground(pri); ta.setForeground(txt); ta.setFont(f);
        ta.setLineWrap(true); ta.setWrapStyleWord(true); ta.setMargin(new Insets(10,20,10,20)); m.add(ta, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}""",
"AboutProjectPage.java": """import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class AboutProjectPage extends JFrame {
    public AboutProjectPage() {
        setTitle("About Project"); setSize(450, 300); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color pri = Color.decode("#2C3E50"), txt = Color.WHITE; Font f = new Font("Segoe UI", Font.PLAIN, 15);
        JPanel m = new JPanel(new BorderLayout()); m.setBackground(pri);
        JLabel tl = new JLabel("ABOUT PROJECT", JLabel.CENTER); tl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tl.setForeground(txt); tl.setBorder(new EmptyBorder(15,10,15,10)); m.add(tl, BorderLayout.NORTH);
        String text = "\\nManaging sports activities manually is time-consuming and error-prone. This project provides a GUI-based Sports Management Portal for managing player records, tournaments, and promoting SDG goals.";
        JTextArea ta = new JTextArea(text); ta.setEditable(false); ta.setBackground(pri); ta.setForeground(txt); ta.setFont(f);
        ta.setLineWrap(true); ta.setWrapStyleWord(true); ta.setMargin(new Insets(10,20,10,20)); m.add(ta, BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout()); bp.setBackground(pri);
        JButton bb = new JButton("Back"); bb.setFont(f); bb.setBackground(Color.decode("#E74C3C")); bb.setForeground(txt);
        bb.addActionListener(e -> { dispose(); new MainPortal().setVisible(true); });
        bp.add(bb); m.add(bp, BorderLayout.SOUTH); add(m);
    }
}"""
}

for name, content in files.items():
    with open(name, "w", encoding="utf-8") as f:
        f.write(content)

print("Files written successfully")
