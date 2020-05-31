package validation;

import domain.CCTransaction;
import java.util.ArrayList;
import java.util.HashMap;

public interface IValidator {
  ArrayList<String> validate(HashMap<String, ArrayList<CCTransaction>> hashMap);
}
