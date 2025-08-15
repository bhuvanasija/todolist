import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ToDoListGUI extends JFrame implements ActionListener {
    // taskpanel will act as a container and taskcomponent will store all the task
    // component
    private JPanel taskPanel, taskComponetPanel;

    public ToDoListGUI() {
        super("To Do List Application");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        addGUIComponentS();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

    };

    private void addGUIComponentS() {
        // BANNER TEXT

        JLabel bannerLabel = new JLabel("To Do List ");
        bannerLabel.setFont(createFont("resources\\LEMONMILK-Light.otf", 48f));
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2,
                20,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height);

        // takspanel
        taskPanel = new JPanel();

        // takscomponentpanel
        taskComponetPanel = new JPanel();
        taskComponetPanel.setLayout(new BoxLayout(taskComponetPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponetPanel);

        // add scrolling to the task
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(10, 70, CommonConstants.TASKPANNEL_SIZE.width, CommonConstants.TASKPANNEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonConstants.TASKPANNEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // changing speed of scrollbar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        // add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setActionCommand("ADD TASK");
        addTaskButton.setBounds(0, CommonConstants.GUI_SIZE.height - 88, CommonConstants.ADDTASK_BUTTON_SIZE.width, 30);
        addTaskButton.setFont(createFont("resources\\LEMONMILK-Light.otf", 26f));
        addTaskButton.addActionListener(this);

        // add to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);

    }

    private Font createFont(String resourse, float size) {
        // get font file path
        String filePath = getClass().getClassLoader().getResource(resourse).getPath();

        // check if file path contain spaces
        if (filePath.contains("%20")) {
            filePath = getClass().getClassLoader().getResource(resourse).getPath().replaceAll("%20", " ");

        }

        // try font

        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("ADD TASK")) {
            // CREATE A TASK COMPONENT
            TaskComponent taskComponent = new TaskComponent(taskComponetPanel);
            taskComponetPanel.add(taskComponent);

            // make previous task appear disabled
            if (taskComponetPanel.getComponentCount() > 1) {

                TaskComponent previousTask = (TaskComponent) taskComponetPanel.getComponent(
                        taskComponetPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);

            }
            ;

            // make a task field request after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();

        }

    };

}
