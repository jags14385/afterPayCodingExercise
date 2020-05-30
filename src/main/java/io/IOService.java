package io;

import domain.CCTransaction;
import java.util.ArrayList;

public interface IOService {
  ArrayList<CCTransaction> read();
}
