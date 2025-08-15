import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TaskComponent extends JPanel implements ActionListener {

    private JTextPane taskField;
    private JCheckBox checkBox;
    private JButton deleteButton;

    private JPanel parentPanel;

    public JTextPane getTaskField() {
        return taskField;
    }

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setPreferredSize(new Dimension(CommonConstants.TASKPANNEL_SIZE.width, 60));
        setOpaque(false);

        // Task field
        taskField = new JTextPane();
        taskField.setContentType("text/html");
        taskField.setText("<html><body style='color:black;'> </body></html>");
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setMinimumSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setMaximumSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setOpaque(true);
        taskField.setBackground(Color.WHITE);

        // Add focus listener to highlight field
        taskField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.LIGHT_GRAY); // Highlight when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(Color.WHITE); // Back to white when not focused
            }
        });

        // Checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);

        // Delete Button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BOX_SIZE);
        deleteButton.addActionListener(this);

        // Add all components to panel
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == checkBox) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", " ");
            if (checkBox.isSelected()) {
                taskField.setText("<html><body style='color:black;'><s>" + taskText + "</s></body></html>");
            } else {
                taskField.setText("<html><body style='color:black;'>" + taskText + "</body></html>");
            }
        }

        if (source == deleteButton) {
            parentPanel.remove(this);
            parentPanel.revalidate();
            parentPanel.repaint();
        }
    }
}
