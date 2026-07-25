import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SportsEventPage extends JFrame {
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
