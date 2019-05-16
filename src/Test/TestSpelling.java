package Test;

import Common.SpellChecker;
import Interfaces.ISpellChecker;
import Utils.DictionaryLoader;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

public class TestSpelling extends Assert {

    private static final ISpellChecker spellChecker;

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

    @Test
    public void test() {
        String[] words = new String[] {
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", ""
        };

        for (String word : words) {
            System.out.println(word);
            spellChecker.getSuggestions(word);
        }
    }

    @Test
    public void spellCheckTraverseTest() {
        String word = "understangind";
        ISpellChecker.CheckResult result = spellChecker.getSuggestions(word);

        System.out.println("Word: " + word + " found: " + spellChecker.contains(word));

        if (result.getOptions().size() > 0) {
            System.out.println("Possible suggestions from dictionary");
            for (String s : result.getOptions()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No suggestions from dictionary");
        }

        if (result.getUserDefinedOptions().size() > 0) {
            System.out.println("Possible suggestions from user words");
            for (String s : result.getUserDefinedOptions()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No suggestions from user words");
        }
    }

}
