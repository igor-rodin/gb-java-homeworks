import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NKnightsProblem {
    // *Максимальный размер доски - 127 */
    private static final byte boardSize = 8;
    private static final byte[][] knightDelta = { { -1, 1 }, { -2, 2 } };

    public static void main(String[] args) {
        int[][] chessBoard = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(chessBoard[i], 0, boardSize, -1);
        }

        final byte startRow = 0;
        final byte startCol = 0;
        chessBoard[startRow][startCol] = 1;
        byte[] startCell = { startRow, startCol };
        if (nKnights(chessBoard, startCell, 1)) {
            printChessBoard(chessBoard);
        } else {
            System.out.printf("Доску размера %dx%d нельзя обойти конем.\n",
                    boardSize, boardSize);
        }

    }

    // *Метод обхода конем шахматной доске так, чтобы фигура в клетке
    // была строго один раз*/
    private static boolean nKnights(int[][] chessBoard, byte[] cell, int movesCount) {
        if (movesCount == boardSize * boardSize) {
            return true;
        }

        List<byte[]> availableMoves = getKnightMoves(cell, chessBoard);
        for (byte[] nextCell : availableMoves) {
            chessBoard[nextCell[0]][nextCell[1]] = movesCount + 1;
            if (nKnights(chessBoard, nextCell, movesCount + 1)) {
                return true;
            }
            chessBoard[nextCell[0]][nextCell[1]] = -1;
        }

        return false;
    }

    // *Метод проверяет, можно сделать ход в ячейку (row, col) */
    private static boolean isCellValid(byte row, byte col, int[][] board) {
        return row >= 0 && col >= 0 && row < boardSize && col < boardSize && board[row][col] == -1;
    }

    // *Метод возвращает все доступные ходы из ячейки cell */
    private static List<byte[]> getKnightMoves(byte[] cell, int[][] board) {
        List<byte[]> availabelMoves = new ArrayList<>();

        for (int i = 0; i < knightDelta.length; i++) {
            for (byte x : knightDelta[i]) {
                for (byte y : knightDelta[(i + 1) % 2]) {
                    byte nextRow = (byte) (cell[0] + x);
                    byte nextCol = (byte) (cell[1] + y);
                    if (isCellValid(nextRow, nextCol, board)) {
                        byte[] nextCell = { nextRow, nextCol };
                        availabelMoves.add(nextCell);
                    }
                }
            }
        }
        return availabelMoves;
    }

    // *Выводит на консоль шахматную доску */
    private static void printChessBoard(int[][] chessBoard) {
        final byte cellSpace = 5;

        char[] dashLine = new char[cellSpace * chessBoard.length + 1];
        Arrays.fill(dashLine, '-');

        System.out.println(dashLine);
        System.out.printf("Доска %dx%d\n", chessBoard.length, chessBoard.length);
        System.out.println(dashLine);

        for (int col = 0; col < chessBoard.length; col++) {
            for (int row = 0; row < chessBoard[0].length; row++) {
                System.out.printf("| %2d ", chessBoard[row][col]);
            }
            System.out.printf("|\n");
        }
        System.out.println(dashLine);
    }
}
