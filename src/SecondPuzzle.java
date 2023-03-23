import java.util.ArrayList;

public class SecondPuzzle {
    private int row; //row of this puzzle
    private int column; //column of this puzzle

    private int n; //number of words to be solved
    private ArrayList<Slot>[][] grid; //2-d array to create the puzzle

    public SecondPuzzle(int row, int column, int n) {
        this.row = row;
        this.column = column;
        this.n = n;
        this.grid = new ArrayList[row][column];
        this.initializeGrid();
    }

    /**
     * initializes a new arraylist in each cell
     */
    private void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    /**
     * place reference of slot for the cell they belong to
     * @param startRow --row of the slot
     * @param startColumn --column of the slot
     * @param until -- size of the slot
     * @param slot -- reference to the slot
     */

    public void claimHorizontalSlots(int startRow, int startColumn, int until, Slot slot) {

        int column = startColumn;
        int row = grid.length - 1 - startRow;
        for (int i = column; i < until + column; i++) {
            ArrayList<Slot> slots = grid[row][i];

            if (!slots.isEmpty()) {
                for (Slot existingSlot : slots) {
                    existingSlot.addSlot(slot);
                    slot.addSlot(existingSlot);
                }
            }
            slots.add(slot);
        }
    }
    /**
     * place reference of slot for the cell they belong to
     * @param startRow --row of the slot
     * @param startColumn --column of the slot
     * @param until -- size of the slot
     * @param slot -- reference to the slot
     */
    public void claimVerticalSlots(int startRow, int startColumn, int until, Slot slot) {
        int column = startColumn;
        int row = grid.length - 1 - startRow;

        for (int i = row; i < row + until; i++) {
            ArrayList<Slot> slots =  grid[i][column];

            if (!slots.isEmpty()) {
                for (Slot existingSlot : slots) {
                    existingSlot.addSlot(slot);
                    slot.addSlot(existingSlot);
                }
            }
            slots.add(slot);
        }
    }

    /**
     * Get the 2-d array of the second puzzle
     * @return -- 2d array of the second puzzle
     */
    public ArrayList<Slot>[][] getGrid() {
        return grid;
    }
}



