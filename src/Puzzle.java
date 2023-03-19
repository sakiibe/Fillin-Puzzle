/**
 * Captures the puzzle in the form of a 2d array
 */
public class Puzzle {

    private int row;
    private int column;
    private int numberOfWords;
    private char[][] grid;

    public Puzzle(int row, int column, int numberOfWords) {
        this.row = row;
        this.column = column;
        this.numberOfWords = numberOfWords;
        this.grid = new char[row][column];
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

            boolean isPlaceHolderValue = currentCell == Character.valueOf('.');
            boolean sameCharacter = (currentCell == word.charAt(wordIdx));

            if (!(isPlaceHolderValue || sameCharacter)) {
                return false;
            }
        }
        for (int i = row, wordIdx = 0; i < row + size; i++, wordIdx++) {
            grid[i][column] = word.charAt(wordIdx);
        }
        return true;
    }
    /**
     * places '.' in a horizontal slot
     * @param startRow -- starting row of the slot
     * @param startColumn -- ending row of the slot
     * @param until -- size of the slot
     */
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

    /**
     * Get the 2d array contating the puzzle
     * @return -- 2d array puzzle
     */
    public char[][] getGrid() {
        return grid;
    }


}
