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

        layeredPane.add(game, JLayeredPane.DEFAULT_LAYER);
        game.setBounds(0, 0, 495, 475);
        layeredPane.add(title, JLayeredPane.PALETTE_LAYER);
        title.setBounds(0, 0, 495, 475);

        this.add(layeredPane);

        pack();
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}