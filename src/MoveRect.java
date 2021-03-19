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
    private int[][] pixels;
    private Point[][] wallBlocks;
    private int cellWidth;
    private int cellHeight;
    private RoundRectangle2D rect;

    public MoveRect(Dimension dim, int[][] walls_, int[][] pixels_) {
        w = 10;
        h = 10;
        walls = walls_;
        pixels = pixels_;
        createRectangle(10, 10, dim);
    }

    public MoveRect(int width, int height, Dimension dim, int[][] walls_, int[][] pixels_) {
        w = width;
        h = height;
        walls = walls_;
        pixels = pixels_;
        createRectangle(width, height, dim);
    }

    public void createRectangle(int width, int height, Dimension dim) {
        mapW = (int) dim.getWidth();
        mapH = (int) dim.getHeight();
        x = 100;
        y = 100;

        cellWidth = (mapW/walls[0].length);
        cellHeight = (mapH/walls.length);
        currCell.x = (mapW - (mapW - (x - (x % cellWidth))))/cellWidth;
        currCell.y = (mapH - (mapH - (y - (y % cellHeight))))/cellHeight;

        rect = new RoundRectangle2D.Double(x, y, width, height, 3, 3);
    }

    //This function is incomplete!
    public boolean[] canMove(int x_, int y_, int dx_, int dy_) {
        boolean[] canMove = new boolean[]{
                true, true
        };

        newX = x_ + dx_;
        newY = y_ + dy_;

        Point newBottomLeft = new Point(newX, newY);
        Point newTopRight = new Point(newX + w, newY + h);

        int[][] newLocationPixels = new int[10][10];
        int curr = 0;

        for (int[] list : Arrays.copyOfRange(pixels, newTopRight.y, newBottomLeft.y)) {
            newLocationPixels[curr] = Arrays.copyOfRange(list, newBottomLeft.x, newTopRight.x);
            curr += 1;
        }

        for (int r = 0; r < newLocationPixels.length; r++) {
            for (int c = 0; c < newLocationPixels[0].length; c++) {
                if (
                        (r == 0 && 1 < c && c < (newLocationPixels.length-2))
                        || (r == (newLocationPixels.length-1))) {
                    if (newLocationPixels[r][c] == 1) {
                        canMove[1] = false;
                    }
                }
                else if (j > )
            }
        }

        //The above loads the pixels in the new rectangle space into newLocationPixels, now just check for black pixels

        /*Point thisCell = new Point(
                (mapW - (mapW - (x_ - (x_ % cellWidth))))/cellWidth,
                (mapH - (mapH - (y_ - (y_ % cellHeight))))/cellHeight
        );
        int thisCellVal = walls[thisCell.y][thisCell.x];
        Point newCell = new Point(
                (mapW - (mapW - (newX - (newX % cellWidth))))/cellWidth,
                (mapH - (mapH - (newY - (newY % cellHeight))))/cellHeight
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
        }*/

        return canMove;
    }

    public void checkWalls() {
        /*boolean[] canMove = {
                true, true
        };

        Point tl = new Point(x, y);
        Point tr = new Point((x + w), y);
        Point br = new Point((x + w), (y + h));
        Point bl = new Point(x, (y + h));

        Point tlCell = new Point(
                (mapW - (mapW - (tl.x - (tl.x % cellWidth))))/cellWidth,
                (mapH - (mapH - (tl.y - (tl.y % cellHeight))))/cellHeight
        );
        Point trCell = new Point(
                (mapW - (mapW - (tr.x - (tr.x % cellWidth))))/cellWidth,
                (mapH - (mapH - (tr.y - (tr.y % cellHeight))))/cellHeight
        );
        Point brCell = new Point(
                (mapW - (mapW - (br.x - (br.x % cellWidth))))/cellWidth,
                (mapH - (mapH - (br.y - (br.y % cellHeight))))/cellHeight
        );
        Point blCell = new Point(
                (mapW - (mapW - (bl.x - (bl.x % cellWidth))))/cellWidth,
                (mapH - (mapH - (bl.y - (bl.y % cellHeight))))/cellHeight
        );

        //Top side
        if (!tlCell.equals(trCell)) {
            if (walls[tlCell.y - 1][tlCell.x] == 1) {
                Point dontIn = wallCoords[tlCell.y - 1][tlCell.x][1];
                //Check if this point comes within the rectangle
            }
        }

        //Right side
        if (!trCell.equals(brCell)) {
            if (walls[trCell.y][trCell.x + 1] == 2 || walls[trCell.y][trCell.x + 1] == 3) {

            }
        }*/
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

        boolean[] cMoveUL = canMove(x, y, dx, dy);
        boolean[] cMoveUR = canMove((x + w), y, dx, dy);
        boolean[] cMoveBR = canMove((x + w), (y + h), dx, dy);
        boolean[]cMoveBL = canMove(x, (y + h), dx, dy);

        boolean canMoveX = cMoveUL[0] && cMoveUR[0] && cMoveBR[0] && cMoveBL[0];
        boolean canMoveY = cMoveUL[1] && cMoveUR[1] && cMoveBR[1] && cMoveBL[1];

        if ((newX > 1 && (newX + w + 2) < mapW) && canMoveX) {
            x += dx;
        }
        if (((newY - h) > 1 && (newY + 2) < mapH) && canMoveY) {
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