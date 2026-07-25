import java.awt.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    JTextField user = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);

    public LoginPage() {
        UIUtils.setupFrame(this, "Login", 450, 300);
        JPanel m = UIUtils.createPanel(new BorderLayout());
        m.add(UIUtils.createTitle("SPORTS MANAGEMENT SYSTEM"), BorderLayout.NORTH);

        JPanel fp = UIUtils.createPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        fp.add(UIUtils.createLabel("Username:"), c);
        c.gridx = 1; user.setFont(UIUtils.FONT);
        fp.add(user, c);
        
        c.gridx = 0; c.gridy = 1;
        fp.add(UIUtils.createLabel("Password:"), c);
        c.gridx = 1; pass.setFont(UIUtils.FONT);
        fp.add(pass, c);

        JButton lb = UIUtils.createBtn("LOGIN", UIUtils.BTN_CLR);
        lb.addActionListener(e -> {
            if ("Praneel".equals(user.getText()) && "Praneel@123".equals(new String(pass.getPassword()))) {
                dispose();
                new MainPortal().setVisible(true);
            } else
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
        });
        c.gridx = 1; c.gridy = 2;
        fp.add(lb, c);

        m.add(fp, BorderLayout.CENTER);
        add(m);
    }
}
