## Lab - Matrix Multiplication

## Objectives
- Understanding of programming concepts such as variables, loops, and conditional statements.
- Proficiency in creating and manipulating 2 dimensions arrays
- Ability to control user input and casting of data from one type to another
- Understanding of string formatting techniques to display in a readable and organized format.
- Ability to analyze the problem requirements and develop algorithms to solve mathematical problems

## Description
When multiplying a matrix `A` of dimensions `(n, m)` with a matrix `B` of dimensions `(m, P)` , we obtain a matrix `C` of dimensions `(n, P)`:
  ```Scripting
    A(n,m) * B(m,p) = C(n,p)
  ```

Matrix multiplication is performed by multiplying the components of the two matrices row by column:

Example:
```xml
  /       \     /     \      /                                     \
  | a b c |     | p q |      | a*p + b*r + c*t     a*q + b*s + c*u |
  | e f g |  *  | r s |  =   | e*p + f*r + g*t     e*q + f*s + g*u |
  | h i j |     | t u |      | h*p + i*r + j*t     h*q + i*s + j*u |
  | k l m |     \     /      | k*p + l*r + m*t     k*q + l*s + m*u |
  \       /                  \                                     /
```

## How does it work?
- User is asked to enter the size of the matrix A and B as shown below, and then it gets parsed and cleaned to avoid any mismatch
  ```java
    // Capture user input
  
    System.out.printf("Matrix %s: ", i == 0 ? "A" : "B");
    // Take matrix size from user
    String rawSize = prompt.nextLine();
    // Verify and convert matrix from string to array of integers
    int[] size = parseMatrixSize(rawSize);
  ```
  ```java
    // Parse user input to array of integers
    
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
  ```
  ```shell
    # Matrix size must two numbers separated by a comma.
  
    # For example:
    Matrix A: 2,3
  ```
- After matrix size, user has to provide data, row by row to match up the given matrix size, and column data must be separated by space.
  ```java
    // This snippet shows the validation process
    // Each row data is parsed and validated individually, so be sure to provide valid data according to the size you've provided
  
    int[][] matrix = new int[size[0]][size[1]];
    for (int rowNum = 0; rowNum < size[0]; rowNum++) {
      String row = prompt.nextLine();
      matrix[rowNum] = parseStringToIntArray(row, size[1], " ", "Invalid matrix row data - ");
    }
  ```
  ```shell
    # For example of a 2x3 array:
  
    12 2 4
    10 1 1
  ```
- Finally, hit `Enter` to see the result. The output should look like
  ```shell
    Matrix C:

    | 30  32 |
    | 22  23 |
  ```
