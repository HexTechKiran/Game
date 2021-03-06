import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {

    private int spaceW;
    private int spaceH;
    private int mapW = 500;
    private int mapH = 500;
    private Timer timer;
    private MoveRect rect;
    private final int DELAY = 10;
    private boolean atEdge = false;
    private GameBoard board = this;
    private int[][] walls = new int[][]{
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
            {1 ,1, 1, 1, 3, 3, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 3, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 3, 0, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 3, 0},
            {3 ,0, 0, 0, 0, 0, 0, 0, 0, 3}
    };
    private int[][] pixels = new int[500][500];
    private Point[][][] wallCoords;

    public GameBoard() {
        initPanel();
    }

    public void initPanel() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setFocusable(true);

        initWallCoords(walls);
        generateWallCoords(walls);
        pixelWalls();

        rect = new MoveRect(10, 10, getPreferredSize(), walls, pixels);

        timer = new Timer(DELAY, this);
        timer.start();
        setVisible(true);
    }

    private void initWallCoords(int[][] walls_) {
        wallCoords = new Point[walls.length][walls[0].length][3];

        for(int i = 0; i < walls.length; i++) {
            for(int j = 0; j < walls[0].length; j++) {
                for(int k = 0; k < 3; k++) {
                    wallCoords[i][j][k] = new Point(-1, -1);
                }
            }
        }
    }

    private void generateWallCoords(int[][] walls) {
        spaceW = mapW / (walls[0].length);
        spaceH = mapH / (walls.length);

        for(int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[0].length; j++) {
                wallCoords[i][j][0] = new Point(spaceH * (j+1), spaceW * i);
                wallCoords[i][j][1] = new Point(spaceH * (j+1), spaceW * (i+1));
                wallCoords[i][j][2] = new Point(spaceH * j, spaceW * (i+1));
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(mapW, mapH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        drawWalls(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.draw(rect.getRectangle());
    }

    public void drawWalls(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[0].length; j++) {
                Point top = wallCoords[i][j][0];
                Point cor = wallCoords[i][j][1];
                Point left = wallCoords[i][j][2];
                switch (walls[i][j]) {
                    case 1:
                        g2d.drawLine(top.x, top.y, cor.x, cor.y);
                        break;
                    case 2:
                        g2d.drawLine(cor.x, cor.y, left.x, left.y);
                        break;
                    case 3:
                        g2d.drawLine(top.x, top.y, cor.x, cor.y);
                        g2d.drawLine(cor.x, cor.y, left.x, left.y);
                        break;
                }
            }
        }
    }

    private void pixelWalls() {
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[0].length; j++) {
                Point top = wallCoords[i][j][0];
                Point cor = wallCoords[i][j][1];
                Point left = wallCoords[i][j][2];
                switch (walls[i][j]) {
                    case 1:
                        drawPixels(top.x, top.y, cor.x, cor.y);
                        break;
                    case 2:
                        drawPixels(cor.x, cor.y, left.x, left.y);
                        break;
                    case 3:
                        drawPixels(top.x, top.y, cor.x, cor.y);
                        drawPixels(cor.x, cor.y, left.x, left.y);
                        break;
                }
            }
        }
    }

    private void drawPixels(int x1, int y1, int x2, int y2) {
        Point[] pointsBetween = new Point[5];

        for (int i = x1; i <= x2; i++) {
            pointsBetween[i-x1].x = i;
        }
        for (int j = y1; j <= y2; j++) {
            pointsBetween[j-y1].y = j;
        }

        for (Point coord : pointsBetween) {
            pixels[coord.y][coord.x] = 1;
        }
    }

    public void step() {
        rect.move();

        repaint(rect.getX()-4, rect.getY()-4, rect.getWidth()+7, rect.getHeight()+7);
    }

    public void actionPerformed(ActionEvent e) {
        step();
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            if (board.hasFocus()) {
                rect.keyPressed(e);
            }
        }

        public void keyReleased(KeyEvent e) {
            if (board.hasFocus()) {
                rect.keyReleased(e);
            }
        }
    }
}