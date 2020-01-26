
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server
 */
public class Server extends Thread {

    public Socket server;
    private DataOutputStream out;
    private DataInputStream in;

    public Server(String host, int port) throws UnknownHostException, IOException {

        this.server = new Socket(host, port);

    }

    public void send(String msg) throws IOException {

        OutputStream out = this.server.getOutputStream();
        this.out = new DataOutputStream(out);
        this.out.writeUTF(msg);

    }

    public String getMsg() throws IOException {

        InputStream in = this.server.getInputStream();
        this.in = new DataInputStream(in);
        return this.in.readUTF();
    }

    public void close() throws IOException {

        this.server.close();
    }

}