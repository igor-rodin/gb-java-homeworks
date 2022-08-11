import java.util.Arrays;

public class NQeensProblem {
    public static void main(String[] args) {
        int boardSize = 7;
        boolean[][] chessBoard = new boolean[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(chessBoard[i], 0, boardSize, false);
        }

        if (nQeens(chessBoard, 0)) {
            printChessBoard(chessBoard);
        } else {
            System.out.printf("На доске размера %dx%d расставить ферзей так, чтобы они не били друг друга нельзя.\n",
                    boardSize, boardSize);
        }

    }

    // *Метод расстановки ферзей на шахматной доске так, чтобы ни один из них не бил
    // другого*/
    public static boolean nQeens(boolean[][] chessBoard, int col) {
        int boardSize = chessBoard.length;
        if (col == boardSize) {
            return true;
        }

        for (int row = 0; row < boardSize; row++) {
            if (isGoodPos(chessBoard, row, col)) {
                chessBoard[row][col] = true;
                if (nQeens(chessBoard, col + 1) == true)
                    return true;
                chessBoard[row][col] = false;
            }
        }

        return false;
    }

    // *Проверяет, можно ли поставить ферзя на клетку (row, col)*/
    private static boolean isGoodPos(boolean[][] chessBoard, int row, int col) {
        // Проверка горизонтали
        for (int i = 0; i < col; i++) {
            if (chessBoard[row][i]) {
                return false;
            }
        }
        // Проверка главной диагонали
        for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[j][i]) {
                return false;
            }
        }

        // Проверка побочной диагонали
        for (int i = col - 1, j = row + 1; i >= 0 && j < chessBoard.length; i--, j++) {
            if (chessBoard[j][i]) {
                return false;
            }
        }

        return true;
    }

    // *Выводит на консоль шахматную доску */
    private static void printChessBoard(boolean[][] chessBoard) {
        final char qeen = 'Q';
        final char empty = '_';
        final byte cellSpace = 4;

        char[] dashLine = new char[cellSpace * chessBoard.length + 1];
        Arrays.fill(dashLine, '-');

        System.out.println(dashLine);
        System.out.printf("Доска %dx%d\n", chessBoard.length, chessBoard.length);
        System.out.println(dashLine);

        for (int col = 0; col < chessBoard.length; col++) {
            for (int row = 0; row < chessBoard[0].length; row++) {
                if (chessBoard[row][col]) {
                    System.out.printf("| %s ", qeen);
                } else {
                    System.out.printf("| %s ", empty);
                }
            }
            System.out.printf("|\n");
        }
        System.out.println(dashLine);
    }

}