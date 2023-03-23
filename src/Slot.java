/**
 * Capture empty spaces in a puzzle
 */


import java.util.*;

public class Slot {
    private int row;
    private int column;
    private int size;

    private Orientation orientation;

    private Set<Slot> connectedSlots;  //capture slots that this slot share a cell with

    private ArrayList<String> possibleWords;  //same sized words for this slot

    private int wordIdx;    //index of word in the arraylist placed for this slot
    private String wordUsed;    //word placed in the puzzle for this slot

    //words placed in this puzzle. this includes words that were placed successfully,
    // but later had to be removed on backtracking
    private Set wordsAttempted;
    public Slot(int row, int column, int size, Orientation orientation) {
        this.row = row;
        this.column = column;
        this.size = size;
        this.orientation = orientation;
        this.connectedSlots = new HashSet<Slot>();
        this.possibleWords = new ArrayList<>();
        this.wordIdx = 0;
        this.wordsAttempted= new HashSet<>();
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


    /**
     * Insert one of the words from it's possibleWord list that isn't on the visited wordSet
     * @param puzzle --puzzle being solved
     * @param visitedWordSet -- words already placed in the puzzle
     * @return -- Return true if the word could be successfully placed. Otherwise, return false.
     */
    public boolean insertWord(Puzzle puzzle, Set<String> visitedWordSet) {
        if (allPossibilitiesExplored(visitedWordSet)){
            this.wordsAttempted.clear();
            return false;
        }
        for (int i = 0; i < possibleWords.size(); i++) {

            if (visitedWordSet.contains(possibleWords.get(i)) || wordsAttempted.contains(possibleWords.get(i))){
                continue;
            }
            wordsAttempted.add(possibleWords.get(i));
            if (this.orientation == Orientation.Horizontal) {
                if (puzzle.placeWordHorizontal(this,possibleWords.get(i) )) {
                    this.wordUsed= possibleWords.get(i);
                    this.wordIdx = i;
                    return true;
                }
            } else if (this.orientation == Orientation.Vertical) {
                if (puzzle.placeWordVertical(this,possibleWords.get(i) )) {
                    this.wordUsed= possibleWords.get(i);
                    this.wordIdx = i;
                    return true;
                }
            }


        }

        return false;
    }

    /**
     * Checks if all possible words are explored for this slot. This is used to know if the wordsAttempted slot should reset
     * @param visitedWordSet -- words already placed in the puzzle
     * @return -- true if there are no words that can be placed for this slot. Otherwise, return false.
     */
    public boolean allPossibilitiesExplored(Set<String> visitedWordSet){
        int count=0;
        for (String visitedWord:visitedWordSet){
            if (visitedWord.length()==this.size){
                count++;
            }
        }
        if (count+this.wordsAttempted.size()==possibleWords.size()){
            return true;
        }else {
            return false;
        }
    }
    public int getWordIdx() {
        return wordIdx;
    }


    public ArrayList<String> getPossibleWords() {
        return possibleWords;
    }

    public void setWordIdx(int wordIdx) {
        this.wordIdx = wordIdx;
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

    public void setWordUsed(String wordUsed) {
        this.wordUsed = wordUsed;
    }

    public String getWordUsed() {
        return wordUsed;
    }
}
