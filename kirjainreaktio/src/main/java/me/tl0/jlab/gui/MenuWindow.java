package me.tl0.jlab.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * MenuWindow that is used to launch game.. later..
 *
 * TODO Fixme
 *
 * @author Teemu
 */
public class MenuWindow extends JPanel {

    private static final long serialVersionUID = 1L;

    private ActionListener action;

    public MenuWindow() {

        action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This language just gets better and better!");
            }
        };

        createElements();
    }

    public void createElements() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 100, 0, 100);
        this.add(createButton("XXX"));

        this.add(createButton("YYY"));

        gbc.gridx++;
        this.add(createButton("ZZZ"));

        this.add(new JPanel(), gbc);
        setVisible(true);
    }

    public JButton createButton(String text) {
        JButton temp = new JButton(text);
        temp.addActionListener(action);
        temp.setForeground(Color.BLACK);
        temp.setBackground(new Color(0, 127, 255));
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        temp.setBorder(compound);
        return temp;
    }

}
