import io.IOService;
import java.util.ArrayList;

public class EntryPoint {

  public static void main(String[] args) {
    System.out.println("Initial Run 101");

    IOService ioService = new IOService("src/main/resources/input.csv");
    ArrayList<String[]> records = ioService.read();
    System.out.println(records);
  }
}
