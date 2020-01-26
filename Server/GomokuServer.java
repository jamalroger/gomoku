
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class GomokuServer {

    private String host = "127.0.0.1";
    private int port = 4000;
    private ServerSocket gameServer = null;
    static Judge judge = null;

    // gather all active players
    public static HashSet<Socket> activePlayers = new HashSet<Socket>();

    public GomokuServer() {

        try {
            gameServer = new ServerSocket(port, 2, InetAddress.getByName(host));
            judge = new Judge();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public GomokuServer(String host, int port) {
        try {
            gameServer = new ServerSocket(port, 2, InetAddress.getByName(host));
            judge = new Judge();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendMsg(Socket pSocket, String msg) throws IOException {

        OutputStream outStream = pSocket.getOutputStream();
        DataOutputStream data = new DataOutputStream(outStream);
        data.writeUTF(msg);
        data.flush();
    }

    public static void sendToAll(String msg) throws IOException {

        for (Socket socket : activePlayers) {
            sendMsg(socket, msg);
        }

    }

    public void open() {

        while (activePlayers.size() < 2) {
            try {
                System.out.println("Listening..");
                Socket pSocket = gameServer.accept();
                System.out.println("Connexion request received");

                if (activePlayers.size() == 0) {

                    sendMsg(pSocket, "username,X");
                } else {

                    sendMsg(pSocket, "username,O");
                }
                Thread t = new Thread(new ProcessingClient(pSocket));
                activePlayers.add(pSocket);
                t.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}