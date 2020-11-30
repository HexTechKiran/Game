import javax.swing.*;
import java.awt.*;

public class MyProgram extends JFrame {
    private TitleScreen title;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MyProgram prg = new MyProgram();
            prg.setVisible(true);
        });
    }

    public MyProgram() {
        setSize(495, 475);
        title = new TitleScreen("This is a Game", this.getContentPane());
        //add(new Panel());
        title.layoutComponents(title.aPane);
        pack();
        title.layoutComponents(title.aPane);
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}