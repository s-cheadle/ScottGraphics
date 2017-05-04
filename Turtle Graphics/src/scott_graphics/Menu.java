package scott_graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Menu extends JMenuBar
{
    private JMenu file, help;
    private JMenuItem newFile, load, save, exit, about;
    private static boolean hasSaved = false;

    public Menu()
    {
        // Build the file menu.
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        add(file);

        // the "new" menu item
        ImageIcon newIcon = new ImageIcon("images/new.png");
        newFile = new JMenuItem("New",newIcon);
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        newFile.getAccessibleContext().setAccessibleDescription("Creates a new canvas.");
        newFile.addActionListener(new GraphicsMenuListener());
        file.add(newFile);

        file.addSeparator();

        // the "save" menu item
        ImageIcon saveIcon = new ImageIcon("images/save.png");
        save = new JMenuItem("Save", saveIcon);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        save.getAccessibleContext().setAccessibleDescription("Saves the canvas as it is.");
        save.addActionListener(new GraphicsMenuListener());
        file.add(save);

        // the "load" menu item
        ImageIcon loadIcon = new ImageIcon("images/load.png");
        load = new JMenuItem("Load", loadIcon);
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
        load.getAccessibleContext().setAccessibleDescription("Loads a previously saved canvas.");
        load.addActionListener(new GraphicsMenuListener());
        file.add(load);

        file.addSeparator();

        // the "exit" menu item
        ImageIcon exitIcon = new ImageIcon("images/exit.png");
        exit = new JMenuItem("Exit", exitIcon);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        exit.getAccessibleContext().setAccessibleDescription("Close the program.");
        exit.addActionListener(new GraphicsMenuListener());
        file.add(exit);

        // Help menu
        help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        add(help);

        // About
        about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        about.addActionListener(new GraphicsMenuListener());
        help.add(about);
    }

    public void saved()
    {
        boolean hasSaved = true;
    }

    public static void changesMade()
    {
        boolean hasSaved = false;
    }

    public static int getSaveState()
    {
        if (hasSaved)
            return 1;
        else
            return 0;
    }

    private class GraphicsMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == newFile)
            {
                int confirm = JOptionPane.showConfirmDialog(
                        Menu.this,
                        "New canvas?",
                        "New",
                        JOptionPane.OK_CANCEL_OPTION);

                if (confirm == JOptionPane.OK_OPTION)
                {
                    if (hasSaved)
                    {
                        Main.getCanvas().clear();
                        changesMade();
                    } else
                    {
                        int confirm2 = JOptionPane.showConfirmDialog(
                                Menu.this,
                                "Would you like to save your canvas?",
                                "You have not saved yet!",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (confirm2 == JOptionPane.YES_OPTION)
                        {
                            // Do save
                            try {
                                Main.getCanvas().save();
                                saved();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                Main.getCanvas().clear();
                                changesMade();
                            }
                        }else if (confirm2 == JOptionPane.NO_OPTION)
                        {
                            Main.getCanvas().clear();
                            changesMade();
                        }

                    }
                }
            } else if (event.getSource() == load)
            {
               int confirm = JOptionPane.showConfirmDialog(
                        Menu.this,
                        "Load canvas?",
                        "Load",
                        JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION && hasSaved)
                {
                    try {
                        Main.getCanvas().load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        changesMade();
                    }
                } else if (confirm == JOptionPane.OK_OPTION && !hasSaved)
                {
                    int confirm2 = JOptionPane.showConfirmDialog(
                            Menu.this,
                            "Would you like to save your canvas?",
                            "You have not saved yet!",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (confirm2 == JOptionPane.YES_OPTION)
                    {
                        // Do save
                        try {
                            Main.getCanvas().save();
                            saved();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                Main.getCanvas().load();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                changesMade();
                            }
                        }
                    }else if (confirm2 == JOptionPane.NO_OPTION)
                    {
                        try {
                            Main.getCanvas().load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            changesMade();
                        }
                    }

                }
            } else if (event.getSource() == save)
            {
               int confirm = JOptionPane.showConfirmDialog(
                        Menu.this,
                        "Save canvas?",
                        "Save",
                        JOptionPane.OK_CANCEL_OPTION);

                if (confirm == JOptionPane.OK_OPTION)
                {
                    try
                    {
                        Main.getCanvas().save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        saved();
                    }
                }

            } else if (event.getSource() == exit)
            {
                int confirm = JOptionPane.showConfirmDialog(
                        Menu.this,
                        "Exit?",
                        "Exit Scott Graphics",
                        JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION && hasSaved)
                {
                    Main.exit();
                }else if (confirm == JOptionPane.OK_OPTION && !hasSaved)
                {
                    int confirm2 = JOptionPane.showConfirmDialog(
                            Menu.this,
                            "Would you like to save your canvas?",
                            "You have not saved yet!",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (confirm2 == JOptionPane.YES_OPTION)
                    {
                        // Do save
                        try
                        {
                            Main.getCanvas().save();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        } finally
                        {
                                Main.exit();
                        }
                    }else if (confirm2 == JOptionPane.NO_OPTION)
                    {
                        Main.exit();
                    }
                }
        } else if (event.getSource() == about)
            {
                String input = "";
                // Set up a reader to display the file
                try{BufferedReader reader = new BufferedReader(new FileReader("about.txt"));
                String line;

                // Take each line...
                while ((line = reader.readLine()) != null)
                {
                    //...and add it to the string.
                    input += line + "\n";
                }
                reader.close();
                // Then display the string in the pop up
                JOptionPane.showInternalMessageDialog(Menu.this,input,"About Scott Graphics",JOptionPane.INFORMATION_MESSAGE);}
                catch (FileNotFoundException fnf){} catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
