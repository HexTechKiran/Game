import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class MoveRect {
    private int x = 0;
    private int y = 0;
    private Point currCell = new Point();
    private int newX;
    private int newY;
    private int dx;
    private int dy;
    private int w;
    private int h;
    private int mapW;
    private int mapH;
    private int spaceW;
    private int spaceH;
    private int[][] walls;
    private Point[][][] wallCoords;
    private Point[][] wallBlocks;
    private int cellWidth;
    private int cellHeight;
    private RoundRectangle2D rect;

    public MoveRect(Dimension dim, int[][] walls_, Point[][][] wallCoords_) {
        w = 10;
        h = 10;
        walls = walls_;
        wallCoords = wallCoords_;
        createRectangle(10, 10, dim);
    }

    public MoveRect(int width, int height, Dimension dim, int[][] walls_, Point[][][] wallCoords_) {
        w = width;
        h = height;
        walls = walls_;
        wallCoords = wallCoords_;
        createRectangle(width, height, dim);
    }

    public void createRectangle(int width, int height, Dimension dim) {
        mapW = (int) dim.getWidth();
        mapH = (int) dim.getHeight();
        x = mapW/2 - w/2;
        y = mapH/2 - h/2;

        cellWidth = (mapW/walls[0].length);
        cellHeight = (mapH/walls.length);
        currCell.x = (mapW - (mapW - (x - (x % cellWidth))))/cellWidth;
        currCell.y = (mapH - (mapH - (y - (y % cellHeight))))/cellHeight;

        rect = new RoundRectangle2D.Double(x, y, width, height, 3, 3);
    }

    public void move() {
        boolean[] passThrough = new boolean[]{
                true, true, true, true
        };
        boolean noX = false;
        boolean noY = false;
        /*
        Values for each side of a cell works like this

             _____0____
             |        |
             3        1
             |____2___|
         */

        newX = x + dx;
        newY = y + dy;

        currCell.x = (mapW - (mapW - (x - (x % cellWidth))))/cellWidth;
        currCell.y = (mapH - (mapH - (y - (y % cellHeight))))/cellHeight;

        switch (walls[currCell.y][currCell.x]) {
            case 1:
                //can't pass through on right
                passThrough[1] = false;
                break;
            case 2:
                //can't pass through on bottom
                passThrough[2] = false;
                break;
            case 3:
                //can't pass through on right or bottom
                passThrough[1] = false;
                passThrough[2] = false;
                break;
        }

        if (walls[currCell.y - 1][currCell.x] == 1 || walls[currCell.y - 1][currCell.x] == 3) {
            //can't pass through on left;
            passThrough[3] = false;
        }

        if (walls[currCell.y][currCell.x - 1] == 2 || walls[currCell.y][currCell.x - 1] == 3) {
            //can't pass through on left;
            passThrough[0] = false;
        }

        System.out.println("Move X: " + (((mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth) != currCell.x));
        System.out.println("Move Y: " + (((mapH - (mapH - ((y + dy) - ((y + dy) % cellHeight))))/cellHeight) != currCell.y));

        if (
                ((((mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth) > currCell.x) && !passThrough[1])
                || ((((mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth) < currCell.x) && !passThrough[3])) {
            noX = true;
        }

        if (
                ((((mapH - (mapH - (newY - (newY % cellHeight))))/cellHeight) > currCell.y) && !passThrough[2])
                || ((((mapH - (mapH - (newY - (newY % cellHeight))))/cellHeight) < currCell.y) && !passThrough[0])) {
            noY = true;
        }

        if ((newX > 1 && (newX + w + 2) < mapW) && !noX) {
            x += dx;
        }
        if ((newY > 1 && (newY + h + 2) < mapH) && !noY) {
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