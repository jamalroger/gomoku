
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Judge extends JFrame {

    public static String[][] table = new String[18][18];
    public static JTextArea textArea = new JTextArea();
    private static int countPlayerX = 0;
    private static int countPlayerO = 0;
    public static int moveCount = 0;
    private static final int win = 5;

    public Judge() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 300));
        textArea.setAutoscrolls(false);
        textArea.setEditable(false);
        add(textArea);
        setTitle("Judge");
        setResizable(false);
        setVisible(true);
        init(table);
    }

    public static void init(String[][] table) {
        for (String[] strings : table) {
            Arrays.fill(strings, "");
        }
    }

    public static boolean pushInput(int x, int y, String player) {

        // adding choices to judge matrix & view
        table[x][y] = player;
        textArea.append("(" + x + "," + y + ") " + "Player : " + player + "\n");

        // counting how many times a player played
        if (player.equals("X")) {
            countPlayerX++;
        } else {
            countPlayerO++;
        }
        moveCount++;

        // specifying when to start verification
        if (countPlayerX >= win || countPlayerO >= win) {
            if (verifyWinnerGomokuColumnCase(x, y, player) || verifyWinnerGomokuLineCase(x, y, player)
                    || verifyWinnerDiagonalCase(x, y, player) || verifyWinnerGomokuAntiDiagonalCase(x, y, player)) {
                return true;
            }
        }
        return false;
    }

    /*
     * winner verification
     */

    // line verification
    public static boolean verifyWinnerGomokuLineCase(int x, int y, String player) {
        int count = 0;
        String caseBefore = player;
        for (int i = 0; i < table[x].length; i++) {
            System.out.println(count);
            if (table[x][i].equals(player) && player.equals(caseBefore)) {
                 count++;
                if (count == win)
                    break;
            } else {
                count = 1;
            }
            caseBefore = table[x][i];
        }
        return count == win;
    }

    // column verification
    public static boolean verifyWinnerGomokuColumnCase(int x, int y, String player) {
        int count = 0;
        int previousElement = 0;
        for (int i = 0; i < table.length; i++) {
            System.out.println(count);
            if (table[i][y].equals(player)) {
                if (i - previousElement <= 1) {
                    count++;
                } else {
                    count = 1;
                }
                previousElement = i;
            }
            if (count == win)
                return true;
        }
        return false;
    }

    // diagonal verification
    public static boolean verifyWinnerDiagonalCase(int x, int y, String player) {
        int count = 0;
        String caseBefore = player;
        int i = Math.max(x - y, 0);
        int j = x - y < 0 ? y - x : 0;
        /*
         * init i & init j are used to verify if the player played for the first time
         */
        int initi = i;
        int initj = j;
        int length = table.length;
        for (; x - y > 0 ? i < length : j < length; i++, j++) {

            System.out.println(i + " " + j);
            System.out.println(count);

            if (table[i][j].equals(player) && player.equals(caseBefore)) {
                ++count;
                if (count == win)
                    break;
            } else {
                if (initi == i && initj == j)
                    count = 1;

            }
            caseBefore = table[i][j];
        }
        System.out.println(count);
        return count == win;
    }

    // anti-diagonal verification
    public static boolean verifyWinnerGomokuAntiDiagonalCase(int x, int y, String player) {
        int count = 0;
        String caseBefore = player;
        int i = Math.max(x + y - (table[x].length - 1), 0);
        /*
         * offset : what remains until the end of table.length
         */
        int offset = x + y - (table[x].length - 1);
        int j = x + y - (table[x].length - 1) >= 0 ? table[x].length - 1 : (table[x].length - 1) - (Math.abs(offset));
        int trackLength = table.length;
        for (; i < table.length; i++, j--) {
            System.out.println(i + " " + j);
            System.out.println(count);
            if (trackLength < 1 || j < 0)
                break;
            trackLength--;

            if (table[i][j].equals(player) && player.equals(caseBefore)) {

                count++;
                if (count == win)
                    break;
            } else {
                count = 1;
            }
            caseBefore = table[i][j];
        }
        return count == win;
    }
}