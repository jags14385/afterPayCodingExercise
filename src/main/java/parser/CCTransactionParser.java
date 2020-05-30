package parser;

import domain.CCTransaction;
import java.text.ParseException;
import java.util.ArrayList;

public class CCTransactionParser implements IParser {
  private final String recordDataDelimiter;

  public CCTransactionParser() {
    recordDataDelimiter = ",";
  }

  public ArrayList<CCTransaction> parse(ArrayList<String> records) {
    ArrayList<CCTransaction> data = new ArrayList<>();
    for (String record : records) {
      String[] split = record.split(recordDataDelimiter);
      try {
        data.add(new CCTransaction(split[0], split[1], split[2]));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return data;
  }
}
