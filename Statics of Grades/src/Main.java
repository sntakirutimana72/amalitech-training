import java.util.Scanner;

public class Main {
  private static int[] getScores() {
    Scanner prompt = new Scanner(System.in);
    System.out.println("Enter student scores separated by spaces: ");
    String scoreString = prompt.nextLine();
    String[] scoreStringArray = scoreString.split(" ");
    int[] scores = new int[scoreStringArray.length];

    for (int i = 0; i < scoreStringArray.length; i++) {
      try {
        scores[i] = Integer.parseInt(scoreStringArray[i]);
      } catch (NumberFormatException e) {
        System.err.println("Invalid input: " + scoreStringArray[i] + " is not an integer.");
        System.exit(0);
      }
    }
    return scores;
  }

  public static void main(String[] args) {
    int[] scores = getScores();
    GradesRepository grader = new GradesRepository(scores);

    grader.getMaximumGrade();
    grader.getMinimumGrade();
    grader.getAverage();
    grader.plot();
  }
}