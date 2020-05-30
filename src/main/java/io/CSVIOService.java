package io;

import domain.CCTransaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVIOService implements IOService {

  private final String filePath;
  private final String csvRecordDelimiter;
  private final String recordDataDelimiter;

  public CSVIOService(String path) {
    this.filePath = path;
    csvRecordDelimiter = " ";
    recordDataDelimiter = ",";
  }

  public ArrayList<CCTransaction> read() {
    ArrayList<CCTransaction> data = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      while ((line = br.readLine()) != null) {
        String[] lineData = line.split(csvRecordDelimiter);
        String[] split = lineData[0].split(recordDataDelimiter);
        data.add(new CCTransaction(split[0], split[1], split[2]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }
}
