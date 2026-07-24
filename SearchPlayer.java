import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchPlayer extends JFrame {

    private final JTextField idField = new JTextField(8);
    private final DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Name", "Sport" }, 0);

    public SearchPlayer() {
        setTitle("Search Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Player ID:"));
        searchPanel.add(idField);
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(event -> searchPlayer());
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> goBack());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void searchPlayer() {
        model.setRowCount(0);
        String sql = "SELECT id, name, sport FROM players WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(idField.getText().trim()));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    model.addRow(new Object[] { resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("sport") });
                } else {
                    JOptionPane.showMessageDialog(this, "Player not found.");
                }
            }
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
