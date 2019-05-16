package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Utility class to load simple dictionaries from file
 * File format: word1 usage1
 *              word2 usage2
 *              word3 usage3
 *              ...
 *              wordN usageN
 *
 * Or file format for dictionary without usage info
 * File format: word1
 *              word2
 *              word3
 *              ...
 *              wordN
 */
public class DictionaryLoader {

    /** Mark words which do not have info about usage count */
    private static final long DEFAULT_WORD_USAGE = -1L;

    /**
     * Load dictionary
     * @param path Relative or full path to the file on HDD
     * @param dict Initialized container to save data
     * @throws FileNotFoundException If specified [via path] file is not fount
     */
    public static void loadDefaultDict(String path, Map<String, Long> dict)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            dict.put(in.next(), in.nextLong());
        }
    }

    /**
     * User defined dictionary
     * @param path Relative or full path to the file on HDD
     * @param dict Initialized container to save data
     * @param userDefined Initialized container to save only user defined data
     * @throws FileNotFoundException If specified [via path] file is not fount
     */
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

    /**
     * Load user defined dictionary without info about usages
     * @param path Relative or full path to the file on HDD
     * @param dict Initialized container to save data
     * @param userDefined Initialized container to save only user defined data
     * @throws FileNotFoundException If specified [via path] file is not fount
     */
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
