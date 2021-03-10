import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

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

    public boolean[] canMove(int x_, int y_, int dx_, int dy_) {
        boolean[] canMove = new boolean[]{
                true, true
        };

        newX = x_ + dx_;
        newY = y_ + dy_;

        Point thisCell = new Point(
                (mapW - (mapW - (x_ - (x_ % cellWidth))))/cellWidth,
                (mapH - (mapH - (12 + y_ - (y_ % cellHeight))))/cellHeight
        );
        int thisCellVal = walls[thisCell.y][thisCell.x];
        Point newCell = new Point(
                (mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth,
                (mapH - (mapH - (12 + newY - (newY % cellHeight))))/cellHeight
        );
        int newCellVal = walls[newCell.y][newCell.x];

        if (thisCell.x != newCell.x || thisCell.y != newCell.y) {
            if (newCell.y < thisCell.y) {
                if (newCellVal == 2 || newCellVal == 3) {
                    canMove[1] = false;
                }
            }
            if (newCell.y > thisCell.y) {
                if (thisCellVal == 2 || thisCellVal == 3) {
                    canMove[1] = false;
                }
            }
            if (newCell.x < thisCell.x) {
                if (newCellVal == 1 || newCellVal == 3) {
                    canMove[0] = false;
                }
            }
            if (newCell.x > thisCell.x) {
                if (thisCellVal == 1 || thisCellVal == 3) {
                    canMove[0] = false;
                }
            }
        }

        /*int newTempX = x_ + dx_;
        int newTempY = y_ + dy_ + 12;
        Point thisCell = new Point(
                (mapW - (mapW - (x - (x % cellWidth))))/cellWidth,
                (mapH - (mapH - (12 + y - (y % cellHeight))))/cellHeight);
        Point cellAbove = new Point(thisCell.x, thisCell.y - 1);
        Point cellLeft = new Point(thisCell.x - 1, thisCell.y);

        int cellAboveY = 0;
        int cellLeftX = 0;
        int thisCellY = 0;
        int thisCellX = 0;

        switch (walls[thisCell.y][thisCell.x]) {
            case 1:
                thisCellX = wallCoords[thisCell.y][thisCell.x][1].x;
                break;
            case 2:
                thisCellY = wallCoords[thisCell.y][thisCell.x][1].y;
                break;
            case 3:
                thisCellX = wallCoords[thisCell.y][thisCell.x][1].x;
                thisCellY = wallCoords[thisCell.y][thisCell.x][1].y;
                break;
        }

        if (cellAbove.y >= 0) {
            switch(walls[cellAbove.y][cellAbove.x]) {
                case 2:
                    cellAboveY = wallCoords[cellAbove.y][cellAbove.x][1].y;
                    break;
                case 3:
                    cellAboveY = wallCoords[cellAbove.y][cellAbove.x][1].y;
                    break;
            }
        }

        if (cellLeft.x >= 0) {
            switch(walls[cellLeft.y][cellLeft.x]) {
                case 1:
                    cellLeftX = wallCoords[cellLeft.y][cellLeft.x][1].x;
                    break;
                case 3:
                    cellLeftX = wallCoords[cellLeft.y][cellLeft.x][1].x;
                    break;
            }
        }

        if (thisCellY != 0 && cellAboveY != 0) {
            if (dy_ > 0) {
                System.out.print(thisCellY + " ");
                System.out.println(newTempY);
                if (thisCellY < newTempY) {
                    canMove[1] = false;
                }
            }
            else if (dy_ < 0) {
                System.out.println(thisCellY);
                System.out.println(newTempY);
                if (newTempY < cellAboveY) {
                    canMove[1] = false;
                }
            }
        }

        if (thisCellX != 0 && cellLeftX != 0) {
            if (dx_ > 0) {
                if (thisCellX < newTempX) {
                    canMove[0] = false;
                }
            }
            else if (dx_ < 0) {
                if (newTempX < cellLeftX) {
                    canMove[0] = false;
                }
            }
        }*/

        return canMove;
    }

    public void move() {
        boolean[] passThrough = new boolean[]{
                true, true, true, true
        };
        /*
        Values for each side of a cell works like this

             _____0____
             |        |
             3        1
             |____2___|
         */

        newX = x + dx;
        newY = y + dy;
        /*

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

        int upperWall = ((currCell.y - 1) >= 0) ? walls[currCell.y - 1][currCell.x] : 3;
        int leftWall = ((currCell.x - 1) >= 0) ? walls[currCell.y][currCell.x - 1] : 3;
        //Account for edge cases where currCell.x/y = 0, meaning it - 1 is negative
        if (upperWall == 2 || upperWall == 3) {
            //can't pass through on left;
            passThrough[0] = false;
        }

        if (leftWall == 1 || leftWall == 3) {
            //can't pass through on left;
            passThrough[3] = false;
        }

        //System.out.println("Move X: " + (((mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth) != currCell.x));
        //System.out.println("Move Y: " + (((mapH - (mapH - ((y + dy) - ((y + dy) % cellHeight))))/cellHeight) != currCell.y));

        int newXCell = ((mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth);
        int newYCell = ((mapH - (mapH - (newY - (newY % cellHeight))))/cellHeight);
        if (
                ((newXCell > currCell.x) && !passThrough[1])
                || ((newXCell < currCell.x) && !passThrough[3])) {
            noX = true;
        }

        if (
                ((newYCell > currCell.y) && !passThrough[2])
                || ((newYCell < currCell.y) && !passThrough[0])) {
            noY = true;
        }

        //TEST CODE
        if (newXCell != currCell.x) {
            System.out.println("New Cell X: " + newXCell);
            System.out.println(Arrays.toString(passThrough));
        }
        if (newYCell != currCell.y) {
            System.out.println("New Cell Y: " + newYCell);
            System.out.println(Arrays.toString(passThrough));
        }*/

        boolean[] cMove = canMove(x, y, dx, dy);

        if ((newX > 1 && (newX + w + 2) < mapW) && cMove[0]) {
            x += dx;
        }
        if ((newY > 1 && (newY + h + 2) < mapH) && cMove[1]) {
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