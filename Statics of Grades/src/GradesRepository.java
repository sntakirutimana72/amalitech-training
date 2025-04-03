import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class GradesRepository {
  private final int[] scores, stats;

  public GradesRepository(int[] studentScores) {
    scores = studentScores;
    stats = new int[5];

    analyzeScores();
  }

  private void analyzeScores() {
    for (int score : scores) {
      if (score <= 20) {
        stats[0]++;
      } else if (score <= 40) {
        stats[1]++;
      } else if (score <= 60) {
        stats[2]++;
      } else if (score <= 80) {
        stats[3]++;
      } else {
        stats[4]++;
      }
    }
  }

  private int getGraphMaxLevel() {
    int level = stats[0];
    for (int i = 1; i < stats.length; i++) {
      if (stats[i] > level) {
        level = stats[i];
      }
    }
    return level;
  }

  public void plot() {
     int level = getGraphMaxLevel();

     for (int i = level; i > 0; i--) {
       System.out.printf("%d >", i);
       for (int stat : stats) {
         if (stat >= i) {
           System.out.print("  #######   ");
         } else {
           System.out.print(" ".repeat(12));
         }
       }
       System.out.println();
     }
     String underscores = "-".repeat(11);
     System.out.printf("  %s+-\n", "+".concat(underscores).repeat(5));
     System.out.print("  |    0-20  ");
     System.out.print(" |   21-40  ");
     System.out.print(" |   41-60  ");
     System.out.print(" |   61-80  ");
     System.out.print(" |   81-100  |\n");
  }

  public void getMaximumGrade() {
    int max = scores[0];
    for (int i = 1; i < scores.length; i++) {
      if (scores[i] > max) {
        max = scores[i];
      }
      System.out.printf("The maximum grade is %d\n", max);
    }
  }

  public void getMinimumGrade() {
    int min = scores[0];
    for (int i = 1; i < scores.length; i++) {
      if (scores[i] > min) {
        min = scores[i];
      }
      System.out.printf("The minimum grade is %d\n", min);
    }
  }

  public void getAverage() {
    OptionalDouble average = IntStream.of(scores).average();
    if (average.isPresent()) {
      System.out.printf("The average grade is %f\n", average.getAsDouble());
    } else {
      System.out.println("No data available");
    }
  }
}
