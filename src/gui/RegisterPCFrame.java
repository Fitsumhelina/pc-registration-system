package gui;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.FileManager;

public class RegisterPCFrame extends JFrame {
    private JTextField studentNameField, studentIDField, departmentField, pcModelField, macAddressField;
    private final JButton saveButton;

    public RegisterPCFrame() {
        setTitle("Register Student PC");
        setSize(400, 300); // Increased size for better UI
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2)); // Change to 6 rows to fit all fields

        add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        add(studentNameField);

        add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        add(studentIDField);
        
        add(new JLabel("Department:"));
        departmentField = new JTextField();
        add(departmentField);

        add(new JLabel("PC Model:"));
        pcModelField = new JTextField();
        add(pcModelField);

        add(new JLabel("MAC Address:"));
        macAddressField = new JTextField();
        add(macAddressField);

        saveButton = new JButton("Save");
        add(saveButton);

        // Empty label to balance the layout
        add(new JLabel());

        saveButton.addActionListener(e -> {
            String studentName = studentNameField.getText();
            String studentID = studentIDField.getText();
            String studentDepartment = departmentField.getText();
            String pcModel = pcModelField.getText();
            String macAddress = macAddressField.getText();

            if (studentName.isEmpty() || studentID.isEmpty() || studentDepartment.isEmpty() || pcModel.isEmpty() || macAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
            } else {
                FileManager.writeToFile("students_pc.txt", studentName + "," + studentID + "," + studentDepartment + "," + pcModel + "," + macAddress);
                JOptionPane.showMessageDialog(null, "PC Registered Successfully!");
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
