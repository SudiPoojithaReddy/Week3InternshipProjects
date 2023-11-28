import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerGUI extends JFrame {
    private List<Expense> expenses;
    private JTextField descriptionField, amountField;
    private DefaultListModel<String> expenseListModel;

    public ExpenseTrackerGUI() {
        expenses = new ArrayList<>();
        expenseListModel = new DefaultListModel<>();

        // Set up the main window
        setTitle("Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        descriptionField = new JTextField();
        amountField = new JTextField();
        JButton addButton = new JButton("Add Expense");
        JList<String> expenseList = new JList<>(expenseListModel);

        // Add components to the main window
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);

        add(inputPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.CENTER);
        add(new JScrollPane(expenseList), BorderLayout.SOUTH);

        // Add action listener for the "Add Expense" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
    }

    private void addExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());

        Expense expense = new Expense(description, amount);
        expenses.add(expense);

        // Update the list model
        expenseListModel.addElement(expense.toString());

        // Clear input fields
        descriptionField.setText("");
        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExpenseTrackerGUI expenseTracker = new ExpenseTrackerGUI();
            expenseTracker.setVisible(true);
        });
    }
}

class Expense {
    private String description;
    private double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description + ": Rs " + amount;
    }
}
