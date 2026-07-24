import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TournamentPage extends JFrame {

    public TournamentPage() {
        setTitle("Tournament Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 320);
        setLocationRelativeTo(null);

        JTextArea information = new JTextArea();
        information.setEditable(false);
        information.setText("Tournament Management\n\nAdd tournament details here.\nThis module is ready for tournament scheduling and player registration.");
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
