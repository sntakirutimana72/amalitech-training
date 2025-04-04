import java.util.Scanner;
import java.util.Stack;

public class Main {
  private static void displayError(String message) {
    System.err.println(message);
    System.exit(0);
  }

  private static int[] getMatrixSize(Scanner prompt, int index) {
    System.out.printf("Matrix %s: ", index == 0 ? "A" : "B");
    // Take matrix size from user
    String rawSize = prompt.nextLine();
    int[] size = parseStringToIntArray(rawSize, 2, ",", "Invalid matrix size format - ");
    if (size[0] < 1 || size[1] < 1)
      displayError("Row or column size should be >= 1");
    return size;
  }

  private static int[] getMatrixRow(Scanner prompt, int size) {
    String rawData = prompt.nextLine();

    return parseStringToIntArray(rawData, size, " ", "Invalid matrix row data - ");
  }

  private static int[] parseStringToIntArray(String raw, int size, String sep, String msg) {
    int[] data = new int[size];
    String[] stringRow = raw.trim().split(sep);
    if (stringRow.length != size)
      displayError(msg + raw);
    for (int i = 0; i < size; i++) {
      try {
        data[i] = Integer.parseInt(stringRow[i]);
      } catch (NumberFormatException e) {
        displayError("Invalid matrix data format - " + raw);
      }
    }
    return data;
  }

  private static void getProduct() {
    // Get matrices data first
    Stack<int[][]> matrices = getMatrices();
    int[][] secondMatrix = matrices.pop();
    int[][] firstMatrix = matrices.pop();
    // Matrix sizes
    int firstRowSize = firstMatrix.length;
    int firstColSize = firstMatrix[0].length;
    int secondColSize = secondMatrix[0].length;
    // Product matrix
    int[][] product = new int[firstRowSize][secondColSize];
    // For graphical presentation
    int colMax = 0;

    for (int i = 0; i < firstRowSize; i++) {
      for (int j = 0; j < secondColSize; j++) {
        for (int k = 0; k < firstColSize; k++) {
          product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
          if (product[i][j] > colMax)
            colMax = product[i][j];
        }
      }
    }

    System.out.println("Matrix C:");

    for (int i = 0; i < firstRowSize; i++) {
      System.out.print("|");
      for (int col : product[i]) {
        int colMaxLength = String.valueOf(colMax).length();
        int fallbackSpaceSize = colMaxLength - String.valueOf(col).length() + 1;
        System.out.printf("%s%d", " ".repeat(fallbackSpaceSize), col);
      }
      System.out.print(" |\n");
    }
  }

  private static Stack<int[][]> getMatrices() {
    // This is used to check the compatibility of our matrices
    // The idea is, first matrix column size must be equal to second matrix row size
    Integer firstColSize = null;
    // A stack container to hold our matrices as they are parsed and cleaned
    Stack<int[][]> matrices = new Stack<>();
    // A system stdin stream for reading user text input from keyboard
    Scanner prompt = new Scanner(System.in);

    for (int i = 0; i < 2; i++) {
      // Verify and convert matrix from string to array of integers
      int[] size = getMatrixSize(prompt, i);
      /* Check if :firstColSize has been assigned
       * For our matrices to be compatible, column size of the first matrix must
       * equal the row size of the second matrix
       */
      if (firstColSize == null)
        firstColSize = size[1];
      else if (firstColSize != size[0])
        displayError("Matrices have mismatched sizes");
      // Take matrix data from user
      int[][] matrix = new int[size[0]][size[1]];
      // Iterate over the row size to capture all required row data
      for (int j = 0; j < size[0]; j++) {
        // Get and parse row data input from user
        matrix[j] = getMatrixRow(prompt, size[1]);
      }
      // Update our matrix stack
      matrices.push(matrix);
      // Add new line
      System.out.println();
    }
    return matrices;
  }

  public static void main(String[] args) {
    // Compute the product of two 2D arrays and display the result
    getProduct();
  }
}
