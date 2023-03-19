import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader steam= new BufferedReader(new FileReader("puzzle.txt"));
        PrintWriter outstream= new PrintWriter(new FileWriter("output.txt"));

        FillInPuzzle fp= new FillInPuzzle();

        System.out.println(fp.loadPuzzle(steam));
        fp.solve();
        System.out.println("a");
        System.out.println(fp.choices());
        System.out.println(fp.getPuzzle().toString());
        fp.print(outstream);

    }

}
