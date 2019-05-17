package Test;

import Common.SpellChecker;
import Interfaces.ISpellChecker;
import Utils.DictionaryLoader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

@RunWith(Parameterized.class)
public class TestSpelling extends Assert {

    private static final ISpellChecker spellChecker;
    private final String word;

    static {
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

    public TestSpelling(String word) {
        this.word = word;
    }

    @Parameterized.Parameters
    public static Object[] inputWords() {
        return new Object[] {
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna"
        };
    }

    @Test
    public void spellCheckTraverseTest() {
        spellChecker.setMaxEditOperationCount(3);
        spellChecker.setMaxSuggestionsCount(8);

        ISpellChecker.CheckResult result = spellChecker.getSuggestions(word);

        System.out.println("Word: " + word + " | correct: " +
                spellChecker.contains(word) + " | user defined: " +
                spellChecker.userDefined(word));

        if (result.fromDict().size() > 0) {
            System.out.println("Possible suggestions from dictionary");
            for (String s : result.fromDict()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No suggestions from dictionary");
        }

        if (result.fromUserData().size() > 0) {
            System.out.println("Possible suggestions from user words");
            for (String s : result.fromUserData()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No suggestions from user words");
        }
    }

}
