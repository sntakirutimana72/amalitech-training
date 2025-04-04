public class Operations {
  public double add(double left, double right) {
    return left + right;
  }

  public double subtract(double left, double right) {
    return left - right;
  }

  public double times(double left, double right) {
    return left * right;
  }

  public double divide(double dividend, double divisor) {
    return dividend / divisor;
  }

  public String format(double result) {
    String resultAsStr = String.valueOf(result);
    int dotIndex = resultAsStr.indexOf('.');
    String floating = resultAsStr.substring(dotIndex + 1);
    StringBuilder newFloating = new StringBuilder();
    int lengthOfFloating = floating.length() - 1;

    for (int i = lengthOfFloating; i > 0; i--) {
      char digit = floating.charAt(i);

      if (digit != '0' && !newFloating.isEmpty()) {
        newFloating.insert(0, digit);
      }
    }

    if (newFloating.isEmpty()) {
      return resultAsStr.substring(0, dotIndex);
    }
    return resultAsStr.substring(0, dotIndex + 1).concat(newFloating.toString());
  }
}
