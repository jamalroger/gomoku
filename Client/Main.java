
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {

        try {

            new PlayGround(args[0], args[1], Integer.parseInt(args[2]));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
