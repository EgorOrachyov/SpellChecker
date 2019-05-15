import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Dictionary implements IDictionary {

    private static int DEFAULT_SIMILARITY_RANGE = 4;
    private static long DEFAULT_WORD_USAGE = -1L;

    private HashMap<String, Long> dictionary = new HashMap<>();
    private HashSet<String> userWords = new HashSet<>();
    private int similarity = DEFAULT_SIMILARITY_RANGE;

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
                userWords.add(word);
                dictionary.put(word, DEFAULT_WORD_USAGE);
            }
        }
    }

    @Override
    public void setSimilarityRange(int range) {
        if (range > 0) {
            similarity = range;
        }
    }

    @Override
    public int getSimilarityRange() {
        return 0;
    }

    @Override
    public boolean containsWord(String word) {
        return dictionary.containsKey(word);
    }

    @Override
    public boolean isUserWord(String word) {
        return userWords.contains(word);
    }

    @Override
    public List<String> getSimilarWords(String word) {
        return null;
    }

}
