package validation;

import domain.CCTransaction;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TransactionValidator implements IValidator {
  private final BigDecimal spendLimit;
  private final int numSecondsIn24Hrs;
  private static final Logger LOGGER = Logger.getLogger(IValidator.class);

  public TransactionValidator(BigDecimal spendLimit) {
    this.spendLimit = spendLimit;
    numSecondsIn24Hrs = 86400;
  }

  public ArrayList<String> validate(HashMap<String, ArrayList<CCTransaction>> hashMap) {
    ArrayList<String> fradulentHashedCCList = new ArrayList<>();

    if (hashMap.size() == 0) {
      return new ArrayList<>(Collections.singleton("No Transactions"));
    }

    for (String hashedCC : hashMap.keySet()) {
      ArrayList<CCTransaction> transactions = hashMap.get(hashedCC);
      CCTransaction trackingTransaction = transactions.get(0);
      BigDecimal totalSpend = trackingTransaction.getAmount();
      LOGGER.log(Level.DEBUG, "TransactionValidator Transaction for CC " + hashedCC);

      if (totalSpend.compareTo(spendLimit) > 0) {
        fradulentHashedCCList.add(hashedCC);
        LOGGER.log(Level.DEBUG, "TransactionValidator SpendLimit was breached for CC " + hashedCC);
        continue;
      }

      for (CCTransaction transaction : transactions.subList(1, transactions.size())) {

        Duration duration =
            Duration.between(
                transaction.getDateOfTransaction(), trackingTransaction.getDateOfTransaction());

        if (duration.getSeconds() <= numSecondsIn24Hrs) {
          totalSpend = totalSpend.add(transaction.getAmount());
        } else {
          totalSpend = transaction.getAmount();
          trackingTransaction = transaction;
        }

        if (totalSpend.compareTo(spendLimit) > 0) {
          LOGGER.log(Level.DEBUG, "TransactionValidator SpendLimit breached for CC " + hashedCC);
          fradulentHashedCCList.add(hashedCC);
          break;
        }
      }

      if (totalSpend.compareTo(spendLimit) <= 0) {
        LOGGER.log(
            Level.DEBUG, "TransactionValidator SpendLimit never breached for CC " + hashedCC);
      }
    }

    LOGGER.log(
        Level.DEBUG,
        "TransactionValidator Number of fradulent Hashed CCList : " + fradulentHashedCCList.size());
    return fradulentHashedCCList;
  }
}
