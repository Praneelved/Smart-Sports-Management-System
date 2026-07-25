import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TournamentSchedulePage extends JFrame {
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
