import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {

    private int spaceW;
    private int spaceH;
    private int mapW = 495;
    private int mapH = 475;
    private Timer timer;
    private MoveRect rect;
    private final int DELAY = 10;
    private boolean atEdge = false;
    private GameBoard board = this;
    private int[][] walls = new int[][]{
            {0, 3, 3, 0, 0, 0, 3, 3, 3, 0},
            {0, 3, 3, 3, 3, 0, 0, 0, 0, 3},
            {3, 3, 3, 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3 ,0, 0, 3, 3, 3, 3, 0, 0, 0},
            {0, 0, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3 ,3, 3, 3, 3, 3, 3, 3, 3, 3}
    };
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

        rect = new MoveRect(10, 10, getPreferredSize(), walls, wallCoords);

        timer = new Timer(DELAY, this);
        timer.start();
        setVisible(true);
    }

    private void initWallCoords(int[][] walls_) {
        wallCoords = new Point[walls.length][walls[0].length][3];

        for(int i = 0; i < walls.length; i++) {
            for(int j = 0; j < wallCoords[0].length; j++) {
                for(int k = 0; k < 3; k++) {
                    wallCoords[i][j][k] = new Point(-1, -1);
                }
            }
        }
    }

    private void generateWallCoords(int[][] walls) {
        int currW = 0;
        int currH = 0;

        spaceW = mapW / (walls[0].length);
        spaceH = mapH / (walls.length);
        currW = spaceW;
        currH = spaceH;

        for(int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[0].length; j++) {
                wallCoords[i][j][0] = new Point(spaceW * (i+1), spaceH * j);
                wallCoords[i][j][1] = new Point(spaceW * (i+1), spaceH * (j+1));
                wallCoords[i][j][2] = new Point(spaceW * i, spaceH * (j+1));
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(495, 475);
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
                    case 0:
                        continue;
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