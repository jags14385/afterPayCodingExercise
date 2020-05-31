package validation;

import domain.CCTransaction;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class TransactionValidator implements IValidator {
  private final BigDecimal spendLimit;

  public TransactionValidator(BigDecimal spendLimit) {
    this.spendLimit = spendLimit;
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

      for (int upperSectionCounter = 1;
          upperSectionCounter < transactions.size();
          upperSectionCounter++) {
        totalSpend = transactions.get(upperSectionCounter).getAmount();
        // More than 1 transactions for Hashed CC
        for (int ctr = upperSectionCounter + 1; ctr < transactions.size(); ctr++) {
          Duration duration =
              Duration.between(
                  transactions.get(upperSectionCounter).getDateOfTransaction(),
                  transactions.get(ctr).getDateOfTransaction());

          if (duration.getSeconds() <= 86400) {
            totalSpend = totalSpend.add(transactions.get(ctr).getAmount());
            ctr++;
          }
        }
        if (totalSpend.compareTo(spendLimit) > 0) {
          fradulentHashedCCList.add(hashedCC);
          break;
        }
      }
    }
    return fradulentHashedCCList;
  }
}
