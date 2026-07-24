import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}

class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sportsdb", "root", "Praneel@123");
    }
}

class UIUtils {
    public static final Color PRIMARY = Color.decode("#2C3E50");
    public static final Color BTN_CLR = Color.decode("#3498DB");
    public static final Color TEXT = Color.WHITE;
    public static final Font FONT = new Font("Segoe UI", Font.PLAIN, 15);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);

    public static void setupFrame(JFrame f, String title, int w, int h) {
        f.setTitle(title);
        f.setSize(w, h);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JPanel createPanel(LayoutManager lm) {
        JPanel p = new JPanel(lm);
        p.setBackground(PRIMARY);
        return p;
    }

    public static JLabel createTitle(String txt) {
        JLabel l = new JLabel(txt, JLabel.CENTER);
        l.setFont(TITLE_FONT);
        l.setForeground(TEXT);
        l.setBorder(new EmptyBorder(15, 10, 15, 10));
        return l;
    }

    public static JLabel createLabel(String txt) {
        JLabel l = new JLabel(txt);
        l.setForeground(TEXT);
        l.setFont(FONT);
        return l;
    }

    public static JButton createBtn(String txt, Color c) {
        JButton b = new JButton(txt);
        b.setFont(FONT);
        b.setBackground(c);
        b.setForeground(TEXT);
        b.setFocusPainted(false);
        return b;
    }

    public static JButton createBackBtn(JFrame f) {
        JButton b = createBtn("Back", Color.decode("#E74C3C"));
        b.addActionListener(e -> {
            f.dispose();
            new MainPortal().setVisible(true);
        });
        return b;
    }

    public static JTable createTable(DefaultTableModel m) {
        JTable t = new JTable(m);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t.setRowHeight(25);
        return t;
    }
}

class LoginPage extends JFrame {
    JTextField user = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

    public LoginPage() {
        UIUtils.setupFrame(this, "Login", 450, 300);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("SPORTS MANAGEMENT SYSTEM"), BorderLayout.NORTH);

        JPanel fp = UIUtils.createPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        fp.add(UIUtils.createLabel("Username:"), c);
        c.gridx = 1;
        user.setFont(UIUtils.FONT);
        fp.add(user, c);
        c.gridx = 0;
        c.gridy = 1;
        fp.add(UIUtils.createLabel("Password:"), c);
        c.gridx = 1;
        pass.setFont(UIUtils.FONT);
        fp.add(pass, c);

        JButton lb = UIUtils.createBtn("LOGIN", UIUtils.BTN_CLR);
        lb.addActionListener(e -> {
            if ("Praneel".equals(user.getText()) && "Praneel@123".equals(new String(pass.getPassword()))) {
                dispose();
                new MainPortal().setVisible(true);
            } else
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
        });
        c.gridx = 1;
        c.gridy = 2;
        fp.add(lb, c);

        m.add(fp, BorderLayout.CENTER);
        add(m);
    }
}

class MainPortal extends JFrame {
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
            case "Register Player":
                new RegisterPlayer().setVisible(true);
                break;
            case "View Players":
                new ViewPlayers().setVisible(true);
                break;
            case "Find Player":
                new FindPlayer().setVisible(true);
                break;
            case "Remove Player":
                new RemovePlayer().setVisible(true);
                break;
            case "Tournament":
                new SportsEventPage().setVisible(true);
                break;
            case "Tournament Schedule":
                new TournamentSchedulePage().setVisible(true);
                break;
            case "SDG Goals":
                new SDGPage().setVisible(true);
                break;
            case "About Us":
                new AboutProjectPage().setVisible(true);
                break;
            case "Points Table":
                new PointsTablePage().setVisible(true);
                break;
            case "Logout":
                new LoginPage().setVisible(true);
                break;
        }
    }
}

