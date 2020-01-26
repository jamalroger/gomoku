
import java.io.IOException;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class Case extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Case(int x, int y, PlayGround play) {

        super(3);
        setHorizontalAlignment(JTextField.CENTER);
        setEnabled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (play.canIPlay()) {
                    System.out.println(x + " " + y);
                    setText(play.caseChar);
                    play.textArea.append("(" + x + "," + y + ")\n");

                    try {

                        play.server.send(x + "," + y + "," + play.caseChar);
                        play.stopCases();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }
        });
    }

}