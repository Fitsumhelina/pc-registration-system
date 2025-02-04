package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UpdateUserFrame extends JFrame {
    public UpdateUserFrame() {
        setTitle("Update User");
        setSize(300, 200);
        setLayout(new BorderLayout());

        JLabel message = new JLabel("Update user functionality will be implemented.");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
