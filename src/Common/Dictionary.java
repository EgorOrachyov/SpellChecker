package Common;

import Interfaces.IDictionary;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary implements IDictionary {

    private static long DEFAULT_WORD_USAGE = -1L;

    private HashMap<String, Long> dictionary = new HashMap<>();
    private HashSet<String> userWords = new HashSet<>();

    @Override
    public void loadDefaultDict(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            dictionary.put(in.next(), in.nextLong());
        }
    }

    @Override
    public void loadCustomDict(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            String word = in.next().toLowerCase();
            if (!dictionary.containsKey(word)) {
                dictionary.put(word, DEFAULT_WORD_USAGE);
                userWords.add(word);
            }
        }
    }

    @Override
    public boolean containsWord(String word) {
        return dictionary.containsKey(word.toLowerCase());
    }

    @Override
    public boolean isUserWord(String word) {
        return userWords.contains(word.toLowerCase());
    }

    @Override
    public Set<Map.Entry<String, Long>> getRawData() {
        return dictionary.entrySet();
    }

}
