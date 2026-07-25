import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PointsTablePage extends JFrame {
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
