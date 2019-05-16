package Interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Spell-checker allows to check word whether it is written
 * correctly or not. If there is some kind of mistake, speller should
 * suggest a list of possible words, which could fix the mistake[s].
 *
 * Supports adding dictionaries (as simple associative containers) and
 * user data (defined words) to explicitly check, whether the user wanted to
 * write a word from his own list.
 */
public interface ISpellChecker {

    /**
     * Add dictionary data in this speller
     * @param data Map with words and their usage case numbers
     */
    void addDictionary(Map<String, Long> data);

    /**
     * Add user defined words set for explicit check
     * @param data Words defined by he user
     */
    void addUserData(Set<String> data);

    /**
     * Set max count of suggestions for words found
     * in the dictionary and words from user data.
     * Total suggestion count = 2 * count (for dict and user data)
     * @param count Non negative number to limit suggestions
     */
    void setMaxSuggestionsCount(int count);

    /**
     * Set max count of editing operations
     * (or metric number) to ignore words, which has the editing number
     * more than this one
     * @param count Non negative number to limit metric value
     */
    void setMaxEditOperationCount(int count);

    /**
     * Set metric used to choose similar words
     * @param factor Metric which will be used
     */
    void setFactor(ISimilarityFactor factor);

    /**
     * @return  max count of suggestions
     */
    int getMaxSuggestionsCount();

    /**
     * @return max count of editing operations
     */
    int getMaxEditOperationCount();

    /**
     * @return Similarity factor
     */
    ISimilarityFactor getFactor();

    /**
     * @param word Word to look up
     * @return True if this word presented in the dictionary
     */
    boolean contains(String word);

    /**
     * @param word Word to look up
     * @return True if this word is defined as used data
     */
    boolean userDefined(String word);

    /**
     * Allows to get suggestion for this word: the most
     * similar (in some way) words
     * @param word Word to be checked
     * @return Container with suggestions found in the
     *         dictionary and user data
     */
    CheckResult getSuggestions(String word);

    /**
     * Allows to get suggestion for this word: the most
     * similar (in some way) words
     * @param word Word to be checked
     * @param firstLetters Enable this flag to find words, which begin with
     *                     the same letter, as the input word
     * @return Container with suggestions found in the
     *         dictionary and user data
     */
    CheckResult getSuggestions(String word, boolean firstLetters);

    /**
     * Container to store suggestion from dictionary and
     * suggestions based on user defined words
     */
    class CheckResult {

        private List<String> options;
        private List<String> userDefinedOptions;

        public CheckResult() {
            this.options = new ArrayList<>();
            this.userDefinedOptions = new ArrayList<>();
        }

        public CheckResult(List<String> options, List<String> userDefinedOptions) {
            this.options = options;
            this.userDefinedOptions = userDefinedOptions;
        }

        public List<String> getOptions() {
            return options;
        }

        public List<String> getUserDefinedOptions() {
            return userDefinedOptions;
        }

    }

}
