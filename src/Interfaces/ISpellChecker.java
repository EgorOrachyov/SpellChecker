package Interfaces;

import java.util.List;

public interface ISpellChecker {

    class CheckResult {

        private List<String> options;
        private List<String> userDefinedOptions;

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

    void setMaxSuggestionsCount(int count);

    void setMaxEditOperationCount(int count);

    void setFactor(ISimilarityFactor factor);

    int getMaxSuggestionsCount();

    int getMaxEditOperationCount();

    ISimilarityFactor getFactor();

    boolean contains(String word);

    boolean userDefined(String word);

    CheckResult getSuggestions(String word);

}
