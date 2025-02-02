package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SuperAdminFrame extends JFrame {
    private JButton createAdminBtn, updateAdminBtn, deleteAdminBtn, viewAdminsBtn;

    public SuperAdminFrame() {
        setTitle("Super Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        createAdminBtn = new JButton("Create Admin");
        updateAdminBtn = new JButton("Update Admin");
        deleteAdminBtn = new JButton("Delete Admin");
        viewAdminsBtn = new JButton("View Admins");

        add(createAdminBtn);
        add(updateAdminBtn);
        add(deleteAdminBtn);
        add(viewAdminsBtn);

        createAdminBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "Create Admin Feature"));
        updateAdminBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "Update Admin Feature"));
        deleteAdminBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "Delete Admin Feature"));
        viewAdminsBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, "View Admins Feature"));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
