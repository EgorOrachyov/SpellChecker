package Interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISpellChecker {

    void addDictionary(Map<String, Long> data);

    void addUserData(Set<String> data);

    void setMaxSuggestionsCount(int count);

    void setMaxEditOperationCount(int count);

    void setFactor(ISimilarityFactor factor);

    int getMaxSuggestionsCount();

    int getMaxEditOperationCount();

    ISimilarityFactor getFactor();

    boolean contains(String word);

    boolean userDefined(String word);

    CheckResult getSuggestions(String word);

    CheckResult getSuggestions(String word, boolean firstLettersAreEqual);

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
