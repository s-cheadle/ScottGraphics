package scott_graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This is the canvas for drawing on. The commands the user gives will be displayed on this image.
 */

@SuppressWarnings("serial")
public class Canvas extends JPanel
{

    // Private variable declarations:

    private final Color BACKGROUND = Color.WHITE; // The background colour
    private BufferedImage image; // The canvas image
    private Image cursor; // The pen
    private int x = 100, y = 100; // coordinates of the pen on the canvas
    private int direction = 180;
    private boolean penDown = false; // Is the pen up or down?
    private Color penColour = Color.BLUE;
    private File selectedFile;

    // Methods:

    /**
     * Draw a line of the designated colour from x1y1 to x2y2 onto the canvas
     *
     * @param color
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawLine(Color color, int x1, int y1, int x2, int y2) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
    /**
     * Draw a hollow circle of the designated colour from x1y1 to x2y2 onto the canvas
     *
     * @param color
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawCircle(Color color, int x1, int y1, int x2, int y2)
    {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.drawOval(x1, y1, x2, y2);
    }
    /**
     * Draw a filled circle of the designated colour from x1y1 to x2y2 onto the canvas
     *
     * @param color
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawFillCircle(Color color, int x1, int y1, int x2, int y2)
    {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillOval(x1, y1, x2, y2);
    }

    /**
     * Method to check whether the pen is down.
     * Returns true if pen is down and false if it is up
     */
    public boolean isPenDown()
    {
        if (penDown)
        return true;
        else
        return false;
    }

    /**
     * Set the pen down on the canvas. It's movements will draw on the canvas.
     */
    public void setPenDown(){penDown = true;}

    /**
     * Raise the pen off the canvas. Moving will not draw on the canvas.
     */
    public void setPenUp(){penDown = false;}

    /**
     * Turn the pen 90 degrees to the LEFT
     */
    public void turnLeft()
    {
        direction -= 90;

        if (direction < 0)
        {
            direction = 270;
        }
    }

    /**
     * Turn the pen 90 degrees to the RIGHT
     */
    public void turnRight()
    {
        direction += 90;

        if (direction > 270)
        {
            direction = 0;
        }
    }
    /**
     * Moves the pen FORWARDS by the specified distance.
     * @param distance
     */
    public void forward(int distance)
    {
        switch(direction)
        {
            case 0: // UP
                if (penDown)
                {drawLine(penColour,x,y,x,y - distance);}
                y -= distance;
                break;
            case 90:
                // RIGHT
                if (penDown)
                {drawLine(penColour,x,y,x + distance, y );}
                x += distance;
                break;
            case 180:
                // DOWN
                if (penDown)
                {drawLine(penColour,x,y,x,y + distance);}
                y += distance;
                break;
            case 270:
                // LEFT
                if (penDown)
                {drawLine(penColour,x,y,x - distance,y);}
                x -= distance;
                break;
        }
        repaint();
    }

    /**
     * Moves the pen BACKWARDS by the specified distance
     * @param distance
     */
    public void backward(int distance)
    {
        switch(direction)
        {
            case 0: // UP
                if (penDown)
                {drawLine(penColour,x,y,x,y + distance);}
                y += distance;
                break;
            case 90:
                // RIGHT
                if (penDown)
                {drawLine(penColour,x,y,x - distance, y );}
                x -= distance;
                break;
            case 180:
                // DOWN
                if (penDown)
                { drawLine(penColour,x,y,x,y - distance);}
                y -= distance;
                break;
            case 270:
                // LEFT
                if (penDown)
                {drawLine(penColour,x,y,x + distance,y);}
                x += distance;
                break;
        }
        repaint();
    }

    public void circle(int radius)
    {
        if (penDown)
        {
            drawCircle(penColour,(x-radius),(y-radius),radius,radius);
            repaint();
        }
    }

    public void fillCircle(int radius)
    {
        if (penDown)
        {
            drawFillCircle(penColour,(x -radius),(y - radius),radius,radius);
            repaint();
        }
    }

    /**
     * Changes the colour of the pen
     * @param colour
     */
    public void setColour(String colour)
    {
        switch (colour)
        {
            case "black":
                penColour = Color.BLACK;
                break;
            case "white":
                penColour = Color.WHITE;
                break;
            case "blue":
                penColour = Color.BLUE;
                break;
            case "red":
                penColour = Color.RED;
                break;
            case "green":
                penColour = Color.GREEN;
                break;
            case "yellow":
                penColour = Color.yellow;
                break;
            default:
                penColour = Color.BLACK;
                break;
        }
    }

    /**
     * Saves the canvas
     * @throws IOException
     */
    public void save() throws IOException
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        int ret = chooser.showSaveDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = chooser.getSelectedFile();
            String absolutePath = selectedFile.getAbsolutePath();
            ImageIO.write(image,"png",new File(absolutePath + ".png" ));
        }
    }

    /**
     * Loads an image to the canvas
     * @throws IOException
     */
    public void load() throws IOException {
        JFileChooser chooser = new JFileChooser();

        int ret = chooser.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = chooser.getSelectedFile().getAbsoluteFile();
            image = ImageIO.read(selectedFile);
            repaint();
        }
    }


    /**
     * Clears the image contents.
     */
    public void clear()
    {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);

        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        x = 100;
        y = 100;
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        // render the image on the panel.
        g.drawImage(image, 0, 0, null);
        // Draw cursor
        g.drawImage(cursor,x - cursor.getWidth(null),y - cursor.getHeight(null),this);
    }


    /**
     * Constructor
     */
    public Canvas()
    {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(800, 400));
        cursor = new ImageIcon("images/cursor.png").getImage();
        image = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);

        // Set max size of the panel, so that is matches the max size of the image.
        setMaximumSize(new Dimension(image.getWidth(), image.getHeight()));
        clear();
    }
}


