//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    Operations calc = new Operations();
    double sum = calc.add(5, 6);  // Should equal 11
    double quotient = calc.divide(9, 2);  // Should equal 4.5
    double difference = calc.subtract(13, 7);  // Should equal 6
    double product = calc.times(3, 9);  // Should equal 27

    System.out.printf("5 + 6 = %s\n", calc.format(sum));
    System.out.printf("9 / 2 = %s\n", calc.format(quotient));
    System.out.printf("13 - 7 = %s\n", calc.format(difference));
    System.out.printf("3 * 9 = %s\n", calc.format(product));
  }
}
