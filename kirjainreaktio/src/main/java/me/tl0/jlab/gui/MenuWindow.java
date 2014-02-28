package me.tl0.jlab.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import me.tl0.jlab.logic.HighscoreSaver;
import me.tl0.jlab.logic.Mode;

/**
 * MenuWindow that is used to launch game.. later..
 *
 * TODO Fixme
 *
 * @author Teemu
 */
public class MenuWindow extends JPanel {

    private static final long serialVersionUID = 1L;

    private GameWindow window;
    private HighscoreSaver hs;
    Image background;

    public MenuWindow() {
        this.hs = new HighscoreSaver();
        createElements();
    }

    public MenuWindow(GameWindow gw) {
        this();
        this.window = gw;
    }

    /**
     * Creates menu elements
     *
     * TODO Logo?
     */
    public void createElements() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 100, 0, 100);
        this.add(createButton("Number (" + hs.getHScore(Mode.NUMBER) + ")", Mode.NUMBER));
        this.add(createButton("Letter (" + hs.getHScore(Mode.LETTER) + ")", Mode.LETTER));
        gbc.gridx++;
        this.add(createButton("Word (" + hs.getHScore(Mode.WORD) + ")", Mode.WORD));

        this.add(new JPanel(), gbc);
        setVisible(true);
    }

    /**
     * I personally dont like default look of JButtons... This is little way to
     * make them more.. beatiful? Also takes care of ActionListener..
     *
     * @param text
     * @param gamemode
     * @return
     */
    public JButton createButton(String text, final Mode gamemode) {
        JButton temp = new JButton(text);
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.startGame(gamemode);
            }
        });
        temp.setForeground(Color.BLACK);
        temp.setBackground(new Color(0, 127, 255));
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        temp.setBorder(compound);
        return temp;
    }

}
