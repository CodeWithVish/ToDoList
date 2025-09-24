import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        done = true;
    }

    public String getDescription() {
        return description + (done ? " [Done]" : "");
    }

    public boolean isDone() {
        return done;
    }
}

public class ToDoList extends JFrame implements ActionListener {
    private ArrayList<Task> tasks = new ArrayList<>();
    private JTextField taskField;
    private JTextArea taskArea;
    private JButton addBtn, removeBtn, doneBtn;

    public ToDoList() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        taskField = new JTextField(20);
        add(taskField);

        addBtn = new JButton("Add Task");
        removeBtn = new JButton("Remove Task");
        doneBtn = new JButton("Mark Done");

        add(addBtn);
        add(removeBtn);
        add(doneBtn);

        taskArea = new JTextArea(15, 30);
        taskArea.setEditable(false);
        add(new JScrollPane(taskArea));

        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        doneBtn.addActionListener(this);

        setVisible(true);
    }

    private void updateTaskArea() {
        taskArea.setText("");
        for (int i = 0; i < tasks.size(); i++) {
            taskArea.append((i + 1) + ". " + tasks.get(i).getDescription() + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String desc = taskField.getText();
            if (!desc.isEmpty()) {
                tasks.add(new Task(desc));
                taskField.setText("");
                updateTaskArea();
            }
        } else if (e.getSource() == removeBtn) {
            String input = JOptionPane.showInputDialog("Enter task number to remove:");
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.remove(index);
                    updateTaskArea();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getSource() == doneBtn) {
            String input = JOptionPane.showInputDialog("Enter task number to mark done:");
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markDone();
                    updateTaskArea();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
