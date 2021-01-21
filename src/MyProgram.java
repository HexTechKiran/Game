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
        layeredPane = getLayeredPane();

        game = new GameBoard();
        title = new TitleScreen("This is a Game");

        layeredPane.add(game, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(title, JLayeredPane.PALETTE_LAYER);
        /*add(new Panel());
        title.layoutComponents(title.aPane);
        pack();
        title.layoutComponents(title.aPane);*/
        pack();
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}