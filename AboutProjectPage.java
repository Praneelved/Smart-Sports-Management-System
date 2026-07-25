import java.awt.*;
import javax.swing.*;

public class AboutProjectPage extends JFrame {
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
