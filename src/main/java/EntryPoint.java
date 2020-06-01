import io.CSVIOService;
import io.IOService;
import java.math.BigDecimal;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import parser.CCTransactionParser;
import parser.IParser;
import service.CCFraudCheckService;
import validation.IValidator;
import validation.TransactionValidator;

public class EntryPoint {
  private static final Logger LOGGER = Logger.getLogger(EntryPoint.class);

  public static void main(String[] args) {
    LOGGER.log(Level.DEBUG, "===== EntryPoint Start : ======");
    IParser parser = new CCTransactionParser();
    IOService ioService = new CSVIOService("src/main/resources/input.csv", parser);
    IValidator validator = new TransactionValidator(new BigDecimal(100));
    CCFraudCheckService ccFraudCheckService = new CCFraudCheckService(ioService, validator);
    ccFraudCheckService.init();
    LOGGER.log(Level.DEBUG, "===== EntryPoint End : ======");
  }
}
