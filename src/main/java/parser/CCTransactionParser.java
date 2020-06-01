package parser;

import domain.CCTransaction;
import java.util.ArrayList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CCTransactionParser implements IParser {
  private final String recordDataDelimiter;
  private static final Logger LOGGER = Logger.getLogger(IParser.class);

  public CCTransactionParser() {
    recordDataDelimiter = ",";
  }

  public ArrayList<CCTransaction> parse(ArrayList<String> records) {
    ArrayList<CCTransaction> data = new ArrayList<>();
    for (String record : records) {
      String[] split = record.split(recordDataDelimiter);
      data.add(new CCTransaction(split[0], split[1], split[2]));
    }
    LOGGER.log(Level.DEBUG, "parsing of data done");
    return data;
  }
}
