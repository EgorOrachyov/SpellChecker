package Common;

import Interfaces.ISearchTree;
import Interfaces.ISpellChecker;
import Utils.CharacterArray;

import java.util.Map;
import java.util.Set;

public class SpellChecker implements ISpellChecker {

    private final int DEFAULT_EDIT_OPERATIONS_COUNT = 4;
    private final int DEFAULT_SUGGESTIONS_COUNT = 10;

    private int editsCount = DEFAULT_EDIT_OPERATIONS_COUNT;
    private int suggestionsCount = DEFAULT_SUGGESTIONS_COUNT;

    private Map<String, Long> dictionary;
    private Set<String> userData;
    private ISearchTree<Character, Long> lookup = new PrefixTree<>();

    public SpellChecker(Map<String,Long> dict, Set<String> userData) {
        this.dictionary = dict;
        this.userData = userData;

        for (Map.Entry<String, Long> word : dictionary.entrySet()) {
            lookup.put(CharacterArray.convert(word.getKey()), word.getValue());
        }
    }

    @Override
    public boolean contains(String word) {
        return dictionary.containsKey(word);
    }

    @Override
    public boolean userDefined(String word) {
        return userData.contains(word);
    }

    @Override
    public void setMaxEditOperationCount(int count) {
        assert (count > 0);
        editsCount = count;
    }

    @Override
    public void setMaxSuggestionsCount(int count) {
        assert (count > 0);
        suggestionsCount = count;
    }

    @Override
    public int getMaxEditOperationCount() {
        return editsCount;
    }

    @Override
    public int getMaxSuggestionsCount() {
        return suggestionsCount;
    }

    @Override
    public CheckResult getSuggestions(String word) {
        return null;
    }



}
