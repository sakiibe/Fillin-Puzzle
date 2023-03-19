import java.util.ArrayList;

public class SecondPuzzle {
    private int row;
    private int column;

    private int n;
    private ArrayList<Slot>[][] grid;

    public SecondPuzzle(int row, int column, int n) {
        this.row = row;
        this.column = column;
        this.n = n;
        this.grid = new ArrayList[row][column];
        this.initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

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

    public ArrayList<Slot>[][] getGrid() {
        return grid;
    }
}



