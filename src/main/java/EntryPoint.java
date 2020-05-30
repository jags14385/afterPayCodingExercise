import domain.CCTransaction;
import io.CSVIOService;
import io.IOService;
import java.util.ArrayList;

public class EntryPoint {

  public static void main(String[] args) {
    System.out.println("Initial Run 101");

    IOService ioService = new CSVIOService("src/main/resources/input.csv");
    ArrayList<CCTransaction> records = ioService.read();
    for (CCTransaction record : records) {
      System.out.println("===========");
      System.out.println("Hashed CC : " + record.getHashedCC());
      System.out.println("Hashed CC : " + record.getAmount());
      System.out.println("Hashed CC : " + record.getDateOfTransaction());
    }
  }
}
