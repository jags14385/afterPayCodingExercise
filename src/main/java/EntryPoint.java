import io.CSVIOService;
import io.IOService;
import java.math.BigDecimal;
import parser.CCTransactionParser;
import parser.IParser;
import validation.IValidator;
import validation.TransactionValidator;

public class EntryPoint {

  public static void main(String[] args) {
    System.out.println("Initial Run 101");

    IParser parser = new CCTransactionParser();
    IOService ioService = new CSVIOService("src/main/resources/input.csv", parser);
    IValidator validator = new TransactionValidator(new BigDecimal(15));
    CCFraudCheckService ccFraudCheckService = new CCFraudCheckService(ioService, validator);
    ccFraudCheckService.init();
  }
}
