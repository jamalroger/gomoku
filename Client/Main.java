
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        try {

            new PlayGround("jamal", "127.0.0.1", 4000);

            new PlayGround("abdelali", "127.0.0.1", 4000);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
