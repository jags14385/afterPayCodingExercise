package service;

import domain.CCTransaction;
import io.IOService;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import validation.IValidator;

public class CCFraudCheckService {
  private final IOService ioService;
  private final IValidator validator;
  private static final Logger LOGGER = Logger.getLogger(CCFraudCheckService.class);

  public HashMap<String, ArrayList<CCTransaction>> getHashMap() {
    return hashMap;
  }

  private final HashMap<String, ArrayList<CCTransaction>> hashMap;

  public CCFraudCheckService(IOService ioService, IValidator validator) {
    this.ioService = ioService;
    this.validator = validator;
    hashMap = new HashMap<>();
    initLoad();
  }

  private void initLoad() {
    for (CCTransaction record : ioService.read()) {
      ArrayList<CCTransaction> entry;
      if (!hashMap.containsKey(record.getHashedCC())) {
        entry = new ArrayList<>();
      } else {
        entry = hashMap.get(record.getHashedCC());
      }
      entry.add(record);
      hashMap.put(record.getHashedCC(), entry);
    }
    LOGGER.log(Level.DEBUG, "CCFraudCheckService initLoad done");
  }

  public void init() {
    ArrayList<String> fradulentCards = validator.validate(hashMap);
    ioService.display(fradulentCards);
  }
}
