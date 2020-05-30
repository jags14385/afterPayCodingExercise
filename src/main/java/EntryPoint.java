import domain.CCTransaction;
import io.CSVIOService;
import io.IOService;
import java.util.ArrayList;
import parser.CCTransactionParser;
import parser.IParser;

public class EntryPoint {

  public static void main(String[] args) {
    System.out.println("Initial Run 101");

    IParser parser = new CCTransactionParser();
    IOService ioService = new CSVIOService("src/main/resources/input.csv", parser);
    ArrayList<CCTransaction> records = ioService.read();
    for (CCTransaction record : records) {
      System.out.println("===========");
      System.out.println("Hashed CC : " + record.getHashedCC());
      System.out.println("Hashed CC : " + record.getAmount());
      System.out.println("Hashed CC : " + record.getDateOfTransaction());
    }
  }
}
