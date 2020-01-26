
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class PlayGround extends JFrame {

    private static final long serialVersionUID = 1L;
    // frame components
    private JPanel panel = new JPanel();
    public JTextArea textArea;
    public JButton buttonStart;
    public JScrollPane scrollPane;
    private Case[][] cases;

    //
    public String caseChar;
    public Server server;
    private boolean isPlayed = false;
    public boolean isCaseActived = true;

    public PlayGround(String title, String host, int port) {

        // init object
        this.cases = new Case[18][18];
        this.textArea = new JTextArea(6, 4);
        this.buttonStart = new JButton("Start playing");
        this.scrollPane = new JScrollPane(textArea);

        this.initGui(title);

        this.buttonStart.addActionListener((event) -> {

            try {

                if (!isPlayed) {

                    this.connectToServer(host, port);
                    this.buttonStart.setText("Stop playing");
                    this.isPlayed = !this.isPlayed;

                } else {

                    this.isPlayed = !this.isPlayed;
                    this.server.close();
                    this.textArea.append(getTitle() + " you are deconnected \n");
                    this.clearPlay();
                    this.activeCases();
                    this.buttonStart.setText("Start playing");

                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public void connectToServer(String host, int port) throws UnknownHostException, IOException {

        this.textArea.append(getTitle() + " you are trying to connect to " + host + " ..... \n");

        this.server = new Server(host, port);

        this.textArea.append(getTitle() + " you are connected \n");

        this.startListenMsg();

    }

    public void initGui(String title) {

        add(buttonStart, BorderLayout.PAGE_START);
        buttonStart.setSize(10, 1000);
        add(scrollPane, BorderLayout.PAGE_END);
        add(panel, BorderLayout.CENTER);

        // add case(Button)
        this.addCases();
        // window
        setSize(new Dimension(821, 602));
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        setVisible(true);
    }

    public void addCases() {

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j] = new Case(i, j, this);
                this.panel.add(cases[i][j]);
            }
        }
    }

    public void startListenMsg() {

        new Thread(new Runnable() {

            public void run() {

                while (true) {

                    try {
                        // updating players view

                        String msg = server.getMsg();
                        String[] config = msg.split(",");
                        if (msg.startsWith("username")) {
                            caseChar = config[1];
                        } else if (msg.startsWith("win")) {
                            textArea.append(config[1].equals(caseChar) ? "you won" : "you lost try again" + "\n");
                            stopCases();
                        } else if (msg.startsWith("draw")) {

                            textArea.append(getTitle() + "you lose");
                            stopCases();
                        } else {
                            String[] coordinates = msg.split(",");
                            System.out.println(coordinates.length);
                            int position1 = Integer.parseInt(coordinates[0]);
                            int position2 = Integer.parseInt(coordinates[1]);
                            char player = coordinates[2].charAt(0);
                            Color color = player == 'X' ? Color.GREEN : Color.RED;
                            activeCases();
                            cases[position1][position2].setBackground(color);
                            cases[position1][position2].setText(coordinates[2]);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }).start();
    }

    public void clearPlay() {

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j].setText("");
            }
        }

    }

    public boolean canIPlay() {

        return this.isCaseActived;
    }

    public void stopCases() {
        this.isCaseActived = false;
    }

    public void activeCases() {

        this.isCaseActived = true;
    }
}