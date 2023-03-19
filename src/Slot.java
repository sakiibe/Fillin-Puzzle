import java.util.*;

public class Slot {
    private int row;
    private int column;
    private int size;

    private Orientation orientation;

    private Set<Slot> connectedSlots;

    private ArrayList<String> possibleWords;

    private int wordIdx;

    public Slot(int row, int column, int size, Orientation orientation) {
        this.row = row;
        this.column = column;
        this.size = size;
        this.orientation = orientation;
        this.connectedSlots = new HashSet<Slot>();
        this.possibleWords = new ArrayList<>();
        this.wordIdx = 0;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Set<Slot> getConnectedSlots() {
        return connectedSlots;
    }

    public void addSlot(Slot slot) {
        this.connectedSlots.add(slot);
    }

    public boolean addPossibleWord(String word) {
        if (possibleWords.contains(word)) {
            return false;
        }
        this.possibleWords.add(word);
        return true;
    }


    public boolean insertWord(Puzzle puzzle, Set<String> visitedWordSet) {

        for (int i = this.wordIdx; i < possibleWords.size(); i++) {
            if (visitedWordSet.contains(possibleWords.get(i))){
                continue;
            }
            if (this.orientation == Orientation.Horizontal) {
                if (puzzle.placeWordHorizontal(this, possibleWords.get(i))) {
                    this.wordIdx = i;
                    return true;
                }
            } else if (this.orientation == Orientation.Vertical) {
                if (puzzle.placeWordVertical(this, possibleWords.get(i))) {
                    this.wordIdx = i;
                    return true;
                }
            }
            //reached the end of possible words, still no word fits
            if (i == possibleWords.size() - 1) {
                //reset pointer
                this.wordIdx = 0;
            }
        }

        return false;
    }

    public int getWordIdx() {
        return wordIdx;
    }

    public ArrayList<String> getPossibleWords() {
        return possibleWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return getRow() == slot.getRow() && getColumn() == slot.getColumn() && getSize() == slot.getSize() && getOrientation() == slot.getOrientation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn(), getSize(), getOrientation());
    }
}
