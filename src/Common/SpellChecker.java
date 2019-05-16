package Common;

import Interfaces.ISearchTree;
import Interfaces.ISimilarityFactor;
import Interfaces.ISpellChecker;
import Utils.CharacterArray;
import Utils.WordData;
import Utils.WordDataComparator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SpellChecker implements ISpellChecker {

    private final int DEFAULT_EDIT_OPERATIONS_COUNT = 3;
    private final int DEFAULT_SUGGESTIONS_COUNT = 10;

    private int editsCount = DEFAULT_EDIT_OPERATIONS_COUNT;
    private int suggestionsCount = DEFAULT_SUGGESTIONS_COUNT;

    private final Map<String, Long> dictionary;
    private final Set<String> userData;
    private final ISearchTree<Character, Long> lookup = new PrefixTree<>();

    private ISimilarityFactor factor = new LevenshteinDistanceMatrix();

    private String input;
    private int inputLength;

    private final ArrayList<WordData> suggestions = new ArrayList<>();
    private final ArrayList<WordData> userDataSuggestions = new ArrayList<>();

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
        input = word;
        inputLength = word.length();
        suggestions.clear();
        userDataSuggestions.clear();

        traverseTree(word.charAt(0) + "", lookup.getRoot().getChildNodes().get(word.charAt(0)));

        suggestions.sort(new WordDataComparator());

        for (WordData data : suggestions) {
            System.out.println(data.getWord() + " " + data.getEditsCount() + " " + data.getUsageCount());
        }

        userDataSuggestions.sort(new WordDataComparator());

        for (WordData data : userDataSuggestions) {
            System.out.println(data.getWord() + " " + data.getEditsCount() + " " + data.getUsageCount());
        }

        return null;
    }

    private void traverseTree(String current, ISearchTree.ITreeNode<Character, Long> node) {
        final int currentLength = current.length();
        final int metric = factor.similarity(input, current);
        final int delta = Math.abs(inputLength - currentLength);

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
