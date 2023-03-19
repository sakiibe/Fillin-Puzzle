import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FillInPuzzle {

    private Puzzle puzzle;
    private SecondPuzzle secondPuzzle;
    private HashMap<Integer, ArrayList<Slot>> slotMap;
    private List<Slot> slots;
    private static Set<String> visitedWord;

    private int choice;

    public FillInPuzzle() {
        this.slots = new ArrayList<>();
        this.slotMap = new HashMap<>();
        this.visitedWord = new HashSet<>();
        this.choice=0;
    }

    /**
     * Read lines from stream and construct puzzle, slot (empty spaces), and add
     * word to same length slots
     *
     * @param stream -- stream being read
     * @return -- True if finished reading successfully. Otherwise, return false
     */
    public Boolean loadPuzzle(BufferedReader stream) {

        if (stream != null) {
            try {
                //first line will contain puzzle dimension and number of words to be solved
                String line = stream.readLine();

                String puzzleSpecs[] = line.split(" ");

                try {
                    int column = Integer.parseInt(puzzleSpecs[0]);
                    int row = Integer.parseInt(puzzleSpecs[1]);
                    int numberOfWords = Integer.parseInt(puzzleSpecs[2]);

                    this.puzzle = new Puzzle(row, column, numberOfWords);
                    this.secondPuzzle = new SecondPuzzle(row, column, numberOfWords);

                    //read empty spaces in puzzles (slots for the context of this program)
                    for (int i = 0; i < numberOfWords; i++) {
                        String wordLine = stream.readLine();
                        String[] words = wordLine.split(" ");
                        column = Integer.parseInt(words[0]);
                        row = Integer.parseInt(words[1]);
                        int size = Integer.parseInt(words[2]);

                        //horizontal slot
                        if (words[3].equalsIgnoreCase("h")) {
                            //fill that space with '.'
                            puzzle.removeHorizontal(row, column, size);

                            Slot slot = new Slot(row, column, size, Orientation.Horizontal);

                            //add slot to set
                            this.slots.add(slot);

                            //map this slot according to it's size
                            mapToSlot(slot);

                            //add slot reference to second puzzles 2-d array
                            secondPuzzle.claimHorizontalSlots(row, column, size, slot);


                        }
                        //vertical slot
                        else if (words[3].equalsIgnoreCase("v")) {
                            //fill that space with '.'
                            puzzle.removeVertical(row, column, size);

                            Slot slot = new Slot(row, column, size, Orientation.Vertical);

                            //add slot to set
                            this.slots.add(slot);

                            //map this slot according to it's size
                            mapToSlot(slot);

                            //add slot reference to second puzzles 2-d array
                            secondPuzzle.claimVerticalSlots(row, column, size, slot);
                        } else {
                            //if "h" or "v" is not given for the line
                            return false;
                        }
                    }
                    //read words from stream
                    for (int i = 0; i < numberOfWords; i++) {
                        //read words
                        String word1 = stream.readLine();

//                        ArrayList<Slot> sameLengthSlotList = slotMap.get(word.length());
                        //add word to same length slots and return false if duplicate exists
                        for (Slot currentSlot : slots) {
                            if (currentSlot.getSize()==word1.length()) {
                                currentSlot.addPossibleWord(word1);
                            }
                        }

                    }

                } catch (NumberFormatException e) {
                    //invalid input, return false
                    return false;
                }


            } catch (IOException e) {
                //an error occured, return false
                return false;
            } catch (Exception e){
                return false;
            }
        }
        return true;
    }

    public Boolean solve() {

//       solveUniqueSlotSizeEntires();

        Stack<Slot> slotStack = new Stack<>();

        for (Slot slot : slots) {
            slotStack.add(slot);
        }

        if (Solution(slotStack, visitedWord)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean Solution(Stack<Slot> slotStack, Set<String> visitedWord) {

        if (slotStack.isEmpty()) {
            return true;
        }

        Slot slot = slotStack.pop();

        if (slot.insertWord(puzzle, visitedWord)) {
            visitedWord.add(slot.getPossibleWords().get(slot.getWordIdx()));
            Solution(slotStack, visitedWord);
        } else {

            slotStack.push(slot);
            if (slot.getOrientation() == Orientation.Horizontal) {
                puzzle.removeHorizontal(slot.getRow(), slot.getColumn(), slot.getSize());
            } else {
                puzzle.removeVertical(slot.getRow(), slot.getColumn(), slot.getSize());
            }

            Solution(slotStack, visitedWord);
        }
        return false;
    }


    public void print(PrintWriter outstream) {
        char[][] grid = puzzle.getGrid();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '\0') {
                    outstream.print("  ");
                } else {
                    outstream.print(grid[i][j]+" ");
                }
            }
            outstream.println();
        }
        outstream.flush();
    }

    private void mapToSlot(Slot slot) {
        int idx = slot.getSize();
        slotMap.putIfAbsent(idx, new ArrayList<>());

        slotMap.get(idx).add(slot);
    }

    public int choices() {
        return this.choice;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public List<Slot> getSlots() {
        return slots;
    }
}

