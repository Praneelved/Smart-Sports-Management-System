import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlayer extends JFrame {

    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField sportField = new JTextField();

    public AddPlayer() {
        setTitle("Add Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Sport:"));
        panel.add(sportField);

        JButton addButton = new JButton("ADD");
        addButton.addActionListener(event -> addPlayer());
        panel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> goBack());
        panel.add(backButton);
        add(panel);
    }

    private void addPlayer() {
        String sql = "INSERT INTO players (id, name, sport) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(idField.getText().trim()));
            statement.setString(2, nameField.getText().trim());
            statement.setString(3, sportField.getText().trim());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Player added successfully.");
            idField.setText("");
            nameField.setText("");
            sportField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new Dashboard().setVisible(true);
    }
}
