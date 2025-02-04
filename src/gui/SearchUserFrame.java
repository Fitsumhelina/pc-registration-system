package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SearchUserFrame extends JFrame {
    public SearchUserFrame() {
        setTitle("Search User");
        setSize(300, 200);
        setLayout(new BorderLayout());

        JLabel message = new JLabel("Search user functionality will be implemented.");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
