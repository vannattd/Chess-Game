
import java.awt.Dimension;

import javax.swing.JFrame;

/**********************************************************************
 * A GUI that displays the chess game
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class ChessGUI {

    /******************************************************************
     *  Main method This creates the GUI and gives it the chess game
     *  title and makes it so we can see the window with the game
     *
     *****************************************************************/
    public static void main (String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 725));
        frame.pack();
        frame.setVisible(true);
    }
}
