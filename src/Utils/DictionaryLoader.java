package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DictionaryLoader {

    public static final long DEFAULT_WORD_USAGE = -1L;

    public static void loadDefaultDict(String path, Map<String, Long> dict)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            dict.put(in.next(), in.nextLong());
        }
    }

    public static void loadCustomDict(String path, Map<String, Long> dict, Set<String> userDefined)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            String word = in.next().toLowerCase();
            Long usage = in.nextLong();
            if (!dict.containsKey(word)) {
                dict.put(word, usage);
                userDefined.add(word);
            }
        }
    }

    public static void loadCustomDictWithoutUsages(String path, Map<String, Long> dict, Set<String> userDefined)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            String word = in.next().toLowerCase();
            if (!dict.containsKey(word)) {
                dict.put(word, DEFAULT_WORD_USAGE);
                userDefined.add(word);
            }
        }
    }

}
