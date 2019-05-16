package Common;

import Interfaces.ISearchTree;
import Interfaces.ISimilarityFactor;
import Interfaces.ISpellChecker;
import Utils.CharacterArray;
import Utils.WordData;
import Utils.WordDataComparator;

import java.util.*;

public class SpellChecker implements ISpellChecker {

    private final int DEFAULT_EDIT_OPERATIONS_COUNT = 3;
    private final int DEFAULT_SUGGESTIONS_COUNT = 10;

    private int editsCount = DEFAULT_EDIT_OPERATIONS_COUNT;
    private int suggestionsCount = DEFAULT_SUGGESTIONS_COUNT;

    private final Set<String> userData;
    private final ISearchTree<Character, Long> lookup;

    private ISimilarityFactor factor = new LevenshteinDistanceMatrix();

    private String input;
    private int inputLength;

    private final PriorityQueue<WordData> suggestions;
    private final PriorityQueue<WordData> userDataSuggestions;

    public SpellChecker() {
        userData = new HashSet<>();
        lookup = new PrefixTree<>();

        WordDataComparator comparator = new WordDataComparator();
        suggestions = new PriorityQueue<>(suggestionsCount, comparator);
        userDataSuggestions = new PriorityQueue<>(suggestionsCount, comparator);
    }

    public SpellChecker(Map<String,Long> dict, Set<String> userData) {
        this();
        addDictionary(dict);
        addUserData(userData);
    }

    @Override
    public void addDictionary(Map<String, Long> data) {
        for (Map.Entry<String, Long> word : data.entrySet()) {
            Character[] keys = CharacterArray.convert(word.getKey());
            if (!lookup.contains(keys)) {
                lookup.put(keys, word.getValue());
            }
        }
    }

    @Override
    public void addUserData(Set<String> data) {
        userData.addAll(data);
    }

    @Override
    public boolean contains(String word) {
        return lookup.contains(CharacterArray.convert(word));
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
    public void setFactor(ISimilarityFactor factor) {
        this.factor = factor;
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
    public ISimilarityFactor getFactor() {
        return factor;
    }

    @Override
    public CheckResult getSuggestions(String word) {
        return getSuggestions(word, true);
    }

    @Override
    public CheckResult getSuggestions(String word, boolean firstLettersAreEqual) {
        if (word.isEmpty()) {
            return new CheckResult();
        }

        input = word.toLowerCase();
        inputLength = word.length();
        suggestions.clear();
        userDataSuggestions.clear();

        if (firstLettersAreEqual) {
            traverseTree(word.charAt(0) + "", lookup.getRoot().getChildNodes().get(word.charAt(0)));
        } else {
            traverseTree("", lookup.getRoot());
        }

        ArrayList<String> fromDict = new ArrayList<>(suggestionsCount);
        ArrayList<String> fromUserData = new ArrayList<>(suggestionsCount);

        while (fromDict.size() < suggestionsCount && !suggestions.isEmpty()) {
            fromDict.add(suggestions.poll().getWord());
        }

        while (fromDict.size() < suggestionsCount && !userDataSuggestions.isEmpty()) {
            fromUserData.add(userDataSuggestions.poll().getWord());
        }

        return new CheckResult(fromDict, fromUserData);
    }

    private void traverseTree(String current, ISearchTree.ITreeNode<Character, Long> node) {
        final int currentLength = current.length();
        final int metric = factor.similarity(input, current);

        if (node.leaf()) {
            if (metric <= editsCount) {
                WordData suggestion = new WordData(current, metric, node.getValue());
                suggestions.add(suggestion);
                if (userDefined(current)) {
                    userDataSuggestions.add(suggestion);
                }
            }
        }
        else if (factor.similarity(input.substring(0, Math.min(inputLength, currentLength)), current) >= editsCount) {
            return;
        }

        for (Map.Entry<Character, ? extends ISearchTree.ITreeNode<Character, Long>> child : node.getChildNodes().entrySet()) {
            traverseTree(current + child.getKey(), child.getValue());
        }
    }


}
