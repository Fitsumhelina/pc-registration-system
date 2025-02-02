package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.FileManager;

public class RegisterPCFrame extends JFrame {
    private JTextField studentIDField, pcModelField, macAddressField;
    private JButton saveButton;

    public RegisterPCFrame() {
        setTitle("Register Student PC");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        add(studentIDField);

        add(new JLabel("PC Model:"));
        pcModelField = new JTextField();
        add(pcModelField);

        add(new JLabel("MAC Address:"));
        macAddressField = new JTextField();
        add(macAddressField);

        saveButton = new JButton("Save");
        add(saveButton);

        saveButton.addActionListener(e -> {
            String studentID = studentIDField.getText();
            String pcModel = pcModelField.getText();
            String macAddress = macAddressField.getText();

            if (studentID.isEmpty() || pcModel.isEmpty() || macAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
            } else {
                FileManager.saveData("data/students_pc.txt", studentID + "," + pcModel + "," + macAddress);
                JOptionPane.showMessageDialog(null, "PC Registered Successfully!");
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
