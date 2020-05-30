package io;

import domain.CCTransaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import parser.IParser;

public class CSVIOService implements IOService {

  private final String filePath;
  private final String csvRecordDelimiter;
  private final IParser ccTransactionParser;

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
    return ccTransactionParser.parse(data);
  }
}
