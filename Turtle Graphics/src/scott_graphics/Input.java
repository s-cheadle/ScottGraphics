package scott_graphics;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is the text field for user input.
 */
public class Input extends JTextArea
{
    private int distance;

    public Input()
    {
        setLineWrap(true);
        setBorder(BorderFactory.createLoweredBevelBorder());
        setRows(5);
        setColumns(5);
        setMaximumSize(getPreferredSize());
        setEditable(true);
        setVisible(true);
        setFocusable(true);
        addKeyListener(new CommandListener());

    }
    private class CommandListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                System.out.println("Enter pressed"); // test

                // get the commands
                String split[];
                // regex for strings and strings with integers
                split = getText().toLowerCase().split("[a-z]+\\s?(\\d*)\n");
                // get the last entered command
                String command = split[split.length - 1].trim().toLowerCase();

                // execute commands
                // Penup
                if (command.equals("penup"))
                {
                    Main.getCanvas().setPenUp();
                    Main.getStatusBar().setStatus("Pen is up!");
                }
                // pendown
                else if (command.equals("pendown"))
                {
                    Main.getCanvas().setPenDown();
                    Main.getStatusBar().setStatus("Pen is down!");
                }
                //turnleft
                else if (command.equals("turnleft"))
                {
                    Main.getCanvas().turnLeft();
                    Main.getStatusBar().setStatus("Turned left!");
                }
                // turnright
                else if (command.equals("turnright"))
                {
                    Main.getCanvas().turnRight();
                    Main.getStatusBar().setStatus("Turned right!");
                }
                // forward
                else if (command.contains("forward"))
                {
                    String splitAgain[] = command.split("\\s+"); // separate the command from distance
                    if (splitAgain.length == 2)
                    {
                        try
                        {
                            distance = Integer.parseInt(splitAgain[1]);
                        }
                        catch (NumberFormatException nfe) {
                            System.err.print(nfe);
                            Main.getStatusBar().setStatus("Error: Was that a whole number?");
                        }
                        finally
                        {
                            Main.getCanvas().forward(distance);
                            Main.getStatusBar().setStatus("Pen moved forward " + distance);
                        }
                    }
                    else
                    {
                        Main.getStatusBar().setStatus("Error: Did you specify a <distance>?");
                    }
                }
                // backward
                else if (command.contains("backward"))
                {
                    String splitAgain[] = command.split("\\s+"); // separate the command from distance
                    if (splitAgain.length == 2)
                    {
                        try
                        {
                            distance = Integer.parseInt(splitAgain[1]);
                        } catch (NumberFormatException nfe)
                        {
                            System.err.print(nfe);
                            Main.getStatusBar().setStatus("Error: Was that a whole number?");
                        } finally
                        {
                            Main.getStatusBar().setStatus("Moved backwards " + distance);
                            Main.getCanvas().backward(distance);
                        }
                    }
                    else
                    {
                        Main.getStatusBar().setStatus("Error: Did you specify a <distance>?");
                        // do nothing if no distance is recorded
                    }
                }
                // circle
                else if (command.contains("circle"))
                {
                    // separate the command from distance
                    String splitAgain[] = command.split("\\s+");
                    if (splitAgain.length == 2) {
                        try {
                            distance = Integer.parseInt(splitAgain[1]);
                        } catch (NumberFormatException nfe) {
                            System.err.print(nfe);
                            Main.getStatusBar().setStatus("Error: Was that a whole number?");
                        } finally {
                            if (command.contains("fill")) {
                                Main.getStatusBar().setStatus("Fill circle! Size: " + distance);
                                Main.getCanvas().fillCircle(distance);
                            } else {
                                Main.getStatusBar().setStatus("Circle! Size: " + distance);
                                Main.getCanvas().circle(distance);
                            }
                        }
                    }
                }
                    // colours
                    else if (command.equals("black"))
                    {
                        Main.getCanvas().setColour("black");
                        Main.getStatusBar().setStatus("Now we're using a black pen!");
                    }
                    else if (command.equals("green"))
                    {
                        Main.getCanvas().setColour("green");
                        Main.getStatusBar().setStatus("Now we're using a green pen!");
                    }
                    else if (command.equals("red"))
                    {
                        Main.getCanvas().setColour("red");
                        Main.getStatusBar().setStatus("Now we're using a red pen!");
                    }
                    else if (command.equals("white"))
                    {
                        Main.getCanvas().setColour("white");
                        Main.getStatusBar().setStatus("Now we're using a white pen!");
                    }
                    else if (command.equals("blue"))
                    {
                        Main.getCanvas().setColour("blue");
                        Main.getStatusBar().setStatus("Now we're using a blue pen!");
                    }
                    else if (command.equals("yellow"))
                    {
                        Main.getCanvas().setColour("yellow");
                        Main.getStatusBar().setStatus("Now we're using a yellow pen!");
                    }
                    // reset
                    else if (command.equals("reset"))
                    {
                        if (Menu.getSaveState() == 1)
                        {
                            Main.getCanvas().clear();
                        }
                        else
                        {
                            int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Unsaved Changes will be lost.",
                                    "Confirm reset",
                                    JOptionPane.OK_CANCEL_OPTION);

                            if (confirm == JOptionPane.OK_OPTION)
                            {
                                Main.getCanvas().clear();
                            }
                        }
                    }
                    // end!
                    Menu.changesMade();

                }

            }


        @Override
        public void keyReleased (KeyEvent e)
        {
            StatusBar.setPenStatus(Main.getCanvas().isPenDown());
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                setText(null);
        }


    }
}


