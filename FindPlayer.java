import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FindPlayer extends JFrame {
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
