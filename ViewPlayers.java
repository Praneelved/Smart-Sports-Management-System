import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewPlayers extends JFrame {
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
