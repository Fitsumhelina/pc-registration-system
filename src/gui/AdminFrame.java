package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AdminFrame extends JFrame {
    private JButton registerPCBtn, updatePCBtn, deletePCBtn, viewPCsBtn;

    public AdminFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        registerPCBtn = new JButton("Register PC");
        updatePCBtn = new JButton("Update PC");
        deletePCBtn = new JButton("Delete PC");
        viewPCsBtn = new JButton("View Registered PCs");

        add(registerPCBtn);
        add(updatePCBtn);
        add(deletePCBtn);
        add(viewPCsBtn);

        registerPCBtn.addActionListener(e -> new RegisterPCFrame());
        updatePCBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "Update PC Feature"));
        deletePCBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "Delete PC Feature"));
        viewPCsBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "View PCs Feature"));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
