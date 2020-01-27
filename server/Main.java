
import java.io.BufferedInputStream;

public class Main {

  public static void main(String[] args) {

    GomokuServer a = new GomokuServer(args[0],Integer.parseInt(args[1]));
                 a.open();
  };
}
