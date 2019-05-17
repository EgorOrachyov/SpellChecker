package Console;

import Common.SpellChecker;
import Interfaces.ISpellChecker;
import Utils.DictionaryLoader;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private final String EXIT = "__exit__";
    private final Scanner in;
    private final PrintStream out;
    private final ISpellChecker spellChecker;

    public ConsoleApplication() {
        in = new Scanner(System.in);
        out = System.out;

        HashMap<String, Long> dictData = new HashMap<>();
        HashSet<String> userData = new HashSet<>();

        try {
            DictionaryLoader.loadDefaultDict("resource/dict-english-default.txt", dictData);
            DictionaryLoader.loadCustomDict("resource/dict-english-custom.txt", dictData, userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        spellChecker = new SpellChecker();
        spellChecker.addDictionary(dictData);
        spellChecker.addUserData(userData);
    }

    public void run() {
        printInfo();
        while (true) {
            out.print("\nInput: ");
            String word = in.next();

            if (word.equals("__exit__")) {
                break;
            }

            boolean isCorrect = spellChecker.contains(word);
            boolean isUserDefined = spellChecker.userDefined(word);

            if (isCorrect) {
                if (isUserDefined) {
                    out.println("correct spelling [user word]");
                } else {
                    out.println("correct spelling");
                }
            }
            else {
                ISpellChecker.CheckResult result = spellChecker.getSuggestions(word);

                List<String> fromDict = result.fromDict();
                List<String> fromUserData = result.fromUserData();

                if (!fromDict.isEmpty()) {
                    out.println("Possible suggestions");
                    for (String w : fromDict) {
                        out.println(w);
                    }
                }

                if (!fromUserData.isEmpty()) {
                    out.println("Possible suggestions [user words]");
                    for (String w : fromUserData) {
                        out.println(w);
                    }
                }
            }
        }
    }

    private void printInfo() {
        out.println("+--------------------------------------------+");
        out.println("|                SpellChecker                |");
        out.println("+--------------------------------------------+");
        out.println("| Enter word to check, whether it is correct |");
        out.println("| or not (suggestions will be generated      |");
        out.println("| (enter __exit__ to exit the application)   |");
        out.println("+--------------------------------------------+");
    }

}
