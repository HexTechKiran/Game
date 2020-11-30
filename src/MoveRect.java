import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class MoveRect {
    private int x = 40;
    private int y = 60;
    private int dx;
    private int dy;
    private int w;
    private int h;
    private int mapW;
    private int mapH;
    private RoundRectangle2D rect;

    public MoveRect(Dimension dim) {
        createRectangle(10, 10, dim);
        w = 10;
        h = 10;
    }

    public MoveRect(int width, int height, Dimension dim) {
        createRectangle(width, height, dim);
        w = width;
        h = height;
    }

    public void createRectangle(int width, int height, Dimension dim) {
        RoundRectangle2D r = new RoundRectangle2D.Double(x, y, width, height, 3, 3);
        w = width;
        h = height;
        mapW = (int) dim.getWidth();
        mapH = (int) dim.getHeight();
        rect = r;
    }

    public void move() {
        if ((x + dx) > 1 && (x + w + dx + 2) < mapW) {
            x += dx;
        }
        if ((y + dy) > 1 && (y + h + dy + 2) < mapH) {
            y += dy;
        }

        rect.setRoundRect(x, y, w, h, 3, 3);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public RoundRectangle2D getRectangle() {
        return rect;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                dy = -2;
                break;
            case KeyEvent.VK_DOWN:
                dy = 2;
                break;
            case KeyEvent.VK_LEFT:
                dx = -2;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 2;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
        }
    }
}