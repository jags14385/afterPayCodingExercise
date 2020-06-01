package validation;

import domain.CCTransaction;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
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

    for (String hashedCC : hashMap.keySet()) {
      ArrayList<CCTransaction> transactions = hashMap.get(hashedCC);
      BigDecimal totalSpend = transactions.get(0).getAmount();
      // Scenario : Where there is only one transaction for a Hashed CC
      if (totalSpend.compareTo(spendLimit) > 0) {
        fradulentHashedCCList.add(hashedCC);
      }
      LOGGER.log(Level.DEBUG, "TransactionValidator  transaction Number 1 done");
      for (int upperSectionCounter = 1;
          upperSectionCounter < transactions.size();
          upperSectionCounter++) {
        totalSpend = transactions.get(upperSectionCounter).getAmount();
        // More than 1 transactions for Hashed CC
        int ctr = upperSectionCounter + 1;
        while (ctr < transactions.size()) {
          Duration duration =
              Duration.between(
                  transactions.get(upperSectionCounter).getDateOfTransaction(),
                  transactions.get(ctr).getDateOfTransaction());
          LOGGER.log(Level.DEBUG, "TransactionValidator  Duration Check:" + duration.getSeconds());
          if (duration.getSeconds() <= numSecondsIn24Hrs) {
            totalSpend = totalSpend.add(transactions.get(ctr).getAmount());
            ctr++;
          } else {
            // Because the transactions are in chronological order, if this particular transaction
            // duration diff is more than 24hrs, rest of them would be also.
            break;
          }
        }
        if (totalSpend.compareTo(spendLimit) > 0) {
          fradulentHashedCCList.add(hashedCC);
          break;
        }
      }
    }
    LOGGER.log(
        Level.DEBUG,
        "TransactionValidator Number of fradulent Hashed CCList : " + fradulentHashedCCList.size());
    return fradulentHashedCCList;
  }
}
