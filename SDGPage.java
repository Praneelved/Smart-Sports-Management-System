import java.awt.*;
import javax.swing.*;

public class SDGPage extends JFrame {
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
