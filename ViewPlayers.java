import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewPlayers extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Name", "Sport" }, 0);

    public ViewPlayers() {
        setTitle("View Players");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);

        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(event -> loadPlayers());
        buttonPanel.add(refreshButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> goBack());
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
        loadPlayers();
    }

    private void loadPlayers() {
        model.setRowCount(0);
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, name, sport FROM players ORDER BY id")) {
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("sport") });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new Dashboard().setVisible(true);
    }
}
