package scott_graphics;

import javax.swing.*;
import java.awt.*;

/**
 *  Status bar to display actions and indicate changes in the program
 */class StatusBar extends JPanel
{
    private static JLabel status;
    private static JLabel penLabel;
    private static String penStatus = "Pen = up";
    public StatusBar()
    {
        setLayout(new BorderLayout(5, 5));
        status = new JLabel("Status: ");
        setPreferredSize(new Dimension(400,16));
        setMaximumSize(getPreferredSize());
        status.setBorder(BorderFactory.createLoweredBevelBorder());
        status.setForeground(Color.black);
        add(BorderLayout.CENTER, status);
        penLabel = new JLabel(penStatus);
        penLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        add(BorderLayout.EAST, penLabel);
    }
    public static void setStatus(String stat)
    {
        status.setText(stat);
    }

    public static void setPenStatus(boolean penDown)
    {
        if (penDown)
        {
            penStatus = "Pen is down";
            penLabel.setText(penStatus);
        }
        else
        {
            penStatus = "Pen is up";
            penLabel.setText(penStatus);
        }

    }


}