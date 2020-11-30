import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    private Timer timer;
    private MoveRect rect;
    private final int DELAY = 10;
    private boolean atEdge = false;

    public Panel() {
        initPanel();
    }

    public void initPanel() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setFocusable(true);

        rect = new MoveRect(100, 100, getPreferredSize());

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(495, 475);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.draw(rect.getRectangle());
    }

    public void showTitle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


    }

    public void step() {
        rect.move();

        repaint(rect.getX()-2, rect.getY()-2, rect.getWidth()+5, rect.getHeight()+5);
    }

    public void actionPerformed(ActionEvent e) {
        step();
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            rect.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            rect.keyReleased(e);
        }
    }
}