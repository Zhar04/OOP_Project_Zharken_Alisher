import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversityGUI extends JFrame {
    private JTextField nameField, ageField, majorField;
    private JButton addButton, updateButton, deleteButton, showAllData;
    private JTextArea outputArea;

    public UniversityGUI() {
        setTitle("University Database Application");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel majorLabel = new JLabel("Major:");
        nameField = new JTextField(15);
        ageField = new JTextField(15);
        majorField = new JTextField(15);
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(ageLabel);
        topPanel.add(ageField);
        topPanel.add(majorLabel);
        topPanel.add(majorField);

        addButton = new JButton("Add Student");
        updateButton = new JButton("Update Student");
        deleteButton = new JButton("Delete Student");
        showAllData = new JButton("Show All Data");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showAllData);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String major = majorField.getText();
                UniversityDatabase.addStudent(name, age, major);
                outputArea.setText("New student added successfully.\n");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String major = majorField.getText();
                UniversityDatabase.updateStudent(name, age, major);
                outputArea.setText("Student information updated successfully.\n");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                UniversityDatabase.deleteStudent(name);
                outputArea.setText("Student deleted successfully.\n");
            }
        });

        showAllData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UniversityDatabase.showTable();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UniversityGUI();
            }
        });
    }
}
