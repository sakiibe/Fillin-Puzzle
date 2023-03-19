import java.util.ArrayList;

/**
 * Captures the puzzle in the form of a 2d array
 */
public class Puzzle {

    private int row;
    private int column;
    private int numberOfWords;
    private char[][] grid;

    private SecondPuzzle secondPuzzle;

    public Puzzle(int row, int column, int numberOfWords, SecondPuzzle secondPuzzle) {
        this.row = row;
        this.column = column;
        this.numberOfWords = numberOfWords;
        this.grid = new char[row][column];
        this.secondPuzzle = secondPuzzle;
    }

    /**
     * Checks if the word can be placed in this slot without causing collision with other slots.
     * If it passes the check, inserts it into the slot.
     *
     * @param slot -- slot being checked for insertion
     * @param word -- word being inserted
     * @return -- True if word can be placed and insertion is successful. Otherwise, return false
     */
    public boolean placeWordHorizontal(Slot slot, String word) {
        int column = slot.getColumn();
        int row = grid.length - 1 - slot.getRow();
        int size = slot.getSize();

        for (int i = column, wordIdx = 0; i < size + column; i++, wordIdx++) {
            char currentCell = grid[row][i];

            boolean isPlaceHolderValue = currentCell == Character.valueOf('.');
            boolean sameCharacter = (currentCell == word.charAt(wordIdx));

            if (!(isPlaceHolderValue || sameCharacter)) {
                return false;
            }

        }
        for (int i = column, wordIdx = 0; i < size + column; i++, wordIdx++) {
            grid[row][i] = word.charAt(wordIdx);
        }
        return true;
    }

    /**
     * Checks if the word can be placed in this slot without causing collision with other slots.
     * If it passes the check, inserts it into the slot.
     *
     * @param slot -- slot being checked for insertion
     * @param word -- word being inserted
     * @return -- True if word can be placed and insertion is successful. Otherwise, return false
     */
    public boolean placeWordVertical(Slot slot, String word) {
        int column = slot.getColumn();
        int row = grid.length - 1 - slot.getRow();
        int size = slot.getSize();


        for (int i = row, wordIdx = 0; i < row + size; i++, wordIdx++) {
            char currentCell = grid[i][column];

            boolean notPlaceHolderValue = currentCell != Character.valueOf('.');
            boolean notSameCharacter = (currentCell != word.charAt(wordIdx));

            if ((notPlaceHolderValue && notSameCharacter)) {
                return false;
            }
        }
        for (int i = row, wordIdx = 0; i < row + size; i++, wordIdx++) {
            grid[i][column] = word.charAt(wordIdx);
        }
        return true;
    }

    public void removeHorizontal(int startRow, int startColumn, int until) {
        int column = startColumn;
        int row = grid.length - 1 - startRow;
        for (int i = column; i < until + column; i++) {
            grid[row][i] = '.';
        }
    }
    /**
     * places '.' in a vertical slot
     * @param startRow -- starting row of the slot
     * @param startColumn -- ending row of the slot
     * @param until -- size of the slot
     */
    public void removeVertical(int startRow, int startColumn, int until) {
        int column = startColumn;
        int row = grid.length - 1 - startRow;

        for (int i = row; i < row + until; i++) {
            grid[i][column] = '.';
        }
    }

    public void fillHorizontalSlot(int startRow, int startColumn, int until) {
        int column = startColumn;
        int row = grid.length - 1 - startRow;

        for (int i = column; i < until + column; i++) {
            grid[row][i] = '.';
        }
    }

    public void fillVerticalSlot(int startRow, int startColumn, int until) {
        int column = startColumn;
        int row = grid.length - 1 - startRow;

        for (int i = row; i < row + until; i++) {
            grid[i][column] = '.';
        }
    }
    /**
     * Get the 2d array contating the puzzle
     *
     * @return -- 2d array puzzle
     */
    public char[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0; i<grid.length;i++){
            for (int j=0; j<grid[i].length; j++){
                if (grid[i][j] == '\0') {
                    stringBuilder.append("  ");
                } else {
                    stringBuilder.append(grid[i][j]+" ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
