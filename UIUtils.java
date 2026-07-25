import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UIUtils {
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
