public class MatrixPrinter {
    public static void printMatrix(int[][] matrix, int N) {
        int rows = N;
        int columns = N;

        int paddingWidth = String.valueOf(rows - 1).length() + 8;

        // Print column labels
        System.out.print("         ");
        for (int i = 0; i < columns; i++) {
            String paddedValue = String.format("%" + (paddingWidth) + "s", "Driver " + (i + 1));
            System.out.print(paddedValue);
        }
        System.out.println();

        // Print each row with row label
        for (int i = 0; i < rows; i++) {
            String rowLabel = "Rider " + (char) (i + 'A') + "  ";
            System.out.print(rowLabel);
            
            for (int j = 0; j < columns; j++) {
                String paddedValue = String.format("%" + paddingWidth + "d", matrix[i][j]);
                System.out.print(paddedValue);
            }
            System.out.println();
        }
    }
}
