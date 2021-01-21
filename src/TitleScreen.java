import java.awt.*;
import javax.swing.*;

public class TitleScreen extends JPanel{
    private JLabel titleText;
    private JLabel upperSpacer = new JLabel("");
    private JLabel lowerSpacer = new JLabel("");
    private JLabel topLeftSpacer = new JLabel("");
    private JLabel topRightSpacer = new JLabel("");
    private JLabel bottomLeftSpacer = new JLabel("");
    private JLabel bottomRightSpacer = new JLabel("");
    private JLabel midSpacer = new JLabel("");
    private JButton start = new JButton("Start");
    private int SW;
    private int padding;

    public TitleScreen(String title) {
        initTitle(title);
    }

    private void initTitle(String title) {
        titleText = new JLabel(title);
        titleText.setFont(new Font("a", 0, 67));
        titleText.setVisible(true);
        start.setVisible(true);
    }

    public void layoutComponents() {
        this.setLayout(new GridBagLayout());

        SW = (495 - Math.max(titleText.getWidth(), start.getWidth())) / 4;
        padding = (Math.max(titleText.getWidth(), start.getWidth()) - Math.max(titleText.getWidth(), start.getWidth())) / 2;
        System.out.println(titleText.getWidth());
        System.out.println(SW);
        System.out.println(padding);

        GridBagConstraints usCons = new GridBagConstraints();
        usCons.gridwidth = 3;
        usCons.gridheight = 1;
        usCons.gridx = 0;
        usCons.gridy = 0;
        usCons.ipady = 50;
        this.add(upperSpacer, usCons);

        GridBagConstraints tlsCons = new GridBagConstraints();
        tlsCons.gridwidth = 1;
        tlsCons.gridheight = 1;
        tlsCons.ipadx = SW;
        tlsCons.gridx = 0;
        tlsCons.gridy = 1;
        this.add(topLeftSpacer, tlsCons);

        GridBagConstraints tCons = new GridBagConstraints();
        tCons.gridwidth = 1;
        tCons.gridheight = 1;
        tCons.gridx = 1;
        tCons.gridy = 1;
        tCons.anchor = GridBagConstraints.PAGE_START;
        this.add(titleText, tCons);

        GridBagConstraints trsCons = new GridBagConstraints();
        trsCons.gridwidth = 1;
        trsCons.gridheight = 1;
        trsCons.ipadx = SW;
        trsCons.gridx = 2;
        trsCons.gridy = 1;
        this.add(topRightSpacer, trsCons);

        GridBagConstraints msCons = new GridBagConstraints();
        msCons.gridwidth = 3;
        msCons.gridheight = 1;
        msCons.ipadx = 247;
        msCons.ipady = 200;
        msCons.gridx = 1;
        msCons.gridy = 2;
        this.add(midSpacer, msCons);

        GridBagConstraints blsCons = new GridBagConstraints();
        blsCons.gridwidth = 1;
        blsCons.gridheight = 1;
        blsCons.ipadx = SW;
        blsCons.gridx = 0;
        blsCons.gridy = 3;
        this.add(bottomLeftSpacer, blsCons);

        GridBagConstraints sCons = new GridBagConstraints();
        sCons.gridwidth = 1;
        sCons.gridheight = 1;
        sCons.gridx = 1;
        sCons.gridy = 3;
        sCons.ipadx = padding;
        this.add(start, sCons);

        GridBagConstraints brsCons = new GridBagConstraints();
        brsCons.gridwidth = 1;
        brsCons.gridheight = 1;
        brsCons.ipadx = SW;
        brsCons.gridx = 2;
        brsCons.gridy = 3;
        this.add(bottomRightSpacer, brsCons);

        GridBagConstraints lsCons = new GridBagConstraints();
        lsCons.gridwidth = 3;
        lsCons.gridheight = 1;
        lsCons.gridx = 0;
        lsCons.gridy = 4;
        lsCons.ipady = 50;
        this.add(lowerSpacer, lsCons);
    }
}