package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CCTransaction {
  private final String hashedCC;
  private final BigDecimal amount;
  private final LocalDateTime dateOfTransaction;

  public String getHashedCC() {
    return hashedCC;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public LocalDateTime getDateOfTransaction() {
    return dateOfTransaction;
  }

  public CCTransaction(String hashedCC, String dateOfTransaction, String amount) {
    this.hashedCC = hashedCC;
    this.amount = new BigDecimal(amount);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    this.dateOfTransaction = LocalDateTime.parse(dateOfTransaction, formatter);
  }
}
