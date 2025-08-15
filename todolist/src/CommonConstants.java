import java.awt.Dimension;

public class CommonConstants {
    // Frame configuration
    public static final Dimension GUI_SIZE = new Dimension(540, 760);

    // banner config

    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 40);

    // TASK PANEL CONFIG
    public static final Dimension TASKPANNEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    // add task button config
    public static final Dimension ADDTASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 40);

    // ADD TASKCOMPONENT CONFIG
    public static final Dimension TASKFIELD_SIZE = new Dimension((int) (TASKPANNEL_SIZE.width * 0.70), 50);

    // CHECKBOX CONFIG
    public static final Dimension CHECKBOX_SIZE = new Dimension((int) (TASKFIELD_SIZE.width * 0.05), 50);

    // DELETE BUTTON
    public static final Dimension DELETE_BOX_SIZE = new Dimension((int) (TASKPANNEL_SIZE.width * 0.12), 50);
}
