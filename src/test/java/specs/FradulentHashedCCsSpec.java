package specs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import io.CSVIOService;
import io.IOService;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import parser.CCTransactionParser;
import parser.IParser;
import service.CCFraudCheckService;
import validation.IValidator;
import validation.TransactionValidator;

public class FradulentHashedCCsSpec {
  public IValidator validator;
  public CCFraudCheckService ccFraudCheckService;

  @Before
  public void setUp() {
    IParser parser = new CCTransactionParser();
    IOService ioService = new CSVIOService("src/test/resources/test.csv", parser);
    validator = new TransactionValidator(new BigDecimal(100));
    ccFraudCheckService = new CCFraudCheckService(ioService, validator);
  }

  @Test
  public void ShouldReturnFradulentHashedCCs() {
    ArrayList<String> fradulentCards = validator.validate(ccFraudCheckService.getHashMap());
    assertThat(fradulentCards.size(), equalTo(2));
    assertThat(fradulentCards.contains("CC2"), equalTo(true));
    assertThat(fradulentCards.contains("CC3"), equalTo(true));
  }
}
