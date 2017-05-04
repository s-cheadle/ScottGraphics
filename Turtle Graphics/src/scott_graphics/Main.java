package scott_graphics;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 *  Scott Cheadle - C3469518
 *  Object Oriented Programming - Leeds Beckett University
 */
public class Main
{
    private static Canvas canvas;
    private static StatusBar statusBar;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("(: Scott's Graphics :)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel contentPane = new JPanel();
                contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
                frame.setContentPane(contentPane);

                setStatusBar(new StatusBar());
                contentPane.add(getStatusBar());

                setCanvas(new Canvas());
                contentPane.add(getCanvas());

                Input input = new Input();
                JScrollPane scroll = new JScrollPane(input);
                scroll.setBounds(input.getBounds());
                contentPane.add(scroll);

                contentPane.setMinimumSize(getCanvas().getSize());

                // Add the menu bar
                Menu menuBar = new Menu();
                frame.setJMenuBar(menuBar);

                frame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        if (Menu.getSaveState() == 0)
                        {
                            int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Unsaved changes will be lost!",
                                    "Save Changes before exit?",
                                    JOptionPane.YES_NO_OPTION);

                            if (confirm == JOptionPane.YES_OPTION)
                            {
                                try {
                                    getCanvas().save();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });

                frame.pack();
                frame.setVisible(true);
            }
        });

    }

    protected static void exit()
    {
        System.exit(0);
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Main.canvas = canvas;
    }

    public static StatusBar getStatusBar() {
        return statusBar;
    }

    public static void setStatusBar(StatusBar statusBar) {
        Main.statusBar = statusBar;
    }

    // KEEP THESE FOR LATER Try to use this code instead of that above

    /* private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MenuDemo demo = new MenuDemo();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    // THIS MIGHT ALSO BE OF USE

    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    */
}
