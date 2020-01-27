
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

public class ProcessingClient implements Runnable {

    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Socket pSocket = null;

    public ProcessingClient(Socket pSocket) {
        this.pSocket = pSocket;
    }

    @Override
    public void run() {
        System.out.println("launching client processing");

        while (!pSocket.isClosed()) {
            try {

                InputStream inStream = pSocket.getInputStream();
                // waiting for client request
                in = new DataInputStream(inStream);
                String response = in.readUTF();

                // sending a response
                String toSend = response;

                // updating judge view
                String[] coordinates = toSend.split(",");
                int position1 = Integer.parseInt(coordinates[0]);
                int position2 = Integer.parseInt(coordinates[1]);
                String currentPlayer = coordinates[2];
                boolean state = Judge.pushInput(position1, position2, currentPlayer);
                if (state) {
                    toSend = "win," + currentPlayer;
                    Judge.textArea.append("Congratulations to player : " + currentPlayer);
                }

                if (Math.pow(Judge.table.length, 2) == Judge.moveCount && !state) {
                    toSend = "draw";
                }
                // broadcasting player choice to all players to update there views
                GomokuServer.sendToAll(toSend);

            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
