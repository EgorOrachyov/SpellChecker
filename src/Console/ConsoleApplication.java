package Console;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication {

    private final Scanner in;
    private final PrintStream out;

    public ConsoleApplication() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public void run() {
        printInfo();
    }

    private void printInfo() {
        out.println("+-----------------------------------------+");
        out.println("|               SpellChecker              |");
        out.println("+-----------------------------------------+");
        out.println("| Options:                                |");
        out.println("| 1. check spelling                       |");
        out.println("| 2. set 'editing operations max count'   |");
        out.println("| 3. set 'suggestions max count'          |");
        out.println("+-----------------------------------------+");
    }

}
