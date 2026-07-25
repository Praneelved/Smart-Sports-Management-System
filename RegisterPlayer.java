import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterPlayer extends JFrame {
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
