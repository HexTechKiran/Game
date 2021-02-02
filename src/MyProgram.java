import javax.swing.*;
import java.awt.*;

public class MyProgram extends JFrame {
    private JLayeredPane layeredPane;
    private TitleScreen title;
    private GameBoard game;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MyProgram prg = new MyProgram();
            prg.setVisible(true);
        });
    }

    public MyProgram() {
        setSize(495, 475);
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(495, 475));
        layeredPane.setBackground(Color.lightGray);

        game = new GameBoard();
        title = new TitleScreen("This is a Game");

        layeredPane.add(game, new Integer(0));
        game.setBounds(0, 0, 495, 475);
        game.setOpaque(true);
        layeredPane.add(title, new Integer(1));
        title.setBounds(0, 0, 495, 475);
        title.setOpaque(true);

        this.add(layeredPane);

        pack();
        setVisible(true);
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}