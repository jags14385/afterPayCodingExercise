package parser;

import domain.CCTransaction;
import java.util.ArrayList;

public interface IParser {
  ArrayList<CCTransaction> parse(ArrayList<String> records);
}
