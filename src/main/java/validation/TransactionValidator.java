package validation;

import domain.CCTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class TransactionValidator implements IValidator {
  private final BigDecimal spendLimit;

  public TransactionValidator(BigDecimal spendLimit) {
    this.spendLimit = spendLimit;
  }

  public ArrayList<String> validate(HashMap<String, ArrayList<CCTransaction>> hashMap) {
    ArrayList<String> fradulentHashedCC = new ArrayList<>();
    for (String hashedCC : hashMap.keySet()) {
      BigDecimal totalSpend = new BigDecimal(0);
      for (CCTransaction record : hashMap.get(hashedCC)) {
        totalSpend = totalSpend.add(record.getAmount());
      }
      if (totalSpend.compareTo(spendLimit) > 0) {
        fradulentHashedCC.add(hashedCC);
      }
    }
    return fradulentHashedCC;
  }
}
