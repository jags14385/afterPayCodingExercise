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

  private boolean isSpendLimitBreached(BigDecimal totalSpend) {
    return (totalSpend.compareTo(this.spendLimit) > 0);
  }

  private boolean isCCFradulent(ArrayList<CCTransaction> transactions, String hashedCC) {
    CCTransaction trackingTransaction = transactions.get(0);
    BigDecimal currentTotalSpend = trackingTransaction.getAmount();

    if (isSpendLimitBreached(currentTotalSpend)) {
      LOGGER.log(Level.DEBUG, "TransactionValidator SpendLimit was breached for CC " + hashedCC);
      return true;
    }

    for (CCTransaction transaction : transactions.subList(1, transactions.size())) {
      Duration duration =
          Duration.between(
              transaction.getDateOfTransaction(), trackingTransaction.getDateOfTransaction());

      if (duration.getSeconds() <= numSecondsIn24Hrs) {
        currentTotalSpend = currentTotalSpend.add(transaction.getAmount());
        if (isSpendLimitBreached(currentTotalSpend)) {
          LOGGER.log(
              Level.DEBUG, "TransactionValidator SpendLimit was breached for CC " + hashedCC);
          return true;
        }
      } else {
        currentTotalSpend = transaction.getAmount();
        trackingTransaction = transaction;
      }
    }
    return currentTotalSpend.compareTo(spendLimit) > 0;
  }

  public ArrayList<String> validate(HashMap<String, ArrayList<CCTransaction>> hashMap) {
    ArrayList<String> fradulentHashedCCList = new ArrayList<>();
    if (hashMap.size() == 0) {
      LOGGER.log(Level.DEBUG, "TransactionValidator No Credit Cards found.");
      return new ArrayList<>(Collections.singleton("No Credit Cards"));
    }

    for (String hashedCC : hashMap.keySet()) {
      LOGGER.log(
          Level.DEBUG,
          "TransactionValidator Transaction Validation initiated for CC : " + hashedCC);
      ArrayList<CCTransaction> transactions = hashMap.get(hashedCC);
      boolean flag = isCCFradulent(transactions, hashedCC);
      if (flag) {
        fradulentHashedCCList.add(hashedCC);
      } else {
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
