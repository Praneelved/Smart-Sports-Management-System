import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class SDGPage extends JFrame {

    public SDGPage() {
        setTitle("Sustainable Development Goals");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);

        JTextArea information = new JTextArea();
        information.setEditable(false);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);
        information.setText("SUSTAINABLE DEVELOPMENT GOALS\n\n"
                + "SDG 3: Good Health and Well-being\n\n"
                + "Sports improve physical and mental health among students.\n\n"
                + "SDG 4: Quality Education\n\n"
                + "Sports develop leadership, teamwork and discipline.\n\n"
                + "Our Sports Management System promotes SDG 3 by encouraging student participation in sports "
                + "and SDG 4 by supporting holistic education through extracurricular activities.");
        add(new JScrollPane(information), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
