package code_alpha_project;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIGradeTracker extends JFrame {
    private ArrayList<Integer> grades = new ArrayList<>();
    private JTextArea displayArea;

    public GUIGradeTracker() {
        setTitle("Student Grade Tracker");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea("Welcome to the Grade Tracker!\n\n");
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Grade");
        JButton reportButton = new JButton("Generate Report");
        
        buttonPanel.add(addButton);
        buttonPanel.add(reportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter student grade (0-100):");
            try {
                if (input != null) {
                    int grade = Integer.parseInt(input);
                    if (grade >= 0 && grade <= 100) {
                        grades.add(grade);
                        displayArea.append("Added Grade: " + grade + "\n");
                    } else {
                        JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        });

        reportButton.addActionListener(e -> {
            if (grades.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No grades entered yet.");
                return;
            }
            int total = 0, highest = grades.get(0), lowest = grades.get(0);
            for (int g : grades) {
                total += g;
                if (g > highest) highest = g;
                if (g < lowest) lowest = g;
            }
            double avg = (double) total / grades.size();
            displayArea.append("\n--- SUMMARY REPORT ---\n");
            displayArea.append(String.format("Total Students: %d\nHighest: %d\nLowest: %d\nAverage: %.2f\n\n", 
                                             grades.size(), highest, lowest, avg));
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIGradeTracker().setVisible(true));
    }
}