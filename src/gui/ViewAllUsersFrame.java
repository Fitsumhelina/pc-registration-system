package gui;

import java.awt.*;
import javax.swing.*;

public class ViewAllUsersFrame extends JFrame {
    public ViewAllUsersFrame() {
        setTitle("View All Users");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JLabel message = new JLabel("All registered users will be displayed here.");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
