package domain;

import java.math.BigDecimal;

public class CCTransaction {
  private final String hashedCC;
  private final BigDecimal amount;
  private final String dateOfTransaction;

  public String getHashedCC() {
    return hashedCC;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getDateOfTransaction() {
    return dateOfTransaction;
  }

  public CCTransaction(String hashedCC, String dateOfTransaction, String amount) {
    this.hashedCC = hashedCC;
    this.amount = new BigDecimal(amount);
    this.dateOfTransaction = dateOfTransaction;
  }
}
