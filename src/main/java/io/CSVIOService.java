package io;

import domain.CCTransaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import parser.IParser;

public class CSVIOService implements IOService {

  private final String filePath;
  private final String csvRecordDelimiter;
  private final IParser ccTransactionParser;
  private static final Logger LOGGER = Logger.getLogger(IOService.class);

  public CSVIOService(String path, IParser ccTransactionParser) {
    this.filePath = path;
    csvRecordDelimiter = " ";
    this.ccTransactionParser = ccTransactionParser;
  }

  public ArrayList<CCTransaction> read() {
    ArrayList<String> data = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      data =
          br.lines()
              .map(line -> line.split(csvRecordDelimiter)[0])
              .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      e.printStackTrace();
    }
    LOGGER.log(Level.DEBUG, "CSVIO Service read done");
    return ccTransactionParser.parse(data);
  }

  public void display(ArrayList<String> fradulentCards) {
    if (fradulentCards.size() == 0)
      System.out.println("No fradulent cards found with the specified criteria");
    else {
      System.out.println("Fradulent Cards: ");
      fradulentCards.forEach(System.out::println);
    }
    LOGGER.log(Level.DEBUG, "CSVIO Service write done");
  }
}
