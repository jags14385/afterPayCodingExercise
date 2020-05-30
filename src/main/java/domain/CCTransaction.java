package domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CCTransaction {
  private final String hashedCC;
  private final BigDecimal amount;
  private final Date dateOfTransaction;

  public String getHashedCC() {
    return hashedCC;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Date getDateOfTransaction() {
    return dateOfTransaction;
  }

  public CCTransaction(String hashedCC, String dateOfTransaction, String amount) throws ParseException {
    this.hashedCC = hashedCC;
    this.amount = new BigDecimal(amount);
    this.dateOfTransaction = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateOfTransaction);
  }
}
