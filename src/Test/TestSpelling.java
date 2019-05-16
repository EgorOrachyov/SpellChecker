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
                "lol", "heee", "ddddd", "akkfna",
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna",
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna",
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna",
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna",
                "swimmer", "kitten", "cat", "ocean", "egor",
                "the", "A", "blaBlaBla", "terraform", "soil",
                "house", "horse", "label", "list", "map", "array",
                "shader", "cpu", "graphics", "engine", "gog",
                "lol", "heee", "ddddd", "akkfna",
        };

        for (String word : words) {
            //System.out.println(word);
            spellChecker.getSuggestions(word);
        }
    }

    @Test
    public void spellCheckTraverseTest() {
        String word = "swimmer";

        spellChecker.setMaxEditOperationCount(3);
        spellChecker.setMaxSuggestionsCount(8);

        ISpellChecker.CheckResult result = spellChecker.getSuggestions(word);

        System.out.println("Word: " + word + " correct: " + spellChecker.contains(word));

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
