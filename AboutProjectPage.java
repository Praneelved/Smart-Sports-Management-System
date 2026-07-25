import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AboutProjectPage extends JFrame {
    
    JButton backButton;
    
    public AboutProjectPage() {
        setTitle("About Project");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = Color.decode("#2C3E50");
        Color backBtnColor = Color.decode("#E74C3C");
        Color textColor = Color.WHITE;
        Font font = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel("ABOUT PROJECT", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String text = "\nManaging sports activities manually is time-consuming and error-prone. This project provides a GUI-based Sports Management Portal for managing player records, tournaments, and promoting SDG goals.";
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setBackground(primaryColor);
        textArea.setForeground(textColor);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 20, 10, 20));

        mainPanel.add(textArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(primaryColor);

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(backBtnColor);
        backButton.setForeground(textColor);
        backButton.setFocusPainted(false);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPortal page = new MainPortal();
                page.setVisible(true);
            }
        });

        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
