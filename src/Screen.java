import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Screen extends JPanel{
    private TitleScreen title;

    public enum State {
        GAME, TITLESCREEN
    }

    public void DisplayTitle() {
        title = new TitleScreen("This is a Game");
        title.layoutComponents();
    }

    public void DisplayGame() {

    }
}