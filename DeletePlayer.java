import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeletePlayer extends JFrame {

    private final JTextField idField = new JTextField(10);

    public DeletePlayer() {
        setTitle("Delete Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 160);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Player ID:"));
        panel.add(idField);
        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(event -> deletePlayer());
        panel.add(deleteButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> goBack());
        panel.add(backButton);
        add(panel);
    }

    private void deletePlayer() {
        String sql = "DELETE FROM players WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(idField.getText().trim()));
            int deletedRows = statement.executeUpdate();
            JOptionPane.showMessageDialog(this, deletedRows == 0 ? "Player not found." : "Player deleted successfully.");
            idField.setText("");
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