class RegisterPlayer extends JFrame {
    JTextField[] t = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };
    JComboBox<String> sp = new JComboBox<>(new String[] { "Cricket", "Football", "Basketball", "Tennis", "Badminton",
            "Volleyball", "Athletics", "Chess", "Table Tennis" });

    public RegisterPlayer() {
        UIUtils.setupFrame(this, "Register Player", 450, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("REGISTER NEW PLAYER"), BorderLayout.NORTH);

        JPanel fp = UIUtils.createPanel(new GridLayout(5, 2, 10, 15));
        fp.setBorder(new EmptyBorder(10, 30, 20, 30));
        String[] lbls = { "ID:", "Name:", "Sport:", "Age:", "Department:" };
        JComponent[] flds = { t[0], t[1], sp, t[2], t[3] };
        for (int i = 0; i < 5; i++) {
            fp.add(UIUtils.createLabel(lbls[i]));
            flds[i].setFont(UIUtils.FONT);
            fp.add(flds[i]);
        }
        m.add(fp, BorderLayout.CENTER);

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        JButton rb = UIUtils.createBtn("Register", Color.decode("#27AE60"));
        rb.addActionListener(e -> {
            if (t[1].getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name empty");
                return;
            }
            try (Connection c = DBConnection.getConnection();
                    PreparedStatement ps = c.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?)")) {
                ps.setInt(1, Integer.parseInt(t[0].getText()));
                ps.setString(2, t[1].getText());
                ps.setString(3, sp.getSelectedItem().toString());
                ps.setInt(4, Integer.parseInt(t[2].getText()));
                ps.setString(5, t[3].getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        bp.add(rb);
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class ViewPlayers extends JFrame {
    public ViewPlayers() {
        UIUtils.setupFrame(this, "View Players", 600, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        DefaultTableModel mod = new DefaultTableModel(new String[] { "ID", "Name", "Sport", "Age", "Dept" }, 0);
        m.add(new JScrollPane(UIUtils.createTable(mod)), BorderLayout.CENTER);

        try (Connection c = DBConnection.getConnection();
                ResultSet rs = c.createStatement().executeQuery("SELECT * FROM players ORDER BY id")) {
            while (rs.next())
                mod.addRow(
                        new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5) });
        } catch (Exception e) {
        }

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class FindPlayer extends JFrame {
    public FindPlayer() {
        UIUtils.setupFrame(this, "Find Player", 600, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        JPanel tp = UIUtils.createPanel(new FlowLayout());
        tp.add(UIUtils.createLabel("ID:"));
        JTextField tf = new JTextField(15);
        tf.setFont(UIUtils.FONT);
        tp.add(tf);
        JButton sb = UIUtils.createBtn("Search", Color.decode("#F39C12"));
        tp.add(sb);
        m.add(tp, BorderLayout.NORTH);

        DefaultTableModel mod = new DefaultTableModel(new String[] { "ID", "Name", "Sport", "Age", "Dept" }, 0);
        m.add(new JScrollPane(UIUtils.createTable(mod)), BorderLayout.CENTER);

        sb.addActionListener(e -> {
            mod.setRowCount(0);
            try (Connection c = DBConnection.getConnection();
                    ResultSet rs = c.createStatement().executeQuery("SELECT * FROM players WHERE id=" + tf.getText())) {
                if (rs.next())
                    mod.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                            rs.getString(5) });
                else
                    JOptionPane.showMessageDialog(this, "Player not found");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        });

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class RemovePlayer extends JFrame {
    public RemovePlayer() {
        UIUtils.setupFrame(this, "Remove Player", 400, 200);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("REMOVE PLAYER"), BorderLayout.NORTH);

        JPanel cp = UIUtils.createPanel(new FlowLayout());
        cp.add(UIUtils.createLabel("ID:"));
        JTextField tf = new JTextField(15);
        tf.setFont(UIUtils.FONT);
        cp.add(tf);
        m.add(cp, BorderLayout.CENTER);

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        JButton rb = UIUtils.createBtn("Remove", Color.decode("#C0392B"));
        rb.addActionListener(e -> {
            try (Connection c = DBConnection.getConnection()) {
                int res = c.createStatement().executeUpdate("DELETE FROM players WHERE id=" + tf.getText());
                JOptionPane.showMessageDialog(this, res > 0 ? "Player removed successfully." : "Player not found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        });
        bp.add(rb);
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class SportsEventPage extends JFrame {
    JTextField[] t = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };

    public SportsEventPage() {
        UIUtils.setupFrame(this, "Tournament Registration", 450, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("TOURNAMENT REGISTRATION"), BorderLayout.NORTH);

        JPanel fp = UIUtils.createPanel(new GridLayout(4, 2, 10, 15));
        fp.setBorder(new EmptyBorder(10, 30, 20, 30));
        String[] lbls = { "Tournament ID:", "Tournament Name:", "Date:", "Venue:" };
        for (int i = 0; i < 4; i++) {
            fp.add(UIUtils.createLabel(lbls[i]));
            t[i].setFont(UIUtils.FONT);
            fp.add(t[i]);
        }
        m.add(fp, BorderLayout.CENTER);

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        JButton rb = UIUtils.createBtn("Register", Color.decode("#27AE60"));
        rb.addActionListener(e -> {
            if (t[1].getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tournament Name cannot be empty");
                return;
            }
            try (Connection c = DBConnection.getConnection();
                    PreparedStatement ps = c.prepareStatement("INSERT INTO tournament VALUES (?,?,?,?)")) {
                ps.setInt(1, Integer.parseInt(t[0].getText()));
                ps.setString(2, t[1].getText());
                ps.setString(3, t[2].getText());
                ps.setString(4, t[3].getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Tournament registered successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        });
        bp.add(rb);
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class TournamentSchedulePage extends JFrame {
    public TournamentSchedulePage() {
        UIUtils.setupFrame(this, "Tournament Schedule", 600, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("UPCOMING TOURNAMENTS"), BorderLayout.NORTH);

        DefaultTableModel mod = new DefaultTableModel(new String[] { "ID", "Tournament Name", "Date", "Venue" }, 0);
        m.add(new JScrollPane(UIUtils.createTable(mod)), BorderLayout.CENTER);

        try (Connection c = DBConnection.getConnection();
                ResultSet rs = c.createStatement().executeQuery("SELECT * FROM tournament ORDER BY date")) {
            while (rs.next())
                mod.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) });
        } catch (Exception e) {
        }

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class PointsTablePage extends JFrame {
    JTextField[] t = { new JTextField(10), new JTextField(5), new JTextField(5), new JTextField(5), new JTextField(5) };

    public PointsTablePage() {
        UIUtils.setupFrame(this, "Points Table", 750, 500);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("TOURNAMENT POINTS TABLE"), BorderLayout.NORTH);

        DefaultTableModel mod = new DefaultTableModel(new String[] { "Team Name", "Played", "Won", "Lost", "Points" },
                0);
        m.add(new JScrollPane(UIUtils.createTable(mod)), BorderLayout.CENTER);

        try (Connection c = DBConnection.getConnection();
                ResultSet rs = c.createStatement().executeQuery("SELECT * FROM points_table ORDER BY points DESC")) {
            while (rs.next())
                mod.addRow(new Object[] { rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5) });
        } catch (Exception e) {
        }

        JPanel ap = UIUtils.createPanel(new FlowLayout());
        String[] lbls = { "Team:", "P:", "W:", "L:", "Pts:" };
        for (int i = 0; i < 5; i++) {
            ap.add(UIUtils.createLabel(lbls[i]));
            ap.add(t[i]);
        }
        JButton ab = UIUtils.createBtn("Add Team", Color.decode("#27AE60"));
        ab.addActionListener(e -> {
            if (t[0].getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Team name cannot be empty!");
                return;
            }
            try (Connection c = DBConnection.getConnection();
                    PreparedStatement ps = c.prepareStatement("INSERT INTO points_table VALUES (?,?,?,?,?)")) {
                ps.setString(1, t[0].getText());
                ps.setInt(2, Integer.parseInt(t[1].getText()));
                ps.setInt(3, Integer.parseInt(t[2].getText()));
                ps.setInt(4, Integer.parseInt(t[3].getText()));
                ps.setInt(5, Integer.parseInt(t[4].getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Team added successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        });
        ap.add(ab);
        JPanel wp = UIUtils.createPanel(new BorderLayout());
        wp.add(ap, BorderLayout.NORTH);
        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        wp.add(bp, BorderLayout.SOUTH);
        m.add(wp, BorderLayout.SOUTH);
        add(m);
    }
}

class SDGPage extends JFrame {
    public SDGPage() {
        UIUtils.setupFrame(this, "Sustainable Development Goals", 600, 400);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("SUSTAINABLE DEVELOPMENT GOALS"), BorderLayout.NORTH);
        JTextArea a = new JTextArea(
                "\nSDG 3: Good Health and Well-being\nSports improve physical and mental health among students, promoting healthy lifestyles.\n\nSDG 4: Quality Education\nSports develop leadership, teamwork, and discipline, supporting holistic education.\n\nOur Sports Management System promotes SDG 3 by encouraging student participation in sports and SDG 4 by fostering crucial soft skills.");
        a.setEditable(false);
        a.setBackground(UIUtils.PRIMARY);
        a.setForeground(UIUtils.TEXT);
        a.setFont(UIUtils.FONT);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setMargin(new Insets(10, 20, 10, 20));
        m.add(a, BorderLayout.CENTER);
        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}

class AboutProjectPage extends JFrame {
    public AboutProjectPage() {
        UIUtils.setupFrame(this, "About Project", 450, 300);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("ABOUT PROJECT"), BorderLayout.NORTH);

        JTextArea a = new JTextArea(
                "\nManaging sports activities manually is time-consuming and error-prone. This project provides a GUI-based Sports Management Portal for managing player records, tournaments, and promoting SDG goals.");
        a.setEditable(false);
        a.setBackground(UIUtils.PRIMARY);
        a.setForeground(UIUtils.TEXT);
        a.setFont(UIUtils.FONT);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setMargin(new Insets(10, 20, 10, 20));
        m.add(a, BorderLayout.CENTER);

        JPanel bp = UIUtils.createPanel(new FlowLayout());
        bp.add(UIUtils.createBackBtn(this));
        m.add(bp, BorderLayout.SOUTH);
        add(m);
    }
}
