
import java.io.BufferedInputStream;

public class Main {

  public static void main(String[] args) {

    GomokuServer a = new GomokuServer("127.0.0.1", 4000);
    a.open();
  };
}
