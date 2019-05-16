package Interfaces;

import java.util.List;

public interface ISpellChecker {

    class CheckResult {

        private boolean correct;
        private List<String> options;
        private List<String> userDefinedOptions;

        public CheckResult(boolean correct, List<String> options, List<String> userDefinedOptions) {
            this.correct = correct;
            this.options = options;
            this.userDefinedOptions = userDefinedOptions;
        }

        public List<String> getOptions() {
            return options;
        }

        public List<String> getUserDefinedOptions() {
            return userDefinedOptions;
        }

        public boolean isCorrect() {
            return correct;
        }
    }

    void setMaxSuggestionsCount(int count);

    void setMaxEditOperationCount(int count);

    int getMaxSuggestionsCount();

    int getMaxEditOperationCount();

    boolean contains(String word);

    boolean userDefined(String word);

    CheckResult getSuggestions(String word);

}
