import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class RemovePlayer extends JFrame {
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
