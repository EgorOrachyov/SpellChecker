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

    public static void main(String ... args) {
        IDictionary dictionary = new Dictionary();
        try {
            dictionary.loadDefaultDict("resource/dict-english-default.txt");
            dictionary.loadCustomDict("resource/dict-english-custom.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] words = new String[]{ "egor", "the", "A", "blaBlaBla", "terraform" };

        for (String word : words) {
            System.out.println(dictionary.containsWord(word) + " " + word);
            System.out.println(dictionary.isUserWord(word) + " " + word);
        }
    }

}
